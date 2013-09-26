package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Articulos;

public class StockSerializadoDAO {
	ArrayList<Integer> codigosPlanos;
	ArrayList<Integer> cantidades;
	ArrayList<String> descripciones;
	
	public ArrayList<String> getdescripciones() {
		return descripciones;
	}

	public ArrayList<Integer> getcodigosPlanos() {
		return codigosPlanos;
	}

	public ArrayList<Integer> getcantidadeseses() {
		return cantidades;
	}

	public void cargarPlanoCantidadesDescripciones() {
		codigosPlanos = new ArrayList<>();
		cantidades = new ArrayList<>();
		descripciones=new ArrayList<>();
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
//			sb.append("SELECT codigo_plano,COUNT (*)");
//			sb.append("FROM [Stock Productos Serializados]");
//			sb.append("GROUP BY codigo_plano");
			sb.append("SELECT s.codigo_plano,COUNT (*),d.descripcion_str ");
			sb.append("FROM [Stock Productos Serializados] s ");
			sb.append("INNER JOIN Articulo a on a.codigo_plano=s.codigo_plano ");
			sb.append("INNER JOIN Descripcion d on d.id=a.descripcion_id ");
			sb.append("GROUP BY s.codigo_plano,d.descripcion_str ");
			
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				codigosPlanos.add(rs.getInt("codigo_plano"));
				cantidades.add(rs.getInt(2));
				String a=rs.getString(3);
				descripciones.add(a);
				// StringBuilder sb1=new StringBuilder();
				// sb1.append(rs.getString(1));
				// sb1.append(rs.getString(2).trim());
				// sb1.append(rs.getString(3));
				//
				// listaCodigosExistentes.add(sb1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de articulos existentes");
		}
	}
	
	
	public int obtenerUltimoLote() {

		int ultimoNumeroLote;
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
//			sb.append("SELECT MAX(id) ");
//			sb.append("FROM Remito ");
			sb.append("SELECT MAX(lote) FROM [Stock Productos Serializados]");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			rs.next();
			ultimoNumeroLote= rs.getInt(1);
			
			
			// JOptionPane.showMessageDialog(null, "Despachado Correctamente");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error ver ultomo id remito");
			return 0;
		}

		return ultimoNumeroLote+1;
	}
	
	public boolean insertarStock(int codigosPlanos,ArrayList<String> serie, ArrayList<Integer> verificador){
	
		Connection con;
		ResultSet rs=null;
		int ultimoLote=obtenerUltimoLote();
		
		
		try{//---------------------------------ID UM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO [Stock Productos Serializados]");
			sb.append("([codigo_plano],[numero_serie],[verificador],[estado_id],[desc_upd],[lote])");
//			sb.append("([codigo_plano],[numero_serie],[verificador],[estado_id],[desc_upd])");
			sb.append("VALUES (?,?,?,'1',?,?)");
			
			
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			for(int i=0;i<serie.size();i++){
				stm.setInt(1, codigosPlanos);
				stm.setString(2,serie.get(i));
				stm.setInt(3,verificador.get(i));
				stm.setString(4,"cargo serie de producto "+codigosPlanos);
				stm.setInt(5,ultimoLote);
				stm.executeUpdate();
			}
			return true;
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "error ingresar serie");
		return false;}
		
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
			sb.append("FROM [Stock Productos Serializados] ");
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
	
	public ArrayList<ArrayList<String>> obtenerArticulosStock(){
		Connection con;
		ResultSet rs;

		ArrayList<ArrayList<String>> articuloCodigo=new ArrayList<>();
		
		
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
	
			sb.append("SELECT distinct a.codigo_plano , d.descripcion_str ");
			sb.append("FROM [Stock Productos Serializados] s ");
			sb.append("INNER JOIN Articulo a on a.codigo_plano=s.codigo_plano ");
			sb.append("INNER JOIN Descripcion d on d.id=a.descripcion_id ");
			
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					ArrayList<String> fila= new ArrayList();
					fila.add(String.valueOf(rs.getString(1)));
					fila.add(rs.getString(2));
					articuloCodigo.add(fila);
//					nodo.setdescripciones();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error obtener articulo stock ");
				}

