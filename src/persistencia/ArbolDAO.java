package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Nodo;

import persistencia.Conexion;

public class ArbolDAO {

	Conexion c=new Conexion();
	Connection cn= c.getConexion();
	private String selectBom;
	private String selectPadres;
	ArrayList<Nodo> padresPrincipales = new ArrayList<>();
	private String bom[][]= new String[200][200];
	
	public ArbolDAO()
	{
		//TRAE TODA la estructura del arbol, sin importar la fecha
		StringBuilder select = new StringBuilder();
		select.append("Select padre,hijo,d2.descripcion_str descH,cantidad,d.descripcion_str descC,a.tipo_id, generico_id,Convert(varchar(10),fecha_inicio) inicio,Convert(varchar(10),fecha_fin) fin ");
		select.append(" from BOM b inner join Articulo a on a.id=b.hijo inner join [Unidad Medida]u on b.um_id=u.id inner join Descripcion d on u.descripcion_id=d.id ");
		select.append(" inner join Descripcion d2 on a.descripcion_id=d2.id");
		selectBom = select.toString();
		int i=0,j=0;
		try {
			ResultSet result= cn.prepareStatement(selectBom).executeQuery();
	        
	        while (result.next()) {
	        	bom[i][j] = (result.getObject("padre")).toString();
	        	  j++;
	        	bom[i][j] = (result.getObject("hijo")).toString();
	        	 j++;
		        bom[i][j] = (result.getObject("descH").toString());
		         j++;
	        	bom[i][j] = (result.getObject("cantidad").toString());
	        	 j++;
		        bom[i][j] = (result.getObject("descC")).toString();
		        j++;
		        bom[i][j] = (result.getObject("tipo_id")).toString();
		        j++;
		        bom[i][j] = (result.getObject("generico_id")).toString();
		        j++;
		        bom[i][j] = (result.getObject("inicio")).toString();
		        j++;
		        Object fin= result.getObject("fin");
		        if (fin!=null)
		            bom[i][j] = (fin).toString();
		        else
		        	bom[i][j]=null;
	        	
		        i++;
	        	j=0;  
	             }
	        	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error query BOM");
		}
	
	}
/*	public ArbolDAO(String fecha)
	{
		StringBuilder select = new StringBuilder();
		select.append("Select padre,hijo,cantidad,d.descripcion_str,a.tipo_id, generico_id ");
		select.append(" from BOM b inner join Articulo a on a.id=b.hijo inner join [Unidad Medida]u on b.um_id=u.id inner join Descripcion d on u.descripcion_id=d.id ");
		//CAST DATE
		select.append(" where fecha_inicio<="+fecha+" and (fecha_fin>="+fecha+" or fecha_fin is NULL)");
		selectBom = select.toString();
		
		StringBuilder sb=new StringBuilder();
		sb.append("Select distinct padre from BOM where padre not in (select distinct hijo from BOM) and");
		sb.append(" padre not in (select hijo from BOM)and");
		sb.append(" borrado=0 and fecha_inicio<="+fecha+" and (fecha_fin>="+fecha+" or fecha_fin is NULL)");
		selectPadres = sb.toString();
	}*/
	
	public  ArrayList<Nodo> obtenerPadresPrincipales()
	{
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("Select distinct padre,generico_id,descripcion_str,Convert(varchar(10),fecha_inicio) inicio,Convert(varchar(10),fecha_fin) fin");
			sb.append(" from BOM,Articulo,Descripcion");
			sb.append(" where padre not in (select distinct hijo from BOM) and");
			sb.append(" padre not in (Select distinct artAlternativo_id from [Articulos Alternativos] where artAlternativo_id not in (select hijo from BOM))and");
			//sb.append(" fecha_inicio<GETDATE() and (fecha_fin>GETDATE() or fecha_fin is null) and");
		    sb.append("	BOM.padre=Articulo.id and");
		    sb.append(" Descripcion.id=Articulo.descripcion_id");

			ResultSet padresP = cn.prepareStatement(sb.toString()).executeQuery();
			while (padresP.next()) {  //controlar el idNodos
		        	Nodo n =new Nodo (padresP.getInt("padre"),padresP.getInt("generico_id"));
		        	n.setDescripcion(String.valueOf(padresP.getObject("descripcion_str")));
		        	n.setFecha_inicio(String.valueOf(padresP.getObject("inicio")));
		        	
		        	Object fin= padresP.getObject("fin");
			        if (fin!=null)
			        	n.setFecha_fin(String.valueOf(fin));
			        else
			        	n.setFecha_fin(null);
		        	//1:Make
		        	int tipo= 1;
		        	n.setTipo(tipo);
				    padresPrincipales.add(n);
		        }
			return padresPrincipales;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problema en query padresPrincipales");
		}
		return padresPrincipales;
	}
	
	public String[][] getBomMatriz()
	{
		return bom;
	}
	
	
	public ArrayList<Integer> buscaBom (Integer id)
	{
		ArrayList<Integer> bomBuscado= new ArrayList<>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("Select padre,hijo");
			sb.append(" from BOM");
			sb.append(" where generico_id="+id);

			ResultSet Resultbom = cn.prepareStatement(sb.toString()).executeQuery();
			while (Resultbom.next()) {  //controlar el idNodos
				bomBuscado.add(Resultbom.getInt("padre"));
				bomBuscado.add(Resultbom.getInt("hijo"));
				System.out.println("BOM BUSCADO:"+bomBuscado.get(0)+"  "+bomBuscado.get(1));
		        }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problema en buscaBom");
		}
		return bomBuscado;	
		
	}
	
	
	public  ArrayList<Nodo> obtenerPadres()
	{
		 ArrayList<Nodo> padres = new ArrayList<>();
		try {
			StringBuilder sb=new StringBuilder();
			sb.append("Select distinct padre,descripcion_str");  //,generico_id,descripcion_str,Convert(varchar(10),fecha_inicio) inicio,Convert(varchar(10),fecha_fin) fin
			sb.append(" from BOM,Articulo,Descripcion");
			sb.append("  where padre not in (select distinct hijo from BOM where hijo not in (select distinct padre from BOM)) and");
			sb.append(" padre not in (Select distinct artAlternativo_id from [Articulos Alternativos] where artAlternativo_id not in (select hijo from BOM))and");
			//sb.append(" fecha_inicio<GETDATE() and (fecha_fin>GETDATE() or fecha_fin is null) and");
		    sb.append("	BOM.padre=Articulo.id and");
		    sb.append(" Descripcion.id=Articulo.descripcion_id");

			ResultSet padresP = cn.prepareStatement(sb.toString()).executeQuery();
			while (padresP.next()) {  //controlar el idNodos
				Nodo n =new Nodo (padresP.getInt("padre"));
				n.setDescripcion(String.valueOf(padresP.getObject("descripcion_str")));
//		        	Nodo n =new Nodo (padresP.getInt("padre"),padresP.getInt("generico_id"));
//		        	n.setDescripcion(String.valueOf(padresP.getObject("descripcion_str")));
//		        	n.setFecha_inicio(String.valueOf(padresP.getObject("inicio")));
//		        	
//		        	Object fin= padresP.getObject("fin");
//			        if (fin!=null)
//			        	n.setFecha_fin(String.valueOf(fin));
//			        else
//			        	n.setFecha_fin(null);
		        	//1:Make
		        	int tipo= 1;
		        	n.setTipo(tipo);
				    padres.add(n);
		        }
			return padres;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problema en query padresPrincipales");
		}
		padres=null;
		return padres;
	}
	
}
