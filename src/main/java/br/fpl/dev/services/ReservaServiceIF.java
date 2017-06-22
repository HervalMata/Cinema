package br.fpl.dev.services;

import java.util.List;

import br.fpl.dev.entities.Reserva;

public interface ReservaServiceIF {
	
	public List<Reserva> buscarReservasPorSessao(long sessaoId);
	
}
