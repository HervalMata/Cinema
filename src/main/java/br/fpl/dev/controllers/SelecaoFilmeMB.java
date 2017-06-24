package br.fpl.dev.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Filme;
import br.fpl.dev.services.FilmeServiceIF;

@Named
@SessionScoped
public class SelecaoFilmeMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1183670266389529517L;

	@Inject
	private FilmeServiceIF service;

	private List<Filme> filmes;

	private long filmeSelecionado;

	/**
	 * Inicia a lista com todos os filmes do banco
	 */
	@PostConstruct
	public void init() {
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
	 *  Verifica se o filme foi selecionado, caso não tenha sido exibe a mensagem
	 * @return próx página
	 */
	public String proximo(){
		
		if (filmeSelecionado == 0){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha um filme!", "É necessário escolher um filme para prosseguir."));
			return null;
		}
		
		return "selecao-sessao.jsf?faces-redirect=true";
	}
	/**
	 * exibe a mensagem do filme selecionado
	 */
	public void exibirMensagem() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		Filme filme = service.buscarFilmePorId(filmeSelecionado);
		context.addMessage(null, new FacesMessage("Filme selecionado: ", filme.getTitulo()));
	}

	public long getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(long filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

}