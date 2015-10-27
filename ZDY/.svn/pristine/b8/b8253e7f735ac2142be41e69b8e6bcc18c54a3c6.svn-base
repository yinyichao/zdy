package com.zdy.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.zdy.dao.MenuDao;
import com.zdy.dao.base.BaseJPADao;
import com.zdy.entity.Menu;
@Service("menuDao")
public class MenuDaoImpl  extends BaseJPADao<Menu, Long> implements MenuDao{
	/**
	 * ����û�id��ѯ�û�
	 */
	public List<Object[]> getMenuAll(Long userId) {
		String sql = "select r.id,r.name from z_menu r inner join z_role_resource rr on r.id = rr.resourceid inner join z_user_role u on u.roleid = rr.roleid and u.userid = ?";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter(1, userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}
}