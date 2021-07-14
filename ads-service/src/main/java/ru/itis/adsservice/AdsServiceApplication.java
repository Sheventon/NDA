package ru.itis.adsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdsServiceApplication.class, args);
    }

}
