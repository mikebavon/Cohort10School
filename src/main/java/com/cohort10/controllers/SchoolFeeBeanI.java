package com.cohort10.controllers;

import com.cohort10.model.SchoolFee;

public interface SchoolFeeBeanI {

    SchoolFee save(SchoolFee schoolFee) throws Exception;

    void delete(SchoolFee schoolFee) throws Exception;

    void assignStudent(SchoolFee schoolFee) throws Exception;

    boolean checkFeePaid(SchoolFee schoolFee) throws Exception;

}
