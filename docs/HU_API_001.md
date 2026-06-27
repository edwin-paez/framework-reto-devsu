# HU_API_001.md

# Historia de Usuario Técnica - Automatización API

| Campo | Valor |
|-------|-------|
| **ID** | HU-API-001 |
| **Nombre** | Automatizar el CRUD de usuarios en PetStore API |
| **Prioridad** | Alta |
| **Tipo** | Smoke |
| **Módulo** | User Management |

---

# Historia de Usuario

Como **Ingeniero QA Automation**,

quiero automatizar las operaciones CRUD sobre el recurso de usuarios de la API PetStore,

para validar de forma repetible que el ciclo de vida completo de un usuario (creación, consulta, actualización y eliminación) funciona correctamente y que los datos persisten con consistencia entre operaciones.

---

# Objetivo

Implementar pruebas de API REST utilizando Karate que validen el flujo completo de gestión de usuarios, verificando los códigos de respuesta HTTP, la estructura del payload y la consistencia de los datos a través de los distintos endpoints.

---

# API Bajo Prueba

**Base URL:** `https://petstore.swagger.io/v2`

**Documentación:** https://petstore.swagger.io/

---

# Alcance

La automatización cubre las siguientes operaciones:

1. **Crear un usuario** — `POST /user`
2. **Buscar el usuario creado** — `GET /user/{username}`
3. **Actualizar nombre y correo del usuario** — `PUT /user/{username}`
4. **Buscar el usuario actualizado** — `GET /user/{username}`
5. **Eliminar el usuario** — `DELETE /user/{username}`

---

# Fuera de Alcance

- Autenticación con token (el endpoint de usuario en PetStore es público).
- Pruebas de carga o rendimiento.
- Validación de paginación.
- Casos negativos adicionales (credenciales inválidas, campos requeridos faltantes).

---

# Reglas de Negocio

### RN-API-001
El `username` utilizado en cada escenario debe ser único para garantizar idempotencia y evitar colisiones entre ejecuciones. Se genera dinámicamente con un sufijo aleatorio.

### RN-API-002
Al crear un usuario, el sistema debe responder con HTTP 200 y un mensaje que confirme la operación.

### RN-API-003
Al buscar un usuario existente, la respuesta debe contener exactamente los datos con los que fue creado o actualizado más recientemente.

### RN-API-004
Al actualizar un usuario, los campos `firstName` y `email` deben reflejar los nuevos valores en la siguiente consulta `GET`.

### RN-API-005
Al eliminar un usuario, las consultas `GET` posteriores deben retornar HTTP 404.

---

# Casos de Prueba

| ID | Descripción | Método | Endpoint | HTTP Esperado |
|----|-------------|--------|----------|---------------|
| CP-API-001 | Crear un usuario nuevo | POST | /user | 200 |
| CP-API-002 | Buscar el usuario creado | GET | /user/{username} | 200 |
| CP-API-003 | Actualizar nombre y correo | PUT | /user/{username} | 200 |
| CP-API-004 | Buscar el usuario actualizado | GET | /user/{username} | 200 |
| CP-API-005 | Eliminar el usuario y verificar 404 | DELETE + GET | /user/{username} | 200 + 404 |

---

# Criterios de Aceptación

```gherkin
@CP-API-001
Scenario: Crear un usuario nuevo
  Given un payload con datos válidos de usuario
  When se realiza POST /user
  Then la respuesta es HTTP 200

@CP-API-002
Scenario: Buscar el usuario creado
  Given que el usuario fue creado previamente
  When se realiza GET /user/{username}
  Then la respuesta es HTTP 200
  And los datos coinciden con los enviados en la creación

@CP-API-003
Scenario: Actualizar nombre y correo del usuario
  Given que el usuario fue creado previamente
  When se realiza PUT /user/{username} con nuevo firstName y email
  Then la respuesta es HTTP 200

@CP-API-004
Scenario: Buscar el usuario actualizado
  Given que el usuario fue actualizado previamente
  When se realiza GET /user/{username}
  Then la respuesta es HTTP 200
  And los campos firstName y email reflejan los valores actualizados

@CP-API-005
Scenario: Eliminar el usuario
  Given que el usuario fue creado previamente
  When se realiza DELETE /user/{username}
  Then la respuesta es HTTP 200
  And una consulta GET posterior retorna HTTP 404
```

---

# Datos de Prueba

| Campo | Valor inicial | Valor actualizado |
|-------|--------------|-------------------|
| username | devsu_user_{random} | (sin cambio) |
| firstName | Andres | Carlos |
| lastName | Paez | Paez |
| email | andres.paez@devsu.com | carlos.paez.updated@devsu.com |
| password | Devsu2024! | (sin cambio) |
| phone | 3001234567 | (sin cambio) |

---

# Definition of Done

La historia se considera terminada cuando:

- Todos los escenarios se ejecutan exitosamente contra `https://petstore.swagger.io/v2`.
- Los códigos HTTP y los campos del payload son validados en cada escenario.
- La ejecución puede realizarse con `./gradlew karateTest`.
- El reporte Karate HTML se genera en `build/reports/tests/karateTest/`.
- Los escenarios son independientes entre sí (cada uno crea su propio usuario con username único).

---

# Consideraciones Técnicas

- Java 21
- Karate 1.5.1
- JUnit 5
- Source set separado (`src/karate/`) para evitar conflictos con Serenity BDD
- `karate-config.js` centraliza la `baseUrl` por entorno
- Ejecutar solo API tests: `./gradlew karateTest`
- Ejecutar solo E2E tests: `./gradlew test`
- Ejecutar ambos: `./gradlew test karateTest`