//				// String a = rs.getString(2);
//
//				try {
//					modelo1.addElement(nodo.getdescripciones());
//					
//				} catch (Exception e) {
//					System.out.println("erros add eleme");
//					e.printStackTrace();
//				}

			}
			
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}
		return articuloCodigo;
	}
	
	public ArrayList<String> cargarListaCodigosExistentes() {
		ArrayList<String> listaCodigosExistentes = new ArrayList<>();
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT [codigo_plano],[numero_serie],[verificador] ");
			sb.append("FROM [Stock Productos Serializados] stock ");
			sb.append("WHERE estado_id=1 ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(rs.getString(1));
				sb1.append(rs.getString(2).trim());
				sb1.append(rs.getString(3));

				listaCodigosExistentes.add(sb1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de articulos existentes");
		}
		return listaCodigosExistentes;
	}
	
	public void ponerDespachadoArticulos(ArrayList<String> plano,ArrayList<String> serie, ArrayList<String> verificacion) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Stock Productos Serializados] ");
			sb.append("SET estado_id=3 ");
			sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (int i = 0; i < serie.size(); i++) {
				stm.setString(1, plano.get(i));
				stm.setString(2, serie.get(i));
				stm.setString(3, verificacion.get(i));
				stm.executeUpdate();
			}
			JOptionPane.showMessageDialog(null, "Despachado Correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error poner despachado");
		}
	}
	
	public void ponerEsperaArticulos(ArrayList<String> plano, ArrayList<String> serie,ArrayList<String> verificacion) {
			Connection con;
			ResultSet rs = null;
			try {// ---------------------------------select todos en stock
				Conexion cn1 = new Conexion();
				con = cn1.getConexion();
				StringBuilder sb = new StringBuilder();
				sb.append("UPDATE [Stock Productos Serializados] ");
				sb.append("SET estado_id=2 ");
				sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
				// PREPARAR CONSULTA
				PreparedStatement stm;
				stm = con.prepareStatement(sb.toString());
				for (int i = 0; i < serie.size(); i++) {
					stm.setString(1, plano.get(i));
					stm.setString(2, serie.get(i));
					stm.setString(3, verificacion.get(i));
					stm.executeUpdate();
				}
				JOptionPane.showMessageDialog(null, "Puesto en espera");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "error poner espera stock");
			}
	}
	public String getdescripcionesByCodigo(String codigo){
		String plano=codigo.substring(0, 4);
		Integer p=Integer.parseInt(plano);
		int indice=codigosPlanos.indexOf(p);
		String desc=descripciones.get(indice);
		return desc;
	}
	
	public void upStockId(String cantidad, String ubDestino, String idArt, String ubOrigen, String lote)
	{
		Conexion cn1 = new Conexion();
		Connection con = cn1.getConexion();
		String condicion=" where stock_id is null";
		System.out.println("LOTE: "+lote + "        ubOrigen: "+ubOrigen);
		if(lote != null) 
		{	condicion=" where lote="+lote;
			if(ubOrigen != null)	
				condicion = condicion + " and stock_id=(select distinct id from Stock where ubicaciones_id="+ubOrigen+" and articuo_id="+idArt+")";
		}
		else if(ubOrigen != null)
		{
			condicion= " where stock_id=(select distinct id from Stock where ubicaciones_id="+ubOrigen+" and articuo_id="+idArt+")";
		}
		
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("update top("+cantidad+")[Stock Productos Serializados] set stock_id=(select distinct top(1) id from Stock where ubicaciones_id="+ubDestino+" and articuo_id="+idArt+" order by id desc)");
			sb1.append(condicion);

			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
		
		}catch (Exception e){e.printStackTrace(); System.out.println("error cambiar sps");}
		
		System.out.println("paso por upStockId");
		
	}
	
	
	public void delStock(String cantidad, String ubDestino,String a, String lote)
	{
		Conexion cn1 = new Conexion();
		Connection con = cn1.getConexion();
		String condicion=" ";
		System.out.println("LOTE: "+lote );
		if((lote != null ) )
			{ if(!lote.equals("") && !lote.equals("Indistinto"))
			condicion= " and lote="+lote;}
//		else
//			condicion=" where stock_id is null";
		
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("Delete top("+cantidad+")[Stock Productos Serializados] where stock_id=(select distinct top(1) id from Stock where ubicaciones_id="+ubDestino+" and articuo_id="+a+" order by id desc)");
			sb1.append(condicion);

			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
		
		}catch (Exception e){e.printStackTrace(); System.out.println("error del sps");}
		
		System.out.println("paso por delStock");
		
	}
	
	
	
	public void upStockId(int cantidad, int ubDestino, int idArt)
	{
		Conexion cn1 = new Conexion();
		Connection con = cn1.getConexion();
		try{
			StringBuilder sb1=new StringBuilder();
			sb1.append("update top("+cantidad+")[Stock Productos Serializados] set stock_id=(select distinct id from Stock where ubicaciones_id="+ubDestino+" and articuo_id="+idArt+")");
			sb1.append("where stock_id is null");

			System.out.println(sb1);
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
		
		}catch (Exception e){e.printStackTrace(); System.out.println("error cambiar sps");}
		
		
	}
	
	
}
