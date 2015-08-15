package com.zdy.dao;

import java.util.Map;

import com.zdy.entity.Person;
import com.zdy.utils.Page;

public interface PersonDao {

	public Page<Person> findPagePersonByIntegrateds(Page<Person> page, Map<String, String> conditions);
	public int updatePersons(String conditions);
	public void savePerson(Person person);
	public Person findPersonById(Long id);
	public void updatePerson(Person person);
	
}
