package br.fpl.dev.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class IndexMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7191363173699444823L;
	
	
	public String fazerReserva(){
		return "/views/selecao-filme.jsf?faces-redirect=true";
	}
	
	public String verReserva(){
		return "/views/busca-reserva.jsf?faces-redirect=true";
	}
}
