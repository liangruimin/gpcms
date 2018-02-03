package cn.gree.zz.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

//这个@Transactional注解对子类中的方法也有效！
@Transactional
@SuppressWarnings("unchecked")
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz = null;   //相当于T.class ? 怎么得到继承它的子类的具体的T
		
	public DaoSupportImpl() {
		// 通过反射获取T的真是类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>)pt.getActualTypeArguments()[0];
		System.out.println("----> clazz= "+clazz);
	}

	/**
	 * 获取当前可用的session
	 * @return
	 */
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);
	}

	@Override
	public void delete(Long id) {
		if(id == null){
			return;
		}
		Object entity = getById(id);
		getSession().delete(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T getById(Long id) {
		if(id == null){
			return null;
		} else {
			return (T) getSession().get(clazz, id);
		}		
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}
		return getSession().createQuery(//
				"FROM "+clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)// 注意一定要使用setParameterList()方法！
				.list();
	}

	@Override
	public List<T> findAll() {
		return getSession().createQuery("From " +clazz.getSimpleName()).list();
	}

}
