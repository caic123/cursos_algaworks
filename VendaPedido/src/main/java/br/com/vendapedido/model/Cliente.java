package br.com.vendapedido.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
		private Long id;
		private String nome;
		private String email;
		private String documentoReceitaFederal;
		private TipoPessoa tipo; 
		private List<Endereco> enderecos = new ArrayList<>();
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getDocumentoReceitaFederal() {
			return documentoReceitaFederal;
		}
		public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
			this.documentoReceitaFederal = documentoReceitaFederal;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public List<Endereco> getEnderecos() {
			return enderecos;
		}
		public void setEnderecos(List<Endereco> enderecos) {
			this.enderecos = enderecos;
		}
		
		public TipoPessoa getTipo() {
			return tipo;
		}
		public void setTipo(TipoPessoa tipo) {
			this.tipo = tipo;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cliente other = (Cliente) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		} 
	
		
		
}
