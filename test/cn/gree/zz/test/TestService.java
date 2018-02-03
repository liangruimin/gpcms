package cn.gree.zz.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gree.zz.domain.User;
//表示注入了容器
@Service
public class TestService {
	@Resource
	private SessionFactory sessionFactory;
	@Transactional
	public void saveTwoUsers(){
		Session session = sessionFactory.getCurrentSession();
		
		session.save(new User());
		//int a = 1/0;
		session.save(new User());
	}
}
