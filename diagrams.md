# Estructura de Carpetas del Proyecto SGAECI (Backend)

## Estructura Completa del Proyecto

```
SGAECI-2-legacy/
├── 📁 Root Files
│   ├── pom.xml                          # Configuración Maven con dependencias y plugins
│   ├── nb-configuration.xml             # Configuración de NetBeans IDE
│   ├── system.properties                # Propiedades del sistema para Heroku
│   ├── Procfile                         # Archivo de despliegue para Heroku
│   ├── circle.yml                       # Configuración de CircleCI para CI/CD
│   ├── README.md                        # Documentación del proyecto
│   ├── tables.sql                       # Script DDL para creación de tablas de BD
│   ├── triggers.sql                     # Script de triggers de base de datos
│   ├── DocumentoFinal.docx/.pdf         # Documentación técnica del proyecto
│   └── architecture.md                  # Análisis de arquitectura del sistema
│
├── 📁 src/main/java/edu/eci/pdsw/samples/
│   │
│   ├── 📁 managedbeans/ (CAPA DE PRESENTACIÓN - Controllers JSF)
│   │   ├── BienvenidaBean.java          # Controller: Página principal y formulario de contacto
│   │   ├── LogginBean.java              # Controller: Autenticación y gestión de sesiones
│   │   ├── UsuarioBean.java             # Controller: Gestión de perfil usuario y generación PDFs
│   │   ├── SolicitudAfiliacionBean.java # Controller: Formularios de solicitud de afiliación
│   │   ├── ProcesarSolicitudAfiliacionBean.java # Controller: Admin - Aprobación/rechazo solicitudes
│   │   ├── PagoAfiliacionBean.java      # Controller: Envío de comprobantes de pago
│   │   ├── ProcesarPagosAfiliacionBean.java # Controller: Admin - Procesamiento de pagos
│   │   └── reporteVencerseBean.java     # Controller: Reportes de afiliaciones por vencer
│   │
│   ├── 📁 services/ (CAPA DE NEGOCIO - Business Logic)
│   │   ├── ServiciosSAGECI.java         # Interface: Definición de servicios de negocio
│   │   ├── ServiciosSAGECIDAOS.java     # Implementation: Lógica de negocio principal
│   │   └── ExcepcionServiciosSAGECI.java # Exception: Excepciones personalizadas del negocio
│   │
│   ├── 📁 entities/ (CAPA DE DOMINIO - Domain Models)
│   │   ├── Persona.java                 # Entity: Clase base para usuarios del sistema
│   │   ├── Estudiante.java              # Entity: Entidad estudiante (hereda de Persona)
│   │   ├── Egresado.java                # Entity: Entidad egresado (hereda de Persona)
│   │   ├── Egresado_Empresa.java        # Entity: Información laboral de egresados
│   │   ├── SolicitudAfiliacion.java     # Entity: Solicitudes de afiliación
│   │   ├── PagoAfiliacion.java          # Entity: Pagos y comprobantes
│   │   ├── estadoAfiliacion.java        # Entity: Estado de afiliación del usuario
│   │   ├── Rol.java                     # Entity: Roles del sistema (Admin, Egresado, Estudiante)
│   │   └── imagen.java                  # Entity: Manejo de imágenes para galería
│   │
│   ├── 📁 persistence/ (CAPA DE ACCESO A DATOS - Repository Layer)
│   │   ├── 📁 interfaces/ (DAO Interfaces)
│   │   │   ├── DaoFactory.java          # Factory: Patrón Factory para creación de DAOs
│   │   │   ├── DaoPersona.java          # Interface: DAO para entidad Persona
│   │   │   ├── DaoEstudiante.java       # Interface: DAO para entidad Estudiante
│   │   │   ├── DaoEgresado.java         # Interface: DAO para entidad Egresado
│   │   │   ├── DaoEgresadoEmpresa.java  # Interface: DAO para entidad Egresado_Empresa
│   │   │   ├── DaoSolicitudAfiliacion.java # Interface: DAO para SolicitudAfiliacion
│   │   │   ├── DaoPagoAfiliacion.java   # Interface: DAO para PagoAfiliacion
│   │   │   ├── DaoEstadoAfiliacion.java # Interface: DAO para estadoAfiliacion
│   │   │   ├── DaoRolPersona.java       # Interface: DAO para relación Rol-Persona
│   │   │   └── PersistenceException.java # Exception: Excepciones de persistencia
│   │   │
│   │   └── 📁 mybatisimpl/ (MyBatis Implementations)
│   │       ├── MyBatisDaoFactory.java   # Implementation: Factory de DAOs para MyBatis
│   │       ├── MyBatisDAOPersona.java   # Implementation: DAO MyBatis para Persona
│   │       ├── MyBatisDAOEstudiante.java # Implementation: DAO MyBatis para Estudiante
│   │       ├── MyBatisDAOEgresado.java  # Implementation: DAO MyBatis para Egresado
│   │       ├── MyBatisDAOEgresadoEmpresa.java # Implementation: DAO MyBatis para Egresado_Empresa
│   │       ├── MyBatisDAOSolicitudAfiliacion.java # Implementation: DAO MyBatis para SolicitudAfiliacion
│   │       ├── MyBatisDAOPagoAfiliacion.java # Implementation: DAO MyBatis para PagoAfiliacion
│   │       ├── MyBatisDAOEstadoAfiliacion.java # Implementation: DAO MyBatis para estadoAfiliacion
│   │       ├── MyBatisDAORolPersona.java # Implementation: DAO MyBatis para RolPersona
│   │       │
│   │       └── 📁 mappers/ (MyBatis Mapper Interfaces)
│   │           ├── PersonaMapper.java   # Mapper: SQL operations para Persona
│   │           ├── EstudianteMapper.java # Mapper: SQL operations para Estudiante
│   │           ├── EgresadoMapper.java  # Mapper: SQL operations para Egresado
│   │           ├── EmpresaMapper.java   # Mapper: SQL operations para Empresa
│   │           ├── SolicitudAfMapper.java # Mapper: SQL operations para SolicitudAfiliacion
│   │           ├── PagoAfMapper.java    # Mapper: SQL operations para PagoAfiliacion
│   │           ├── EstadoAfMapper.java  # Mapper: SQL operations para estadoAfiliacion
│   │           └── RolPersonaMapper.java # Mapper: SQL operations para RolPersona
│   │
│   ├── 📁 Security/ (SEGURIDAD Y AUTENTICACIÓN)
│   │   ├── SHA1.java                    # Utility: Hashing de contraseñas con SHA-1
│   │   └── Registro.java                # Utility: Logging y registro de eventos de seguridad
│   │
│   └── 📁 javamail/ (COMUNICACIONES - Email Module)
│       ├── 📁 core/
│       │   ├── Email.java               # Interface: Definición de email
│       │   ├── SimpleEmail.java         # Implementation: Email simple de texto
│       │   ├── EmailSender.java         # Interface: Definición de envío de emails
│       │   ├── SimpleEmailSender.java   # Implementation: Enviador de emails simple
│       │   ├── AbstractEmailSender.java # Abstract: Clase base para enviadores
│       │   └── EmailConfiguration.java  # Config: Configuración de servidor de email
│       │
│       └── 📁 client/standalone/
│           └── SendEmail.java           # Utility: Cliente standalone para envío de emails
│
├── 📁 src/main/resources/ (CONFIGURACIONES Y RECURSOS)
│   ├── 📁 Database Configuration
│   │   ├── mybatis-config.xml           # Config: Configuración principal de MyBatis (MySQL)
│   │   ├── h2-mybatis-config.xml        # Config: Configuración MyBatis para H2 (testing)
│   │   ├── applicationconfig.properties # Config: Configuración de aplicación (producción)
│   │   ├── applicationconfig _test.properties # Config: Configuración para testing
│   │   └── h2-applicationconfig.properties # Config: Configuración H2 para testing
│   │
│   ├── 📁 Security Configuration
│   │   └── shiro.ini                    # Config: Configuración de Apache Shiro (seguridad)
│   │
│   ├── 📁 Logging Configuration
│   │   └── log4j.properties             # Config: Configuración de logging con Log4J
│   │
│   ├── 📁 Email Configuration
│   │   └── javamail.properties          # Config: Configuración de JavaMail
│   │
│   └── 📁 mappers/ (SQL MAPPERS - MyBatis XML)
│       ├── PersonaMapper.xml            # SQL: Queries para operaciones de Persona
│       ├── EstudianteMapper.xml         # SQL: Queries para operaciones de Estudiante
│       ├── EgresadoMapper.xml           # SQL: Queries para operaciones de Egresado
│       ├── EmpresaMapper.xml            # SQL: Queries para operaciones de Empresa
│       ├── SolicitudAfMapper.xml        # SQL: Queries para operaciones de SolicitudAfiliacion
│       ├── PagoAfMapper.xml             # SQL: Queries para operaciones de PagoAfiliacion
│       ├── EstadoAfMapper.xml           # SQL: Queries para operaciones de estadoAfiliacion
│       └── RolPersonaMapper.xml         # SQL: Queries para operaciones de RolPersona
│
├── 📁 src/main/webapp/ (FRONTEND - Web Layer)
│   ├── 📁 Root Pages
│   │   ├── index.xhtml                  # View: Página raíz con enlaces principales
│   │   ├── Bienvenida.xhtml             # View: Página de bienvenida e información
│   │   ├── Registro.xhtml               # View: Formulario de login/autenticación
│   │   ├── unauthorized.xhtml           # View: Página de acceso no autorizado
│   │   └── sheet.css                    # Styles: Hoja de estilos principal
│   │
│   ├── 📁 open/ (PÁGINAS PÚBLICAS)
│   │   ├── SolicitudAfiliacionEst.xhtml # View: Formulario solicitud para estudiantes
│   │   └── SolicitudAfiliacionEgre.xhtml # View: Formulario solicitud para egresados
│   │
│   ├── 📁 Admin/ (ÁREA ADMINISTRATIVA)
│   │   ├── index.xhtml                  # View: Dashboard principal de administrador
│   │   ├── Opciones.xhtml               # View: Panel de opciones de administrador
│   │   ├── ProcesarSolicitudesAfiliacion.xhtml # View: Gestión de solicitudes pendientes
│   │   ├── ProcesarPagosAfiliacion.xhtml # View: Gestión de pagos pendientes
│   │   └── SolicitudesPorVencer.xhtml   # View: Reporte de afiliaciones por vencer
│   │
│   ├── 📁 Egresado/ (ÁREA DE EGRESADOS)
│   │   ├── index.xhtml                  # View: Dashboard de egresado
│   │   └── Opciones.xhtml               # View: Gestión de perfil y opciones de egresado
│   │
│   ├── 📁 Estudiante/ (ÁREA DE ESTUDIANTES)
│   │   ├── index.xhtml                  # View: Dashboard de estudiante
│   │   └── Opciones.xhtml               # View: Gestión de perfil y opciones de estudiante
│   │
│   ├── 📁 resource/ (RECURSOS ESTÁTICOS)
│   │   ├── AECI.PNG                     # Image: Logo principal de AECI
│   │   ├── aecimagen.png                # Image: Imagen institucional
│   │   ├── acceso-denegado.jpg          # Image: Imagen para acceso denegado
│   │   └── [otras imágenes]             # Images: Galería de empresas y recursos
│   │
│   ├── 📁 WEB-INF/ (CONFIGURACIÓN WEB)
│   │   ├── web.xml                      # Config: Configuración principal de la aplicación web
│   │   └── META-INF/
│   │       └── context.xml              # Config: Contexto de la aplicación
│   │
│   └── 📁 META-INF/
│       └── context.xml                  # Config: Configuración de contexto adicional
│
├── 📁 src/test/java/edu/eci/pdsw/samples/tests/ (TESTING)
│   ├── ReportePorVencerTest.java        # Test: Pruebas unitarias para reporte de vencimientos
│   └── PersonasPagoRealizarTest.java    # Test: Pruebas unitarias para gestión de pagos
│
├── 📁 target/ (DIRECTORIO DE COMPILACIÓN)
│   ├── PDSW.war                         # Artifact: Archivo WAR desplegable
│   ├── 📁 classes/                      # Compiled: Clases Java compiladas y recursos
│   ├── 📁 dependency/
│   │   └── webapp-runner.jar            # Dependency: Servidor embebido para Heroku
│   ├── 📁 db/                           # Database: Base de datos H2 para testing
│   │   ├── testdb2.mv.db                # DB File: Archivo de base de datos H2
│   │   └── testdb2.trace.db             # DB File: Archivo de traza H2
│   └── 📁 [otros directorios de build] # Build: Otros artefactos de construcción
│
└── 📁 nbproject/ (IDE CONFIGURATION)
    └── project.properties                # IDE: Configuración específica de NetBeans
```

