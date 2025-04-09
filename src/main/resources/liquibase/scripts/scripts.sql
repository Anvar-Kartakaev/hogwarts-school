-- liquibase formatted sql

-- changeset jwayne:1
SELECT FROM student;

-- changeset jwayne:2
CREATE INDEX student_name_index ON student (name);

-- changeset jwayne:3
SELECT FROM faculty;

-- changeset jwayne:4
CREATE INDEX faculty_color_index ON faculty (color);

-- changeset jwayne:5
CREATE INDEX faculty_name_index ON faculty (name);