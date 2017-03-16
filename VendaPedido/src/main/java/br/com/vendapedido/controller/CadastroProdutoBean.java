package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import br.com.vendapedido.model.Categoria;
import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Categorias;
import br.com.vendapedido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Categorias categorias;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean(){
		produto = new Produto();
	}
	
	public void inicializar(){//VendaPU esta na persistence
		//ResultList() -> Retorna uma lista
		//Verifica se a pagina não e postback -> se clicar no botão a pagina so vai carregar uma vez
		if(FacesUtil.isNotPostback()){
			categoriasRaizes = categorias.raizes();
		}
	}
	
	public void carregarSubcategorias(){
		setSubcategorias(categorias.subcategoriasDe(categoriaPai));
	}
	
	public void salvar() {
		System.out.println("Categoria pai selecionada: "+ categoriaPai.getDescricao());
		System.out.println("SubCategoria pai selecionada: "+ produto.getCategoria().getDescricao());
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

	@NotNull //Obrigar a seleção de categoria Pai
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

}