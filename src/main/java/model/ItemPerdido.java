package model;

import java.sql.Date;

public class ItemPerdido {

	private long idItem;
	private String nomeItem;
	private TipoDeItem tipo;
	private Localizacao localizacao;
	private Date data;
	private String localEncontrado;
	private int prazo;
	private Usuario pessoaQueAchou;
	
	public long getIdItem() {
		return idItem;
	}
	public void setIdItem(long idItem) {
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
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
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
