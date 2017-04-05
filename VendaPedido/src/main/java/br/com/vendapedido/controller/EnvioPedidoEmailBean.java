package br.com.vendapedido.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendapedido.controller.PedidoEdicao;
import com.outjected.email.api.MailMessage;

import br.com.vendapedido.model.Pedido;
import br.com.vendapedido.util.jsf.FacesUtil;
import br.com.vendapedido.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail())
			.subject("Pedido " + this.pedido.getId())
			.bodyHtml("<strong>Valor total:</strong> " + this.pedido.getValorTotal())
			.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
}
