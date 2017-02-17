package br.com.vendapedido.controller;
  
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.vendapedido.model.EnderecoEntrega;
import br.com.vendapedido.model.Pedido;

//@RequestScoped -> quando terminar a requisição o bean morre 
@Named
@ViewScoped // o bean so vai morrer quando der um refresh na pagina 
public class CadastroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L; // Quando a View é muito grande é necessario usar o Serializable
	
	private Pedido pedido;
	
	private List<Integer> itens;
	
	public CadastroPedidoBean() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public void salvar() {
	}

	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}