## Resumen de Arquitectura por Capas

### 🎯 Capa de Presentación (Frontend)
**Responsabilidad:** Interfaz de usuario, validación de entrada, navegación
- **Tecnología:** JSF 2.2 + PrimeFaces 6.0 + XHTML
- **Archivos:** `src/main/webapp/` + `managedbeans/`
- **Patrón:** MVC con Managed Beans como Controllers

### 🏢 Capa de Negocio (Business Logic)
**Responsabilidad:** Reglas de negocio, workflows, validaciones complejas
- **Tecnología:** Java EE + Service Layer Pattern
- **Archivos:** `services/`
- **Patrón:** Service Layer con interfaces y implementaciones

### 🗄️ Capa de Acceso a Datos (Data Access)
**Responsabilidad:** Persistencia, transacciones, consultas SQL
- **Tecnología:** MyBatis 3.2.7 + MySQL/H2
- **Archivos:** `persistence/` + `mappers/`
- **Patrón:** DAO Pattern + Repository Pattern

### 🔐 Módulos Transversales
**Seguridad:** Apache Shiro + SHA-1 (`Security/`)
**Comunicaciones:** JavaMail API (`javamail/`)
**Configuración:** Properties + XML (`resources/`)
**Testing:** JUnit + H2 (`test/`)

