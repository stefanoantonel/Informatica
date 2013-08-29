package modelo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
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
	int idRemito, estado;
	
	

	public Remito (ArrayList<Integer> plano, ArrayList<Integer> cant ){
		dao=new RemitoDAO();
		planoRequerido=plano;
		estado=10;
		cantRequerido=cant;

	}
	public Remito (ArrayList<Integer> plano, ArrayList<Integer> cant ,int id,RemitoDAO d){
		idRemito=id;
		dao=d;
		planoRequerido=plano;
		cantRequerido=cant;
		cargarUI();
		estado=11;

	}
	
	public int getEstado() {
		return estado;
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
	public void cargarUI(){
		RemitoUI rui=new RemitoUI(this);
		rui.agregarCargados();
		rui.setVisible(true);
	}
	
//	public void inicializarUI( ArrayList<String> a){
//		int j=0;
//		DefaultListModel<String> mod=new DefaultListModel<>();
//		for(String s:a){
//			mod.addElement(s);
//		}
//		
//	}
	
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
	
	public int getIdRemito() {
		return idRemito;
	}
	public void guardarRemito(ArrayList<String> listaPara){
		dao.guardarRemito(listaPara);
	}
	public void guardarRemitoEspera(){
		dao.guardarRemitoEspera();
	}
	public void guardarRemitoDespachado(){
		dao.guardarRemitoDespachado();
	}
	public void guardarRemitoActivo(){
		
		dao.guardarRemitoActivo();
	}
	
	public ArrayList<String> getArticulosCargados(){
		ArrayList<String> t=dao.getArticulosCargados(idRemito);
		return t;
	}
	public boolean verificarConcordancia(String codigoCompleto){
		String p=codigoCompleto.substring(0,4);
		String s=codigoCompleto.substring(4,11);
		String v=codigoCompleto.substring(11,12);
		int p1=Integer.parseInt(p);
		if(planoRequerido.contains(p1)){
			System.out.println("si contiene");
		}
		else{
			JOptionPane.showMessageDialog(null, "Ingrese articulo correcto para este remito");
			return false;
		}
		return true;
	}
	
	public String rellenar(String cod){
		StringBuilder sb=new StringBuilder();
		if(cod.length()<12){
			
			for (int i=12;i>cod.length();i--){
				sb.append("0");
			}
			sb.append(cod);
		}
		else{
			sb.append(cod);
		}
		return sb.toString();
	}
	public void liberarArticulo(String art){
		dao.liberaArticulo(art);
	}
	
	public void updateRemito(ArrayList<String> listaParaDespachar,int remitoId){
		dao.guardarRemito(listaParaDespachar, remitoId);
	}
}
