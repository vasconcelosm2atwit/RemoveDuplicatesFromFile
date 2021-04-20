package com.validity.monolithstarter.domain;

public class Person {
    private int id;
    private String first_name;
    private String last_name;
    private String company;
    private String email;
    private String address;

    public Person(){

    }

    public Person(String[] people){
        this.id = Integer.parseInt(people[0]);
        this.first_name = people[1];
        this.last_name = people[2];
        this.company = people[3];
        this.email = people[4];
        this.address = people[5];
    }

    public String getFullName(){
        return first_name + " " + last_name;
    }

    @Override
    public String toString() {
        return getFullName() + " " + getCompany() + " " + getEmail() + " " + getAddress();
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
