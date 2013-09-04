                                                                     
                                             
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import persistencia.ArbolDAO;
import persistencia.Conexion;
import utilidades.DateClass;
//import javax.swing.JTree;

public class Arbol {
	ArbolDAO adao;
	
	String [][] bom;
	ArrayList<Nodo> padresPrincipales = new ArrayList<>();
	ArrayList<Nodo> listaArt = new ArrayList<>();
	Nodo NodobyID = new Nodo();
	Nodo buscaNodo = new Nodo();
	DateClass date = new DateClass();
	Nodo aDesc=null;
	ArbolDAO arbolDAO;
	
	
//	public static void main(String[] args) {
//		Arbol x = new Arbol();
//		
//	}
	
	
	public Arbol(){
		arbolDAO = new ArbolDAO();
		bom=arbolDAO.getBomMatriz();
		padresPrincipales = arbolDAO.obtenerPadresPrincipales();
		
//		BOM
//	    0padre
//	    1hijo
//	    2descripcion
//	    3cantidad
//	    4descripcion cantidad
//	    5tipo
//	    6generico
		
		
		InicializarArbol();
		//ArrayList<Nodo> m=getNodoByArticulo(5);
	}
	
	
	
	private void InicializarArbol(){		
	try{
		//Para cada padrePrincipal le arma el arbol		        
				     for(Nodo a: padresPrincipales)
				     {
					     ArmaArbol(bom, a);
				     }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problema en armar arbol en Arbol.java");
		}
	}
	
	//bom: lo que levanto de la tabla BOM transformado en un array
	//k: posicion de la bom que se esta usando en este momento
	
	private void ArmaArbol(String bom[][],Nodo nodo)
	{
				//   La primera vez entra con el nodo padreprincipal
				//	 recorre la bom hasta encontrar q el padre coincide, 
				//	 una vez que lo encuentra setea al hijo segun la segunda columna de la bom
				//	 y llama a la funcion con el hijo 
				//	 cuando no tiene mas hijo, aumenta el k y pregunta de ahi para abajo (bom[k][0])= nodo.getPadre
				//
		
					try{	
						
						for (int k=0; k<bom.length;k++)
						{	// obtengo el id del articulo de ese nodo
							String padre= nodo.getArt().getValor().toString(); 
						while (bom[k][0]!=null &&(bom[k][0].equals(padre)) && k<30)
					        { 
							//Seteo idArt, idNodo
							Nodo h = new Nodo(Integer.parseInt(bom[k][1]),Integer.parseInt(bom[k][6]));
							 
						//	System.out.println(bom[k][3]);
							 h.setDescripcion(bom[k][2]);
							 h.setCantidad(Float.parseFloat(bom[k][3]));
							 h.setUm(bom[k][4]);
							 h.setTipo(Integer.parseInt(bom[k][5]));
							 h.setFecha_inicio(bom[k][6]);
							 h.setFecha_fin(bom[k][7]);
							 
						//	 System.out.println("fecha inicio BOM: "+ h.getFecha_inicio());
							 
							 nodo.AgregarHijo(h);
							// System.out.println("Valor:"+nodo.getId()+" cantidad:"+h.getCantidad()+" desc:"+h.getDescripcion());
							 h.setPadre(nodo);
							
							 //System.out.println("ARBOL: ");
							 //System.out.println("Padre: "+nodo.getArt().getValor()+"--hijo:"+h.getArt().getValor());
							 
							 ArmaArbol(bom,h);
							k++;
							  
					        }
						}
						
						
					  }catch (Exception e) {
							e.printStackTrace();
							System.out.println("Error en ArmaArbol");
					}

					 return;			
	}
	
	
//	public void MostrarArbol(){
//		Jtree j=new Jtree(padresPrincipales, padresPrincipalesE);
//	}
	
	public void MostrarArbol(String fecha){
	
		//transformo fecha en un objeto tipo Date y lo paso por parametro
		
		Jtree j=new Jtree(padresPrincipales, date.obtenerFecha(fecha));
	}
	
	
	public void MostrarArbol(Nodo a,float cant,String fecha){
		//explosiona el nodo
		
		Jtree j=new Jtree(a,cant,date.obtenerFecha(fecha));
	}
	

