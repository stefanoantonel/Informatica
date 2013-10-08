package modelo;

import java.util.List;

import txt.Lectura;

public class MRP {
	
	public static void main(String[] args) {
		MRP m=new MRP();
		m.getSemanas2(40, 0);
		
	}
	
//	public List<Integer> getSemanas(int cantidadTotal, int articuloID){
//		int cantidadP1,cantidadP2,cantidadTengoProveedores,cantidadFalta,capacidadP1,capacidadP2;
//		cantidadP1=capacidadP1*((int)0.7); //saco el 70 % del p1
//		if(cantidadP1<cantidadTotal){ //si no me alcanza con p1 
//			cantidadP2=capacidadP2*((int)0.3); //saco el 30% del p2
//			cantidadTengoProveedores=cantidadP1+cantidadP2; //me brindan los proveedores
//			if(cantidadTengoProveedores<cantidadTotal){ //no me alcanza con 70-30
//				cantidadFalta=cantidadTotal-cantidadTengoProveedores;
//				cantidadP1=cantidadP1+capacidadP1*((int)0.3); //el 70 mas el 30 de p1
//				if(cantidadFalta<cantidadP1){ //me basta con el 30 del p1 y LISTO
//					//cp1=cant-(cp1+cp2); //flor?
//					//ACA YA TENGO CUANTO TENGO QUE PEDIR DE P1 y P2
//				}
//				else{
//					//cap1+=capP1*0.3;
//					cantidadP2=cantidadP2+capacidadP2*((int)0.7);
//					if((cantidad-(cp1+cp2))<capP2*0.7)
//						cp2=cantidad-(cp1+cp2);
//					else{
//						("Error")
//					}
//				}
//		}
//		
//
//		}
//			
//		return null;
//		
//	}
	
	public void getSemanas2(int cantidadTotal, int articuloID){
		double cantidadP1=0,cantidadP2=0,cantidadFalta,capacidadP1,capacidadP2,setenta,treinta;
		double cantidadP12=0,cantidadP22=0, totalP1,totalP2;
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
					}
					else{
						System.out.println("Proveedor 1: "+cantidadP1+" "+cantidadP12+" = "+totalP1);
						System.out.println("Proveedor 2: "+cantidadP2+" "+cantidadP22+" = "+totalP2);
						
					}
				}
				else{
					System.out.println("Proveedor 1: "+cantidadP1+cantidadP12);
					System.out.println("Proveedor 2: "+cantidadP2);
				}
			}
			else{
				System.out.println("Proveedor 1: "+cantidadP1);
				System.out.println("Proveedor 2: "+cantidadP2);
			}
		}
		else{
			System.out.println("Proveedor 1: "+cantidadP1);
		}
	}

}
