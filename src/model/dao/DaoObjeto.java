package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Objeto;
import connection.DBconnection;

public class DaoObjeto {

	private Connection conn = null;

	public DaoObjeto() {
		conn = DBconnection.getConnection();
	}

	public Long salvar(Objeto objeto) {
		try {
			String sql = "insert into objetos (titulo, descricao, tipo, situacao, cidade, uf, latLng, imagem, userId) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setString(1, objeto.getTitulo());
			st.setString(2, objeto.getDescricao());
			st.setString(3, objeto.getTipo());
			st.setString(4, objeto.getSituacao());
			st.setString(5, objeto.getCidade());
			st.setString(6, objeto.getUf());
			st.setString(7, objeto.getLatLng());
			st.setString(8, objeto.getImagem());
			st.setLong(9, objeto.getUserId());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				return rs.getLong(1);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	public void delete(String id) {
		try {
			String sql = "delete from objetos where id = '" + id + "'";
			PreparedStatement st;
			st = conn.prepareStatement(sql);
			st.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public Objeto consultar(String id) throws Exception {
		String sql = "select * from objetos where id = '" + id + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setId(rs.getLong("id"));
			objeto.setTitulo(rs.getString("titulo"));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setTipo(rs.getString("tipo"));
			objeto.setSituacao(rs.getString("situacao"));
			objeto.setCidade(rs.getString("cidade"));
			objeto.setUf(rs.getString("uf"));
			objeto.setLatLng(rs.getString("latLng"));
			objeto.setImagem(rs.getString("imagem"));
			objeto.setUserId(rs.getLong("userId"));
			return objeto;
		}
		return null;
	}
	
	public List<Objeto> listar(String id) throws Exception {
		String sql = "select * from objetos where userId = '" + id + "'";
		List<Objeto> lista = new ArrayList<Objeto>();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setId(rs.getLong("id"));
			objeto.setTitulo(rs.getString("titulo"));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setTipo(rs.getString("tipo"));
			objeto.setSituacao(rs.getString("situacao"));
			objeto.setCidade(rs.getString("cidade"));
			objeto.setUf(rs.getString("uf"));
			objeto.setLatLng(rs.getString("latLng"));
			objeto.setImagem(rs.getString("imagem"));
			objeto.setUserId(rs.getLong("userId"));
			lista.add(objeto);
		}
		return lista;
	}
	
	public List<Objeto> listarMarker(String cidade, String uf, String situacao, String tipo) throws Exception {
		String sql = "select id, titulo, descricao, latLng, imagem from objetos where "
				+ "cidade = '" + cidade + "' and uf = '" + uf + "' and situacao = '" + situacao + "' and tipo = '" + tipo + "'";
		List<Objeto> lista = new ArrayList<Objeto>();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setId(rs.getLong("id"));
			objeto.setTitulo(rs.getString("titulo"));
			objeto.setDescricao(rs.getString("descricao"));
			objeto.setLatLng(rs.getString("latLng"));
			objeto.setImagem(rs.getString("imagem"));
			lista.add(objeto);
		}
		return lista;
	}
	
	public List<Objeto> listarEstados(String situacao) throws Exception {
		String sql = "select distinct uf from objetos where situacao = '" + situacao + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List<Objeto> lista = new ArrayList<Objeto>();
		while (rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setUf(rs.getString("uf"));
			lista.add(objeto);
		}
		return lista;
	}
	
	public List<Objeto> listarCidades(String uf, String situacao) throws Exception {
		String sql = "select distinct cidade from objetos where uf = '" + uf + "' and situacao = '" + situacao + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List<Objeto> lista = new ArrayList<Objeto>();
		while (rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setCidade(rs.getString("cidade"));
			lista.add(objeto);
		}
		return lista;
	}
	
	public List<Objeto> listarTipos(String uf, String cidade, String situacao) throws Exception {
		String sql = "select distinct tipo from objetos where uf = '" + uf + "' and cidade = '" + cidade + "' and situacao = '" + situacao + "'";
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List<Objeto> lista = new ArrayList<Objeto>();
		while (rs.next()) {
			Objeto objeto = new Objeto();
			objeto.setTipo(rs.getString("tipo"));
			lista.add(objeto);
		}
		return lista;
	}

	public void atualizar(Objeto objeto) {
		try {
			String sql = "update objetos set titulo = ?, descricao = ?, tipo = ?, situacao = ?"
					+ ", cidade = ?, uf = ?, latLng = ?, imagem = ?, userId = ?  where id = " + objeto.getId();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, objeto.getTitulo());
			st.setString(2, objeto.getDescricao());
			st.setString(3, objeto.getTipo());
			st.setString(4, objeto.getSituacao());
			st.setString(5, objeto.getCidade());
			st.setString(6, objeto.getUf());
			st.setString(7, objeto.getLatLng());
			st.setString(8, objeto.getImagem());
			st.setLong(9, objeto.getUserId());
			st.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
}
