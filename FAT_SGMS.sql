DROP DATABASE IF EXISTS "FAT_SGMS";

GO

CREATE DATABASE "FAT_SGMS";

GO

USE "FAT_SGMS";

GO

CREATE TABLE "User"(
    "id" INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "name" NVARCHAR(31) NOT NULL,
    "email" VARCHAR(63) NOT NULL UNIQUE,
    "avatar" VARCHAR(255) NULL,
    "gender" VARCHAR(7) NOT NULL,
    "dob" DATE NOT NULL,
    "role" VARCHAR(15) NOT NULL
);

CREATE TABLE "Student"(
    "id" INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "user_id" INT NOT NULL FOREIGN KEY REFERENCES "User"("id"),
    "dept_code" VARCHAR(7) NOT NULL
);

CREATE TABLE "Course"(
    "id" INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "name" VARCHAR(127) NOT NULL,
    "duration" INT NOT NULL,
    "syllabus" VARCHAR(255) NULL
);

CREATE TABLE "Group"(
    "id" INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "name" VARCHAR(31) NOT NULL,
    "course_id" INT NOT NULL FOREIGN KEY REFERENCES "Course"("id"),
);

CREATE TABLE "Student_group"(
    "student_id" INT NOT NULL FOREIGN KEY REFERENCES "Student"("id"),
    "group_id" INT NOT NULL FOREIGN KEY REFERENCES "Group"("id"),
	PRIMARY KEY ("student_id", "group_id"),

);

CREATE TABLE "Room"(
    "id" INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "name" VARCHAR(15) NOT NULL
);

CREATE TABLE "Time_slot"(
    "id" TINYINT NOT NULL PRIMARY KEY,
    "start_time" TIME NOT NULL,
    "end_time" TIME NOT NULL
);


CREATE TABLE "Session"(
    "id" BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "time_slot_id" TINYINT NOT NULL FOREIGN KEY REFERENCES "Time_slot"("id"),
    "instructor_id" INT NOT NULL FOREIGN KEY REFERENCES "User"("id"),
    "room_id" INT NOT NULL FOREIGN KEY REFERENCES "Room"("id"),
    "group_id" INT NOT NULL FOREIGN KEY REFERENCES "Group"("id"),
    "date" DATE NOT NULL
);

CREATE TABLE "Status"(
    "student_id" INT NOT NULL FOREIGN KEY REFERENCES "Student"("id"),
    "session_id" BIGINT NOT NULL FOREIGN KEY REFERENCES "Session"("id"),
    "attended" BIT NOT NULL DEFAULT 0,
    "record_time" TIMESTAMP NOT NULL,
	PRIMARY KEY ("student_id", "session_id")
);