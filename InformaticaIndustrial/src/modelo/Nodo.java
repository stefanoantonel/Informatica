package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import persistencia.Conexion;

public class Nodo {


		private Integer valor;
		private Nodo padre;
		private  ArrayList<Nodo> hijo;
		private String descripcion;
		private Float cantidad;
		//1:Make, 2:Buy, 3:Generic
		private int tipo;

		private String um;/**888*/



		public Nodo(Float p)
		{
			valor=(int)(Math.round(p));
			
		}

		public Nodo(Integer p)
		{
			valor=p;
			
		}
		public Integer GetValor ()
		{
			return valor;
		}
		
//		public void Explosiona(){
//			Jtree j=new Jtree(this);
//		}

		public void setPadre (Nodo p)
		{
			padre=p;
		}
		public Nodo getPadre ()
		{
			return padre;
		}
		
		public ArrayList<Nodo> GetHijos()
		{
			return hijo;
		}

		public void AgregarHijo(Nodo h)
		{
			if(hijo== null)
				hijo = new ArrayList<>();
		   
			hijo.add(h);

		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		public Float getCantidad() {
			return cantidad;
		}
		public void setCantidad(Float cant) {
			this.cantidad = cant;
		}
		
		public String getUm() {
			return um;
		}
		
		public void setUm(String unmed) {
		    um=unmed;
		}
		
		public int getTipo() {
			return tipo;
		}
		
		public void setTipo(int t) {
		    tipo=t;
		}
		
		
		public ArrayList<String> getAlternativos(String desc)
		{
			Connection con;
			Conexion cn = new Conexion();
			con=cn.getConexion();
			ResultSet rs;

			ArrayList<String> alt=new ArrayList<>();
			alt=null;
			Arbol a = new Arbol();
			Nodo nodo=a.getNodoByDescripcion(desc);
			try {
				Conexion cn1 = new Conexion();
				con = cn1.getConexion();
				StringBuilder sb = new StringBuilder();
				sb.append("SELECT a.id[Articulo ID], d.");
				
				PreparedStatement stm;
				stm = con.prepareStatement(sb.toString());
				rs = stm.executeQuery();
				
				while (rs.next()) {
				    alt.add(rs.getString(1));
				}
				
			} catch (Exception e) {
				System.out.println("error en getAlternativos");
			}
						
						
			return alt;
		}
		
		
		
}
