package com.zdy.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.zdy.dao.CarDao;
import com.zdy.dao.base.BaseDAO;
import com.zdy.entity.Car;
import com.zdy.utils.Page;
@Service("carDao")
public class CarDaoImpl extends BaseDAO<Car,Long> implements CarDao{

	@Override
	public Page<Car> findPageCarByIntegrateds(Page<Car> page, Map<String, String> conditions){
		String fromSql = "from z_car t where t.zt = 0";
		if (conditions != null && conditions.size() > 0) {
			if (conditions.get("cph") != null&& !"".equals(conditions.get("cph"))) {
				fromSql += " and cph like '%" + conditions.get("cph") + "%'";
			}
		}
		fromSql += " order by t.id desc";
		String countSql = "select count(1) " + fromSql;
		String sql = "select t.* " + fromSql;
//		Query query = getEntityManager().createNativeQuery(countSql);
		Query query = (Query) getCurrentSession().createSQLQuery(countSql);
		BigDecimal count = (BigDecimal) query.uniqueResult();
		page.setTotal(count.longValue());
//		query = getEntityManager().createNativeQuery(sql, this.getEntityClass());
		query = (Query) getCurrentSession().createSQLQuery(sql).addEntity(Car.class);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getLimit());
		@SuppressWarnings("unchecked")
		List<Car> result = query.list();
		page.setRoot(result);
		return page;
	}
	@Override
	public int updateCars(String conditions) {
		String sql = "update z_car set zt = 1 where id in(";
		sql+=conditions;
		sql+=")";
		System.out.println(sql);
		//Query query = getEntityManager().createNativeQuery(hql);
		Query query = (Query) getCurrentSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	@Override
	public void saveCar(Car car) {
		this.save(car);
	}

	@Override
	public Car findCarById(Long id) {
		return this.get(id);
	}

	@Override
	public void updateCar(Car car) {
		this.update(car);
	}

}
