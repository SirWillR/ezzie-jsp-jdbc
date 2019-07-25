package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = -3709112848005496965L;

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String telefone;
	private String email;
	private String fotoBase64Mini;
	private Boolean atualizarFoto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	@Lob
	public String getFotoBase64Mini() {
		return fotoBase64Mini;
	}
	public void setFotoBase64Mini(String fotoBase64Mini) {
		this.fotoBase64Mini = fotoBase64Mini;
	}
	@Transient
	public boolean getAtualizarFoto() {
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
