package model;

import java.io.Serializable;
import java.sql.Date;

public class ItemPerdido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userid;
	private String nome;
	private int tipo;
	private int estado;
	private int cidade;
	private Date data;
	
	public ItemPerdido() {
		
	}

	public ItemPerdido(int id, int userid, String nome, int tipo, int estado, int cidade, Date data) {
		this.id = id;
		this.userid = userid;
		this.nome = nome;
		this.tipo = tipo;
		this.estado = estado;
		this.cidade = cidade;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		ItemPerdido other = (ItemPerdido) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemPerdido [id=" + id + ", userid=" + userid + ", nome=" + nome + ", tipo=" + tipo + ", estado="
				+ estado + ", cidade=" + cidade + ", data=" + data + "]";
	}

}
