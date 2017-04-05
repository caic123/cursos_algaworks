package br.com.vendapedido.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.service.CancelamentoPedidoService;
import br.com.vendapedido.util.jsf.FacesUtil;

@Named 
@RequestScoped
public class CancelamentoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Inject //quando o pedido for alterado ele faz um evento
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvento;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void cancelarPedido(){
		this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
		this.pedidoAlteradoEvento.fire(new PedidoAlteradoEvent(this.pedido));
		
		FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
	}
}
