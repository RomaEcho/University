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
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	birthday DATE NOT NULL,
	gender VARCHAR (6) NOT NULL,
	phone VARCHAR (20) NOT NULL,
	email VARCHAR (50) NOT NULL,
	address VARCHAR (50) NOT NULL,
	CONSTRAINT person_id_pkey PRIMARY KEY(id)
) ;

CREATE TABLE university_staff( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	number VARCHAR ( 50 ) UNIQUE NOT NULL,
	person_id BIGINT UNIQUE NOT NULL,
	title VARCHAR (20) NOT NULL,
	CONSTRAINT staff_pkey PRIMARY KEY(id), 
	CONSTRAINT staff_fkey FOREIGN KEY(person_id) REFERENCES persons (id) ON DELETE CASCADE 
);

CREATE TABLE students ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	staff_id BIGINT UNIQUE NOT NULL,
	starting_date DATE NOT NULL,
	course_events INT [], 
	exam_events INT [], 
	state TEXT CHECK (state IN ('active', 'terminated', 'absolvent')),
	CONSTRAINT student_pkey PRIMARY KEY(id), 
	CONSTRAINT student_fkey FOREIGN KEY(staff_id) REFERENCES university_staff(id) ON DELETE CASCADE
);

CREATE TABLE exams ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	title VARCHAR (20) NOT NULL,
	description TEXT,
	CONSTRAINT exam_pkey PRIMARY KEY(id)
);

CREATE TABLE exam_events ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	exam_id BIGINT NOT NULL,
	date DATE NOT NULL,
	exam_start DATE NOT NULL,
	exam_end DATE NOT NULL,
	current_state exam_state,
	state TEXT CHECK (state IN ('upcoming', 'ongoing', 'closed')),
	lab INT NOT NULL,
	rate INT, 
	CONSTRAINT exam_event_pkey PRIMARY KEY(id), 
	CONSTRAINT exam_event_fkey FOREIGN KEY(exam_id) REFERENCES exams(id) ON DELETE CASCADE
);

CREATE TABLE lecturers ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	staff_id BIGINT UNIQUE NOT NULL,
	level VARCHAR (20) NOT NULL,
	CONSTRAINT lecturer_pkey PRIMARY KEY(id), 
	CONSTRAINT lecturer_fkey FOREIGN KEY(staff_id) REFERENCES university_staff(id) ON DELETE CASCADE
);

CREATE TABLE subjects ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	number INT UNIQUE NOT NULL,
	name VARCHAR (20) UNIQUE NOT NULL,
	description TEXT,
	CONSTRAINT subject_pkey PRIMARY KEY(id)
);

CREATE TABLE courses ( 
	id BIGINT GENERATED ALWAYS AS IDENTITY,
	lecturer_id BIGINT NOT NULL,
	subject_id BIGINT NOT NULL,
	topic VARCHAR (20) NOT NULL,
	description TEXT,
	start DATE NOT NULL,
	end_date DATE NOT NULL,
	number_of_hours INT NOT NULL,
	rate INT, 
	CONSTRAINT course_pkey PRIMARY KEY(id), 
	CONSTRAINT lecturer_fkey FOREIGN KEY(lecturer_id) REFERENCES lecturers (id),
	CONSTRAINT subject_fkey FOREIGN KEY(subject_id) REFERENCES subjects (id) ON DELETE CASCADE
);

CREATE TABLE student_courses (
	student_id INT NOT NULL,
	course_id INT NOT NULL,
	CONSTRAINT student_courses_pkey PRIMARY KEY (student_id, course_id),
	CONSTRAINT student_fkey FOREIGN KEY(student_id) REFERENCES students (id) ON DELETE CASCADE,
	CONSTRAINT course_fkey FOREIGN KEY(course_id) REFERENCES courses (id) ON DELETE CASCADE
) ;

CREATE TABLE universities ( 
	id INT GENERATED ALWAYS AS IDENTITY,
	name VARCHAR (20) NOT NULL,
	hq_location VARCHAR (50) NOT NULL,
	CONSTRAINT university_pkey PRIMARY KEY(id) 
);

CREATE TABLE faculties ( 
	id INT GENERATED ALWAYS AS IDENTITY,
	university_id INT NOT NULL,
	department VARCHAR (20) NOT NULL,
	adress VARCHAR (50) NOT NULL,
	CONSTRAINT faculty_pkey PRIMARY KEY(id),
	CONSTRAINT faculty_fkey FOREIGN KEY(university_id) REFERENCES universities (id) 
);

CREATE TABLE faculty_subjects (
	faculty_id INT NOT NULL,
	subject_id BIGINT NOT NULL,
	CONSTRAINT faculty_subjects_pkey PRIMARY KEY (faculty_id, subject_id),
	CONSTRAINT faculty_fkey FOREIGN KEY(faculty_id) REFERENCES faculties (id) ON DELETE CASCADE,
	CONSTRAINT subject_fkey FOREIGN KEY(subject_id) REFERENCES subjects (id) ON DELETE CASCADE
) ;


