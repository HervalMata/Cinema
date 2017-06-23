package br.fpl.dev.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.fpl.dev.entities.Reserva;
import br.fpl.dev.services.ReservaServiceIF;

@ManagedBean
@SessionScoped
public class buscaReservaMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2203896128979011776L;

	@Inject
	private ReservaServiceIF service;

	private String codigo;

	private Reserva reserva;

	private SimpleDateFormat sdf;

	@PostConstruct
	public void init() {
		sdf = new SimpleDateFormat("HH:mm"); // converte HH:mm:ss em HH:mm
	}


	public String exibirResultado() {
		
		buscarReserva();
		
		if (reserva == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhuma Reserva encontrada", ""));
					resetarCampo();
					return null;
		} else {
			return "resultado/resultado-pesquisa.jsf?faces-redirect=true";
		}
		
		
	}

	public void buscarReserva() {
		reserva = service.buscarReservaPorCodigo(codigo);
		resetarCampo();
	}
	
	
	
	public void resetarCampo() {
		codigo = new String();
	}

	/**
	 * 
	 * @return página inicial
	 */
	public String voltar() {
		return "/index.jsf?faces-redirect=true";
	}
	
	public String voltarPesquisa(){
		return "/views/busca-reserva.jsf?faces-redirect=true";
	}
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	

}