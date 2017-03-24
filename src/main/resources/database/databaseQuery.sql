USE DATABASE database

DROP TABLE Usuario;
DROP TABLE Score;

CREATE TABLE Usuario
	(
	Usuario varchar(10) NOT NULL,
	Password varchar(20) NOT NULL,
	PRIMARY KEY (Usuario)
	)
;
CREATE TABLE Score
	(
	Ingrediente varchar(10) NOT NULL,
	Tipo int NOT NULL,
	Precio float,
	Stock int,
	PRIMARY KEY (Ingrediente)
	)
;

SELECT *
FROM Usuario
WHERE Usuario="admin"
AND Password="admin"

DELETE
FROM Usuario
WHERE Usuario="asd";

Insert into Usuario values ("123", "admin")
;