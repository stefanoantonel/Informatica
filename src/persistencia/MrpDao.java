package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MrpDao {
	private ArrayList<ArrayList<Integer>> articuloCapacidad=new ArrayList<>();
	//en articuloCapacidad tiene 2 columnas, la primera indica el articulo id y la segunda la capacidad

	private void loadCapacidadMia(int articuloID){
		ArrayList<Integer> capacidad=new ArrayList<>();
		Connection con;
		ResultSet rs = null;
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT art_id,capacidad ");
			sb.append("FROM [Capacidad_productiva] ");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				capacidad.add(rs.getInt("art_id"));
				capacidad.add(rs.getInt("capacidad"));
				articuloCapacidad.add(capacidad);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error load capacidad mia");
		}
	}
	
	public int getCapacidad(int articuloID){
		int cap=searchCapacidad(articuloID);
		if(cap!=-1){
			return cap;
		}
		else{
			loadCapacidadMia(articuloID);
			cap=searchCapacidad(articuloID);
			if(cap!=-1){
				return cap;
			}
			else{
				System.out.println("error: capacidad del articulo no disponible");
				return -1;
			}
			
		}
	}
	private int searchCapacidad(int articuloID){
		int capacidad=-1;
		if(articuloCapacidad!=null){
			for(ArrayList<Integer> art:articuloCapacidad){
				if(art.get(0)==articuloID){
					capacidad=art.get(1);
					return capacidad;
				}
			}
		}
		return -1;
	}
}
