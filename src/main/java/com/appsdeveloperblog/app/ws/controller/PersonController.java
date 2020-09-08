package com.appsdeveloperblog.app.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.data.vo.PersonVO;
import com.appsdeveloperblog.app.ws.model.Person;
import com.appsdeveloperblog.app.ws.service.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", description = "Endpoint for person", tags = "PersonEndpoint")
@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	PersonService personService = new PersonService();

	@ApiOperation(value = "Gets all persons")
	@RequestMapping
	public List<PersonVO> findAll() {
		List<PersonVO> persons = personService.findAll();
		return persons;
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findPersonById(@PathVariable("id") Long id) throws Exception {
		PersonVO person = personService.findById(id);
		return person;
	}
	@PostMapping
	public PersonVO addPerson(@RequestBody PersonVO person) {
		PersonVO newPerson = personService.create(person);
		return newPerson;
	}
	
	@PutMapping
	public PersonVO updatePerson(@RequestBody Person person) throws Exception{
		PersonVO newPerson = personService.update(person);
		return newPerson;
	}
	
	@DeleteMapping(value = "/{id}")
	public void deletePerson(@PathVariable("id") String id) throws Exception{
		Long personId =  Long.valueOf(id).longValue();
		personService.delete(personId);
	}
}
