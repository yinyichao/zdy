package com.zdy.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.zdy.dao.UserDao;
import com.zdy.dao.base.BaseJPADao;
import com.zdy.entity.User;
import com.zdy.utils.Page;
@Service("userDao")
public class UserDaoImpl  extends BaseJPADao<User, Long> implements UserDao{
	/**
	 * 
	 */
	public User getUser(User user) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("username", user.getUsername());
		userMap.put("mm", user.getMm());
		return findUniqueBy(userMap);
	}

	public Page<User> findPageUserByIntegrateds(Page<User> page, Map<String, String> conditions) {
		String fromSql = "from z_user t where t.zt = 0";
		if(conditions!=null&&conditions.size()>0){
			if(conditions.get("username")!=null&&!"".equals(conditions.get("username"))){
				fromSql+=" and username like '%"+conditions.get("username")+"%'";
			}
		}
		fromSql += " order by t.id desc";
		String countSql = "select count(1) " + fromSql;
		String sql = "select t.* " + fromSql;
		Query query = getEntityManager().createNativeQuery(countSql);
		BigDecimal count = (BigDecimal) query.getSingleResult();
		page.setTotal(count.longValue());
		
		query = getEntityManager().createNativeQuery(sql, this.getEntityClass());
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getLimit());
		@SuppressWarnings("unchecked")
		List<User> result = query.getResultList();
		page.setRoot(result);
		
		return page;
	}

	@Override
	public int updateUsers(String conditions) {
		String hql = "update z_user set zt = 1 where id in(";
		hql+=conditions;
		hql+=")";
		System.out.println(hql);
		Query query = getEntityManager().createNativeQuery(hql);
		return query.executeUpdate();
	}
	public User checkUserName(String name){
		return this.findUniqueBy("username",name);
	}
	public void saveUser(User user){
		this.save(user);
	}
	public User findUserById(Long id){
		return this.get(id);
	}
	public void updateUser(User user){
		this.update(user);
	}
}