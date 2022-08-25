package com.foxmindedjavaspring.university.model;

public class Faculty {
    private final University university;
    private final String department;
    private final String address;

    private Faculty(Builder builder) {
        this.university = builder.university;
        this.department = builder.department;
        this.address = builder.address;
    }

    public static final class Builder {
        private University university;
        private String department;
        private String address;

        public Builder withUniversity(University university) {
            this.university = university;
            return this;
        }

        public Builder withDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Faculty build() {
            return new Faculty(this);
        }
    }

    public University getUniversity() {
        return university;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

}
