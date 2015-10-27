package com.zdy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdy.dao.ResourceDao;
import com.zdy.entity.Resources;
import com.zdy.service.ResourceManager;
@Service("resourceManager")
public class ResourceManagerImpl implements ResourceManager {
	@Autowired
    @Qualifier("resourceDao")
	private ResourceDao resourceDao;
	
	@Transactional(readOnly = true)
	public List<Resources> getResourceAll(Long userId) {
//		 List<Object[]> objectList = resourceDao.getResourceAll(userId);
		 return resourceDao.getResourceAll(userId);
	}

	@Transactional(readOnly = true)
	public Resources findResourcesListByName(String name) {
		// TODO Auto-generated method stub
		return resourceDao.findResourcesListByName(name);
	}
	@Transactional(readOnly = true)
	public List<Resources> getResourceAll(){
		return resourceDao.getResourceAll();
	}
}
