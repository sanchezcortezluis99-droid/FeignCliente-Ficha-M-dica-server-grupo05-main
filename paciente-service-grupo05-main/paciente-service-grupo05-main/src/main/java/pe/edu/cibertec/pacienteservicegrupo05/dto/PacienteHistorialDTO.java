package pe.edu.cibertec.pacienteservicegrupo05.dto;

import java.util.List;

public class PacienteHistorialDTO {

    private Long pacienteId;
    private String numeroDocumento;
    private String nombreCompleto;
    private String tipoSeguro;
    private List<DiagnosticoDTO> listaDiagnosticosActivos;
    private String estadoPaciente;

    public PacienteHistorialDTO() {
    }

    public PacienteHistorialDTO(Long pacienteId, String numeroDocumento, String nombreCompleto,
                                String tipoSeguro, List<DiagnosticoDTO> listaDiagnosticosActivos,
                                String estadoPaciente) {
        this.pacienteId = pacienteId;
        this.numeroDocumento = numeroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.tipoSeguro = tipoSeguro;
        this.listaDiagnosticosActivos = listaDiagnosticosActivos;
        this.estadoPaciente = estadoPaciente;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public List<DiagnosticoDTO> getListaDiagnosticosActivos() {
        return listaDiagnosticosActivos;
    }

    public void setListaDiagnosticosActivos(List<DiagnosticoDTO> listaDiagnosticosActivos) {
        this.listaDiagnosticosActivos = listaDiagnosticosActivos;
    }

    public String getEstadoPaciente() {
        return estadoPaciente;
    }

    public void setEstadoPaciente(String estadoPaciente) {
        this.estadoPaciente = estadoPaciente;
    }
}
