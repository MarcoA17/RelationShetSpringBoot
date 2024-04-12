package com.mast.curso.springboot.jpa.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String lastname;

	@OneToOne (fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_clients_details")
	private ClientDetails clientDetails;

	// @JoinColumn(name = "client_id")// colocala el foreing king en la tabla de la
	// relacion
	// @JoinColumn(name = "client_id")
	// @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch =
	// FetchType.EAGER)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "tbl_clientes_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_direcciones"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"id_direcciones" }))
	private Set<Address> addresses;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
	private Set<Invoice> invoices;

	public Client() {
		addresses = new HashSet<>();
		invoices = new HashSet<>();
	}

	public Client(String name, String lastname) {
		this();
		this.name = name;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Client addInvoice(Invoice invoice) {
		invoices.add(invoice);
		invoice.setClient(this);
		return this;
	}

	public void removeInvoice(Invoice invoice) {
		this.getInvoices().remove(invoice);
		invoice.setClient(null);
	}

	/**
	 * @return the addresses
	 */
	public Set<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the invoices
	 */
	public Set<Invoice> getInvoices() {
		return invoices;
	}

	/**
	 * @param invoices the invoices to set
	 */
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	/**
	 * @return the clientDetails
	 */
	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	/**
	 * @param clientDetails the clientDetails to set
	 */
	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
	}

	@Override
	public String toString() {
		return "{id=" + id + 
				", name=" + name + 
				", lastname=" + lastname + 
				", invoices=" + invoices + 
				", addresses=" + addresses + 
				", clientDetails=" + clientDetails + 
				"}";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addresses, id, lastname, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(addresses, other.addresses) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(name, other.name);
	}

}
