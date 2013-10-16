package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockDAO {

	Conexion cn;
	Connection con;
	ResultSet rs;
	
	public StockDAO(){
		cn = new Conexion();
		con = cn.getConexion();
		rs = null;
	}
	
	public Integer getCantidadStock(Integer id_art)
	{
		
		Integer cantidad=0;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select SUM(cantidad) cant");
			sb.append(" from Stock where (fecha_mrp<GETDATE() or fecha_mrp is null) and articuo_id=");
			sb.append(id_art);
			sb.append(" group by articuo_id");
			PreparedStatement stm;
			System.out.println(sb.toString());
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
	
			while (rs.next()) {
				cantidad = (int)rs.getObject("cant");
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getCantidadStock");
		}
		return cantidad;
	}
	
	public void guardarStock(Integer id_art, Integer cantidad)
	{
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO stock (ubicaciones_id,articuo_id,cantidad, fecha_mrp)");
			sb.append(" VALUES(7,");
			sb.append(id_art);
			sb.append(",");
			sb.append(cantidad);
			sb.append(",GETDATE()+56)");
			PreparedStatement ps=con.prepareStatement(sb.toString());
			ps.executeUpdate();	
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en guardarStock");
		}
	
	}
	
	public void restarStock(Integer id_art, Integer cantidad)
	{
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE top(1)Stock");
			sb.append("  SET cantidad = cantidad-");
			sb.append(cantidad);
			sb.append(" WHERE articuo_id=");
			sb.append(id_art);
			sb.append(" and cantidad>=");
			sb.append(cantidad);
			PreparedStatement ps=con.prepareStatement(sb.toString());
			ps.executeUpdate();	
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en guardarStock");
		}
	
	}
	
}