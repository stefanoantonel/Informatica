package modelo;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.naming.BinaryRefAddr;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import persistencia.CargaStockDAO;
import ui.CodigoDeArticulos;

public class CargaStock {
	
	int cantidad;
	int codigoPlano;
	ArrayList<String> serie=new ArrayList<>();
	ArrayList<Integer> verificador=new ArrayList<>();
	CargaStockDAO cs=new CargaStockDAO();
	
	public CargaStock(int cant, int plano){
		this.cantidad=cant;
		this.codigoPlano=plano;
		obtenerSerie();
		calcularVerificador();
	}
	
	private void obtenerSerie() {
		int base=cs.obtenerUltimoSerie(codigoPlano); //obtengo para que no se me pise en las serie
        for(int i=base+1;i<=base+cantidad;i++){
        	StringBuilder sb=new StringBuilder();
        	String bina=Integer.toBinaryString(i);
        	for(int j=7;j>bina.length();j--){
        		sb.append("0");
        	}
        	sb.append(bina);
        	serie.add(sb.toString());
        }        
    }
	
//	private void obtenerPlano() {
//
//        
//        	StringBuilder sb=new StringBuilder();
//        	String bina=Integer.toBinaryString(i);
//        	for(int j=4;j>this.codigoPlano.size().;j--){
//        		sb.append("0");
//        	}
//        	sb.append(bina);
//        	serie.add(sb.toString());
//        }        
//    }
	
	private void calcularVerificador(){
		
		String pl=String.valueOf(codigoPlano);
		boolean bandera=true;
		for(String s:serie){
			StringBuilder sb=new StringBuilder();
			sb.append(codigoPlano);
			sb.append(s);
			String[] unido=sb.toString().split(""); //es todo ej 1040 0001001001
			int acumulador=11;
			while(acumulador>=10){
				acumulador=0;
				
				for(String cadaUno:unido){
					if(!cadaUno.equals("")&&bandera==true){
						acumulador+=Integer.parseInt(cadaUno);
						bandera=false;
						continue;
					}
					if(!cadaUno.equals("")&&bandera==false){
						acumulador+=Integer.parseInt(cadaUno);
						bandera=true;
					}
					
				}
				unido=String.valueOf(acumulador).split(""); //del resultado lo vuelvo a split
			}
//			System.out.println("acumulador: "+acumulador);
			verificador.add(acumulador);
			
		}
		
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
		
		
		cs.insertarStock(codigoPlano, serie, verificador);
		
		cargarModeloTabla();
	}
	
	private void cargarModeloTabla(){
		DefaultListModel<String> modelo1=new DefaultListModel<>();
		int i=0;
		for(String s:serie){
			StringBuilder sb1=new StringBuilder();
			sb1.append(codigoPlano);
			sb1.append(s);
			sb1.append(String.valueOf(verificador.get(i)));
			i++;
			modelo1.addElement(sb1.toString());
		}
		CodigoDeArticulos ca=new CodigoDeArticulos(modelo1);
	}
}
