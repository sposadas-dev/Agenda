CREATE DATABASE IF NOT EXISTS agenda;
USE agenda;

CREATE TABLE IF NOT EXISTS localidad
(
  idLocalidad int (11) NOT NULL AUTO_INCREMENT,
  Nombre varchar (15) NOT NULL,

  PRIMARY KEY (idLocalidad)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS tipoContacto
(
  idTipoContacto int (11) NOT NULL AUTO_INCREMENT, 
  nombre varchar(10) NOT NULL,
  
  PRIMARY KEY(idTipoContacto)
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS domicilio
(
  idDomicilio int (11) NOT NULL AUTO_INCREMENT,
  Calle varchar (45) NOT NULL,
  Altura varchar (20) NOT NULL,
  Piso varchar (20) NOT NULL,
  idLocalidad int (11),

  PRIMARY KEY (idDomicilio),

  FOREIGN KEY (idLocalidad) REFERENCES localidad (idLocalidad) 
  
)ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS personas
(
  idPersona int(11) NOT NULL AUTO_INCREMENT,
  Nombre varchar(45) NOT NULL,
  Telefono varchar(20) NOT NULL,
  idDomicilio int(11),
  idTipoContacto int(11),   
  PRIMARY KEY (idPersona),
  
  FOREIGN KEY (idDomicilio) REFERENCES domicilio(idDomicilio),
  FOREIGN KEY (idTipoContacto) REFERENCES tipoContacto(idTipoContacto) 
) ENGINE=INNODB;