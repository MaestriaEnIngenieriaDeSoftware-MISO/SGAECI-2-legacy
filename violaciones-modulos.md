# Análisis de Violaciones de Separación de Responsabilidades en SGAECI-2-legacy

## Evaluación general
La arquitectura sigue patrones tradicionales de Java EE/JSF, con separación entre entidades, DAOs, beans de presentación y servicios. Sin embargo, se identifican algunos puntos de mejora:

## Módulos que violan la separación de responsabilidades
- **Beans de presentación (ManagedBeans):**
  - Beans como `BienvenidaBean`, `ProcesarPagosAfiliacionBean`, `ProcesarSolicitudAfiliacionBean` y `reporteVencerseBean` contienen lógica de negocio (por ejemplo, envío de correos, manipulación directa de DAOs) que debería estar en la capa de servicios.
  - Se recomienda que los beans gestionen solo la interacción con la vista y deleguen la lógica a los servicios.
- **Servicios:**
  - La clase `ServiciosSAGECI` centraliza reglas de negocio, pero parte de la lógica se replica en los beans.
- **Pruebas:**
  - Las pruebas unitarias/integración mezclan lógica de negocio y preparación de datos, lo que puede dificultar su mantenimiento.

## Recomendaciones
- Refactorizar los ManagedBeans para delegar toda la lógica de negocio a los servicios.
- Mantener los DAOs exclusivamente para acceso a datos.
- Documentar claramente la responsabilidad de cada módulo.
- Considerar la introducción de una capa de servicios más granular si la lógica crece.

---
_Análisis realizado el 30 de junio de 2025._
