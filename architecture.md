# Análisis de la Arquitectura de SGAECI (Sistema de Gestión de Asociación de Egresados de la Escuela Colombiana de Ingeniería)

## Preguntas clave 

### 1. **¿Cuáles son los componentes funcionales de la aplicación y cómo se relacionan entre sí?** 

La aplicación SGAECI está estructurada como una aplicación web monolítica de 3 capas con los siguientes componentes principales:

**Capa de Presentación (Frontend):**
- **JSF (JavaServer Faces) con PrimeFaces**: Framework principal para la interfaz de usuario
- **Managed Beans**: Controladores que manejan la lógica de presentación
  - `BienvenidaBean`: Página principal y contacto
  - `LogginBean`: Autenticación y sesiones
  - `UsuarioBean`: Gestión de usuarios y certificados PDF
  - `SolicitudAfiliacionBean`: Procesamiento de solicitudes
  - `ProcesarSolicitudAfiliacionBean`: Administración de solicitudes
  - `PagoAfiliacionBean`: Gestión de pagos
  - `ProcesarPagosAfiliacionBean`: Administración de pagos
  - `reporteVencerseBean`: Reportes de vencimiento

**Capa de Negocio (Business Logic):**
- **ServiciosSAGECI**: Interfaz principal de servicios
- **ServiciosSAGECIDAOS**: Implementación concreta de servicios de negocio
- Manejo de reglas de negocio para afiliaciones, pagos, usuarios y reportes

**Capa de Datos (Data Access):**
- **Patrón DAO**: Interfaces y implementaciones para acceso a datos
- **MyBatis**: Framework ORM para mapeo objeto-relacional
- **MySQL**: Base de datos relacional principal
- **H2**: Base de datos en memoria para testing

**Componentes de Seguridad:**
- **Apache Shiro**: Framework de seguridad y autenticación
- **SHA1**: Hashing de contraseñas
- Gestión de roles (Administrador, Egresado, Estudiante)

### 2. **¿Cómo es el despliegue de los componentes en el entorno productivo?** 

**Arquitectura de Despliegue:**
- **Aplicación WAR**: Empaquetado como archivo `PDSW.war`
- **Servidor de Aplicaciones**: Apache Tomcat (usando webapp-runner embedded)
- **Base de Datos**: MySQL en servidor remoto (`desarrollo.is.escuelaing.edu.co:3306`)
- **Despliegue en la Nube**: Configurado para Heroku mediante `Procfile`
  ```
  web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
  ```
- **Puerto**: Configurable mediante variable de entorno `$PORT`
- **Maven**: Sistema de construcción y gestión de dependencias

### 3. **¿Cómo interactúan los componentes con las fuentes de datos?** 

**Patrón de Acceso a Datos:**
- **Factory Pattern**: `DaoFactory` gestiona la creación de DAOs
- **MyBatis Configuration**: 
  - Archivo `mybatis-config.xml` con configuración de base de datos
  - Mappers XML para cada entidad (`PersonaMapper.xml`, `EstudianteMapper.xml`, etc.)
- **Gestión de Transacciones**: Control manual con `beginSession()`, `commitTransaction()`, `rollbackTransaction()`
- **Pool de Conexiones**: BoneCP para gestión eficiente de conexiones
- **Múltiples Entornos**: 
  - Producción: MySQL remoto
  - Testing: H2 embebida

**Entidades Principales:**
- `Persona`, `Estudiante`, `Egresado`, `SolicitudAfiliacion`, `PagoAfiliacion`, `estadoAfiliacion`, `Egresado_Empresa`

### 4. **¿Qué patrones y tácticas de arquitectura se están utilizando?** 

**Patrones de Diseño:**
- **MVC (Model-View-Controller)**: JSF como framework MVC
- **DAO (Data Access Object)**: Separación de lógica de acceso a datos
- **Factory Pattern**: Para creación de DAOs
- **Singleton Pattern**: En `ServiciosSAGECI.getInstance()`
- **Template Method**: En clases abstractas de servicios

**Patrones Arquitectónicos:**
- **Layered Architecture**: Separación clara en 3 capas
- **Session Bean Pattern**: Managed beans con scope de sesión
- **Front Controller**: JSF Servlet actúa como controlador frontal

