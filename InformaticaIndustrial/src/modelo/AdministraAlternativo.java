package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.Conexion;

import ui.AdministraAlternativoUI;

public class AdministraAlternativo {

	private String art;
	private String cant;
	private String um;
	private int codigoUm;
	Connection con;
	Conexion cn;
	 String[][] conversion = new String[10][10];
	public AdministraAlternativo(String art, String cant, String um) {
		this.art=art;
		this.cant=cant;
		this.um=um;
		MostrarAlternativo();
	}
	
	private ArrayList<String> getAlternativos()
	{
	cn = new Conexion();
		con = cn.getConexion();
	 ArrayList<String> descUm = new ArrayList();
	
	try {
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
		String selectUM = new String();
		selectUM ="select a.um_alt_id from Articulo a ,Descripcion d where a.descripcion_id=d.id and d.descripcion_str like '"+art+"'";
		PreparedStatement stm;
		stm = con.prepareStatement(selectUM.toString());
		ResultSet rs = stm.executeQuery();
		while (rs.next())
		{
			codigoUm=rs.getInt(1);
		}
				
		StringBuilder sb = new StringBuilder();
		sb.append("select uma.id UMAid, uma.um_principal, d2.descripcion_str descPrincipal,uma.un_alternativo, d.descripcion_str, uma.factor");
		sb.append(" from [Unidad Medida]um, [Unidad Medida Alternativa] uma, Descripcion d, Articulo a, Descripcion d2 ,[Unidad Medida]um2 ");
		sb.append(" where uma.un_alternativo=um.id and d.id=um.descripcion_id and um.descripcion_id=d.id ");
		sb.append(" and a.um_alt_id=uma.id and um2.id=uma.um_principal  and d2.id=um2.descripcion_id and a.um_alt_id="+codigoUm);
		 stm=null;
		stm = con.prepareStatement(sb.toString());
		 rs = stm.executeQuery();
		 int ind=0;
		 while (rs.next())
		 {
			 
			 conversion[ind][0]=String.valueOf(rs.getInt("um_principal"));
			 String sP=rs.getString("descPrincipal");
			 conversion[ind][1]=sP;
			 String s=rs.getString("descripcion_str");
			 descUm.add(s);
			 //conversion[ind][1]=rs.getString("un_alternativo");
			 conversion[ind][2]=s;
			 conversion[ind][3]=rs.getString("factor");
			 ind++;
		 }
	}catch(Exception e) { e.printStackTrace(); System.out.println("Error: getAlternativos");}

		return descUm;
	}
	
	public void MostrarAlternativo()
	{
		ArrayList<String> alternativosDescripcion = getAlternativos();
		AdministraAlternativoUI admUI = new AdministraAlternativoUI(art, cant, um,alternativosDescripcion,conversion);
		admUI.setVisible(true);
	}
	
//	public String[][] Recargar()
//	{
//		ArrayList<String> alternativosDescripcion = getAlternativos();
//		return conversion;
//	}
	
	
}
