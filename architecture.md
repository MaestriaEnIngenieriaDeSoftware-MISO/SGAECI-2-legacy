# Análisis de Arquitectura de la Aplicación SGAECI-2

## Preguntas clave

### 1. **¿Cuáles son los componentes funcionales de la aplicación y cómo se relacionan entre sí?**

La aplicación SGAECI-2 (Sistema de Gestión de la Asociación de Egresados de la Escuela Colombiana de Ingeniería) está estructurada en capas siguiendo una arquitectura de aplicación web Java EE tradicional:

**Componentes principales:**
- **Capa de Presentación (JSF)**: Páginas XHTML con PrimeFaces para la interfaz de usuario
- **Capa de Controlador (Managed Beans)**: Beans JSF que manejan la lógica de presentación
- **Capa de Servicios**: Servicios de negocio centralizados (`ServiciosSAGECI`)
- **Capa de Persistencia**: DAOs y mappers de MyBatis para acceso a datos
- **Capa de Entidades**: POJOs que representan el modelo de dominio

**Relaciones entre componentes:**
- Las páginas JSF se conectan a los Managed Beans
- Los Managed Beans utilizan los servicios de negocio
- Los servicios utilizan el patrón Factory para obtener DAOs
- Los DAOs interactúan con la base de datos a través de MyBatis

### 2. **¿Cómo es el despliegue de los componentes en el entorno productivo?**

**Estrategia de despliegue:**
- **Empaquetado**: Aplicación WAR (`PDSW.war`) generada por Maven
- **Servidor de aplicaciones**: Tomcat (usando webapp-runner para Heroku)
- **Integración continua**: CircleCI configurado para despliegue automático
- **Plataforma de producción**: Heroku (según `circle.yml` y `Procfile`)
- **Base de datos**: MySQL en producción, H2 para pruebas locales
- **Contenedorización**: Docker Compose para desarrollo local

**Configuración de despliegue:**
```yaml
# Heroku deployment via Procfile
web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
```

### 3. **¿Cómo interactúan los componentes con las fuentes de datos?**

**Arquitectura de acceso a datos:**
- **ORM/Mapper**: MyBatis como framework de mapeo objeto-relacional
- **Patrón Factory**: `DaoFactory` para abstracción de implementaciones DAO
- **Pool de conexiones**: BoneCP para gestión eficiente de conexiones
- **Múltiples fuentes de datos**: 
  - MySQL para producción
  - H2 para pruebas y desarrollo local

**Configuración de base de datos:**
```xml
<!-- MyBatis Configuration -->
<dataSource type="POOLED">
    <property name="driver" value="com.mysql.jdbc.Driver" />
    <property name="url" value="jdbc:mysql://db:3306/pdswgr2" />
    <property name="username" value="pdswgr2" />
    <property name="password" value="pdswg202" />
</dataSource>
```

### 4. **¿Qué patrones y tácticas de arquitectura se están utilizando?**

**Patrones arquitectónicos implementados:**
- **MVC (Model-View-Controller)**: Separación clara entre presentación, lógica y datos
- **Factory Pattern**: `DaoFactory` para creación de objetos DAO
- **Singleton Pattern**: Instancia única de servicios (`ServiciosSAGECI.getInstance()`)
- **Data Access Object (DAO)**: Abstracción de acceso a datos
- **Dependency Injection**: A través de JSF Managed Beans
- **Layered Architecture**: Separación en capas bien definidas

**Tácticas de arquitectura:**
- **Separación de responsabilidades**: Cada capa tiene una responsabilidad específica
- **Abstracción**: Interfaces para servicios y DAOs
- **Configuration externalization**: Propiedades externalizadas en archivos de configuración

### 5. **¿Qué tecnologías y frameworks forman parte de la arquitectura?**

**Stack tecnológico:**
- **Backend Framework**: Java EE 7
- **Web Framework**: JSF 2.2 con PrimeFaces 6.0
- **ORM**: MyBatis 3.2.7
- **Security**: Apache Shiro 1.2.1
- **Database**: MySQL 5.7 (producción), H2 (desarrollo)
- **Connection Pool**: BoneCP 0.8.0
- **Build Tool**: Maven 3.x
- **Application Server**: Tomcat 7
- **Template Engine**: Facelets (XHTML)
- **Logging**: Log4j 1.2.17 + SLF4J
- **Testing**: JUnit 4.12, Selenium 2.44.0
- **Email**: JavaMail API 1.4.7
- **PDF Generation**: iText 5.0.6

