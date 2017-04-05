package br.com.vendapedido.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vendapedido.service.CadastroPedidoService;
import br.com.vendapedido.service.EstoqueService;
import br.com.vendapedido.service.NegocioException;
import br.com.vendapedido.util.jpa.Transactional;
import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.model.StatusPedido;
import br.com.vendapedido.repository.Pedidos;

public class EmissaoPedidoService implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com status "
					+ pedido.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}
}
