package com.cohort10.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = Subject.FIND_ALL, query = "SELECT s FROM Subject s")
@Entity
@Table(name = "subjects")
public class Subject extends BaseEntity{

    public static final String FIND_ALL = "Subject.findAll";

    @Column
    private String name;

    @Column
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
