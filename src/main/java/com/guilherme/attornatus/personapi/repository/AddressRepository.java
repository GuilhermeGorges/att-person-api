package com.guilherme.attornatus.personapi.repository;

import com.guilherme.attornatus.personapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
