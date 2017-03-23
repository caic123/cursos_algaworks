package br.com.vendapedido.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.model.StatusPedido;
import br.com.vendapedido.repository.Pedidos;
import br.com.vendapedido.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {

			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}
}
