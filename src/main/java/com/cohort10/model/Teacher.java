package com.cohort10.model;

import com.cohort10.common.Gender;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends BaseEntity{

    @Embedded
    private Person person;

    @Embedded
    private Address address;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}