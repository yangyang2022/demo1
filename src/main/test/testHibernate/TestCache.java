package testHibernate;

import com.yangyang.model.HQLDemo1.Student;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by syy on 2017/1/23.
 */
public class TestCache {
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
    public void testLoad1() {

        //List<Student> students = session.createQuery("from Student ", Student.class)
        //        .setFirstResult(0).setMaxResults(10).list();
        //
        //students.forEach(e-> System.out.println(e.getName()));


        //如果使用Iterator,hibernate仅仅select id ,再发出相应的的sql取出所有的学生的信息
        //这就是N+1问题 --> 避免: 不要使用iterator
        //但是iterator查询出所有的学生会缓存在一级缓存里面
        session.createQuery("from Student", Student.class)
                .setFirstResult(0).setMaxResults(10)
                .iterate()
                .forEachRemaining(e-> System.out.println(e.getName()));

        System.out.println("==================================================");

        //session.createQuery("from Student", Student.class)
        //        .setFirstResult(0).setMaxResults(10)
        //        .iterate()
        //        .forEachRemaining(e-> System.out.println(e.getName()));

        session.clear(); //存在session里面,一旦关闭就会从新发出sql

        Student student = session.load(Student.class, 1282);
        System.out.println(student.getName()+" <--- ");



    }
}
