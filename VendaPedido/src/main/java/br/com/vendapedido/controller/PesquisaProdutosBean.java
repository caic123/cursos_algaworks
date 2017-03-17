package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Produtos;
import br.com.vendapedido.repository.filter.ProdutoFilter;
import br.com.vendapedido.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private ProdutoFilter filtro;
	private List<Produto> produtosFiltrados;
	
	private Produto produtoSelecionado;
	
	public void pesquisar(){
		produtosFiltrados = produtos.filtrados(filtro);
	}
	
	public void excluir(){
		produtos.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		
		FacesUtil.addInfoMessage("Produto "+ produtoSelecionado.getSku()
				+ " excluído com sucesso.");
	}
	
	
	
	//*******************GET E SET****************************************************************************************************************************//
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ProdutoFilter filtro) {
		this.filtro = filtro;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}





	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}





	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
}