package pe.edu.cibertec.citaservicegrupo05.repository;

import org.springframework.stereotype.Repository;
import pe.edu.cibertec.citaservicegrupo05.dto.AtencionMedicaResponseDTO;
import pe.edu.cibertec.citaservicegrupo05.dto.DiagnosticoDTO;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CitaRepository {
    private final Map<Long, AtencionMedicaResponseDTO>citas = new HashMap<>();
    public CitaRepository(){
        citas.put(1L,
                new AtencionMedicaResponseDTO(1L, "ATENCION-001", LocalDateTime.of(2026,10,1,9,0),1L,"Dr. Ramos", "Cardiologia","PROGRAMADA",
                        List.of(new DiagnosticoDTO(1L,"I10", "Hipertensión",LocalDateTime.of(2026,10,1,9,30),"MODERADO")),
                        50.0,null));
        citas.put(2L,
                new AtencionMedicaResponseDTO(2L, "ATENCION-002", LocalDateTime.of(2026,10,2,10,0),2L,"Dr. House", "Enfermedades infecciosas y nefrología","PROGRAMADA",
                        List.of(new DiagnosticoDTO(2L,"J06", "Lupus",LocalDateTime.of(2026,10,2,10,30),"SEVERO")),
                        50.0,null));
        citas.put(3L,
                new AtencionMedicaResponseDTO(3L, "ATENCION-003", LocalDateTime.of(2026,9,20,11,0),1L,"Dr. Murphy", "Cirugia general","COMPLETADA",
                        List.of(new DiagnosticoDTO(3L,"J06", "ecocardiograma",LocalDateTime.of(2026,9,20,11,30),"LEVE")),
                        50.0,10.0));
        citas.put(4L,
                new AtencionMedicaResponseDTO(4L, "ATENCION-004", LocalDateTime.of(2026,10,5,15,0),3L,"Dra. Torres", "Medicina general","PROGRAMADA",
                        List.of(new DiagnosticoDTO(4L,"J06.9", "Infección respiratoria aguda",LocalDateTime.of(2026,10,5,15,20),"LEVE")),
                        50.0,null));
        citas.put(5L,
                new AtencionMedicaResponseDTO(5L, "ATENCION-005", LocalDateTime.of(2026,10,8,8,30),2L,"Dra. Cuddy", "Endocrinologia","CANCELADA",
                        List.of(new DiagnosticoDTO(5L,"E11", "Diabetes tipo 2",LocalDateTime.of(2026,10,8,8,45),"MODERADO")),
                        50.0,null));
    }
    public Optional<AtencionMedicaResponseDTO> findById(Long idCIta){
        return Optional.ofNullable(citas.get(idCIta));
    }
    public List<AtencionMedicaResponseDTO> findByPacienteId(Long pacienteId){
        return citas.values().stream().filter( cita -> cita.getPacienteId().equals(pacienteId)).toList();
    }
    public AtencionMedicaResponseDTO save(AtencionMedicaResponseDTO cita){
        citas.put(cita.getIdCita(),cita);
        return cita;
    }
}
