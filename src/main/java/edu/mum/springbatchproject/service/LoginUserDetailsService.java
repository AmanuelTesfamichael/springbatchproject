package edu.mum.springbatchproject.service;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.repository.PersonRepository;
import edu.mum.springbatchproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserDetailsService implements UserDetailsService{


    private final PersonService personService;

    @Autowired
    private PersonRepository personRepository;


    @Autowired
    public LoginUserDetailsService(PersonService personService)
    {
        this.personService = personService;
    }

//    @Scheduled(fixedRate = 500)
//    public void reportCurrentTime() {
//        PersonService.sendReservationReminder();
//    }

@Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    Optional<Person> optionalUser = personRepository.findByUsername ( username );
    if (optionalUser.isPresent()){
        return new edu.mum.springbatchproject.domain.object.LoginUserDetail ( optionalUser.get () );

    }

    String error = String.format("Username is not found");
    throw new UsernameNotFoundException(error);
}

}





