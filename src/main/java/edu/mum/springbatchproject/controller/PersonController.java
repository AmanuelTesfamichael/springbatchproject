package edu.mum.springbatchproject.controller;


import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public List<Person>findAll(){
        return personService.findAllPerson ();
    }
    @GetMapping("/person/{name}")
    public Set<String> getPersonByName(@PathVariable("name") String name){
        return personService.findPersonByFirstname ( name );
    }

    @GetMapping("/person/{id}")
    public Optional<Person> getPersonById(@PathVariable("id") int id){
        return personService.findPersonById ( id );
    }

//    @GetMapping("/persons")
//    public Set<String> getAllPersons(){
//        return personService.findUniquePersons ();
//    }

}
