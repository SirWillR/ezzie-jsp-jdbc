package model;

public class Usuario {

	private long idUsuario;
	private String usuario;
	private String senha;
	private String nome;
	private String email;
	private String telefone;
	private String redeSocial;
	
	public Usuario() {
		
	}
	
	public Usuario(long idUsuario, String usuario, String senha, String nome, String email, String telefone, String redeSocial) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.redeSocial = redeSocial;
	}
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getRedeSocial() {
		return redeSocial;
	}
	public void setRedeSocial(String redeSocial) {
		this.redeSocial = redeSocial;
	}
	
}
