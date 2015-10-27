package com.zdy.dao.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdy.utils.Page;
import com.zdy.utils.ReflectionUtils;



/**
 * 封装Java Persistence API的DAO泛型基类.
 * 
 * 可在Service层直接使用,也可以扩展泛型DAO子类使用.
 * 
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 */
@SuppressWarnings("unchecked")
public class BaseJPADao<T, PK extends Serializable> {

	// 所有子类中都使用此logger进行日志打印, 系统对日志进行统一配置
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	// JPA实体管理器
	private EntityManager entityManager;

	// Dao操作的实体类型
	private Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数.<br />
	 * 通过子类的泛型定义取得对象类型Class.<br />
	 * eg.<br />
	 * public class UserDao extends SimpleJPADao<User, Integer>
	 */
	public BaseJPADao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleDao的构造函数.<br />
	 * 在构造函数中定义对象类型Class.<br />
	 * eg.<br />
	 * SimpleJPADao<User, Long> userDao = new SimpleDao<User,
	 * Integer>(User.class);
	 */
	public BaseJPADao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SimpleDao的构造函数.<br />
	 * 在构造函数中定义对象类型Class.<br />
	 * eg.<br />
	 * SimpleDao<User, Long> userDao = new SimpleJPADao<User,
	 * Integer>(entityManager, User.class);
	 */
	public BaseJPADao(final EntityManager entityManager, final Class<T> entityClass) {
		this.entityManager = entityManager;
		this.entityClass = entityClass;
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 采用@PersistenceContext注入EntityManager,当有多个EntityManager的时候Override本函数.
	 */
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * 保存新增的对象.
	 */
	public void save(final T entity) {
		assert entity != null;
		getEntityManager().persist(entity);
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 保存修改过的对象.
	 */
	public void update(final T entity) {
		assert entity != null;
		getEntityManager().merge(entity);
		logger.debug("update entity: {}", entity);
	}

	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		assert id != null;
		return (T) getEntityManager().find(entityClass, id);
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity 对象必须是持久态对象或含id属性的瞬态对象.
	 */
	public void remove(final T entity) {
		assert entity != null;
		getEntityManager().remove(entity);
		logger.debug("remove entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void remove(final PK id) {
		assert id != null;
		remove(get(id));
		logger.debug("remove entity {}, id is {}", getEntityClass().getSimpleName(), id);
	}

	/**
	 * 获取全部对象.
	 */
	public List<T> listAll() {
		String jpql = "from " + getEntityClass().getName();
		return find(jpql);
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		assert StringUtils.isNotBlank(propertyName);
		String jpql = "from " + getEntityClass().getName() + " t where t." + propertyName + "=?";
		return find(jpql, value);
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
	 * 按集合属性里的某个属性查找对象列表,匹配方式为相等.
	 * 
	 * @param propertyName 集合属性名
	 * @param nestedPropertyName 集合属性对象的指定属性
	 * @param value 匹配值
	 */
	public List<T> findBy(final String propertyName, final String nestedPropertyName, final Object value) {
		assert StringUtils.isNotBlank(propertyName);
		assert StringUtils.isNotBlank(nestedPropertyName);
		String jpql = "select distinct t from " + getEntityClass().getName() + " t left join t." + propertyName
				+ " p where p." + nestedPropertyName + "=?";
		return find(jpql, value);
	}

	/**
	 * 按集合属性里的某个属性查找唯一对象,匹配方式为相等.
	 * 
	 * @param propertyName 集合属性名
	 * @param nestedPropertyName 集合属性对象的指定属性
	 * @param value 匹配值
	 */
	public T findUniqueBy(final String propertyName, final String nestedPropertyName, final Object value) {
		assert StringUtils.isNotBlank(propertyName);
		assert StringUtils.isNotBlank(nestedPropertyName);
		String jpql = "select distinct t from " + getEntityClass().getName() + " t left join t." + propertyName
				+ " p where p." + nestedPropertyName + "=?";
		return findUnique(jpql, value);
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * 
	 * @param properties 属性名-值列表
	 */
	public List<T> findBy(final Map<String, Object> properties) {
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
		return find(jpql, values);
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
	 * 按JPQL查询对象列表.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public List<T> find(final String jpql, final Object... values) {
		return createQuery(jpql, values).getResultList();
	}

	/**
	 * 按JPQL查询对象列表.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public List<T> find(final String jpql, final Map<String, Object> values) {
		return createQuery(jpql, values).getResultList();
	}

	/**
	 * 按JPQL查询唯一对象.
	 * 
	 * @param values 数量可变的参数,按顺序绑定.
	 */
	public T findUnique(final String jpql, final Object... values) {
		try {
			return (T) createQuery(jpql, values).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * 按JPQL查询唯一对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public T findUnique(final String jpql, final Map<String, Object> values) {
		try {
			return (T) createQuery(jpql, values).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * 按JPQL查询Integer类型结果.
	 */
	public Integer findInt(final String jpql, final Object... values) {
		return (Integer) createQuery(jpql, values).getSingleResult();
	}

	/**
	 * 按JPQL查询Integer类型结果.
	 */
	public Integer findInt(final String jpql, final Map<String, Object> values) {
		return (Integer) createQuery(jpql, values).getSingleResult();
	}

	/**
	 * 按JPQL查询Long类型结果.
	 */
	public Long findLong(final String jpql, final Object... values) {
		return (Long) createQuery(jpql, values).getSingleResult();
	}

	/**
	 * 按JPQL查询Long类型结果.
	 */
	public Long findLong(final String jpql, final Map<String, Object> values) {
		return (Long) createQuery(jpql, values).getSingleResult();
	}

	/**
	 * 执行JPQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String jpql, final Object... values) {
		return createQuery(jpql, values).executeUpdate();
	}

	/**
	 * 执行JPQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String jpql, final Map<String, Object> values) {
		return createQuery(jpql, values).executeUpdate();
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
		Query query = getEntityManager().createQuery(jpql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				// jpa参数从1开始
				query.setParameter(i + 1, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询JPQL与参数列表创建Query对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	public Query createQuery(final String jpql, final Map<String, Object> values) {
		assert StringUtils.isNotBlank(jpql);
		Query query = getEntityManager().createQuery(jpql);
		if (values != null) {
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		try {
			T t = findUniqueBy(propertyName, newValue);
			return t == null;
		} catch (NoResultException e) {
			return true;
		}
	}


	/**
	 * 通过Set将不唯一的对象列表唯一化.
	 * 
	 * 主要用于JPQL预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	public <X> List<X> distinct(List<X> list) {
		Set<X> set = new LinkedHashSet<X>(list);
		return new ArrayList<X>(set);
	}
	
	/**
	 * 按JPQL分页查询.
	 * @param page 分页参数.
	 * @param jpql JPQL语句.
	 * @param alias JPQL中实体的别名,可以为null
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> find(final Page<T> page, final String jpql, final String alias, final Object... values) {
		assert page != null;

		// 总记录数
		long totalCount = countSimpleJPQLResult(jpql, alias, values);
		page.setTotal(totalCount);

		// 当前页数据
		Query query = createQuery(attachPageOrderBy(jpql, alias, page), values);
		setPageParameter(query, page);
		List<T> result = query.getResultList();
		page.setRoot(result);

		return page;
	}

	/**
	 * 按JPQL分页查询.
	 * @param page 分页参数.
	 * @param jpql JPQL语句.
	 * @param alias JPQL中实体的别名,可以为null
	 * @param values 命名参数,按名称绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> find(final Page<T> page, final String jpql, final String alias, final Map<String, Object> values) {
		assert page != null;

		// 总记录数
		long totalCount = countSimpleJPQLResult(jpql, alias, values);
		page.setTotal(totalCount);

		// 当前页数据
		Query query = createQuery(attachPageOrderBy(jpql, alias, page), values);
		setPageParameter(query, page);
		List<T> result = query.getResultList();
		page.setRoot(result);

		return page;
	}

	/**
	 * 按复杂JPQL分页查询.
	 * @param page 分页参数.
	 * @param countJpql 用于查询总记录数的JPQL语句.
	 * @param jpql JPQL语句.
	 * @param values 数量可变的查询参数,按顺序绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findComplexJpql(final Page<T> page, final String countJpql, final String jpql, final String alias,
			final Object... values) {
		assert page != null;

		// 总记录数
		long totalCount = findLong(countJpql, alias, values);
		page.setTotal(totalCount);

		// 当前页数据
		Query query = createQuery(attachPageOrderBy(jpql, alias, page), values);
		setPageParameter(query, page);
		List<T> result = query.getResultList();
		page.setRoot(result);

		return page;
	}

	/**
	 * 按复杂JPQL分页查询.
	 * @param page 分页参数.
	 * @param countJpql 用于查询总记录数的JPQL语句.
	 * @param jpql JPQL语句.
	 * @param values 命名参数,按名称绑定.
	 * @return 分页查询结果, 附带结果列表及所有查询时的参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findComplexJpql(final Page<T> page, final String countJpql, final String jpql, final String alias,
			final Map<String, Object> values) {
		assert page != null;

		// 总记录数
		long totalCount = findLong(countJpql, alias, values);
		page.setTotal(totalCount);

		// 当前页数据
		Query query = createQuery(attachPageOrderBy(jpql, alias, page), values);
		setPageParameter(query, page);
		List<T> result = query.getResultList();
		page.setRoot(result);

		return page;
	}

	/**
	 * 获取只有from子句的jpql
	 */
	protected String getFromJpql(final String jpql) {
		// TODO: 有没有办法切除fetch子句？
		String fromJpql = "from " + StringUtils.substringAfter(jpql, "from");
		fromJpql = getJpqlWithoutOrderBy(fromJpql);
		return fromJpql;
	}

	/**
	 * 获取切除order by子句的jpql
	 */
	protected String getJpqlWithoutOrderBy(final String jpql) {
		// FIXME: 不能处理"order  by"这种情况（多个空格）
		return StringUtils.substringBefore(jpql, "order by");
	}

	/**
	 * 根据Page中的字段执行排序，转化为jpql排序查询子句
	 */
	protected String attachPageOrderBy(final String jpql, final Page<T> page) {
		return attachPageOrderBy(jpql, null, page);
	}

	/**
	 * 根据Page中的字段执行排序，转化为jpql排序查询子句
	 */
	protected String attachPageOrderBy(final String jpql, final String alias, final Page<T> page) {
		String fullAlias = StringUtils.isNotBlank(alias) ? alias + "." : "";
		String newJpql = getJpqlWithoutOrderBy(jpql);
		if (StringUtils.isNotBlank(page.getSort())) {
			newJpql += " order by " + fullAlias + page.getSort() + " " + page.getDir();
		} else {
			newJpql += " order by " + fullAlias + "id desc";
		}
		return newJpql;
	}

	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query query, final Page<T> page) {
		// 如果limit等于Page.LIMIT_NO_PAGINATION，则不分页
		if (Page.LIMIT_NO_PAGINATION != page.getLimit()) {
			// jpa的firstResult的序号从0开始
			query.setFirstResult(page.getStart());
			query.setMaxResults(page.getLimit());
		}
		return query;
	}

	/**
	 * 执行count查询获得本次JPQL查询所能获得的对象总数.
	 * 本函数只能自动处理简单的jpql语句,复杂的jpql查询(带fetch语句)请另行编写count语句查询.
	 */
	protected long countSimpleJPQLResult(final String jpql, final Object... values) {
		return countSimpleJPQLResult(jpql, null, values);
	}

	/**
	 * 执行count查询获得本次JPQL查询所能获得的对象总数.
	 * 本函数只能自动处理简单的jpql语句,复杂的jpql查询(带fetch语句)请另行编写count语句查询.
	 */
	protected long countSimpleJPQLResult(final String jpql, final String alias, final Object... values) {
		long count = 0;
		// select子句与order by子句会影响count查询,进行简单的排除.
		String fromJpql = getFromJpql(jpql);

		String countJpql = "select count(" + (StringUtils.isBlank(alias) ? "*" : alias) + ") " + fromJpql;

		try {
			count = findLong(countJpql, values);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("jpql can't be auto count, jpql is: " + countJpql, e);
		}

		return count;
	}

	/**
	 * 执行count查询获得本次JPQL查询所能获得的对象总数.
	 * 本函数只能自动处理简单的jpql语句,复杂的jpql查询(带fetch语句)请另行编写count语句查询.
	 */
	protected long countSimpleJPQLResult(final String jpql, final Map<String, Object> values) {
		return countSimpleJPQLResult(jpql, null, values);
	}

	/**
	 * 执行count查询获得本次JPQL查询所能获得的对象总数.
	 * 本函数只能自动处理简单的jpql语句,复杂的jpql查询(带fetch语句)请另行编写count语句查询.
	 */
	protected long countSimpleJPQLResult(final String jpql, final String alias, final Map<String, Object> values) {
		long count = 0;
		// select子句与order by子句会影响count查询,进行简单的排除.
		String fromJpql = getFromJpql(jpql);

		String countJpql = "select count(" + (StringUtils.isBlank(alias) ? "*" : alias) + ") " + fromJpql;

		try {
			count = findLong(countJpql, values);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("jpql can't be auto count, jpql is: " + countJpql, e);
		}

		return count;
	}

}
