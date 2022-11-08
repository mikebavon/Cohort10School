package com.cohort10.model;

import javax.persistence.*;

@Entity
@Table(name = "parents_students")
public class ParentStudent extends BaseEntity {

    @ManyToOne
    private Parent parent;

    @ManyToOne
    private Student student;

    @Column
    @Enumerated(EnumType.STRING)
    private FamilyRelationship relationship;

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public FamilyRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(FamilyRelationship relationship) {
        this.relationship = relationship;
    }
}
