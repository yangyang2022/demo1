package testHibernate;

import com.yangyang.model.HQLDemo1.ClassRoom;
import com.yangyang.model.HQLDemo1.Student;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestFetch {

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

        //xml will luanch 3 sql, annotation luanch 1 sql
        //Student student = session.load(Student.class, 1);
        //System.out.println(student.getName() );

        List<Student> students = session.createQuery("from Student stu join fetch stu.classRoom clz join fetch clz.special spe", Student.class).list();
        students.forEach(e-> System.out.println(e.getName()));

        //Long countStudents = session.createQuery("select count (*)from Student stu join fetch stu.classRoom clz join fetch clz.special spe", Long.class).uniqueResult();
        //System.out.println("count: "+countStudents);

    }

    @Test
    public void testLoad2() {
        ClassRoom classRoom = session.load(ClassRoom.class, 1);
        System.out.println(classRoom.getName());

        classRoom.getStudents().forEach(e-> System.out.println(e.getName()));

    }
}
