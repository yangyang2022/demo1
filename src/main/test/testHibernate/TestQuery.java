package testHibernate;

import com.yangyang.model.hqlDemo2.Department;
import com.yangyang.model.hqlDemo2.Employee;
import com.yangyang.util.HibernateUtil;
import com.yangyang.util.NameUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by syy on 2017/1/23.
 */
public class TestQuery {
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

    private <E> void  printColl(Collection<E> coll){
        coll.forEach(System.out::println);
    }
    private void printDeptName(Collection<Department> coll){
        coll.forEach(e-> System.out.println(e.getName()));
    }
    @Test
    public void testQuery() {
        Random random = new Random();
        String[] strings = {"财务部", "销售部", "运营部", "开发部", "维护部", "售后部", "售前部", "行政部"};

        for (int i = 0; i < strings.length; ++i) {
            save(new Department(strings[i]));
        }

        save(new Employee("admin",(float) 1.1,"email@qq.com",new Department(1+random.nextInt(8))));

        NameUtil.getNames(500)
                .forEach(e->save(new Employee(e,(float) random.nextInt(1000),
                        "yangyang@qq.com",
                        new Department(1+random.nextInt(8)))));
    }

    @Test
    public void testQuery1() {
        List<Department> departments = session.createQuery("from Department d where d.id > :id order by d.id", Department.class)
                .setParameter("id", 3)
                .list();
        System.out.println(departments.size());

        printDeptName(departments);
    }

    @Test
    public void testPageQuery() {
        int pageNo = 3;
        int pageSize = 10;
        List<Employee> employees = session.createQuery("from Employee ", Employee.class)
                .setFirstResult((pageNo - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();

        printColl(employees);

    }

    @Test
    public void testNameQuery() {
        List<Employee> employees = session.createNamedQuery("salaryEmps", Employee.class)
                .setParameter("minSalary", (float)100.0).setParameter("maxSalry", (float)500.0).list();

        printColl(employees);
    }

    @Test
    public void testReflect() {
        List<Object[]> list = session
                .createQuery("select e.name,e.email,e.email,e.department.name from Employee e where e.department.id=1", Object[].class)
                .list();
        for(Object[] obj : list){
            System.out.println(Arrays.asList(obj));
        }
    }

    @Test
    public void testGroupBy() {
        List<Object[]> list = session.createQuery(
                "select e.department.name,min(e.salary),max(e.salary) " +
                        "from Employee e " +
                        "group by e.department " +
                        "having min(e.salary) > :minSalary", Object[].class)
                .setParameter("minSalary",(float)10)
                .list();
        for(Object[] obj:list){
            System.out.println(Arrays.asList(obj));
        }
    }

    @Test
    public void testJoin() {
        List<Department> departments = session.createQuery("select distinct d " +
                "from Department d " +
                "left join fetch d.employees", Department.class)
                .list();
        System.out.println(departments.size());

        departments.forEach(e-> System.out.println(e.getName()+" : "+e.getEmployees().size()));
    }

    @Test
    public void testLeftJoin2() {
        List<Employee> employees = session.createQuery("from Employee e left join fetch e.department dep", Employee.class)
                .list();
        System.out.println(employees.size());

        System.out.println("department: "+employees.get(0).getDepartment().getName());

    }

    @Test
    public void testNativeSql() {
        Employee employee = session.createNativeQuery("select * from t_employee where id=11", Employee.class)
                .uniqueResult();
        System.out.println(employee);
    }

    @Test
    public void testSecondLevelCache() {

        // send 2 sql
        //Employee employee = session.createQuery("from Employee e where e.id = 11", Employee.class).uniqueResult();
        //System.out.println(employee.getName());
        //
        //Employee employee2 = session.createQuery("from Employee e where e.id = 11", Employee.class).uniqueResult();
        //System.out.println(employee2.getName());


        Employee employee = session.get(Employee.class, 11);
        System.out.println(employee.getName());

        session.clear();

        Employee employee1 = session.get(Employee.class, 11);
        System.out.println(employee1.getName());
    }

    @Test
    public void testCollections() {
        Department department = session.get(Department.class, 3);
        System.out.println(department.getName());
        System.out.println(department.getEmployees().size());

        //
        session.clear();

        Department department1 = session.get(Department.class, 3);
        System.out.println(department1.getName());
        System.out.println(department1.getEmployees().size());

    }

    //testTimeStampCache
    @Test
    public void testQueryCache() {
        Query<Employee> query = session.createQuery("from Employee ", Employee.class);
        query.setCacheable(true);

        List<Employee> employees = query.list();
        System.out.println(employees.size());

        Employee employee = session.get(Employee.class, 11);
        employee.setSalary(123123.123f);

        List<Employee> employees1 = query.list();
        System.out.println(employees1.size());
    }

    @Test
    public void testIterator() {
        Department department = session.get(Department.class, 1);
        System.out.println(department.getName());
        System.out.println(department.getEmployees().size());

        Iterator<Employee> iterate = session.createQuery("from Employee e where e.department.id=1", Employee.class)
                .iterate();

        while (iterate.hasNext()){
            System.out.println(iterate.next().getName());
        }

    }

    @Test
    public void testBatchQuery() {
        //批量查询,通过原生jdbc最快
        session.doWork(connection -> {

        });
    }
}
