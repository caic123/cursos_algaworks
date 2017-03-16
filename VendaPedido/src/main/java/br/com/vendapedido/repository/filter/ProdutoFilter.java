//VAI SER UTILIZADO PARA O FILTRO DE PESQUISA DE PRODUTO

package br.com.vendapedido.repository.filter;

import java.io.Serializable;

import br.com.vendapedido.validation.SKU;

public class ProdutoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;
	private String nome;
	
	@SKU// pega a validação do sku -> EX."aa9999"
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();//evitar nullPointException
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
