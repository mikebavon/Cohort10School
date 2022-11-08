package com.cohort10.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exams_taken")
public class ExamTaken extends BaseEntity {

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exam exam;

    @Column
    private Double score;

    @Column(name = "time_taken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTaken;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Date timeTaken) {
        this.timeTaken = timeTaken;
    }
}
