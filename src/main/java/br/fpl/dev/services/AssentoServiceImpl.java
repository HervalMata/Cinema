package br.fpl.dev.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.fpl.dev.dao.AssentoDAO;
import br.fpl.dev.entities.Assento;

@Stateless
public class AssentoServiceImpl implements AssentoServiceIF{
	
	@Inject
	private AssentoDAO dao;

	@Override
	public List<Assento> buscarAssentos() {
		return dao.buscarTodos();
	}

	public Assento buscarAssentoPorId(long id) {
		return dao.buscarPorId(id);
	}
	
}
