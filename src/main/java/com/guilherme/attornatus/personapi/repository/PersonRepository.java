package com.guilherme.attornatus.personapi.repository;
import com.guilherme.attornatus.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByCPF(String cpf);
}