**Tácticas de Calidad:**
- **Manejo de Excepciones**: `ExcepcionServiciosSAGECI` personalizada
- **Logging**: Log4J para trazabilidad
- **Transacciones**: Control explícito de transacciones de base de datos

### 5. **¿Qué tecnologías y frameworks forman parte de la arquitectura?** 

**Frontend Technologies:**
- JSF 2.2.12 (JavaServer Faces)
- PrimeFaces 6.0 (Componentes UI ricos)
- XHTML/HTML5
- CSS3
- JavaScript (integrado con PrimeFaces)

**Backend Technologies:**
- Java EE 7 / Java 8
- Apache Shiro 1.2.1 (Seguridad)
- MyBatis 3.2.7 (Persistencia)
- iText PDF 5.0.6 (Generación de PDFs)

**Database & Persistence:**
- MySQL 5.1.36 (Producción)
- H2 1.4.191 (Testing)
- BoneCP 0.8.0 (Pool de conexiones)

**Build & Deployment:**
- Maven 3.x
- Apache Tomcat 7/8
- Webapp Runner (Heroku)

**Testing:**
- JUnit 4.12
- Selenium 2.44.0

**Email:**
- JavaMail API 1.4.7
- Mailtrap.io (Servicio de email)

### 6. **¿Cuáles son los principales módulos o capas en la aplicación?** 

**Estructura de Paquetes:**

```
edu.eci.pdsw.samples/
├── managedbeans/          # Capa de Presentación
│   ├── LogginBean
│   ├── UsuarioBean
│   ├── SolicitudAfiliacionBean
│   └── ...
├── services/              # Capa de Negocio
│   ├── ServiciosSAGECI
│   └── ServiciosSAGECIDAOS
├── persistence/           # Capa de Acceso a Datos
│   ├── DaoFactory
│   ├── mybatisimpl/
│   └── mappers/
├── entities/              # Modelo de Dominio
│   ├── Persona
│   ├── Estudiante
│   ├── Egresado
│   └── ...
├── Security/              # Seguridad
│   ├── SHA1
│   └── Registro
└── javamail/              # Comunicaciones
    └── core/
```

**Módulos Funcionales:**
- **Gestión de Usuarios**: Registro, autenticación, perfiles
- **Solicitudes de Afiliación**: Workflow de aprobación
- **Gestión de Pagos**: Procesamiento y tracking
- **Reportes**: Vencimientos y estadísticas
- **Generación de Certificados**: PDFs automáticos

### 7. **¿Existen dependencias entre los servicios o microservicios?** 

**Arquitectura Monolítica:**
- No es una arquitectura de microservicios, sino una aplicación monolítica
- Todas las funcionalidades están integradas en una sola aplicación WAR

**Dependencias Internas:**
- **Managed Beans → Servicios**: Los beans dependen de `ServiciosSAGECI`
- **Servicios → DAOs**: Los servicios utilizan múltiples DAOs
- **DAOs → MyBatis Mappers**: Acceso directo a mappers
- **Entidades**: Relaciones JPA/MyBatis entre entidades

**Dependencias Externas:**
- **Base de Datos MySQL**: Dependencia crítica
- **Servidor SMTP**: Para envío de emails
- **Apache Shiro Realm**: Para autenticación

### 8. **¿Cómo se gestionan la seguridad y la autenticación dentro de la aplicación?** 

**Framework de Seguridad: Apache Shiro**

**Configuración (`shiro.ini`):**
```ini
jdbcRealm = org.apache.shiro.realm.jdbc.JdbcRealm
authc.loginUrl = /Registro.xhtml
authc.successUrl = /index.xhtml
```

**Autenticación:**
- **Realm JDBC**: Consultas personalizadas a base de datos
- **Password Hashing**: SHA-256 con 500,000 iteraciones
- **Salt**: Generación automática de salt público + salt privado

**Autorización:**
- **Roles definidos**: Administrador, Egresado, Estudiante
- **Filtros de URL**: Protección basada en roles
- **Verificaciones programáticas**: `subject.hasRole()`

**Gestión de Sesiones:**
- **Session Timeout**: 30 minutos
- **Session Management**: Shiro gestiona sesiones
- **Logout**: Invalidación explícita de sesiones

