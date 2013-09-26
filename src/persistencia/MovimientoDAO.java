package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Articulos;
import modelo.UnidadMedida;

public class MovimientoDAO {
	//15
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
			//	System.out.println("Consulta rs: "+aa);
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
			//	System.out.println("Consulta rs: "+p+" "+e+" "+c);
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
	
	public ArrayList<Articulos> getArticuloXubicacion(String ub)
	{
		
		ArrayList<Articulos> arts=null;
		String ubic="";
		String cant="";
		//System.out.println("ub:"+ub);
		if (ub==null)
			{ubic="";			
			}
		else
			{if(ub.equals("")|| ub.equals(" "))
			ubic="";
		    else
			   {
		    	System.out.println("ub: "+ub);
		    	ubic=" and u.id="+ub;
		    	cant=", s.cantidad cant";
		    	}
		   }
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select distinct ar.id,d.descripcion_str as descripcion, um.id um, d2.descripcion_str descrip"); //, sps.lote");
			sb.append(cant);
			sb.append(" from Ubicaciones u");
			sb.append(" inner join Stock s on u.id=s.ubicaciones_id");
			sb.append(" inner join Articulo ar on ar.id=s.articuo_id");
			sb.append(" inner join Descripcion d on d.id=ar.descripcion_id");
			//sb.append(" inner join [Stock Productos Serializados] sps on sps.stock_id=s.id");
			sb.append(" inner join [Unidad Medida] um on ar.um_id=um.id");
			sb.append(" inner join Descripcion d2 on d2.id=um.descripcion_id"); 
			sb.append(" where s.cantidad>0");
			sb.append(ubic);
			//sb.append(" order by ar.id");
			PreparedStatement stm;
			System.out.println("getArtXub: "+sb);
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();
			Integer id=-1;
			Articulos a= new Articulos();
			UnidadMedida um;
			//la primera vez le setea todo
			//despues se fija si el id sigue siendo el mismo (es el mismo articulo pero con otro lote)
			//si es otro articulo lo crea, y si es el mismo le agrega el lote
			if(rs.next())
			{	arts= new ArrayList<>();
				a.setValor(rs.getInt("id"));
				a.setDesc(rs.getObject("descripcion").toString());
				um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
				a.setUM(um);
				if(!cant.equals(""))
				{a.setCant(rs.getObject("cant").toString());
					System.out.println("cantidad=== "+ a.getCant());
				}
				arts.add(a);
				while (rs.next()) {
						a= new Articulos(rs.getInt("id"));
						a.setDesc(rs.getObject("descripcion").toString());
						um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
						a.setUM(um);
						if(!cant.equals(""))
							{a.setCant(rs.getObject("cant").toString());
								System.out.println("cantidad=== "+ a.getCant());
							}
						arts.add(a);
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getArticuloXubicacion");
		}


		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select distinct s.articuo_id,lote"); 
			sb.append(" from [Stock Productos Serializados] sps ");
			sb.append(" inner join Stock s on s.id=sps.stock_id");

			PreparedStatement stm;
			System.out.println(sb);
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();
			
				while (rs.next()) {
					Integer id= rs.getInt("articuo_id");
					for (Articulos am: arts)	
					{
					 if(am.getValor()== id)
						 {
							 Integer l= rs.getInt("lote");
							 am.setLote(l);							 
						 }
					}
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
				sb.append(" select distinct a.id,d.descripcion_str as descripcion, um.id um, d2.descripcion_str descrip");// sps.lote");
				sb.append(" from Articulo a");
				sb.append(" inner join Descripcion d on d.id=a.descripcion_id");
				sb.append(" inner join [Unidad Medida] um on a.um_id=um.id");
			    sb.append(" inner join Descripcion d2 on d2.id=um.descripcion_id");
			  //  sb.append(" inner join [Stock Productos Serializados] sps on sps.stock_id=s.id");
				sb.append(tipoCompra);
				PreparedStatement stm;
				stm = con.prepareStatement(sb.toString());
				//stm.setString(1, alm);
				rs = stm.executeQuery();
				Integer id;
				Articulos a;
				UnidadMedida um;
				artic=new ArrayList<>();
				while(rs.next())
				{
					id = rs.getInt("id");
				    a= new Articulos(id);
					a.setDesc(rs.getObject("descripcion").toString());
					um= new UnidadMedida(rs.getInt("um"),rs.getObject("descrip").toString());
					a.setUM(um);
					artic.add(a);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error en getArticulos");
			}
			return artic;
	}

	
	 
//select a.id, lote, s.articuo_id, SUM(cantidad)
//from Stock	s
//inner join Ubicaciones u on s.ubicaciones_id=u.id
//inner join Almacenes a on a.id=u.almacenes_id
//inner join [Stock Productos Serializados] sps on sps.stock_id=s.id
//group by a.id, lote, s.articuo_id

	public Integer obetenerCP(String id)
	{
		Integer cp=-1;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select codigo_plano from Articulo where id=");
			sb.append(id);

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				cp=rs.getInt("codigo_plano");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en obtenerCP");
		}
		return cp;
	}
	
	
	public ArrayList<ArrayList<String>>  getCantidadXlote()
	{
		String ubicacion, lot,artic,cant;
		ArrayList<ArrayList<String>> mat = new ArrayList<>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select u.id ub , lote, s.articuo_id art, COUNT(sps.id) cant");
			sb.append(" from Stock s");
			sb.append(" inner join Ubicaciones u on s.ubicaciones_id=u.id");
			sb.append(" inner join Almacenes a on a.id=u.almacenes_id");
			sb.append(" inner join [Stock Productos Serializados] sps on sps.stock_id=s.id");
			sb.append(" group by u.id, lote, s.articuo_id");
			PreparedStatement stm;
			System.out.println(sb.toString());
			stm = con.prepareStatement(sb.toString());
			//stm.setString(1, alm);
			rs = stm.executeQuery();

			while (rs.next()) {
				ArrayList<String> aux= new ArrayList<>();
				ubicacion = rs.getObject("ub").toString();
				aux.add(ubicacion);
				lot = rs.getObject("lote").toString();
				aux.add(lot);
				artic = rs.getObject("art").toString();
				aux.add(artic);
				cant = rs.getObject("cant").toString();
				System.out.println("cant: "+cant);
				aux.add(cant);
				mat.add(aux);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getCantidadxLote");
		}
		return mat;
		
	}
	
