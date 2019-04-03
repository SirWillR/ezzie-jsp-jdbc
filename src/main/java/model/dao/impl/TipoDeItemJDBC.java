package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.TipoDeItem;
import model.dao.TipoDeItemDAO;

public class TipoDeItemJDBC implements TipoDeItemDAO {

	private Connection conn = null;
	
	public TipoDeItemJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public TipoDeItem findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM tipodeitem WHERE ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				TipoDeItem tipo = new TipoDeItem();
				tipo.setIdTipo(rs.getInt("ID"));
				tipo.setNome(rs.getString("Nome"));
				return tipo;
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<TipoDeItem> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<TipoDeItem> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM tipodeitem");
			rs = st.executeQuery();
			
			while(rs.next()) {
				TipoDeItem tipo = new TipoDeItem();
				tipo.setIdTipo(rs.getInt("ID"));
				tipo.setNome(rs.getString("Nome"));
				list.add(tipo);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
