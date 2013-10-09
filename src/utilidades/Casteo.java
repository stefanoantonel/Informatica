package utilidades;

public class Casteo {
	public static int getRedondeo(double a){
		int b=0;
		double c=0;
		c=Math.ceil(a);
		b=(int)c;
		return b;
	}
}
