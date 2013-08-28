package modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.RemitoDAO;
import ui.RemitoUI;
import ui.TablaCargaRemitoUI;

public class Remito {
	

	RemitoDAO dao;
	String codigoCompleto;
//	int plano;
//	int serie;
//	int verificador;
	String plano,serie,verificador;
	int cantidadEleccion, planoEleccion;
	ArrayList<Integer> planoRequerido, cantRequerido;
	
	

	public Remito (ArrayList<Integer> plano, ArrayList<Integer> cant ){
		dao=new RemitoDAO();
		planoRequerido=plano;
		cantRequerido=cant;

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
	
	
	public boolean comprobarPyC(ArrayList<Integer> p, ArrayList<Integer> c){
		ArrayList<Integer> planoDao=dao.getPlano();
		ArrayList<Integer> cantDao=dao.getCantidad();
		int j=0;
		for(Integer i:p){
			int indiceDao=planoDao.indexOf(p.get(j));
			int cantidadendao=cantDao.get(indiceDao);
			int cantidadlocal=c.get(j);
			if(cantidadendao!=cantidadlocal){
				JOptionPane.showMessageDialog(null, "La cantida que ingreso del articulo: "+p.get(j)+ " es mayor a la de stock");
				return false;
			}
		}
		return true;
	}
		
		
//		for(int j=0;j<c;j++){
//			plano=JOptionPane.showInputDialog("Ingrese codigo articulo ");
//			cant=JOptionPane.showInputDialog("Ingrese cantidad ");
//			
//			c=-1;
//			try {
//				c=Integer.parseInt(cant);
//				p=Integer.parseInt(plano);
//				
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "ingrese datos correctos");
//				return;
//			}
//		}
	public void inicializarUI(){
		RemitoUI rui=new RemitoUI(this);
		rui.setVisible(true);
	}
	
	public boolean verificarFinalizacionPedido(ArrayList<String> listaDespachar){
		int j=0;
		int cont=0;
		for(Integer i:planoRequerido){
			for(String li:listaDespachar){
				if(i.toString().equals(li.substring(0, 4))){
					cont++;
				}
			}
			if(cont!=cantRequerido.get(j)){
				JOptionPane.showMessageDialog(null, "la cantidad que tiene quq tener el pedio \n no coincide con los articlos ingresados");
				return false;
			}
			j++;
			cont=0;
		}
		return true;
	}
	
	public void guardarRemito(ArrayList<String> listaPara){
		dao.guardarRemito(listaPara);
	}
}
