package testHibernate;

import com.yangyang.model.HQLDemo1.ClassRoom;
import com.yangyang.model.HQLDemo1.Special;
import com.yangyang.model.HQLDemo1.Student;
import com.yangyang.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Random;

/**
 * 为HQL的数据做准备
 * @author Administrator
 *
 */
public class TestAdd {
	
	Random ran = new Random();
	
	@Test
	public void testAddSpecial() {
		Session session = null;
		session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			
			session.save(new Special("计算机教育","教育类"));
			session.save(new Special("计算机应用技术","高职类"));
			session.save(new Special("计算机网络技术","高职类"));
			session.save(new Special("计算机信息管理","高职类"));
			session.save(new Special("数学教育","教育类"));
			session.save(new Special("物理教育","教育类"));
			session.save(new Special("化学教育","教育类"));
			session.save(new Special("会计","高职类"));
			session.save(new Special("英语教育","教育类"));
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if(session!=null) session.close();
		}
	}
	
	@Test
	public void testAddClassroom() {
		Session session = null;
		session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			
			session.save(new ClassRoom("计算机教育1班",2009,new Special(1)));
			session.save(new ClassRoom("计算机教育2班",2009,new Special(1)));
			session.save(new ClassRoom("计算机教育班",2010,new Special(1)));
			session.save(new ClassRoom("计算机教育班",2011,new Special(1)));
			session.save(new ClassRoom("计算机应用技术",2009,new Special(2)));
			session.save(new ClassRoom("计算机应用技术",2010,new Special(2)));
			session.save(new ClassRoom("计算机应用技术",2011,new Special(2)));
			session.save(new ClassRoom("计算机网络技术",2009,new Special(3)));
			session.save(new ClassRoom("计算机网络技术",2010,new Special(3)));
			session.save(new ClassRoom("计算机网络技术",2011,new Special(3)));
			session.save(new ClassRoom("计算机信息管理",2009,new Special(4)));
			session.save(new ClassRoom("计算机信息管理",2010,new Special(4)));
			session.save(new ClassRoom("计算机信息管理",2011,new Special(4)));
			session.save(new ClassRoom("数学教育1班",2009,new Special(5)));
			session.save(new ClassRoom("数学教育2班",2009,new Special(5)));
			session.save(new ClassRoom("数学教育3班",2009,new Special(5)));
			session.save(new ClassRoom("数学教育1班",2010,new Special(5)));
			session.save(new ClassRoom("数学教育2班",2010,new Special(5)));
			session.save(new ClassRoom("数学教育1班",2011,new Special(5)));
			session.save(new ClassRoom("数学教育2班",2011,new Special(5)));
			session.save(new ClassRoom("物理教育",2009,new Special(6)));
			session.save(new ClassRoom("物理教育",2010,new Special(6)));
			session.save(new ClassRoom("物理教育",2011,new Special(6)));
			session.save(new ClassRoom("化学教育",2009,new Special(7)));
			session.save(new ClassRoom("化学教育",2010,new Special(7)));
			session.save(new ClassRoom("化学教育",2011,new Special(7)));
			session.save(new ClassRoom("会计",2009,new Special(8)));
			session.save(new ClassRoom("会计",2010,new Special(8)));
			session.save(new ClassRoom("会计",2011,new Special(8)));
			session.save(new ClassRoom("英语教育A班",2009,new Special(9)));
			session.save(new ClassRoom("英语教育B班",2009,new Special(9)));
			session.save(new ClassRoom("英语教育A班",2010,new Special(9)));
			session.save(new ClassRoom("英语教育B班",2010,new Special(9)));
			session.save(new ClassRoom("英语教育A班",2011,new Special(9)));
			session.save(new ClassRoom("英语教育B班",2011,new Special(9)));
			session.save(new ClassRoom("选修课班A",2011,null));
			session.save(new ClassRoom("选修课班B",2011,null));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if(session!=null) session.close();
		}
	}
	
	@Test
	public void testRan() {
		for(int i=0;i<20;i++) {
			System.out.println(ran.nextInt(2));
		}
	}
	
	@Test
	public void testAddStu() {
		Session session = null;
		session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			String[] sexs = new String[]{"男","女"};
			//仅仅添加32个班 的学生，方便做外连接的实验
			for(int i=1;i<=32;i++) {
				//每个班40个学生
				for(int j=1;j<=40;j++) {
					session.save(new Student(getName(),sexs[ran.nextInt(2)],new ClassRoom(i)));
				}
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if(session!=null) session.close();
		}
	}
	
	@Test
	public void testAddStuNull() {
		//添加100个没有班级的学生方便做right连接查询
		Session session = null;
		session = HibernateUtil.openSession();
		try {
			session.beginTransaction();
			String[] sexs = new String[]{"男","女"};
			for(int j=1;j<=100;j++) {
				session.save(new Student(getName(),sexs[ran.nextInt(2)],null));
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if(session!=null) session.close();
		}
	}
	
	private String getName() {
		String[] name1 = new String[]{"孔","张","叶","李","叶入","孔令",
				"张立","陈","刘","牛","夏侯","令","令狐","赵","母","穆","倪",
				"张毅","称","程","王","王志","刘金","冬","吴","马","沈"};
		
		String[] name2 = new String[]{"凡","课","颖","页","源","都",
				"浩","皓","西","东","北","南","冲","昊","力","量","妮",
				"敏","捷","杰","坚","名","生","华","鸣","蓝","春","虎","刚","诚"};
		
		String[] name3 = new String[]{"吞","明","敦","刀","备","伟",
				"唯","楚","勇","诠","佺","河","正","震","点","贝","侠",
				"伟","大","凡","琴","青","林","星","集","财"};
		
		boolean two = ran.nextInt(50)>=45?false:true;
		if(two) {
			String n1 = name1[ran.nextInt(name1.length)];
			String n2;
			int n = ran.nextInt(10);
			if(n>5) {
				n2 = name2[ran.nextInt(name2.length)];
			} else {
				n2 = name3[ran.nextInt(name3.length)];
			}
			return n1+n2;
		} else {
			String n1 = name1[ran.nextInt(name1.length)];
			String n2 = name2[ran.nextInt(name2.length)];
			String n3 = name3[ran.nextInt(name3.length)];
			return n1+n2+n3;
		}
	}
}
