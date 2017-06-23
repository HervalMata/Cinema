package br.fpl.dev.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.fpl.dev.entities.Assento;
import br.fpl.dev.entities.Reserva;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.AssentoServiceIF;
import br.fpl.dev.services.ReservaServiceIF;

@ManagedBean
@SessionScoped
public class SelecaoReservaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7330520234959704692L;
	
	@Inject
	private AssentoServiceIF assentoService;
	
	@Inject
	private ReservaServiceIF reservaService;
	
	@ManagedProperty(value = "#{selecaoAssentoMB}")
	private SelecaoAssentoMB selecaoAssento;
	
	private Sessao sessao;
	
	private Assento assento;
	
	private Reserva reserva;
	
	private String codigo;
	
	private SimpleDateFormat sdf;
	
	@PostConstruct
	public void init(){
		sdf = new SimpleDateFormat("HH:mm"); // converte HH:mm:ss em HH:mm
	}
	
	/**
	 * Gera a reserva pela Sessão e o assento selecionado
	 */
	public void gerarReserva(){
		sessao = selecaoAssento.getSessaoSelecionada();
		buscarAssentoSelecionado();
	}
	
	/**
	 * busca o assento que foi selecionado no ultimo MB
	 */
	public void buscarAssentoSelecionado(){
		assento = assentoService.buscarAssentoPorId(selecaoAssento.getAssentoSelecionado());
	}
	
	public void gerarCodigoParaReserva(){
		codigo = reservaService.gerarCodigo();
	}
	
	public String confirmarReserva(){
		
		gerarCodigoParaReserva();
		
		reserva = new Reserva();
	
		reserva.setSessao(sessao);
		reserva.setAssento(assento);
		reserva.setCodigo(codigo);
		
		reservaService.salvarReserva(reserva);
		
		return "resultado/confirmacao.jsf?faces-redirect=true";
	}
	
	/**
	 * 
	 * @return página anterior
	 */
	public String voltar(){
		return "selecao-assento.jsf?faces-redirect=true";
	}
	

	public SelecaoAssentoMB getSelecaoAssento() {
		return selecaoAssento;
	}

	public void setSelecaoAssento(SelecaoAssentoMB selecaoAssento) {
		this.selecaoAssento = selecaoAssento;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
