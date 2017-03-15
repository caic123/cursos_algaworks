package br.com.vendapedido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendapedido.model.Categoria;
import br.com.vendapedido.repository.Categorias;
import br.com.vendapedido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

	private Categorias categorias;
	
	public CategoriaConverter(){
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}

	@Override//Recebe uma string, apartir da string tem que retornar um objeto, objeto Categoria
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if(value != null){
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(value != null){
			return ((Categoria) value).getId().toString();
		}
		return "";
	}
		
	
	
}
