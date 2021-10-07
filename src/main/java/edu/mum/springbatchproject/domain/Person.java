package edu.mum.springbatchproject.domain;


import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


//@NamedQueries({
//        @NamedQuery(name = "Person.findPersonByFirstname", query = "SELECT p.firstname FROM Person p  where p.firstname =:person"),
//        @NamedQuery(name = "Person.findPersonById", query = "SELECT p.id FROM Person p where p.id =:id"),
//        @NamedQuery(name = "Person.getAllPerson", query = "SELECT  p.firstname,p.lastname, p.gpa, p.dob FROM Person p where p.id is not null"),
//})

@Entity
@Getter
@Setter

@Component
@Inheritance(strategy = InheritanceType.JOINED)
public class Person{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "FirstName")
    private String firstname;

    @Column(name = "LastName")
    private String lastname;

    @Column(name = "GPA")
    private Double gpa;

    @Column(name = "DOB")
    private int dob;


    private String username;


    private String password;

    private RoleType role;

    public Person() {

    }

    public Person(int id, String firstname, String lastname, Double gpa, int dob, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gpa = gpa;
        this.dob = dob;
        this.username = username;
        this.password = password;
    }
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "Person_Role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new LinkedList<> ();

    public void addRole(Role role){
        roles.add(role);
    }
    public void deleteRole(Role role){
        roles.remove(role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gpa=" + gpa +
                ", dob=" + dob +
                '}';
    }

//    @Override
//    public void setResource(Resource resource) {
//        this.firstname = resource.getFilename ();
//
//    }
}