	public Nodo getNodoByDescripcion (String desc)
	{
		Nodo a= null;
		aDesc=null;
		for(Nodo padre: padresPrincipales)
		{
			a=IterarDesc(padre,desc);
			if(aDesc!=null)
			  {System.out.println(aDesc.getDescripcion()+"Nodo:"+ aDesc.getId());
			  	return aDesc;
			  }
		}
			
		return a;
	}
	
	private Nodo IterarDesc(Nodo nodo, String desc)
	{
		if (nodo.GetHijos()!= null)
		{   Nodo a=nodo;
			if(a.getDescripcion().equals(desc)){
				aDesc=a;
				return a;
			}
			Iterator<Nodo> ListaHijos = nodo.GetHijos().iterator();
			
			while (ListaHijos.hasNext())
			{
				a= ListaHijos.next();
				if (a.getDescripcion().equals(desc))
				{
					aDesc=a;
					return a; }
				else
					IterarDesc(a,desc);
			}
			
		}	
		return null;
	 
	}
	
	//Buscar nodo segun el arituclo devuelve una lista
	public ArrayList<Nodo> getNodoByArticulo (Integer idArt)
	{
		//ArrayList<Nodo> a= null;
		listaArt=new ArrayList<>();
		Nodo resultado;
		for(Nodo padre: padresPrincipales)
		{
			resultado=IterarArt(padre,idArt);
			if(resultado!=null)
			   listaArt.add(resultado);
		}
			
		return listaArt;
	}
	
	private Nodo IterarArt(Nodo nodo, Integer idArt)
	{
		if (nodo.GetHijos()!= null)
		{   Nodo a=nodo;
			if(a.getArt().getValor().equals(idArt)){
				listaArt.add(a);
				return a;
			}
			Iterator<Nodo> ListaHijos = nodo.GetHijos().iterator();
			
			while (ListaHijos.hasNext())
			{
				a= ListaHijos.next();
				if (a.getArt().getValor().equals(idArt))
				{
					listaArt.add(a);
					return a; }
				else
					IterarArt(a,idArt);
			}
			
		}	
		return null;
	 
	}
	
	
	
//	public Nodo getNodoByArt (Integer idArt)
//	{
//		//ArrayList<Nodo> a= null;
//		Nodo NodoArt=null;
//		for(Nodo padre: padresPrincipales)
//		{
//			if(NodoArt!=null)
//			  {System.out.println(NodobyID.getDescripcion()+"Nodo:"+ NodobyID.getId());
//			  	return NodobyID;
//			  }
//		}
//			
//		return listaArt;
//	}
//	
//	private Nodo IterarArt1(Nodo nodo, Integer idArt)
//	{
//		if (nodo.GetHijos()!= null)
//		{   Nodo a=nodo;
//			if(a.getArt().getValor().equals(idArt)){
//				listaArt.add(a);
//				return a;
//			}
//			Iterator<Nodo> ListaHijos = nodo.GetHijos().iterator();
//			
//			while (ListaHijos.hasNext())
//			{
//				a= ListaHijos.next();
//				if (a.getArt().getValor().equals(idArt))
//				{
//					listaArt.add(a);
//					return a; }
//				else
//					IterarArt(a,idArt);
//			}
//			
//		}	
//		return null;
//	 
//	}
//	
	
	
	public ArrayList<Nodo> getNodoByIdBom (Integer id)
	{
		//Devuelve un ArrayList de Nodos, con dos nodos, el padre y el hijo para ese id
		ArrayList<Nodo> nodosBom = new ArrayList<>();
		ArrayList<Integer> intBom = new ArrayList<>();
		intBom=arbolDAO.buscaBom(id);
			nodosBom.add(getNodoByArticulo(intBom.get(0)).get(0));
			nodosBom.add(getNodoByArticulo(intBom.get(1)).get(0));
			
		return nodosBom;
	}
	
	
	
	//BUSCAR NODO POR ID
	public Nodo getNodoById (Integer id)
	{
		Nodo a= null;
		NodobyID=null;
		for(Nodo padre: padresPrincipales)
		{
			a=IterarId(padre,id);
			if(NodobyID!=null)
			  {System.out.println(NodobyID.getDescripcion()+"Nodo:"+ NodobyID.getId());
			  	return NodobyID;
			  }
		}
			
		return a;
	}
	
	
	
