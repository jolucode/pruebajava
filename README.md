# pruebajava

# API RESTful de Registro de Usuarios

Este proyecto es una API RESTful desarrollada en Java con Spring Boot, que permite registrar usuarios y sus teléfonos, validando formato de correo, contraseña y evitando duplicidad de emails.

## 🛠 Tecnologías usadas

- Java 17+
- Spring Boot 3
- Maven
- JPA (Hibernate)
- H2 (base de datos en memoria)
- Bean Validation (Jakarta)
- Lombok

---

## 🚀 Cómo ejecutar el proyecto

1. Clona el repositorio:
```bash
git clone https://github.com/tu_usuario/registro-usuarios.git
cd registro-usuarios
```

2. Ejecuta el proyecto con Maven:
```bash
./mvnw spring-boot:run
```

3. Accede a la consola H2 (opcional, para ver la BD):
```bash
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (vacío)
```

4. Endpoint disponible (curl)
```bash
curl --location 'http://localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Jose Becerra",
    "email": "josebecerra@gmail.cl",
    "password": "Clave123",
    "phones": [
      {
        "number": "930373525",
        "citycode": "1",
        "contrycode": "57"
      }
    ]
  }'
```

5. Estructura del Proyecto
```bash
src
├── controller       # Controlador REST
├── service          # Lógica de negocio
├── dto              # Objetos de transferencia (entrada/salida)
├── entity           # Entidades JPA
├── repository       # Acceso a datos (JPARepository)
├── config           # Validaciones personalizadas
├── exception        # Manejo global de errores
```


6. Notas
```bash

* Se respeta los codigos http solicitados.
* La expresión regular para la contraseña es configurable desde application.properties.
* Toda la base de datos se ejecuta en memoria (H2), sin necesidad de instalar nada adicional.
* Todos los errores siguen el formato estándar:

{ "mensaje": "..." }

```

6. Autor
```bash

Nombres: Jose Luis Becerra Regalado
Correo: becerra.j@pucp.edu.pe
Linkedin: https://www.linkedin.com/in/jose-luis-becerra-r-3b3155168/

```