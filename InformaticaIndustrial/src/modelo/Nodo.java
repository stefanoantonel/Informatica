package modelo;

import java.util.ArrayList;

public class Nodo {


		private Integer valor;
		private Nodo padre;
		private  ArrayList<Nodo> hijo;
		private String descripcion;
		private Float cantidad;
		//1:Make, 2:Buy, 3:Generic
		private int tipo;
		private Integer valorXdefecto;
		private String xDefectoDesc;

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
		
		public Integer getXdefecto() {
			    return valorXdefecto;
		}
		
		public void setXdefecto(int defect) {

			valorXdefecto=defect;
		}
		
		public String getXdefectoDesc() {
		    return xDefectoDesc;
		}
		
		public void setXdefectoDesc(String defect) {
	
			xDefectoDesc=defect;
		}
		
		
}
