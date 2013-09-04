package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Remito;

import org.omg.CORBA.ULongLongSeqHelper;

public class RemitoDAO {

	ArrayList<String> listaCodigosExistentes;
	ArrayList<Integer> planos;
	ArrayList<Integer> cantidades;
	int ultimoNumeroRemito;
	CargaStockDAO stock=new CargaStockDAO();
	ArrayList<Integer> idRemitosPendientes;
	ArrayList<String> fechaRemitoPendiente;
	ArrayList<Integer> planoEleccion;
	ArrayList<Integer> cantidadEleccion;
	ArrayList<Integer> remitoEleccion = new ArrayList<>();
	ArrayList<String> descripciones = new ArrayList<>();
	ArrayList<String> descripcionesEleccion;

	public ArrayList<String> getDescripcionEleccion() {
		return descripciones;
	}

	public void setDescripcionEleccion(ArrayList<String> descripcionEleccion) {
		this.descripciones = descripcionEleccion;
	}

	public ArrayList<Integer> getIdRemitosPendientes() {
		return idRemitosPendientes;
	}

	public void setIdRemitosPendientes(ArrayList<Integer> idRemitosPendientes) {
		this.idRemitosPendientes = idRemitosPendientes;
	}

	public ArrayList<String> getFechaRemitoPendiente() {
		return fechaRemitoPendiente;
	}

	public void setFechaRemitoPendiente(ArrayList<String> fechaRemitoPendiente) {
		this.fechaRemitoPendiente = fechaRemitoPendiente;
	}

	public RemitoDAO() {
		cargar(); //carga la lista de codigos completos
		getPlanoCantidadDesc(); //carga lista de planos y cantidades
		
	}

	public ArrayList<Integer> getPlano() {
		return planos;
	}

	public ArrayList<Integer> getCantidad() {
		return cantidades;
	}

	public int getUltimoRemito() {
		return ultimoNumeroRemito;
	}

	public void cargar() {
		listaCodigosExistentes=stock.cargarListaCodigosExistentes();
	}

	public ArrayList<String> getListaCodigosExistentes() {
		return listaCodigosExistentes;
	}

	public void ponerDespachado(ArrayList<String> plano,ArrayList<String> serie, ArrayList<String> verificacion) {
		stock.ponerDespachadoArticulos(plano, serie, verificacion);
	}

	public void ponerEspera(ArrayList<String> plano, ArrayList<String> serie,ArrayList<String> verificacion) {
		stock.ponerEsperaArticulos(plano, serie, verificacion);
	}

	public void getPlanoCantidadDesc() {
		stock.cargarPlanoCantidadesDescripciones(); //carga las cosas
		planos=stock.getcodigosPlanos();
		cantidades=stock.getcantidadeseses();
		descripciones=stock.getdescripciones();
	}

