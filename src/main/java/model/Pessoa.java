package model;

public class Pessoa extends TipoDeItem {

	private String nomePessoa;
	private int idade;
	private String nomeDosPais;
	
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getNomeDosPais() {
		return nomeDosPais;
	}
	public void setNomeDosPais(String nomeDosPais) {
		this.nomeDosPais = nomeDosPais;
	}
}