---

# Diagramas de Arquitectura - SGAECI

## 1. Diagrama de Componentes

```mermaid
graph TB
    subgraph "Frontend Layer"
        JSF[JSF Views<br/>*.xhtml]
        MB[Managed Beans<br/>Controllers]
        CSS[CSS/Resources]
    end
    
    subgraph "Business Layer"
        SVC[ServiciosSAGECI<br/>Business Logic]
        EXC[ExcepcionServiciosSAGECI<br/>Custom Exceptions]
    end
    
    subgraph "Data Access Layer"
        DAO[DAO Interfaces]
        MYBATIS[MyBatis Implementation]
        MAPPERS[XML Mappers]
    end
    
    subgraph "Domain Layer"
        ENT[Entities<br/>Domain Models]
    end
    
    subgraph "Cross-cutting Concerns"
        SEC[Security Module<br/>Apache Shiro]
        MAIL[JavaMail Module]
        LOG[Logging<br/>Log4J]
    end
    
    subgraph "Data Sources"
        MYSQL[(MySQL Database<br/>Production)]
        H2[(H2 Database<br/>Testing)]
    end
    
    JSF --> MB
    MB --> SVC
    SVC --> DAO
    DAO --> MYBATIS
    MYBATIS --> MAPPERS
    MAPPERS --> MYSQL
    MAPPERS --> H2
    SVC --> ENT
    MB --> ENT
    
    SEC --> MB
    MAIL --> SVC
    LOG --> SVC
    EXC --> SVC
    
    CSS --> JSF
```

