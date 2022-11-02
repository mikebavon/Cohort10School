package com.cohort10.controllers;

import javax.enterprise.inject.Alternative;

@Alternative
public class TestAlternativeTwo  implements TestAlternativeI{
    public void display() {
        System.out.println("Test .....2");
    }
}
