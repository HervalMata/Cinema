package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Sala;

@Stateless
public class SalaDAO extends BaseDAO<Sala>{
	public SalaDAO(){
		super(Sala.class);
	}
}
