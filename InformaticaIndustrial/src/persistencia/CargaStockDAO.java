package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CargaStockDAO {
	ArrayList<Integer> codigoPlano;
	ArrayList<Integer> cantidad;
	
	public ArrayList<Integer> getCodigoPlano() {
		return codigoPlano;
	}

	public ArrayList<Integer> getCantidad() {
		return cantidad;
	}

	public void getPlanoCantidad() {
		codigoPlano = new ArrayList<>();
		cantidad = new ArrayList<>();

		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT codigo_plano,COUNT (*)");
			sb.append("FROM [Stock Productos Serializados]");
			sb.append("GROUP BY codigo_plano");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				codigoPlano.add(rs.getInt(1));
				cantidad.add(rs.getInt(2));
				// StringBuilder sb1=new StringBuilder();
				// sb1.append(rs.getString(1));
				// sb1.append(rs.getString(2).trim());
				// sb1.append(rs.getString(3));
				//
				// listaCodigosExistentes.add(sb1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de articulos existentes");
		}
	}
	
	public void insertarStock(int codigoPlano,ArrayList<String> serie, ArrayList<Integer> verificador){
	
		Connection con;
		ResultSet rs=null;
		
		
		try{//---------------------------------ID UM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO [Informatica].[dbo].[Stock Productos Serializados]");
			sb.append("([codigo_plano],[numero_serie],[verificador],[estado_id],[desc_upd])");
			sb.append("VALUES (?,?,?,'1',?)");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			for(int i=0;i<serie.size();i++){
				stm.setInt(1, codigoPlano);
				stm.setString(2,serie.get(i));
				stm.setInt(3,verificador.get(i));
				stm.setString(4,"cargo serie de producto "+codigoPlano);
				
				stm.executeUpdate();
				
			}
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "error ingresar serie");}
		
	}
	
	public int obtenerUltimoSerie(int plano){
		Connection con;
		ResultSet rs=null;
		
		int ultimoSerie=0;
		
		try{//---------------------------------ID UM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) ");
			sb.append("FROM [Informatica].[dbo].[Stock Productos Serializados] ");
//			int p=Integer.parseInt(plano);
			sb.append("WHERE [codigo_plano]="+plano+" ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			rs=stm.executeQuery();
			if(rs.next()){
				ultimoSerie=rs.getInt(1);
			}
			else{
				ultimoSerie=0;
			}
			
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "error ingresar serie");
		}
		
		return ultimoSerie;
	}
	
	public ArrayList<ArrayList<String>> obtenerArticulosStock(){
		Connection con;
		ResultSet rs;

		ArrayList<ArrayList<String>> articuloCodigo=new ArrayList<>();
		
		
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
	
			sb.append("SELECt distinct a.codigo_plano , d.descripcion_str ");
			sb.append("FROM [Stock Productos Serializados] s ");
			sb.append("INNER JOIN Articulo a on a.codigo_plano=s.codigo_plano ");
			sb.append("INNER JOIN Descripcion d on d.id=a.descripcion_id ");
			
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					ArrayList<String> fila= new ArrayList();
					fila.add(String.valueOf(rs.getString(1)));
					fila.add(rs.getString(2));
					articuloCodigo.add(fila);
//					nodo.setDescripcion();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error obtener articulo stock ");
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
		return articuloCodigo;
	}
	
	public ArrayList<String> cargarListaCodigosExistentes() {
		ArrayList<String> listaCodigosExistentes = new ArrayList<>();
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT [codigo_plano],[numero_serie],[verificador] ");
			sb.append("FROM [Stock Productos Serializados] stock ");
			sb.append("WHERE estado_id=1 ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(rs.getString(1));
				sb1.append(rs.getString(2).trim());
				sb1.append(rs.getString(3));

				listaCodigosExistentes.add(sb1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de articulos existentes");
		}
		return listaCodigosExistentes;
	}
	
	public void ponerDespachadoArticulos(ArrayList<String> plano,ArrayList<String> serie, ArrayList<String> verificacion) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Stock Productos Serializados] ");
			sb.append("SET estado_id=3 ");
			sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (int i = 0; i < serie.size(); i++) {
				stm.setString(1, plano.get(i));
				stm.setString(2, serie.get(i));
				stm.setString(3, verificacion.get(i));
				stm.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Despachado Correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error poner despachado");
		}
	}
	
	public void ponerEsperaArticulos(ArrayList<String> plano, ArrayList<String> serie,ArrayList<String> verificacion) {
			Connection con;
			ResultSet rs = null;
			try {// ---------------------------------select todos en stock
				Conexion cn1 = new Conexion();
				con = cn1.getConexion();
				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE [Stock Productos Serializados] ");
				sb.append("SET estado_id=2 ");
				sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
				// PREPARAR CONSULTA
				PreparedStatement stm;
				stm = con.prepareStatement(sb.toString());
				for (int i = 0; i < serie.size(); i++) {
					stm.setString(1, plano.get(i));
					stm.setString(2, serie.get(i));
					stm.setString(3, verificacion.get(i));
					stm.executeUpdate();
				}
				JOptionPane.showMessageDialog(null, "Puesto en espera");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "error poner espera stock");
			}
		}
	
}
