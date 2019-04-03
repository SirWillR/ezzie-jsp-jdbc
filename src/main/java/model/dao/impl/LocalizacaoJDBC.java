package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.Cidade;
import model.Estado;
import model.Localizacao;
import model.Pais;
import model.dao.LocalizacaoDAO;

public class LocalizacaoJDBC implements LocalizacaoDAO {
	
	private Connection conn = null;

	public LocalizacaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Pais> findAllPais() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estado> findAllEstado(int idPais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cidade> findAllCity(int idEstado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Localizacao findById(int idCity) {
		// TODO Auto-generated method stub
		return null;
	}
}
