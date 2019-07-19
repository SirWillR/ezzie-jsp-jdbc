package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String telefone;
	private String email;
	private String fotoBase64Mini;
	
	@Transient
	private boolean atualizarFoto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public String getFotoBase64Mini() {
		return fotoBase64Mini;
	}
	public void setFotoBase64Mini(String fotoBase64Mini) {
		this.fotoBase64Mini = fotoBase64Mini;
	}
	public boolean isAtualizarFoto() {
		return atualizarFoto;
	}
	public void setAtualizarFoto(boolean atualizarFoto) {
		this.atualizarFoto = atualizarFoto;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
