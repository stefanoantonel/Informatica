package modelo;
 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.Conexion;
import ui.*;

//import sql.Conexion;


public class Main {

	/**
	 * @param args
	 */
	
//	static ArrayList<String> um=new ArrayList<>(), material,tipo;
	public static void main(String[] args) {
		
//		Arbol ma = new Arbol();
//		ma.getNodoByDescripcion ("Madera cuadrada para pata");
//		EliminaRelacion elimina=new EliminaRelacion();
//		elimina.InicializarPadre();
//		AgregaRelacion ag=new AgregaRelacion();
//		ag.InicilizarUI();
		
		
		
//		Connection con;
//		ResultSet rs;
//		
//		
//		//----------------------------------------------ARRAY PARA LAS UNIDADES DE MEDIDA	
//				try {
//					Conexion cn2 = new Conexion();
//					con = cn2.getConexion();
//					StringBuilder sb = new StringBuilder();
//					sb.append("SELECT um.id [um id],d.descripcion_str ");
//					sb.append("FROM [Unidad Medida] um ");
//					sb.append("inner join Descripcion d on um.descripcion_id=d.id ");
//					
//					PreparedStatement stm;
//					stm = con.prepareStatement(sb.toString());
//					rs = stm.executeQuery();
//				
//					while (rs.next()) {
//						try {
//							System.out.println(rs.getInt(1));
//							String a=rs.getString(2);
//							System.out.println(a);
//							um.add(a);
//							
//						} catch (Exception e) {
//							System.out.println("error new nodo padre");
//							e.printStackTrace();
//						}
//					}
//					
//				}
//				catch (Exception e) {
//					System.out.println("error reporta conexion" );
//					e.printStackTrace();
//				}
//				
				
		//AgregaArticulo aa=new AgregaArticulo();
		//aa.InicializarUI();
		String a="111122222223";
		System.out.println("1: "+a.substring(0, 3));
		System.out.println("2: "+a.substring(4, 10));
		System.out.println("3: "+a.substring(11, 12));
	
		
	}
	

}
