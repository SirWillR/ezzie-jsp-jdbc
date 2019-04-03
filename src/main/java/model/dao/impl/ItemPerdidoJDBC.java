package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.ItemPerdido;
import model.dao.ItemPerdidoDAO;

public class ItemPerdidoJDBC implements ItemPerdidoDAO {
	
	private Connection conn;
	
	public ItemPerdidoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(ItemPerdido obj) {

	}

	@Override
	public void update(ItemPerdido obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ItemPerdido findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT itemperdido.Nome, itemperdido.Data, tipodeitem.Nome as TypeName, estado.Nome as UFName, cidade.Nome as CityName "
					+ "FROM itemperdido "
					+ "INNER JOIN tipodeitem ON itemperdido.TipoID = tipodeitem.ID "
					+ "INNER JOIN estado ON itemperdido.EstadoID = estado.ID "
					+ "INNER JOIN cidade ON itemperdido.CidadeID = cidade.ID "
					+ "WHERE itemperdido.ID = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				ItemPerdido itemPerdido = instantiateItemPerdido(rs);
				return itemPerdido;
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	private ItemPerdido instantiateItemPerdido(ResultSet rs) throws SQLException {
		ItemPerdido obj = new ItemPerdido();
		obj.setNome(rs.getString("Nome"));
		obj.setTipo(rs.getString("TypeName"));
		obj.setData(rs.getDate("Data"));
		obj.setEstado(rs.getString("UFName"));
		obj.setCidade(rs.getString("CityName"));
		return obj;
	}

	@Override
	public List<ItemPerdido> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ItemPerdido> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement(
					"SELECT itemperdido.Nome, itemperdido.Data, tipodeitem.Nome as TypeName, estado.Nome as UFName, cidade.Nome as CityName "
					+ "FROM itemperdido "
					+ "INNER JOIN tipodeitem ON itemperdido.TipoID = tipodeitem.ID "
					+ "INNER JOIN estado ON itemperdido.EstadoID = estado.ID "
					+ "INNER JOIN cidade ON itemperdido.CidadeID = cidade.ID ");
			rs = st.executeQuery();
			while(rs.next()) {
				ItemPerdido itemPerdido = instantiateItemPerdido(rs);
				list.add(itemPerdido);
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
