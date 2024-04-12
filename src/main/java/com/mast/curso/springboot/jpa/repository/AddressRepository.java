package com.mast.curso.springboot.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.mast.curso.springboot.jpa.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
