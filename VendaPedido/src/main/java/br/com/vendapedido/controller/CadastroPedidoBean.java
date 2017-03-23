package br.com.vendapedido.controller;
  
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendapedido.model.Cliente;
import br.com.vendapedido.model.EnderecoEntrega;
import br.com.vendapedido.model.FormaPagamento;
import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.model.Usuario;
import br.com.vendapedido.repository.Clientes;
import br.com.vendapedido.repository.Usuarios;
import br.com.vendapedido.service.CadastroPedidoService;
import br.com.vendapedido.util.jsf.FacesUtil;

//@RequestScoped -> quando terminar a requisição o bean morre 
@Named
@ViewScoped // o bean so vai morrer quando der um refresh na pagina 
public class CadastroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L; // Quando a View é muito grande é necessario usar o Serializable
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	Clientes clientes;
	
	@Inject
	CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	private List<Integer> itens;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			this.vendedores = this.usuarios.vendedores();
		}
	}
	
	public List<Cliente> completarCliente(String nome){
		return this.clientes.porNome(nome);
	}
	
	private void limpar(){
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}

	public FormaPagamento[] getFormasPagamento(){
		return FormaPagamento.values();
	}
	
	//*****************GET E SET************************************************************************************************************//
	public Pedido getPedido() {
		return pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public List<Integer> getItens() {
		return itens;
	}

	public void setItens(List<Integer> itens) {
		this.itens = itens;
	}
	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}



}