	private Nodo IterarId(Nodo nodo, Integer id)
	{
		if (nodo.GetHijos()!= null)
		{   Nodo a=nodo;
			if(a.getId().equals(id)){
				NodobyID=a;
				return a;
			}
			Iterator<Nodo> ListaHijos = nodo.GetHijos().iterator();
			
			while (ListaHijos.hasNext())
			{
				a= ListaHijos.next();
				if (a.getId().equals(id))
				{
					NodobyID=a;
					return a; }
				else
					IterarId(a,id);
			}
			
		}	
		return null;
	 
	}
	
	// TERMINA BUSCAR NODO POR ID
	
	//id del nodo hijo que hay que eliminar
	public  void eliminarHijo(Integer id)
	{
		//obtengo todos los nodos que tengan el articulo con el id que viene por parametro
		ArrayList<Nodo> listaNodo=getNodoByArticulo(id);
		
		for (Nodo nodo: listaNodo)
		{
			System.out.println("ElimarHijo "+nodo.getArt().getValor());
		}
		
	}
	
	
	
	
	public ArrayList<StringBuilder> ArmaListaPadre (Nodo nodo)
	{
		ArrayList<StringBuilder> listaPadre = new ArrayList<>();// crear nuevos StringBuilder
		for(Nodo padre: padresPrincipales)
		{
			//Encontre el nodo dentro del arbol, y llamo a una funcion con el nodo y un nuevo sb
			buscaNodo=null;
			Nodo a=BuscarNodo(padre,nodo.getId()); //cuando entra a la funcion lo setea
			if(buscaNodo!=null)
			{
				listaPadre.add(new StringBuilder());
				StringBuilder stringB= new  StringBuilder();
				stringB.append(ArmaLista(buscaNodo, listaPadre.get(listaPadre.size()-1),0)); //llama a la funcion con el nodo, un nuevo StringBuilder
				stringB = new StringBuilder(stringB.substring(0, stringB.length()-2));
				listaPadre.set(listaPadre.size()-1,stringB);
				System.out.println(listaPadre.get(listaPadre.size()-1));
			}
		}
		return listaPadre;
	}
	
	private Nodo BuscarNodo (Nodo nodo, Integer valor)
	{
		Nodo a= null;	
		if (nodo.GetHijos()!= null)
		{   Iterator<Nodo> ListaHijos = nodo.GetHijos().iterator();
			//Nodo a= new Nodo(-1);
			while (ListaHijos.hasNext())
			{
				a= ListaHijos.next();
				if (a.getId().equals(valor))
				{ buscaNodo=a;
					return null; }
				else
					BuscarNodo(a,valor);
				//return a;
			}
			//return a;
		}	
		return null;
		
	}
	//siempre llamar esta funcion con un 0 al final
	private StringBuilder ArmaLista (Nodo nodo, StringBuilder sb, int primeraVezCero)
	{
		if(primeraVezCero==0)
			sb.append("El Articulo "+nodo.getDescripcion()+" Compone a: ");
		while (nodo.getPadre()!=null)
		{
			sb.append(nodo.getPadre().getDescripcion().toString()+", ");
			ArmaLista(nodo.getPadre(), sb,primeraVezCero+1);
			return sb;
			//sigue llamando con un 5
		}
		return sb;
	}
	
	public ArrayList<Nodo> getPadresPrincipales ()
	{
		return padresPrincipales;
	}
	/*
	public void getAlternativos (Nodo x)
	{
		Integer padre=null;
		if(x.getPadre()!=null)
		   padre=x.getPadre().getId();
		Integer hijo=x.getId();
		Integer id_generico=null;
		String principal_id= "Select generico_id from Bom where padre="+padre+" and hijo=" +hijo+ " and borrado=0";
		PreparedStatement stm;
		
		try {
			Conexion cc= new Conexion();
			Connection con= cc.getConexion();
			stm = con.prepareStatement(principal_id);
			ResultSet rs = stm.executeQuery();
			rs.next();
			id_generico=rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String alternativos = "Select artGenerico_id from [Articulos Alternativos] where artPrincipal_id="+ id_generico;		
		try {
			Conexion cc2= new Conexion();
			Connection con2= cc2.getConexion();
			stm = con2.prepareStatement(alternativos);
			ResultSet rs = stm.executeQuery();
			while (rs.next())
			{
				System.out.println(rs.getInt(1));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public ArrayList<Nodo> obtenerPadres ()
	{
		arbolDAO = new ArbolDAO();
		return arbolDAO.obtenerPadres();
	}
	
}
