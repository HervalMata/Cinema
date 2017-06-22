package br.fpl.dev.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.fpl.dev.dao.ReservaDAO;
import br.fpl.dev.entities.Reserva;

@Stateless
public class ReservaServiceImpl implements ReservaServiceIF{
	
	@Inject
	private ReservaDAO dao;

	public List<Reserva> buscarReservasPorSessao(long sessaoId) {
		
		List<Reserva> todasReservas = dao.buscarTodos();
		List<Reserva> reservasSessao = new ArrayList<>();
		
		for (Reserva r: todasReservas){
			
			if (r.getSessao().getId() == sessaoId){
				reservasSessao.add(r);
			}
			
		}
		
		return reservasSessao;
	}

}
