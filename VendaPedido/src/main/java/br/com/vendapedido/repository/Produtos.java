package br.com.vendapedido.repository;

import java.awt.List;
import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.filter.ProdutoFilter;
import br.com.vendapedido.service.NegocioException;
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
	
	@Transactional // para chamar esse metodo deve ter uma transação
	public void remover(Produto produto){
		try{
		produto = porId(produto.getId());
		manager.remove(produto);//so vai ser executado quando chamar o flush() 
		manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluido.");
		}
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
	
	@SuppressWarnings("unchecked")
	public java.util.List<Produto> filtrados(ProdutoFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		//StringUtils.isNotBlank -> Verifica se uma string que eu passar como parametro não é vazia e nem nula
		if(StringUtils.isNotBlank(filtro.getSku())){
		//Restrictions = equals
		criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())){
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}
	
}
