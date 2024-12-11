package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Persons")
public class Person {
    @Id
    @Column(name = "personID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int personId;
    @Column(name = "lastName")
    String lastName;
    String firstName;
    String address;
    String city;

    public Person() {
//        this.personId = 0;
//        this.lastName = "";
//        this.firstName = "";
//        this.address = "";
//        this.city = "";
    }

    public Person(int personId, String lastName, String firstName, String address, String city) {
        this.personId = personId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.city = city;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}