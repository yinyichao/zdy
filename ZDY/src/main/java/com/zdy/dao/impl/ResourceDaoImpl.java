package com.zdy.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.zdy.dao.ResourceDao;
import com.zdy.dao.base.BaseJPADao;
import com.zdy.entity.Resources;
@Service("resourceDao")
public class ResourceDaoImpl  extends BaseJPADao<Resources, Long> implements ResourceDao{
	/**
	 * ����û�id��ѯ�û�
	 */
	public List<Object[]> getResourceAll(Long userId) {
		String sql = "select r.id,r.name,r.url,r.pid from z_resource r inner join z_role_resource rr on r.id = rr.resourceid inner join z_user_role u on u.roleid = rr.roleid and u.userid = ?";
		Query query = getEntityManager().createNativeQuery(sql);
		query.setParameter(1, userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}
}