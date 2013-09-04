package utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateClass {

	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 String hoy= df.format(new java.util.Date()); 
	public DateClass(){
	}
	
	
	public Date obtenerFecha(String strFecha)
	{
		//String strFecha = "2007-12-25";
		//System.out.println("strFecha: "+strFecha+" vs hoy: "+hoy);
		Date fecha = null;
		try {
			if (strFecha==null)
				strFecha=hoy;
		fecha = formatoDelTexto.parse(strFecha);

		} catch (ParseException ex) {

		ex.printStackTrace();

		}
		//System.out.println("FECHA OBTENERFECHA: "+ fecha);
		return fecha;
	}
//	
//	public Date toDate(Date dateFormat) {
//
//		System.out.println(dateFormat);        
//
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(dateFormat);
//		String formatedDate = cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH) + 1)+"-"+ cal.get(Calendar.DATE);  
//		System.out.println("formatedDate : " + formatedDate);
//		
//        return obtenerFecha(formatedDate);
//        
//    }
//	
	
	
}
