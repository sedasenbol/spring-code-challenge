package com.canovate.scc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.canovate.scc.*")
@ComponentScan(basePackages = { "com.canovate" })
@EntityScan("com.canovate.scc.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SccApplication {

    public static void main(String[] args) {
        SpringApplication.run(SccApplication.class, args);
    }

}
