package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Assento;

@Stateless
public class AssentoDAO extends BaseDAO<Assento> {
	public AssentoDAO(){
		super(Assento.class);
	}
}
