package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class UniversityStaff extends Person {
	private final String staffId;
	private final String title;

	public UniversityStaff(String firstName, String lastName,
			LocalDate birthday, String gender, String phone, String email,
			String address, String staffId, String title) {
		super(firstName, lastName, birthday, gender, phone, email, address);
		this.staffId = staffId;
		this.title = title;
	}

	public String getStaffId() {
		return staffId;
	}

	public String getTitle() {
		return title;
	}
}
