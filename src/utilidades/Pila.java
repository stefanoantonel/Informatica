package utilidades;

import java.util.ArrayList;
import java.util.Collections;

public class Pila {

	ArrayList<Integer> lista=new ArrayList<>();
	
	public static void main(String[] args) {
		Pila p=new Pila();
		p.getInvertido();
		
	}
	
	public ArrayList<Integer> getInvertido(){
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(1);
		Collections.reverse(lista);
		System.out.println(lista);
//		Object [] a=lista.toArray();
//		for(int j=a.length-1;j>=0;j--){
//			System.out.println(a[j]);
//		}
		return null;
	}
}
