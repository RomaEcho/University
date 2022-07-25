package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class Person {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final String gender;
    private String phone;
    private String email;
    private String address;

    public Person(String firstName, String lastName, LocalDate birthday,
            String gender, String phone, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
