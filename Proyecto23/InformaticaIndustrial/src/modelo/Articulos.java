package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import persistencia.Conexion;

public class Articulos {

	private Integer valor;
	
	public Articulos(){
		
	}
	
	public Articulos(Integer id){
		valor=id;
	}
	
	public Integer getValor()
	{
		return valor;
	}
	
	
	public ArrayList<String> ObtenerArticulos(){
		
		Connection con;
		ResultSet rs;

		ArrayList<String> articulo=new ArrayList<>();
		
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id[Articulo ID], d.descripcion_str[Descripcion] ,a.tipo_id[Tipo ID] ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id = d.id ");
			sb.append("where a.id not in (select hijo from BOM where borrado=1)" +
					  " and a.id not in (select a.id from Articulo a where a.tipo_id=3) ");
			

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					articulo.add(rs.getString(2));
//					nodo.setDescripcion();
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}

//				// String a = rs.getString(2);
//
//				try {
//					modelo1.addElement(nodo.getDescripcion());
//					
//				} catch (Exception e) {
//					System.out.println("erros add eleme");
//					e.printStackTrace();
//				}

			}
			
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}
		return articulo;

	}
}
