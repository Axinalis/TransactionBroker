package com.axinalis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.axinalis.repository")
@SpringBootApplication
public class JpaApp {

    public static void main( String[] args ) {
        SpringApplication.run(JpaApp.class, args);
    }

}
