package edu.mum.springbatchproject.service;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.domain.Role;
import edu.mum.springbatchproject.domain.RoleType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonService {

    Set<String> findPersonByFirstname(String name);
    Optional<Person> findPersonById(int id);
    Optional<Person> findByUsername(String username);
    List<Person> findAllPerson();
    void savePerson(Person person);

}
