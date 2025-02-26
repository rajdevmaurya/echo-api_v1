package com.echo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.echo.api"})
public class JobsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsApiApplication.class, args);
    }
}
