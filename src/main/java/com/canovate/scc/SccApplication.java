package com.canovate.scc;

import com.canovate.scc.controller.DeviceController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.canovate.scc.*")
@ComponentScan(basePackages = {"com.canovate.scc.controller", "com.canovate.*"}, basePackageClasses = {DeviceController.class})
@EntityScan("com.canovate.scc.*")
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SccApplication {

    public static void main(String[] args) {
        SpringApplication.run(SccApplication.class, args);
    }

}
