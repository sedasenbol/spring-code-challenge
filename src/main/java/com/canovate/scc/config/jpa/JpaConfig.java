package com.canovate.scc.config.jpa;

import com.canovate.scc.model.Device;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class JpaConfig {

    private SessionFactory sessionFactory;

    @Bean
    public EntityManager entityManager() {
        return sessionFactory.createEntityManager();
    }

    ;

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean(name = "entityManagerFactory")
    public SessionFactory sessionFactory() {


        this.sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Device.class)
                .buildSessionFactory();

        return sessionFactory;
    }
}
