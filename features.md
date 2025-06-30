# FUNCIONALIDADES SGAECI POR ROL

## TABLA RESUMEN DE FUNCIONALIDADES

| **FUNCIONALIDAD** | **ADMINISTRADOR** | **EGRESADO** | **ESTUDIANTE** | **ARCHIVO PRINCIPAL** | **BEAN ASOCIADO** | **ESTADO** |
|-------------------|-------------------|--------------|----------------|----------------------|-------------------|------------|
| **Inicio de sesión** | ✅ | ✅ | ✅ | `/Registro.xhtml` | `LogginBean` | ✅ IMPLEMENTADO |
| **Administración de perfil** | ✅ | ✅ | ✅ | `/[Rol]/Opciones.xhtml` | `UsuarioBean` | ✅ IMPLEMENTADO |
| **Solicitud de afiliación** | ❌ | ✅ | ✅ | `/open/SolicitudAfiliacion[Egre/Est].xhtml` | `SolicitudAfiliacionBean` | ✅ IMPLEMENTADO |
| **Procesar solicitudes de afiliación** | ✅ | ❌ | ❌ | `/Admin/ProcesarSolicitudesAfiliacion.xhtml` | `ProcesarSolicitudAfiliacionBean` | ✅ IMPLEMENTADO |
| **Registrar pago afiliación/renovación** | ❌ | ✅ | ❌ | `/Egresado/index.xhtml` (Tab "Adjuntar Recibo") | `GenerarPago` Bean | ✅ IMPLEMENTADO |
| **Procesar pagos afiliaciones/renovaciones** | ✅ | ❌ | ❌ | `/Admin/ProcesarPagosAfiliacion.xhtml` | `ProcesarPagosAfiliacionBean` | ✅ IMPLEMENTADO |
| **Generación de certificados de afiliación** | ❌ | ✅ | ✅ | `/[Rol]/index.xhtml` (Tab "Generar Certificado") | `UsuarioBean` | ✅ IMPLEMENTADO |
| **Reporte de afiliaciones por vencerse** | ✅ | ❌ | ❌ | `/Admin/SolicitudesPorVencer.xhtml` | `reporteVencerseBean` | ✅ IMPLEMENTADO |

---

## FUNCIONALIDADES TRANSVERSALES

| **COMPONENTE** | **DESCRIPCIÓN** | **ARCHIVOS** | **ESTADO** |
|----------------|-----------------|--------------|------------|
| **Autenticación** | Sistema de login con Apache Shiro | `shiro.ini`, `LogginBean` | ✅ IMPLEMENTADO |
| **Autorización** | Control de acceso por roles | `web.xml`, configuración Shiro | ✅ IMPLEMENTADO |
| **Notificaciones** | Sistema de correos electrónicos | `EmailSender`, beans diversos | ✅ IMPLEMENTADO |
| **Persistencia** | Acceso a datos con MyBatis | Mappers XML, DAOs | ✅ IMPLEMENTADO |
| **Validaciones** | Validaciones de formularios | JSF validators, beans | ✅ PARCIAL |

---
# FUNCIONALIDADES PRIORITARIAS PARA MODERNIZACIÓN

## TABLA DE FUNCIONALIDADES A MODERNIZAR

| **ID** | **FUNCIONALIDAD** | **ROL** | **PRIORIDAD** | **DESCRIPCIÓN** | **CRITERIOS DE ACEPTACIÓN** |
|--------|-------------------|---------|---------------|-----------------|----------------------------|
| **F001** | Inicio de sesión | Admin/Egresado/Estudiante | 🔴 **CRÍTICA** | Sistema de autenticación moderno con validaciones robustas y UX mejorada | - Login con documento<br>- Validación en tiempo real<br>- Recuperación de contraseña<br>- Autenticación segura (JWT/OAuth)<br>- Interfaz responsive<br>- Manejo de errores amigable |
| **F002** | Solicitud de registro | Egresado/Estudiante | 🔴 **CRÍTICA** | Formulario de registro modernizado con flujo intuitivo y validaciones | - Formulario paso a paso (wizard)<br>- Validaciones en tiempo real<br>- Progreso visual del proceso<br>- Confirmación por email<br>|
| **F003** | Aprobación de solicitudes | Administrador | 🔴 **CRÍTICA** | Dashboard administrativo para gestión eficiente de solicitudes | - Vista detallada de solicitudes<br>- Aprobación/rechazo con un clic<br>- Comentarios y razones<br>- Notificaciones automáticas<br>|
| **F004** | Generación de certificados | Egresado/Estudiante | 🔴 **CRÍTICA** | Sistema modernizado de generación y descarga de certificados PDF | - Generación instantánea<br>- Diseño actualizado del certificado<br>- Descarga directa o envío por email<br>- Validación de estado activo<br>- Personalización por rol<br>|
| **F005** | Verificación de comprobantes | Administrador | 🟡 **OPCIONAL** | Sistema mejorado para revisar y aprobar pagos con visualización optimizada | - Visor de documentos integrado<br>- Estados claros de proceso<br>- Comentarios de rechazo<br>- Notificaciones automáticas |
| **F006** | Cargue de pagos | Egresado | 🟡 **OPCIONAL** | Interfaz moderna para subir comprobantes de pago con mejor UX | - Vista previa antes de envío<br>- Múltiples formatos aceptados<br>- Progreso de subida<br>- Validación de formato/tamaño<br>- Confirmación de recepción |

