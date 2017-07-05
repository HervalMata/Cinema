package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

@Named
@RequestScoped
public class ResultadoReservaBean implements Serializable {
	
	private String codigo;

	@PostConstruct
	public void init(){
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		codigo = flash.get("codigo").toString();
	}
	
	// GETTERS & SETTERS: CÓDIGO
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
