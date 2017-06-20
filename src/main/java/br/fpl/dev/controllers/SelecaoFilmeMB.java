package br.fpl.dev.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.fpl.dev.entities.Filme;
import br.fpl.dev.services.FilmeServiceIF;

@ManagedBean
@SessionScoped
public class SelecaoFilmeMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183670266389529517L;

	@Inject
	private FilmeServiceIF service;

	private List<Filme> filmes;

	private String filmeSelecionado;

	/**
	 * Inicia a lista com todos os filmes do banco
	 */
	@PostConstruct
	public void init() {
		filmeSelecionado = new String();
		filmes = service.buscarTodosFilmes();
	}

	/**
	 * 
	 * @return página inicial
	 */
	public String voltar() {
		return "/index.jsf?faces-redirect=true";
	}
	
	/**
	 * 
	 * @return próx página
	 */
	public String proximo(){
		return "selecao-sessao.jsf?faces-redirect=true";
	}
	/**
	 * exibe a mensagem do filme selecionado
	 * 
	 * @param actionEvent
	 */
	public void exibirMensagem() {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Filme selecionado: ", filmeSelecionado));
	}

	public String getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(String filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

}
