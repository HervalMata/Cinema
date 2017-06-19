package br.fpl.dev.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.fpl.dev.dao.FilmeDAO;
import br.fpl.dev.entities.Filme;

@Stateless
public class FilmeServiceImpl implements FilmeServiceIF{
	
	@Inject
	private FilmeDAO dao;

	public List<Filme> buscarTodosFilmes() {
		return dao.buscarTodos();
	}
}
