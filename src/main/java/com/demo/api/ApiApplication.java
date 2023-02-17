package com.demo.api;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.Locale;
import org.springframework.context.MessageSource;

@SpringBootApplication
@ComponentScan("com.demo.api.*")
@RequiredArgsConstructor
/**
 * Api Application.
 * 
 * @author QuynhNN
 */
public class ApiApplication {

    @Autowired
    private MessageSource messageSource;

    /**
     * Main function to run this application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Running Message Property Data");
        System.out.println(messageSource.getMessage("api.error.id.not.found", null, Locale.ENGLISH));
        System.out.println(messageSource.getMessage("api.error.id.already.registered", null, Locale.ENGLISH));
        System.out.println(messageSource.getMessage("api.response.staff.creation.successful", null, Locale.ENGLISH));
        System.out.println(messageSource.getMessage("api.response.staff.update.successful", null, Locale.ENGLISH));
        System.out.println("End Message Property Data");
    }
}
