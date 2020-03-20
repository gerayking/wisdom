package com.lab.wisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WisdomApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

}
