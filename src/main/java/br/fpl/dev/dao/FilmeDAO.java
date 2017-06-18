package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Filme;

@Stateless
public class FilmeDAO extends BaseDAO<Filme>{
	public FilmeDAO(){
		super(Filme.class);
	}
}
