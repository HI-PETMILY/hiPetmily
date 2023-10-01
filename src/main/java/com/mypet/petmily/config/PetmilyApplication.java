package com.mypet.petmily.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mypet.petmily")
@MapperScan("com.mypet.petmily.member.dao")
public class PetmilyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetmilyApplication.class, args);
    }

}
