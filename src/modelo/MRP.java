package modelo;

import java.io.ObjectInputStream.GetField;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import persistencia.MrpDao;
import txt.Lectura;
import ui.MrpPruebaTabla;
import utilidades.Casteo;

public class MRP {
	
	private static Arbol arbol;
	private static ArrayList<ArrayList<Integer>> tablaMrp=new ArrayList<>();
	private static Stock s;
	

	public static void main(String[] args) {
		MRP m=new MRP();
		/*
		ArrayList<Nodo> padres;
		padres=m.obtenerPadresPrincipales();
		ArrayList<Nodo> buy=new ArrayList<>();
		padres.get(6).getHijosBuyCantidad(padres.get(6),1,new ArrayList<Nodo>());
		buy=padres.get(6).getListaHijos();
		for(Nodo n:buy){
			System.out.println(n.getDescripcion()+"Cant: "+n.getCantidad());
		}
		
		ArrayList<ArrayList<Integer>> provCapLote=m.getSetentaTreinta(800, 2);
		for(ArrayList<Integer> lista:provCapLote){
			System.out.println(lista);
		}
		*/
//		m.setPadres(400, 2);
//		ArrayList<Integer>listaFactor=m.getListaAdelantoFactor(m.getTablaMrp().get(0), 2, 100);
//		System.out.println(listaFactor);
		s = new Stock();
		m.obtenerPadresPrincipales();
		m.armarMRP();
	}
	
	public ArrayList<Nodo> obtenerPadresPrincipales ()
	{
		arbol = new Arbol();
		return arbol.getPadresPrincipales();
	}
	
	public ArrayList<ArrayList<Integer>> getSetentaTreinta(int cantidadTotal, int articuloID){
		double cantidadP1=0,cantidadP2=0,cantidadFalta,capacidadP1,capacidadP2,setenta,treinta;
		double cantidadP12=0,cantidadP22=0, totalP1,totalP2;
		ArrayList<ArrayList<Integer>> provCant=new ArrayList<>();
		setenta=0.7;
		treinta=0.3;
		Proveedor prove=new Proveedor();
		ArrayList<ArrayList<Integer>> capLoteProv=prove.loadcapacidadLote(articuloID);
		//Obtengo de lo que me llego de la lista que tiene ID-prov,Capacidad,Lote
		capacidadP1=capLoteProv.get(0).get(1); 
		capacidadP2=capLoteProv.get(1).get(1);
	
		
		int proveedor1Id=capLoteProv.get(0).get(0);
		int proveedor2Id=capLoteProv.get(1).get(0);
		int proveedor1Lote=capLoteProv.get(0).get(2);
		int proveedor2Lote=capLoteProv.get(1).get(2);
		
		cantidadP1=capacidadP1*setenta; //saco el 70 % del p1
		cantidadFalta=cantidadTotal;
		if(cantidadP1<cantidadFalta){ //si no me alcanza con p1
			cantidadFalta=cantidadFalta-cantidadP1;
			cantidadP2=capacidadP2*treinta; //saco el 30% del p2
			if(cantidadP2<cantidadFalta){ //no me alcanza con 70-30
				cantidadFalta=cantidadFalta-cantidadP2;
				cantidadP12=capacidadP1*treinta; //el 70 mas el 30 de p1
				if(cantidadP12<cantidadFalta){  //no me alcanza 70-30-30 
					cantidadFalta=cantidadFalta-cantidadP12;
					cantidadP22=capacidadP2*setenta; //el 70 del p2
					
					if(cantidadP22<cantidadFalta){ 
						System.out.println("ERROR: No poveedores insuficientes");
						return null;
					}
					else{ //me alcanzo con los proveedores
						totalP1=cantidadP1+cantidadP12;
//						totalP2=cantidadP2+cantidadP22;
						totalP2=cantidadP2+cantidadFalta;
						ArrayList<Integer> aux1=new ArrayList<>();
						ArrayList<Integer> aux2=new ArrayList<>();
						aux1.add(proveedor1Id);
						aux1.add(getRedondeo(totalP1));
						aux1.add(proveedor1Lote);
						aux1.add((int)capacidadP1);
						
						aux2.add(proveedor2Id);
						aux2.add(getRedondeo(totalP2));
						aux2.add(proveedor2Lote);
						aux2.add((int)capacidadP2);
						
						provCant.add(aux1);
						provCant.add(aux2);
						return provCant;
					}
				}
				else{
//					totalP1=cantidadP1+cantidadP12;
					totalP1=cantidadP1+cantidadFalta;
					totalP2=cantidadP2;
					ArrayList<Integer> aux1=new ArrayList<>();
					ArrayList<Integer> aux2=new ArrayList<>();
					aux1.add(proveedor1Id);
					aux1.add(getRedondeo(totalP1));
					aux1.add(proveedor1Lote);
					aux1.add((int)capacidadP1);
					
					aux2.add(proveedor2Id);
					aux2.add(getRedondeo(totalP2));
					aux2.add(proveedor2Lote);
					aux2.add((int)capacidadP2);
					
					provCant.add(aux1);
					provCant.add(aux2);
					return provCant;
				}
			}
			else{
				totalP1=cantidadP1;
//				totalP2=cantidadP2;
				totalP2=cantidadFalta;
				ArrayList<Integer> aux1=new ArrayList<>();
				ArrayList<Integer> aux2=new ArrayList<>();
				aux1.add(proveedor1Id);
				aux1.add(getRedondeo(totalP1));
				aux1.add(proveedor1Lote);
				aux1.add((int)capacidadP1);
				
				aux2.add(proveedor2Id);
				aux2.add(getRedondeo(totalP2));
				aux2.add(proveedor2Lote);
				aux2.add((int)capacidadP2);
				
				provCant.add(aux1);
				provCant.add(aux2);
				return provCant;
			}
		}
		else{
//			totalP1=cantidadP1;
			totalP1=cantidadFalta;
			ArrayList<Integer> aux1=new ArrayList<>();
			ArrayList<Integer> aux2=new ArrayList<>();
			aux1.add(proveedor1Id);
			aux1.add(getRedondeo(totalP1));
			aux1.add(proveedor1Lote);
			aux1.add((int)capacidadP1);
			
			provCant.add(aux1);
			return provCant;
		}
		
	}
	private int getRedondeo(double a){
		int b=0;
		double c=0;
		c=Math.ceil(a);
		b=(int)c;
		return b;
	}
	private ArrayList<Integer> getNuevaLinea(){

		int largo=tablaMrp.get(0).size();
		ArrayList<Integer> linea=new ArrayList<>();
		while(largo>0){
			linea.add(0);
			largo--;
		}
		return linea;
	}

