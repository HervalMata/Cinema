package br.fpl.dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDAO<T> {
	
	@PersistenceContext(unitName="cinemaPU")
	private EntityManager em;
	
	public Class<T> tipo;
	
	public BaseDAO(){ // Constructor
		
	}
	
	public BaseDAO(Class<T> tipo){ // Full Constructor
		this.tipo = tipo;
	}
	
	/**
	 * Método para salvar um objeto no banco
	 * @param tipo
	 */
	public void salvar(T tipo){ // INSERT
		try{
			em.persist(tipo);
		} catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Método para buscar todos os objetos do banco
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(){ // SELECT
		StringBuilder sql = new StringBuilder();
		sql.append("FROM ").append(tipo.getSimpleName());
		
		return em.createQuery(sql.toString()).getResultList();
	}
	
	/**
	 * Método para atualizar um objeto no banco
	 * @param tipo
	 */
	public void atualizar(T tipo){ // UPDATE
		try{
			em.merge(tipo);
		} catch (Exception e){
			throw e;
		}
	}
	
	/**
	 * Método para deletar um objeto no banco
	 * @param tipo
	 */
	public void deletar(T tipo){ // DELETE
		try{
			em.remove(tipo);
		} catch (Exception e){
			throw e;
		}
	}

}
