package br.com.vendapedido.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.util.jpa.Transactional;

public class Produtos implements Serializable{

	/** 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject// Como se eu ja estiversse instanciado. ex: EntityManager manager = new EntityManager();
	private EntityManager manager;

	@Transactional
	public Produto guardar(Produto produto) {
		
		return  manager.merge(produto);
	}
	
	public Produto porSku(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
