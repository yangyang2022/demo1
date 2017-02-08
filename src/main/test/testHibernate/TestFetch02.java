package testHibernate;

import com.yangyang.model.xml.Customer;
import com.yangyang.model.xml.Order;
import com.yangyang.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestFetch02 {
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
    public void testFetchClassLevel() {
        Customer yangyang1 = new Customer("yangyang1");
        Customer yangyang2 = new Customer("yangyang2");
        Customer yangyang3 = new Customer("yangyang3");

        Order order1 = new Order("apple", yangyang1);
        Order order2 = new Order("banana", yangyang2);
        Order order3 = new Order("pear", yangyang3);
        Order order4 = new Order("fruit", yangyang1);
        Order order5 = new Order("bee", yangyang2);

        save(yangyang1,yangyang2,yangyang3,order1,order2,order3,order4,order5);

    }

    @Test
    public void testLoad() {
        Customer customer = session.load(Customer.class, 1);
        System.out.println(customer.getClass().getName());
    }

    @Test
    public void testOne2Many() {
        Customer customer = session.get(Customer.class, 2);

        //1:　对于1-n / n-n 集合,默认使用懒加载策略
        //2:　设置set的属性 lazy = false ,不推荐这样设置 --> 2 sql
        //3:　lazy可以设置为extra ,增强的lazy
        System.out.println(customer.getName());
        System.out.println(customer.getOrders().size());
        //
        Order order = new Order(2);
        System.out.println(customer.getOrders().contains(order));

        //显示初始化
        //Hibernate.initialize(customer.getOrders());

        //customer.getOrders().iterator().forEachRemaining(e-> System.out.println(e.getOrder_name()));
    }

    @Test
    public void testBatchSize() {
        List<Customer> customers = session.createQuery("from Customer ", Customer.class).list();
        System.out.println(customers.size());

        customers.forEach(e-> System.out.println(e.getName()+" : "+e.getOrders().size()));

    }

    @Test
    public void testFetch1() {
        testBatchSize();
    }

    //如取值为join,则懒加载失效...迫切左外连接的方式检索n的一段的几何属性
    //(迫切左外连接 使用左外连接查询并且初始化集合 )
    //如果使用query.list则任然使用lazy加载
    @Test
    public void testJoin() {
        Customer customer = session.get(Customer.class, 1);
        System.out.println(customer.getOrders().size());

    }

    @Test
    public void testMany2One() {

        // 1: fetch 为proxy and false 分别对应lazy and not lazy
        // 2: fetch 为join 使用迫切左外连接
        //Order order = session.get(Order.class, 1);
        //System.out.println(order.getCustomer().getName());

        List<Order> orders = session.createQuery("from Order ", Order.class)
                .list();
        for(Order order : orders){
            if(order.getCustomer() != null){
                System.out.println(order.getCustomer().getName());
            }
        }
    }
}
