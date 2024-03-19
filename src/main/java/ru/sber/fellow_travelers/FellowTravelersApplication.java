package ru.sber.fellow_travelers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FellowTravelersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FellowTravelersApplication.class, args);
    }

}
