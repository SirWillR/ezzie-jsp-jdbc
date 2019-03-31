package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class InserirDados {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"INSERT INTO itemperdido "
					+ "(Nome, UsuarioID, TipoID, EstadoID, CidadeID, Data) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Cachorro");
			st.setInt(2, 0001);
			st.setInt(3, 2);
			st.setInt(4, 11);
			st.setInt(5, 1630);
			st.setDate(6, Date.valueOf("2019-03-31"));
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! ID = " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
