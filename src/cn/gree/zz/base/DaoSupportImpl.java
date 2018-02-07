package cn.gree.zz.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
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
		//System.out.println("----> clazz= "+clazz);
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
	
	//=========================多条件查询===========================
	/**
	 *单表多条记录查询
	 *@param calssName 要查询的对象
	 *@param varables 封装查询条件的Map
	 *@return 返回查询结果的List集合 
	 */
	public <T> List<T> queryResultList(Class<T> className,Map<String,Object> varables){
		List<T> valueList = selectStatement(className,varables,getSession()).list();
		return valueList;
	}

	/**
	 * 拼接SQL查询字符串,得到Query并赋值查询条件
	 *@param calssName 要查询的对象
	 *@param varables 封装查询条件的Map
	 *@param session
	 *@return Query
	 */
	private <T> Query selectStatement(Class<T> className,Map<String,Object> varables,Session session){
		StringBuilder stringBuilder = new StringBuilder();
		/*
		 * 通过className得到该实体类的字符串形式
		 */
		stringBuilder.append("FROM " +sessionFactory.getClassMetadata(className).getEntityName()) ;
		stringBuilder.append(" WHERE 1=1 ");
		/**
		 * 动态的拼接hql语句,如果一个属性的值为"",则不往条件中添加.
		 */
		for(Entry<String, Object> entry:varables.entrySet()){
			if(!entry.getValue().equals("")){
				stringBuilder.append(" AND " + entry.getKey() + "=:" + entry.getKey());
			}
		}
		Query query = getSession().createQuery(stringBuilder.toString());
		/**
		 * 动态的给条件赋值
		 */
		for(Entry<String, Object> entry:varables.entrySet()){
			if(!entry.getValue().equals("")){
				query.setParameter(entry.getKey(), entry.getValue());
				query.setMaxResults(20);   //最多显示20条
			}
		}
		return query;
	}

}
