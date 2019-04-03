package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
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
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pais> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM pais ORDER BY Nome");
			rs = st.executeQuery();
			
			while(rs.next()) {
				Pais pais = new Pais();
				pais.setId(rs.getInt("ID"));
				pais.setNome(rs.getString("Nome"));
				pais.setSigla(rs.getString("Sigla"));
				list.add(pais);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Estado> findAllEstado(int idPais) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Estado> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM estado WHERE PaisID = ? ORDER BY Nome");
			st.setInt(1, idPais);
			rs = st.executeQuery();
			
			while(rs.next()) {
				Estado estado = new Estado();
				estado.setId(rs.getInt("ID"));
				estado.setNome(rs.getString("Nome"));
				estado.setSigla(rs.getString("UF"));
				estado.setIdPais(rs.getInt("PaisID"));
				list.add(estado);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Cidade> findAllCity(int idEstado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Cidade> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM cidade WHERE EstadoID = ? ORDER BY Nome");
			st.setInt(1, idEstado);
			rs = st.executeQuery();
			
			while(rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("ID"));
				cidade.setNome(rs.getString("Nome"));
				cidade.setIdEstado(rs.getInt("EstadoID"));
				list.add(cidade);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Localizacao findById(int idCity) {
		Localizacao obj = new Localizacao();
		Cidade cidade = findCityById(idCity);
		obj.setCidade(cidade);
		Estado estado = findEstadoById(cidade.getIdEstado());
		obj.setEstado(estado);
		Pais pais = findPaisById(estado.getIdPais());
		obj.setPais(pais);
		return obj;
	}

	@Override
	public Pais findPaisById(int idPais) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM pais WHERE ID = ?");
			st.setInt(1, idPais);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Pais pais = new Pais();
				pais.setId(rs.getInt("ID"));
				pais.setNome(rs.getString("Nome"));
				pais.setSigla(rs.getString("Sigla"));
				return pais;
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public Estado findEstadoById(int idEstado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM estado WHERE ID = ?");
			st.setInt(1, idEstado);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Estado estado = new Estado();
				estado.setId(rs.getInt("ID"));
				estado.setNome(rs.getString("Nome"));
				estado.setSigla(rs.getString("UF"));
				estado.setIdPais(rs.getInt("PaisID"));
				return estado;
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public Cidade findCityById(int idCity) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM cidade WHERE ID = ?");
			st.setInt(1, idCity);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setId(rs.getInt("ID"));
				cidade.setNome(rs.getString("Nome"));
				cidade.setIdEstado(rs.getInt("EstadoID"));
				return cidade;
			}
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}
}