### 6. **¿Cuáles son los principales módulos o capas en la aplicación?**

**Estructura de capas:**

1. **Capa de Presentación** (`src/main/webapp/`)
   - Páginas XHTML con componentes JSF/PrimeFaces
   - Recursos estáticos (CSS, imágenes)
   - Configuración web (web.xml)

2. **Capa de Controlador** (`managedbeans/`)
   - `LogginBean`: Autenticación y autorización
   - `UsuarioBean`: Gestión de usuarios
   - `SolicitudAfiliacionBean`: Solicitudes de afiliación
   - `PagoAfiliacionBean`: Gestión de pagos
   - `ProcesarPagosAfiliacionBean`: Procesamiento de pagos

3. **Capa de Servicios** (`services/`)
   - `ServiciosSAGECI`: Servicio principal de negocio
   - `ServiciosSAGECIDAOS`: Implementación con acceso a datos

4. **Capa de Persistencia** (`persistence/`)
   - `DaoFactory`: Factory para DAOs
   - DAOs específicos por entidad (DaoEstudiante, DaoEgresado, etc.)
   - Mappers MyBatis

5. **Capa de Entidades** (`entities/`)
   - `Persona`, `Estudiante`, `Egresado`
   - `SolicitudAfiliacion`, `PagoAfiliacion`
   - `Rol`, `estadoAfiliacion`

### 7. **¿Existen dependencias entre los servicios o microservicios?**

**Arquitectura monolítica:**
- La aplicación es un monolito, no utiliza microservicios
- Un solo servicio principal (`ServiciosSAGECI`) maneja toda la lógica de negocio
- Las dependencias son internas entre capas:
  - Managed Beans → Servicios
  - Servicios → DAOs
  - DAOs → Base de datos

**Dependencias identificadas:**
- Acoplamiento fuerte entre capas adyacentes
- Dependencia directa de la implementación de servicios
- Sin separación de dominios o contextos acotados

### 8. **¿Cómo se gestionan la seguridad y la autenticación dentro de la aplicación?**

**Framework de seguridad: Apache Shiro**

**Mecanismos de seguridad implementados:**
- **Autenticación**: Basada en base de datos con JdbcRealm
- **Autorización**: Control de acceso basado en roles (RBAC)
- **Hash de contraseñas**: SHA-256 con salt y 500,000 iteraciones
- **Gestión de sesiones**: Sesiones web con cache en memoria

**Configuración de seguridad:**
```ini
# Roles y permisos
/Admin/** = authc, roles[Administrador]
/Estudiante/** = authc, roles[Estudiante]
/Egresado/** = authc, roles[Egresado]
```

**Roles del sistema:**
- Administrador: Acceso completo al sistema
- Estudiante: Funcionalidades específicas para estudiantes
- Egresado: Funcionalidades específicas para egresados

### 9. **¿Existen mecanismos de escalabilidad y balanceo de carga?**

**Limitaciones de escalabilidad:**
- **Arquitectura monolítica**: Escalabilidad limitada
- **Estado en sesión**: Los Managed Beans mantienen estado en sesión
- **Sin balanceador de carga**: No hay evidencia de configuración de balanceeo
- **Pool de conexiones**: BoneCP proporciona cierta escalabilidad de base de datos

**Consideraciones:**
- La aplicación está diseñada para despliegue single-node
- La escalabilidad horizontal requeriría refactoring significativo
- El uso de sesiones JSF limita la escalabilidad stateless

### 10. **¿Cómo se manejan los errores y la resiliencia del sistema?**

**Manejo de errores:**
- **Excepciones personalizadas**: `ExcepcionServiciosSAGECI`
- **Logging**: Log4j para registro de errores y eventos
- **Mensajes de usuario**: FacesMessage para feedback al usuario
- **Rollback de transacciones**: Manejo de transacciones en capa de persistencia

