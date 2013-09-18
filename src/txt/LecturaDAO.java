package txt;

import java.beans.FeatureDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import persistencia.Conexion;

public class LecturaDAO {
	
	int idMovimiento=-1;
	
	public boolean insertarArchivoLeido(int archivo,boolean corrupto){
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
//			sb.append("SELECT MAX(id) ");
//			sb.append("FROM Remito ");
			sb.append("INSERT INTO [Archivos Leidos] ");
			sb.append("(numero,corrupto) ");
			sb.append("VALUES (?,?)");
		
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setInt(1, archivo);
			stm.setBoolean(2, corrupto);
//			stm.setInt(3, idMovimiento);
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
			sb.append("WHERE a.numero="+archivo+" AND corrupto=0 ");
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
	
	public int insertarMovimiento(int almacenDestino, ArrayList<Integer> articulos, ArrayList<Integer> cantidades,String fecha_despacho,String fecha_estim_arribo){
		
		Connection con;
		
		
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();

			sb.append("insert into Movimientos (almacen_destino,articulo_id,cantidad,causa_id,sucursal_destino,um_id,lugar_upd,fecha,fecha_despacho,fecha_estim_arribo) ");
			sb.append("values (?,?,?,1,5,1,'casa',GETDATE(),?,?) ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (int j=0;j<articulos.size();j++){
				stm.setInt(1,almacenDestino );
				stm.setInt(2, articulos.get(j));
				stm.setInt(3, cantidades.get(j));
				stm.setString(4, fecha_despacho);
				stm.setString(5, fecha_estim_arribo);
				stm.executeUpdate();
			}
			
//			String qry="SELECT MAX(id) FROM Movimientos";
//			stm=con.prepareStatement(qry);
//			ResultSet rs = null;
//			rs=stm.executeQuery();
//			rs.next();
//			idMovimiento=rs.getInt(1);
			// JOptionPane.showMessageDialog(null, "Despachado Correctamente");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error insertar movimientos. ");
			
		}
		return idMovimiento;
	}
	public int getAlmacenDestino(String alm){
		
		if(alm.toLowerCase().equals("p")){
			alm="Paso de los Libres";
		}
		if(alm.toLowerCase().equals("c")){
			alm="Cristo Redentor";
		}
		
		int idAlmacen=-1; 
		
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT id FROM Almacenes WHERE Descripcion=? ");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());	
			stm.setString(1, alm);
			rs = stm.executeQuery();
			if(rs.next()){
				idAlmacen=rs.getInt("id");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error insert archivos leidos. ");

		}
		
		return idAlmacen;
		
	}
	public void insertarOrdenCompra(ArrayList<Integer> proveedor,ArrayList<Integer> articulos,ArrayList<Integer> cantidades,ArrayList<Integer> lotes,String fecha_despacho, String fecha_arribo){
		Connection con;
		ResultSet rs = null;
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO [Orden de Compra](proveedor_id,articulo_id,cantidad,lote_proveedor,fecha_despacho,fecha_arribo) ");
			sb.append("VALUES(?,?,?,?,?,?) ");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());	
			for (int j=0;j<articulos.size();j++){
				stm.setInt(1,proveedor.get(j) );
				stm.setInt(2, articulos.get(j));
				stm.setInt(3,cantidades.get(j));
				stm.setInt(4,lotes.get(j));
				stm.setString(5,fecha_despacho);
				stm.setString(6,fecha_arribo);
				stm.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error insert archivos leidos. ");

		}
		
	}
	
}
