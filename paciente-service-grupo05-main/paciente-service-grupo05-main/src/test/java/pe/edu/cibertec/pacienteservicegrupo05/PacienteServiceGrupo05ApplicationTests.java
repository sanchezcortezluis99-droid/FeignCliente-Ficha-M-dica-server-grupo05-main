package pe.edu.cibertec.pacienteservicegrupo05;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.discovery.enabled=false"
})
class PacienteServiceGrupo05ApplicationTests {

    @Test
    void contextLoads() {
    }
}
