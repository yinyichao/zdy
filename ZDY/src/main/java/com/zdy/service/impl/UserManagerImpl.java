package com.zdy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdy.dao.UserDao;
import com.zdy.dao.UserRoleDao;
import com.zdy.entity.User;
import com.zdy.entity.UserRole;
import com.zdy.service.UserManager;
import com.zdy.utils.Page;
@Service("userManager")
public class UserManagerImpl implements UserManager {
	@Autowired
    @Qualifier("userDao")
	private UserDao userDao;
	@Autowired
    @Qualifier("userRoleDao")
	private UserRoleDao userRoleDao;
	
	@Transactional(readOnly = true)
	public User getUser(User user) {
		return userDao.getUser(user);
	}
	@Transactional(readOnly = true)
	public Page<User> findPageUserByIntegrateds(int start, int limit, Map<String, String> conditions){
		Page<User> page = new Page<User>();
		page.setStart(start);
		page.setLimit(limit);
		return userDao.findPageUserByIntegrateds(page, conditions);
	}

	@Transactional(rollbackFor = Exception.class)
	public int updateUsers(String ids) {
		int upResult = userDao.updateUsers(ids);
		if(upResult>0){
			userRoleDao.delUserRole(ids);
		}
		return upResult;
	}

	@Transactional(readOnly = true)
	public int checkUserName(String name) {
		int checkResult = 0;
		User user = userDao.checkUserName(name);
		if(user!=null){checkResult = 1;}
		return checkResult;
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) {
		if(user.getId()!=null){
			userDao.updateUser(user);
			userRoleDao.delUserRole(user.getId().toString());
		}else{
			userDao.saveUser(user);
		}
		if(user.getQx()==0){
			UserRole userRole = new UserRole();
			userRole.setUserid(user.getId());
			userRole.setRoleid((long)2);
			userRoleDao.saveUserRole(userRole);
		}
	}
	@Transactional(readOnly = true)
	public User findUserById(Long id){
		return userDao.findUserById(id);
	}
}
