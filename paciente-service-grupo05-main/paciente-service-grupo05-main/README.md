# Paciente Service - Grupo 05

Chicos, les dejo el proyecto `paciente-service-grupo05` listo para trabajar la parte de pacientes e historial de diagnosticos del examen.

## Para abrirlo

1. Descompriman el proyecto.
2. Abran IntelliJ IDEA.
3. Seleccionen **File > Open**.
4. Elijan la carpeta `paciente-service-grupo05`.
5. Esperen a que Maven cargue las dependencias.
6. Verifiquen que el proyecto utilice Java 25.
7. Levanten primero `discovery-server-grupo05` en el puerto 8761.
8. Ejecuten `PacienteServiceGrupo05Application`.

## Configuracion

El proyecto incluye:

- Java 25
- Spring Boot 4.1.1
- Spring Cloud 2025.1.3
- Spring Web
- Eureka Discovery Client
- Puerto 8082

El servicio se registra en Eureka mediante:

```properties
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

## DTO incluidos

- `DiagnosticoDTO`
- `PacienteHistorialDTO`

## Endpoints

Consultar paciente:

```text
GET http://localhost:8082/api/v1/pacientes/1
```

Consultar diagnosticos del paciente:

```text
GET http://localhost:8082/api/v1/pacientes/1/diagnosticos
```

Hay datos de prueba para los pacientes `1`, `2` y `3`, incluyendo seguros `EPS`, `ESSALUD` y `PARTICULAR`.

Cuando el servicio este levantado debe aparecer en Eureka como `PACIENTE-SERVICE-GRUPO05`.

Esta estructura queda lista para que luego `cita-service-grupo05` consuma los endpoints mediante FeignClient.
