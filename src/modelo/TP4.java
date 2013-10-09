package modelo;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistencia.Conexion;
import ui.MenuUI;

public class TP4 {
	Conexion c=new Conexion();
	Connection cn= c.getConexion();
	ArrayList<ArrayList<Integer>> conexionCiclos = new ArrayList<>();
	float ttotal;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	TP4 tp = new TP4();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public TP4 (){
		ArrayList<Integer> conec;
		
	try {

		StringBuilder sb=new StringBuilder();
		sb.append("select cpre.id as pre, cpos.id as pos,ceiling(ai.cantidad/ae.cantidad) as factor from [Conexion Ciclos] cc");
		sb.append(" inner join Ciclo cpre on cc.id_ciclo_pre=cpre.id");
		sb.append(" inner join Ciclo cpos on cc.id_ciclo_pos=cpos.id");
		sb.append("	inner join Articulo_egresa ae on cpre.id_egresa = ae.id_egresa");
		sb.append(" inner join Articulo_ingresa ai on cpos.id_ingresa=ai.id_ingresa");
		sb.append(" Where ae.id_articulo=ai.id_articulo");
		ResultSet conCiclo = cn.prepareStatement(sb.toString()).executeQuery();
		while (conCiclo.next()) {  //controlar el idNodos
			conec = new ArrayList<>();			
			conec.add(Integer.parseInt(conCiclo.getString("pre")));
			conec.add(Integer.parseInt(conCiclo.getString("pos")));
			conec.add(conCiclo.getInt("factor"));
			System.out.println("Conecxion entre ciclos:"+conec.get(0)+"  "+conec.get(1)+" factor:"+ conec.get(2));
			conexionCiclos.add(conec);
	        }
		ttotal=0;
		calculaTiempo(1,1);
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Problema en Conexion Ciclos");
	}
	}
	
	
	public void calculaTiempo (Integer id,Integer factor)
	{
		int cicloId=0;
		int cantIng=0;
		int cE=0;
		StringBuilder sb=new StringBuilder();
		sb.append("select distinct d.descripcion_str, (c.tiempo_setup+c.tiempo_montaje+c.tiempo_end) as tiempo,c.id");
		sb.append(" from Ciclo c");
		sb.append(" inner join Articulo_egresa ae on c.id_egresa=ae.id_egresa");
		sb.append(" inner join Articulo a on ae.id_articulo=a.id");
		sb.append(" inner join Descripcion d on d.id=a.descripcion_id");
		sb.append(" inner join Articulo_ingresa ai on c.id_ingresa=ai.id_ingresa");
		sb.append(" where c.id=").append(id);
		try{
		ResultSet tciclo = cn.prepareStatement(sb.toString()).executeQuery();
		
		while (tciclo.next()) {  //controlar el idNodos
			if (factor<1)
				factor=1;
			System.out.println("factor: "+factor);
			ttotal+=(Float.parseFloat(tciclo.getString("tiempo")))*factor;
			cicloId=tciclo.getInt("id");
			System.out.println("tiempo intermedio: "+ttotal);
			System.out.println("ciclo id: "+cicloId);
	        }
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Problema calculaTotal");
		}
		for(ArrayList<Integer> c: conexionCiclos)
		{	
			if(c.get(1)==cicloId)
			{
				calculaTiempo(c.get(0),c.get(2));
			}
		}
		System.out.println("tiempo total= " + ttotal);
	}
	
	
}
