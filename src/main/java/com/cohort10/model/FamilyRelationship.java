package com.cohort10.model;

public enum FamilyRelationship {

    FATHER("Father"),

    MOTHER("Mother");

    private String name;

    FamilyRelationship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
