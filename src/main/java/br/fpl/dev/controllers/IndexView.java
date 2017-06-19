package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IndexView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7191363173699444823L;
	
	
	public String fazerReserva(){
		return "/views/selecao-filme.jsf?faces-redirect=true";
	}
}
