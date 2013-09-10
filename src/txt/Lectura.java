package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import modelo.Main;

public class Lectura {
	
	static String pie = null;
	static String linea=""; // contiene la linea
	static String cabecera;
	static ArrayList<String> txtCompleto=new ArrayList<>();
	static String tipoCabecera,proveedorCabecera,fecha_despachoCabecera,fecha_arriboCabecera,fileCabecera,puestoCabecera,fillerCabecera; //para la cabecera
	static String fileCuerpo, proveedorCuerpo,productoCuerpo,cantidadCuerpo,loteCuerpo,fillerCuerpo;
	static String filePie,lineasPie,fillerPie; //lineas solo el numero de cuerpos
	public static void main(String[] args) {
		
		loadFile();
		if(comprobarLineas()==true){
			procesar();
			if(comprobaciones()==true){
				System.out.println("todo OK");
			}
			else 
				System.out.println("mal las comprobaciones");
		}
		else
			JOptionPane.showMessageDialog(null, "Error Procesar Archivo");
	}
	private static void loadFile(){
		File archivo = new File("D:\\Escritorio\\leer.txt");
		FileReader fileReader = null; // abre el archivo en disco
		BufferedReader br = null; // buffer de todo el archivo
		try {
			fileReader = new FileReader(archivo);
			br = new BufferedReader(fileReader);
			while (linea != null) {
				if(!linea.equals(""))
					txtCompleto.add(linea);
				linea = br.readLine();
			}
			fileReader.close();
		} catch (IOException e) {JOptionPane.showMessageDialog(null,"ERROR \nCree un archivo 'leer.txt' en C: ");}
	}
	private static boolean procesar(){
		leerCabecera(txtCompleto.get(0));
		for(int j=1;j<txtCompleto.size()-1;j++){
			leerCuerpo(txtCompleto.get(j));
		}
		leerPie(txtCompleto.get(txtCompleto.size()-1)); //obteno el ultimo
		return true;
	}
	private static void leerCabecera(String cabecera) {
		
		
		tipoCabecera=cabecera.substring(0, 2);
		proveedorCabecera=cabecera.substring(2, 8);
		fecha_despachoCabecera=getDate(cabecera.substring(8, 16));
		fecha_arriboCabecera=getDate(cabecera.substring(16, 24));
		fileCabecera=cabecera.substring(24, 34);
		puestoCabecera=cabecera.substring(34, 35);
		fillerCabecera=cabecera.substring(35, 50);
		System.out.println("cabecera: " + cabecera);
	}
	private static String getDate(String d){

		String year,month,day;
		year=d.substring(0, 4);
		month=d.substring(4, 6);
		day=d.substring(6, 8);
		StringBuilder sb=new StringBuilder();
		sb.append(year);
		sb.append("-");
		sb.append(month);
		sb.append("-");
		sb.append(day);
		return sb.toString();
	}
	private static void leerCuerpo(String cuerpo) {
		
		
		fileCuerpo=cuerpo.substring(0, 10);
		proveedorCuerpo=cuerpo.substring(10, 16);
		productoCuerpo=cuerpo.substring(16, 32);
		cantidadCuerpo=cuerpo.substring(32, 42);
		loteCuerpo=cuerpo.substring(42, 46);
		fillerCuerpo=cuerpo.substring(46, 50);
		System.out.println("cuerpo: " + cuerpo);
		
	}
	private static void leerPie(String pie) {
		
		filePie=pie.substring(0, 10);
		lineasPie=pie.substring(10, 13);
		fillerPie=pie.substring(13, 50);
		System.out.println("pie: " + pie);
	}
	private static boolean comprobarLineas(){
		int cantidadLineas=txtCompleto.size()-2;
		String lPie=txtCompleto.get(txtCompleto.size()-1).substring(10, 13); //la cantidad de filas que me dice el pie
		int lineasPie=Integer.parseInt(lPie);
		if(cantidadLineas!=lineasPie){
			System.out.println("cantidad de lineas errones");
			return false;
		}
		else{ System.out.println("cantidad lineas comprobacion OK");}
		return true;
	}
	private static boolean comprobaciones(){
		//compruebo numero de archivos
		if(!fileCabecera.equals(filePie))
			return false;
		
		for(int j=1;j<txtCompleto.size()-1;j++){
			String fileCuerpoString =txtCompleto.get(j).substring(0, 10);
			if(!fileCuerpoString.equals(fileCabecera)){
				System.out.println("error file cuerpo con cabeza");
				return false;
			}
		}
		
		//compruebo puesto
		if(!puestoCabecera.toLowerCase().equals("o") && !puestoCabecera.toLowerCase().equals("c") && !puestoCabecera.toLowerCase().equals("p") && !puestoCabecera.toLowerCase().equals("u"))
		return false;
		
		
		return true;
	}
}
	