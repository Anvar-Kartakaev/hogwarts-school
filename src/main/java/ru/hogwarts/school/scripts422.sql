CREATE TABLE people
(
    StudentID INT,
    Name VARCHAR(50) PRIMARY KEY,
    Age INT,
    Driver_license INT
);

CREATE TABLE cars
(
    CarID INT,
    Brand VARCHAR(50) PRIMARY KEY,
    Model VARCHAR(50),
    Price REAL
)