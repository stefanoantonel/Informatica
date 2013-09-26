package modelo;

import java.util.ArrayList;

import persistencia.MovimientoDAO;
import ui.MovimientoStockUI;

public class Movimiento {
	//8
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
		//System.out.println("paso por getAlmacenes---------");
		almacenes=mDao.getAlmacenes(suc);
		//System.out.println("almacen1: "+almacenes.get(0).get(0)+"---------");
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
	
	public ArrayList<Articulos> getArticulosXubicacion(String ub)
	{
		String u= getFormatoUbicacion(ub);
		
		arts= mDao.getArticuloXubicacion(u);
		return arts;
	}
	
	public ArrayList<Articulos> getArticulosCompra()
	{
		artsCompra=mDao.getArticulos(1);
		return artsCompra;
	}
	
	public ArrayList<Articulos> getArticulos()
	{
		arts=mDao.getArticuloXubicacion(null);
		//System.out.println("arts1"+arts.get(0).getDesc());
		return arts;
	}
	public Integer getCantidadXlote(Integer lote, String ubicacion, String art)
	{
		String a= getFormatoUbicacion(ubicacion);
		Articulos articulo= getArtByDesc(art);
		System.out.println("ubicacion:"+ubicacion+" id="+a);
		ArrayList<ArrayList<String>> matLoteXCantidad = mDao.getCantidadXlote();
		//almcaen, lote ,articulo, cantidad
		
		
		for (ArrayList<String> ar: matLoteXCantidad)
		{
			
			if((ar.get(0).equals(a) && ar.get(1).equals(lote.toString()) && ar.get(2).equals(articulo.getValor().toString())) || (ar.get(0)==a && ar.get(1)==lote.toString()&& articulo.getValor()==Integer.parseInt(ar.get(2))))
				{
				
				 return Integer.parseInt(ar.get(3));
				}
		}	
		return null;
	}
	
	public boolean controlLoteUbicacion(Integer lote, String ub, Articulos art)
	{
		if(getCantidadXlote(lote,ub,art.getDesc())!=null)
			return true;
		else 
			return false;
	}

	public void insertarMovimiento(MovimientoStockUI msUI)
	{
		String causa =msUI.causaElegida;
		String sucursalOrigen= msUI.sucursalOrigen;
		String almacenOrigen= getAlmByDesc(msUI.almacenOrigen);
	//	System.out.println("msUI.ubicacionOrigen: "+msUI.ubicacionOrigen);
		String ubicacionOrigen= getFormatoUbicacion(msUI.ubicacionOrigen);
		System.out.println("ubicacionOrigen: "+ubicacionOrigen);
		String sucursalDestino= msUI.sucursalDestino;
		String almacenDestino=getAlmByDesc( msUI.almacenDestino);
		String ubicacionDestino= getFormatoUbicacion(msUI.ubicacionDestino);
		System.out.println("ubicacionDestino: "+ubicacionDestino);
		String cantidad= msUI.cantIngresada.toString();
		String art= msUI.articulo;
		String fecha= msUI.fecha;
		String nota= msUI.nota;
		String lote = msUI.lote;
		Articulos a= getArtByDesc(art);
		
		if(nota.equals(""))
			nota="null";
		if(ubicacionOrigen==null ||ubicacionOrigen.equals(""))
			ubicacionOrigen="null";
		if(sucursalOrigen==null || sucursalOrigen.equals(""))
			sucursalOrigen="null";
		if(almacenOrigen==null ||almacenOrigen.equals(""))
			almacenOrigen="null";
		if(fecha==null ||fecha.equals(""))
			fecha="";
		if(lote==null ||lote.equals(""))
			{lote=""; System.out.println("lote nulo");}
		
		mDao.insertarMovimiento(causa,sucursalOrigen,almacenOrigen,ubicacionOrigen,sucursalDestino,almacenDestino,ubicacionDestino,a,fecha,nota,cantidad, lote);
		
		
//		if(causa.equals("1")) //COMPRA
//		{Integer cp=obtenerCodigoPlano(a.getValor().toString());
//		StockSerializado s = new StockSerializado(Integer.parseInt(cantidad),cp);
//		s.upStockId(cantidad, ubicacionDestino, a.getValor().toString(),null,null);
//		}
//		
//		if(causa.equals("5"))
//		{
//			StockSerializado s = new StockSerializado();
//			System.out.println("lote ------------------"+ lote);
//			s.upStockId(cantidad, ubicacionDestino, a.getValor().toString(),ubicacionOrigen,lote);
//		}
//		
//		if(causa.equals("2")|| causa.equals("4"))
//		{
//		   StockSerializado s = new StockSerializado();
//		   s.delStock(cantidad,ubicacionDestino,a,lote);
//		}
		
		gestionSps(causa,cantidad,ubicacionDestino,a.getValor().toString(),lote,ubicacionOrigen);
		
	}
	
	
	public void gestionSps (String causa, String cantidad, String ubicacionDestino, String aId, String lote, String ubicacionOrigen)
	{
		if(causa.equals("1") || causa.equals("3")) //COMPRA
		{Integer cp=obtenerCodigoPlano(aId);
		StockSerializado s = new StockSerializado(Integer.parseInt(cantidad),cp);
		s.upStockId(cantidad, ubicacionDestino, aId,null,null);
		System.out.println("compro");
		}
		
		if(causa.equals("5"))
		{
			StockSerializado s = new StockSerializado();
			System.out.println("lote ------------------"+ lote);
			if(lote.equals("Indistinto"))
				lote=null;
			s.upStockId(cantidad, ubicacionDestino, aId,ubicacionOrigen,lote);
		}
		
		if(causa.equals("2")|| causa.equals("4"))
		{
		   StockSerializado s = new StockSerializado();
		   s.delStock(cantidad,ubicacionDestino,aId,lote);
		}
	}
	
	
	public Articulos getArtByDesc (String desc)
	{
		for (Articulos a:arts)
		{
			//System.out.println("articulos "+a.getDesc());
			if(a.getDesc().equals(desc))
				return a;
		}
		
		for (Articulos a:artsCompra)
		{
			//System.out.println("articulos "+a.getDesc());
			if(a.getDesc().equals(desc))
				return a;
		}
		//System.out.println("--------------------");
		return null;
	}
	
	public Integer obtenerCodigoPlano(String id)
	{
		return mDao.obetenerCP(id);
	}
	
	
	public String getAlmByDesc (String desc)
	{
		almacenes= mDao.getAlmacenes(null);
		if(almacenes!=null)
		for (ArrayList<String> a:almacenes)
		{
		//	System.out.println("almacen "+a.get(1));
			if(a.get(1).equals(desc))
				return a.get(0);
		}
		//System.out.println("--------------------");
		return null;
	}

	public String getFormatoUbicacion(String ub)
	{
		
		if(ub!=null)
		{String id= ub.split("\\)")[0];
		return id;
		}
		return "";

	}
	
	public ArrayList<String> obtenerUltimosMov()
	{
		return mDao.obtenerUltimosMov();
	}
	
	public void revertirCambio(String id)
	{
		mDao.revertirCambio(id,this,null);
		//gestionSps(causa,cantidad,ubicacionDestino,a,lote,ubicacionOrigen);
	}
	
	
}

