package application;

import model.ItemPerdido;
import model.dao.DaoFactory;
import model.dao.ItemPerdidoDAO;

public class Program {

	public static void main(String[] args) {

		ItemPerdidoDAO itemPerdidoDao = DaoFactory.createItemPerdidoDAO();
		
		ItemPerdido itemPerdido = itemPerdidoDao.findById(1);

		System.out.println(itemPerdido.getNome());
		System.out.println(itemPerdido.getTipo());
		System.out.println(itemPerdido.getData());
		System.out.println(itemPerdido.getEstado());
		System.out.println(itemPerdido.getCidade());
	}

}
