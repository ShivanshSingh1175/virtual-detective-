package com.virtualdetective.codebreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodebreakerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodebreakerApplication.class, args);
    }
} 