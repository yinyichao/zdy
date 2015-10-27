package com.zdy.dao;

import java.util.List;

import com.zdy.entity.Resources;

public interface ResourceDao {

	public List<Resources> getResourceAll(Long userId);
	public Resources findResourcesListByName(String name);
	public List<Resources> getResourceAll();
	
}
