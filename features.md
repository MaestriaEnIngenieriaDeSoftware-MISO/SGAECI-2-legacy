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

## DETALLE POR ROL

### 🔐 **ADMINISTRADOR**

| **FUNCIONALIDAD** | **DESCRIPCIÓN** | **ARCHIVO VISTA** | **BEAN** | **CARACTERÍSTICAS** |
|-------------------|-----------------|-------------------|----------|---------------------|
| **Procesar solicitudes** | Aceptar/rechazar solicitudes de afiliación | `ProcesarSolicitudesAfiliacion.xhtml` | `ProcesarSolicitudAfiliacionBean` | - Lista de solicitudes pendientes<br>- Formulario de revisión<br>- Envío de correos automático |
| **Procesar pagos** | Revisar y aprobar pagos de afiliación | `ProcesarPagosAfiliacion.xhtml` | `ProcesarPagosAfiliacionBean` | - Visualización de comprobantes<br>- Estados: registrados/no tramitados<br>- Procesamiento con notificaciones |
| **Reportes vencimiento** | Ver afiliados próximos a vencer | `SolicitudesPorVencer.xhtml` | `reporteVencerseBean` | - Lista de afiliados (30 días)<br>- Envío masivo de recordatorios |
| **Gestión perfil** | Administrar cuenta personal | `Opciones.xhtml` | `UsuarioBean` | - Cambio de contraseña<br>- Datos personales |

### 👨‍🎓 **EGRESADO**

| **FUNCIONALIDAD** | **DESCRIPCIÓN** | **ARCHIVO VISTA** | **BEAN** | **CARACTERÍSTICAS** |
|-------------------|-----------------|-------------------|----------|---------------------|
| **Solicitud afiliación** | Formulario de solicitud específico | `SolicitudAfiliacionEgre.xhtml` | `SolicitudAfiliacionBean` | - Wizard multi-paso<br>- Datos personales y empresariales<br>- Validaciones requeridas |
| **Registro pagos** | Subir comprobantes de pago | `index.xhtml` (Tab "Adjuntar Recibo") | `GenerarPago` Bean | - Upload de archivos<br>- Selección de fecha<br>- Dialog modal integrado |
| **Certificados** | Generar certificados de afiliación | `index.xhtml` (Tab "Generar Certificado") | `UsuarioBean` | - Generación PDF con iText<br>- Visualización en modal<br>- Validación estado afiliación<br>- Descarga integrada |
| **Gestión perfil** | Administrar datos personales | `Opciones.xhtml` | `UsuarioBean` | - Actualización de datos<br>- Cambio de contraseña |

### 👨‍🎓 **ESTUDIANTE**

| **FUNCIONALIDAD** | **DESCRIPCIÓN** | **ARCHIVO VISTA** | **BEAN** | **CARACTERÍSTICAS** |
|-------------------|-----------------|-------------------|----------|---------------------|
| **Solicitud afiliación** | Formulario de solicitud específico | `SolicitudAfiliacionEst.xhtml` | `SolicitudAfiliacionBean` | - Datos académicos<br>- Código estudiantil<br>- Semestre y carrera |
| **Registro pagos** | ❌ NO DISPONIBLE | - | - | - **Afiliación gratuita** por 6 meses<br>- No requieren pagos como estudiantes activos |
| **Certificados** | Generar certificados de afiliación | `index.xhtml` (Tab "Generar Certificado") | `UsuarioBean` | - Generación PDF con iText<br>- Visualización en modal<br>- Validación estado afiliación<br>- Descarga integrada |
| **Gestión perfil** | Administrar datos personales | `Opciones.xhtml` | `UsuarioBean` | - Actualización de datos<br>- Cambio de contraseña |

---

## DETALLE TÉCNICO: VISTAS PRINCIPALES DE USUARIO

### 📋 **ANÁLISIS DE ARCHIVOS `index.xhtml`**

#### 🎓 **EGRESADO (`/Egresado/index.xhtml`)**

| **SECCIÓN** | **FUNCIONALIDAD** | **IMPLEMENTACIÓN** |
|-------------|-------------------|-------------------|
| **Header** | Dashboard personal | - Saludo personalizado<br>- Estado de afiliación<br>- Menú de navegación |
| **Tab 1** | "Adjuntar Recibo de Pago" | - `p:fileUpload` con `#{GenerarPago.handleFileUpload}`<br>- `p:calendar` para fecha<br>- Dialog modal para proceso completo |
| **Tab 2** | "Generar Certificado" | - `p:media` para visualizar PDF<br>- `#{Usuario.streamedContent}` como fuente<br>- Dialog modal 850x1000px |

#### 🎓 **ESTUDIANTE (`/Estudiante/index.xhtml`)**

