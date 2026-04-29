package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.Metamodel;
import java.util.Set;

@Component
public class JpaChecker implements ApplicationRunner {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void run(ApplicationArguments args) {
        Metamodel metamodel =  this.entityManagerFactory.getMetamodel();
        Set<ManagedType<?>> entities = metamodel.getManagedTypes();

        System.out.println("Зарегистрированные сущности:");
        entities.forEach(entity -> System.out.println("  - " + entity.getJavaType().getName()));
    }
}