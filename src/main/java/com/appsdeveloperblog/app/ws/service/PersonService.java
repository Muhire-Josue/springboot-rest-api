package com.appsdeveloperblog.app.ws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.converter.DozerConverter;
import com.appsdeveloperblog.app.ws.data.vo.PersonVO;
import com.appsdeveloperblog.app.ws.model.Person;
import com.appsdeveloperblog.app.ws.repository.PersonRepository;

@Service
public class PersonService {


	@Autowired
	PersonRepository personRepository;

	public PersonVO findById(Long id) throws Exception {
		Person person = personRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
		return DozerConverter.parseObject(person, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		List<PersonVO> persons = DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);
		return persons;
	}

	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVO update(Person person) throws Exception{
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new Exception("User not found"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		
		PersonVO updatedPerson = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		return updatedPerson;
	}

	public void delete(Long id) throws Exception{
		Person entity = personRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
		personRepository.delete(entity);
		
	}
}
