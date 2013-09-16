package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Articulos;

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
	
	
	//sp_rename Moviminetos, Movimientos
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
	
	public ArrayList<Articulos> getArticuloXalmacen(String alm)
	{
		
		ArrayList<Articulos> arts=null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select ar.id,d.descripcion_str as descripcion, sps.lote");
			sb.append(" from Almacenes a");
			sb.append(" inner join Ubicaciones u on a.id=u.almacenes_id");
			sb.append(" inner join Stock s on u.id=s.ubicaciones_id");
			sb.append(" inner join Articulo ar on ar.id=s.articuo_id");
			sb.append(" inner join Descripcion d on d.id=ar.descripcion_id");
			sb.append(" inner join [Stock Productos Serializados] sps on sps.codigo_plano=ar.codigo_plano");
			sb.append(" where a.descripcion like '");
			sb.append(alm);
			sb.append("'");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();
			if(rs.next())
			{arts=new ArrayList<>();
				do {
					Articulos a= new Articulos(rs.getInt("id"));
					a.setDesc(rs.getString("descripcion"));
					a.setLote(rs.getInt("lote"));
					arts.add(a);
				}while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getCausas");
		}
		return arts;
	}

	public Integer getCantidadXlote(Integer lote)
	{
		Integer cant=null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select COUNT(*) as cant from [Stock Productos Serializados] where lote="+lote);
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				cant =rs.getInt("cant");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getCantidadxLote");
		}
		return cant;
		
	}
}
