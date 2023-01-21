package com.guilherme.attornatus.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
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
    @CPF
    @Column(name = "cpf", nullable = false, unique = true)
    private String CPF;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id_address")
    private Address mainAddress;
}
