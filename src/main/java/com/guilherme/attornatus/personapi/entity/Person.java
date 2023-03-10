package com.guilherme.attornatus.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="person")
public class Person {
    @Id
    @Column(name = "id_person", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 200)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    //@CPF
    @Column(name = "cpf", nullable = false, unique = true)
    private String CPF;
    @Column(name = "address_id")
    private Long mainAddress;
}
