//Responsavel pelas regras de Negocio de Cadastro de produto / alteraçã/ exclusão/ dentre outras 
package br.com.vendapedido.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Produtos;
import br.com.vendapedido.util.jpa.Transactional;

// Usa-se serializable porque vai injetar o BEAN DENTRO do controlador que é do tipo @ViewScoped
public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
 
	@Inject
	private Produtos produtos;
	
	//Recebe o produto que é para salvar
	@Transactional // Esse netodo vai ser executado dentro da transação "Transaction" do pacote jpa
	public Produto salvar(Produto produto){ //Metodo usado para SALVAR e EDIÇÃO
		
		Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}
}
