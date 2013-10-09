package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProveedorDAO {
	
	Connection con;
	ResultSet rs;
	
	public ProveedorDAO()
	{
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
	}
	
	public ArrayList<ArrayList<Integer>> obtenerProveedor ()
	{
		ArrayList<ArrayList<Integer>> proveedores=new ArrayList<>();
		try {
		StringBuilder sb = new StringBuilder();
		// si alguno tiene 1 en principale se es el principal, sino se elige por precio mas barato
		sb.append("select id_art,id_prov,capacidad,tam_lote,lead_time,principal");
		sb.append(" from Articulo_proveedor ");
		sb.append(" order by id_art,principal desc, precio_compra");
		PreparedStatement stm;
		stm = con.prepareStatement(sb.toString());
		rs = stm.executeQuery();
		
			
			while (rs.next()){
			//
		    ArrayList<Integer> p=new ArrayList<>();
			Integer art = (Integer)(rs.getObject("id_art"));
			System.out.println("art Dao: "+art);
		    p.add(art);
		    Integer prov = (Integer)(rs.getObject("id_prov"));
		    p.add(prov);
		    Integer capacidad =Math.ceil(rs.getFloat("capacidad"));
		    p.add(capacidad);
		    Integer lote = (Integer)(rs.getObject("tam_lote"));
		    p.add(lote);
		   // String precioCompra = String.valueOf(rs.getObject("precio_compra"));
		    //p.add(precioCompra);
		    Integer lead = (Integer)(rs.getObject("lead_time"));
		    p.add(lead);
		    Integer principal =(Integer)(rs.getObject("principal"));
		    p.add(principal);
		    
		    proveedores.add(p);
		}

		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("error en obtenerProveedor");
	}
	return proveedores;
	}
}
