package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.*;
import model.dao.ItemPerdidoDAO;

public class ItemPerdidoJDBC implements ItemPerdidoDAO {
	
	private Connection conn;
	
	public ItemPerdidoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(ItemPerdido obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO itemperdido (UsuarioID, Nome, TipoID, CidadeID, Data, LocalEncontrado, Prazo) VALUES  (?, ?, ?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, obj.getPessoaQueAchou().getIdUsuario());
			st.setString(2, obj.getNomeItem());
			st.setLong(3, obj.getTipo().getIdTipo());
			st.setInt(4, obj.getLocalizacao().getCidade().getId());
			st.setDate(5, obj.getData());
			st.setString(6, obj.getLocalEncontrado());
			st.setInt(7, obj.getPrazo());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setIdItem(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpeceted error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(ItemPerdido obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE itemperdido SET UsuarioID = ?, Nome = ?, TipoID = ?, CidadeID = ?, Data = ?, LocalEncontrado = ?, Prazo = ? WHERE ID = ?");
			st.setLong(1, obj.getPessoaQueAchou().getIdUsuario());
			st.setString(2, obj.getNomeItem());
			st.setLong(3, obj.getTipo().getIdTipo());
			st.setInt(4, obj.getLocalizacao().getCidade().getId());
			st.setDate(5, obj.getData());
			st.setString(6, obj.getLocalEncontrado());
			st.setInt(7, obj.getPrazo());
			st.setInt(8, obj.getIdItem());
			
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(int id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM itemperdido WHERE ID = ?");
			st.setInt(1, id);
			st.executeUpdate();
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public ItemPerdido findById(int id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM itemperdido WHERE itemperdido.ID = ? ORDER BY Data");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario usuario = instantiateUsuario(rs);
				TipoDeItem tipo = instantiateTipoDeItem(rs);
				Localizacao local = instantiateLocalizacao(rs);
				ItemPerdido itemPerdido = instantiateItemPerdido(rs, tipo, local, usuario);
				return itemPerdido;
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
	public List<ItemPerdido> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<ItemPerdido> list = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM itemperdido ORDER BY Data");
			rs = st.executeQuery();
			
			Map<Integer, Usuario> mapUser = new HashMap<>();
			Map<Integer, TipoDeItem> mapTypeOfItem = new HashMap<>();
			Map<Integer, Localizacao> mapLocal = new HashMap<>();
			
			while(rs.next()) {
				Usuario usuario = mapUser.get(rs.getInt("UsuarioID"));
				if(usuario == null) {
					usuario = instantiateUsuario(rs);
					mapUser.put(rs.getInt("UsuarioID"), usuario);
				}
				
				TipoDeItem tipo = mapTypeOfItem.get(rs.getInt("TipoID"));
				if(tipo == null) {
					tipo = instantiateTipoDeItem(rs);
					mapTypeOfItem.put(rs.getInt("TipoID"), tipo);
				}
				
				Localizacao local = mapLocal.get(rs.getInt("TipoID"));
				if(local == null) {
					local = instantiateLocalizacao(rs);
					mapLocal.put(rs.getInt("CidadeID"), local);
				}
				
				ItemPerdido itemPerdido = instantiateItemPerdido(rs, tipo, local, usuario);
				list.add(itemPerdido);
			}
			return list;
		} catch(SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private ItemPerdido instantiateItemPerdido(ResultSet rs, TipoDeItem tipo, Localizacao local, Usuario usuario) throws SQLException {
		ItemPerdido obj = new ItemPerdido();
		obj.setIdItem(rs.getInt("ID"));
		obj.setNomeItem(rs.getString("Nome"));
		obj.setTipo(tipo);
		obj.setLocalizacao(local);
		obj.setData(rs.getDate("Data"));
		obj.setLocalEncontrado(rs.getString("LocalEncontrado"));
		obj.setPrazo(rs.getInt("Prazo"));
		obj.setPessoaQueAchou(usuario);
		return obj;
	}
	
	private TipoDeItem instantiateTipoDeItem(ResultSet rs) throws SQLException {
		TipoDeItemJDBC tipo = new TipoDeItemJDBC(conn);
		TipoDeItem obj = tipo.findById(rs.getInt("TipoID"));
		return obj;
	}
	
	private Localizacao instantiateLocalizacao(ResultSet rs) throws SQLException {
		LocalizacaoJDBC local = new LocalizacaoJDBC(conn);
		Localizacao obj = local.findById(rs.getInt("CidadeID"));
		return obj;
	}
	
	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		UsuarioJDBC usuario = new UsuarioJDBC(conn);
		Usuario obj = usuario.findById(rs.getInt("UsuarioID"));
		return obj;
	}
	
}
