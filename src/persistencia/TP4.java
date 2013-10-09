package persistencia;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Proveedor;

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
					//TP4 tp = new TP4();
					Proveedor a = new Proveedor();
					for(ArrayList<Integer> x: a.capacidadLote(2) )
					{
						System.out.println("proveedor: "+x.get(0));
						System.out.println("cap: "+x.get(1));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*
	public TP4 (){
		ArrayList<Integer> conec;
		
	try {

		/*
		 * el factor es porque si necesito que haya 4 patas, entonces tengo quehacer el cliclo 4 veces.. 
		 * 
		 
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
		calculaTiempo(2,1);
		//la primra vez siemrpe recibe un 1 factor ?, el otro es el 2 es el del cliclo 
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
		/*
		 * levate el ciclo y la sumatoria de tiempo de ese cliclo que luego lo suma en tiempo toral multilicaod por el factor (por 2 patas)
		 * dsp se fija en la matriz si tenia predecesor llama de nuevo a la funcion con el factor para el ciclo que hace al sguiente
		 
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
*/	
	
}