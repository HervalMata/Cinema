package br.fpl.dev.dao;

import javax.ejb.Stateless;

import br.fpl.dev.entities.Horario;

@Stateless
public class HorarioDAO extends BaseDAO<Horario>{
	public HorarioDAO(){
		super(Horario.class);
	}
}
