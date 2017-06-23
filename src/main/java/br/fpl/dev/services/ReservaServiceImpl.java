package br.fpl.dev.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.fpl.dev.dao.ReservaDAO;
import br.fpl.dev.entities.Reserva;

@Stateless
public class ReservaServiceImpl implements ReservaServiceIF {

	@Inject
	private ReservaDAO dao;

	public List<Reserva> buscarReservasPorSessao(long sessaoId) {

		List<Reserva> todasReservas = dao.buscarTodos();
		List<Reserva> reservasSessao = new ArrayList<>();

		for (Reserva r : todasReservas) {

			if (r.getSessao().getId() == sessaoId) {
				reservasSessao.add(r);
			}

		}

		return reservasSessao;
	}

	public void salvarReserva(Reserva reserva) {
		dao.salvar(reserva);
	}

	public String gerarCodigo() {

		List<Reserva> todasReservas = dao.buscarTodos();

		java.util.Random random = new java.util.Random();

		// Opções alfanumericas para compor o codigo
		char[] alfanumerico = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'x', 'w', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7',
				'8', '9' };

		StringBuilder sequencia = new StringBuilder();
		String codigo = new String();

		for (int i = 0; i < 10; i++) {
			sequencia.append(alfanumerico[random.nextInt(alfanumerico.length)]);
		}

		codigo = sequencia.toString();

		// Entra na lista de reservas para ver os códigos existentes
		for (Reserva r : todasReservas) {

			while(r.getCodigo().equals(codigo)){
				
				for (int i = 0; i <= 10; i++) {
					sequencia.append(alfanumerico[random.nextInt(alfanumerico.length)]);
				}
				
				codigo = sequencia.toString();
			}
		}

		return codigo;
	}

	public Reserva buscarReservaPorCodigo(String codigo) {
		
		List<Reserva> todasReservas = dao.buscarTodos();
		
		for (Reserva r : todasReservas){
			
			if(r.getCodigo().equals(codigo)){
				
				return r;
			}
		}
		
		return null;
	}
	
	

}
