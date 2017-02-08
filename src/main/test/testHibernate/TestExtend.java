package testHibernate;

import com.yangyang.model.subClass.Person;
import com.yangyang.model.subClass.Student;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by syy on 2017/1/23.
 */
public class TestExtend {
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
    public void testSave() {

        Person person1 = new Person("person1", 11);
        Person person2 = new Person("person2", 12);

        Student student1 = new Student("student1", 21, "Tinghua");
        Student student2 = new Student("student1", 22, "Beida");

        save(person1,person2,student1,student2);
    }

    /**
     * 使用 sub-class
     * 缺点: 1:添加了辨别者列 2:子类独有字段不能添加非空约束 3:若继承体系较深,则表比较大
     */
    @Test
    public void testQuery1() {
        //Person person = session.load(Person.class, 1);
        //System.out.println(person.getName());

        List<Person> list = session.createQuery("from Person ", Person.class).list();
        System.out.println(list.size());

        //查出了所有的子类
        list.forEach(e-> System.out.println(e.getName()));

        List<Student> list1 = session.createQuery("from Student ", Student.class).list();
        System.out.println(list1.size());

    }

    /**
     * 使用joined-class
     * 优点: 没有冗余字段
     * 缺点: 1:插入父类查一张表,插入子类插入2张表,插入性能差一些
     *      2:多态查询需要连接两张表 查询能力稍弱
     */
    @Test
    public void testJoinedClass() {
        testQuery1();
    }

    /**
     * 使用 Unoin查询
     *  1: 查询子类: 只需要查询一张表
     *  2: 查询父类,需要将父表和字表联合在一起查询
     *  3: 跟新麻烦 --> update 效率较低
     */
    @Test
    public void testUnionClass() {
        testQuery1();
    }

    @Test
    public void testUpdate() {
        session.createQuery("update Person p set p.age = 33").executeUpdate();

    }
}
