package com.mast.curso.springboot.jpa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.mast.curso.springboot.jpa.entities.Address;
import com.mast.curso.springboot.jpa.entities.Client;
import com.mast.curso.springboot.jpa.entities.ClientDetails;
import com.mast.curso.springboot.jpa.entities.Invoice;
import com.mast.curso.springboot.jpa.repository.AddressRepository;
import com.mast.curso.springboot.jpa.repository.ClienRepository;
import com.mast.curso.springboot.jpa.repository.ClientDetailsRepository;
import com.mast.curso.springboot.jpa.repository.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshetApplication implements CommandLineRunner {

	@Autowired
	private ClienRepository clientClienRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// manyToOne();
		// manyToOneFindByClient();
		// oneToMany();
		// oneToManyFindById();
		// removeAddress();
		// removeAddressFindById();
		// oneToManyInvoiceBiDireccional();
		// oneToManyInvoiceBiDireccionalFindById();
		// removeInvoiceBiDireccionalFindById();
		// removeInvoiceBiDireccional();
		//oneToOne();
		oneToOneFindById();
	}

	@Transactional
	public void oneToOneFindById() {
		
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);
		
		Optional<Client> clientOptional = clientClienRepository.findOne(2L);
		
		clientOptional.ifPresent(client ->{
			client.setClientDetails(clientDetails);
			clientClienRepository.save(client);
			System.out.println(client);
		});
		
		
		
	}
	
	@Transactional
	public void oneToOne() {
		
		ClientDetails clientDetails = new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);
		
		Client client = new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientClienRepository.save(client);

		//ClientDetails clientDetails = new ClientDetails(true, 5000);
		//clientDetails.setClient(client);
		
		System.out.println(client);
		
	}

	@Transactional
	public void removeInvoiceBiDireccional() {

		// Optional<Client> client = Optional.of(new Client("Fran", "Moras"));
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientClienRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClientBd = clientClienRepository.findOne(3L);

		optionalClientBd.ifPresent(clientDb -> {

			Invoice invoice3 = new Invoice("Compras de la casa", 5000L);
			invoice3.setId(2L);

			Optional<Invoice> optionalInvoice = Optional.of(invoice3);/// invoiceRepository.findById(1L);
			optionalInvoice.ifPresent(invoice -> {
//				client.getInvoices().remove(invoice);
//				invoice.setClient(null);

				// Mejora
				clientDb.removeInvoice(invoice);

				clientClienRepository.save(clientDb);

				System.out.println(clientDb);
			});
		});

	}

	@Transactional
	public void removeInvoiceBiDireccionalFindById() {

		Optional<Client> optionalClient = clientClienRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientClienRepository.save(client);

			System.out.println(client);

		});

		Optional<Client> optionalClientBd = clientClienRepository.findOne(1L);

		optionalClientBd.ifPresent(client -> {

			Invoice invoice3 = new Invoice("Compras de la casa", 5000L);
			invoice3.setId(2L);

			Optional<Invoice> optionalInvoice = Optional.of(invoice3);/// invoiceRepository.findById(1L);
			optionalInvoice.ifPresent(invoice -> {
//				client.getInvoices().remove(invoice);
//				invoice.setClient(null);

				// Mejora
				client.removeInvoice(invoice);

				clientClienRepository.save(client);

				System.out.println(client);
			});
		});

	}

	@Transactional
	public void oneToManyInvoiceBiDireccionalFindById() {

		Optional<Client> optionalClient = clientClienRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
			Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientClienRepository.save(client);

			System.out.println(client);

		});

	}

	@Transactional
	public void oneToManyInvoiceBiDireccional() {

		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("Compras de la casa", 5000L);
		Invoice invoice2 = new Invoice("Compras de oficina", 8000L);

		List<Invoice> invoices = new ArrayList<>();
//		invoices.add(invoice1);
//		invoices.add(invoice2);
//		client.setInvoices(invoices);
//
//		invoice1.setClient(client);
//		invoice2.setClient(client);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientClienRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientClienRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			clientClienRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientClienRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				// Address address3 = c.getAddresses().get(1);
				c.getAddresses().remove(address2);
				clientClienRepository.save(c);
				System.out.println(c);
			});

		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientClienRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientClienRepository.findById(3L);
		optionalClient.ifPresent(c -> {

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);
//			Address address3 = c.getAddresses().get(1);

			c.getAddresses().remove(address1);
			clientClienRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientClienRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			client.setAddresses(addresses);

			Client clienteBD = clientClienRepository.save(client);

			System.out.println(clienteBD);

		});

	}

	@Transactional
	public void oneToMany() {

		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verejel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientClienRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOneFindByClient() {

		Optional<Client> optionalClient = clientClienRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow(null);
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);

			Invoice invoiceDb = invoiceRepository.save(invoice);
			System.out.println(invoiceDb);
		}

	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("Jonh", "Doe");
		clientClienRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);

		invoice.setClient(client);

		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);
	}

}
