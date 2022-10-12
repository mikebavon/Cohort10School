package com.cohort10.common;

public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non Binary"),
    NA("Not Specified");

    private String name;

    Gender(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
