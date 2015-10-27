package com.zdy.dao;

import java.util.List;

import com.zdy.entity.Menu;

public interface MenuDao {

	public List<Menu> getMenuAll(Long userId);
	
}
