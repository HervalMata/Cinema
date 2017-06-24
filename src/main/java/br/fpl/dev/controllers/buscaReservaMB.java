package br.fpl.dev.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.fpl.dev.entities.Reserva;
import br.fpl.dev.services.ReservaServiceIF;

@Named
@SessionScoped
public class BuscaReservaMB implements Serializable {

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

	/**
	 * Verifica se encontrou a reserva
	 * @return pagina de erro ou exibe a reserva
	 */
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
	
	/**
	 * busca a reserva no banco que tenha o mesmo código que foi passado
	 */
	public void buscarReserva() {
		reserva = service.buscarReservaPorCodigo(codigo);
		resetarCampo();
	}
	
	
	/**
	 * reseta o campo de pesquisa
	 */
	public void resetarCampo() {
		codigo = new String();
	}

	/**
	 * 
	 * @return página inicial
	 */
	public String voltar() {
		
		zerarSessao();
		return "/index.jsf?faces-redirect=true";
	}
	
	public void zerarSessao(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public String voltarPesquisa(){
		zerarSessao();
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