package com.demo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo.api.*")

/**
 * Api Application.
 * 
 * @author QuynhNN
 */
public class ApiApplication {

    /**
     * Main function to run this application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
