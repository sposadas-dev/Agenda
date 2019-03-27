CREATE DATABASE IF NOT EXISTS agenda;
USE agenda;

CREATE TABLE IF NOT EXISTS localidad(

	idLocalidad INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    PRIMARY KEY(idLocalidad)
    
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tipo_contacto(

	idTipo INT(11) NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    
    PRIMARY KEY(idTipo)
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS personas(

	idPersona INT(11) NOT NULL AUTO_INCREMENT,
	Nombre VARCHAR(50) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	dpto VARCHAR(20),
	calle VARCHAR(50),
	localidad INT(11),
	email VARCHAR(50),
	cumpleanios DATE,
	tipo INT(11),

	PRIMARY KEY (idPersona),
    
	FOREIGN KEY (localidad)	REFERENCES localidad(idLocalidad),

    FOREIGN KEY (tipo) REFERENCES tipo_contacto(idTipo)

    
)ENGINE=INNODB;