| **SECCIÓN** | **FUNCIONALIDAD** | **IMPLEMENTACIÓN** |
|-------------|-------------------|-------------------|
| **Header** | Dashboard personal | - Idéntico al egresado<br>- Saludo personalizado<br>- Estado de afiliación |
| **Tab único** | "Generar Certificado" | - Solo tiene funcionalidad de certificados<br>- **NO tiene tab de pagos** (afiliación gratuita)<br>- Misma implementación PDF que egresado |

### 🔍 **DIFERENCIAS CLAVE ENCONTRADAS:**

1. **EGRESADOS:** Tienen **2 tabs** (Pagos + Certificados)
2. **ESTUDIANTES:** Tienen **1 tab** (Solo Certificados)
3. **Razón:** Los estudiantes tienen **afiliación gratuita por 6 meses** según el certificado generado

### ⚠️ **IMPORTANTE - LÓGICA DE NEGOCIO:**

**Los estudiantes NO tienen funcionalidad de registro de pagos porque:**
- Su afiliación es **gratuita por 6 meses**
- Solo necesitan generar certificados
- No requieren renovaciones mientras sean estudiantes activos

### 🎯 **BEANS UTILIZADOS:**

| **FUNCIONALIDAD** | **BEAN** | **MÉTODO/PROPIEDAD** |
|-------------------|----------|---------------------|
| **Datos personales** | `Usuario` (UsuarioBean) | `#{Usuario.p.nombre}`, `#{Usuario.p.apellido}` |
| **Estado afiliación** | `Usuario` (UsuarioBean) | `#{Usuario.eaf.estado}` |
| **Upload pagos** | `GenerarPago` | `#{GenerarPago.handleFileUpload}`, `#{GenerarPago.fechaConsignacion}` |
| **Certificados PDF** | `Usuario` (UsuarioBean) | `#{Usuario.streamedContent}` |

### 📱 **CARACTERÍSTICAS UX/UI:**

- **Diseño responsive** con CSS personalizado
- **Dialogs modales** para todas las funcionalidades
- **Navegación integrada** con menú contextual
- **Validaciones en tiempo real**
- **Feedback visual** con mensajes de estado

---

## DETALLE TÉCNICO: GENERACIÓN DE CERTIFICADOS

### 📋 **IMPLEMENTACIÓN ENCONTRADA EN `UsuarioBean`**

| **ASPECTO** | **DETALLE** | **IMPLEMENTACIÓN** |
|-------------|-------------|-------------------|
| **Librería PDF** | iText PDF | `com.itextpdf.text.*` |
| **Método principal** | `getStreamedContent()` | Genera y retorna el PDF como stream |
| **Validación** | Estado de afiliación | Solo genera si estado = "ACTIVO" |
| **Plantillas** | Diferenciadas por rol | `plantillaEst` para estudiantes, `plantillaEgr` para egresados |
| **Personalización** | Datos dinámicos | Reemplaza variables con datos reales del usuario |
| **Elementos incluidos** | - Logo AECI<br>- Datos personales<br>- Fechas de afiliación<br>- Firma digital del director<br>- Información de contacto |

### 🔧 **CARACTERÍSTICAS TÉCNICAS**

- **Formato:** PDF generado dinámicamente
- **Tamaño:** A4 (PageSize.A4)
- **Imágenes:** Logo y firma desde URLs externas
- **Validación:** Verifica estado "ACTIVO" antes de generar
- **Descarga:** Automática como "Certificado.pdf"
- **Plantilla Estudiante:** Incluye semestre actual y carrera
- **Plantilla Egresado:** Incluye período de graduación

### 📝 **CONTENIDO DEL CERTIFICADO**

1. **Encabezado:** Logo AECI + "CERTIFICADO DE AFILIACION AECI"
2. **Introducción:** Identificación de la asociación (NIT: 830.031.137-4)
3. **Cuerpo principal:** Plantilla personalizada según rol
4. **Condiciones:** Información sobre vigencia y convenios
5. **Firma:** Juan Carlos Romero Ordóñez - Director
6. **Contacto:** Dirección, teléfonos y correo institucional

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

## RESUMEN DE IMPLEMENTACIÓN

| **ESTADO** | **CANTIDAD** | **FUNCIONALIDADES** |
|------------|--------------|---------------------|
| ✅ **IMPLEMENTADO** | 8 | Inicio sesión, Administración perfil, Solicitudes, Procesamiento solicitudes, Registro pagos (solo egresados), Procesamiento pagos, Reportes vencimiento, Generación certificados |
| ⚠️ **PENDIENTE** | 0 | - |
| 📊 **TOTAL** | 8 | **100% Completitud** |

**Nota:** El registro de pagos para estudiantes NO es una funcionalidad faltante, sino una **decisión de negocio correcta** ya que tienen afiliación gratuita.

---
