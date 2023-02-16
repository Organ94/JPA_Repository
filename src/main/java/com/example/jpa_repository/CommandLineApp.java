package com.example.jpa_repository;

import com.example.jpa_repository.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class CommandLineApp implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var random = new Random();

        var names = List.of("Ivan", "Petr", "Boris", "Olga", "Alex");
        var surnames = List.of("Ivanov(a)", "Petrov(a)", "Sidorov(a)", "Smirnov(a)");
        var cities = List.of("Moscow", "Kazan", "St.Petersburg", "Samara");

        IntStream.range(0, 20)
                .forEach(i -> {
                    var person = Person.builder()
                            .name(names.get(random.nextInt(names.size())))
                            .surname(surnames.get(random.nextInt(surnames.size())))
                            .age(random.nextInt(16, 45))
                            .phoneNumber("+7(999)123 " + random.nextInt(1000, 10000))
                            .cityOfLiving(cities.get(random.nextInt(cities.size())))
                            .build();

                    entityManager.persist(person);
                });
    }
}
