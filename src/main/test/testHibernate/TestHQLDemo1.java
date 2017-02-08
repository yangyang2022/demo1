package testHibernate;

import com.yangyang.model.DTO.StudentDto;
import com.yangyang.model.HQLDemo1.Student;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestHQLDemo1 {

    private Session session;

    private Transaction transaction;

    @Before
    public void setUp(){
        session = HibernateUtil.openSession();
        transaction = session.beginTransaction();
    }
    @After
    public void tearDown(){
        transaction.commit();
        HibernateUtil.closeSession(session);
    }

    private void save(Object ...objects){
        for(Object obj : objects) session.save(obj);
    }

    @Test
    public void testDemo1() {
        //serach all special,can't use select * from ...
        //List<Special> list = session.createQuery("from Special ",Special.class).list();
        //list.forEach(e-> System.out.println(e.getName()));

        //List<Student> students = session.createQuery("from Student where name like '%张%'", Student.class).list();
        //students.forEach(e-> System.out.println(e.getName()));
        //System.out.println(students.size());

        //List<Student> students1 = session.createQuery("from Student  where name like ?", Student.class)
        //        .setParameter(0, "%李%").list();
        //students1.forEach(e-> System.out.println(e.getName()));

        //List<Student> students = session.createQuery("from Student where name like :name and sex=:sex", Student.class)
        //        .setParameter("name", "%刘%")
        //        .setParameter("sex", "男")
        //        .list();
        //students.forEach(e-> System.out.println(e.getName()+" : "+e.getSex()));

        Long countStus = session.createQuery("select count (*)from Student where name like :name and sex=:sex",
                Long.class)
                .setParameter("name", "%刘%")
                .setParameter("sex", "男")
                .uniqueResult();

        System.out.println("stus:　"+countStus);

    }

    @Test
    public void testLoad1() {
        // 基于投影的查询
        List<Object[]> list = session.createQuery("select sex,count (*)from Student group by sex", Object[].class)
                .list();
        for(Object[] obj: list){
            System.out.println(obj[0]+" :　"+obj[1]);
        }
    }


    @Test
    public void testLoad2() {
        //基于导航的查询
        List<Student> list = session.createQuery("from Student stu where stu.classRoom.id=?", Student.class)
                .setParameter(0, 1)
                .list();
        list.forEach(e-> System.out.println(e.getName()+" : "+e.getClassRoom().getName()));
    }

    @Test
    public void testLoad3() {
        //查询1班和2班的男生 只能使用别名(注意列表必须在其他查询之后并且为setParameterList)
        List<Student> list = session.createQuery("select stu from Student stu where stu.name like ? and stu.classRoom.id in (:clz)", Student.class)
                .setParameter(0,"%张%").setParameterList("clz",new Integer[]{1,2})
                .list();

        System.out.println("size: "+list.size());

        //list.forEach(e-> System.out.println(e.getName()));
    }

    @Test
    public void testLoad4() {
        //分页查询
        List<Student> list = session.createQuery("select stu from Student stu where stu.name like ? and stu.classRoom.id in (:clz)", Student.class)
                .setParameter(0,"%张%").setParameterList("clz",new Integer[]{1,2})
                .setFirstResult(0).setMaxResults(2)
                .list();
        System.out.println("size: "+list.size());

    }

    @Test
    public void testLoad5() {
        //查询班级为null的班级

        List<Student> students = session.createQuery("select stu from Student stu where stu.classRoom.id is null ",
                Student.class)
                .setFirstResult(2).setMaxResults(10)
                .list();

        students.forEach(e-> System.out.println(e.getName()));
    }

    @Test
    public void testLoad6() {
        //导航链接是基于cross链接 效率不高...
        List<Student> students = session.createQuery("select stu from Student stu left join stu.classRoom clz where clz.id=2",
                Student.class)
                .setFirstResult(0).setMaxResults(10).list();

        System.out.println(students.size());
    }

    @Test
    public void testLoad7() {
        //统计每个班多少人
        List<Object[]> list = session.createQuery(
                "select clz.name,count (stu.id) from Student stu right join stu.classRoom clz group by clz.id",
                Object[].class).list();

        for(Object[] obj : list){
            System.out.println(obj[0] + " : " + obj[1]);
        }
    }

    @Test
    public void testLoad8() {
        //获取student完整信息---> 需要加上完整包名
        List<StudentDto> list = session.createQuery(
                "select new com.yangyang.model.DTO.StudentDto(stu.id,stu.name,stu.sex,clz.name,spe.name) " +
                        "from Student stu left join stu.classRoom clz left join clz.special spe ",
                StudentDto.class).list();

        list.forEach(e-> System.out.println(e));


        //for(Object[] obj : list){
        //    System.out.println(obj[0]+" : "+obj[1]+" : "+obj[2]+" : "+obj[3]+" : "+obj[4]);
        //}
    }

    @Test
    public void testLoad9() {
        //统计专业里面不同性别的人数
        List<Object[]> list = session.createQuery(
                "select spe.name,stu.sex,count(stu.id) from Student stu right join stu.classRoom clz right join clz.special spe " +
                        "group by spe.id,stu.sex having count(stu.id) > 30",
                Object[].class).list();

        for(Object[] obj: list){
            System.out.println(obj[0]+" : "+obj[1]+" : "+obj[2]);
        }

    }

    
}
