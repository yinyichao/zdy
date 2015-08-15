package com.zdy.dao;

import java.util.Map;

import com.zdy.entity.User;
import com.zdy.utils.Page;

public interface UserDao {

	public User getUser(User user);
	public Page<User> findPageUserByIntegrateds(Page<User> page, Map<String, String> conditions);
	public int updateUsers(String conditions);
	public User checkUserName(String name);
	public void saveUser(User user);
	public User findUserById(Long id);
	public void updateUser(User user);
}
