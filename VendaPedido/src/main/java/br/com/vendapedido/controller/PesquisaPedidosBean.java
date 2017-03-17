package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.model.StatusPedido;
import br.com.vendapedido.repository.Pedidos;
import br.com.vendapedido.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;
	
	private PedidoFilter filtro;
	private List<Pedido> pedidosFiltrados;
	
	public PesquisaPedidosBean() {
		filtro = new PedidoFilter();
		pedidosFiltrados = new ArrayList<>();
	}
	
	public void pesquisar(){
		pedidosFiltrados = pedidos.filtrados(filtro);
	}

	public StatusPedido[] getStatuses(){
		return StatusPedido.values();
	}
	
	//****************GET E SET********************************************************************************************//
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
	public PedidoFilter getFiltro(){
		return filtro;
	}
	
}