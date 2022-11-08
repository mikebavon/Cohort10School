package com.cohort10.model;

import com.cohort10.common.Gender;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Person {

    @Column
    private String name;

    @Column
    private Gender gender;

    @Column
    private String idNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
