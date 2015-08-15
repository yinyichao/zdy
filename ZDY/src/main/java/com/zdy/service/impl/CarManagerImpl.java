package com.zdy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdy.dao.CarDao;
import com.zdy.entity.Car;
import com.zdy.service.CarManager;
import com.zdy.utils.Page;
@Service("carManager")
public class CarManagerImpl implements CarManager{
	
	@Autowired
    @Qualifier("carDao")
	private CarDao carDao;

	@Transactional(readOnly = true)
	public Page<Car> findPageCarByIntegrateds(int start, int limit,Map<String, String> conditions) {
		Page<Car> page = new Page<Car>();
		page.setStart(start);
		page.setLimit(limit);
		return carDao.findPageCarByIntegrateds(page, conditions);
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateCars(String ids) {
		return carDao.updateCars(ids);
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveCar(Car car) {
		if(car.getId()!=null){
			carDao.updateCar(car);
		}else{
			carDao.saveCar(car);
		}
	}

	@Transactional(readOnly = true)
	public Car findCarById(Long id) {
		return carDao.findCarById(id);
	}

	
}