**Queries de Seguridad:**
```sql
-- Autenticación
select rp.Clave from Persona pe 
left join Rol_Persona rp on pe.DocumentoID=rp.DocumentoID 
where pe.DocumentoID=?

-- Roles
select r.Nombre from Persona pe 
left join Rol_Persona rp on pe.DocumentoID=rp.DocumentoID 
left join Roles r on r.RolID=rp.Roles_rol 
where pe.DocumentoID=?
```

### 9. **¿Existen mecanismos de escalabilidad y balanceo de carga?** 

**Limitaciones de Escalabilidad:**
- **Arquitectura Monolítica**: Escalado vertical únicamente
- **Estado en Sesión**: Los Managed Beans con `@SessionScoped` limitan la escalabilidad horizontal
- **Sin Balanceador**: No hay configuración de load balancing
- **Base de Datos Única**: Single point of failure

**Oportunidades de Mejora:**
- Pool de conexiones BoneCP permite cierta concurrencia
- Configuración de Heroku permite escalado básico
- Estateless session beans mejorarían escalabilidad

### 10. **¿Cómo se manejan los errores y la resiliencia del sistema?** 

**Manejo de Excepciones:**
```java
// Excepción personalizada
ExcepcionServiciosSAGECI

// Pattern de manejo
try {
    daof.beginSession();
    // operación
    daof.commitTransaction();
} catch (PersistenceException ex) {
    daof.rollbackTransaction();
    throw new ExcepcionServiciosSAGECI(ex);
} finally {
    daof.endSession();
}
```

**Logging y Monitoreo:**
- **Log4J**: Configurado para trazabilidad
- **Logging Levels**: Error, Info, Debug
- **Exception Tracking**: Registro automático de excepciones

**Resiliencia Limitada:**
- **Rollback de Transacciones**: Garantiza consistencia
- **Validaciones Frontend**: JSF validation
- **Manejo de Errores UI**: Mensajes `FacesMessage`

### 11. **¿Cómo se entienden las capas de la aplicación y cómo se manejan?**

**Arquitectura de 3 Capas Clásica:**

**Capa de Presentación:**
- **Responsabilidad**: UI, validación de entrada, navegación
- **Tecnologías**: JSF + PrimeFaces + XHTML
- **Managed Beans**: Actúan como controladores
- **Scope Management**: Session-scoped para mantener estado

**Capa de Lógica de Negocio:**
- **Responsabilidad**: Reglas de negocio, workflows, validaciones
- **Patrón**: Service Layer con interfaces abstractas
- **Transacciones**: Gestión manual de transacciones
- **Singleton Services**: Acceso centralizado

**Capa de Acceso a Datos:**
- **Responsabilidad**: CRUD operations, mapeo O/R
- **Patrón DAO**: Abstracción de persistencia
- **MyBatis**: ORM ligero con mappers XML
- **Factory Pattern**: Creación centralizada de DAOs

### 12. **¿Cómo me puedo comunicar con esta aplicación?: API? mecanismo de comunicación. Si es una API generar el código para entender cuáles son los endpoints**

**Mecanismo de Comunicación: Aplicación Web Tradicional**

Esta aplicación **NO expone una API REST**. Es una aplicación web tradicional que utiliza:

**Interfaz de Usuario Web:**
- **Protocolo**: HTTP/HTTPS
- **Método**: Navegación por páginas XHTML
- **Autenticación**: Formularios web con sesiones

**Endpoints de Navegación (URLs):**