## 2. Diagrama de Despliegue

```mermaid
graph TB
    subgraph "Development Environment"
        DEV[Developer Machine<br/>NetBeans IDE<br/>Maven Build]
    end
    
    subgraph "CI/CD Pipeline"
        CI[CircleCI<br/>Continuous Integration]
        GIT[Git Repository]
    end
    
    subgraph "Production Environment - Heroku"
        LB[Load Balancer]
        subgraph "Application Container"
            WAR[PDSW.war<br/>Web Application]
            TOMCAT[Embedded Tomcat<br/>webapp-runner.jar]
        end
    end
    
    subgraph "Database Server"
        DBSERVER[desarrollo.is.escuelaing.edu.co:3306<br/>MySQL Server]
    end
    
    subgraph "External Services"
        MAILTRAP[Mailtrap.io<br/>Email Service]
    end
    
    subgraph "Client Browsers"
        ADMIN[Administrator<br/>Browser]
        STUDENT[Student<br/>Browser]
        GRADUATE[Graduate<br/>Browser]
    end
    
    DEV --> GIT
    GIT --> CI
    CI --> WAR
    WAR --> TOMCAT
    LB --> TOMCAT
    TOMCAT --> DBSERVER
    TOMCAT --> MAILTRAP
    
    ADMIN --> LB
    STUDENT --> LB
    GRADUATE --> LB
```

## 3. Diagrama de Flujo de Datos

```mermaid
flowchart TD
    START([User Access]) --> LOGIN{Authentication}
    LOGIN -->|Success| ROLE{User Role?}
    LOGIN -->|Failure| ERROR[Error Page]
    
    ROLE -->|Admin| ADMIN_DASH[Admin Dashboard]
    ROLE -->|Student| STUDENT_DASH[Student Dashboard]
    ROLE -->|Graduate| GRADUATE_DASH[Graduate Dashboard]
    
    ADMIN_DASH --> ADMIN_OPS{Admin Operations}
    ADMIN_OPS -->|Process Requests| PROC_REQ[Process Affiliation Requests]
    ADMIN_OPS -->|Process Payments| PROC_PAY[Process Payments]
    ADMIN_OPS -->|Generate Reports| GEN_REP[Generate Reports]
    
    STUDENT_DASH --> STUDENT_OPS{Student Operations}
    STUDENT_OPS -->|Apply| APPLY_REQ[Submit Affiliation Request]
    STUDENT_OPS -->|Profile| PROFILE[Manage Profile]
    
    GRADUATE_DASH --> GRADUATE_OPS{Graduate Operations}
    GRADUATE_OPS -->|Apply| APPLY_GRAD[Submit Affiliation Request]
    GRADUATE_OPS -->|Payment| SUBMIT_PAY[Submit Payment Proof]
    GRADUATE_OPS -->|Profile| PROFILE_GRAD[Manage Profile]
    
    PROC_REQ --> DB_UPDATE[(Database Update)]
    PROC_PAY --> DB_UPDATE
    APPLY_REQ --> DB_INSERT[(Database Insert)]
    APPLY_GRAD --> DB_INSERT
    SUBMIT_PAY --> DB_INSERT
    
    DB_UPDATE --> EMAIL_NOTIF[Email Notification]
    DB_INSERT --> EMAIL_NOTIF
    
    EMAIL_NOTIF --> MAILTRAP[Mailtrap Service]
    
    GEN_REP --> PDF_GEN[PDF Generation]
    PDF_GEN --> DOWNLOAD[File Download]
```

## 4. Diagrama de Clases Principal

```mermaid
classDiagram
    class Persona {
        -int documentoID
        -long telefono1
        -BigInteger telefono2
        -String tipoDocumentoID
        -String nombre
        -String apellido
        -String direccion
        -String correo_Personal
        -String genero
        -Rol rol
        +getDocumentoID() int
        +setDocumentoID(int)
        +getNombre() String
        +setNombre(String)
        +getCorreo_Personal() String
        +setCorreo_Personal(String)
    }
    
    class Estudiante {
        -String codigo
        -String programa
        -int semestre
        +getCodigo() String
        +setCodigo(String)
        +getPrograma() String
        +setPrograma(String)
    }
    
    class Egresado {
        -String codigo
        -String programa
        -Date fecha_Grado
        +getCodigo() String
        +setCodigo(String)
        +getFecha_Grado() Date
        +setFecha_Grado(Date)
    }
    
    class Rol {
        -int rolID
        -String nombre
        +getRolID() int
        +setRolID(int)
        +getNombre() String
        +setNombre(String)
    }
    
    class SolicitudAfiliacion {
        -int solicitudID
        -Date fecha_Solicitud
        -String estado
        -String observaciones
        -int documentoID
        +getSolicitudID() int
        +setSolicitudID(int)
        +getFecha_Solicitud() Date
        +setFecha_Solicitud(Date)
        +getEstado() String
        +setEstado(String)
    }
    
    class PagoAfiliacion {
        -int pagoID
        -Date fecha_Pago
        -double valor
        -String comprobante
        -int solicitudID
        +getPagoID() int
        +setPagoID(int)
        +getValor() double
        +setValor(double)
        +getComprobante() String
        +setComprobante(String)
    }
    
    class Egresado_Empresa {
        -int empresaID
        -String nombre_Empresa
        -String cargo
        -Date fecha_Ingreso
        -int documentoID
        +getEmpresaID() int
        +setEmpresaID(int)
        +getNombre_Empresa() String
        +setNombre_Empresa(String)
    }
    
    Persona <|-- Estudiante
    Persona <|-- Egresado
    Persona --> Rol
    Persona "1" --> "0..*" SolicitudAfiliacion
    SolicitudAfiliacion "1" --> "0..*" PagoAfiliacion
    Egresado "1" --> "0..*" Egresado_Empresa
```

