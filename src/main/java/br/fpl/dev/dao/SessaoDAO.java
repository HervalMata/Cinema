package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Sessao;

@Stateless
public class SessaoDAO extends BaseDAO<Sessao>{
	public SessaoDAO(){
		super(Sessao.class);
	}
}