	public ArrayList<ArrayList<Integer>> getTablaMrp() {
		return tablaMrp;
	}

	public void setTablaMrp(ArrayList<ArrayList<Integer>> tablaMrp) {
		this.tablaMrp = tablaMrp;
	}
	
	private ArrayList<Integer> setPadres(int cantidad,int articuloID){
		//Es la que me hace la primera fila. 
		MrpDao dao=new MrpDao();
		int semanasCongeladas=4;
		ArrayList<Integer> lineaPadre=new ArrayList<>();
		int capacidad=dao.getCapacidad(articuloID);
		if(capacidad!=-1){
			for(int j=semanasCongeladas;j>0;j--){
				lineaPadre.add(0);
			}
			double semanasD=(double)cantidad/(double)capacidad;
			int semanasI=getRedondeo(semanasD);
			
			for(int j=semanasI;j>0;j--){
				lineaPadre.add(capacidad);
			}
//			lineaPadre.add(0);
//			lineaPadre.add(0);
			tablaMrp.add(lineaPadre);
			System.out.println(lineaPadre);
		}
		return lineaPadre;
		
	}
	
	/*
	private ArrayList<Integer> getListaAdelantoFactor(ArrayList<Integer> padre, int factor, int lote){
//		tengo que recibir el factor ya redondeado.
//		cantidad es el que saco del nodo de la funcion getHijosBuy y se lo mando aca. 
		ArrayList<Integer> dreal=new ArrayList<>();
		int indiceP=0;
		
		for(int j=0;j<padre.size();j++){
			//Pongo una lista con el tamaño del padre
			dreal.add(0);
		}
		for(int j=0;j<padre.size();j++){
			if(padre.get(j)!=0){
				indiceP=j;
//				l.set(indidreaceP-factor, cantidadHijo*padre.get(j));
//				ej: cantidad hijo =4 patas * 5 del padre para esa semana ?????
				dreal.set(indiceP-factor, lote);
			}
		}
		return dreal;
	}
	
	*/
	
