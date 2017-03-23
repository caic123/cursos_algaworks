package br.com.vendapedido.model;

public enum FormaPagamento {
	
	DINHEIRO("Dinheiro"), 
	CATAO_CREDITO("Cartão de crédito"), 
	CARTAO_DEBITO("Cartão de débito"), 
	CHEQUE("Cheque"), 
	BOLETO_BANCARIO("Boleto bancario"), 
	DEPOSITO_BANCARIO("Depósito bancário");
	
	private String descricao;
	
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	FormaPagamento(String descricao){
		this.descricao = descricao;
	}



}
