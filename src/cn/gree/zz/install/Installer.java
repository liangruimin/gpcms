package cn.gree.zz.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.domain.Privilege;
import cn.gree.zz.domain.User;

/**
 * 安装程序（初始化数据）
 * 
 * @author tyg
 * 
 */
@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// =======================================================================
		// 1，超级管理员
//		User user = new User();
//		user.setLoginName("admin");
//		user.setName("超级管理员");
//		user.setPassword(DigestUtils.md5Hex("admin")); // 密码要使用MD5摘要
//		session.save(user); // 保存

		// =======================================================================
		// 2，权限数据 >> 基础数据管理
		Privilege menu, menu1, menu2, menu3,menu4,menu5,menu6,menu10,menu11;

		menu = new Privilege("系统管理", null, null);
		menu1 = new Privilege("岗位管理", "/role_list", menu);
		menu2 = new Privilege("部门管理", "/department_list", menu);
		menu3 = new Privilege("用户管理", "/user_list", menu);
		menu4 = new Privilege("机型管理", "/model_list", menu);
		menu5 = new Privilege("故障类别管理", "/failuretype_list", menu);
		menu6 = new Privilege("责任工序管理", "/process_list", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		session.save(menu6);

		session.save(new Privilege("岗位列表", "/role_list", menu1));

		session.save(new Privilege("部门列表", "/department_list", menu2));

		session.save(new Privilege("用户列表", "/user_list", menu3));
		session.save(new Privilege("用户初始化密码", "/user_initPassword", menu3));
		
		session.save(new Privilege("机型列表", "/user_list", menu4));
		
		session.save(new Privilege("故障类别列表", "/failure_list", menu5));
		
		session.save(new Privilege("责任工序列表", "/process_list", menu6));

		// 2，权限数据 >> 抽测任务管理
		menu10 = new Privilege("抽测任务", null, null);
		menu11 = new Privilege("任务列表", "/task_list", menu10);
		session.save(menu10);
		session.save(menu11);
	}

	public static void main(String[] args) {
		System.out.println("正在初始化数据...");

		// 一定要从Spring容器中取出对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		// 执行安装
		installer.install();

		System.out.println("初始化数据完毕！");
	}

}

