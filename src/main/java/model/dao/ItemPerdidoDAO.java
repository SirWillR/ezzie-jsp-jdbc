package model.dao;

import java.util.List;

import model.ItemPerdido;

public interface ItemPerdidoDAO {
	
	void insert(ItemPerdido obj);
	void update(ItemPerdido obj);
	void deleteById(int id);
	ItemPerdido findById(int id);
	List<ItemPerdido> findAll();
}