package com.lab.wisdom;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.lab.wisdom.mapper")
@SpringBootApplication
public class WisdomApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WisdomApplication.class, args);
    }

}
