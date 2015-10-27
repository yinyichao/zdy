package com.zdy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdy.dao.PersonDao;
import com.zdy.entity.Person;
import com.zdy.service.PersonManager;
import com.zdy.utils.Page;
@Service("personManager")
public class PersonManagerImpl implements PersonManager{
	
	@Autowired
    @Qualifier("personDao")
	private PersonDao personDao;

	@Transactional(readOnly = true)
	public Page<Person> findPagePersonByIntegrateds(int start, int limit,Map<String, String> conditions) {
		Page<Person> page = new Page<Person>();
		page.setStart(start);
		page.setLimit(limit);
		return personDao.findPagePersonByIntegrateds(page, conditions);
	}

	@Transactional(rollbackFor = Exception.class)
	public int updatePersons(String ids) {
		return personDao.updatePersons(ids);
	}

	@Transactional(rollbackFor = Exception.class)
	public void savePerson(Person person) {
		if(person.getId()!=null){
			personDao.updatePerson(person);
		}else{
			personDao.savePerson(person);
		}
	}

	@Transactional(readOnly = true)
	public Person findPersonById(Long id) {
		return personDao.findPersonById(id);
	}

}
