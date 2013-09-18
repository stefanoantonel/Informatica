package modelo;

import java.util.ArrayList;

import persistencia.MovimientoDAO;
import ui.MovimientoStockUI;

public class Movimiento {
	
	ArrayList<ArrayList<String>> almacenes;
	ArrayList<String> sucursales;
	ArrayList<String> ubicaciones;
	MovimientoDAO mDao;
	ArrayList<Articulos> artsCompra;
	ArrayList<Articulos> arts;
	
	public Movimiento()
	{
		mDao= new MovimientoDAO();
		getArticulosCompra();
		getArticulos();
	}

	public ArrayList<String> getSucursales()
	{
		sucursales=mDao.getSucursales();
		return  sucursales;
	}
	
	public ArrayList<ArrayList<String>> getAlmacenes(String suc)
	{
		System.out.println("paso por getAlmacenes---------");
		almacenes=mDao.getAlmacenes(suc);
		System.out.println("almacen1: "+almacenes.get(0).get(0)+"---------");
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
	
	public ArrayList<Articulos> getArticulosCompra()
	{
		artsCompra=mDao.getArticulos(1);
		return artsCompra;
	}
	
	public ArrayList<Articulos> getArticulos()
	{
		arts=mDao.getArticulos(0);
		System.out.println("arts1"+arts.get(0).getDesc());
		return arts;
	}
	public Integer getCantidadXlote(Integer lote)
	{
		return mDao.getCantidadXlote(lote);
	}

	public void insertarMovimiento(MovimientoStockUI msUI)
	{
		String causa =msUI.causaElegida;
		String sucursalOrigen= msUI.sucursalOrigen;
		Integer almacenOrigen= getAlmByDesc(msUI.almacenOrigen);
		System.out.println("msUI.ubicacionOrigen: "+msUI.ubicacionOrigen);
		String ubicacionOrigen= getFormatoUbicacion(msUI.ubicacionOrigen);
		System.out.println("ubicacionOrigen: "+ubicacionOrigen);
		String sucursalDestino= msUI.sucursalDestino;
		Integer almacenDestino=getAlmByDesc( msUI.almacenDestino);
		String ubicacionDestino= getFormatoUbicacion(msUI.ubicacionDestino);
		System.out.println("ubicacionDestino: "+ubicacionDestino);
		String cantidad= msUI.cantIngresada.toString();
		String art= msUI.articulo;
		String fecha= msUI.fecha;
		String nota= msUI.nota;
		Articulos a= getArtByDesc(art);
		
		if(nota.equals(""))
			nota="null";
		if(ubicacionDestino.equals(""))
			nota="null";
		
		mDao.insertarMovimiento(causa,sucursalOrigen,almacenOrigen,ubicacionOrigen,sucursalDestino,almacenDestino,ubicacionDestino,a,fecha,nota);
		
		
	}
	public Articulos getArtByDesc (String desc)
	{
		for (Articulos a:arts)
		{
			System.out.println("articulos "+a.getDesc());
			if(a.getDesc().equals(desc))
				return a;
		}
		System.out.println("--------------------");
		return null;
	}
	
	public Integer getAlmByDesc (String desc)
	{
		if(almacenes!=null)
		for (ArrayList<String> a:almacenes)
		{
			System.out.println("almacen "+a.get(1));
			if(a.get(1).equals(desc))
				return Integer.parseInt(a.get(0));
		}
		System.out.println("--------------------");
		return null;
	}

	public String getFormatoUbicacion(String ub)
	{
		
		if(ub!=null)
		{String id= ub.split("\\)")[0];
		return id;
		}
		return "";
//		// obtengo un arraylist donde:
//		// 1- pasillo
//		// 2- estante
//		// 3-columna
//		String[] descompuesto;
//		ArrayList<String> ubicaciones= new ArrayList<>();
//		descompuesto=ub.split(" ");
//		for (String d: descompuesto)
//		{
//			ubicaciones.add(d.split(":")[1]);
//		}
//		
//		
//		
//		return ubicaciones;
	}
	
	
}
