package pe.edu.cibertec.pacienteservicegrupo05.dto;

import java.time.LocalDateTime;

public class DiagnosticoDTO {

    private Long idDiagnostico;
    private String codigoCIE10;
    private String descripcion;
    private LocalDateTime fechaDiagnostico;
    private String gravedad;

    public DiagnosticoDTO() {
    }

    public DiagnosticoDTO(Long idDiagnostico, String codigoCIE10, String descripcion,
                          LocalDateTime fechaDiagnostico, String gravedad) {
        this.idDiagnostico = idDiagnostico;
        this.codigoCIE10 = codigoCIE10;
        this.descripcion = descripcion;
        this.fechaDiagnostico = fechaDiagnostico;
        this.gravedad = gravedad;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getCodigoCIE10() {
        return codigoCIE10;
    }

    public void setCodigoCIE10(String codigoCIE10) {
        this.codigoCIE10 = codigoCIE10;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaDiagnostico() {
        return fechaDiagnostico;
    }

    public void setFechaDiagnostico(LocalDateTime fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }
}
