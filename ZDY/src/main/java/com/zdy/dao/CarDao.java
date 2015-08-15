package com.zdy.dao;

import java.util.Map;

import com.zdy.entity.Car;
import com.zdy.utils.Page;

public interface CarDao {
	public Page<Car> findPageCarByIntegrateds(Page<Car> page, Map<String, String> conditions);
	public int updateCars(String conditions);
	public void saveCar(Car car);
	public Car findCarById(Long id);
	public void updateCar(Car car);

}
