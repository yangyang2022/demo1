package testHibernate;

import com.yangyang.model.xml.*;
import com.yangyang.util.HibernateUtil;
import org.apache.commons.fileupload.util.Streams;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class TestHibernate {

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
    public void testSave1() {

        News news = new News("人民日报","主编", LocalDate.now());
        session.save(news);

    }

    @Test
    public void testLoad1() {
        News news = session.load(News.class, 1);
        System.out.println(news);

        //session clear ...
        //session.clear();

        News news1 = session.load(News.class,1);
        System.out.println(news1);

    }

    @Test
    public void testSessionFlush() {
        News news = session.load(News.class, 1);
        news.setAuthor("Oracle");
        //session.flush();
        //News res = (News) session.createCriteria(News.class).uniqueResult();
        //System.out.println("get News: "+news);
    }

    @Test
    public void testFlush2() {
        News news = new News("testTitle2", "yangyang2", LocalDate.now());
        session.save(news);
    }

    @Test
    public void testReflush() {
        News news = session.load(News.class,1);
        System.out.println(news);

        System.out.println("-----------------------------");

        session.refresh(news);
        System.out.println(news);

    }

    @Test
    public void testSave() {
        News news = new News(123123, "titleID", "hehe", LocalDate.now());
        session.save(news);
    }

    @Test
    public void testPersist() {
        News news = new News(998,"title2", "author2", LocalDate.now());
        //throw a exception
        session.persist(news);
    }

    @Test
    public void testGet() {
        News news = session.get(News.class, 2);
        System.out.println(news);
        News load = session.load(News.class, 3);
        System.out.println(load.getId());
        System.out.println("----------------------------");
        System.out.println(load);

    }

    @Test
    public void testUpdate() {
        News news = session.get(News.class, 1);
        transaction.commit();
        session.close();
        //
        session = HibernateUtil.openSession();
        transaction = session.beginTransaction();
        //news.setAuthor("Sun"); //now news is detach object,must be use session.update

        //whatever news changes or not ,will execute update sql
        session.update(news);
    }

    @Test
    public void testSaveOrUpdate() {
        News news = new News("title2", "author2", LocalDate.now());
        news.setId(79);

        session.saveOrUpdate(news);

    }

    @Test
    public void testDelete() {
        News news = session.get(News.class, 3);

        //plan to delete ,when flush then execute delete
        session.delete(news);

        System.out.println(news);

    }

    @Test
    public void testEvict() {
        //evict : remove persistent object from session
        News news1 = session.get(News.class, 1);
        News news2 = session.get(News.class, 2);

        news1.setTitle("aaa");
        news2.setTitle("bbb");

        session.evict(news1);

    }

    @Test
    public void testProcedure() {
        //1: get Connection 2: execute producre
        session.doWork(connection -> {
            //invoke producre
            System.out.println(connection);
        });
    }

    //=================== test hbm file ======================

    @Test
    public void testDynamicDemo() {
        //Paper paper = new Paper("title2","content2","this is desc2","yangyang", LocalDateTime.now());
        //session.save(paper);

        Paper paper = session.get(Paper.class, 1);

        System.out.println(paper.getDesc());

        //session.update(paper);

    }

    @Test
    public void testBlob() throws IOException, SQLException {
        //String filePath = "C:\\Users\\syy\\Desktop\\pic\\dog.jpg";
        //InputStream in = new FileInputStream(filePath);
        //Blob blob = Hibernate.getLobCreator(session).createBlob(in,in.available());

        //Paper paper = new Paper("title2", "content2", "this is desc2", "author2", LocalDateTime.now(), blob);
        //session.save(paper);

        String outPath = "C:\\code";

        Paper paper = session.get(Paper.class,4);
        System.out.println(paper.getId()+" : "+paper.getAuthor()+" : "+paper.getContent());
        System.out.println(paper.getDesc());

        Streams.copy(paper.getPic().getBinaryStream(),new FileOutputStream(outPath+"\\dog.jpg"),true);
        //Files.copy(paper.getPic().getBinaryStream(), Paths.get(outPath));
    }


    @Test
    public void testComponentWorker() {
        Pay pay = new Pay(10,20,30);
        Worker worker = new Worker("yangyang",pay);

        session.save(worker);

    }

    // ===================== test Many-to-One ===================

    @Test
    public void testManyToOne() {
        Customer yangyang1 = new Customer("yangyang2");
        Order order = new Order("order2",yangyang1);

        // --> three insert
        //save(yangyang1);
        //save(order);

        //--> three insert and a update
        save(order);
        save(yangyang1);
    }

    @Test
    public void testMany2One() {

        //xml is default lazy load while annotation is left outer join ...
        Order order = session.get(Order.class, 1);
        System.out.println(order.getOrder_name());
        System.out.println(order.getCustomer().getName());

    }

    // ================== test Many-to-One both =========

    @Test
    public void testMang2OneBoth() {
        Customer cus1 = new Customer("cus1");
        Customer cus2 = new Customer("cus2");

        Order order1 = new Order("order1", cus1);
        Order order2 = new Order("order2", cus1);

        cus1.setOrders(new HashSet<>(Arrays.asList(order1,order2)));

        save(cus1,cus2,order1,order2);
    }

    @Test
    public void testGet1() {
        Customer customer = session.load(Customer.class, 1);
        //System.out.println(customer.getOrders().getClass().getName());
        //session.delete(customer);
        System.out.println(customer.getOrders().size());

    }

    @Test
    public void testOne2One() {
        Depart depart = new Depart();
        Manager manager = new Manager();
        depart.setDepName("House");
        depart.setManager(manager);

        manager.setManagerName("yangyang");
        manager.setDepart(depart);

        save(depart,manager);
    }

    @Test
    public void testLoadOne2One() {

        //Manager manager = session.get(Manager.class, 1);
        Depart depart = session.get(Depart.class, 1);
        System.out.println(depart.getDepName());

        //session.close(); //LazyInitializationException

        System.out.println(depart.getManager().getManagerName());

    }

    @Test
    public void testLoadOne2One2() {
        //在查询没有外键关联的实体对象时,在使用左外连接时,一并查询出所有关联对象
        Manager manager = session.get(Manager.class, 1);
        System.out.println(manager.getManagerName());
        System.out.println(manager.getDepart().getDepName());

    }
    // ================= many-to-many ================
    @Test
    public void testMany2Many() {
        Category food = new Category("food");
        Category fruit = new Category("fruit");
        Item apple = new Item("apple");
        Item banana = new Item("banana");
        Item pear = new Item("pear");
        Item price = new Item("price");

        food.getItems().addAll(Arrays.asList(price,apple));
        fruit.getItems().addAll(Arrays.asList(apple,banana,pear));

        apple.getCategories().addAll(Arrays.asList(food,fruit));
        banana.getCategories().add(fruit);

        save(apple,banana,pear,price,food,fruit);
    }

    @Test
    public void testGetCategory() {
        Category category = session.get(Category.class, 1);
        System.out.println(category.getCategoryName());

        System.out.println(category.getItems().size());

    }
}
