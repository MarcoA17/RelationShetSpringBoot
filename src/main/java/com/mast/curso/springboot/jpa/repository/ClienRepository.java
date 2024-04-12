package com.mast.curso.springboot.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mast.curso.springboot.jpa.entities.Client;

public interface ClienRepository extends CrudRepository<Client, Long> {
	@Query("select c from Client c left join fetch c.addresses where c.id=?1")
	Optional<Client> findOneWithAddresses(long id);
	
	@Query("select c from Client c left join fetch c.invoices where c.id=?1") //left tenga o no tenga facturas
	Optional<Client> findOneWithInvoices(long id);
	
	@Query("select c from Client c left join fetch c.invoices left join fetch c.addresses left join fetch c.clientDetails where c.id=?1") //left tenga o no tenga facturas
	Optional<Client> findOne(long id);
}
