package br.com.vendapedido.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vendapedido.util.jpa.Transactional;

import br.com.vendapedido.model.ItemPedido;
import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.repository.Pedidos;

public class EstoqueService implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
}
