package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RemitoDAO {
	
	ArrayList<String> listaCodigosExistentes;

	public void cargar(){
		listaCodigosExistentes=new ArrayList<>();
		Connection con;
		ResultSet rs=null;
		try{//---------------------------------select todos en stock 
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT [codigo_plano],[numero_serie],[verificador] ");
			sb.append("FROM [Stock Productos Serializados] stock ");
			sb.append("WHERE estado=1 ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs=stm.executeQuery();
			while(rs.next()){
				StringBuilder sb1=new StringBuilder();
				sb1.append(rs.getString(1));
				sb1.append(rs.getString(2).trim());
				sb1.append(rs.getString(3));
				
				listaCodigosExistentes.add(sb1.toString());
			}
		
		}catch (Exception e){e.printStackTrace();
		JOptionPane.showMessageDialog(null, "error en la carga de articulos existentes");}
	}

	public ArrayList<String> getListaCodigosExistentes() {
		return listaCodigosExistentes;
	}

	public void setListaCodigosExistentes(ArrayList<String> listaCodigosExistentes) {
		this.listaCodigosExistentes = listaCodigosExistentes;
	}
	
	public void ponerDespachado(ArrayList<String> plano, ArrayList<String> serie,ArrayList<String> verificacion ){
		Connection con;
		ResultSet rs=null;
		try{//---------------------------------select todos en stock 
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Stock Productos Serializados] ");
			sb.append("SET estado=3 ");
			sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for(int i=0;i<serie.size();i++){
				stm.setString(1, plano.get(i));
				stm.setString(2, serie.get(i));
				stm.setString(3, verificacion.get(i));
				stm.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Despachado Correctamente");
		}catch (Exception e){e.printStackTrace();
		JOptionPane.showMessageDialog(null, "error update espera");}
	}
	
	public void ponerEspera(ArrayList<String> plano, ArrayList<String> serie,ArrayList<String> verificacion ){
		Connection con;
		ResultSet rs=null;
		try{//---------------------------------select todos en stock 
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Stock Productos Serializados] ");
			sb.append("SET estado=2 ");
			sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for(int i=0;i<serie.size();i++){
				stm.setString(1, plano.get(i));
				stm.setString(2, serie.get(i));
				stm.setString(3, verificacion.get(i));
				stm.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Puesto en espera");
		}catch (Exception e){e.printStackTrace();
		JOptionPane.showMessageDialog(null, "error update espera");}
	}
}
