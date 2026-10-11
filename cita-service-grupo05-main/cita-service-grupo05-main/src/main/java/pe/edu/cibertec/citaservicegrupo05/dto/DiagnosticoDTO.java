package pe.edu.cibertec.citaservicegrupo05.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticoDTO {
    private Long idDiagnostico;
    private String codigoCIE10;
    private String descripcion;
    private LocalDateTime fechaDiagnostico;
    private String gravedad;
}
