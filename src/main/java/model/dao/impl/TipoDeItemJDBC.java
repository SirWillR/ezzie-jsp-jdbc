package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.ItemPerdido;
import model.TipoDeItem;
import model.dao.TipoDeItemDAO;

public class TipoDeItemJDBC implements TipoDeItemDAO {

	private Connection conn = null;
	
	public TipoDeItemJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public TipoDeItem findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoDeItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
