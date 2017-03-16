//Responsavel pelas regras de Negocio de Cadastro de produto / alteraçã/ exclusão/ dentre outras 
package br.com.vendapedido.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vendapedido.service.NegocioException;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Produtos;

// Usa-se serializable porque vai injetar o BEAN DENTRO do controlador que é do tipo @ViewScoped
public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
 
	@Inject
	private Produtos produtos;
	
	//Recebe o produto que é para salvar
	public Produto salvar(Produto produto){
		
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}
}
