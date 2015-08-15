package com.zdy.service;

import java.util.Map;

import com.zdy.entity.Person;
import com.zdy.utils.Page;

public interface PersonManager {

	public Page<Person> findPagePersonByIntegrateds(int start, int limit, Map<String, String> conditions);
	public int updatePersons(String conditions);
	public void savePerson(Person person);
	public Person findPersonById(Long id);
	
}
