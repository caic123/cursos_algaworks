package br.com.vendapedido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.repository.Pedidos;
import br.com.vendapedido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter{

	private Pedidos produtos;
	
	public PedidoConverter(){
		produtos = CDIServiceLocator.getBean(Pedidos.class);
	}

	@Override//Recebe uma string, apartir da string tem que retornar um objeto, objeto Pedido
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Pedido produto = (Pedido) value;
			return produto.getId() == null ? null: produto.getId().toString();
		}
		return "";
	}
		
	
	
}
