# spring-pizzahurt
## Proyecto obligatorio final. Curso: Desarrollo de aplicaciones con Spring / Spring Boot
- Universidad ORT
- Agosto 2023 - Octubre 2023

### Autores
- Fourment, Juan
- Navarro Gutiérrez, Nicolás
- Ortuzar, Martín

### Docente
- Juan Larrayoz

# Descripción del proyecto
El objetivo de este proyecto la implementación de un sistema para satisfacer las necesidades de una pizzería, desarrollando un sistema de pedidos en línea funcional utilizando Spring Boot y tecnologías relacionadas como JPA y Thymeleaf.

El frontend está realizado con Spring MVC + Thymeleaf + HTML con backend en Spring Boot v3.1.3

# Requerimientos funcionales implementados
- Autenticación de usuarios
- Registro de usuarios nuevos
- Creación de pizzas
- Realizar pedidos de las creaciones del usuario en líena
- Añadir medios de pago al usuario
- Añadir domicilios al usuario
- Servicios REST para interoperabilidad
    - Login de usuario
    - Registro de nuevo usuario
    - Listar medios de pago de un usuario
    - Ingresar nuevo medio de pago al usuario

Así mismo, para cada una de las entidades se realizaron implementaciones de Bean Validation además de implementar Spring Security para asegurar los request.

# Requerimientos no funcionales para ejecutar la aplicación
El equipo donde se ejecutará la aplicación debe contar con la plataforma Java (JVM / JRE) versión 17 o superior.

# Iniciar el sistema
## Opción 1
1. Dentro del repositorio git ![https://github.com/nnavarrog/spring-pizzahurt](https://github.com/nnavarrog/spring-pizzahurt) en rama __main__, descargar el archivo pizzahurt-1.0.jar
2. Ingrese en el directorio donde descargó el archivo anterior y abra una consola (cmd o powershell)
3. Estando en el directorio, escriba el siguiente comando: __java -jar pizzahurt-1.0.jar__
4. Abra un navegador web e ingrese en el enlace: __[http://localhost:8080/]__ para poder operar con el sistema.

## Opción 2
Para esta opción se recomienda tener instalado las herramientas de Maven.
1. Realice un git clone del repositorio ![https://github.com/nnavarrog/spring-pizzahurt](https://github.com/nnavarrog/spring-pizzahurt)
2. Inicie una terminal e ingrese en el directorio del repositorio.
3. Ejecute el siguiente comando __mvn spring-boot:run__
4. Abra un navegador web e ingrese en el enlace: __[http://localhost:8080/]__ para poder operar con el sistema.

## Opción 3
Para esta opción necesitará de un IDE de desarrollo integrado como Intellij IDEA o NetBeans.
1. Realice un git clone del repositorio ![https://github.com/nnavarrog/spring-pizzahurt](https://github.com/nnavarrog/spring-pizzahurt)
2. Abra alguno de los IDE mencionados anteriormente.
3. Dentro del IDE, selecciones la opción de _Abrir Proyecto_ y seleccione el directorio del proyecto.
4. Inicie el programa con la opción _RUN_ del IDE.

