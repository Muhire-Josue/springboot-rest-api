package com.appsdeveloperblog.app.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
