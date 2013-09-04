package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Nodo;

public class NodoDAO {

	Connection con;
	ResultSet rs;

	public NodoDAO()
	{
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
	}

	public ArrayList<String> obtenerArlternativos(String padre, String hijo)
	{
		
		ArrayList<String> alt=null;
		try {
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("select distinct d.descripcion_str ");
			sb.append(" from BOM b,Articulo a,[Articulos Alternativos] at,Descripcion d");
		    sb.append(" where at.artAlternativo_id=a.id and");
			sb.append(" a.descripcion_id=d.id and");
			sb.append(" b.generico_id=at.artGenerico_id and");
			sb.append("  b.padre=? and b.hijo =? and");
			sb.append(" at.artAlternativo_id <> b.hijo");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setString(1, padre);
			stm.setString(2,hijo);
			rs = stm.executeQuery();
			
			if(rs.next())
			{
				 alt=new ArrayList<>();
			do {
				//
				String aa = String.valueOf(rs.getObject(1));
				System.out.println("Consulta rs: "+aa);
			    alt.add(aa);
			}while (rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en getAlternativos");
		}
		return alt;
	}
	
	public String obterXdefecto(String padre, String hijo)
	{
		ArrayList<String> alt=new ArrayList<>();
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("select d.descripcion_str ");
			sb.append(" from BOM b,Articulo a,Descripcion d");
		   
			sb.append(" where a.descripcion_id=d.id and ");
			
			sb.append("  b.padre=? and b.hijo =? and");
			sb.append(" a.id=b.hijo");
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			stm.setString(1, padre);
			stm.setString(2, hijo);
			rs = stm.executeQuery();
			
			while (rs.next()) {
				//
				String aa = String.valueOf(rs.getObject(1));
				System.out.println("Consulta rs: "+aa);
			    alt.add(aa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error en obtenerXdefecto");
		}
		
		return alt.get(0);
	}
	
	public void eliminarRealcion (Nodo p, Nodo h)
	{
	
		try{
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE BOM ");
			sb.append("SET borrado=1 ,fecha_fin=getDate() ");
			sb.append("WHERE padre="+p.getArt().getValor()+" and hijo= "+h.getArt().getValor()+"");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());	
			stm.executeUpdate();	
		}catch (Exception e) {
			System.out.println("error pdate eliminar");
			e.printStackTrace();
		}
		
	}
	
	
}