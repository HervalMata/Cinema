package br.fpl.dev.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.fpl.dev.dao.SessaoDAO;
import br.fpl.dev.entities.Sessao;

@Stateless
public class SessaoServiceImpl implements SessaoServiceIF {

	@Inject
	private SessaoDAO dao;
	
	public List<Sessao> buscarSessaoPorFilme(String titulo) {
		
		List<Sessao> todasSessoes = dao.buscarTodos();
		List<Sessao> sessoesFilme = new ArrayList<>();
		
		for (Sessao s : todasSessoes){
			
			if (s.getFilme().getTitulo().equals(titulo)){
				
				sessoesFilme.add(s);
			}
		}
		
		return sessoesFilme;
	}

}
