CREATE TABLE department (
  id INTEGER PRIMARY KEY,
  name VARCHAR(50)
);

CREATE TABLE employee (
  id INTEGER NOT NULL PRIMARY KEY,
  firstname VARCHAR(50),
  surname  VARCHAR(50),
  departmentId INTEGER
);

ALTER TABLE employee
ADD FOREIGN KEY (departmentId) REFERENCES department (id);
