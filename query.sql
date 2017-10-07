CREATE TABLE authors (
  id INT auto_increment PRIMARY KEY,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  nationality CHAR(2),
  date_of_birth DATE NOT NULL,
  date_of_death DATE NOT NULL
);

INSERT INTO
authors
(firstname, lastname, nationality, date_of_birth, date_of_death)
VALUES
('Adam', 'Mickiewicz', 'PL', '1798-12-24', '1855-11-26'),
('Juliusz', 'Słowacki', 'PL', '1809-09-04', '1849-04-03'),
('Bolesław', 'Prus', 'PL', '1847-08-20', '1912-05-19'),
('Zygmunt', 'Krasiński', 'PL', '1812-02-19', '1859-02-23'),
('Henryk', 'Sienkiewicz', 'PL', '1846-05-05', '1916-11-15');

INSERT INTO
authors
(firstname, lastname, nationality, date_of_birth)
VALUES
('Andrzej', 'Sapkowski', 'PL', '1948-06-21');

CREATE TABLE addresses (
  id INT auto_increment PRIMARY KEY,
  street VARCHAR(50),
  house_number VARCHAR(20) NOT NULL,
  flat_number VARCHAR(20),
  postal_code CHAR(6) NOT NULL,
  city VARCHAR(50) NOT NULL
);

CREATE TABLE clients (
  id INT auto_increment PRIMARY KEY,
  firstname VARCHAR (255) NOT NULL ,
  lastname VARCHAR (255) NOT NULL ,
  document_number VARCHAR (30) NOT NULL ,
  pesel CHAR (11) NOT NULL ,
  addres_id INT NOT NULL,
  FOREIGN KEY (addres_id) REFERENCES addresses(id)
);

INSERT INTO addresses (street, house_number, flat_number, postal_code, city)
VALUES ("Ulica", "2", "2", "22-222", "Miasto");

