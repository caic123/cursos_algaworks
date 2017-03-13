package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.vendapedido.model.Categoria;
import br.com.vendapedido.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private Produto produto;
	
	private List<Categoria> categoriasRaizes;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}
	
	public void inicializar(){//VendaPU esta na persistence
		//ResultList() -> Retorna uma lista
		categoriasRaizes = manager.createQuery("from Categoria", Categoria.class).getResultList();
	}
	
	public void salvar() {
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getCategoriaRaizes() {
		return categoriasRaizes;
	}

}