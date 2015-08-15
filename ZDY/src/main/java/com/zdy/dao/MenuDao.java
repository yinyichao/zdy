package com.zdy.dao;

import java.util.List;

public interface MenuDao {

	public List<Object[]> getMenuAll(Long userId);
	
}
