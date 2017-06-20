package br.fpl.dev.services;

import java.util.List;

import br.fpl.dev.entities.Sessao;

public interface SessaoServiceIF {
	
	public List<Sessao> buscarSessaoPorFilme(String titulo);
	
}
