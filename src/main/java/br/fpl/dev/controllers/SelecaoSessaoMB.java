package br.fpl.dev.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.fpl.dev.entities.Filme;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.FilmeServiceIF;
import br.fpl.dev.services.SessaoServiceIF;

@ManagedBean
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
	
	// Busca o valor do filme selecionado do outro MB
	@ManagedProperty(value="#{selecaoFilmeMB}")
	private SelecaoFilmeMB selecaoFilme;
	
	private List<Sessao> sessoes;
	
	private long sessaoSelecionada;
	
	private Filme filmeSelecionado;
	
	private SimpleDateFormat sdf;
	
	@PostConstruct
	public void init(){
		sdf = new SimpleDateFormat("HH:mm"); // converte HH:mm:ss em HH:mm
	}
	
	
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
	 * 
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

	public SelecaoFilmeMB getSelecaoFilme() {
		return selecaoFilme;
	}

	public void setSelecaoFilme(SelecaoFilmeMB selecaoFilme) {
		this.selecaoFilme = selecaoFilme;
	}

	public long getSessaoSelecionada() {
		return sessaoSelecionada;
	}

	public void setSessaoSelecionada(long sessaoSelecionada) {
		this.sessaoSelecionada = sessaoSelecionada;
	}
	
	

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public Filme getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(Filme filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}
	
}