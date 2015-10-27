package com.zdy.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zdy.dao.ResourceDao;
import com.zdy.dao.base.BaseDAO;
import com.zdy.entity.Resources;
@Service("resourceDao")
public class ResourceDaoImpl  extends BaseDAO<Resources,Long> implements ResourceDao{
	/**
	 * ����û�id��ѯ�û�
	 */
	public List<Resources> getResourceAll(Long userId) {
		String sql = "select r.id,r.name,r.url,r.pid from z_resource r inner join z_role_resource rr on r.id = rr.resourceid inner join z_user_role u on u.roleid = rr.roleid and u.userid = ?";
//		Query query = getEntityManager().createNativeQuery(sql);
		Query query = (Query) getCurrentSession().createSQLQuery(sql).addEntity(Resources.class);
		query.setParameter(0, userId);
		@SuppressWarnings("unchecked")
		List<Resources> result = query.list();
		return result;
	}

	@Override
	public Resources findResourcesListByName(String name) {
		// TODO Auto-generated method stub
		return findUniqueBy("name",name);
	}

	@Override
	public List<Resources> getResourceAll() {
		// TODO Auto-generated method stub
		return this.listAll();
	}
}