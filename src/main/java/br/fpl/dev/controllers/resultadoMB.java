package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@SessionScoped
public class ResultadoMB implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 606776800474065113L;
	
	private String codigo;
	
	private boolean finalizado;
	
	/**
	 * sinaliza que a reserva foi finalizada passando o valor de true para a variavel
	 */
	public void finalizarReserva(){
		this.finalizado = true;
	}
	
	/**
	 * encerra a sessão
	 */
	public void zerarSessao(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	/**
	 * encerra a sessão
	 * @return pagina inicial
	 */
	public String voltar(){
		zerarSessao();
		return "/index.jsf?faces-redirect=true";
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
}
