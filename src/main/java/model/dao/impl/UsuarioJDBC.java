package model.dao.impl;

import java.sql.Connection;

import model.Usuario;
import model.dao.UsuarioDAO;

public class UsuarioJDBC implements UsuarioDAO {
	
	private Connection conn = null;
	
	public UsuarioJDBC (Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuario obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
