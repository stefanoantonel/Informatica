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

import persistencia.ArticuloDAO;
import persistencia.StockSerializadoDAO;

import modelo.Main;
import modelo.StockSerializado;

public class Lectura {
	
	static String pie = null;
	static String linea=""; // contiene la linea
	static String cabecera;
	static ArrayList<String> txtCompleto=new ArrayList<>();
	static String tipoCabecera,proveedorCabecera,fecha_despachoCabecera,fecha_arriboCabecera,fileCabecera,puestoCabecera,fillerCabecera; //para la cabecera
	static ArrayList<String> fileCuerpo, proveedorCuerpo,productoCuerpo,cantidadCuerpo,loteCuerpo,fillerCuerpo;
	static String filePie,lineasPie,fillerPie; //lineas solo el numero de cuerpos
	static LecturaDAO dao;
	
	
	public static void main(String[] args) {
		Lectura l=new Lectura();
		
	}
	public Lectura(){
		dao=new LecturaDAO();
		boolean corrupto=false;
		loadFile();
		if(comprobarLineas()==true){
			if(comprobacionArchivoLeido()==false){ //NO SE LEYO
				procesar();
				if(comprobaciones()==true){
					System.out.println("todo OK");
					insertarMovimiento();
					corrupto=false;
					int archivo=Integer.parseInt(txtCompleto.get(0).substring(24, 34)); //numero del archivo
					dao.insertarArchivoLeido(archivo, corrupto,proveedorCabecera);
				}
				else{
					System.out.println("mal las comptobaciones finales");
					corrupto=true;
					int archivo=Integer.parseInt(txtCompleto.get(0).substring(24, 34)); //numero del archivo
					dao.insertarArchivoLeido(archivo, corrupto,proveedorCabecera);
				}
			}
			
			else{
				System.out.println("archivo ya fue leido ok");
				
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Error Procesar Archivo");
			corrupto=true;
			int archivo=Integer.parseInt(txtCompleto.get(0).substring(24, 34)); //numero del archivo
			dao.insertarArchivoLeido(archivo, corrupto,proveedorCabecera);
		}
			
		
		
	}
	private static void loadFile(){
		File archivo = new File("D:\\Escritorio\\leer2.txt");
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
		} catch (IOException e) {JOptionPane.showMessageDialog(null,"ERROR \nCree un archivo 'leer.txt' en Escritorio: ");}
	}
	private static boolean procesar(){
		
		fileCuerpo=new ArrayList<>();
		proveedorCuerpo=new ArrayList<>();
		productoCuerpo=new ArrayList<>();
		cantidadCuerpo=new ArrayList<>();
		loteCuerpo=new ArrayList<>();
		fillerCuerpo =new ArrayList<>();
		
		
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
		
	
		
		fileCuerpo.add(cuerpo.substring(0, 10));
		proveedorCuerpo.add(cuerpo.substring(10, 16));
		productoCuerpo.add(cuerpo.substring(16, 32));
		cantidadCuerpo.add(cuerpo.substring(32, 42));
		loteCuerpo.add(cuerpo.substring(42, 46));
		fillerCuerpo.add(cuerpo.substring(46, 50));
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
		
		if (!tipoCabecera.equals("CP"))
			return false;
	
		
		return true;
	}
	
	public boolean comprobacionArchivoLeido(){
		String archivo=txtCompleto.get(0).substring(24, 34); 
		int numeroArchivo=Integer.parseInt(archivo);
//		return false;
		return dao.fueLeido(numeroArchivo);
//		comprobar que no este.
		
	}
	public boolean insertarMovimiento(){
		ArrayList<Integer> art=new ArrayList<>(); 
		ArrayList<Integer> cant=new ArrayList<>();
		ArrayList<Integer> lote=new ArrayList<>();
		ArrayList<Integer> prov=new ArrayList<>();
		ArrayList<Integer> codigosPlanos=new ArrayList<>();
		art=convertirInt(productoCuerpo); //array articulos en INteger
		cant=convertirInt(cantidadCuerpo); //array cantidades en INteger
		lote=convertirInt(loteCuerpo);
		prov=convertirInt(proveedorCuerpo);
		int almacenDestino=dao.getAlmacenDestino(puestoCabecera);
		int ubicacionDestino= dao.getUbicacionDestino(almacenDestino);
		int id=dao.insertarMovimiento(ubicacionDestino,almacenDestino, art, cant,fecha_despachoCabecera,fecha_arriboCabecera);
		
		dao.insertarOrdenCompra(prov,art,cant,lote,fecha_despachoCabecera,fecha_arriboCabecera);
		dao.insertarStock(art,cant ,ubicacionDestino);
		codigosPlanos=getCodigosPlanos(art);
		
		for (int j=0;j<art.size();j++){
			StockSerializado ss=new StockSerializado(cant.get(j), codigosPlanos.get(j));
		}
		StockSerializadoDAO ssdao=new StockSerializadoDAO();
		for (int j=0;j<art.size();j++){
			ssdao.upStockId(cant.get(j), ubicacionDestino, art.get(j));
		}
		
		
		
		return true;
	}
	public ArrayList<Integer> convertirInt(ArrayList<String> str){
		
		ArrayList<Integer> e=new ArrayList<>();
		for(String s:str){
			e.add(Integer.parseInt(s));
		}
		return e;
	}
	public ArrayList<Integer> getCodigosPlanos(ArrayList<Integer> art){
		
		ArrayList<Integer> e=new ArrayList<>();
		ArticuloDAO adao=new ArticuloDAO();
		for(int articulo:art){
			e.add(adao.obetenerCP(articulo));
		}
		return e;
	}
	
}
	