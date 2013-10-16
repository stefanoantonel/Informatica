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
		//Pongo una lista con el tamaño del padre
		for(int j=0;j<padre.size();j++){
			dreal.add(0);
		}
		for(int j=0;j<padre.size();j++){
			if(padre.get(j)!=0){
				indiceP=j;
				
				dreal.set(indiceP-semanasAdenato, padre.get(j));
			}	
		}
		return dreal;
	}
		
	
	
	private ArrayList<Integer> saturar(int lote, ArrayList<Integer> demanda,int capacidad, Integer st){
//		 es con algo parecedio al factor porque si le paso capacidad puede que no me venda lote =capacidad.
		Integer stock=st;
		
//		hay que llamr a stock flor
// 		si el stock es <0 me voy a retrasar seguro. 
 
		ArrayList<Integer> saturado=new ArrayList<>();
		for(Integer d:demanda){
			if(d!=0){
				if(stock<d){
//					nuevoLote=llamar a funcion getcantidadLotes a perdir--> devuelve cant * lote 	
					int nuevaCantidad=getNuevoCantidadLotePedir(lote, capacidad, d);
					System.out.println("Nueva cantida:"+ nuevaCantidad);
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
		}
		return saturado;
	}
	
	
	/*
	public void funcionMain(){
		int demandaReal=30; //desde la interfaz
		
		Nodo padre=arbol.getNodoByDescripcion("Silla"); //Cargo con interfaz
		ArrayList<Integer> listaPadre=setPadres(demandaReal, padre.getArt().getValor());
		for(Nodo n:padre.GetHijos()){
			recursiva(listaPadre, n, demandaReal*n.getCantidad());
		}
		
	}
	
	private void recursiva(ArrayList<Integer> listaP,Nodo h,int demandaReal){
		if(h.getArt().getTipo==make){
//			MrpDao dao=new MrpDao();
//			ArrayList<Integer> lista1=getDemandaReal(h.getCantidad(), listaP, 1);
//			ArrayList<Integer> lista2=saturar(m.getcapacidad(h.getArt().getValor()), lista1 );
			for(Nodo n:h.GetHijos()){
				recursiva(listaP, n, demandaReal*n.getCantidad());
			}
		}
		else{
			ArrayList<ArrayList<Integer>> proveedores=getSetentaTreinta(demandaReal, h.getArt().getValor());
			//Devuelve id,cantidad a pedir y lote
			
			for(ArrayList<Integer> p:proveedores){
				int semanasAdelanto=getSemanasAdelanto(h.getArt().getValor(), p.get(0));
				double a=h.getCantidad();
				int cantidadHijo=Casteo.getRedondeo(a);
				ArrayList<Integer> lista1=getDemandaReal(cantidadHijo,listaP,semanasAdelanto);
				//int cantidadHijo, ArrayList<Integer> padre, int semanasAdenato){
				ArrayList<Integer> lista2=saturar(p.get(1),lista1);
//				recursiva(lista2, n, demandaReal*n.getCantidad());
			}
		}
	}
	
	*/
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
	
	
	public void armarMRP(){
		//los recible de la UI. nodo_id y cantidad
//		hay que cambiar por getNodoById();;!!!!
		Nodo nod=arbol.getNodoByDescripcion("Mesa redonda 3 patas"); 
		int demanda=400;
		int articuloId=2;
		
		setPadres(demanda, articuloId);
		ArrayList<Nodo> listaBuy=nod.getListaHijos(nod, demanda, new ArrayList<Nodo>());
		for(Nodo nodo:listaBuy){
			ArrayList<ArrayList<Integer>> setentaTreinta=getSetentaTreinta(nodo.getCantidad(), nodo.getArt().getValor());
			for(ArrayList<Integer> prov:setentaTreinta){
				//prov tiene id, cantidadPedir, lote, capacidad
				ArrayList<Integer> auxAbajo=distribucionAbajo(prov.get(1)); // cantidad del proveedor
				int semAdelanto=getSemanasAdelanto(nodo.getArt().getValor(), prov.get(0)); //proveedor_id
				ArrayList<Integer> auxDesplaz=getDesplazado(auxAbajo, semAdelanto);
				Integer stock = s.getCantidadStock(nod.getArt().getValor());
				ArrayList<Integer> auxSaturado=saturar(prov.get(2), auxDesplaz,prov.get(3),stock);
				//prov get(2) es lote, prov get(3) es la capacidad
				auxSaturado.add(nodo.getArt().getValor()); //El id del articulo
				auxSaturado.add(prov.get(0)); //El id del proveedor
				getTablaMrp().add(auxSaturado);
				System.out.println(auxSaturado);
				
			}
			
		}
		for(ArrayList<Integer> filas: getTablaMrp()){
			for(Integer columna:filas){
				System.out.print(columna);
				System.out.print("\t");
			}
			System.out.println();
			
		}
		MrpPruebaTabla prueba=new MrpPruebaTabla(tablaMrp,arbol,articuloId);
		
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
