-- Crear tablas
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-11-23 01:44:32.166

-- tables
-- Table: Egresado
CREATE TABLE Egresado
(
    DocumentoID         int          NOT NULL,
    Semestre_Graduacion varchar(6)   NOT NULL,
    Correo_Estudiantil  varchar(50)  NULL,
    Empresa             varchar(100) NULL,
    Labora              varchar(20)  NULL,
    Cargo               varchar(20)  NULL,
    Fecha_Graduacion    date         NULL,
    Carrera             varchar(50)  NOT NULL,
    UNIQUE INDEX Correo_Estudiantil (Correo_Estudiantil),
    CONSTRAINT Egresado_pk PRIMARY KEY (DocumentoID)
);

-- Table: Egresado_Empresa
CREATE TABLE Egresado_Empresa
(
    Nombre    varchar(100) NOT NULL,
    Direccion varchar(100) NULL,
    Telefono  int          NOT NULL,
    CONSTRAINT Egresado_Empresa_pk PRIMARY KEY (Nombre)
);

-- Table: Empresa_Convenio
CREATE TABLE Empresa_Convenio
(
    ConvenioID int          NOT NULL,
    Nombre     varchar(30)  NOT NULL,
    Telefono   int          NOT NULL,
    Direccion  varchar(100) NOT NULL,
    Contacto   varchar(20)  NOT NULL,
    CONSTRAINT Empresa_Convenio_pk PRIMARY KEY (ConvenioID)
);

-- Table: Estado_afiliacion
CREATE TABLE Estado_afiliacion
(
    DocumentoID  int         NOT NULL,
    Fecha_Inicio date        NULL,
    Fecha_Fin    date        NOT NULL,
    Estado       varchar(40) NOT NULL CHECK (Estado in ('AFILIADO INACTIVO',
                                                        'AFILIADO INACTIVO - PENDIENTE PAGO CUOTA AFILIACIÓN',
                                                        'AFILIADO ACTIVO')),
    CONSTRAINT Estado_afiliacion_pk PRIMARY KEY (DocumentoID)
);

-- Table: Estudiante
CREATE TABLE Estudiante
(
    Codigo_Estudiante  int         NOT NULL,
    DocumentoID        int         NOT NULL,
    Semestre_Ponderado int         NULL,
    Carrera            varchar(50) NOT NULL,
    CONSTRAINT Estudiante_pk PRIMARY KEY (Codigo_Estudiante)
);

-- Table: Pago_Cuota
CREATE TABLE Pago_Cuota
(
    PagoID      int  NOT NULL,
    Fecha_Cobro date NULL,
    Valor_Pago  int  NULL,
    DocumentoID int  NOT NULL,
    Fecha_Pago  date NULL,
    CONSTRAINT Pago_Cuota_pk PRIMARY KEY (PagoID)
);

-- Table: Persona
CREATE TABLE Persona
(
    DocumentoID     int          NOT NULL,
    TipoDocumentoID varchar(2)   NULL CHECK (TipoDocumentoID in ('CC', 'CE')),
    Nombre          varchar(110) NULL,
    Apellido        varchar(100) NOT NULL,
    Direccion       varchar(30)  NULL,
    Correo_Personal varchar(100) NULL,
    Genero          varchar(30)  NOT NULL,
    Telefono        bigint       NULL CHECK (Telefono > 1000000 AND Telefono <= 9999999),
    Celular         bigint       NOT NULL CHECK (Celular > 1000000 AND Celular <= 9999999999),
    UNIQUE INDEX Celular (Celular),
    UNIQUE INDEX Correo_Personal (Correo_Personal),
    CONSTRAINT Persona_pk PRIMARY KEY (DocumentoID)
);

-- Table: Persona_Servicios
CREATE TABLE Persona_Servicios
(
    Persona_DocumentoID int NOT NULL,
    Servicio_ServicioID int NOT NULL,
    CONSTRAINT Persona_Servicios_pk PRIMARY KEY (Persona_DocumentoID, Servicio_ServicioID)
);

-- Table: Rol_Persona
CREATE TABLE Rol_Persona
(
    DocumentoID int          NOT NULL,
    Roles_rol   int          NOT NULL,
    Clave       varchar(150) NOT NULL,
    CONSTRAINT Rol_Persona_pk PRIMARY KEY (DocumentoID)
)
;

-- Table: Roles
CREATE TABLE Roles
(
    RolID  int         NOT NULL AUTO_INCREMENT,
    Nombre varchar(50) NOT NULL,
    CONSTRAINT Roles_pk PRIMARY KEY (RolID)
);

-- Table: Servicio
CREATE TABLE Servicio
(
    ServicioID  int          NOT NULL,
    Nombre      varchar(30)  NOT NULL,
    Descripcion varchar(200) NOT NULL,
    ConvenioID  int          NOT NULL,
    CONSTRAINT Servicio_pk PRIMARY KEY (ServicioID)
);

-- Table: Solicitud_Afiliacion
CREATE TABLE Solicitud_Afiliacion
(
    SolicitudID      int         NOT NULL AUTO_INCREMENT,
    DocumentoID      int         NOT NULL,
    Fecha_Solicitud  timestamp   NULL,
    Estado_Solicitud varchar(30) NULL,
    Comentario       text        NULL,
    CONSTRAINT Solicitud_Afiliacion_pk PRIMARY KEY (SolicitudID)
);

