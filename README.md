# Proyecto Grupal de VyV - Sistema de Gestión Veterinaria Borbor 🐾
Aplicación para clínicas veterinarias con módulos:
- Usuario
- Cliente -> Andy Romero
- Mascota -> Luis Aguirre
- Veterinario -> Lenny Borbor
- Cita -> Saúl Maldonado
- Medicamento -> Jeremy Alvarado
- Servicio -> Mariana Yagual
- Factura -> Anthony López
- DetalleFactura

## Tecnologías
- **Java 17**
- **MySQL 8+** (o compatible)
- Dependencias:
  - `mysql-connector-j` (v9.2 o superior)
  - `JUnit Jupiter (Aggregator)` (v5.13.3 o superior) para pruebas
  - `Mockito JUnit Jupiter`  (v5.18.0 o superior) para mocks

---

## Instalación
###1. Ejecutar script SQL en `database/VeterinariaScriptSQL.sql`, incluyendo insert en `usuario`.
###2. Configurar directorio `resources` como Resources Root, para lectura de los .properties.
###3. Añada las dependencias de `mysql-connector-j`, `JUnit Jupiter (Aggregator)` y `Mockito JUnit Jupiter`.
###4. En caso de realizar pruebas, configurar directorio `test` como Test Sources Root

## Usuario para iniciar sesión
nombre de usuario: admin
contraseña: admin123