	public void getElecciones(int idRemito) {
		cantidadEleccion=new ArrayList<>();
		descripcionesEleccion=new ArrayList<>();
		planoEleccion=new ArrayList<>();
		Remito r = null;
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT remito_id,a.codigo_plano,cantidad ,d.descripcion_str  ");
			sb.append("FROM [EleccionPyC] e ");			
			sb.append("INNER JOIN [Stock Productos Serializados] sps on sps.id=e.sps_id ");
			sb.append("INNER JOIN Articulo a on sps.codigo_plano=a.codigo_plano ");
			sb.append("INNER JOIN Descripcion d on d.id=a.descripcion_id ");
			sb.append("WHERE remito_id= " + idRemito + "");
			
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			while (rs.next()) {
				remitoEleccion.add(rs.getInt(1));
				planoEleccion.add(rs.getInt(2));
				cantidadEleccion.add(rs.getInt(3));
				descripcionesEleccion.add(rs.getString("descripcion_str"));
			}
			r = new Remito(planoEleccion, cantidadEleccion,	remitoEleccion.get(0), descripciones, this);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de elecciones");
		}
		//return r;
	}

	public ArrayList<String> getArticulosCargados(int idRemito) {

		ArrayList<String> articulos = new ArrayList<>();
		Remito r = null;
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.codigo_plano,s.numero_serie,s.verificador ");
			sb.append("FROM [Remito-SPS] r, [Stock Productos Serializados] s ");
			sb.append("WHERE r.sps_id=s.id AND r.remito_id=" + idRemito + "");

			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(rs.getInt(1));
				sb1.append(rs.getString(2));
				sb1.append(rs.getInt(3));
				articulos.add(sb1.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"error en la carga de articulos de reito pendiente");
		}
		return articulos;
	}

	public void guardarRemito(ArrayList<String> listaPara,ArrayList<Integer> planosRequeridos,ArrayList<Integer> cantidadesRequeridas, int idRemito ) {

		insertarRemito();
//		ultimoNumeroRemito = obtenerUltimoRemito();
		ultimoNumeroRemito=idRemito;
		guardarElecciones(planosRequeridos, cantidadesRequeridas);
		ArrayList<Integer> idsps = obtenerIdByCodigo(listaPara);
		guardarListaArticulos(idsps);

	}
	public void guardarRemito(ArrayList<String> listaPara,int remitoId) {

//		insertarRemito();
		ultimoNumeroRemito = remitoId;
//		guardarElecciones();
		ArrayList<Integer> idsps = obtenerIdByCodigo(listaPara);
		updateListaArticulos(idsps,remitoId);

	}

	private void insertarRemito() {
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO Remito (estado_id,fecha_inicio) ");
			sb.append("VALUES ('10', getDate()) ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.executeUpdate();
			// JOptionPane.showMessageDialog(null, "Despachado Correctamente");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error guardar remito");
		}

	}

	public int obtenerUltimoRemito() {

		int ultimoNumeroRemito;
		Connection con;
		ResultSet rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT MAX(id) ");
			sb.append("FROM Remito ");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
			rs.next();
			ultimoNumeroRemito = rs.getInt(1);
			// JOptionPane.showMessageDialog(null, "Despachado Correctamente");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error ver ultomo id remito");
			return 0;
		}

		return ultimoNumeroRemito;
	}

	private void guardarElecciones(ArrayList<Integer> planoRequerido,ArrayList<Integer> cantidadRequerido) {
		Connection con;
		ResultSet rs = null;
		rs = null;
		CargaStockDAO car=new CargaStockDAO();
		
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO EleccionPyC (sps_id,cantidad,remito_id) ");
			String obtenerId="(SELECT TOP 1 s.id FROM [Stock Productos Serializados] s WHERE s.codigo_plano=?)";
			sb.append("VALUES ("+obtenerId+",?,?)");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			int j = 0;
			for (Integer i : planoRequerido) {
				stm.setInt(1, i);
				stm.setInt(2, cantidadRequerido.get(j));
				stm.setInt(3, ultimoNumeroRemito);
				stm.executeUpdate();
				j++;
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error guardar eleccionpyc");
		}
	}

	private void guardarListaArticulos(ArrayList<Integer> idsps) {
		Connection con;
		ResultSet rs = null;
		rs = null;
		// int idsps=obtenerIdByCodigo(codigo)
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			
			sb.append("INSERT INTO [Remito-SPS] (remito_id,sps_id) ");
			sb.append("VALUES (?,?) ");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (Integer i : idsps) {
				stm.setInt(1, ultimoNumeroRemito);
				stm.setInt(2, i);
				stm.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(null, "error guardar lista articulos");
		}
	}

	private void updateListaArticulos(ArrayList<Integer> idsps,int remitoId) {
		Connection con;
		ResultSet rs = null;
		rs = null;
		// int idsps=obtenerIdByCodigo(codigo)
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("IF NOT EXISTS(SELECT * FROM [Remito-SPS] WHERE sps_id=? AND remito_id=?)");
			sb.append("BEGIN ");
			sb.append("INSERT INTO [Remito-SPS] (remito_id,sps_id) ");
			sb.append("VALUES (?,?) ");
			sb.append("END");
			// sb.append("VALUES ('10') ");
			// sb.append("WHERE [codigo_plano]=? AND [numero_serie]=? AND [verificador]=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (Integer i : idsps) {
				stm.setInt(1, i);
				stm.setInt(2, remitoId);
				stm.setInt(3, remitoId);
				stm.setInt(4, i);
				stm.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane
					.showMessageDialog(null, "error guardar update articulos");
		}
	}
	private ArrayList<Integer> obtenerIdByCodigo(ArrayList<String> codigo) {

		Connection con;
		ResultSet rs = null;
		int id = 0;
		ArrayList<Integer> idsps = new ArrayList<>();
		rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT s.id ");
			sb.append("FROM [Stock Productos Serializados] s ");
			sb.append("WHERE s.codigo_plano=? AND s.numero_serie=? AND s.verificador=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			for (String s : codigo) {
				stm.setInt(1, Integer.parseInt(s.substring(0, 4)));
				stm.setString(2, s.substring(4, 11));
				stm.setInt(3, Integer.parseInt(s.substring(11, 12)));
				rs = stm.executeQuery();
				if (rs.next()) {
					id = rs.getInt(1);
					idsps.add(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error gobtener id by codigo");
		}
		return idsps;
	}

	public void guardarRemitoEspera(int idRemito) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Remito] ");
			sb.append("SET estado_id=11 ");
			sb.append("WHERE id=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setInt(1, idRemito);
			stm.executeUpdate();
//			JOptionPane.showMessageDialog(null,
//					"Puesto en espera dso de guardado");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error update espera");
		}
	}
	
	public void guardarRemitoActivo(int idRemito) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Remito] ");
			sb.append("SET estado_id=10 ");
			sb.append("WHERE id=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setInt(1, idRemito);
			stm.executeUpdate();
//			JOptionPane.showMessageDialog(null,
//					"Puesto en espera dso de guardado");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error update espera");
		}
	}

	public void guardarRemitoDespachado(int idRemito) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Remito] ");
			sb.append("SET estado_id=12,fecha_despacho=GETDATE() ");
			sb.append("WHERE id=? ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setInt(1, idRemito);
			stm.executeUpdate();
//			JOptionPane.showMessageDialog(null,
//					"Puesto en despachado al remito que estaba guardado");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error update espera");
		}
	}

	public void cargarRemitosPendientes() {
		Connection con;
		ResultSet rs = null;
		int id = 0;
		idRemitosPendientes = new ArrayList<>();
		fechaRemitoPendiente=new ArrayList<>();
		rs = null;
		try {// --------------------------------
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT r.id ,r.fecha_inicio ");
			sb.append("FROM [Remito] r ");
			sb.append("WHERE r.estado_id=11 ");
			// PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());

			rs = stm.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				idRemitosPendientes.add(id);
				fechaRemitoPendiente.add(rs.getDate("fecha_inicio").toString());
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error cargar pedidos pendientes");
		}
	}

	public void liberaArticulo(String articulo) {
		Connection con;
		ResultSet rs = null;
		try {// ---------------------------------select todos en stock
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE [Stock Productos Serializados] ");
			sb.append("SET estado_id=1 ");
			sb.append("WHERE codigo_plano=? AND numero_serie=? AND verificador=? ");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());

			stm.setInt(1, Integer.parseInt(articulo.substring(0, 4)));
			stm.setString(2, articulo.substring(4, 11));
			stm.setInt(3, Integer.parseInt(articulo.substring(11, 12)));
			stm.executeUpdate();
			
//			JOptionPane.showMessageDialog(null,"Puesto en despachado al remito que estaba guardado");
			System.out.println("articulo liberado");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "error libera articuloa");
		}
	}
	public String getDescripcionByCodigo(String codigo){
//		String cod=stock.getdescripcionesByCodigo(codigo);
		int co=Integer.parseInt(codigo.substring(0,4).trim());
		int indice=planos.indexOf(co);
		String desc=descripciones.get(indice);
		return desc;
	}
}
