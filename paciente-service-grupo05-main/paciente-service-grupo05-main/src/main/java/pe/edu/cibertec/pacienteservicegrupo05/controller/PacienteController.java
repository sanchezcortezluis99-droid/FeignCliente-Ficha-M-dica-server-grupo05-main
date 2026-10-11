package pe.edu.cibertec.pacienteservicegrupo05.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.pacienteservicegrupo05.dto.DiagnosticoDTO;
import pe.edu.cibertec.pacienteservicegrupo05.dto.PacienteHistorialDTO;
import pe.edu.cibertec.pacienteservicegrupo05.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<PacienteHistorialDTO> obtenerPaciente(@PathVariable Long pacienteId) {
        return pacienteService.obtenerPaciente(pacienteId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{pacienteId}/diagnosticos")
    public ResponseEntity<List<DiagnosticoDTO>> obtenerDiagnosticos(@PathVariable Long pacienteId) {
        return pacienteService.obtenerDiagnosticos(pacienteId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
