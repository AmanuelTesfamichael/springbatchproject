package edu.mum.springbatchproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {

    private String firstname;
    private String lastname;
    private Double gpa;
    private int dob;

    public PersonDTO(String firstname, String lastname, Double gpa, int dob) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gpa = gpa;
        this.dob = dob;
    }


    @Override
    public String toString() {
        return "PersonDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gpa=" + gpa +
                ", dob=" + dob +
                '}';
    }


}
