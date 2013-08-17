                                                                     
                                             
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

//import javax.swing.JTree;

import persistencia.Conexion;

public class Arbol {

	Conexion c=new Conexion();
	Connection cn= c.getConexion();
	String [][] bom = new String[100][100];
	String[][] desc = new String[100][100];
	int i,j=0;
	Nodo buscaNodo= null; //exclusivo para la funcion busar nodo porla recursiviad que me perdia el contexto 
	Nodo aDesc= null ; //Exclusivo para getnodoByDesc
	ArrayList<Nodo> padresPrincipales = new ArrayList<>();
	

	public Arbol(){
		InicializarArbol();
	}
	
	private void InicializarArbol(){

		//Obtener descripcion de Articulos
				try {
					StringBuilder sb=new StringBuilder();
					ResultSet descripcion = cn.prepareStatement("select a.id,descripcion_str from Articulo a,Descripcion d where a.descripcion_id=d.id").executeQuery();
					int f=0,c=0;
					while (descripcion.next()) {
				        	desc[f][c]=descripcion.getString("id");
				        	c++;
				        	desc[f][c]=descripcion.getString("descripcion_str");
							System.out.println("id: "+desc[f][0]);
							System.out.println("desc: "+desc[f][1]);
				        	
				        	f++;
				        	c=0;
				        }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
		//OBTENER PADRES PRINCIPALES
				try {
					StringBuilder sb=new StringBuilder();
					ResultSet padresP = cn.prepareStatement("Select distinct padre from BOM where padre not in (select hijo from BOM) and borrado=0").executeQuery();
					while (padresP.next()) {
				        	Nodo n =new Nodo (padresP.getInt("padre"));
						    padresPrincipales.add(n);
				        	for (int d=0; d<desc.length;d++)
							 { 
				        		if(desc[d][0]!=null && desc[d][0].equals((n.GetValor()).toString()))
								{
									n.setDescripcion(desc[d][1]);
								}
								
							 }
				        	
				        }
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		 //OBTENER TODA LA BOM
					try {
						ResultSet result= cn.prepareStatement("Select padre,hijo,cantidad,d.descripcion_str from BOM b,Descripcion d,[Unidad Medida]u where borrado=0 and b.um_id=u.id and u.descripcion_id=d.id").executeQuery();
				        
				        while (result.next()) {
				        	bom[i][j] = (result.getObject("padre")).toString();
				        	  j++;
				        	bom[i][j] = (result.getObject("hijo")).toString();
				        	 j++;
				        	bom[i][j] = (result.getObject("cantidad").toString());
				        	 j++;
					        bom[i][j] = (result.getObject("descripcion_str")).toString();
				        	  i++;
				        	j=0;  
				        	
				        	
				        }
		//Para cada padrePrincipal le arma el arbol		        
				     for(Nodo a: padresPrincipales)
				     {
					     ArmaArbol(bom, a);
				     }
							} catch (Exception e) {
								e.printStackTrace();
						}
//				MostrarArbol();	
				
					
					//getNodoByDescripcion ("Madera cuadrada para pata");
				//ArmaListaPadre (padresPrincipales.get(0).GetHijos().get(2).GetHijos().get(0));
			
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
						{	
							String padre= nodo.GetValor().toString(); 
						while (bom[k][0]!=null &&(bom[k][0].equals(padre)) && k<20)
					        { 
							 //System.out.println("Valor: "+nodo.GetValor().toString());
							
							System.out.println(bom[k][1]); 
							Nodo h = new Nodo(Integer.parseInt(bom[k][1]));
							 
							 h.setCantidad(Float.parseFloat(bom[k][2]));
							 h.setUm(bom[k][3]);
							 for (int d=0; d<desc.length;d++)
							 { 
								// System.out.println("desc: "+desc[d][0]+"-nodoV:"+h.GetValor() );
								if(desc[d][0]!=null && desc[d][0].equals((h.GetValor()).toString()))
								{
									//System.out.println("entro");
									h.setDescripcion(desc[d][1]);
								}
							 }
							 nodo.AgregarHijo(h);
							 System.out.println("Valor:"+nodo.GetValor()+" cantidad:"+h.getCantidad()+" desc:"+h.getDescripcion());
							 h.setPadre(nodo);
							 
							 System.out.println("Padre: "+nodo.GetValor()+"--hijo:"+h.GetValor());
							 ArmaArbol(bom,h);
							 k++;
							  
					        }
						}
						
						
					  }catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}

					 return;
					
	}
	
	
	public void MostrarArbol(){
		Jtree j=new Jtree(padresPrincipales);
	}
	
	public void MostrarArbol(Nodo a){
		Jtree j=new Jtree(a);
	}
	
	
	
	
	
	
	private StringBuilder MostrarArbol (StringBuilder sb, Nodo padre)
	{
			
//			Jtree j = new Jtree(padresPrincipales);
			
		
			System.out.println(sb.toString());
			System.out.println("||||||||||||||||||||||");
			sb.append(padre.GetValor());
			if(padre.GetHijos()!=null){
				
				sb.append("\n \t |");
				
					for (Nodo nodoH:padre.GetHijos()){
						System.out.println("hijo->"+nodoH.GetValor());
//						sb.append("\t");
//						System.out.println(sb.toString());
//						System.out.println("---------------");
						
						MostrarArbol(sb, nodoH);
						sb.append("\n \t |");
//						sb.append("\n \t |");
//						sb.append("\t");
//						sb.append(nodoH.GetValor());
//						System.out.println(sb.toString());
					}
			}
				
				
//				Jtree j = new Jtree(padresPrincipales);
				return sb;
	}


	public Nodo getNodoByDescripcion (String desc)
	{
		Nodo a= null;
		aDesc=null;
		for(Nodo padre: padresPrincipales)
		{
			a=IterarDesc(padre,desc);
			if(aDesc!=null)
			  {System.out.println(aDesc.getDescripcion()+"Nodo:"+ aDesc.GetValor());
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
	
	public ArrayList<StringBuilder> ArmaListaPadre (Nodo nodo)
	{
		ArrayList<StringBuilder> listaPadre = new ArrayList<>();
		for(Nodo padre: padresPrincipales)
		{
			//Encontre el nodo dentro del arbol, y llamo a una funcion con el nodo y un nuevo sb
			Nodo a=BuscarNodo(padre,nodo.GetValor());
			if(buscaNodo!=null)
			{
				listaPadre.add(new StringBuilder());
				StringBuilder stringB= new  StringBuilder();
				stringB.append(ArmaLista(buscaNodo, listaPadre.get(listaPadre.size()-1),0));
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
				if (a.GetValor().equals(valor))
				{ buscaNodo=a;
					return a; }
				else
					BuscarNodo(a,valor);
				//return a;
			}
			//return a;
		}	
		return a;
		
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
}
