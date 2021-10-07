package edu.mum.springbatchproject.service.Imp;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.repository.PersonRepository;
import edu.mum.springbatchproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    public PersonRepository personRepository;


    @Override
    public Set<String> findPersonByFirstname(String name) {
        return personRepository.findPersonByFirstname ( name );
    }

    @Override
    public Optional<Person> findPersonById(int id) {
        return personRepository.findById ( id );
    }


    public Person findPersonByUsernameAndPassword(String username, String password) {

        return personRepository.findPersonByUsernameAndAndPassword ( username, password );

    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personRepository.findByUsername ( username );
    }

    public Optional<Person> findPersonByUsername(String username) {
        return personRepository.findByUsername ( username );

    }

    public List<Person> findAllPerson() {
        return personRepository.findAll ();
    }

    @Override
    public void savePerson(Person person) {
        if (null != person) {

            personRepository.save ( person );


        }

    }
}