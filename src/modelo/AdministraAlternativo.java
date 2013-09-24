package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import persistencia.Conexion;

import ui.AdministraAlternativoUI;

public class AdministraAlternativo {

	private String id;
	private String art;
	private String cant;
	private String um;
	private int codigoUm;
	
	
	Connection con;
	Conexion cn;
    String[][] conversion = new String[10][10];
	public AdministraAlternativo(String id,String art, String cant, String um) {
		this.id=id;
		this.art=art;
		this.cant=cant;
		this.um=um;
		MostrarAlternativo();
	}
	public AdministraAlternativo(){
		
	}
	
	private ArrayList<String> getAlternativosUM()
	{
	cn = new Conexion();
		con = cn.getConexion();
	 ArrayList<String> descUm = new ArrayList<>();
	
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
		ArrayList<String> alternativosDescripcion = getAlternativosUM(); //alternativos de UM
		if(id!=null)
		{
			Nodo n=new Nodo();
			ArrayList<String> articulos=n.getAlternativos(Integer.parseInt(id));
			if(articulos!=null)
				{AdministraAlternativoUI admUI = new AdministraAlternativoUI(id,art, cant, um,alternativosDescripcion,conversion,articulos);
				admUI.setVisible(true);
				}
			else
				JOptionPane.showMessageDialog(null, "El Articulo no posee alternativos");
		}
		
	}
	
//	public String[][] Recargar()
//	{
//		ArrayList<String> alternativosDescripcion = getAlternativos();
//		return conversion;
//	}
	public void cambiarDefault(int padreId, int hijoId, int nuevo){
		
		ArrayList<String> descUm = new ArrayList();
	
	try {
		cn = new Conexion();
		con = cn.getConexion();
		
		PreparedStatement stm;
		
		ResultSet rs;
	
				
		StringBuilder sb = new StringBuilder();
		
//		sb.append(" IF EXISTS (SELECT * FROM BOM WHERE padre="+padreId+" and hijo= "+hijoId+")");
//		 sb.append("BEGIN ");
//		     sb.append(" UPDATE BOM ");
//			 sb.append("SET borrado=1 ");
//			 sb.append(" WHERE padre= "+padreId+" and hijo=  "+hijoId+"");
//			 sb.append("INSERT INTO BOM (padre,hijo,cantidad,um_id,fecha_inicio,fecha_fin,user_id,descr_upd,lugar_upd) ");
//			 sb.append("VALUES ( "+padreId+", "+nuevo+",101,1,GETDATE(),null,2,'cargo bom','casa') ");
//		sb.append("END ");
//		 sb.append("ELSE ");
//		 sb.append("BEGIN ");
//			 sb.append("INSERT INTO BOM (padre,hijo,cantidad,um_id,fecha_inicio,fecha_fin,user_id,descr_upd,lugar_upd) ");
//			 sb.append("VALUES ( "+padreId+", "+nuevo+",101,1,GETDATE(),null,2,'cargo bom','casa') ");
//		 sb.append("END ");
		 
		sb.append("UPDATE BOM ");
//		sb.append("SET borrado=1 ,fecha_fin=getDate() ");
		sb.append("SET hijo="+nuevo+"");
		sb.append("WHERE padre="+padreId+" and hijo= "+hijoId+"");
		 stm=null;
		 stm = con.prepareStatement(sb.toString());
		 stm.executeUpdate();
//		 int ind=0;
//		 while (rs.next())
//		 {
//			 
//			 conversion[ind][0]=String.valueOf(rs.getInt("um_principal"));
//			 String sP=rs.getString("descPrincipal");
//			 conversion[ind][1]=sP;
//			 String s=rs.getString("descripcion_str");
//			 descUm.add(s);
//			 //conversion[ind][1]=rs.getString("un_alternativo");
//			 conversion[ind][2]=s;
//			 conversion[ind][3]=rs.getString("factor");
//			 ind++;
//		 }
		}catch(Exception e) { e.printStackTrace(); System.out.println("Error: getAlternativos");}

	}
	
}
