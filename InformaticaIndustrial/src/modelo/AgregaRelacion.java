package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import persistencia.Conexion;
import ui.AgregaRelacionUI;

public class AgregaRelacion {
	Nodo nodo;
	int padreId;
	int hijoId;
	int umId;

	public AgregaRelacion() {

	}

	public void InsertarRelacion(String padreDesc, String hijoDesc,String cantidad, String un ) {
		Connection con;
		ResultSet rs=null;

		try {//---------------------------------ID DEL PADRE
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SElECT a.id ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str= ? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			String a=padreDesc;
			stm.setString(1, a);			
			rs = stm.executeQuery();
			rs.next();
			padreId=rs.getInt(1);
			//-------------------------------------ID DEL HIJO
			stm = con.prepareStatement(sb.toString());
			a=hijoDesc;
			stm.setString(1, a);
			rs = stm.executeQuery();
			rs.next();
			hijoId=rs.getInt(1);

		} catch (Exception e) {
			System.out.println("error buscar id con descripcion");
			e.printStackTrace();
		}
		
		
		
		try{//---------------------------------ID UM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT um.id [um id],d.descripcion_str ");
			sb.append("FROM [Unidad Medida] um ");
			sb.append("inner join Descripcion d on um.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str = ? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			stm.setString(1, un);
			
			rs = stm.executeQuery();
			rs.next();
			umId=rs.getInt(1);
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		
		try{//---------------------------------INSERTAR
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO BOM (padre,hijo,cantidad,um_id,fecha_inicio,fecha_fin,user_id,descr_upd,lugar_upd) ");
			sb.append("VALUES (?,?,?,?,GETDATE(),null,2,'cargo bom','casa')");
			
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			stm.setInt(1, padreId);
			stm.setInt(2, hijoId);
			stm.setString(3, cantidad);
			stm.setInt(4, umId);
			stm.executeUpdate();
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		
		JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
	}

	public void InicilizarUI() {

		Connection con;
		ResultSet rs;

		DefaultListModel<String> modelo1 = new DefaultListModel<>();
		DefaultListModel<String> modelo2 = new DefaultListModel<>();
		ArrayList<String> um=new ArrayList<>();
		
		//------------------------------------------MODELO PARA EL PADRE
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id[Articulo ID], d.descripcion_str[Descripcion] ,a.tipo_id[Tipo ID] ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id = d.id ");
			sb.append("WHERE a.tipo_id=1 ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					nodo = new Nodo(rs.getInt(1));
					nodo.setDescripcion(rs.getString(2));
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}

				// String a = rs.getString(2);

				try {
					modelo1.addElement(nodo.getDescripcion());
					
				} catch (Exception e) {
					System.out.println("erros add eleme");
					e.printStackTrace();
				}

			}
			con.close();
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}

		//---------------------------------------------------MODELO PARA EL HIJO
		try {
			Conexion cn2 = new Conexion();
			con = cn2.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id[Articulo ID], d.descripcion_str[Descripcion] ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id=d.id ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					nodo = new Nodo(rs.getInt(1));
					nodo.setDescripcion(rs.getString(2));
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}

				// String a = rs.getString(2);

				try {
					modelo2.addElement(nodo.getDescripcion());
				} catch (Exception e) {
					System.out.println("erros add eleme");
					e.printStackTrace();
				}
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}
		
		
		//----------------------------------------------MODELO PARA LAS UNIDADES	
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
		} catch (Exception e) {
			System.out.println("error reporta conexion" );
			e.printStackTrace();
		}
		AgregaRelacionUI rep = new AgregaRelacionUI(modelo1, modelo2,um.toArray());
		rep.setVisible(true);
	}
}
