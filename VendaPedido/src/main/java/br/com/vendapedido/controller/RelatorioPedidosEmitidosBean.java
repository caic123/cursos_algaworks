package br.com.vendapedido.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;

import br.com.vendapedido.util.jsf.FacesUtil;
import br.com.vendapedido.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dataInicio;
	private Date dataFim;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;//precisa para buscar a conexão

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("data_inicio", this.dataInicio);//essas data_inicio sao as variaveis declaro aqui, que vem da tela(view)
		parametros.put("data_fim", this.dataFim);//essas data_fim sao as variaveis declaro aqui, que vem da tela(view)
		
		//caminho do relatorio
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper",
				this.response, parametros, "Pedidos emitidos.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		//se o relatorio nao for gerado apresenta msg de erro
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();//Fala para o jsf que já fez tudo que já tinha que fzr
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

//********GET E SET**********************************************************************************************************
	@NotNull
	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	@NotNull
	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

}