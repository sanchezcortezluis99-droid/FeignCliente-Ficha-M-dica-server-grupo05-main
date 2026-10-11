package pe.edu.cibertec.citaservicegrupo05.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.citaservicegrupo05.dto.DiagnosticoDTO;
import pe.edu.cibertec.citaservicegrupo05.dto.PacienteHistorialDTO;

import java.util.List;

@FeignClient(name = "paciente-service-grupo05")
public interface PacienteFeignClient {

    @GetMapping("/api/v1/pacientes/{pacienteId}")
    PacienteHistorialDTO obtenerPacientePorId(@PathVariable("pacienteId") Long pacienteId);

    @GetMapping("/api/v1/pacientes/{pacienteId}/diagnosticos")
    List<DiagnosticoDTO> obtenerDiagnosticosPorPacienteId(@PathVariable("pacienteId") Long pacienteId);
}
