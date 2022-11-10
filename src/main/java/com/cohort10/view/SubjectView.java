package com.cohort10.view;

import com.cohort10.bean.SubjectBeanI;
import com.cohort10.model.Student;
import com.cohort10.model.Subject;

import javax.ejb.EJB;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("SubjectView")
public class SubjectView implements Serializable {

    @EJB
    private SubjectBeanI subjectBean;

    public List<Subject> getList(){
        return subjectBean.list();
    }

}