## 5. Diagrama de Paquetes

```mermaid
graph TB
    subgraph "edu.eci.pdsw.samples"
        subgraph "managedbeans"
            MB1[BienvenidaBean]
            MB2[LogginBean]
            MB3[UsuarioBean]
            MB4[SolicitudAfiliacionBean]
            MB5[ProcesarSolicitudAfiliacionBean]
            MB6[PagoAfiliacionBean]
            MB7[ProcesarPagosAfiliacionBean]
            MB8[reporteVencerseBean]
        end
        
        subgraph "services"
            SVC1[ServiciosSAGECI]
            SVC2[ServiciosSAGECIDAOS]
            SVC3[ExcepcionServiciosSAGECI]
        end
        
        subgraph "entities"
            ENT1[Persona]
            ENT2[Estudiante]
            ENT3[Egresado]
            ENT4[SolicitudAfiliacion]
            ENT5[PagoAfiliacion]
            ENT6[Rol]
            ENT7[Egresado_Empresa]
            ENT8[estadoAfiliacion]
            ENT9[imagen]
        end
        
        subgraph "persistence"
            subgraph "interfaces"
                DAO1[DaoFactory]
                DAO2[DaoPersona]
                DAO3[DaoEstudiante]
                DAO4[DaoEgresado]
                DAO5[DaoSolicitudAfiliacion]
                DAO6[DaoPagoAfiliacion]
                DAO7[PersistenceException]
            end
            
            subgraph "mybatisimpl"
                MYB1[MyBatisDaoFactory]
                MYB2[MyBatisDAOPersona]
                MYB3[MyBatisDAOEstudiante]
                MYB4[MyBatisDAOEgresado]
                MYB5[MyBatisDAOSolicitudAfiliacion]
                MYB6[MyBatisDAOPagoAfiliacion]
                
                subgraph "mappers"
                    MAP1[PersonaMapper]
                    MAP2[EstudianteMapper]
                    MAP3[EgresadoMapper]
                    MAP4[SolicitudAfMapper]
                    MAP5[PagoAfMapper]
                end
            end
        end
        
        subgraph "Security"
            SEC1[SHA1]
            SEC2[Registro]
        end
        
        subgraph "javamail"
            subgraph "core"
                MAIL1[Email]
                MAIL2[SimpleEmail]
                MAIL3[EmailSender]
                MAIL4[SimpleEmailSender]
                MAIL5[AbstractEmailSender]
                MAIL6[EmailConfiguration]
            end
            
            subgraph "client.standalone"
                MAIL7[SendEmail]
            end
        end
    end
    
    managedbeans --> services
    services --> persistence
    services --> entities
    persistence --> entities
    managedbeans --> Security
    services --> javamail
    mybatisimpl --> interfaces
    mappers --> mybatisimpl
```

## 6. Diagramas de Clases por Carpeta

### 6.1. Managed Beans (Controllers)

```mermaid
classDiagram
    class BienvenidaBean {
        -String nombre
        -String email
        -String mensaje
        +enviarMensaje()
        +limpiarFormulario()
        +getNombre() String
        +setNombre(String)
        +getEmail() String
        +setEmail(String)
    }
    
    class LogginBean {
        -String usuario
        -String password
        -boolean loggedIn
        -String userRole
        +iniciarSesion() String
        +cerrarSesion() String
        +validarCredenciales() boolean
        +getUsuario() String
        +setUsuario(String)
        +isLoggedIn() boolean
    }
    
    class UsuarioBean {
        -Persona personaActual
        -List~Persona~ listaPersonas
        +actualizarPerfil() String
        +generarCertificadoPDF() void
        +cargarDatosUsuario() void
        +getPersonaActual() Persona
        +setPersonaActual(Persona)
    }
    
    class SolicitudAfiliacionBean {
        -SolicitudAfiliacion solicitud
        -List~SolicitudAfiliacion~ listaSolicitudes
        +enviarSolicitud() String
        +validarDatos() boolean
        +cargarSolicitudes() void
        +getSolicitud() SolicitudAfiliacion
        +setSolicitud(SolicitudAfiliacion)
    }
    
    class ProcesarSolicitudAfiliacionBean {
        -List~SolicitudAfiliacion~ solicitudesPendientes
        -SolicitudAfiliacion solicitudSeleccionada
        +aprobarSolicitud() String
        +rechazarSolicitud() String
        +cargarSolicitudesPendientes() void
        +enviarNotificacion() void
    }
    
    class PagoAfiliacionBean {
        -PagoAfiliacion pago
        -List~PagoAfiliacion~ listaPagos
        -String archivoComprobante
        +subirComprobante() String
        +validarPago() boolean
        +cargarPagos() void
        +getPago() PagoAfiliacion
        +setPago(PagoAfiliacion)
    }
    
    class ProcesarPagosAfiliacionBean {
        -List~PagoAfiliacion~ pagosPendientes
        -PagoAfiliacion pagoSeleccionado
        +aprobarPago() String
        +rechazarPago() String
        +cargarPagosPendientes() void
        +generarRecibo() void
    }
    
    class reporteVencerseBean {
        -List~SolicitudAfiliacion~ afiliacionesPorVencer
        -Date fechaLimite
        +generarReporte() void
        +exportarPDF() void
        +cargarAfiliacionesPorVencer() void
        +getFechaLimite() Date
        +setFechaLimite(Date)
    }
    
    UsuarioBean --> Persona
    SolicitudAfiliacionBean --> SolicitudAfiliacion
    ProcesarSolicitudAfiliacionBean --> SolicitudAfiliacion
    PagoAfiliacionBean --> PagoAfiliacion
    ProcesarPagosAfiliacionBean --> PagoAfiliacion
    reporteVencerseBean --> SolicitudAfiliacion
```

