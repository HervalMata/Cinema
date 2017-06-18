package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Reserva;

@Stateless
public class ReservaDAO extends BaseDAO<Reserva>{
	public ReservaDAO(){
		super(Reserva.class);
	}
}
