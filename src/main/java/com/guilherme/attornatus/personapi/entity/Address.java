package com.guilherme.attornatus.personapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address", nullable = false)
    private Long id;
    @Column(name = "logradouro",nullable = false, length = 250)
    private String logradouro;
    @Column(name = "cep",nullable = false)
    private String CEP;
    @Column(name = "number",nullable = false)
    private Long number;
    @Column(name = "city_name",nullable = false, length = 50)
    private Long city;
    @ManyToOne//(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "person_id", referencedColumnName = "id_person", nullable = false)
    private Person person;
}
