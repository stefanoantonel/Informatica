package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import txt.Lectura;

public class MRP {
	
	private static Arbol arbol;
	
	public static void main(String[] args) {
		MRP m=new MRP();
		
		ArrayList<Integer> provCant=m.getSemanas2(41, 0);
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
		
		
		
		
	}
	
	public ArrayList<Nodo> obtenerPadresPrincipales ()
	{
		arbol = new Arbol();
		return arbol.obtenerPadres();
	}
	
	public ArrayList<Integer> getSemanas2(int cantidadTotal, int articuloID){
		double cantidadP1=0,cantidadP2=0,cantidadFalta,capacidadP1,capacidadP2,setenta,treinta;
		double cantidadP12=0,cantidadP22=0, totalP1,totalP2;
		ArrayList<Integer> provCant=new ArrayList<>();
		setenta=0.7;
		treinta=0.3;
		capacidadP1=30;
		capacidadP2=10;
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
					totalP1=cantidadP1+cantidadP12;
					totalP2=cantidadP2+cantidadP22;
					if(cantidadP22<cantidadFalta){ 
						System.out.println("ERROR: No poveedores insuficientes");
						return null;
					}
					else{ //me alcanzo con los proveedores
						System.out.println("Proveedor 1: "+cantidadP1+" "+cantidadP12+" = "+totalP1);
						System.out.println("Proveedor 2: "+cantidadP2+" "+cantidadP22+" = "+totalP2);
						provCant.add((int) totalP1);
						provCant.add((int) totalP2);
						return provCant;
					}
				}
				else{
					System.out.println("Proveedor 1: "+cantidadP1+" "+cantidadP12);
					System.out.println("Proveedor 2: "+cantidadP2);
					provCant.add((int) (cantidadP1+cantidadP12));
					provCant.add((int) cantidadP12);
					return provCant;
				}
			}
			else{
				System.out.println("Proveedor 1: "+cantidadP1);
				System.out.println("Proveedor 2: "+cantidadP2);
				provCant.add((int) cantidadP1);
				provCant.add((int) cantidadP2);
				return provCant;
			}
		}
		else{
			System.out.println("Proveedor 1: "+cantidadP1);
			provCant.add((int) cantidadP1);
			return provCant;
		}
		
	}
	
	
}
