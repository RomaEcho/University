package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class Person<B extends Person.Builder<B>> {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;

    Person(Builder<B> builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.phone = builder.phone;
        this.email = builder.email;
        this.address = builder.address;
    }

    public static class Builder<B extends Person.Builder<B>> {
        private String firstName;
        private String lastName;
        private LocalDate birthday;
        private String gender;
        private String phone;
        private String email;
        private String address;

        public B withFirstName(String firstName) {
            this.firstName = firstName;
            return (B) this;
        }

        public B withLastName(String lastName) {
            this.lastName = lastName;
            return (B) this;
        }

        public B withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return (B) this;
        }

        public B withGender(String gender) {
            this.gender = gender;
            return (B) this;
        }

        public B withPhone(String phone) {
            this.phone = phone;
            return (B) this;
        }

        public B withEmail(String email) {
            this.email = email;
            return (B) this;
        }

        public B withAddress(String address) {
            this.address = address;
            return (B) this;
        }

        public Person<B> build() {
            return new Person<>(this);
        }
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
}
