package com.cohort10.model;

import javax.persistence.*;

@Entity
@Table(name = "exams")
public class Exam extends BaseEntity {

    @Column(name = "name", columnDefinition = "text")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
