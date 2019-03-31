package model.dao;

import db.DB;
import model.dao.impl.ItemPerdidoJDBC;

public class DaoFactory {
	
	public static ItemPerdidoDAO createItemPerdidoDAO() {
		return new ItemPerdidoJDBC(DB.getConnection());
	}
}
