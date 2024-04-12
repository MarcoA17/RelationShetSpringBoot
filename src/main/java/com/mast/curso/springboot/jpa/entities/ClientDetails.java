package com.mast.curso.springboot.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clients_details")
public class ClientDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean premium;
	private Integer points;

	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	public ClientDetails() {
		// TODO Auto-generated constructor stub
	}

	public ClientDetails(boolean premium, Integer points) {
		super();
		this.premium = premium;
		this.points = points;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the premium
	 */
	public boolean isPremium() {
		return premium;
	}

	/**
	 * @param premium the premium to set
	 */
	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the client
	 */
//	public Client getClient() {
//		return client;
//	}
//
//	/**
//	 * @param client the client to set
//	 */
//	public void setClient(Client client) {
//		this.client = client;
//	}

	@Override
	public String toString() {
		return "{id=" + id + ", premium=" + premium + ", points=" + points + "}";
	}

}
