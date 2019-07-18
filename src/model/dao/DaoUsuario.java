package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;
import connection.DBconnection;

public class DaoUsuario {

	private Connection conn = null;

	public DaoUsuario() {
		conn = DBconnection.getConnection();
	}

	public Long salvar(Usuario usuario) {
		try {
			String sql = "insert into usuarios (login, senha, nome, telefone, email, fotoBase64Mini) "
					+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getTelefone());
			st.setString(5, usuario.getEmail());
			st.setString(6, usuario.getFotoBase64Mini());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();
			conn.commit();
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from usuarios where id = '" + id + "' and login <> 'admin'";
			PreparedStatement st;
			st = conn.prepareStatement(sql);
			st.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public Usuario consultar(String id) throws Exception {
		String sql = "select * from usuarios where id = '" + id + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getLong("id"));
			usuario.setLogin(rs.getString("login"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNome(rs.getString("nome"));
			usuario.setTelefone(rs.getString("telefone"));
			usuario.setEmail(rs.getString("email"));
			usuario.setFotoBase64Mini(rs.getString("fotoBase64Mini"));
			return usuario;
		}
		return null;
	}
	
	public void atualizar(Usuario usuario) {
		try {
			String sql = "update usuarios set login = ?, senha = ?, nome = ?, telefone = ?, email = ?";
			if(usuario.isAtualizarFoto())
				sql += "fotoBase64Mini = ? ";
			sql += " where id = " + usuario.getId();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, usuario.getLogin());
			st.setString(2, usuario.getSenha());
			st.setString(3, usuario.getNome());
			st.setString(4, usuario.getTelefone());
			st.setString(5, usuario.getEmail());
			if(usuario.isAtualizarFoto()) {
				st.setString(6, usuario.getFotoBase64Mini());
			}
			st.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	// verifica se o login está disponível na hora de registrar
	public boolean verificarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuarios where login = '" + login + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs.getInt("qtd") <= 0;
		}
		return true;
	}
}
