package testHibernate;

import com.yangyang.model.annotation.*;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class TestHibernateAnno {

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

        //ClassRoom classRoom1 = new ClassRoom("class One");
        //ClassRoom classRoom2 = new ClassRoom("class Two");
        //Student stu1 = new Student("yangyang1", classRoom1);
        //Student stu2 = new Student("yangyang2", classRoom2);
        //
        //save(classRoom1,classRoom2,stu1,stu2);

    }

    @Test
    public void testCreateTable() {

    }
    @Test
    public void testSaveUser() {
        User yangyang1 = new User("yangyang1", "123123", LocalDate.now());
        User yangyang2 = new User("yangyang2", "123123", LocalDate.now());

        save(yangyang1,yangyang2);
    }

    @Test
    public void testSaveStudentClassRoom() {

        ClassRoom class1 = new ClassRoom("classOne", 2013);
        ClassRoom class2 = new ClassRoom("classOne", 2014);
        Student stu1 = new Student("洋洋1", 2011080329, class1);
        Student stu2 = new Student("洋洋2", 2011080329, class2);
        Student stu3 = new Student("洋洋3", 2011080329, class2);
        Student stu4 = new Student("洋洋4", 2011080329, class1);

        save(class1,class2,stu1,stu2,stu3,stu4);

    }

    @Test
    public void testLoad1() {
        Student student = session.get(Student.class, 1);
        System.out.println(student.getName());
        System.out.println(student.getClassRoom().getName());

    }

    @Test
    public void testLoad2() {
        ClassRoom classRoom = session.load(ClassRoom.class, 1);
        System.out.println(classRoom.getStudents().size());
    }

    @Test
    public void testone2one() {
        Person yangyang1 = new Person("yangyang1");
        Person yangyang2 = new Person("yangyang2");
        IdCard id1 = new IdCard("123123", yangyang1);
        IdCard id2 = new IdCard("123456", yangyang2);

        save(yangyang1,yangyang2,id1,id2);

    }

    @Test
    public void testLoadOne2one() {
        IdCard id1 = session.load(IdCard.class, 1);
        System.out.println(id1.getNumber());
        System.out.println(id1.getPerson().getName());
    }

    @Test
    public void testManyToMany() {
        Admin admin1 = new Admin("admin1");
        Admin admin2 = new Admin("admin2");
        Admin admin3 = new Admin("admin3");

        Role role1 = new Role("role1");
        Role role2 = new Role("role2");

        role1.getAdmins().addAll(Arrays.asList(admin1,admin2,admin3));
        role2.getAdmins().addAll(Arrays.asList(admin2,admin3));

        save(admin1,admin2,admin3,role1,role2);

    }

    @Test
    public void testTeacherCourse() {
        Teacher yangyang = new Teacher("yangyang");
        Teacher liubing = new Teacher("liubing");

        Course chinese = new Course("Chinese");
        Course math = new Course("math");
        Course english = new Course("english");

        TeacherCourse tcs1 = new TeacherCourse(1.1, yangyang, chinese);
        TeacherCourse tcs2 = new TeacherCourse(1.2, yangyang, english);

        TeacherCourse tcs3 = new TeacherCourse(2.1, liubing, chinese);
        TeacherCourse tcs4 = new TeacherCourse(2.2, liubing, math);
        TeacherCourse tcs5 = new TeacherCourse(2.3, liubing, english);

        save(yangyang,liubing,chinese,math,english,tcs1,tcs2,tcs3,tcs4,tcs5);


    }
}
