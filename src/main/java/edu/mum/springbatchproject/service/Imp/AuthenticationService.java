package edu.mum.springbatchproject.service.Imp;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.repository.PersonRepository;
import edu.mum.springbatchproject.util.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private PersonRepository perosonRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalUser = perosonRep.findByUsername(username);
        if (optionalUser.isPresent()){
            return new UserSecurity (optionalUser.get());
        }

        String error = String.format("Username is not found");
        throw new UsernameNotFoundException(error);
    }


    public boolean createUser(Person personDTO) {
        Person pesron = new Person();

        var finduser = perosonRep.findByUsername(personDTO.getUsername()).orElse(null);
        if( finduser!=null) {
            return false;
        }

        pesron.setUsername(personDTO.getUsername());
        pesron.setPassword(passwordEncoder.encode(personDTO.getPassword()));
        pesron.setFirstname ( personDTO.getFirstname () );
        pesron.setLastname ( personDTO.getLastname () );
        pesron.setGpa ( personDTO.getGpa () );
        pesron.setDob ( personDTO.getDob () );
        perosonRep.save(pesron);

        return true;
    }
}