	public void insertarMovimiento(String causa,String sucOrigen,String almacenOrigen,String ubicacionOrigen,String sucDestino,
			String almacenDestino, String ubicacionDestino,Articulos art,String fecha, String nota, String cantidad)
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
			sb1.append(" causa_id,articulo_id,um_id,nota,fecha,cantidad,user_upd,lugar_upd,descripcion_upd) ");
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
			sb1.append(cantidad).append(", ");
			sb1.append("2,'casa','cargo bom') ");
			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
			JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
		
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: insertar movimiento");}
		
	//	upCantidad(cantidad,ubicacionDestino,art.getValor().toString());
		
//		try{
//			StringBuilder sb1=new StringBuilder();
//			sb1.append("update top("+cantidad+")[Stock Productos Serializados] set stock_id=(select distinct id from Stock where ubicaciones_id="+ubicacionDestino+" and articuo_id="+art.getValor()+")");
//			sb1.append("where stock_id in (select id from Stock where ubicaciones_id="+ubicacionOrigen+" and articuo_id="+art.getValor()+")");
//
//			System.out.println(sb1);
//			PreparedStatement ps=con.prepareStatement(sb1.toString());
//			ps.executeUpdate();			
//		
//		}catch (Exception e){e.printStackTrace(); System.out.println("error cambiar sps");}
//		

		
		updateCantidad(cantidad,art.getValor().toString(),causa,ubicacionOrigen,ubicacionDestino);
		
	}
	
	
