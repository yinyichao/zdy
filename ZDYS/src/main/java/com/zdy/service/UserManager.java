package com.zdy.service;

import java.util.Map;

import com.zdy.entity.User;
import com.zdy.utils.Page;

public interface UserManager {

	public User getUser(User user);
	public Page<User> findPageUserByIntegrateds(int start, int limit, Map<String, String> conditions);
	public int updateUsers(String ids);
	public int checkUserName(String name);
	public User findUser(String name);
	public void saveUser(User user);
	public User findUserById(Long id);
}
