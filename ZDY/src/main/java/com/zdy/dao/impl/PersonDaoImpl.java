package com.zdy.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.zdy.dao.PersonDao;
import com.zdy.dao.base.BaseJPADao;
import com.zdy.entity.Person;
import com.zdy.utils.Page;
@Service("personDao")
public class PersonDaoImpl extends BaseJPADao<Person, Long> implements PersonDao{

	@Override
	public Page<Person> findPagePersonByIntegrateds(Page<Person> page, Map<String, String> conditions){
		String fromSql = "from z_person t where t.zt = 0";
		if (conditions != null && conditions.size() > 0) {
			if (conditions.get("sfzh") != null&& !"".equals(conditions.get("sfzh"))) {
				fromSql += " and sfzh like '%" + conditions.get("sfzh") + "%'";
			}
			if (conditions.get("xm") != null&& !"".equals(conditions.get("xm"))) {
				fromSql += " and xm like '%" + conditions.get("xm") + "%'";
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
		List<Person> result = query.getResultList();
		page.setRoot(result);
		return page;
	}
	@Override
	public int updatePersons(String conditions) {
		String hql = "update z_person set zt = 1 where id in(";
		hql+=conditions;
		hql+=")";
		System.out.println(hql);
		Query query = getEntityManager().createNativeQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public void savePerson(Person person) {
		this.save(person);
	}

	@Override
	public Person findPersonById(Long id) {
		return this.get(id);
	}

	@Override
	public void updatePerson(Person person) {
		this.update(person);
	}
}
