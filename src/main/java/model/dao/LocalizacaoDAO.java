package model.dao;

import java.util.List;

import model.Cidade;
import model.Estado;
import model.Pais;
import model.Localizacao;

public interface LocalizacaoDAO {
	
	List<Pais> findAllPais();
	List<Estado> findAllEstado(int idPais);
	List<Cidade> findAllCity(int idEstado);
	Pais findPaisById(int idPais);
	Estado findEstadoById(int idEstado);
	Cidade findCityById(int idCity);
	Localizacao findById(int idCity);
}
