package pe.edu.cibertec.citaservicegrupo05.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.cibertec.citaservicegrupo05.client.PacienteFeignClient;
import pe.edu.cibertec.citaservicegrupo05.dto.AtencionMedicaResponseDTO;
import pe.edu.cibertec.citaservicegrupo05.dto.DiagnosticoDTO;
import pe.edu.cibertec.citaservicegrupo05.dto.PacienteHistorialDTO;
import pe.edu.cibertec.citaservicegrupo05.dto.ResumenFichaMedicaConsolidadaDTO;
import pe.edu.cibertec.citaservicegrupo05.repository.CitaRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteFeignClient pacienteFeignClient;

    public AtencionMedicaResponseDTO obtenerCita(Long idCita) {
        return citaRepository.findById(idCita)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cita no encontrada: " + idCita));
    }

    public AtencionMedicaResponseDTO evaluarYCompletar(Long idCita) {
        AtencionMedicaResponseDTO cita = obtenerCita(idCita);

        PacienteHistorialDTO paciente = pacienteFeignClient.obtenerPacientePorId(cita.getPacienteId());
        if (paciente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado para la cita: " + idCita);
        }

        String tipoSeguro = paciente.getTipoSeguro() != null ? paciente.getTipoSeguro().toUpperCase().trim() : "PARTICULAR";
        double base = cita.getMontoConsultaCalculado() != null ? cita.getMontoConsultaCalculado() : 0.0;
        double copagoFinal;

        switch (tipoSeguro) {
            case "EPS":
                // 80% descuento sobre el monto base (paga el 20%)
                copagoFinal = Math.round((base * 0.20) * 100.0) / 100.0;
                break;
            case "ESSALUD":
                // 100% cobertura / copago 0
                copagoFinal = 0.0;
                break;
            case "PARTICULAR":
            default:
                // 0% descuento sobre el copago base (paga el 100%)
                copagoFinal = base;
                break;
        }

        cita.setEstadoCita("COMPLETADA");
        cita.setCopagoAplicado(copagoFinal);

        return citaRepository.save(cita);
    }

    public ResumenFichaMedicaConsolidadaDTO obtenerFichaMedica(Long pacienteId) {
        // Consultar paciente y sus diagnósticos del servicio paciente mediante FeignClient
        PacienteHistorialDTO paciente = pacienteFeignClient.obtenerPacientePorId(pacienteId);
        List<DiagnosticoDTO> diagnosticosRemotos = pacienteFeignClient.obtenerDiagnosticosPorPacienteId(pacienteId);

        if (diagnosticosRemotos == null) {
            diagnosticosRemotos = new ArrayList<>();
        }

        // Obtener atenciones locales de cita-service
        List<AtencionMedicaResponseDTO> citasPaciente = citaRepository.findByPacienteId(pacienteId);

        int totalAtendidas = 0;
        double totalGasto = 0.0;

        // Consolidación de diagnósticos (evitando duplicados por idDiagnostico o código CIE10)
        Map<String, DiagnosticoDTO> diagnosticosMap = new LinkedHashMap<>();

        // Agregar diagnósticos remotos del historial
        for (DiagnosticoDTO diag : diagnosticosRemotos) {
            if (diag != null) {
                String key = diag.getIdDiagnostico() != null ? "ID-" + diag.getIdDiagnostico() : diag.getCodigoCIE10();
                diagnosticosMap.put(key, diag);
            }
        }

        // Agregar diagnósticos de las atenciones locales y calcular métricas
        for (AtencionMedicaResponseDTO atencion : citasPaciente) {
            if ("COMPLETADA".equalsIgnoreCase(atencion.getEstadoCita())) {
                totalAtendidas++;
                if (atencion.getCopagoAplicado() != null) {
                    totalGasto += atencion.getCopagoAplicado();
                } else if (atencion.getMontoConsultaCalculado() != null) {
                    totalGasto += atencion.getMontoConsultaCalculado();
                }
            }

            if (atencion.getListaDiagnosticos() != null) {
                for (DiagnosticoDTO diag : atencion.getListaDiagnosticos()) {
                    if (diag != null) {
                        String key = diag.getIdDiagnostico() != null ? "ID-" + diag.getIdDiagnostico() : diag.getCodigoCIE10();
                        diagnosticosMap.putIfAbsent(key, diag);
                    }
                }
            }
        }

        List<DiagnosticoDTO> listaConsolidada = new ArrayList<>(diagnosticosMap.values());

        // Regla: requiereAtencionEspecializada = true si posee diagnósticos con gravedad 'SEVERO'
        boolean requiereEspecializada = listaConsolidada.stream()
                .anyMatch(d -> d.getGravedad() != null && "SEVERO".equalsIgnoreCase(d.getGravedad().trim()));

        // Regla: clasificacionRiesgo ('ALTO_RIESGO' o 'BAJO_RIESGO')
        String clasificacionRiesgo = requiereEspecializada ? "ALTO_RIESGO" : "BAJO_RIESGO";

        String nombre = paciente != null ? paciente.getNombreCompleto() : "Desconocido";
        String tipoSeguro = paciente != null ? paciente.getTipoSeguro() : "NO_REGISTRADO";

        return ResumenFichaMedicaConsolidadaDTO.builder()
                .pacienteId(pacienteId)
                .nombrePaciente(nombre)
                .edad(28) // edad referencial
                .tipoSeguro(tipoSeguro)
                .totalCitasAtendidas(totalAtendidas)
                .montoTotalGasto(Math.round(totalGasto * 100.0) / 100.0)
                .historialAtenciones(citasPaciente)
                .diagnosticosConsolidados(listaConsolidada)
                .requiereAtencionEspecializada(requiereEspecializada)
                .clasificacionRiesgo(clasificacionRiesgo)
                .build();
    }
}
