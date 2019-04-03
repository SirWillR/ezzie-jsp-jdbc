package model;

import java.sql.Date;

public class ItemPerdido {

	private int idItem;
	private String nomeItem;
	private TipoDeItem tipo;
	private Cidade cidade;
	private Estado estado;
	private Pais pais;
	private Date data;
	private String localEncontrado;
	private int prazo;
	private Usuario pessoaQueAchou;
	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public TipoDeItem getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeItem tipo) {
		this.tipo = tipo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getLocalEncontrado() {
		return localEncontrado;
	}

	public void setLocalEncontrado(String localEncontrado) {
		this.localEncontrado = localEncontrado;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public Usuario getPessoaQueAchou() {
		return pessoaQueAchou;
	}

	public void setPessoaQueAchou(Usuario pessoaQueAchou) {
		this.pessoaQueAchou = pessoaQueAchou;
	}

}
