package com.guilherme.attornatus.personapi.repository;

import com.guilherme.attornatus.personapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByPersonId(Long personId);
}
