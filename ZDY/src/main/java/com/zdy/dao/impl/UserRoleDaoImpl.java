package com.zdy.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.zdy.dao.UserRoleDao;
import com.zdy.dao.base.BaseJPADao;
import com.zdy.entity.UserRole;
@Service("userRoleDao")
public class UserRoleDaoImpl  extends BaseJPADao<UserRole, Long> implements UserRoleDao{
	public void saveUserRole(UserRole userRole){
		this.save(userRole);
	}
	public void delUserRole(String userId){
		String hql = "delete z_user_role  where userid in(";
		hql+=userId;
		hql+=")";
		Query query = getEntityManager().createNativeQuery(hql);
		query.executeUpdate();
	}

}