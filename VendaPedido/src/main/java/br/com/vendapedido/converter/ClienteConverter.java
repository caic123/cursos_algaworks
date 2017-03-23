package br.com.vendapedido.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendapedido.model.Cliente;
import br.com.vendapedido.repository.Clientes;
import br.com.vendapedido.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	//@Inject
	private Clientes clientes;
	
	public ClienteConverter() {
		this.clientes = (Clientes) CDIServiceLocator.getBean(Clientes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null) {
			retorno = this.clientes.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Cliente) value).getId().toString();
		}
		return "";
	}

}