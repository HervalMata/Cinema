package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Reserva;
import br.fpl.dev.services.ReservaServiceIF;

@Named
@RequestScoped
public class ResultadoPesquisaBean implements Serializable {

	@Inject
	private ReservaServiceIF service;

	private String codigo;
	private Reserva reserva;
	
	/**
	 * busca a reserva no banco que tenha o mesmo código que foi passado
	 */
	public void buscarReserva() {
		reserva = service.buscarReservaPorCodigo(codigo);
	}
	
	/**
	 * Exibe o resultado da pesquisa realizada
	 * @return mensagem de erro ou reserva encontrada
	 */
	public String exibirResultado() {
		
		buscarReserva();
		
		if (reserva != null) {
			return "/resultado/resultado-pesquisa.xhtmlfaces-redirect=true";
			// exibe a reserva encontrada
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma Reserva encontrada", ""));
			// exibe a mensagem de erro
			return null;
		}

	}

	// GETTERS & SETTERS: CÓDIGO
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	// GETTERS & SETTERS: RESERVA
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
