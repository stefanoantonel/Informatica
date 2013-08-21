package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

<<<<<<< HEAD
<<<<<<< HEAD
import persistencia.Conexion;
=======
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
import javax.swing.JOptionPane;

import persistencia.Conexion;
import ui.AgregaArticuloUI;
import ui.ArticuloUI;
<<<<<<< HEAD
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e

public class AgregaArticulo {
	String descipcion;
	int materialID, umID, tipoID, centroCostoID, claseMercioID;
	String alto, ancho, profundidad, diametro, peso,color,costoStd, precioVtaStd,descripcionStd= descipcion,  descUpd="cargo"+ descipcion;
	ArrayList<String> um, material,tipo;
	
	
	
	
	public AgregaArticulo(){
<<<<<<< HEAD
<<<<<<< HEAD
		
=======
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
		um=new ArrayList<>();
		material=new ArrayList<>();
		tipo=new ArrayList<>();
//		InicializarUI();
<<<<<<< HEAD
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
	}
	public void InicializarUI(){
		
		
		Connection con;
		ResultSet rs;
		
		
		//----------------------------------------------ARRAY PARA LAS UNIDADES DE MEDIDA	
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT um.id [um id],d.descripcion_str ");
					sb.append("FROM [Unidad Medida] um ");
					sb.append("inner join Descripcion d on um.descripcion_id=d.id ");
<<<<<<< HEAD
<<<<<<< HEAD

=======
					
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
					
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							um.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
<<<<<<< HEAD
<<<<<<< HEAD
						}
					}
					con.close();
=======
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
							e.printStackTrace();
						}
					}
					
<<<<<<< HEAD
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				
				//----------------------------------------------ARRAY PARA tipo
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
					sb.append("SELECT tipo.id [um id],d.descripcion_str ");
					sb.append("FROM [Tipo] tipo ");
					sb.append("inner join Descripcion d on tipo.descripcion_id=d.id ");

					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							tipo.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
						}
					}
<<<<<<< HEAD
<<<<<<< HEAD
					con.close();
=======
				
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
				
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				//----------------------------------------------ARRAY PARA material
				try {
					Conexion cn2 = new Conexion();
					con = cn2.getConexion();
					StringBuilder sb = new StringBuilder();
<<<<<<< HEAD
<<<<<<< HEAD
					sb.append("SELECT ma.id [um id],d.descripcion_str ");
					sb.append("FROM [Material] ma ");
					sb.append("inner join Descripcion d on ma.descripcion_id=d.id ");
=======
					sb.append("SELECT * ");
					sb.append("FROM [Material] ma ");
//					sb.append("inner join Descripcion d on ma.descripcion_id=d.id ");
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
					sb.append("SELECT * ");
					sb.append("FROM [Material] ma ");
//					sb.append("inner join Descripcion d on ma.descripcion_id=d.id ");
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e

					PreparedStatement stm;
					stm = con.prepareStatement(sb.toString());
					rs = stm.executeQuery();
				
					while (rs.next()) {
						try {
							material.add(rs.getString(2));
							
						} catch (Exception e) {
							System.out.println("error new nodo padre");
						}
					}
<<<<<<< HEAD
<<<<<<< HEAD
					con.close();
=======
					
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
					
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
				}
				catch (Exception e) {
					System.out.println("error reporta conexion" );
					e.printStackTrace();
				}
				
				
<<<<<<< HEAD
<<<<<<< HEAD
				
				
	}
=======
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
			AgregaArticuloUI aui=new AgregaArticuloUI(material,tipo,um);
//			aui.setVisible(true);
				
	}

	public void agregarArticulo(String descripcionSeleccionado, String materialSeleccionado, String unidadMedidaSeleccionado, String tipoSeleccionado){
//		Arbol arb=new Arbol();
		int materialId, umId, tipoId;
//		Nodo n=null;
//		n=arb.getNodoByDescripcion(descripcionSeleccionado);
		umId=getUmBy(unidadMedidaSeleccionado);
		materialId=getMaterialBy(materialSeleccionado);
		tipoId=getTipoBy(tipoSeleccionado);
		
		insertarArticulo(descripcionSeleccionado,umId,materialId,tipoId);
	}
	
	public int getUmBy(String um){
		Connection con;
		ResultSet rs;
		int umId=-1;
		
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
			stm.setString(1, um);
			
			rs = stm.executeQuery();
			rs.next();
			umId=rs.getInt(1);
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		return umId;
	}

	public int getMaterialBy(String mat){
		Connection con;
		ResultSet rs;
		int material=-1;
		try{//---------------------------------ID material
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * ");
			sb.append("FROM Material m ");
//			sb.append("inner join Descripcion d on um.descripcion_id=d.id ");
			sb.append("WHERE m.nombre= '"+mat+"' ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
//			stm.setString(1, um);
			
			rs = stm.executeQuery();
			rs.next();
			material=rs.getInt(1);
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		return material;
		
	}

	public int getTipoBy(String tip){
		Connection con;
		ResultSet rs;
		int tipo=-1;
		
		try{//---------------------------------ID tipo
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT t.id, d.descripcion_str ");
			sb.append("FROM Tipo t ");
			sb.append("inner join Descripcion d on t.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str='"+tip+"' ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
//			stm.setString(1, um);
			
			rs = stm.executeQuery();
			rs.next();
			tipo=rs.getInt(1);
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		return tipo;
	}

	public void insertarArticulo(String descripcion, int umId,int materialId,int tipoId){
		int descripcionId=-1;
		
		Connection con;
		ResultSet rs=null;
		//--------------------------------inserta descricion y busca la clave de insercion
		try{
		Conexion cn1 = new Conexion();
		con = cn1.getConexion();
		StringBuilder sb1=new StringBuilder();
		sb1.append("INSERT INTO Descripcion (id,descripcion_str) ");
		sb1.append("VALUES ((select max(id)+1 from Descripcion),'"+descripcion+"') ");
//		sb1.append(" END");
		PreparedStatement ps=con.prepareStatement(sb1.toString());
		ps.executeUpdate();
//		JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: insercion");}
		
		try{//---------------------------------ID ultima desccricion
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT id ");
			sb.append("FROM Descripcion d ");
//			sb.append("inner join Descripcion d on t.descripcion_id=d.id ");
			sb.append("WHERE d.descripcion_str='"+descripcion+"' ");
			//PREPARAR CONSULTA
			PreparedStatement stm;
			stm = con.prepareStatement(sb.toString());
			//PONER VALORES
//			stm.setString(1, um);
			
			rs = stm.executeQuery();
			rs.next();
			descripcionId=rs.getInt(1);
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");}
		
		try{//---------------------------------INSERTAR articulo
			Conexion cn1 = new Conexion();
			con = cn1.getConexion();
			StringBuilder sb1=new StringBuilder();
			sb1.append("INSERT INTO Articulo (descripcion_id,material_id,um_id,tipo_id,descr_upd) ");
			sb1.append("VALUES ('"+descripcionId+"' ,"+materialId+" ,"+umId+", "+tipoId+" , 'cargo "+descripcion+"') ");
//			sb1.append(" END");
			PreparedStatement ps=con.prepareStatement(sb1.toString());

			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
			
		}catch (Exception e){e.printStackTrace(); System.out.println("error insertar");
		JOptionPane.showMessageDialog(null, "ERROR: Relacion ya establecida");}
		
	}




<<<<<<< HEAD
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
=======
>>>>>>> 64a622bc85c2213ef7482b078f2f76a8bf4eaf5e
}
