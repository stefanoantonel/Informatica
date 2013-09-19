package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticuloDAO {

	public Integer obetenerCP(String id) {
		Conexion cn1 = new Conexion();
		Connection con = cn1.getConexion();
		ResultSet rs = null;
		Integer cp = -1;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select codigo_plano from Articulo where id=");
			sb.append(id);

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				cp = rs.getInt("codigo_plano");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en obtenerCP");
		}
		return cp;
	}
	public Integer obetenerCP(int id) {
		Conexion cn1 = new Conexion();
		Connection con = cn1.getConexion();
		ResultSet rs = null;
		Integer cp = -1;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select codigo_plano from Articulo where id=");
			sb.append(id);

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				cp = rs.getInt("codigo_plano");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en obtenerCP");
		}
		return cp;
	}
}
