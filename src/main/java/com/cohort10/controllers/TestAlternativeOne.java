package com.cohort10.controllers;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestAlternativeOne implements TestAlternativeI{
    public void display() {
        System.out.println("Test .....1");
    }
}