### 6.2. Services (Business Logic)

```mermaid
classDiagram
    class ServiciosSAGECI {
        <<interface>>
        +consultarPersona(int documentoID) Persona
        +registrarPersona(Persona persona) void
        +consultarEstudiante(String codigo) Estudiante
        +registrarEstudiante(Estudiante estudiante) void
        +consultarEgresado(String codigo) Egresado
        +registrarEgresado(Egresado egresado) void
        +crearSolicitudAfiliacion(SolicitudAfiliacion solicitud) void
        +consultarSolicitudesAfiliacion() List~SolicitudAfiliacion~
        +procesarSolicitudAfiliacion(int solicitudID, String estado) void
        +registrarPagoAfiliacion(PagoAfiliacion pago) void
        +consultarPagosAfiliacion() List~PagoAfiliacion~
        +procesarPagoAfiliacion(int pagoID, String estado) void
        +consultarAfiliacionesPorVencer(Date fecha) List~SolicitudAfiliacion~
        +getInstance() ServiciosSAGECI
    }
    
    class ServiciosSAGECIDAOS {
        -DaoFactory daoFactory
        -static ServiciosSAGECIDAOS instance
        +consultarPersona(int documentoID) Persona
        +registrarPersona(Persona persona) void
        +consultarEstudiante(String codigo) Estudiante
        +registrarEstudiante(Estudiante estudiante) void
        +consultarEgresado(String codigo) Egresado
        +registrarEgresado(Egresado egresado) void
        +crearSolicitudAfiliacion(SolicitudAfiliacion solicitud) void
        +consultarSolicitudesAfiliacion() List~SolicitudAfiliacion~
        +procesarSolicitudAfiliacion(int solicitudID, String estado) void
        +registrarPagoAfiliacion(PagoAfiliacion pago) void
        +consultarPagosAfiliacion() List~PagoAfiliacion~
        +procesarPagoAfiliacion(int pagoID, String estado) void
        +consultarAfiliacionesPorVencer(Date fecha) List~SolicitudAfiliacion~
        +getInstance() ServiciosSAGECI
        -ServiciosSAGECIDAOS()
    }
    
    class ExcepcionServiciosSAGECI {
        +ExcepcionServiciosSAGECI(String mensaje)
        +ExcepcionServiciosSAGECI(String mensaje, Throwable causa)
        +ExcepcionServiciosSAGECI(Throwable causa)
    }
    
    ServiciosSAGECI <|.. ServiciosSAGECIDAOS
    ServiciosSAGECIDAOS --> ExcepcionServiciosSAGECI
    ServiciosSAGECIDAOS --> DaoFactory
```

### 6.3. Entities (Domain Models)

