package modelo;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import persistencia.MrpDao;
import txt.Lectura;

public class MRP {
	
	private static Arbol arbol;
	private static ArrayList<ArrayList<Integer>> tablaMrp=new ArrayList<>();
	
	
	
	public static void main(String[] args) {
		MRP m=new MRP();
		
		ArrayList<Integer> provCant=m.getSetentaTreinta(160, 1);
		if(provCant!=null){
			int cont=0;
			for(Integer cant :provCant){
				cont++;
				System.out.print("Prov "+cont+" ");
				System.out.print("Cant: "+cant+" ");
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Proveedores Insuficientes");
		}
		
		m.setPadres(30, 6);
		
		
		//Para organizar Que se hace:
		/*
		 * padre=llamar a setPadres(cantidad,articuloID)
		 * si son productos make llamo a getDemandaReal(cantidadDelProveedor=10 (capNuestra 5x cantidad dell hijo=2 ), padre,int leadTime=1)
		 * 
		 */
		
		/*
		 * demanda real=con la cantidad y el articulo llamo a stock y le resto lo que me devolvio
		 * genera la primera linea 
		 * llama a saturar de esa primera
		 *  por cada hijo
		 *  	
		 */
	}
	
	public ArrayList<Nodo> obtenerPadresPrincipales ()
	{
		arbol = new Arbol();
		return arbol.obtenerPadres();
	}
	
	public ArrayList<Integer> getSetentaTreinta(int cantidadTotal, int articuloID){
		double cantidadP1=0,cantidadP2=0,cantidadFalta,capacidadP1,capacidadP2,setenta,treinta;
		double cantidadP12=0,cantidadP22=0, totalP1,totalP2;
		ArrayList<Integer> provCant=new ArrayList<>();
		setenta=0.7;
		treinta=0.3;
		capacidadP1=160;
		capacidadP2=50;
		
//		el dao me devuelve los proveedores y saco la capacidad del p1 y el p2 respectivos y el lote de cada uno. 
//		Math.ceil(double)
		
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
						provCant.add(getRedondeo(totalP1));
						provCant.add(getRedondeo(totalP2));
						return provCant;
					}
				}
				else{
//					totalP1=cantidadP1+cantidadP12;
					totalP1=cantidadP1+cantidadFalta;
					totalP2=cantidadP2;
					
					provCant.add(getRedondeo(totalP1));
					provCant.add(getRedondeo(totalP2));
					return provCant;
				}
			}
			else{
				totalP1=cantidadP1;
//				totalP2=cantidadP2;
				totalP2=cantidadFalta;
				provCant.add(getRedondeo(totalP1));
				provCant.add(getRedondeo(totalP2));
				return provCant;
			}
		}
		else{
//			totalP1=cantidadP1;
			totalP1=cantidadFalta;
			provCant.add(getRedondeo(cantidadP1));
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
//		ArrayList<Integer> ag=new ArrayList<>();
//		ag.add(2);
//		tablaMrp.add(ag);
//		ag.add(2);
//		tablaMrp.add(ag);
//		ag.add(2);
//		tablaMrp.add(ag);
		
		int largo=tablaMrp.get(0).size();
		ArrayList<Integer> linea=new ArrayList<>();
//		int cont=4;
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
	
	private ArrayList<Integer> getDemandaReal(int cantidad, ArrayList<Integer> padre,int leadTime,int tipo){
		ArrayList<Integer> dreal=new ArrayList<>();
//		el leadtime se lo pido a flor getLead(proveedor, articulo)
		
		int indiceP;
		for(int j=0;j<padre.size();j++){
			dreal.add(0);
		}
		for(int j=0;j<padre.size();j++){
			if(padre.get(j)!=0){
				indiceP=j;
				if(tipo==1){
					//make
					dreal.set(indiceP-leadTime, cantidad*padre.get(j));
				}
				if(tipo==2){
					//buy
					dreal.set(indiceP-leadTime, cantidad);
				}
				
			}
		}
		
		return dreal;
	}
	
	private ArrayList<Integer> saturar(int lote, ArrayList<Integer> demanda){
		float stock=0;
		
		//hay que llamr a stock flor
		ArrayList<Integer> saturado=new ArrayList<>();
		for(Integer d:demanda){
			if(d!=0){
				if(stock<d){
					saturado.add(lote);
					stock+=lote-d;
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
			//llamar funcion sumarStock(stock)
		}
		return saturado;
	}
	
	private void setPadres(int cantidad,int articuloID){
		MrpDao dao=new MrpDao();
		int semanasCongeladas=4;
		ArrayList<Integer> lineaPadre=new ArrayList<>();
		int capacidad=dao.getCapacidad(articuloID);
		if(capacidad!=-1){
			for(int j=semanasCongeladas;j>0;j--){
				lineaPadre.add(0);
			}
//			float c=cantidad;
//			float ca=capacidad;
			double semanasD=(double)cantidad/(double)capacidad;
//			float semanaD=c/ca;
			int semanasI=getRedondeo(semanasD);
			
			for(int j=semanasI;j>0;j--){
				lineaPadre.add(capacidad);
			}
			tablaMrp.add(lineaPadre);
			System.out.println(lineaPadre);
		}
		
	}
	
}
