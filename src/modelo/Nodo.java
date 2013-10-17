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
	private int cantidad;
	private  ArrayList<Nodo> alternativo;
	private String fecha_inicio;
	
	private ArrayList<Nodo> listaHijos=new ArrayList<>(); 
	//tiene los buy de un producto que le paso y las cantidades totales de las hojas, es para el MRP
	//cargo nuevos nodos del articulo pero con la cantidad que es necesario comprar
	
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}

	private String fecha_fin;
//	private Calendar fecha_i;
//	private Calendar fecha_f;
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
    
	
    
//    public Calendar getFecha_inicio()
//    {
//    	
//    	fecha_i = date.obtenerFecha(fecha_inicio);
//    	//System.out.println("fecha_i: "+fecha_i);
//    	return fecha_i;
//    }
//    public Calendar getFecha_fin()
//    {
//    	fecha_f = date.obtenerFecha(fecha_fin);
//    	return fecha_f;
//    }
    
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
		
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cant) {
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
		
//		private void loadHijosBuyCantidad(Nodo n,int cantidadP,ArrayList<Nodo> lista2){
//			//La primera vez viene con 1 que es el de los padres principales. 
//			//Para que me de cuanto necesito de buy y dsp lo multiploco por lo que tengo en la lista del padre 
//			System.out.println(n.getCantidad());
//			int cantidadNodo=n.getCantidad();
//			if(cantidadNodo<1){ cantidadNodo=1;}
//			cantidad=cantidadNodo*cantidadP;
//			if(n.GetHijos()!=null){
//				for(Nodo hijo:n.GetHijos()){
//					//int cantidadHijo=hijo.getCantidad();
//					//cantidad=cantidadHijo*cantidad;
//					System.out.println("id: "+hijo.getArt().getValor()+"cantidad: "+cantidad+" cantidadHijo: "+cantidadNodo);
//					//if(hijo.GetHijos()!=null){
//					loadHijosBuyCantidad(hijo,cantidad,lista2);
//						//cantidad=cantidadHijo*cantidad;
//						System.out.println("Cantidad dsp rec: "+cantidad);
//						listaHijos=lista2;
//						cantidad=cantidadP;
//					
//				}
//			}
//			else{ //cuando es hoja
//				Nodo hoja=new Nodo();
//				hoja=n;
//				hoja.setCantidad(cantidad);
//				boolean yaEstaba=false;
//				//Creo un nodo igual que el de antes pero cambiado la cantidad
//				for(Nodo nodo:lista2){
//					if(nodo.getArt().getValor()==hoja.getArt().getValor()){
//						int cant=nodo.getCantidad();
//						nodo.setCantidad(cant+cantidad);
//						lista2.remove(hoja);
//						yaEstaba=true;
//					}	
//				}
//				if(yaEstaba==false){
//					lista2.add(hoja);
//					System.out.println("-"+hoja.getDescripcion());
//				}
//			}
//		}
		
		private void loadHijosBuyCantidad(Nodo n,int cantidadP,ArrayList<Nodo> lista2){
			
			if(n.GetHijos()!=null){
				for(Nodo hijo:n.GetHijos()){
					if(hijo.getCantidad()<1){ hijo.setCantidad(1);}
					System.out.println("cant recursiva:"+cantidadP*hijo.getCantidad());
					loadHijosBuyCantidad(hijo, cantidadP*hijo.getCantidad(), lista2);
					
					listaHijos=lista2;
				}
			}
			else{ //cuando es hoja
				Nodo hoja=new Nodo();
				hoja=n;
				hoja.setCantidad(cantidadP);
				boolean yaEstaba=false;
				//Creo un nodo igual que el de antes pero cambiado la cantidad
				for(Nodo nodo:lista2){
					if(nodo.getArt().getValor()==hoja.getArt().getValor()){
						int cant=nodo.getCantidad();
						nodo.setCantidad(cant+cantidad);
						lista2.remove(hoja);
						yaEstaba=true;
					}	
				}
				if(yaEstaba==false){
					lista2.add(hoja);
					System.out.println("-"+hoja.getDescripcion());
				}
			}
		}
		
		public ArrayList<Nodo> getListaHijos(Nodo n,int cantidad,ArrayList<Nodo> lista2) {
			loadHijosBuyCantidad( n,cantidad,lista2);
			return listaHijos;
		}
}
