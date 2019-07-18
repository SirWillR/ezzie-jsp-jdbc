package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBconnection;

public class DaoLogin {
	private Connection conn = null;
	
	public DaoLogin() {
		conn = DBconnection.getConnection();
	}
	
	// valida o login para acesso ao sistema
	public Long validarLogin(String login, String senha) throws Exception {
		String sql = "select * from usuarios where login = '" + login + "' and senha = '" + senha + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return rs.getLong("id");
		} else {
			return null;
		}
	}
}
