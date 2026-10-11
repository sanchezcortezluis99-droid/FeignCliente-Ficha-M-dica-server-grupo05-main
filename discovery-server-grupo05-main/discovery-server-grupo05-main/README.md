# Discovery Server - Grupo 05

Chicos, les dejo el proyecto `discovery-server-grupo05` listo para utilizar como servidor Eureka del grupo.

Para abrirlo:

1. Descompriman el proyecto.
2. Abran IntelliJ IDEA.
3. Seleccionen **File > Open**.
4. Elijan la carpeta `discovery-server-grupo05`.
5. Esperen a que Maven cargue las dependencias.
6. Verifiquen que el proyecto utilice Java 25.
7. Ejecuten `DiscoveryServerGrupo05Application`.
8. Abran en el navegador `http://localhost:8761/`.

El proyecto incluye:

- Spring Boot 4.1.1
- Spring Cloud 2025.1.3
- Java 25
- Eureka Server
- Puerto 8761

Para que sus proyectos se registren en Eureka deben configurar:

```properties
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
```

Cada proyecto debe mantener su propio nombre de servicio. Para nuestro grupo se usarán:

- `api-gateway-grupo05`
- `cita-service-grupo05`
- `paciente-service-grupo05`

Antes de realizar las pruebas integradas debemos levantar primero este Discovery Server y luego los demás servicios.
