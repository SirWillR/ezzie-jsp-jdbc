package model.dao;

import db.DB;
import model.dao.impl.*;

public class DaoFactory {
	
	public static ItemPerdidoDAO createItemPerdidoDAO() {
		return new ItemPerdidoJDBC(DB.getConnection());
	}
	
	public static TipoDeItemDAO createTipoDeItemDAO() {
		return new TipoDeItemJDBC(DB.getConnection());
	}
	
	public static LocalizacaoDAO createLocalizacaoDAO() {
		return new LocalizacaoJDBC(DB.getConnection());
	}
	
	public static UsuarioDAO createUsuarioDAO() {
		return new UsuarioJDBC(DB.getConnection());
	}
}