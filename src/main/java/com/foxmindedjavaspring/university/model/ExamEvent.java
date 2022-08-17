package com.foxmindedjavaspring.university.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExamEvent {
	private final Exam exam;
	private final LocalDate date;
	private final LocalTime startTime;
	private final LocalTime endTime;
	private final ExamState state;
	private final Integer lab;
	private final Integer rate;

	private ExamEvent(Builder builder) {
		this.exam = builder.exam;
		this.date = builder.date;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
		this.state = builder.state;
		this.lab = builder.lab;
		this.rate = builder.rate;
	}

	public static final class Builder {
		private Exam exam;
		private LocalDate date;
		private LocalTime startTime;
		private LocalTime endTime;
		private ExamState state;
		private Integer lab;
		private Integer rate;

		public Builder() {
		}

		public Builder withExam(Exam exam) {
			this.exam = exam;
			return this;
		}

		public Builder withDate(LocalDate date) {
			this.date = date;
			return this;
		}

		public Builder withStartTime(LocalTime startTime) {
			this.startTime = startTime;
			return this;
		}

		
		public Builder withEndTime(LocalTime endTime) {
			this.startTime = endTime;
			return this;
		}
		
		public Builder withState(ExamState state) {
			this.state = state;
			return this;
		}

		public Builder withLab(Integer lab) {
			this.lab = lab;
			return this;
		}
		
		public Builder withRate(Integer rate) {
			this.rate = rate;
			return this;
		}

		public ExamEvent build() {
			return new ExamEvent(this);
		}
	}
	
	public Exam getExam() {
		return exam;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}
	
	public ExamState getState() {
		return state;
	}

	public Integer getLab() {
		return lab;
	}

	public Integer getRate() {
		return rate;
	}
}
