package br.fpl.dev.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="horario")
public class Horario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7412336732380722005L;
	
	// Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable=false)
	private Date hora;
	
	@OneToMany(mappedBy="horario", targetEntity=Sessao.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Sessao> sessoes;

	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Date getHora() {
		return hora;
	}
	public void setHora(Date hora) {
		this.hora = hora;
	}
	
}
