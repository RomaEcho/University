package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;

public class Course {
	private final String topic;
	private final Subject subject;
	private final String description;
	private final LocalDate startDate;
	private final Lecturer lecturer;
	private final Integer numberOfHours;
	private final Integer rate;

	private Course(Builder builder) {
		this.startDate = builder.startDate;
		this.lecturer = builder.lecturer;
		this.numberOfHours = builder.numberOfHours;
		this.rate = builder.rate;
		this.topic = builder.topic;
		this.subject = builder.subject;
		this.description = builder.description;
	}

	public static final class Builder {
		private LocalDate startDate;
		private Lecturer lecturer;
		private Integer numberOfHours;
		private Integer rate;
		private String topic;
		private Subject subject;
		private String description;

		public Builder withStartDate(LocalDate startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder withLecturer(Lecturer lecturer) {
			this.lecturer = lecturer;
			return this;
		}

		public Builder withNumberOfHours(Integer numberOfHours) {
			this.numberOfHours = numberOfHours;
			return this;
		}

		public Builder withRate(Integer rate) {
			this.rate = rate;
			return this;
		}

		public Builder withTopic(String topic) {
			this.topic = topic;
			return this;
		}

		public Builder withSubject(Subject subject) {
			this.subject = subject;
			return this;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Course build() {
			return new Course(this);
		}
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public Integer getNumberOfHours() {
		return numberOfHours;
	}

	public Integer getRate() {
		return rate;
	}

	public String getTopic() {
		return topic;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}
}
