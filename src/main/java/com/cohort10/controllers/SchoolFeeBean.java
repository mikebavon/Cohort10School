package com.cohort10.controllers;

import com.cohort10.model.SchoolFee;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Stateless
@Remote
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SchoolFeeBean implements SchoolFeeBeanI{

    @PersistenceContext
    EntityManager em;

    public SchoolFee save(SchoolFee schoolFee) throws Exception {
        if (schoolFee == null || schoolFee.getAmount() == null
                || schoolFee.getAmount().compareTo(BigDecimal.ZERO) < 1)
            throw new Exception("Invalid fee details");

        if (schoolFee.getId() != null)
            System.out.println("Entity manager will perform an update");

        return em.merge(schoolFee);

    }

    public void delete(SchoolFee schoolFee) throws Exception {
        if (schoolFee == null || schoolFee.getId() == null)
            throw new Exception("Invalid fee details");

        em.remove(em.find(SchoolFee.class, schoolFee.getId()));

    }

    public void assignStudent(SchoolFee schoolFee) throws Exception {
        if (schoolFee == null)
            throw new Exception("Invalid fee details");

        if (schoolFee.getStudent() == null)
            throw new Exception("Student not provided");

        em.merge(schoolFee);

    }

    public boolean checkFeePaid(SchoolFee schoolFee) throws Exception {
        List<SchoolFee> schoolFees = em.createQuery("FROM SchoolFee f WHERE f.student.regNo=:regNo",
            SchoolFee.class)
            .setParameter("regNo", schoolFee.getStudent().getRegNo())
            .getResultList();

        return !(schoolFees == null || schoolFees.isEmpty() || schoolFees.get(0) == null);

    }


}
