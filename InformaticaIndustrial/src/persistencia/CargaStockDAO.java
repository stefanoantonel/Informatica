package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CargaStockDAO {
	
		
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

}
