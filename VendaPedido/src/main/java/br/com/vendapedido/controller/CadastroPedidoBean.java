package br.com.vendapedido.controller;
  
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.vendapedido.model.Cliente;
import br.com.vendapedido.model.EnderecoEntrega;
import br.com.vendapedido.model.FormaPagamento;
import br.com.vendapedido.model.ItemPedido;
import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.model.Produto;
import br.com.vendapedido.model.Usuario;
import br.com.vendapedido.repository.Clientes;
import br.com.vendapedido.repository.Produtos;
import br.com.vendapedido.repository.Usuarios;
import br.com.vendapedido.service.CadastroPedidoService;
import br.com.vendapedido.util.jsf.FacesUtil;
import br.com.vendapedido.validation.SKU;

//@RequestScoped -> quando terminar a requisição o bean morre 
@Named
@ViewScoped // o bean so vai morrer quando der um refresh na pagina 
public class CadastroPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L; // Quando a View é muito grande é necessario usar o Serializable
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Produtos produtos;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private String sku;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	private Produto produtoLinhaEditavel;
	
	public CadastroPedidoBean() {
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.vendedores = this.usuarios.vendedores();
			
			this.pedido.adicionarItemVazio();
			
			this.recalcularPedido();
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
	
	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}
	
	public List<Produto> completarProduto(String nome) {
		return this.produtos.porNome(nome);
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);
		
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}
	
	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {//StringUtils.isNotEmpty -> se o SKU nao fr vazio ele entra
			this.produtoLinhaEditavel = this.produtos.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	//*****************GET E SET************************************************************************************************************//
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isEditando(){
		return this.pedido.getId() != null; //se nao for nulo é porque esta editando o pedido, isso vai mudar o nome da tela do pedido
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}