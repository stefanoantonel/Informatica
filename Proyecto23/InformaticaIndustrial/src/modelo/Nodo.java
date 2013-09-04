package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import persistencia.NodoDAO;
import ui.MenuUI;
import utilidades.DateClass;

public class Nodo {


	private Nodo padre;
	private  ArrayList<Nodo> hijo;	
	private Articulos art;
	private Float cantidad;
	private  ArrayList<Nodo> alternativo;
	private String fecha_inicio;
	private String fecha_fin;
	private Calendar fecha_i;
	private Calendar fecha_f;
    private Integer id;
    private DateClass date = new DateClass();
    private NodoDAO nDao = new NodoDAO();
//    private Arbol a = new Arbol();
    
    
	private String descripcion;
		//1:Make, 2:Buy, 3:Generic
	private int tipo;
    private String um;/**888*/

	public Nodo() {
		
	}
    public Nodo(Integer idArt)
	{
		art= new Articulos(idArt);
	}

	public Nodo(Integer idArt, Integer idNodo)
	{
		id=idNodo;
		art= new Articulos(idArt);
	}
		public Nodo(Articulos art)
		{
			this.art=art;
			hijo=null;
		}
    
	
    
    public Calendar getFecha_inicio()
    {
    	
    	fecha_i = date.obtenerFecha(fecha_inicio);
    	//System.out.println("fecha_i: "+fecha_i);
    	return fecha_i;
    }
    public Calendar getFecha_fin()
    {
    	fecha_f = date.obtenerFecha(fecha_fin);
    	return fecha_f;
    }
    
    public void setFecha_inicio (String inicio)
    {
    	fecha_inicio= inicio;
    }
    
    public void setFecha_fin (String fin)
    {
    	fecha_inicio= fin;
    }
    
		
		public Nodo(Float idArt,Float idNodo )
		{
			id=(int)(Math.round(idNodo));
			int idArticulo= (int)(Math.round(idArt));
			art= new Articulos(idArticulo);
			hijo=null;
			
			
		}
		
		
		public Integer getId ()
		{
			return id;
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
			if(hijo==null)
				hijo = new ArrayList<Nodo>();
		   
			hijo.add(h);

		}
		
		public ArrayList<Nodo> getAlternativo()
		{
			return alternativo;
		}

		public void agregarAlternativos(Nodo alt)
		{
			if(alternativo== null)
				alternativo = new ArrayList<>();
		   
			alternativo.add(alt);

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
		
		public Articulos getArt()
		{
			return art;
		}
		
		

		
		
		public ArrayList<String> getAlternativos(Integer id)
		{

			ArrayList<Nodo> nodosBom = new ArrayList<>();
			ArrayList<String> artAlt=null;
			//Arbol a=new Arbol();
			nodosBom = MenuUI.arbol.getNodoByIdBom(id);
			Nodo padre= nodosBom.get(0);
			Nodo hijo= nodosBom.get(1);
			
			String artPadre = padre.getArt().getValor().toString();
			String artHijo=	hijo.getArt().getValor().toString();
			
			artAlt=nDao.obtenerArlternativos(artPadre, artHijo );
		
			return artAlt;
		}
		
		
		public String obtenerDefecto(Integer id){
			//Arbol a=new Arbol();
			String xDefecto=null;
			Nodo nodo=MenuUI.arbol.getNodoById(id);
			
			nDao.obterXdefecto(nodo.getPadre().getId().toString(), nodo.getId().toString());
						
						
			return xDefecto;
		}
		
		public void eliminarRelacion (Nodo padre,Nodo hijo)
		{
			nDao.eliminarRealcion(padre, hijo);
			return;
		}
}