```mermaid
classDiagram
    class Persona {
        -int documentoID
        -long telefono1
        -BigInteger telefono2
        -String tipoDocumentoID
        -String nombre
        -String apellido
        -String direccion
        -String correo_Personal
        -String genero
        -Rol rol
        +Persona()
        +Persona(int, long, BigInteger, String, String, String, String, String, String, Rol)
        +getDocumentoID() int
        +setDocumentoID(int)
        +getTelefono1() long
        +setTelefono1(long)
        +getNombre() String
        +setNombre(String)
        +getApellido() String
        +setApellido(String)
        +getCorreo_Personal() String
        +setCorreo_Personal(String)
        +getRol() Rol
        +setRol(Rol)
    }
    
    class Estudiante {
        -String codigo
        -String programa
        -int semestre
        +Estudiante()
        +Estudiante(String, String, int)
        +getCodigo() String
        +setCodigo(String)
        +getPrograma() String
        +setPrograma(String)
        +getSemestre() int
        +setSemestre(int)
    }
    
    class Egresado {
        -String codigo
        -String programa
        -Date fecha_Grado
        +Egresado()
        +Egresado(String, String, Date)
        +getCodigo() String
        +setCodigo(String)
        +getPrograma() String
        +setPrograma(String)
        +getFecha_Grado() Date
        +setFecha_Grado(Date)
    }
    
    class SolicitudAfiliacion {
        -int solicitudID
        -Date fecha_Solicitud
        -String estado
        -String observaciones
        -int documentoID
        +SolicitudAfiliacion()
        +SolicitudAfiliacion(int, Date, String, String, int)
        +getSolicitudID() int
        +setSolicitudID(int)
        +getFecha_Solicitud() Date
        +setFecha_Solicitud(Date)
        +getEstado() String
        +setEstado(String)
        +getObservaciones() String
        +setObservaciones(String)
        +getDocumentoID() int
        +setDocumentoID(int)
    }
    
    class PagoAfiliacion {
        -int pagoID
        -Date fecha_Pago
        -double valor
        -String comprobante
        -int solicitudID
        +PagoAfiliacion()
        +PagoAfiliacion(int, Date, double, String, int)
        +getPagoID() int
        +setPagoID(int)
        +getFecha_Pago() Date
        +setFecha_Pago(Date)
        +getValor() double
        +setValor(double)
        +getComprobante() String
        +setComprobante(String)
        +getSolicitudID() int
        +setSolicitudID(int)
    }
    
    class Rol {
        -int rolID
        -String nombre
        +Rol()
        +Rol(int, String)
        +getRolID() int
        +setRolID(int)
        +getNombre() String
        +setNombre(String)
    }
    
    class Egresado_Empresa {
        -int empresaID
        -String nombre_Empresa
        -String cargo
        -Date fecha_Ingreso
        -int documentoID
        +Egresado_Empresa()
        +Egresado_Empresa(int, String, String, Date, int)
        +getEmpresaID() int
        +setEmpresaID(int)
        +getNombre_Empresa() String
        +setNombre_Empresa(String)
        +getCargo() String
        +setCargo(String)
        +getFecha_Ingreso() Date
        +setFecha_Ingreso(Date)
        +getDocumentoID() int
        +setDocumentoID(int)
    }
    
    class estadoAfiliacion {
        -int estadoID
        -String nombre
        -String descripcion
        +estadoAfiliacion()
        +estadoAfiliacion(int, String, String)
        +getEstadoID() int
        +setEstadoID(int)
        +getNombre() String
        +setNombre(String)
        +getDescripcion() String
        +setDescripcion(String)
    }
    
    class imagen {
        -int imagenID
        -String nombre
        -String ruta
        -String descripcion
        +imagen()
        +imagen(int, String, String, String)
        +getImagenID() int
        +setImagenID(int)
        +getNombre() String
        +setNombre(String)
        +getRuta() String
        +setRuta(String)
        +getDescripcion() String
        +setDescripcion(String)
    }
    
    Persona <|-- Estudiante
    Persona <|-- Egresado
    Persona --> Rol
    Persona "1" --> "0..*" SolicitudAfiliacion
    SolicitudAfiliacion "1" --> "0..*" PagoAfiliacion
    Egresado "1" --> "0..*" Egresado_Empresa
    SolicitudAfiliacion --> estadoAfiliacion
```

### 6.4. Persistence Layer (DAO & MyBatis)

