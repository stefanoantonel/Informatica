package utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateClass {

	
	public DateClass(){
	}
	
	private Calendar obtenerFecha(String strFecha)
	{
		if(strFecha==null){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			System.out.println();
			strFecha=dateFormat.format(cal.getTime());
		}
		
		String [] a=strFecha.split("-");
		int [] b=new int[3];
		int j=0;
		for (String string : a) {
			b[j]=Integer.parseInt(string);
			j++;
		}
		Calendar calendar= new GregorianCalendar();
		calendar.set(b[0], b[1], b[2]); //seteo año mes dia
		return calendar;
	}

	public int compararFecha(String fecha1, String fecha2){
		Calendar c1=obtenerFecha(fecha1);
		Calendar c2=obtenerFecha(fecha2);
		return c1.compareTo(c2);
	}
	
}
