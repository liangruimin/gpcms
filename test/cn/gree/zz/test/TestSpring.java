package cn.gree.zz.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testSessionFactory(){
		SessionFactory sf = (SessionFactory)ac.getBean("sessionFactory");
		System.out.println(sf.openSession());
		
	}
	//测试事务管理
	@Test
	public void testTx(){
		TestService service = (TestService)ac.getBean("testService");
		service.saveTwoUsers();
	}
	//测试Action对象的管理
	@Test
	public void testAction(){
		TestAction action = (TestAction)ac.getBean("testAction");
		System.out.println(action);
	}
}
