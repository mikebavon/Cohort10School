package com.cohort10.model;

import javax.persistence.*;

@Entity
@Table(name = "parents")
public class Parent extends BaseEntity{

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