```mermaid
classDiagram
    class DaoFactory {
        <<abstract>>
        +getDaoPersona() DaoPersona
        +getDaoEstudiante() DaoEstudiante
        +getDaoEgresado() DaoEgresado
        +getDaoSolicitudAfiliacion() DaoSolicitudAfiliacion
        +getDaoPagoAfiliacion() DaoPagoAfiliacion
        +getDaoEstadoAfiliacion() DaoEstadoAfiliacion
        +getDaoRolPersona() DaoRolPersona
        +beginSession() void
        +commitTransaction() void
        +rollbackTransaction() void
        +endSession() void
        +getInstance() DaoFactory
    }
    
    class MyBatisDaoFactory {
        -SqlSessionFactory sessionFactory
        -ThreadLocal~SqlSession~ localSession
        +getDaoPersona() DaoPersona
        +getDaoEstudiante() DaoEstudiante
        +getDaoEgresado() DaoEgresado
        +getDaoSolicitudAfiliacion() DaoSolicitudAfiliacion
        +getDaoPagoAfiliacion() DaoPagoAfiliacion
        +getDaoEstadoAfiliacion() DaoEstadoAfiliacion
        +getDaoRolPersona() DaoRolPersona
        +beginSession() void
        +commitTransaction() void
        +rollbackTransaction() void
        +endSession() void
        -getSqlSession() SqlSession
    }
    
    class DaoPersona {
        <<interface>>
        +load(int documentoID) Persona
        +save(Persona persona) void
        +update(Persona persona) void
        +getAll() List~Persona~
    }
    
    class MyBatisDAOPersona {
        -SqlSessionFactory sessionFactory
        +load(int documentoID) Persona
        +save(Persona persona) void
        +update(Persona persona) void
        +getAll() List~Persona~
        -getSqlSession() SqlSession
    }
    
    class DaoEstudiante {
        <<interface>>
        +load(String codigo) Estudiante
        +save(Estudiante estudiante) void
        +update(Estudiante estudiante) void
        +getAll() List~Estudiante~
    }
    
    class MyBatisDAOEstudiante {
        -SqlSessionFactory sessionFactory
        +load(String codigo) Estudiante
        +save(Estudiante estudiante) void
        +update(Estudiante estudiante) void
        +getAll() List~Estudiante~
        -getSqlSession() SqlSession
    }
    
    class DaoSolicitudAfiliacion {
        <<interface>>
        +load(int solicitudID) SolicitudAfiliacion
        +save(SolicitudAfiliacion solicitud) void
        +update(SolicitudAfiliacion solicitud) void
        +getAll() List~SolicitudAfiliacion~
        +getSolicitudesPorEstado(String estado) List~SolicitudAfiliacion~
        +getSolicitudesPorVencer(Date fecha) List~SolicitudAfiliacion~
    }
    
    class MyBatisDAOSolicitudAfiliacion {
        -SqlSessionFactory sessionFactory
        +load(int solicitudID) SolicitudAfiliacion
        +save(SolicitudAfiliacion solicitud) void
        +update(SolicitudAfiliacion solicitud) void
        +getAll() List~SolicitudAfiliacion~
        +getSolicitudesPorEstado(String estado) List~SolicitudAfiliacion~
        +getSolicitudesPorVencer(Date fecha) List~SolicitudAfiliacion~
        -getSqlSession() SqlSession
    }
    
    class PersonaMapper {
        <<interface>>
        +getPersona(int documentoID) Persona
        +insertPersona(Persona persona) void
        +updatePersona(Persona persona) void
        +getAllPersonas() List~Persona~
    }
    
    class SolicitudAfMapper {
        <<interface>>
        +getSolicitudAfiliacion(int solicitudID) SolicitudAfiliacion
        +insertSolicitudAfiliacion(SolicitudAfiliacion solicitud) void
        +updateSolicitudAfiliacion(SolicitudAfiliacion solicitud) void
        +getAllSolicitudes() List~SolicitudAfiliacion~
        +getSolicitudesPorEstado(String estado) List~SolicitudAfiliacion~
        +getSolicitudesPorVencer(Date fecha) List~SolicitudAfiliacion~
    }
    
    class PersistenceException {
        +PersistenceException(String mensaje)
        +PersistenceException(String mensaje, Throwable causa)
        +PersistenceException(Throwable causa)
    }
    
    DaoFactory <|-- MyBatisDaoFactory
    DaoPersona <|.. MyBatisDAOPersona
    DaoEstudiante <|.. MyBatisDAOEstudiante
    DaoSolicitudAfiliacion <|.. MyBatisDAOSolicitudAfiliacion
    MyBatisDAOPersona --> PersonaMapper
    MyBatisDAOSolicitudAfiliacion --> SolicitudAfMapper
    MyBatisDaoFactory --> PersistenceException
```

### 6.5. Security Module

```mermaid
classDiagram
    class SHA1 {
        +getHash(String input) String
        +validatePassword(String input, String hash) boolean
        -convertToHex(byte[] data) String
    }
    
    class Registro {
        -Logger logger
        +registrarEvento(String evento) void
        +registrarError(String error) void
        +registrarAcceso(String usuario, String accion) void
        +registrarIntento(String usuario, boolean exitoso) void
        -formatearMensaje(String mensaje) String
    }
```

### 6.6. JavaMail Module

```mermaid
classDiagram
    class Email {
        <<interface>>
        +getSubject() String
        +setSubject(String subject) void
        +getFrom() String
        +setFrom(String from) void
        +getTo() List~String~
        +setTo(List~String~ to) void
        +getBody() String
        +setBody(String body) void
    }
    
    class SimpleEmail {
        -String subject
        -String from
        -List~String~ to
        -String body
        +getSubject() String
        +setSubject(String subject) void
        +getFrom() String
        +setFrom(String from) void
        +getTo() List~String~
        +setTo(List~String~ to) void
        +getBody() String
        +setBody(String body) void
    }
    
    class EmailSender {
        <<interface>>
        +send(Email email) void
        +isConfigured() boolean
    }
    
    class AbstractEmailSender {
        <<abstract>>
        -EmailConfiguration configuration
        +send(Email email) void
        +isConfigured() boolean
        #doSend(Email email) void
        #validateEmail(Email email) void
    }
    
    class SimpleEmailSender {
        +SimpleEmailSender(EmailConfiguration config)
        #doSend(Email email) void
        -createSession() Session
        -createMessage(Email email, Session session) MimeMessage
    }
    
    class EmailConfiguration {
        -String host
        -int port
        -String username
        -String password
        -boolean useSSL
        -boolean useTLS
        +getHost() String
        +setHost(String host) void
        +getPort() int
        +setPort(int port) void
        +getUsername() String
        +setUsername(String username) void
        +getPassword() String
        +setPassword(String password) void
        +isUseSSL() boolean
        +setUseSSL(boolean useSSL) void
        +isUseTLS() boolean
        +setUseTLS(boolean useTLS) void
    }
    
    class SendEmail {
        -EmailSender emailSender
        +main(String[] args) void
        +enviarNotificacion(String destinatario, String asunto, String mensaje) void
        -configurarEmail() EmailConfiguration
    }
    
    Email <|.. SimpleEmail
    EmailSender <|.. AbstractEmailSender
    AbstractEmailSender <|-- SimpleEmailSender
    AbstractEmailSender --> EmailConfiguration
    SendEmail --> EmailSender
    EmailSender ..> Email
```
