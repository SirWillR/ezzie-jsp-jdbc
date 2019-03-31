package model.dao;

import model.dao.impl.ItemPerdidoJDBC;

public class DaoFactory {
	
	public static ItemPerdidoDAO createItemPerdidoDAO() {
		return new ItemPerdidoJDBC();
	}
}
