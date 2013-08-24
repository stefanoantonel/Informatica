package modelo;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.naming.BinaryRefAddr;

import persistencia.CargaStockDAO;

public class CargaStock {
	
	int cantidad;
	int codigoPlano;
	ArrayList<String> serie=new ArrayList<>();
	ArrayList<Integer> verificador=new ArrayList<>();
	
	public CargaStock(int cant, int plano){
		this.cantidad=cant;
		this.codigoPlano=plano;
		obtenerSerie();
		calcularVerificador();
	}
	
//	public void generaCodigos(int cantidad, int codigoPlano){
//		String codigoSerie;
//		int codigoVerificador;
//		int acumulador;
//		
//		
//	}
	
	
	
	private void obtenerSerie() {

        for(int i=1;i<=cantidad;i++){
        	StringBuilder sb=new StringBuilder();
        	String bina=Integer.toBinaryString(i);
        	for(int j=7;j>bina.length();j--){
        		sb.append("0");
        	}
        	sb.append(bina);
        	serie.add(sb.toString());
        }        
    }
	
	private void calcularVerificador(){
		
		String pl=String.valueOf(codigoPlano);
		
		for(String s:serie){
			StringBuilder sb=new StringBuilder();
			sb.append(codigoPlano);
			sb.append(s);
			String[] unido=sb.toString().split(""); //es todo ej 1040 0001001001
			int acumulador=11;
			while(acumulador>10){
				acumulador=0;
				
				for(String cadaUno:unido){
					if(!cadaUno.equals("")){
						acumulador+=Integer.parseInt(cadaUno);
					}
					
				}
				unido=String.valueOf(acumulador).split(""); //del resultado lo vuelvo a split
			}
//			System.out.println("acumulador: "+acumulador);
			verificador.add(acumulador);
			
		}
		
//		String[] b=a.split("");
//		for(String s:b){
//			System.out.println(s);
//		}
		StringBuilder sb1=new StringBuilder();
		int i=0;
		for(String s:serie){
			
			sb1.append(codigoPlano);
			sb1.append(" ");
			sb1.append(s );
			sb1.append(" ");
			sb1.append(String.valueOf(verificador.get(i)));
			sb1.append(" ");
			i++;
//			System.out.println();
			sb1.append("\n");
		}
		System.out.println(sb1.toString());
		
		//-----------------------mando para el instertar
		
		CargaStockDAO cs=new CargaStockDAO();
		cs.insertarStock(codigoPlano, serie, verificador);
	}
}
