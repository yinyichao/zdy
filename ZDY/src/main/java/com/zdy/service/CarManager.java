package com.zdy.service;

import java.util.Map;

import com.zdy.entity.Car;
import com.zdy.utils.Page;

public interface CarManager {
	public Page<Car> findPageCarByIntegrateds(int start, int limit, Map<String, String> conditions);
	public int updateCars(String conditions);
	public void saveCar(Car car);
	public Car findCarById(Long id);
}
