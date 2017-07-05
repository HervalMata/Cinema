package br.fpl.dev.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Assento;
import br.fpl.dev.entities.Filme;
import br.fpl.dev.entities.Reserva;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.AssentoServiceIF;
import br.fpl.dev.services.FilmeServiceIF;
import br.fpl.dev.services.ReservaServiceIF;
import br.fpl.dev.services.SessaoServiceIF;

@Named
@FlowScoped(value = "selecao")
public class SelecaoBean implements Serializable {

	// Etapa filme
	@Inject
	private FilmeServiceIF filmeService;
	private List<Filme> filmes = new ArrayList<>();
	private long filmeSelecionado;
	private Filme filme;

	// Etapa Sessao
	@Inject
	private SessaoServiceIF sessaoService;
	private List<Sessao> sessoes;
	private long sessaoSelecionada;
	private Sessao sessao;

	// Etapa Assento
	@Inject
	private AssentoServiceIF assentoService;
	private List<Assento> assentos;
	private long assentoSelecionado;
	private Assento assento;

	// Etapa Reserva
	@Inject
	private ReservaServiceIF reservaService;
	private Reserva reserva;
	

	////////////////////////////////////
	// INICIALIZADORES
	////////////////////////////////////

	/**
	 * Inicia a view de seleção de filme
	 */
	public void initFilme() {
		buscarTodosFilmes();
	}

	/**
	 * Inicia a view de seleção de sessão
	 */
	public void initSessao() {
		buscarFilmeSelecionado();
		buscarSessoes();
	}

	/**
	 * Inicia a view de seleção de assento
	 */
	public void initAssento() {
		buscarSessaoSelecionada();
		buscarAssentos();
		definirAssentosOcupados();
	}
	
	/**
	 * Inicia a view de seleção de reserva
	 */
	public void initReserva(){
		buscarAssentoSelecionado();
		definirReserva();
	}

	//////////////////////////////////////////////
	// BUSCAR TODOS
	//////////////////////////////////////////////

	/**
	 * Método para buscar todos os filmes do banco
	 */
	public void buscarTodosFilmes() {
		filmes = filmeService.buscarTodosFilmes();
	}

	/**
	 * Método para buscar assentos
	 */
	public void buscarAssentos() {
		assentos = assentoService.buscarAssentos();
	}

	/////////////////////////////////////////////////
	// BUSCA POR ID
	/////////////////////////////////////////////////

	/**
	 * Método para buscar o filme pelo ID 
	 */
	public void buscarFilmeSelecionado() {
		filme = filmeService.buscarFilmePorId(filmeSelecionado);
	}
	
	/**
	 * Verifica quais as sessões para o filme selecionado
	 */
	public void buscarSessoes() {
		sessoes = sessaoService.buscarSessoesPorFilme(filmeSelecionado);
	}

	/**
	 * Método para buscar sessao pelo ID 
	 */
	public void buscarSessaoSelecionada() {
		sessao = sessaoService.buscarSessaoPorId(sessaoSelecionada);
	}
	
	/**
	 * Método para buscar assento pelo ID
	 */
	public void buscarAssentoSelecionado(){
		assento = assentoService.buscarAssentoPorId(assentoSelecionado);
	}

	/////////////////////////////////////////////
	// LÓGICA DE APLICAÇÃO
	/////////////////////////////////////////////
	
	/**
	 * exibe a mensagem do filme selecionado
	 */
	public void exibirMensagem() {
		Filme filme = filmeService.buscarFilmePorId(filmeSelecionado);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Filme Selecionado", filme.getTitulo()));
	}

	/**
	 * Método para definir os assentos ocupados na sessão selecionada
	 */
	public void definirAssentosOcupados() {
		List<Reserva> reservasDaSessao = reservaService.buscarReservasPorSessao(sessaoSelecionada);

		for (Reserva r : reservasDaSessao) {
			// faz a iteração das reservas da sessão

			for (Assento a : assentos) {
				// faz a iteração dos assentos que serão exibidos

				if (r.getAssento().getNumeracao().equals(a.getNumeracao())) {
					// Se o assento mostrado na tela ja estiver reservado para
					// esta sessão, aparece como indisponível

					a.setOcupado(true);
				}
			}
		}
	}
	
	/**
	 * Gera um código aleatório único
	 * @return código
	 */
	public String gerarCodigoParaReserva(){
		String codigo = reservaService.gerarCodigo();
		return codigo;
	}
	
	/**
	 * passa os valores para o objeto reserva
	 */
	public void definirReserva(){
		String codigo = gerarCodigoParaReserva();
		reserva = new Reserva();
		reserva.setSessao(sessao);
		reserva.setAssento(assento);
		reserva.setCodigo(codigo);
	}
	
	/**
	 * Salva a reservano banco de dados
	 */
	public void salvarReserva(){
		reservaService.salvarReserva(reserva);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("codigo", reserva.getCodigo());
		// Adiciona código para ser exibido na próximo view
	}
	

	/////////////////////////////////////////////
	// FINALIZADORES
	/////////////////////////////////////////////
	
	/**
	 * Saida do flow
	 * @return página de resultado
	 */
	public String finalizar() {
		return "finalizar";
	}
	
	/**
	 * Saida do flow
	 * @return página index
	 */
	public String voltar(){
		return "voltar";
	}


	// GETTERS & SETTERS: FILME
	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public long getFilmeSelecionado() {
		return filmeSelecionado;
	}

	public void setFilmeSelecionado(long filmeSelecionado) {
		this.filmeSelecionado = filmeSelecionado;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	// GETTERS & SETTERS: SESSÃO
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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	// GETTERS & SETTERS: ASSENTO
	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public long getAssentoSelecionado() {
		return assentoSelecionado;
	}

	public void setAssentoSelecionado(long assentoSelecionado) {
		this.assentoSelecionado = assentoSelecionado;
	}

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}
	
	// GETTERS & SETTERS: RESERVA
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
}
