package com.canovate.scc;

import aj.org.objectweb.asm.TypeReference;
import com.canovate.scc.model.Device;
import com.canovate.scc.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@EnableJpaRepositories("com.canovate.scc.*")
@ComponentScan(basePackages = { "com.canovate" })
@EntityScan("com.canovate.scc.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SccApplication {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean(name="entityManagerFactory")
    public SessionFactory sessionFactory() {
          return new Configuration()
                .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Device.class)
                                .buildSessionFactory();
    }

    @Bean
    CommandLineRunner runner(@Autowired DeviceService deviceService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            com.fasterxml.jackson.core.type.TypeReference<List<Device>> typeReference = new com.fasterxml.jackson.core.type.TypeReference<List<Device>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/devices.json");
            try {
                List<Device> devices = mapper.readValue(inputStream,typeReference);
                deviceService.save(devices);
                System.out.println("Devices were saved!");
            } catch (IOException e){
                System.out.println("Unable to save devices: " + e.getMessage());
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SccApplication.class, args);
    }

}
