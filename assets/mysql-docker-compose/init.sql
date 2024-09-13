-- Crear base de datos (opcional si ya está creada)
CREATE DATABASE IF NOT EXISTS university_db;

USE university_db;

-- Crear tabla University
CREATE TABLE IF NOT EXISTS University (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Crear tabla Courses
CREATE TABLE IF NOT EXISTS Courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    university_id INT,
    FOREIGN KEY (university_id) REFERENCES University(id)
);

-- Crear tabla Student
CREATE TABLE IF NOT EXISTS Student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    course_id INT,
    university_id INT,
    FOREIGN KEY (course_id) REFERENCES Courses(id),
    FOREIGN KEY (university_id) REFERENCES University(id)
);

-- Insertar datos en University
INSERT INTO University (name) VALUES ('Harvard University');
INSERT INTO University (name) VALUES ('Stanford University');
INSERT INTO University (name) VALUES ('MIT');

-- Insertar datos en Courses
INSERT INTO Courses (name, university_id) VALUES ('Computer Science', 1);
INSERT INTO Courses (name, university_id) VALUES ('Mechanical Engineering', 1);
INSERT INTO Courses (name, university_id) VALUES ('Data Science', 2);
INSERT INTO Courses (name, university_id) VALUES ('Artificial Intelligence', 3);

-- Insertar datos en Student
INSERT INTO Student (name, course_id, university_id) VALUES ('Alice Johnson', 1, 1);
INSERT INTO Student (name, course_id, university_id) VALUES ('Bob Smith', 2, 1);
INSERT INTO Student (name, course_id, university_id) VALUES ('Charlie Brown', 3, 2);
INSERT INTO Student (name, course_id, university_id) VALUES ('David Wilson', 4, 3);
INSERT INTO Student (name, course_id, university_id) VALUES ('Eve Davis', 1, 1);
