package modelo;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import persistencia.MovimientoDAO;

public class Movimiento {
	
	ArrayList<String> almacenes;
	ArrayList<String> sucursales;
	ArrayList<String> ubicaciones;
	MovimientoDAO mDao;
	
	public Movimiento()
	{
		mDao= new MovimientoDAO();
	}

	public ArrayList<String> getSucursales()
	{
		sucursales=mDao.getSucursales();
		return  sucursales;
	}
	
	public ArrayList<String> getAlmacenes(String suc)
	{
		
		almacenes=mDao.getAlmacenes(suc);
		return  almacenes;
	}
	public ArrayList<String> getUbicacion(String alm)
	{
		ubicaciones=mDao.getUbicaciones(alm);
		return  ubicaciones;
	}
	
	public ArrayList<String> getCausas()
	{
		return  mDao.getCausas();
	}
	
	public ArrayList<Articulos> getArticulosXalmacen(String alm)
	{
		return mDao.getArticuloXalmacen(alm);
	}
	
	public Integer getCantidadXlote(Integer lote)
	{
		return mDao.getCantidadXlote(lote);
	}
}
