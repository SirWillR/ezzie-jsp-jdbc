package application;

import model.dao.DaoFactory;
import model.dao.ItemPerdidoDAO;

public class Program {

	public static void main(String[] args) {

		ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();

	}

}
