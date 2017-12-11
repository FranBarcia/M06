-- Table: Company
-- DROP TABLE Company;
CREATE TABLE Establiment(
  codi INTEGER NOT NULL,
  nom VARCHAR(50) NOT NULL,
  ciutat VARCHAR(50),
  CONSTRAINT establiment_pkey PRIMARY KEY (codi)
);

-- Table: Employee
-- DROP TABLE Employee;
CREATE TABLE Empleat(
  codi INTEGER NOT NULL,
  nom VARCHAR(50) NOT NULL,
  ciutat VARCHAR(50),
  establiment INTEGER,
  CONSTRAINT empleat_pkey PRIMARY KEY (codi),
  CONSTRAINT empleat_establiment_fkey FOREIGN KEY (Establiment) REFERENCES Establiment(codi)
);
