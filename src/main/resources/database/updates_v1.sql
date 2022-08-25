ALTER TABLE exam_events ALTER COLUMN exam_start TYPE TiMESTAMP without time zone;
ALTER TABLE exam_events ALTER COLUMN exam_end TYPE TiMESTAMP without time zone;
ALTER TABLE exam_events DROP COLUMN date;