-- foreign keys
-- Reference: Egresado_Persona (table: Egresado)
ALTER TABLE Egresado
    ADD CONSTRAINT Egresado_Persona FOREIGN KEY (DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Empresa_Egresado (table: Egresado)
ALTER TABLE Egresado
    ADD CONSTRAINT Empresa_Egresado FOREIGN KEY (Empresa)
        REFERENCES Egresado_Empresa (Nombre);

-- Reference: Estado_afiliacion_Persona (table: Estado_afiliacion)
ALTER TABLE Estado_afiliacion
    ADD CONSTRAINT Estado_afiliacion_Persona FOREIGN KEY (DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Estudiante_Persona (table: Estudiante)
ALTER TABLE Estudiante
    ADD CONSTRAINT Estudiante_Persona FOREIGN KEY (DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Pago_Cuota_Egresado (table: Pago_Cuota)
ALTER TABLE Pago_Cuota
    ADD CONSTRAINT Pago_Cuota_Egresado FOREIGN KEY (DocumentoID)
        REFERENCES Egresado (DocumentoID);

-- Reference: Persona_Servicios_Persona (table: Persona_Servicios)
ALTER TABLE Persona_Servicios
    ADD CONSTRAINT Persona_Servicios_Persona FOREIGN KEY (Persona_DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Persona_Servicios_Servicio (table: Persona_Servicios)
ALTER TABLE Persona_Servicios
    ADD CONSTRAINT Persona_Servicios_Servicio FOREIGN KEY (Servicio_ServicioID)
        REFERENCES Servicio (ServicioID);

-- Reference: Persona_Solicitud_Afiliacion (table: Solicitud_Afiliacion)
ALTER TABLE Solicitud_Afiliacion
    ADD CONSTRAINT Persona_Solicitud_Afiliacion FOREIGN KEY (DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Rol_Persona_Persona (table: Rol_Persona)
ALTER TABLE Rol_Persona
    ADD CONSTRAINT Rol_Persona_Persona FOREIGN KEY (DocumentoID)
        REFERENCES Persona (DocumentoID);

-- Reference: Rol_Persona_Roles (table: Rol_Persona)
ALTER TABLE Rol_Persona
    ADD CONSTRAINT Rol_Persona_Roles FOREIGN KEY (Roles_rol)
        REFERENCES Roles (RolID);

-- Reference: Servicio_Empresa_Convenio (table: Servicio)
ALTER TABLE Servicio
    ADD CONSTRAINT Servicio_Empresa_Convenio FOREIGN KEY (ConvenioID)
        REFERENCES Empresa_Convenio (ConvenioID);

-- Crear triggers
DELIMITER $$

CREATE TRIGGER nuevo_estado_afiliacion
    AFTER UPDATE
    ON Solicitud_Afiliacion
    FOR EACH ROW
BEGIN
    DECLARE tempDocumnentoID int;
    SELECT DocumentoID FROM Egresado WHERE NEW.DocumentoID = Egresado.DocumentoID INTO tempDocumnentoID;

    IF (tempDocumnentoID IS NOT NULL) THEN
        IF ((NEW.Estado_Solicitud = 'ACEPTADA')) THEN
            IF ((SELECT DATEDIFF(SYSDATE(), Fecha_Graduacion)
                 FROM Egresado
                 WHERE NEW.DocumentoID = Egresado.DocumentoID) <= 180) THEN
                INSERT INTO Estado_afiliacion (DocumentoID, Fecha_Inicio, Fecha_Fin, Estado)
                VALUES (NEW.DocumentoID, SYSDATE(), DATE_ADD(SYSDATE(), INTERVAL 180 DAY), 'ACTIV0');
            ELSE
                INSERT INTO Estado_afiliacion (DocumentoID, Fecha_Inicio, Fecha_Fin, Estado)
                VALUES (NEW.DocumentoID, SYSDATE(), DATE_ADD(SYSDATE(), INTERVAL 30 DAY),
                        'INACTIVO - PENDIENTE PAGO CUOTA');
            END IF;
        ELSEIF ((NEW.Estado_Solicitud = 'RECHAZADA')) THEN
            DELETE FROM Persona WHERE DocumentoID = tempDocumnentoID;
        END IF;
    ELSE
        INSERT INTO Estado_afiliacion (DocumentoID, Fecha_Inicio, Fecha_Fin, Estado)
        VALUES (NEW.DocumentoID, SYSDATE(), DATE_ADD(SYSDATE(), INTERVAL 180 DAY), 'ACTIVO');
    END IF;
END$$

DELIMITER ;


-- Insertar usuario admin en Persona
INSERT INTO Persona (DocumentoID, Nombre, Apellido, Correo_Personal, Genero, Celular)
VALUES (1001, 'Administrador', 'Admin', 'admin@tuapp.com', 'Otro', 3000000000);

-- Insertar roles
INSERT IGNORE INTO Roles (RolID, Nombre)
VALUES (1, 'Administrador');
INSERT INTO Roles (Nombre)
VALUES ('Egresado');
INSERT INTO Roles (Nombre)
VALUES ('Estudiante');

-- Relacionar usuario con rol y clave
INSERT INTO Rol_Persona (DocumentoID, Roles_rol, Clave)
VALUES (1001, 1, '$shiro1$SHA-256$500000$OTxOWBStwOzU/30h5Pzq7A==$HAJjHXiAO2Q7wvAE5seJfMs25fzcYj/ozY/4hsJaiwY=');
