package com.foxmindedjavaspring.university.model;

public class UniversityStaff<B extends UniversityStaff.Builder<B>>
		extends Person<B> {
	private final long staffId;
	private final String title;

	UniversityStaff(Builder<B> builder) {
		super(builder);
		this.staffId = builder.staffId;
		this.title = builder.title;
	}

	public static class Builder<B extends UniversityStaff.Builder<B>>
			extends Person.Builder<B> {
		private long staffId;
		private String title;

		public B withStaffId(long staffId) {
			this.staffId = staffId;
			return (B) this;
		}

		public B withTitle(String title) {
			this.title = title;
			return (B) this;
		}

		@Override
		public UniversityStaff<B> build() {
			return new UniversityStaff<>(this);
		}
	}

	public long getStaffId() {
		return staffId;
	}

	public String getTitle() {
		return title;
	}
}
