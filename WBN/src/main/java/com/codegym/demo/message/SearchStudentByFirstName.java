package com.codegym.demo.message;

public class SearchStudentByFirstName {
    private String firstName;

    public SearchStudentByFirstName() {
    }

    public SearchStudentByFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