---

## JUSTIFICACIÓN DE SELECCIÓN DE FUNCIONALIDADES

### 🎯 **ALINEACIÓN CON EL CORE DEL NEGOCIO AECI**

Estas funcionalidades fueron seleccionadas porque representan el **núcleo operativo** de la Asociación de Egresados de la Escuela Colombiana de Ingeniería Julio Garavito (AECI). Según la visión del producto, el software debe gestionar eficientemente los procesos críticos desde las diferentes vistas (Administrador, Egresado, Estudiante).

#### **🔴 FUNCIONALIDADES CRÍTICAS (Obligatorias)**

**1. F001 - Inicio de sesión (Todos los roles)**
- **Relevancia del negocio:** Es la puerta de entrada al ecosistema AECI que garantiza la seguridad de la información
- **Impacto en la visión:** Sin autenticación segura, no se puede garantizar que "estar seguros de su información por el sistema de seguridad"
- **Justificación:** El sistema actual (JSF + Shiro) requiere modernización para cumplir estándares de seguridad actuales
- **Beneficio:** Fortalece la confianza de afiliados y administradores en la plataforma

**2. F002 - Solicitud de registro (Egresado/Estudiante)**
- **Relevancia del negocio:** Proceso fundamental para **incorporar nuevos miembros** a la asociación
- **Impacto en la visión:** Facilita que "personas que desean afiliarse conozcan el proceso necesario para pertenecer"
- **Justificación:** Primera impresión de AECI - un proceso confuso reduce conversiones y afecta crecimiento
- **Beneficio:** Mayor captación de afiliados, mejor experiencia inicial, datos más precisos

**3. F003 - Aprobación de solicitudes (Administrador)**
- **Relevancia del negocio:** **Proceso core administrativo** que determina quién pertenece a la asociación
- **Impacto en la visión:** Cumple el rol crítico del administrador de "aceptar o rechazar solicitudes que ingresan al sistema"
- **Justificación:** Eficiencia administrativa directamente impacta tiempo de respuesta a solicitantes
- **Beneficio:** Reducción de tiempos de procesamiento, mejor control de calidad, trazabilidad completa

**4. F004 - Generación de certificados (Egresado/Estudiante)**
- **Relevancia del negocio:** **Servicio principal** que AECI ofrece a sus afiliados - evidencia su pertenencia
- **Impacto en la visión:** Materializa el beneficio de "generar certificados de afiliación" para afiliados activos
- **Justificación:** Certificado representa valor tangible de la membresía - debe reflejar profesionalismo de AECI
- **Beneficio:** Fortalecimiento de imagen institucional, mayor satisfacción de afiliados

#### **🟡 FUNCIONALIDADES OPCIONALES (Si tiempo permite)**

**5. F005 - Verificación de comprobantes (Administrador)**
- **Relevancia del negocio:** Garantiza **sostenibilidad financiera** de la asociación
- **Impacto en la visión:** Apoya al administrador a "revisar los pagos que hagan los afiliados para determinar si siguen en la asociación"
- **Justificación:** Proceso actual funcional, mejoras incrementarían eficiencia pero no son críticas
- **Beneficio:** Mayor precisión en validación de pagos, reducción de errores manuales

**6. F006 - Cargue de pagos (Egresado)**
- **Relevancia del negocio:** Facilita **renovación de membresías** de egresados (principal fuente de ingresos)
- **Impacto en la visión:** Permite a egresados "gestionar sus pagos o renovaciones de su estado de afiliación"
- **Justificación:** Funcionalidad actual operativa, mejoras serían de UX/comodidad
- **Beneficio:** Mayor comodidad para egresados, potencial incremento en renovaciones