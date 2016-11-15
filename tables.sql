-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-11-14 03:30:37.046

-- tables
-- Table: Egresado
CREATE TABLE Egresado (
    DocumentoID int NOT NULL,
    Semestre_Graduacion varchar(6) NULL,
    Correo_Personal varchar(30) NOT NULL,
    Empresa varchar(100) NULL,
    Labora varchar(2) NULL,
    Cargo varchar(20) NULL,
    Fecha_Graduacion date NULL,
    CONSTRAINT Egresado_pk PRIMARY KEY (DocumentoID)
);

-- Table: Egresado_Empresa
CREATE TABLE Egresado_Empresa (
    Nombre varchar(100) NOT NULL,
    Direccion varchar(100) NULL,
    Telefono int NOT NULL,
    CONSTRAINT Egresado_Empresa_pk PRIMARY KEY (Nombre)
);

-- Table: Empresa_Convenio
CREATE TABLE Empresa_Convenio (
    ConvenioID int NOT NULL,
    Nombre varchar(30) NOT NULL,
    Telefono int NOT NULL,
    Direccion varchar(100) NOT NULL,
    Contacto varchar(20) NOT NULL,
    CONSTRAINT Empresa_Convenio_pk PRIMARY KEY (ConvenioID)
);

-- Table: Estado_afiliacion
CREATE TABLE Estado_afiliacion (
    DocumentoID int NOT NULL,
    Fecha_Inicio date NULL,
    Fecha_Fin date NOT NULL,
    Estado varchar(40) NOT NULL CHECK (Estado in ('AFILIADO INACTIVO','AFILIADO INACTIVO - PENDIENTE PAGO CUOTA AFILIACIÓN','AFILIADO ACTIVO')),
    CONSTRAINT Estado_afiliacion_pk PRIMARY KEY (DocumentoID)
);

-- Table: Estudiante
CREATE TABLE Estudiante (
    Codigo_Estudiante int NOT NULL,
    DocumentoID int NOT NULL,
    Semestre_Ponderado int NULL,
    Carrera varchar(50) NOT NULL,
    CONSTRAINT Estudiante_pk PRIMARY KEY (Codigo_Estudiante)
);

-- Table: Pago_Cuota
CREATE TABLE Pago_Cuota (
    PagoID int NOT NULL,
    Fecha_Cobro date NULL,
    Valor_Pago int NULL,
    DocumentoID int NOT NULL,
    Fecha_Pago date NULL,
    CONSTRAINT Pago_Cuota_pk PRIMARY KEY (PagoID)
);

-- Table: Persona
CREATE TABLE Persona (
    DocumentoID int NOT NULL,
    TipoDocumentoID varchar(2) NULL CHECK (TipoDocumentoID in ('CC','CE')),
    Nombre varchar(110) NULL,
    Direccion varchar(30) NULL,
    Correo varchar(100) NULL,
    Genero varchar(30) NOT NULL,
    Telefono bigint NOT NULL CHECK (Telefono>1000000 AND Telefono<=9999999),
    Celular bigint NULL CHECK (Celular>1000000 AND Celular<=9999999),
    CONSTRAINT Persona_pk PRIMARY KEY (DocumentoID)
);

-- Table: Persona_Servicios
CREATE TABLE Persona_Servicios (
    Persona_DocumentoID int NOT NULL,
    Servicio_ServicioID int NOT NULL,
    CONSTRAINT Persona_Servicios_pk PRIMARY KEY (Persona_DocumentoID,Servicio_ServicioID)
);

-- Table: Servicio
CREATE TABLE Servicio (
    ServicioID int NOT NULL,
    Nombre varchar(30) NOT NULL,
    Descripcion varchar(200) NOT NULL,
    ConvenioID int NOT NULL,
    CONSTRAINT Servicio_pk PRIMARY KEY (ServicioID)
);

-- Table: Solicitud_Afiliacion
CREATE TABLE Solicitud_Afiliacion (
    SolicitudID int NOT NULL AUTO_INCREMENT,
    DocumentoID int NOT NULL,
    Fecha_Solicitud timestamp NULL,
    Estado_Solicitud varchar(30) NULL,
    Comentario text NULL,
    CONSTRAINT Solicitud_Afiliacion_pk PRIMARY KEY (SolicitudID)
);

-- foreign keys
-- Reference: Egresado_Persona (table: Egresado)
ALTER TABLE Egresado ADD CONSTRAINT Egresado_Persona FOREIGN KEY (DocumentoID)
    REFERENCES Persona (DocumentoID);

-- Reference: Empresa_Egresado (table: Egresado)
ALTER TABLE Egresado ADD CONSTRAINT Empresa_Egresado FOREIGN KEY (Empresa)
    REFERENCES Egresado_Empresa (Nombre);

-- Reference: Estado_afiliacion_Persona (table: Estado_afiliacion)
ALTER TABLE Estado_afiliacion ADD CONSTRAINT Estado_afiliacion_Persona FOREIGN KEY (DocumentoID)
    REFERENCES Persona (DocumentoID);

-- Reference: Estudiante_Persona (table: Estudiante)
ALTER TABLE Estudiante ADD CONSTRAINT Estudiante_Persona FOREIGN KEY (DocumentoID)
    REFERENCES Persona (DocumentoID);

-- Reference: Pago_Cuota_Egresado (table: Pago_Cuota)
ALTER TABLE Pago_Cuota ADD CONSTRAINT Pago_Cuota_Egresado FOREIGN KEY (DocumentoID)
    REFERENCES Egresado (DocumentoID);

-- Reference: Persona_Servicios_Persona (table: Persona_Servicios)
ALTER TABLE Persona_Servicios ADD CONSTRAINT Persona_Servicios_Persona FOREIGN KEY (Persona_DocumentoID)
    REFERENCES Persona (DocumentoID);

-- Reference: Persona_Servicios_Servicio (table: Persona_Servicios)
ALTER TABLE Persona_Servicios ADD CONSTRAINT Persona_Servicios_Servicio FOREIGN KEY (Servicio_ServicioID)
    REFERENCES Servicio (ServicioID);

-- Reference: Persona_Solicitud_Afiliacion (table: Solicitud_Afiliacion)
ALTER TABLE Solicitud_Afiliacion ADD CONSTRAINT Persona_Solicitud_Afiliacion FOREIGN KEY (DocumentoID)
    REFERENCES Persona (DocumentoID);

-- Reference: Servicio_Empresa_Convenio (table: Servicio)
ALTER TABLE Servicio ADD CONSTRAINT Servicio_Empresa_Convenio FOREIGN KEY (ConvenioID)
    REFERENCES Empresa_Convenio (ConvenioID);
