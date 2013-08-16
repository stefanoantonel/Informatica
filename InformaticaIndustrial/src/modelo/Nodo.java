package modelo;

import java.util.ArrayList;

public class Nodo {


		private Integer valor;
		private Nodo padre;
		private  ArrayList<Nodo> hijo;
		private Integer cont=0;
		private Nodo proxPadre;
		private Integer cuentaHijo=0;
		private String descripcion;
		private Float cantidad;
		private String um;

		//i000

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
		
		public void Explosiona(){
			Jtree j=new Jtree(this);
		}

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
		
//		public Integer GetProxHijo()
//		{
//			cont++;
//			return hijo[cont--];
//		}
		
		public void SetProxPadre (Nodo proximo)
		{
			proxPadre=proximo;
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
		
		
		
}