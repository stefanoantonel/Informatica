package modelo;

import java.util.ArrayList;

import persistencia.MovimientoDAO;
import ui.MovimientoStockUI;

public class Movimiento {
	
	ArrayList<String> almacenes;
	ArrayList<String> sucursales;
	ArrayList<String> ubicaciones;
	MovimientoDAO mDao;
	ArrayList<Articulos> arts;
	
	public Movimiento()
	{
		mDao= new MovimientoDAO();
		getArticulos();
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
	
	public ArrayList<Articulos> getArticulos()
	{
		arts=mDao.getArticulos();
		return arts;
	}
	public Integer getCantidadXlote(Integer lote)
	{
		return mDao.getCantidadXlote(lote);
	}

	public void guardarMovimiento(MovimientoStockUI msUI)
	{
		String causa =msUI.causaElegida;
		String sucursalOrigen= msUI.sucursalOrigen;
		String almacenOrigen= msUI.almacenOrigen;
		String ubicacionOrigen= msUI.ubicacionOrigen;
		String sucursalDestino= msUI.sucursalDestino;
		String almacenDestino= msUI.sucursalDestino;
		String ubicacionDestino= msUI.sucursalDestino;
		String cantidad= msUI.cantIngresada.toString();
		
	}
	public Articulos getArtByDesc (String desc)
	{
		for (Articulos a:arts)
		{
			if(a.getDesc().equals(desc))
				return a;
		}
		return null;
	}
	
}
