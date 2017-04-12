package br.com.vendapedido.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.vendapedido.model.Usuario;
import br.com.vendapedido.repository.Pedidos;
import br.com.vendapedido.security.UsuarioLogado;
import br.com.vendapedido.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private CartesianChartModel model;

	public void preRender(){
		this.model = new CartesianChartModel();
		
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}
	
private void adicionarSerie(String rotulo, Usuario criadoPor) {
																			//de hoje a 15 dias atras
	Map<Date, BigDecimal> valoresPorDia = this.pedidos.valoresTotaisPorData(15, criadoPor);
	//Instanciar uma serie
		ChartSeries series = new ChartSeries(rotulo);
		
		//vai interar em todas as chaves do mapa que recebemos
		for (Date data : valoresPorDia.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorDia.get(data));
		}
		
		this.model.addSeries(series); 
	}
//*****GET E SET****************************************************************************************
	public CartesianChartModel getModel() {
		return model;
	}

	public void setModel(CartesianChartModel model) {
		this.model = model;
	}
}
