package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Articulos;
import modelo.UnidadMedida;

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
	
	public ArrayList<ArrayList<String>> getAlmacenes(String sucursal)

	{

		if(sucursal==null)
		{
			sucursal="";
		}
		else
			sucursal=" where sucursales_id like '"+getSucursalByDescripcion(sucursal)+"'";
		
		ArrayList<ArrayList<String>> almacenes=new ArrayList<>();
		ArrayList<String> aux= new ArrayList<>();
		aux.add("");
		aux.add("");
		almacenes.add(aux);
		try {
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("select * from Almacenes");
			sb.append(sucursal);
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				aux= new ArrayList<>();
				aux.add(rs.getObject("id").toString());
				aux.add(rs.getObject("descripcion").toString());
				//String aa = String.valueOf(rs.getObject("descripcion"));
				//System.out.println("Consulta rs: "+aa);
				almacenes.add(aux);
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
				String id= String.valueOf(rs.getObject("id"));
				String p = String.valueOf(rs.getObject("pasillo"));
				String e = String.valueOf(rs.getObject("estante"));
				String c = String.valueOf(rs.getObject("columna"));
				System.out.println("Consulta rs: "+p+" "+e+" "+c);
				StringBuilder resultado = new StringBuilder();
				resultado.append(id).append(")");
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
			sb.append("select id,Descripcion from Causa");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				causas.add(rs.getInt("id")+")"+rs.getString("descripcion"));
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
		boolean haymas;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select distinct ar.id,d.descripcion_str as descripcion, um.id um, d2.descripcion_str descrip, sps.lote");
			sb.append(" from Almacenes a");
			sb.append(" inner join Ubicaciones u on a.id=u.almacenes_id");
			sb.append(" inner join Stock s on u.id=s.ubicaciones_id");
			sb.append(" inner join Articulo ar on ar.id=s.articuo_id");
			sb.append(" inner join Descripcion d on d.id=ar.descripcion_id");
			sb.append(" inner join [Stock Productos Serializados] sps on sps.codigo_plano=ar.codigo_plano");
			sb.append(" inner join [Unidad Medida] um on ar.um_id=um.id");
			sb.append(" inner join Descripcion d2 on d2.id=um.descripcion_id"); 
			
			sb.append(" where a.descripcion like '"+alm+"'");
			sb.append(" order by ar.id");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();
			Integer id=-1;
			Articulos a;
			UnidadMedida um;
			//la primera vez le setea todo
			//despues se fija si el id sigue siendo el mismo (es el mismo articulo pero con otro lote)
			//si es otro articulo lo crea, y si es el mismo le agrega el lote
			if(rs.next())
			{arts=new ArrayList<>();
				id = rs.getInt("id");
			    a= new Articulos(id);
				a.setDesc(rs.getObject("descripcion").toString());
				um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
				a.setUM(um);
				a.setLote(rs.getInt("lote"));
				
				while (rs.next()) {
					Integer id2 = rs.getInt("id");
					if(id!=id2)
					{
						arts.add(a);
						id=id2;
						a= new Articulos(id);
						a.setDesc(rs.getObject("descripcion").toString());
						um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
						a.setUM(um);
						a.setLote(rs.getInt("lote"));
					}
					else
						a.setLote(rs.getInt("lote"));
				}
				arts.add(a);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getArticuloXAlmacen");
		}
		return arts;
	}
	
	public ArrayList<Articulos> getArticulos(Integer causa)
	{
		ArrayList<Articulos> artic = new ArrayList<>();
		String tipoCompra;
		if (causa==1)
		    tipoCompra=" where a.tipo_id=2";
		else
			tipoCompra="";
		
		 try {
				StringBuilder sb = new StringBuilder();
				sb.append(" select distinct a.id,d.descripcion_str as descripcion, um.id um, d2.descripcion_str descrip, sps.lote");
				sb.append(" from Articulo a");
				sb.append(" inner join Descripcion d on d.id=a.descripcion_id");
				sb.append(" inner join [Unidad Medida] um on a.um_id=um.id");
			    sb.append(" inner join Descripcion d2 on d2.id=um.descripcion_id");
			    sb.append(" inner join [Stock Productos Serializados] sps on sps.codigo_plano=a.codigo_plano");
				sb.append(tipoCompra);
				PreparedStatement stm;
				stm = con.prepareStatement(sb.toString());
				//stm.setString(1, alm);
				rs = stm.executeQuery();
				Integer id;
				Articulos a;
				UnidadMedida um;
				if(rs.next())
				{artic=new ArrayList<>();
					id = rs.getInt("id");
				    a= new Articulos(id);
					a.setDesc(rs.getObject("descripcion").toString());
					um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
					a.setUM(um);
					a.setLote(rs.getInt("lote"));
					
					while (rs.next()) {
						Integer id2 = rs.getInt("id");
						if(id!=id2)
						{
							artic.add(a);
							id=id2;
							a= new Articulos(id);
							a.setDesc(rs.getObject("descripcion").toString());
							um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
							a.setUM(um);
							a.setLote(rs.getInt("lote"));
						}
						else
						{
							a.setLote(rs.getInt("lote"));
						}
					}
					artic.add(a);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error en getArticulos");
			}
			return artic;
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
	
	public void insertarMovimiento(String causa,String sucOrigen,int almacenOrigen,String ubicacionOrigen,String sucDestino,
			int almacenDestino, String ubicacionDestino,Articulos art,String fecha, String nota)
	{
		if(fecha==null||fecha.equals("")){
			fecha="getDate() ";
		}
		else{
			fecha="'"+fecha+"'";
		}

		Integer sucursarOrigen = getSucursalByDescripcion(sucOrigen);
		Integer sucursalDestino = getSucursalByDescripcion(sucDestino);
		
		
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("Insert into Movimientos (sucursal_origen,almacen_origen,ubicacion_origen,sucursal_destino,almacen_destino,ubicacion_destino,");
			sb1.append(" causa_id,articulo_id,um_id,nota,fecha,user_upd,lugar_upd,descripcion_upd) ");
			sb1.append(" VALUES (");
			sb1.append(sucursarOrigen).append(", ");
			sb1.append(almacenOrigen).append(", ");
			sb1.append(ubicacionOrigen).append(", ");
			sb1.append(sucursalDestino).append(", ");
			sb1.append(almacenDestino).append(", ");
			sb1.append(ubicacionDestino).append(", ");
			sb1.append(causa).append(", ");
			sb1.append(art.getValor()).append(", ");
			sb1.append(art.getUM().getId()).append(", ");
			sb1.append(nota).append(", ");
			sb1.append(fecha).append(", ");
			sb1.append("2,'casa','cargo bom') ");
			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
			JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
		
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: insertar movimiento");}
		
	}
	
//	public void getUM ()
//	{
//		ArrayList<String> um = new ArrayList<>();
//
//		 try {
//				StringBuilder sb = new StringBuilder();
//				sb.append(" select a.id,d.descripcion_str as descr");
//				sb.append(" from Articulo a");
//				sb.append(" inner join Descripcion d on d.id=a.descripcion_id");
//				sb.append(" where a.tipo_id=2");
//				PreparedStatement stm;
//				stm = con.prepareStatement(sb.toString());
//				//stm.setString(1, alm);
//				rs = stm.executeQuery();
//
//				while (rs.next()) {
//					Articulos a= new Articulos(rs.getInt("id")); 
//					a.setDesc(rs.getObject("descr").toString());
//					artic.add(a);
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("error en getCantidadxLote");
//			}
//			return artic;
//	}
	
}
