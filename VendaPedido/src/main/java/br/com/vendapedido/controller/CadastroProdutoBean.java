package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named; 
import javax.validation.constraints.NotNull;

import br.com.vendapedido.model.Categoria;
import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Categorias;
import br.com.vendapedido.service.CadastroProdutoService;
import br.com.vendapedido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject //Como se eu ja estiversse instanciado. ex: Categorias categorias = new Categorias();
	private Categorias categorias;
	
	@Inject //Como se eu ja estiversse instanciado. ex: CadastroProdutoService cadastroProdutoService = new CadastroProdutoService();
	private CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria categoriaPai;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	
	public CadastroProdutoBean(){
		limpar();
	}
	
	public void inicializar(){//VendaPU esta na persistence
		//ResultList() -> Retorna uma lista
		//Verifica se a pagina não e postback -> se clicar no botão a pagina so vai carregar uma vez
		if(FacesUtil.isNotPostback()){
			categoriasRaizes = categorias.raizes();
			
			if(this.categoriaPai != null){
				carregarSubcategorias();
			}
		}
	}
	  
	public void carregarSubcategorias(){
		setSubcategorias(categorias.subcategoriasDe(categoriaPai));
	}
	
	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		//A mensagem vai aparecer assim qeu terminar o processo
		FacesUtil.addInfoMessage("Produto Salvo com sucesso!");
	}
	
	private void limpar(){
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}
	
	//Se o id não for nulo é porque estou editando 
	//Metodo usado para mudar o nome "Novo Produto" ao fazer a edição
	public boolean isEditando(){
		return this.produto.getId() != null;
	}
//****************************************GET E SET*******************************************************************************************************//	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto != null){
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
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