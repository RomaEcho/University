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


ALTER SEQUENCE university_seq
OWNED BY NONE;

