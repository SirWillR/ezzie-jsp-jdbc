package model.dao;

import java.util.List;

import model.TipoDeItem;

public interface TipoDeItemDAO {
	TipoDeItem findById(int id);
	List<TipoDeItem> findAll();
}
