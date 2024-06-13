# Task Manager

## Descripción

El proyecto Task Manager es un sistema de gestión de tareas colaborativas desarrollado con Spring Boot. Permite a los usuarios registrar cuentas, crear proyectos, asignar tareas y gestionar el estado de las mismas.

## Requisitos

- Java 17
- Maven 3.3.0
- SQL Server

## Instalación

### Configuración de la Base de Datos

1. **Crear la base de datos en SQL Server**:
    En la raíz del proyecto, hay un archivo `.sql` que contiene todas las instrucciones necesarias para configurar la base de datos.

2. **Ejecuta el archivo .sql** en tu gestor de base de datos para crear las tablas necesarias.

### Back-end (Spring Boot)

1. Clona el repositorio:
    ```bash
    git clone https://github.com/nahuel-code1/task-manager.git
    cd task-manager
    ```

2. Configura tu base de datos en el archivo `application.yml` ubicado en `src/main/resources`.

3. Compila y ejecuta la aplicación:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Uso

1. Accede a la aplicación en tu navegador:
    ```
    http://localhost:8080/task-manager/swagger-ui/index.html#/
    ```

2. Regístrate como un nuevo usuario.

3. Crea proyectos y asigna tareas.

4. Gestiona el estado de las tareas.

## Estructura del Proyecto

- `src/main/java`: Código fuente del back-end.
- `src/main/resources`: Recursos del back-end (configuraciones, templates, etc.).
- `database.sql`: Archivo SQL para configurar la base de datos.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
