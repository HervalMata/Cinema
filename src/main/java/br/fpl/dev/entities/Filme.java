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
@Table(name="filme")
public class Filme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8091356281883409055L;
	
	// Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String imagem;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private int duracao;
	
	@Column(nullable=false)
	private String sinopse;
	
	@OneToMany(mappedBy="filme", targetEntity=Sessao.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Sessao> sessoes;

	
	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
}
