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
 * 增加功能
 * 
 * @author tyg
 * 
 */
@Component
public class AddNav {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();


		// =======================================================================
		// 2，权限数据
		Privilege menu, menu1;

		menu = new Privilege("指标填报", null, null);
		menu1 = new Privilege("填报(月度)指标", "/kpi_list", menu);

		session.save(menu);
		session.save(menu1);

	}

	public static void main(String[] args) {
		System.out.println("正在添加数据...");

		// 一定要从Spring容器中取出对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AddNav installer = (AddNav) ac.getBean("addNav");
		// 执行安装
		installer.install();

		System.out.println("添加数据完毕！");
	}

}

