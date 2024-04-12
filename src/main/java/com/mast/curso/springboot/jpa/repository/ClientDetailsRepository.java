package com.mast.curso.springboot.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.mast.curso.springboot.jpa.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, Long> {

}
