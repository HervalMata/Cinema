package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Assento;
import br.fpl.dev.entities.Reserva;
import br.fpl.dev.entities.Sessao;
import br.fpl.dev.services.AssentoServiceIF;
import br.fpl.dev.services.ReservaServiceIF;

@Named
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
	
	@Inject
	private SelecaoAssentoMB selecaoAssento;
	
	@Inject
	private ResultadoMB resultado;
	
	private Sessao sessao;
	
	private Assento assento;
	
	private Reserva reserva;
	
	private String codigo;
	
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
	
	
	/**
	 * Gera o código da reserva
	 * passa os valores para o objeto reserva
	 * insere no banco
	 * passa o valor do código para outro MB
	 * finaliza a reserva
	 * @return pagina de confirmação
	 */
	public String confirmarReserva(){
		
		gerarCodigoParaReserva();
		
		reserva = new Reserva();
	
		reserva.setSessao(sessao);
		reserva.setAssento(assento);
		reserva.setCodigo(codigo);
		
		reservaService.salvarReserva(reserva);
		
		resultado.setCodigo(codigo);
		resultado.finalizarReserva();
		
		return "resultado/confirmacao.jsf?faces-redirect=true";
	}
	
	/**
	 * 
	 * @return página anterior
	 */
	public String voltar(){
		return "selecao-assento.jsf?faces-redirect=true";
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
