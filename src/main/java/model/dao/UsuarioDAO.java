package model.dao;

import model.Usuario;

public interface UsuarioDAO {

	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(int id);
	Usuario findById(int id);
}
