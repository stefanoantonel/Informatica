package modelo;

import persistencia.StockDAO;

public class Stock {
	
	//se fija en la tabla stock la cantidad
	//le paso id de art y me devuelve la cantidad.
	StockDAO sDao;
	
	public Stock(){
		sDao = new StockDAO();
	}
	
	public Integer getCantidadStock(Integer id_art)
	{
		return sDao.getCantidadStock(id_art);
	}
}