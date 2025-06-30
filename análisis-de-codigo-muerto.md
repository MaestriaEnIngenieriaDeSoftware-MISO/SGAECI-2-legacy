# Análisis Detallado de Código Muerto en SGAECI-2-legacy

## Metodología
Se realizó una inspección exhaustiva de todos los archivos y carpetas, incluyendo recursos estáticos (imágenes), archivos `.xhtml`, `.css` y referencias cruzadas. Se verificó el uso de cada recurso en los archivos de la aplicación.

## Resultados

### Archivos `.xhtml` y rutas
- Todos los archivos y carpetas enlazados desde `index.xhtml` (`Admin/`, `Egresado/`, `Estudiante/`, `open/`) existen y contienen archivos de navegación, por lo que **no se detecta código muerto en las rutas principales**.
- Los archivos dentro de cada subcarpeta (`Opciones.xhtml`, `index.xhtml`, etc.) corresponden a rutas accesibles desde la navegación principal o secundaria.

### Recursos estáticos (imágenes en `resource/`)
- **Imágenes utilizadas:**
    - `EscIng_2.jpg`, `Registro.jpg`, `acceso-denegado.jpg`, `aecimagen.png`, `candado.png`, `certificado.png`, `contactenos.PNG`, `informacion.png`, `junta.PNG`, `pago.jpg`, `procesarsolicitud.png`, `solicitudpendiente.png`.
    - `escuela.jpg` se usa como fondo en `sheet.css` y este CSS es referenciado en múltiples archivos `.xhtml`.
- **Imágenes potencialmente sin uso (no referenciadas en `.xhtml` ni `.css`):**
    - `AECI.PNG`, `nature1.jpg`, `nature10.png`, `nature11.png`, `nature12.png`, `nature13.png`, `nature14.png`, `nature2.jpg`, `nature3.jpg`, `nature4.jpg`, `nature5.jpg`, `nature6.jpg`, `nature7.jpg`, `nature8.jpg`, `nature9.png`.

### Archivos de configuración y otros
- Archivos como `web.xml`, `sheet.css`, y `unauthorized.xhtml` son típicos en aplicaciones JSF y pueden ser requeridos por el framework o para manejo de errores.

### Conclusión
- **No se detecta código muerto en la navegación principal ni en los recursos críticos.**
- **Imágenes potencialmente sin uso:**
    - `AECI.PNG`
    - `nature1.jpg`, `nature10.png`, `nature11.png`, `nature12.png`, `nature13.png`, `nature14.png`, `nature2.jpg`, `nature3.jpg`, `nature4.jpg`, `nature5.jpg`, `nature6.jpg`, `nature7.jpg`, `nature8.jpg`, `nature9.png`

Se recomienda revisar manualmente si estas imágenes son necesarias o pueden eliminarse.

---
_Análisis realizado el 30 de junio de 2025._
