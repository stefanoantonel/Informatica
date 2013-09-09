package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import modelo.Main;

public class Lectura {
	static String pie = null;
	static String linea; // contiene la linea
	static String cabecera;
	public static void main(String[] args) {

		StringBuilder retornar = new StringBuilder(); // va acumulando todo
		
		
		File archivo = new File("D:\\Escritorio\\leer.txt");
		FileReader fileReader = null; // abre el archivo en disco
		BufferedReader br = null; // buffer de todo el archivo
		int contadorLinea = 0;

		try {
			fileReader = new FileReader(archivo);
			br = new BufferedReader(fileReader);
			
			cabecera = br.readLine();
			leerCabecera(cabecera);

			while ((linea = br.readLine()) != null) {
			
				leerCuerpo(linea);
				pie = linea;
			}

			leerPie(pie);
			fileReader.close();

		} catch (IOException e) {JOptionPane.showMessageDialog(null,"ERROR \nCree un archivo 'leer.txt' en C: ");}
	}

	private static void leerCabecera(String cabecera) {
		
		String tipo,proveedor,fecha_despacho,fecha_arribo,file,puesto,filler;
		
		tipo=cabecera.substring(0, 2);
		proveedor=cabecera.substring(2, 8);
		fecha_despacho=getDate(cabecera.substring(8, 16));
		fecha_arribo=getDate(cabecera.substring(16, 24));
		file=cabecera.substring(24, 34);
		puesto=cabecera.substring(34, 35);
		filler=cabecera.substring(35, 50);
		
//		System.out.println("tipo: "+tipo);
//		System.out.println("prov: "+proveedor);
//		System.out.println("despacho: "+fecha_despacho);
//		System.out.println("arribo: "+fecha_arribo);
//		System.out.println("file: "+file);
//		System.out.println("puesto: "+puesto);
//		System.out.println("filler: "+filler);
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
		
		//lo hago porque no puedo cortar el while penultimo para no guardar el pie
		String file, proveedor,producto,cantidad,lote,filler;
		file=cuerpo.substring(0, 10);
		proveedor=cuerpo.substring(10, 16);
		producto=cuerpo.substring(16, 32);
		cantidad=cuerpo.substring(32, 42);
		lote=cuerpo.substring(42, 46);
		filler=cuerpo.substring(46, 50);
		System.out.println("cuerpo: " + cuerpo);
		

		
		
	}
	
	private static void leerPie(String pie) {
		String file,lineas,filler; //lineas solo el numero de cuerpos
		file=pie.substring(0, 10);
		lineas=pie.substring(10, 13);
		file=pie.substring(13, 50);
		System.out.println("pie: " + pie);
	}
}
