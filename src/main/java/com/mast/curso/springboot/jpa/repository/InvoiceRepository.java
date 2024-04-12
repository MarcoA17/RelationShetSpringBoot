package com.mast.curso.springboot.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.mast.curso.springboot.jpa.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

}
