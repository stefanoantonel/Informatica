package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import persistencia.Conexion;
import ui.AgregaRelacionUI;

public class AgregaRelacion {
	Nodo nodo;
	int padreId;
	int hijoId;
	int umId;
	//private Connection con;
	//private Connection con2;
	
	public AgregaRelacion() {

	}

	
	public Integer  ObtenerArituculoID (String artDesc)
	{
		ResultSet rs=null;
		Connection con;
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
		Integer id = null;
		
		try {//---------------------------------ID DEL PADRE
			StringBuilder sb = new StringBuilder();
			sb.append("SElECT a.id ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str= ? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			
			String a=artDesc;
			stm.setString(1, a);			
			rs = stm.executeQuery();
			rs.next();
			id=rs.getInt(1);

		} catch (Exception e) {
			System.out.println("error buscar id con descripcion");
			e.printStackTrace();
			}
		
		return id;	
	}
	
	
	public void InsertarRelacion(String padreDesc, String hijoDesc,String cantidad, String un, String fechaInicio, String fechaFin) {
		Connection con;
		ResultSet rs=null;
		//Conexion cn1 = new Conexion();
		//con = cn1.getConexion();
		Integer pId=null;
		Integer hId=null;
		if(fechaFin==null||fechaFin.equals("")){
			fechaFin="NULL ";
		}
		else{
			fechaFin="'"+fechaFin+"'";
		}
		if(fechaInicio==null||fechaInicio.equals("")){
			fechaInicio="getDate() ";
		}
		else{
			fechaInicio="'"+fechaInicio+"'";
		}
			
		
		pId=ObtenerArituculoID(padreDesc);
		hId=ObtenerArituculoID(hijoDesc);
		
		try{//---------------------------------ID UM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT um.id [um id],d.descripcion_str ");
			sb.append("FROM [Unidad Medida] um ");
			sb.append("inner join Descripcion d on um.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str = ? ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
			stm.setString(1, un);
			
			rs = stm.executeQuery();
			rs.next();
			umId=rs.getInt(1);
			rs.close();
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		
		try{//---------------------------------INSERTAR
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			//Siempre agrego en la BOM, lo que va a cambiar es la fecha del lastupdate
			
			StringBuilder sb1=new StringBuilder();
			sb1.append("INSERT INTO BOM (padre,hijo,cantidad,um_id,fecha_inicio,fecha_fin,user_id,descr_upd,lugar_upd,borrado) ");
			//HACER!!!!!!!!!!!!!!!!!!!!!!
			//sb1.append("VALUES ("+padreId+","+hijoId+","+cantidad+","+umId+","+fechaInicio+","+fechaFin+",2,'cargo bom','casa',0) ");
			sb1.append("VALUES ("+pId+","+hId+","+cantidad+","+umId+","+fechaInicio+","+fechaFin+",2,'cargo bom','casa',0) ");
			PreparedStatement ps=con.prepareStatement(sb1.toString());
			ps.executeUpdate();			
			JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida");}
		Integer artGenerico=null;
		try{//---------------------------------INSERTAR en ARTICULO ALTERNATIVO
			//obtengo el indice del generico de la tabla BOM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder generico=new StringBuilder();
			generico.append(" SELECT generico_id FROM BOM WHERE padre= "+pId+" and hijo= "+hId);
			PreparedStatement psGenerico=con.prepareStatement(generico.toString());
			ResultSet rGenerico=psGenerico.executeQuery();
			rGenerico.next();
			artGenerico = rGenerico.getInt(1);
			rGenerico.close();
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida En la tabla Generico");}
		
		try{//---------------------------------INSERTAR en ARTICULO ALTERNATIVO
			//obtengo el indice del generico de la tabla BOM
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb1=new StringBuilder();
			sb1.append(" INSERT INTO [Articulos Alternativos] (artGenerico_id,artAlternativo_id) values ("+artGenerico+", "+hId+")");
			PreparedStatement ps2=con.prepareStatement(sb1.toString());
			ps2.executeUpdate();			
			
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida En la tabla Generico");}
		
		
	}

	
	public boolean ControlAlternativos(String padreDesc, String hijoDesc)
	{
		
		Connection con;
		//con2 = cn1.getConexion();
		ResultSet rs=null;
		Integer padreID=null;
		Integer hijoID=null;
		ArrayList<Integer> idAlt = new ArrayList();
		
		padreID=ObtenerArituculoID(padreDesc);
		hijoID=ObtenerArituculoID(hijoDesc);
		
		try{//---------------------------------Controlar que la relacion exista
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder controlar=new StringBuilder();
			controlar.append(" SELECT * FROM BOM WHERE padre= "+padreID+" and hijo= "+hijoID);
			controlar.append(" and borrado=0 and fecha_inicio in ");
			controlar.append(" (Select fecha_inicio from BOM where padre= "+padreID+" and hijo= "+hijoID+" and GETDATE() between fecha_inicio and fecha_fin)");
			PreparedStatement psControl=con.prepareStatement(controlar.toString());
			ResultSet rControl=psControl.executeQuery();
			if(rControl.next())
			{
				//rControl.close();
				try{
					cn1 = new Conexion();
					con = cn1.getConexion();
				//---------------------------------INSERTAR en ARTICULO ALTERNATIVO
				//obtengo el indice del generico de la tabla BOM
				StringBuilder generico=new StringBuilder();
				generico.append(" SELECT principal_id FROM BOM WHERE padre= "+padreID+" and hijo= "+hijoID);
				PreparedStatement psGenerico=con.prepareStatement(generico.toString());
				ResultSet rGenerico=psGenerico.executeQuery();
				rGenerico.next();
				int artGenerico = rGenerico.getInt(1);
				
				for (int ind=0; ind<idAlt.size(); ind++)
				{
				StringBuilder sb1=new StringBuilder();
				sb1.append(" INSERT INTO [ARTICULO ALTERNATIVO] (artPrincipal_id,artAlternativo_id) values "+artGenerico+", "+idAlt.get(ind)+")");
				sb1.append("Where artPrincipal_id="+artGenerico);
				PreparedStatement ps=con.prepareStatement(sb1.toString());
				ps.executeUpdate();
				}
				}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
				JOptionPane.showMessageDialog(null, "ERROR: Control de existencia de relacion");}
			}	
			else
			{
			  return false;}
			
			
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: Control de existencia de relacion");}
		
		return true;
		
	}
	
	
	public void InsertarAlternativo(String padreDesc, String hijoDesc, ArrayList<String> alternativos) {
		Conexion cn1 = new Conexion();
		Conexion cn2 = new Conexion();
		Connection con;
		ResultSet rs=null;
		Integer padreID=null;
		Integer hijoID=null;
		ArrayList<Integer> idAlt = new ArrayList();
		
		padreID=ObtenerArituculoID(padreDesc);
		hijoID=ObtenerArituculoID(hijoDesc);
		
		
		if(alternativos !=null) 
		{
			ArrayList<Integer> alt = new ArrayList();
			for (String al: alternativos)
			{
				idAlt.add(ObtenerArituculoID(al));
			}
		}
		
//		
//		try{//---------------------------------Controlar que la relacion exista
//			StringBuilder controlar=new StringBuilder();
//			controlar.append(" SELECT * FROM BOM WHERE padre= "+padreId+" and hijo= "+hijoId);
//			controlar.append(" and borrado=0 and fecha_inicio in ");
//			controlar.append(" (Select fecha_inicio from BOM where padre= "+padreId+" and hijo= "+hijoId+" and GETDATE() between fecha_inicio and fecha_fin)");
//			PreparedStatement psControl=con.prepareStatement(controlar.toString());
//			ResultSet rControl=psControl.executeQuery();
//			if(rControl.next())
//			{
			try{
				con = cn1.getConexion();
		        //---------------------------------INSERTAR en ARTICULO ALTERNATIVO
				//obtengo el indice del generico de la tabla BOM
				StringBuilder generico=new StringBuilder();
				generico.append(" SELECT principal_id FROM BOM WHERE padre= "+padreID+" and hijo= "+hijoID);
				PreparedStatement psGenerico=con.prepareStatement(generico.toString());
				ResultSet rGenerico=psGenerico.executeQuery();
				Integer artGenerico=null;
				if(rGenerico.next())
					{artGenerico = rGenerico.getInt(1);
					System.out.println("artGenerico  "+artGenerico );}
				
				for (int ind=0; ind<idAlt.size(); ind++)
				{
				 try{
					 con = cn2.getConexion();
					StringBuilder sb1=new StringBuilder();
					sb1.append(" INSERT INTO [Articulos Alternativos] (artPrincipal_id,artAlternativo_id) values ("+artGenerico+", "+idAlt.get(ind)+")");
					//sb1.append("Where artPrincipal_id="+artGenerico);
					PreparedStatement ps=con.prepareStatement(sb1.toString());
					ps.executeUpdate();
				 }catch(Exception e){e.printStackTrace(); System.out.println("error insertar");}
				}
			}catch(Exception e){e.printStackTrace(); System.out.println("error insertar");}
//			}	
//			else
//			{JOptionPane.showMessageDialog(null, "La relacion entre los articulos seleccionados no ha sido establecida");
//			  return;}
//			
//			
//		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
//		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida");}
		
		
//		
//		
//		try{//---------------------------------INSERTAR en ARTICULO ALTERNATIVO
//			//obtengo el indice del generico de la tabla BOM
//			StringBuilder generico=new StringBuilder();
//			generico.append(" SELECT principal_id FROM BOM WHERE padre= "+padreId+" and hijo= "+hijoId);
//			PreparedStatement psGenerico=con.prepareStatement(generico.toString());
//			ResultSet rGenerico=psGenerico.executeQuery();
//			rGenerico.next();
//			int artGenerico = rGenerico.getInt(1);
//			
//			StringBuilder sb1=new StringBuilder();
//			sb1.append(" INSERT INTO [ARTICULO ALTERNATIVO] (artPrincipal_id,artAlternativo_id) values "+artGenerico+", "+hijoId+")");
//			sb1.append("Where artPrincipal_id="+artGenerico);
//			PreparedStatement ps=con.prepareStatement(sb1.toString());
//			ps.executeUpdate();			
//			
//			
//		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
//		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida");}
		
	}
	
	
	public void InicilizarUI() {

		Connection con;
		ResultSet rs;

		DefaultListModel<String> modelo1 = new DefaultListModel<>();
		DefaultListModel<String> modelo2 = new DefaultListModel<>();
		ArrayList<String> um=new ArrayList<>();
		
		//------------------------------------------MODELO PARA EL PADRE
		try {
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id[Articulo ID], d.descripcion_str[Descripcion] ,a.tipo_id[Tipo ID] ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id = d.id ");
			sb.append("WHERE a.tipo_id=1 ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					nodo = new Nodo(rs.getInt(1));
					nodo.setDescripcion(rs.getString(2));
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}

				// String a = rs.getString(2);

				try {
					modelo1.addElement(nodo.getDescripcion());
					
				} catch (Exception e) {
					System.out.println("erros add eleme");
					e.printStackTrace();
				}

			}
			con.close();
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}

		//---------------------------------------------------MODELO PARA EL HIJO
		try {
			Conexion cn2 = new Conexion();
			con = cn2.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT a.id[Articulo ID], d.descripcion_str[Descripcion] ");
			sb.append("FROM Articulo a ");
			sb.append("inner join Descripcion d on a.descripcion_id=d.id ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();

			while (rs.next()) {
				try {
					nodo = new Nodo(rs.getInt(1));
					nodo.setDescripcion(rs.getString(2));
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}

				// String a = rs.getString(2);

				try {
					modelo2.addElement(nodo.getDescripcion());
				} catch (Exception e) {
					System.out.println("erros add eleme");
					e.printStackTrace();
				}
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println("error reporta conexion");
			e.printStackTrace();
		}
		
		
		//----------------------------------------------MODELO PARA LAS UNIDADES	
		try {
			Conexion cn2 = new Conexion();
			con = cn2.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT um.id [um id],d.descripcion_str ");
			sb.append("FROM [Unidad Medida] um ");
			sb.append("inner join Descripcion d on um.descripcion_id=d.id ");

			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			rs = stm.executeQuery();
		
			while (rs.next()) {
				try {
					um.add(rs.getString(2));
					
				} catch (Exception e) {
					System.out.println("error new nodo padre");
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println("error reporta conexion" );
			e.printStackTrace();
		}
		AgregaRelacionUI rep = new AgregaRelacionUI(modelo1, modelo2,um.toArray());
		rep.setVisible(true);
	}
}
