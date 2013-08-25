package modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import persistencia.RemitoDAO;

public class Remito {
	

	RemitoDAO dao;
	String codigoCompleto;
//	int plano;
//	int serie;
//	int verificador;
	String plano,serie,verificador;
	

	public Remito (RemitoDAO remDao){
		this.dao=remDao;
		
	}
	private void dividir(){
		try{
//			plano=Integer.parseInt(codigoCompleto.substring(0, 3));
//			serie=Integer.parseInt(codigoCompleto.substring(4, 10));
//			verificador=Integer.parseInt(codigoCompleto.substring(11, 12));
			plano=(codigoCompleto.substring(0, 4));
			serie=(codigoCompleto.substring(4, 11));
			verificador=(codigoCompleto.substring(11, 12));
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "error de ingreso de codigo");
			return;
		}
		
	}
	
	public boolean verificarExistencia(String numero){
		ArrayList<String> lista=new ArrayList<>();
		lista=dao.getListaCodigosExistentes();
		return lista.contains(numero);
		 
	}
	public void despachado(ArrayList<String> listaParaDespachar){
		ArrayList<String> ser=new ArrayList<>();
		ArrayList<String> ver=new ArrayList<>();
		ArrayList<String> pla=new ArrayList<>();
		
		for(String s:listaParaDespachar){
			codigoCompleto=s;
			dividir();
			ser.add(String.valueOf(serie));
			ver.add(String.valueOf(verificador));
			pla.add(String.valueOf(plano));
			
		}
		dao.ponerDespachado(pla, ser, ver);
	}
	public void espera(ArrayList<String> listaParaEspera){
		ArrayList<String> ser=new ArrayList<>();
		ArrayList<String> ver=new ArrayList<>();
		ArrayList<String> pla=new ArrayList<>();
		
		for(String s:listaParaEspera){
			codigoCompleto=s;
			dividir();
			ser.add(String.valueOf(serie));
			ver.add(String.valueOf(verificador));
			pla.add(String.valueOf(plano));
			
		}
		dao.ponerEspera(pla, ser, ver);
	}
//	public void 
}
