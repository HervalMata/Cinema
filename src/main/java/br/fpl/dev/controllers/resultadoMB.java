package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.fpl.dev.entities.Reserva;

@ManagedBean
@SessionScoped
public class resultadoMB implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 606776800474065113L;
	
	@ManagedProperty(value = "#{selecaoReservaMB}")
	private SelecaoReservaMB selecaoReserva;
	
	private Reserva reserva;
	
	
	public void buscarDadosDaReserva(){
		reserva = selecaoReserva.getReserva();
	}
	
	@PostConstruct
	public void init(){
		buscarDadosDaReserva();
	}
	
	public void zerarSessao(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public String voltar(){
		
		zerarSessao();
		return "/index.jsf?faces-redirect=true";
	}
	
	
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public SelecaoReservaMB getSelecaoReserva() {
		return selecaoReserva;
	}

	public void setSelecaoReserva(SelecaoReservaMB selecaoReserva) {
		this.selecaoReserva = selecaoReserva;
	}

	
}
