//RECARREGA AS CATEGORIAS
package br.com.vendapedido.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.vendapedido.model.Categoria;

public class Categorias implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> raizes(){//Pesquisa para listar a categoria na comboBox
		return manager.createQuery("from Categoria where categoriaPai is null",
				Categoria.class).getResultList();
	}
	
	public List<Categoria> subcategoriasDe(Categoria categoriaPai){
		//O parametro :raiz é o parametro que vai receber o resultado
		return manager.createQuery("from Categoria where categoriaPai = :raiz", 
				Categoria.class)
				.setParameter("raiz", categoriaPai).getResultList();
	}
	
	public Categoria porId(Long id){
		return manager.find(Categoria.class, id);//Buscar um Objeto a partir do ID
	}

}
