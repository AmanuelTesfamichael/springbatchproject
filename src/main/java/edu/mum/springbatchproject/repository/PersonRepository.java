package edu.mum.springbatchproject.repository;

import edu.mum.springbatchproject.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional(readOnly = false)
public interface PersonRepository extends JpaRepository<Person , Integer> {

    @Query("select p from Person p where p.firstname = ?1")
    Set<String> findPersonByFirstname(String name);

    @Query("select p from Person p where p.id = ?1")
    Set<String> findPersonById(int id);
    List<Person> findAll();

    @Query("select p from Person p where p.id = ?1")
    Optional<Person> findById(int id);

    @Query("select p from Person p where p.username = :username")
    Optional<Person>  findByUsername(@Param ( "username" )String username);

    @Query("select p from Person p where p.username = :username and p.password = :password")
    public Person findPersonByUsernameAndAndPassword(@Param ( "username" )String username, @Param("password")String password);

    @Query("select p from Person p where p.role = :role")
    public List<Person> findPersonByRole(@Param ( "role" ) Person role);
}
