package com.engineerpro.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BookingApplication {
    private static ApplicationContext appContext;

    public static void main(String[] args) {
        appContext = SpringApplication.run(BookingApplication.class, args);
        String[] beanNames = appContext.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }
}
