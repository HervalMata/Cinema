package br.fpl.dev.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Assento;
import br.fpl.dev.entities.Reserva;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.AssentoServiceIF;
import br.fpl.dev.services.ReservaServiceIF;
import br.fpl.dev.services.SessaoServiceIF;

@Named
@SessionScoped
public class SelecaoAssentoMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6788685215710490659L;

	@Inject
	private SessaoServiceIF sessaoService;
	
	@Inject
	private AssentoServiceIF assentoService;
	
	@Inject
	private ReservaServiceIF reservaService;
	
	@Inject
	private SelecaoSessaoMB selecaoSessao;

	private List<Assento> assentos;

	private long assentoSelecionado;

	private Sessao sessaoSelecionada;
	
	private SimpleDateFormat sdf;

	@PostConstruct
	public void init() {
		sdf = new SimpleDateFormat("HH:mm"); // converte HH:mm:ss em HH:mm
	}
	
	/**
	 * Método para exibir os resultados do MB
	 */
	public void gerarAssento(){
		buscarSessaoSelecionada();
		buscarAssentos();
		definirAssentosOcupados();
	}

	/**
	 * Método para buscar sessao pelo ID da sessão selecionada
	 */
	public void buscarSessaoSelecionada() {
		
		sessaoSelecionada = sessaoService.buscarSessaoPorId(selecaoSessao.getSessaoSelecionada());
	}
	
	/**
	 * Método para buscar assentos
	 */
	public void buscarAssentos(){
		assentos = assentoService.buscarAssentos();
	}
	
	/**
	 * Método para definir os assentos que já estão ocupados na sessão selecionada
	 */
	public void definirAssentosOcupados(){
		List<Reserva> reservasDaSessao = reservaService.buscarReservasPorSessao(selecaoSessao.getSessaoSelecionada());
		
		for (Reserva r : reservasDaSessao){
			//ordena as reservas da sessão
			
			for (Assento a : assentos){
				//ordena os assentos que serão exibidos
				
				if (r.getAssento().getNumeracao().equals(a.getNumeracao())){
					//Se o assento mostrado na tela ja estiver reservado para esta sessão, aparece como indisponível
					
					a.setOcupado(true);
				}
				
			}
		}
	}
	
	/**
	 * 
	 * @return página anterior
	 */
	public String voltar(){
		return "selecao-sessao.jsf?faces-redirect=true";
	}
	
	/**
	 * 
	 * @return próxima página
	 */
	public String proximo(){
		
		if (assentoSelecionado == 0){
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Escolha um assento!", "É necessário escolher um assento para prosseguir."));
			return null;
		}
		
		return "selecao-reserva.jsf?faces-redirect=true";
	}
	
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public long getAssentoSelecionado() {
		return assentoSelecionado;
	}

	public void setAssentoSelecionado(long assentoSelecionado) {
		this.assentoSelecionado = assentoSelecionado;
	}

	public List<Assento> getAssentos() {
		return assentos;
	}

	public void setAssentos(List<Assento> assentos) {
		this.assentos = assentos;
	}

	public Sessao getSessaoSelecionada() {
		return sessaoSelecionada;
	}

	public void setSessaoSelecionada(Sessao sessaoSelecionada) {
		this.sessaoSelecionada = sessaoSelecionada;
	}

	public SelecaoSessaoMB getSelecaoSessao() {
		return selecaoSessao;
	}

	public void setSelecaoSessao(SelecaoSessaoMB selecaoSessao) {
		this.selecaoSessao = selecaoSessao;
	}

}