	private ArrayList<Integer> getDemandaReal(int cantidadHijo, ArrayList<Integer> padre, int semanasAdenato){
		//Cantidad es la cantidad que requeire el padre del hijo (4 patas para mesa)
		//Semanas de adelante es el leadtime+factor todo redondeado para arriba
		ArrayList<Integer> dreal=new ArrayList<>();
		int indiceP=0;
		//Pongo una lista con el tamaño del padre
		for(int j=0;j<padre.size();j++){
			dreal.add(0);
		}
		for(int j=0;j<padre.size();j++){
			if(padre.get(j)!=0){
				indiceP=j;
				
				dreal.set(indiceP-semanasAdenato, cantidadHijo*padre.get(j));
			}	
		}
		return dreal;
	}
	
	private ArrayList<Integer> getDesplazado(ArrayList<Integer> padre, int semanasAdenato){
		//Mueve
		ArrayList<Integer> dreal=new ArrayList<>();
		int indiceP=0;
		Integer cant;
		//Pongo una lista con el tamaño del padre
		for(int j=0;j<padre.size();j++){
			dreal.add(0);
		}
		for(int j=0;j<padre.size();j++){
			cant=padre.get(j);
			if(cant!=0){
				indiceP=j;
				dreal.set(indiceP-semanasAdenato,cant);
			}	
		}
		return dreal;
	}
		
	
	
	private ArrayList<Integer> saturar(int lote, ArrayList<Integer> demanda,int capacidad,Integer idArt){
//		 es con algo parecedio al factor porque si le paso capacidad puede que no me venda lote =capacidad.
		Integer stock=0;
//		hay que llamr a stock flor
// 		si el stock es <0 me voy a retrasar seguro. 
 
		ArrayList<Integer> saturado=new ArrayList<>();
		for(Integer d:demanda){
			if(d!=0){
				if(stock<d){
//					nuevoLote=llamar a funcion getcantidadLotes a perdir--> devuelve cant * lote 	
					int nuevaCantidad=getNuevoCantidadLotePedir(lote, capacidad, d);
					//System.out.println("Nueva cantida:"+ nuevaCantidad);
					saturado.add(nuevaCantidad); 
					stock+=nuevaCantidad-d;
				}
				else{
					saturado.add(0);
					stock-=d;
				}
			}
			else{
				saturado.add(0);
			}
		}
		if(stock>0){
//			llamar funcion sumarStock(stock)
			System.out.println("stock restante: "+stock);
			s.guardarStock(idArt,stock);
		}
		return saturado;
	}
	
	public int getSemanasAdelanto(int articuloId,int proveedorId){
		Proveedor p=new Proveedor();
		int lote, capacidad, leadTime;
		capacidad=p.getCapacidad(articuloId, proveedorId);
		lote=p.getLote(articuloId, proveedorId);
		leadTime=p.getLeadTime(articuloId, proveedorId);
		double fa=(double)lote/(double)capacidad;
		int factor=Casteo.getRedondeo(fa);
		return factor+leadTime;
	}
	
	public ArrayList<Integer> distribucionAbajo(int cantidad){
		//va a distribuir de mi padre principal cada uno de los buy por proveedor
		//la cantidad me la devuelve el 70-30 por cada una.
		ArrayList<Integer> listaPadre=tablaMrp.get(0);
		int count=0;
		for(Integer i:listaPadre){
			if(i!=0){
				count++;
			}
		}
		//es el count +4 a partir de la semana congelada.
		int cantidadPorSemana=cantidad/count;
		ArrayList<Integer> lista1=new ArrayList<>();
		for(Integer i:listaPadre){
			if(i!=0){
				lista1.add(cantidadPorSemana);
			}
			else{
				lista1.add(0);
			}
		}
		return lista1;
	}
	
	
	public ArrayList<Integer> distribucionYControlStock(int cantidad, int idArt){
		//va a distribuir de mi padre principal cada uno de los buy por proveedor
		//la cantidad me la devuelve el 70-30 por cada una.
		Integer stock=s.getCantidadStock(idArt);
		Integer stockOriginal= stock;
		Integer cant;
		System.out.println("ya habia en stock: "+stock);

		ArrayList<Integer> listaPadre=tablaMrp.get(0);
		int count=0;
		for(Integer i:listaPadre){
			if(i!=0){
				count++;
			}
		}
		//es el count +4 a partir de la semana congelada.
		int cantidadPorSemana=cantidad/count;
		
		ArrayList<Integer> lista1=new ArrayList<>();
		for(Integer i:listaPadre){
			if(i!=0){
				if(stock!=0)
				{
					if(stock>cantidadPorSemana)
					{
						lista1.add(0);
						stock-=cantidadPorSemana;
					}
					else
					{
						lista1.add(cantidadPorSemana-stock);
						stock=0;
					}
				}
				else
					lista1.add(cantidadPorSemana);
			}
			else{
				lista1.add(0);
			}
		}
		
		if(stockOriginal>stock)
			{
			int stockUse=stockOriginal-stock;
			s.restarStock(idArt,stockUse);
			//System.out.println("stock use: "+stockUse);
			}
		
		return lista1;
	}
	
