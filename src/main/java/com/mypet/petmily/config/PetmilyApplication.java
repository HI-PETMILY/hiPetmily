package com.mypet.petmily.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mypet.petmily")
public class PetmilyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetmilyApplication.class, args);
    }

}
