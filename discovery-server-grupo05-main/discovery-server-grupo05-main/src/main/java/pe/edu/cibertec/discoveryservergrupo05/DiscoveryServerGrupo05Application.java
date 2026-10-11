package pe.edu.cibertec.discoveryservergrupo05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerGrupo05Application {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerGrupo05Application.class, args);
    }
}
