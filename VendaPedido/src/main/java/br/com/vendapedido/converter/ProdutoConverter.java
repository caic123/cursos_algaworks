package br.com.vendapedido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendapedido.model.Produto;
import br.com.vendapedido.repository.Produtos;
import br.com.vendapedido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter{

	private Produtos produtos;
	
	public ProdutoConverter(){
		produtos = CDIServiceLocator.getBean(Produtos.class);
	}

	@Override//Recebe uma string, apartir da string tem que retornar um objeto, objeto Produto
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = produtos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			Produto produto = (Produto) value;
			return produto.getId() == null ? null: produto.getId().toString();
		}
		return "";
	}
		
	
	
}
