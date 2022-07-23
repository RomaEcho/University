DROP TABLE IF EXISTS persons CASCADE ;
DROP TABLE IF EXISTS university_staff CASCADE ; 
DROP TABLE IF EXISTS students CASCADE ;


CREATE TABLE persons {
	person_id INT GENERATED ALWAYS AS IDENTITY,
	first_name VARCHAR ( 50 ) NOT NULL,
	last_name VARCHAR ( 50 ) NOT NULL,
	birthday DATE NOT NULL,
	gender VARCHAR ( 6 ) NOT NULL,
	phone VARCHAR ( 20 ) NOT NULL,
	email VARCHAR ( 50 ) NOT NULL,
	address VARCHAR ( 50 ) NOT NULL,
	CONSTRAINT person_id_pkey PRIMARY KEY(person_id)
}

CREATE TABLE university_staff {
	staff_id INT GENERATED ALWAYS AS IDENTITY,
	person_id INT NOT NULL,
	title VARCHAR ( 20 ) NOT NULL,
	CONSTRAINT staff_pkey PRIMARY KEY(staff_id), 
	CONSTRAINT staff_fkey FOREIGN KEY(person_id) REFERENCES persons(person_id)
}

CREATE TYPE student_state AS ENUM ('active', 'terminated', 'absolvent');

CREATE TABLE students {
	student_id INT GENERATED ALWAYS AS IDENTITY,
	staff_id INT NOT NULL,
	starting_date DATE NOT NULL,
	course_events INT[],
	exam_events INT[],
	state student_state,
	CONSTRAINT student_pkey PRIMARY KEY(student_id), 
	CONSTRAINT student_fkey FOREIGN KEY(staff_id) REFERENCES university_staff(staff_id)
}




CREATE TABLE groups (
	group_id INT GENERATED ALWAYS AS IDENTITY,
	group_name VARCHAR ( 50 ) UNIQUE NOT NULL,
	CONSTRAINT group_id_pkey PRIMARY KEY(group_id)
) ;

CREATE TABLE studen3ts (
	student_id INT GENERATED ALWAYS AS IDENTITY,
	student_number VARCHAR ( 50 ) NOT NULL,
	group_id INT,
	first_name VARCHAR ( 50 ) NOT NULL,
	last_name VARCHAR ( 50 ) NOT NULL,
	CONSTRAINT student_id_pkey PRIMARY KEY(student_id),
	CONSTRAINT fk_group FOREIGN KEY(group_id) REFERENCES groups(group_id)
) ;

CREATE TABLE courses (
	course_id INT GENERATED ALWAYS AS IDENTITY,
	course_name VARCHAR ( 50 ) UNIQUE NOT NULL,
	course_description VARCHAR ( 255 ),
	CONSTRAINT course_id_pkey PRIMARY KEY(course_id)
) ;

CREATE TABLE student_courses (
	student_id INT NOT NULL,
	course_id INT NOT NULL,
	CONSTRAINT student_courses_pkey PRIMARY KEY (student_id, course_id),
	CONSTRAINT fk_student FOREIGN KEY(student_id) REFERENCES students (student_id)ON DELETE CASCADE,
	CONSTRAINT fk_course FOREIGN KEY(course_id) REFERENCES courses (course_id) ON DELETE CASCADE
) ;
