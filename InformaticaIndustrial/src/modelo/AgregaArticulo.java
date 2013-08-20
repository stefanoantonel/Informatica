package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.Conexion;

public class AgregaArticulo {
	String descipcion;
	int materialID, umID, tipoID, centroCostoID, claseMercioID;
	String alto, ancho, profundidad, diametro, peso,color,costoStd, precioVtaStd,descripcionStd= descipcion,  descUpd="cargo"+ descipcion;
	ArrayList<String> um, material,tipo;
	
	
	
	
	public AgregaArticulo(){
		
	}
	public void InicializarUI(){
		
		
		Connection con;
		ResultSet rs;
		
		
		//----------------------------------------------ARRAY PARA LAS UNIDADES DE MEDIDA	
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT um.id [um id],d.descripcion_str ");
					sb.append("FROM [Unidad Medida] um ");
					sb.append("inner join Descripcion d on um.descripcion_id=d.id ");

					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							um.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
						}
					}
					con.close();
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				
				//----------------------------------------------ARRAY PARA tipo
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT tipo.id [um id],d.descripcion_str ");
					sb.append("FROM [Tipo] tipo ");
					sb.append("inner join Descripcion d on tipo.descripcion_id=d.id ");

					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							tipo.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
						}
					}
					con.close();
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				//----------------------------------------------ARRAY PARA material
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT ma.id [um id],d.descripcion_str ");
					sb.append("FROM [Material] ma ");
					sb.append("inner join Descripcion d on ma.descripcion_id=d.id ");

					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							material.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
						}
					}
					con.close();
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				
				
				
	}
}
