package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import persistencia.Conexion;
import ui.EliminiaRelacionUI;

public class EliminaRelacion {
	
	int padreId;
	int hijoId;
	
	public void InicializarPadre() {
		Connection con;
		ResultSet rs;
		//dejo el integer
		DefaultListModel<String> modeloPadre = new DefaultListModel<>();
		DefaultListModel<String> modelo2 = new DefaultListModel<>();
//		ArrayList<String> um = new ArrayList<>();

		// ------------------------------------------MODELO PARA EL PADRE
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT distinct b.padre [Articulo id], d.descripcion_str [Descripcion] ");
			sb.append("FROM BOM b, Articulo a, Descripcion d ");
			sb.append("WHERE b.padre=a.id AND a.descripcion_id=d.id AND b.borrado=0");
			
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();


			while (rs.next()) {

				try {
//					modeloPadre.add(rs.getInt(1), rs.getString(2));
//					int indice=0;
					String descripcion =rs.getString(2);
					
//					modeloPadre.setElementAt(descripcion,indice);
					modeloPadre.addElement(descripcion);
					
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

		
		
		EliminiaRelacionUI elim = new EliminiaRelacionUI(modeloPadre);
		elim.setVisible(true);

	}

	public DefaultListModel<String> InicializarHijo(Nodo nodo){
		DefaultListModel<String> modeloHijo = new DefaultListModel<>();
		//--------------------busco los hijos con el nodo padre
		ArrayList<Nodo> hijos=new ArrayList();
		hijos=nodo.GetHijos();
		for (Nodo n:hijos){
			modeloHijo.addElement(n.getDescripcion());
		}
		return modeloHijo;
	}

	public void Eliminacion(String padreDesc, String hijoDesc){
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
		
		try{
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE BOM ");
			sb.append("SET borrado=1 ");
			sb.append("WHERE padre="+padreId+" and hijo= "+hijoId+"");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());	
			stm.executeUpdate();	
			JOptionPane.showMessageDialog(null, "Borrado con exito");
			
		}catch (Exception e) {
			System.out.println("error pdate eliminar");
			e.printStackTrace();
		}
	}
}
