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