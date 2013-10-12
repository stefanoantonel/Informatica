package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockDAO {

	public StockDAO(){}
	
	public Integer getCantidadStock(Integer id_art)
	{
		Conexion cn = new Conexion();
		Connection con = cn.getConexion();
		ResultSet rs = null;
		Integer cantidad=0;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select SUM(cantidad) cant");
			sb.append(" from Stock where articuo_id=");
			sb.append(id_art);
			sb.append(" group by articuo_id");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
	
			while (rs.next()) {
				cantidad = (int)rs.getObject("cant");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en obtenerCP");
		}
		return cantidad;
	}
}