**Limitaciones de resiliencia:**
- Sin circuit breakers o retry policies
- Manejo de errores básico sin estrategias de recuperación
- Sin monitoreo proactivo de salud del sistema

### 11. **¿Cómo se entienden las capas de la aplicación y cómo se manejan?**

**Arquitectura en capas bien definida:**

```
┌─────────────────────────────────────┐
│        Capa de Presentación         │
│    (JSF Pages + PrimeFaces)        │
└─────────────────────────────────────┘
                    │
┌─────────────────────────────────────┐
│       Capa de Controlador          │
│       (Managed Beans)              │
└─────────────────────────────────────┘
                    │
┌─────────────────────────────────────┐
│        Capa de Servicios           │
│      (ServiciosSAGECI)             │
└─────────────────────────────────────┘
                    │
┌─────────────────────────────────────┐
│      Capa de Persistencia          │
│     (DAOs + MyBatis)               │
└─────────────────────────────────────┘
                    │
┌─────────────────────────────────────┐
│        Base de Datos               │
│       (MySQL/H2)                   │
└─────────────────────────────────────┘
```

**Principios de diseño:**
- **Separación de responsabilidades**: Cada capa tiene una función específica
- **Abstracción**: Interfaces definen contratos entre capas
- **Inversión de dependencias**: Uso de Factory Pattern para inyección

### 12. **¿Cómo me puedo comunicar con esta aplicación?: API? mecanismo de comunicación**

**Mecanismo de comunicación: Aplicación Web Tradicional**

Esta aplicación **NO expone APIs REST** ni servicios web. Es una aplicación web tradicional que utiliza:

**Interfaz de usuario web:**
- **Protocolo**: HTTP/HTTPS
- **Tecnología**: JSF con PrimeFaces
- **Páginas principales**:
  - `/Bienvenida.xhtml` - Página de inicio
  - `/Registro.xhtml` - Autenticación
  - `/Admin/` - Panel administrativo
  - `/Estudiante/` - Portal de estudiantes
  - `/Egresado/` - Portal de egresados

**Endpoints de navegación (No APIs):**
```
GET /Bienvenida.xhtml          # Página de bienvenida
GET /Registro.xhtml            # Página de login
GET /Admin/index.xhtml         # Dashboard admin
GET /Estudiante/index.xhtml    # Dashboard estudiante
GET /Egresado/index.xhtml      # Dashboard egresado
```

**Comunicación:**
- **Frontend**: Navegador web interactuando con páginas JSF
- **Backend**: Procesamiento server-side con Managed Beans
- **Sin APIs REST**: La aplicación no expone servicios web
- **Sin endpoints JSON**: Todo el intercambio es HTML/XHTML

**Para crear APIs REST se necesitaría:**
```java
// Ejemplo de lo que se podría agregar:
@RestController
@RequestMapping("/api/v1")
public class SolicitudAfiliacionController {
    
    @GetMapping("/solicitudes")
    public ResponseEntity<List<SolicitudAfiliacion>> getSolicitudes() {
        // Implementación
    }
    
    @PostMapping("/solicitudes")
    public ResponseEntity<SolicitudAfiliacion> crearSolicitud(@RequestBody SolicitudAfiliacion solicitud) {
        // Implementación
    }
}
```

**Conclusión:**
La aplicación SGAECI-2 es una aplicación web tradicional JSF que no expone APIs. La comunicación se realiza exclusivamente a través de la interfaz web del navegador. Para integración con otros sistemas o desarrollo de aplicaciones móviles, sería necesario agregar una capa de servicios REST.

## Resumen de la Arquitectura

SGAECI-2 es una aplicación web monolítica desarrollada en Java EE que utiliza:
- **JSF + PrimeFaces** para la interfaz de usuario
- **Apache Shiro** para seguridad
- **MyBatis** para persistencia
- **MySQL** como base de datos principal
- **Arquitectura en capas** bien definida
- **Despliegue en Heroku** con integración continua

La aplicación está diseñada para gestionar la asociación de egresados de la ECI, manejando estudiantes, egresados, solicitudes de afiliación y pagos a través de una interfaz web tradicional.
