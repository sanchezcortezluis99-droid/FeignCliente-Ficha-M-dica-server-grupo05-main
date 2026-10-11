package pe.edu.cibertec.pacienteservicegrupo05.service;

import org.springframework.stereotype.Service;
import pe.edu.cibertec.pacienteservicegrupo05.dto.DiagnosticoDTO;
import pe.edu.cibertec.pacienteservicegrupo05.dto.PacienteHistorialDTO;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PacienteService {

    private final Map<Long, PacienteHistorialDTO> pacientes = new LinkedHashMap<>();

    public PacienteService() {
        cargarDatos();
    }

    public Optional<PacienteHistorialDTO> obtenerPaciente(Long pacienteId) {
        return Optional.ofNullable(pacientes.get(pacienteId));
    }

    public Optional<List<DiagnosticoDTO>> obtenerDiagnosticos(Long pacienteId) {
        PacienteHistorialDTO paciente = pacientes.get(pacienteId);
        if (paciente == null) {
            return Optional.empty();
        }
        return Optional.of(paciente.getListaDiagnosticosActivos());
    }

    private void cargarDatos() {
        DiagnosticoDTO diagnostico1 = new DiagnosticoDTO(
                1L,
                "J00",
                "Rinofaringitis aguda",
                LocalDateTime.of(2026, 9, 15, 10, 30),
                "LEVE"
        );

        DiagnosticoDTO diagnostico2 = new DiagnosticoDTO(
                2L,
                "I10",
                "Hipertension esencial",
                LocalDateTime.of(2026, 10, 2, 9, 15),
                "SEVERO"
        );

        DiagnosticoDTO diagnostico3 = new DiagnosticoDTO(
                3L,
                "K29.7",
                "Gastritis no especificada",
                LocalDateTime.of(2026, 8, 21, 16, 0),
                "MODERADO"
        );

        pacientes.put(1L, new PacienteHistorialDTO(
                1L,
                "74859621",
                "Andrea Torres Salazar",
                "EPS",
                List.of(diagnostico1, diagnostico2),
                "ACTIVO"
        ));

        pacientes.put(2L, new PacienteHistorialDTO(
                2L,
                "70451236",
                "Miguel Rojas Diaz",
                "ESSALUD",
                List.of(diagnostico3),
                "ACTIVO"
        ));

        pacientes.put(3L, new PacienteHistorialDTO(
                3L,
                "45123698",
                "Lucia Fernandez Vega",
                "PARTICULAR",
                List.of(),
                "ACTIVO"
        ));
    }
}
