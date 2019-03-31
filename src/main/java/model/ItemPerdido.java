package model;

import java.sql.Date;

public class ItemPerdido {

	private String nome;
	private String tipo;
	private String estado;
	private String cidade;
	private Date data;
	
	public ItemPerdido() {
		
	}
	
	public ItemPerdido(String nome, String tipo, String estado, String cidade, Date data) {
		this.nome = nome;
		this.tipo = tipo;
		this.estado = estado;
		this.cidade = cidade;
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