```markdown
## Endpoints Principales de la Aplicación

### Públicos (Sin autenticación)
- `GET /` - Página raíz con enlaces principales
- `GET /Bienvenida.xhtml` - Página de bienvenida e información
- `GET /Registro.xhtml` - Formulario de login
- `POST /Registro.xhtml` - Procesamiento de login

### Área de Estudiantes (Rol: Estudiante)
- `GET /Estudiante/index.xhtml` - Dashboard de estudiante
- `GET /Estudiante/Opciones.xhtml` - Opciones disponibles
- `GET /open/SolicitudAfiliacionEst.xhtml` - Solicitud de afiliación

### Área de Egresados (Rol: Egresado)  
- `GET /Egresado/index.xhtml` - Dashboard de egresado
- `GET /Egresado/Opciones.xhtml` - Gestión de perfil y pagos
- `GET /open/SolicitudAfiliacionEgre.xhtml` - Solicitud de afiliación

### Área de Administración (Rol: Administrador)
- `GET /Admin/index.xhtml` - Panel de administración
- `GET /Admin/Opciones.xhtml` - Configuración de administrador
- `GET /Admin/ProcesarSolicitudesAfiliacion.xhtml` - Gestión de solicitudes
- `GET /Admin/ProcesarPagosAfiliacion.xhtml` - Gestión de pagos
- `GET /Admin/SolicitudesPorVencer.xhtml` - Reportes de vencimiento
```

**Acciones JSF (Procesamiento Backend):**

```java
// Ejemplos de métodos que se invocan desde la UI

// Autenticación
LogginBean.doLogin() -> POST /Registro.xhtml

// Gestión de Solicitudes  
SolicitudAfiliacionBean.enviarSolicitud() -> Procesa formulario
ProcesarSolicitudAfiliacionBean.aceptarSolicitudAfiliacion()
ProcesarSolicitudAfiliacionBean.rechazarSolicitudAfiliacion()

// Gestión de Pagos
PagoAfiliacionBean.aceptarEnvio() -> Subida de comprobante
ProcesarPagosAfiliacionBean.aceptarPagoAfiliacion()
ProcesarPagosAfiliacionBean.rechazarPagoAfiliacion()

// Generación de Certificados
UsuarioBean.getStreamedContent() -> Descarga PDF

// Reportes
reporteVencerseBean.recordatorio() -> Envío de emails
```

**Si fuera necesario crear una API REST, los endpoints serían:**

```java
// Ejemplo conceptual de API REST (NO IMPLEMENTADA)

@RestController
@RequestMapping("/api/v1")
public class SGAECIRestController {
    
    // Autenticación
    @POST("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    
    // Solicitudes
    @GET("/solicitudes")
    public ResponseEntity<List<SolicitudAfiliacion>> getSolicitudes()
    
    @POST("/solicitudes") 
    public ResponseEntity<SolicitudAfiliacion> crearSolicitud(@RequestBody SolicitudRequest request)
    
    @PUT("/solicitudes/{id}/aprobar")
    public ResponseEntity<Void> aprobarSolicitud(@PathVariable int id)
    
    @PUT("/solicitudes/{id}/rechazar") 
    public ResponseEntity<Void> rechazarSolicitud(@PathVariable int id)
    
    // Pagos
    @GET("/pagos")
    public ResponseEntity<List<PagoAfiliacion>> getPagos()
    
    @POST("/pagos")
    public ResponseEntity<PagoAfiliacion> registrarPago(@RequestBody PagoRequest request)
    
    // Usuarios
    @GET("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id)
    
    @PUT("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioRequest request)
    
    // Certificados
    @GET("/certificados/{usuarioId}")
    public ResponseEntity<byte[]> generarCertificado(@PathVariable int usuarioId)
    
    // Reportes
    @GET("/reportes/vencimientos")
    public ResponseEntity<List<ReporteVencimiento>> getReporteVencimientos()
}
```

**Comunicación Actual:**
La aplicación actual solo acepta comunicación a través de:
1. **Navegador Web**: Interacción humana con formularios HTML/JSF
2. **Formularios HTTP**: POST/GET tradicionales
3. **Sesiones Web**: Mantenimiento de estado en servidor
4. **AJAX**: Componentes PrimeFaces con actualizaciones parciales

Para integración con otros sistemas sería necesario desarrollar una capa de API REST sobre la lógica de negocio existente.

---

## Conclusiones

SGAECI es una aplicación web monolítica tradicional bien estructurada que utiliza tecnologías Java EE estándar. Su arquitectura de 3 capas proporciona separación clara de responsabilidades, aunque presenta limitaciones típicas de aplicaciones monolíticas en términos de escalabilidad y modernización. La aplicación maneja adecuadamente la seguridad y tiene una base sólida que podría servir como punto de partida para una eventual migración a arquitecturas más modernas.
