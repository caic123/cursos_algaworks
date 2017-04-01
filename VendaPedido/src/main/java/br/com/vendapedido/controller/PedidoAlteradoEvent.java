package br.com.vendapedido.controller;

import br.com.vendapedido.model.Pedido;

public class PedidoAlteradoEvent {

private Pedido pedido;
	
	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

}
