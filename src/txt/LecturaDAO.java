package txt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import persistencia.Conexion;

public class LecturaDAO {
	
	public boolean insertarArchivoLeido(int archivo,boolean cifrado){
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
//			sb.append("SELECT MAX(id) ");
//			sb.append("FROM Remito ");
			sb.append("INSERT INTO [Archivos Leidos] ");
			sb.append("(numero,cifrado) ");
			sb.append("VALUES (?,?)");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setInt(1, archivo);
			stm.setBoolean(2, cifrado);
			stm.executeUpdate();
			
			// JOptionPane.showMessageDialog(null, "Despachado Correctamente");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error insert archivos leidos. ");
			return false;
		}
		return true;
	}
	
	public boolean fueLeido(int archivo){
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT id ");
			sb.append("FROM [Archivos Leidos] a ");
			sb.append("WHERE a.numero="+archivo+" AND cifrado=0 ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());	
			rs = stm.executeQuery();
			if(rs.next()){
				return true;
			}
			
				
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error insert archivos leidos. ");
//			return false;
		}
//		System.out.println("el archivo "+archivo+" fue leido");
//		return true;
		return false;
	}
	

}
