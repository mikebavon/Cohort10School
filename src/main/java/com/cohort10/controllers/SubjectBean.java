package com.cohort10.controllers;

import com.cohort10.model.Subject;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class SubjectBean implements SubjectBeanI {

    @PersistenceContext
    EntityManager em;

    public void add(Subject subject) {
        if (subject == null || StringUtils.isBlank(subject.getName()) || StringUtils.isBlank(subject.getCode()))
            return;

        em.merge(subject);

    }

    public void update(Subject subject) {

    }

    public void delete(Subject subject) {

    }

    public List<Subject> getList() {
        return em.createQuery("FROM Subject s", Subject.class).getResultList();

    }

}
