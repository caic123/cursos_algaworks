package br.com.vendapedido.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.jdbc.Work;

public class ExecutorRelatorio implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;
	
	private boolean relatorioGerado;
	
	//Construtor -> esta sendo chamado na classe RelatorioPedidosEmitidosBean, la ele chama o caminho e os demais comandos. 
	public ExecutorRelatorio(String caminhoRelatorio,
			HttpServletResponse response, Map<String, Object> parametros,
			String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		//fala que o formato do valor sera em reais 
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {//pega o fluxo de um arquivo que esta empacotado dentro do projeto
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
			
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
			//se o relatorio estiver mais que 0 ele foi gerado
			this.relatorioGerado = print.getPages().size() > 0;
			
			//se ele for gerado vai ser exportado, se não nao vai ser gerado
			if (this.relatorioGerado) { //Exportar para PDF
				JRExporter exportador = new JRPdfExporter();
				//a saida desse pdf vai ser enviado para a resposta do http
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				//objeto usado para gerar o pdf "print"
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				//fala que vamos enviar um arquivo pdf
				response.setContentType("application/pdf");
				//vai forçar o download e falar o nome do arquivo
				response.setHeader("Content-Disposition", "attachment; filename=\"" 
						+ this.nomeArquivoSaida  + "\"");
				//pega os dados e gera o pdf
				exportador.exportReport();
			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}
