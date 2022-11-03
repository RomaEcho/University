ALTER TABLE exam_events ALTER COLUMN exam_start TYPE TiMESTAMP without time zone;
ALTER TABLE exam_events ALTER COLUMN exam_end TYPE TiMESTAMP without time zone;
ALTER TABLE exam_events DROP COLUMN date;

DROP TABLE IF EXISTS comments CASCADE;
CREATE SEQUENCE comments_id_seq;
CREATE TABLE comments( 
	id BIGINT NOT NULL DEFAULT nextval('comments_id_seq'),
	student_id BIGINT UNIQUE NOT NULL,
	course_id BIGINT UNIQUE NOT NULL,
    rating SMALLINT CHECK (rating BETWEEN 1 AND 10),
    rating_update TiMESTAMP,
	comment TEXT,
    comment_update TiMESTAMP,
	CONSTRAINT comment_pkey PRIMARY KEY(id), 
	CONSTRAINT student_fkey FOREIGN KEY(student_id) REFERENCES students (id) ON DELETE CASCADE,
	CONSTRAINT course_fkey FOREIGN KEY(course_id) REFERENCES courses (id) ON DELETE CASCADE 
);
ALTER SEQUENCE comments_id_seq
OWNED BY comments.id;

DROP SEQUENCE IF EXISTS university_seq;
CREATE SEQUENCE university_seq;

ALTER TABLE persons ALTER COLUMN id DROP DEFAULT;
ALTER TABLE persons ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE university_staff ALTER COLUMN id DROP DEFAULT;
ALTER TABLE university_staff ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE students ALTER COLUMN id DROP DEFAULT;
ALTER TABLE students ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE exams ALTER COLUMN id DROP DEFAULT;
ALTER TABLE exams ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE exam_events ALTER COLUMN id DROP DEFAULT;
ALTER TABLE exam_events ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE lecturers ALTER COLUMN id DROP DEFAULT;
ALTER TABLE lecturers ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE subjects ALTER COLUMN id DROP DEFAULT;
ALTER TABLE subjects ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE courses ALTER COLUMN id DROP DEFAULT;
ALTER TABLE courses ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE faculties ALTER COLUMN id DROP DEFAULT;
ALTER TABLE faculties ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE comments ALTER COLUMN id DROP DEFAULT;
ALTER TABLE comments ALTER COLUMN id SET DEFAULT nextval('university_seq');
ALTER TABLE universities ALTER COLUMN id DROP IDENTITY;
ALTER TABLE universities ALTER COLUMN id DROP DEFAULT;
ALTER TABLE universities ALTER COLUMN id SET DEFAULT nextval('university_seq');


DROP SEQUENCE persons_id_seq;
DROP SEQUENCE university_staff_id_seq;
DROP SEQUENCE students_id_seq;
DROP SEQUENCE exams_id_seq;
DROP SEQUENCE exam_events_id_seq;
DROP SEQUENCE lecturers_id_seq;
DROP SEQUENCE subjects_id_seq;
DROP SEQUENCE courses_id_seq;
DROP SEQUENCE faculties_id_seq;
DROP SEQUENCE comments_id_seq;

ALTER TABLE comments RENAME TO feedbacks;
ALTER TABLE feedbacks DROP CONSTRAINT comment_pkey;
ALTER TABLE feedbacks ADD CONSTRAINT feedback_pkey PRIMARY KEY(id);
ALTER TABLE feedbacks 
DROP COLUMN rating_update,
DROP COLUMN comment,
DROP COLUMN comment_update;
ALTER TABLE feedbacks 
ADD COLUMN creation_date TIMESTAMP,
ADD COLUMN update_date TIMESTAMP;

DROP TABLE IF EXISTS comments;
CREATE TABLE comments( 
	id BIGINT NOT NULL DEFAULT nextval('university_seq'),
    feedback_id BIGINT UNIQUE NOT NULL,
	comment TEXT,
    creation_date TiMESTAMP,
    update_date TiMESTAMP,
	CONSTRAINT comment_pkey PRIMARY KEY(id), 
	CONSTRAINT feedback_fkey FOREIGN KEY(feedback_id) REFERENCES feedbacks (id) ON DELETE CASCADE
);

ALTER TABLE universities ALTER COLUMN name type VARCHAR( 50 );
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public to new_user;

ALTER TABLE lecturers
ADD COLUMN first_name VARCHAR (50) NOT NULL,
ADD COLUMN last_name VARCHAR (50) NOT NULL,
ADD COLUMN birthday DATE NOT NULL,
ADD COLUMN gender VARCHAR (6) NOT NULL,
ADD COLUMN phone VARCHAR (20) NOT NULL,
ADD COLUMN email VARCHAR (50) NOT NULL,
ADD COLUMN address VARCHAR (50) NOT NULL,
ADD COLUMN title VARCHAR (20) NOT NULL;

ALTER TABLE students
ADD COLUMN first_name VARCHAR (50) NOT NULL,
ADD COLUMN last_name VARCHAR (50) NOT NULL,
ADD COLUMN birthday DATE NOT NULL,
ADD COLUMN gender VARCHAR (6) NOT NULL,
ADD COLUMN phone VARCHAR (20) NOT NULL,
ADD COLUMN email VARCHAR (50) NOT NULL,
ADD COLUMN address VARCHAR (50) NOT NULL,
ADD COLUMN title VARCHAR (20) NOT NULL;

ALTER TABLE students DROP CONSTRAINT student_fkey;
ALTER TABLE lecturers DROP CONSTRAINT lecturer_fkey;
DROP TABLE IF EXISTS university_staff;
DROP TABLE IF EXISTS persons;

ALTER TABLE faculties
RENAME COLUMN adress TO address;