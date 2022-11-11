package com.cohort10.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;


@NamedQueries({
    @NamedQuery(name = Student.FIND_ALL, query = "SELECT s FROM Student s"),
    @NamedQuery(name = Student.FIND_WITH_ID, query = "SELECT s FROM Student s WHERE s.id=:studentId"),
    @NamedQuery(name = Student.FIND_WITH_REG_NO, query = "SELECT s FROM Student s WHERE s.regNo=:studentRegNo")
})
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    public static final String FIND_ALL = "Student.findAll";
    public static final String FIND_WITH_ID = "Student.findWithId";
    public static final String FIND_WITH_REG_NO = "Student.findWithRegNo";

    @Embedded
    private Person person;

    @Column(name = "reg_no")
    private String regNo;

    @Formula("(select count(s.id) from students s where s.gender='MALE')")
    private int countMale;

    @Formula("(select count(s.id) from students s where s.gender='FEMALE')")
    private int countFemale;

    @Formula("(select count(s.id) from students s where s.gender='NA')")
    private int countNa;

    public Student(){}

    public Student(Person person){
        this.person = person;
    }

    public Student(Long id, String regNo, int countMale, int  countFemale, int countNa){
        setId(id);
        this.regNo = regNo;
        this.countMale = countMale;
        this.countFemale = countFemale;
        this.countNa = countNa;
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

    public int getCountMale() {
        return countMale;
    }

    public void setCountMale(int countMale) {
        this.countMale = countMale;
    }

    public int getCountFemale() {
        return countFemale;
    }

    public void setCountFemale(int countFemale) {
        this.countFemale = countFemale;
    }

    public int getCountNa() {
        return countNa;
    }

    public void setCountNa(int countNa) {
        this.countNa = countNa;
    }
}
