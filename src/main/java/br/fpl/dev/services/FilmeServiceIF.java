package br.fpl.dev.services;

import java.util.List;

import br.fpl.dev.entities.Filme;

public interface FilmeServiceIF {
	
	public List<Filme> buscarTodosFilmes();
	
	public Filme buscarFilmePorId(long id);
}
