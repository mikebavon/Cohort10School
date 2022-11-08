package com.cohort10.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "school_fees")
public class SchoolFee extends BaseEntity{

    @Column(name = "txn_date")
    @Temporal(TemporalType.DATE)
    private Date txnDate;

    @Column(name = "reference_no")
    private String referenceNo;

    @Column
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public SchoolFee(){}

    public SchoolFee(String referenceNo){
        this.referenceNo = referenceNo;
    }

    public SchoolFee(Student student){
        this.student = student;
    }

    public Date getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(Date txnDate) {
        this.txnDate = txnDate;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