//	public void upStockSerializado(String cantidad, String ubDestino, String idArt)
//	{
//		try{
//			StringBuilder sb1=new StringBuilder();
//			sb1.append("update top("+cantidad+")[Stock Productos Serializados] set stock_id=(select distinct id from Stock where ubicaciones_id="+ubDestino+" and articuo_id="+idArt+")");
//			sb1.append("where stock_id is null");
//
//			System.out.println(sb1);
//			PreparedStatement ps=con.prepareStatement(sb1.toString());
//			ps.executeUpdate();			
//		
//		}catch (Exception e){e.printStackTrace(); System.out.println("error cambiar sps");}
//		
//		
//	}

	public boolean controlDestino (String art, String ubDestino)
	{
		ArrayList<ArrayList<String>> mat = new ArrayList<>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select * ");
			sb.append(" from Stock s");
			sb.append(" inner join Ubicaciones u on s.ubicaciones_id=u.id");
			sb.append(" where  articuo_id=? and ubicaciones_id=?");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setString(1, art);
			stm.setString(2, ubDestino);
			rs = stm.executeQuery();

			if(rs.next())
			{	while (rs.next()) {
						
				}
				return true;
		    }
		} catch (Exception e){e.printStackTrace(); System.out.println("error controlDestino");}
		
		return false;
	}
	
	
	public void updateCantidad(String cantidad, String art, String causa, String ubOrigen, String ubDestino)
	{


		String cantDestino;
		System.out.println("causa en up: " + causa);
		cantDestino=cantidad;
		if(causa.equals("2")||causa.equals("4"))//destruccion o ajuste negativo
			{cantDestino="cantidad-"+cantidad;
			 System.out.println("cantd: "+cantDestino);
			}
		if(causa.equals("1") ||causa.equals("3") ||causa.equals("5") || causa=="1" ||causa=="3" ||causa=="5")//ajuste positivo o compra o movimiento
			{cantDestino="cantidad+"+cantidad;
			System.out.println("cantD: "+cantDestino);
			}

		
	
		if(controlDestino(art, ubDestino))
		{
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("update Stock set cantidad="+cantDestino+", user_upd=2, descrpcion_upd='movimiento stock', lugar_upd='facu' where articuo_id="+art+" and ubicaciones_id="+ubDestino);

			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();	
			
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error updateCantidad1");
		JOptionPane.showMessageDialog(null, "ERROR: updateCantidad");}
		
		}
		else
		{
			try{
				StringBuilder sb1=new StringBuilder();
				sb1.append("insert into Stock (cantidad, user_upd,descrpcion_upd,lugar_upd,articuo_id,ubicaciones_id) ");
				sb1.append("Values ("+cantidad+",2,'movimieneto stock','facu',"+art+","+ubDestino+")");
				System.out.println(sb1);
				PreparedStatement ps=con.prepareStatement(sb1.toString());
				ps.executeUpdate();			
				
			}catch (Exception e){e.printStackTrace(); System.out.println("error updateCantidad3");}
			
		}
		
		
		if(ubOrigen!=null || !ubOrigen.equals(""))
		{
		String cantOrigen="";
		
//		if(causa.equals("2")||causa.equals("4"))//destruccion o ajuste negativo
//			cantOrigen="cantidad-"+cantidad;
//		if(causa.equals("1") ||causa.equals("5"))//ajuste positivo o compra
//			cantOrigen="cantidad+"+cantidad;
//		else 
//			cantOrigen=cantidad;
		cantOrigen="cantidad-"+cantidad;
		
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("update Stock set cantidad="+cantOrigen+", user_upd=2, descrpcion_upd='movimieneto stock', lugar_upd='facu' where articuo_id="+art+" and ubicaciones_id="+ubOrigen);

			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error updateCantidad2");
		JOptionPane.showMessageDialog(null, "ERROR: updateCantidad");}
		
		}

		
//		if(causa.equals("5")|| causa=="5" )//movimiento  restar en el origen y cambiar en sps
//		{
//			cantDestino="cantidad+"+cantidad;
//			System.out.println("cantD: "+cantDestino);
//			//restar en el origen
//			try{
//				StringBuilder sb1=new StringBuilder();
//				sb1.append("update Stock set cantidad=cantidad-"+cantidad+", user_upd=2, descrpcion_upd='movimiento stock', lugar_upd='facu' where articuo_id="+art+" and ubicaciones_id="+ubOrigen);
//
//				System.out.println(sb1);
//				PreparedStatement ps=con.prepareStatement(sb1.toString());
//				ps.executeUpdate();			
//				
//			}catch (Exception e){e.printStackTrace(); System.out.println("error updateCantidad1");
//			JOptionPane.showMessageDialog(null, "ERROR: updateCantidad en movimiento");}
//			
//			//update el SPS
////			try{
////				StringBuilder sb1=new StringBuilder();
////				sb1.append("update [Stock Productos Serializados] set");
////				sb1.append(" stock_id=(select distinct id from Stock where articuo_id=");
////				sb1.append(art);
////				sb1.append(" and ubicaciones_id=");
////				sb1.append(ubDestino);
////				sb1.append(") where stock_id=(select distinct id from Stock where articuo_id=");
////				sb1.append(art);
////				sb1.append(" and ubicaciones_id=");
////				sb1.append(ubOrigen);
////				sb1.append(")");
////				System.out.println("actualzia sps "+ sb1);
////				PreparedStatement ps=con.prepareStatement(sb1.toString());
////				ps.executeUpdate();			
////				
////			}catch (Exception e){e.printStackTrace(); System.out.println("error updateCantidad1");
////			JOptionPane.showMessageDialog(null, "ERROR: updateSPS");}
//		}
//		
		
		
		
	}
	

}

