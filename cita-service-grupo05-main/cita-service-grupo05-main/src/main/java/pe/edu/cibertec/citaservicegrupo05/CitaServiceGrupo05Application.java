package pe.edu.cibertec.citaservicegrupo05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CitaServiceGrupo05Application {

    public static void main(String[] args) {
        SpringApplication.run(CitaServiceGrupo05Application.class, args);
    }

}
