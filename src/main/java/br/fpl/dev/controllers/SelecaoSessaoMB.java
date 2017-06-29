package br.fpl.dev.controllers;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Filme;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.FilmeServiceIF;
import br.fpl.dev.services.SessaoServiceIF;

@Named
@SessionScoped
public class SelecaoSessaoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1894950326936854406L;
	
	@Inject
	private SessaoServiceIF sessaoService;
	
	@Inject
	private FilmeServiceIF filmeService;
	
	@Inject
	private SelecaoFilmeMB selecaoFilme;
	
	private List<Sessao> sessoes;
	
	private long sessaoSelecionada;
	
	private Filme filmeSelecionado;
	
	/**
	 * Método para exibir os resultados do MB
	 */
	public void gerarSessao(){
		buscarFilmeSelecionado();
		buscarSessoes();
	}
	
	/**
	 * Método para buscar o filme pelo ID selecionado
	 */
	public void buscarFilmeSelecionado(){
		filmeSelecionado = filmeService.buscarFilmePorId(selecaoFilme.getFilmeSelecionado());
	}
	
	/**
	 * Verifica quais as sessões para o filme selecionado
	 */
	public void buscarSessoes(){
		sessoes = sessaoService.buscarSessoesPorFilme(selecaoFilme.getFilmeSelecionado());

	}
	
	/**
	 * 
	 * @return página anterior
	 */
	public String voltar(){
		return "selecao-filme.jsf?faces-redirect=true";
	}
	
	/**
	 *  Verifica se a sessão foi selecionado, caso não tenha sido exibe a mensagem
	 * @return próxima página
	 */
	public String proximo(){
		
		if (sessaoSelecionada == 0){
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha uma sessão!","É necessário escolher uma sessão para prosseguir."));
			return null;
		}
		
		return "selecao-assento.jsf?faces-redirect=true";
	}
	
	
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public long getSessaoSelecionada() {
		return sessaoSelecionada;
	}

	public void setSessaoSelecionada(long sessaoSelecionada) {
		this.sessaoSelecionada = sessaoSelecionada;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}
	
}