package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MovimientoDAO {
	
	Connection con;
	ResultSet rs;
	
	public MovimientoDAO()
	{
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
	}
	

	public ArrayList<String> getSucursales()
	{
		ArrayList<String> sucursal=new ArrayList<>();
		sucursal.add(" ");
		try {
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Sucursales ");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				String aa = String.valueOf(rs.getObject("descripcion"));
				System.out.println("Consulta rs: "+aa);
				sucursal.add(aa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getSucursales");
		}
		return sucursal;
	}
	
	public ArrayList<String> getAlmacenes(String sucursal)

	{

		if(sucursal==null)
		{
			sucursal="";
		}
		else
			sucursal=" where sucursales_id like '"+getSucursalByDescripcion(sucursal)+"'";
		
		ArrayList<String> almacenes=new ArrayList<>();
		almacenes.add("  ");
		try {
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Almacenes");
			sb.append(sucursal);
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				String aa = String.valueOf(rs.getObject("descripcion"));
				System.out.println("Consulta rs: "+aa);
				almacenes.add(aa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getAlmacenes");
		}
		return almacenes;
	}

	public ArrayList<String> getUbicaciones(String almacen)
	{
		if(almacen==null)
		{
			almacen="";
		}
		else
			almacen=" where almacenes_id like '"+getAlmacenByDescripcion(almacen)+"'";
		System.out.println(" Almacen en MovimientoDAO:"+almacen);
		ArrayList<String> almacenes=new ArrayList<>();
		almacenes.add(" ");
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Ubicaciones ");
			sb.append(almacen);
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				String p = String.valueOf(rs.getObject("pasillo"));
				String e = String.valueOf(rs.getObject("estante"));
				String c = String.valueOf(rs.getObject("columna"));
				System.out.println("Consulta rs: "+p+" "+e+" "+c);
				StringBuilder resultado = new StringBuilder();
				resultado.append("pasillo:").append(p);
				resultado.append(" est:").append(e);
				resultado.append(" col:").append(c);
				almacenes.add(resultado.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getUbicaciones");
		}
		return almacenes;
	}
	
	
	public Integer getSucursalByDescripcion (String suc)
	{
		Integer id=null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Sucursales where descripcion like '"+suc+"'");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, suc);
			rs = stm.executeQuery();

			while (rs.next()) {
				id =rs.getInt("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en SucursalesByDesc");
		}
		return id;
	}
	
	public Integer getAlmacenByDescripcion (String alm)
	{
		Integer id=null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Almacenes where descripcion like '"+alm+"'");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				id =rs.getInt("id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en AltByDesc");
		}
		return id;
	}
	
	public ArrayList<String> getCausas()
	{
		ArrayList<String> causas=new ArrayList<>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select Descripcion from Causa");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				causas.add(rs.getString("descripcion"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getCausas");
		}
		return causas;
	}
}
