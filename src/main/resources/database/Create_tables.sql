DROP TABLE IF EXISTS persons CASCADE;
DROP TABLE IF EXISTS university_staff CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS exams CASCADE;
DROP TABLE IF EXISTS exam_events CASCADE;
DROP TABLE IF EXISTS lecturers CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS course_events CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS faculties CASCADE;
DROP TABLE IF EXISTS universities CASCADE;

CREATE TABLE persons ( 
	person_id INT GENERATED ALWAYS AS IDENTITY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	birthday DATE NOT NULL,
	gender VARCHAR (6) NOT NULL,
	phone VARCHAR (20) NOT NULL,
	email VARCHAR (50) NOT NULL,
	address VARCHAR (50) NOT NULL,
	CONSTRAINT person_id_pkey PRIMARY KEY(person_id)
) ;

CREATE TABLE university_staff( 
	staff_id INT GENERATED ALWAYS AS IDENTITY,
	staff_number VARCHAR ( 50 ) UNIQUE NOT NULL,
	person_id INT UNIQUE NOT NULL,
	title VARCHAR (20) NOT NULL,
	CONSTRAINT staff_pkey PRIMARY KEY(staff_id), 
	CONSTRAINT staff_fkey FOREIGN KEY(person_id) REFERENCES persons (person_id) 
);

CREATE TYPE student_state AS ENUM ('active', 'terminated', 'absolvent');

CREATE TABLE students ( 
	student_id INT GENERATED ALWAYS AS IDENTITY,
	staff_id INT UNIQUE NOT NULL,
	starting_date DATE NOT NULL,
	course_events INT [], 
	exam_events INT [], 
	current_state student_state,
	CONSTRAINT student_pkey PRIMARY KEY(student_id), 
	CONSTRAINT student_fkey FOREIGN KEY(staff_id) REFERENCES university_staff(staff_id) 
);

CREATE TABLE exams ( 
	exam_id INT GENERATED ALWAYS AS IDENTITY,
	title VARCHAR (20) NOT NULL,
	exam_description TEXT NOT NULL,
	CONSTRAINT exam_pkey PRIMARY KEY(exam_id)
);

CREATE TYPE exam_state AS ENUM ('upcoming', 'ongoing', 'closed');

CREATE TABLE exam_events ( 
	exam_event_id INT GENERATED ALWAYS AS IDENTITY,
	exam_id INT NOT NULL,
	date DATE NOT NULL,
	current_state exam_state,
	lab INT NOT NULL,
	rate INT, 
	CONSTRAINT exam_event_pkey PRIMARY KEY(exam_event_id), 
	CONSTRAINT exam_event_fkey FOREIGN KEY(exam_id) REFERENCES exams(exam_id) 
);

CREATE TABLE lecturers ( 
	lecturer_id INT GENERATED ALWAYS AS IDENTITY,
	staff_id INT UNIQUE NOT NULL,
	level VARCHAR (20) NOT NULL,
	CONSTRAINT lecturer_pkey PRIMARY KEY(lecturer_id), 
	CONSTRAINT lecturer_fkey FOREIGN KEY(staff_id) REFERENCES university_staff(staff_id) 
);

CREATE TABLE subjects ( 
	subject_id INT GENERATED ALWAYS AS IDENTITY,
	subject_number INT UNIQUE NOT NULL,
	name VARCHAR (20) UNIQUE NOT NULL,
	description TEXT NOT NULL,
	CONSTRAINT subject_pkey PRIMARY KEY(subject_id)
);

CREATE TABLE courses ( 
	course_id INT GENERATED ALWAYS AS IDENTITY,
	subject_id INT NOT NULL,
	topic VARCHAR (20) NOT NULL,
	description TEXT NOT NULL,
	CONSTRAINT course_pkey PRIMARY KEY(course_id), 
	CONSTRAINT course_fkey FOREIGN KEY(subject_id) REFERENCES subjects (subject_id) 
);

CREATE TABLE course_events ( 
	course_event_id INT GENERATED ALWAYS AS IDENTITY,
	course_id INT NOT NULL,
	lecturer_id INT NOT NULL,
	course_event_start DATE NOT NULL,
	number_of_hours INT NOT NULL,
	rate INT, 
	CONSTRAINT course_event_pkey PRIMARY KEY(course_event_id), 
	CONSTRAINT course_fkey FOREIGN KEY(course_id) REFERENCES courses (course_id), 
	CONSTRAINT lecturer_fkey FOREIGN KEY(lecturer_id) REFERENCES lecturers (lecturer_id) 
);

CREATE TABLE faculties ( 
	faculty_id INT GENERATED ALWAYS AS IDENTITY,
	department VARCHAR (20) NOT NULL,
	adress VARCHAR (50) NOT NULL,
	subjects INT [], 
	CONSTRAINT faculty_pkey PRIMARY KEY(faculty_id) 
);

CREATE TABLE universities ( 
	university_id INT GENERATED ALWAYS AS IDENTITY,
	name VARCHAR (20) NOT NULL,
	faculties INT [], 
	CONSTRAINT university_pkey PRIMARY KEY(university_id) 
);