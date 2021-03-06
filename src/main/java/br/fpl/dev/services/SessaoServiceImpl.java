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
	
	public List<Sessao> buscarSessoesPorFilme(long id) {
		
		List<Sessao> todasSessoes = dao.buscarTodos();
		List<Sessao> sessoesFilme = new ArrayList<>();
		
		for (Sessao s : todasSessoes){
			
			if (s.getFilme().getId() == id){
				
				sessoesFilme.add(s);
			}
		}
		
		return sessoesFilme;
	}
	
	public Sessao buscarSessaoPorId(long id){
		return dao.buscarPorId(id);
	}

}