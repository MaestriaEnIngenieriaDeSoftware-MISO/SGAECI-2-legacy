-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-11-03 16:24:19.933

-- tables
-- Table: Egresado
CREATE TABLE Egresado (
    DocumentoID int NOT NULL,
    Semestre_Graduacion varchar(6) NULL,
    Correo_Personal varchar(30) NOT NULL,
    EmpresaID int NOT NULL,
    Empleado bool NULL,
    CONSTRAINT Egresado_pk PRIMARY KEY (DocumentoID)
);

-- Table: Egresado_Empresa
CREATE TABLE Egresado_Empresa (
    EmpresaID int NOT NULL,
    Nombre varchar(100) NULL,
    Direccion varchar(100) NULL,
    Telefono int NOT NULL,
    Cargo varchar(20) NULL,
    CONSTRAINT Egresado_Empresa_pk PRIMARY KEY (EmpresaID)
);

-- Table: Empresa_Convenio
CREATE TABLE Empresa_Convenio (
    ConvenioID int NOT NULL,
    Nombre varchar(30) NOT NULL,
    Telefono int NOT NULL,
    Direccion varchar(100) NOT NULL,
    Contacto varchar(20) NOT NULL,
    DocumentoID int NOT NULL,
    CONSTRAINT Empresa_Convenio_pk PRIMARY KEY (ConvenioID)
);

-- Table: Estado_afiliacion
CREATE TABLE Estado_afiliacion (
    DocumentoID int NOT NULL,
    Fecha_Inicio date NULL,
    Fecha_Fin date NOT NULL,
    Estado varchar(30) NOT NULL,
    CONSTRAINT Estado_afiliacion_pk PRIMARY KEY (DocumentoID)
);

-- Table: Estudiante
CREATE TABLE Estudiante (
    Codigo_Estudiante int NOT NULL,
    DocumentoID int NOT NULL,
    Semestre_Ponderado int NULL,
    CONSTRAINT Estudiante_pk PRIMARY KEY (Codigo_Estudiante)
);

-- Table: Pago_Cuota
CREATE TABLE Pago_Cuota (
    PagoID int NOT NULL,
    Fecha date NULL,
    Valor_Pago int NULL,
    DocumentoID int NOT NULL,
    CONSTRAINT Pago_Cuota_pk PRIMARY KEY (PagoID)
);

-- Table: Persona
CREATE TABLE Persona (
    DocumentoID int NOT NULL,
    TipoDocumentoID varchar(2) NULL,
    Nombre varchar(30) NULL,
    Direccion varchar(30) NULL,
    Carrera varchar(20) NULL,
    Correo varchar(30) NULL,
    Genero varchar(30) NOT NULL,
    CONSTRAINT Persona_pk PRIMARY KEY (DocumentoID)
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
    DocumentoID int NOT NULL,
    Fecha_Solicitud date NULL,
    Tipo_Solicitante char(30) NULL,
    Estado_Solicitud varchar(30) NULL,
    Comentario varchar(140) NULL,
    CONSTRAINT Solicitud_Afiliacion_pk PRIMARY KEY (DocumentoID)
);

-- Table: Telefonos
CREATE TABLE Telefonos (
    Identificador int NOT NULL,
    DocumentoID int NOT NULL,
    Telefono int NULL,
    CONSTRAINT Telefonos_pk PRIMARY KEY (Identificador)
);



