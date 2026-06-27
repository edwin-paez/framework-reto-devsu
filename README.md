# Framework Reto Devsu — Automatización E2E y API

Repositorio: https://github.com/edwin-paez/framework-reto-devsu

Este proyecto tiene dos suites de pruebas automatizadas en el mismo repositorio:

- **E2E** con Serenity BDD + Screenplay + Cucumber sobre [SauceDemo](https://www.saucedemo.com/)
- **API** con Karate sobre la [PetStore API](https://petstore.swagger.io/)

Ambas se gestionan con Gradle y tienen sus propios source sets para no pisarse entre sí.

---

## Requisitos

- Java 21 (se recomienda usar [SDKMAN](https://sdkman.io/))
- Google Chrome instalado (para las pruebas E2E)
- Conexión a internet (la API de PetStore es pública)
- No es necesario instalar Gradle globalmente, el proyecto trae el wrapper `./gradlew`

---

## Estructura del proyecto

```
src/test/
  serenity/               → código Java de las pruebas E2E (Serenity + Screenplay)
    co/devsu/reto/
      hooks/
      interactions/
      models/
      questions/
      runners/
      stepdefinitions/
      tasks/
      userinterfaces/
      utils/
  karate/                 → runner de Karate
    co/devsu/reto/karate/
      KarateRunner.java
  resources/
    features/
      web/                → feature files de Serenity (PurchaseFlow.feature)
      api/users/          → feature files de Karate (user-create, read, update, delete, e2e)
    serenity.conf
    karate-config.js
    logback-test.xml
docs/
  HU_E2E_001.md           → historia de usuario E2E con criterios de aceptación
  HU_API_001.md           → historia de usuario API con criterios de aceptación
```

---

## Ejecución local

### Pruebas E2E (Serenity)

```bash
# Ejecutar con tag @Smoke (por defecto)
./gradlew test

# Con tag específico
./gradlew test -Dcucumber.filter.tags="@Smoke"

# Con ambiente específico
./gradlew test -Denvironment=stg -Dcucumber.filter.tags="@Smoke"
```

El reporte se genera en `target/site/serenity/index.html`.

Por defecto las pruebas corren en modo headless. Si querés verlas en el navegador,
cambiá `headless.mode = false` en `src/test/resources/serenity.conf`.

### Pruebas de API (Karate)

```bash
# Ejecutar toda la suite
./gradlew karateTest

# Filtrar por tag
./gradlew karateTest -Dkarate.options="--tags @Smoke"
./gradlew karateTest -Dkarate.options="--tags @Regression"
./gradlew karateTest -Dkarate.options="--tags @E2E"
./gradlew karateTest -Dkarate.options="--tags @Create"

# Con ambiente específico
./gradlew karateTest -Dkarate.env=staging
```

El reporte se genera en `build/karate-reports/karate-summary.html`.

### Ejecutar todo junto

```bash
./gradlew test karateTest
```

---

## Tags disponibles

### E2E
| Tag | Descripción |
|-----|-------------|
| `@Smoke` | Flujo de compra completo (único escenario) |

### API
| Tag | Escenario |
|-----|-----------|
| `@Smoke` | Create + Read |
| `@Regression` | Update + Delete + E2E + negativos |
| `@E2E` | Flujo CRUD completo encadenado |
| `@Create` | Solo creación de usuario |
| `@Read` | Solo consulta por username |
| `@Update` | Solo actualización de datos |
| `@Delete` | Solo eliminación |

---

## Ejecución en el pipeline (GitHub Actions)

El pipeline está en `.github/workflows/test.yml` y se ejecuta manualmente desde:

**https://github.com/edwin-paez/framework-reto-devsu/actions**

Para lanzarlo hay que ir a esa URL, seleccionar el workflow **"Ejecutar pruebas automatizadas"**
y hacer clic en **"Run workflow"**. Ahí aparecen tres parámetros:

| Parámetro | Opciones | Descripción |
|-----------|----------|-------------|
| `suite` | `e2e` / `api` / `all` | Qué suite ejecutar. Con `all` corren las dos en paralelo |
| `environment` | `default`, `stg`, `dev`, `prod`, `qa`, `staging` | Para E2E usar `default` o `stg`. Para API usar `qa` o `staging` |
| `tags` | cualquier tag válido | Opcional. Por defecto corre `@Smoke` |

Ejemplos de combinaciones típicas:

- Solo E2E en default: `suite=e2e`, `environment=default`, `tags=@Smoke`
- Solo API completa: `suite=api`, `environment=qa`, `tags=@Regression`
- Todo junto: `suite=all`, `environment=default` / `qa`, `tags=@Smoke`

---

## Reportes en GitHub Pages

Los reportes se publican automáticamente en GitHub Pages después de cada ejecución.

Las rutas dependen del tipo de suite y el número de ejecución del pipeline:

**E2E (Serenity):**
```
https://edwin-paez.github.io/framework-reto-devsu/reports/e2e/{run_number}/index.html
```

**API (Karate):**
```
https://edwin-paez.github.io/framework-reto-devsu/reports/api/{run_number}/index.html
```

El `{run_number}` es el número que aparece en la columna de la izquierda al entrar al pipeline
en GitHub Actions. Por ejemplo, si la ejecución fue la número 7:

- E2E: https://edwin-paez.github.io/framework-reto-devsu/reports/e2e/7/index.html
- API: https://edwin-paez.github.io/framework-reto-devsu/reports/api/7/index.html

---

## Ambientes configurados

### E2E (serenity.conf)
| Ambiente | URL |
|----------|-----|
| `default` | https://www.saucedemo.com/ |
| `stg` | https://www.saucedemo.com/ |

### API (karate-config.js)
| Ambiente | URL |
|----------|-----|
| `qa` | https://petstore.swagger.io/v2 |
| `staging` | https://petstore.swagger.io/v2 |

---

## Escenarios API cubiertos

La suite de API cubre el ciclo CRUD completo del recurso `/user` de PetStore,
incluyendo caminos alternativos que documentan comportamientos no estándar de la API:

| Escenario | Tag | Resultado esperado |
|-----------|-----|--------------------|
| Crear usuario nuevo | `@Create @Smoke` | PASS |
| Buscar usuario creado | `@Read @Smoke` | PASS |
| Actualizar nombre y email | `@Update @Regression` | PASS |
| Actualizar usuario inexistente | `@Regression` | PASS (bug documentado) |
| Eliminar usuario | `@Delete @Regression` | PASS |
| POST con username null | `@BadUser` | FAIL intencional — bug en la API |
| POST con id enviado por cliente | `@BadId` | FAIL intencional — bug en la API |
| Flujo E2E completo | `@E2E @Regression` | PASS |

Los escenarios marcados como "FAIL intencional" documentan defectos de la API
donde el comportamiento real no cumple los estándares REST. Esa falla es
la evidencia del bug, no un error del framework.

---

## Documentación técnica

- `docs/HU_E2E_001.md` — historia de usuario, reglas de negocio y criterios de aceptación del flujo E2E
- `docs/HU_API_001.md` — historia de usuario, casos de prueba y criterios de aceptación de la suite API
- `conclusiones.txt` — hallazgos, bugs identificados y conclusiones de ambas suites
