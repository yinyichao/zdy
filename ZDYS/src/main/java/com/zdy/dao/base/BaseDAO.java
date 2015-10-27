package com.zdy.dao.base;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zdy.utils.ReflectionUtils;


@SuppressWarnings("all")
@Repository
public class BaseDAO<T, PK extends Serializable>{
	// Dao操作的实体类型
	private Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数.<br />
	 * 通过子类的泛型定义取得对象类型Class.<br />
	 * eg.<br />
	 * public class UserDao extends SimpleJPADao<User, Integer>
	 */
	public BaseDAO() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleDao的构造函数.<br />
	 * 在构造函数中定义对象类型Class.<br />
	 * eg.<br />
	 * SimpleJPADao<User, Long> userDao = new SimpleDao<User,
	 * Integer>(User.class);
	 */
	public BaseDAO(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleDao的构造函数.<br />
	 * 在构造函数中定义对象类型Class.<br />
	 * eg.<br />
	 * SimpleDao<User, Long> userDao = new SimpleJPADao<User,
	 * Integer>(entityManager, User.class);
	 */
	public BaseDAO(final EntityManager entityManager, final Class<T> entityClass) {
//		this.entityManager = entityManager;
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final Map<String, Object> properties) {
		String jpql = "from " + getEntityClass().getName() + " t";
		int i = 0;
		Object[] values = new Object[properties.size()];
		for (Map.Entry<String, Object> property : properties.entrySet()) {
			if (i == 0) {
				jpql += " where";
			} else {
				jpql += " and";
			}
			jpql += " t." + property.getKey() + "=?";
			values[i++] = property.getValue();
		}
		return findUnique(jpql, values);
	}
	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		assert StringUtils.isNotBlank(propertyName);
		String jpql = "from " + getEntityClass().getName() + " t where t." + propertyName + "=?";
		return findUnique(jpql, value);
	}
	/**
	 * 按JPQL查询唯一对象.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public T findUnique(final String jpql, final Object... values) {
		try {
			return (T) createQuery(jpql, values).uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	/**
	 * 根据查询JPQL与参数列表创建Query对象.
	 * 
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String jpql, final Object... values) {
		assert StringUtils.isNotBlank(jpql);
		Query query = (Query) getCurrentSession().createQuery(jpql);
		//Query query = getHibernateTemplate().createQuery(jpql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				// jpa参数从1开始
				query.setString(i, (String) values[i]);
			}
		}
		return query;
	}

	/**
	 * 获取全部对象.
	 */
	public List<T> listAll() {
		String jpql = "from " + getEntityClass().getName();
		return find(jpql);
	}

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		assert id != null;
		return (T) getCurrentSession().get(entityClass,id);
	}
	public Integer executeSql(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).executeUpdate();
	}
}