	private ArrayList<ArrayList<Integer>> listaSetentaTreinta(ArrayList<Integer> demandaReal,Integer art_id)
	{
		ArrayList<ArrayList<Integer>> distProv= new ArrayList<>();
		//ArrayList<Integer> treinta= new ArrayList<>();
		ArrayList<ArrayList<Integer>> valoresProveedor=null;
		ArrayList<Integer> p1= new ArrayList<>();
		ArrayList<Integer> p2= new ArrayList<>();
		Boolean tieneP2=false;
		for (Integer dem: demandaReal)
		{
			if(dem!=0)
			{
				valoresProveedor=getSetentaTreinta(dem, art_id);
				p1.add(valoresProveedor.get(0).get(1)); //agrego la cantidad del primer proveedor
				if(valoresProveedor.get(1)!=null)
				{	p2.add(valoresProveedor.get(1).get(1)); //si tiene 2 prov agrego la cantidad del 2 proveedor
					tieneP2=true;
				}
			}
			else
				{p1.add(0);
				p2.add(0);
				}
			
		}
		
		p1.add(valoresProveedor.get(0).get(0)); //agrego el id del proveedor
		distProv.add(p1);
		
		if(tieneP2=true)
		{	p2.add(valoresProveedor.get(1).get(0)); //agrego el id del proveedor
			distProv.add(p2);
		}
		return distProv;
	}
	
	
	public void armarMRP(){
		//los recible de la UI. nodo_id y cantidad
//		hay que cambiar por getNodoById();;!!!!
		Nodo nod=arbol.getNodoByDescripcion("Mesa redonda 3 patas"); 

		setPadres(300, 2);
		ArrayList<Nodo> listaBuy=nod.getListaHijos(nod, 300, new ArrayList<Nodo>());
		Proveedor proveedor = new Proveedor();
		
		for(Nodo nodo:listaBuy){
			int artID = nodo.getArt().getValor();
		//	System.out.println("Articulo: "+artID);
			ArrayList<Integer> demandaReal=distribucionYControlStock(nodo.getCantidad(),nodo.getArt().getValor()); // cantidad del proveedor
			System.out.println("demandaReal: "+demandaReal);
			ArrayList<ArrayList<Integer>> setentaTreinta=listaSetentaTreinta(demandaReal,nodo.getArt().getValor());
			//ArrayList<ArrayList<Integer>> setentaTreinta=getSetentaTreinta(nodo.getCantidad(), nodo.getArt().getValor()); //0:prov, 1:cant, 2:lote, 3:capacidad
			
			//System.out.println("70-30: "+ setentaTreinta);
			for(ArrayList<Integer> prov:setentaTreinta){

				System.out.println("70-30: "+ prov);
				//ArrayList<Integer> auxAbajo=distribucionAbajo(prov.get(1)); // cantidad del proveedor
				int provID = prov.remove((prov.size())-1);
				//System.out.println(provID);
				int semAdelanto=getSemanasAdelanto(nodo.getArt().getValor(), provID);
				//int semAdelanto=2;
				ArrayList<Integer> auxDesplaz=getDesplazado(prov, semAdelanto);
				System.out.println("auxDesplaz: "+auxDesplaz);
				int lote= proveedor.getLote(artID, provID);
				int capacidad= proveedor.getCapacidad(artID, provID);
				ArrayList<Integer> auxSaturado=saturar(lote, auxDesplaz,capacidad,artID);
				auxSaturado.add(artID);
				auxSaturado.add(provID);
				//System.out.println(auxSaturado);
				tablaMrp.add(auxSaturado);
			}
			System.out.println();
			
		}


		MrpPruebaTabla mrpTabla = new MrpPruebaTabla(tablaMrp, arbol, nod.getArt().getValor());

		
	}
	
	public int getNuevoCantidadLotePedir (int lote, int capacidad, int demanda){
		double auxFactor=(double)capacidad/(double)lote;
		int factor=(int) auxFactor;
		int nuevaCantidad=lote;
		int cont=factor-1;
		while(demanda>nuevaCantidad && cont>0){
			nuevaCantidad+=lote;
			cont--;
		}
		return nuevaCantidad;
	}
}
