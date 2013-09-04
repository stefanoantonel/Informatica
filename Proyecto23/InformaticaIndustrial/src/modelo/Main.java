package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




//import sql.Conexion;


public class Main {

	/**
	 * @param args
	 */
	
//	static ArrayList<String> um=new ArrayList<>(), material,tipo;
	public static void main(String[] args) {
		
//		
		System.out.println();
		String strFecha="2013-7-6";
		String fecha=strFecha;
		String [] a=strFecha.split("-");
		int [] b=new int[3];
		int j=0;
		for (String string : a) {
			b[j]=Integer.parseInt(string);
			j++;
		}
		
//		Calendar calendar= new GregorianCalendar(intYear, intMonth - 1, intDay);
		Calendar calendar= new GregorianCalendar();
		calendar.set(b[0], b[1], b[2]);
//		Date d=new Date();
//		d.setTime(calendar.getTime().getTime());
//		System.out.println("d "+d);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.MONDAY));
		System.out.println(calendar.get(Calendar.YEAR));
//		return calendar;

	}
	

}
