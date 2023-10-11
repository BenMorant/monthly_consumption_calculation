package com.ekwateur.model;

public class Individual extends Customer {

    private final String firstName;
    private final String lastName;
    private Title title;


    public Individual(Title title, String firstName, String lastName, Consumption consumption) {
        super(consumption);
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


}
