package com.cohort10.model;

import com.cohort10.common.Gender;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Embedded
    private Person person;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Student(){}

    public Student(Person person, String regNo, Date dateOfBirth, Gender gender){
        this.person = person;
        this.regNo = regNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
