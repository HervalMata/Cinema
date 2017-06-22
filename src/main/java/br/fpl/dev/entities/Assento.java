package br.fpl.dev.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="assento")
public class Assento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 108624197000141986L;
	
	// Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String numeracao;
	
	@Column(nullable=false)
	private boolean ocupado;
	
	@OneToMany(mappedBy="assento", targetEntity=Reserva.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Reserva> reservas;

	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNumeracao() {
		return numeracao;
	}
	public void setNumeracao(String numeracao) {
		this.numeracao = numeracao;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
