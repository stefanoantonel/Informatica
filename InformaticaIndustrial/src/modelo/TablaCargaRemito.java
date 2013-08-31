package modelo;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import persistencia.CargaStockDAO;
import persistencia.RemitoDAO;

import ui.RemitoUI;
import ui.TablaCargaRemitoUI;

public class TablaCargaRemito {
	int filas;
	ArrayList<Integer> planoInt,cantInt;
	RemitoDAO dao=new RemitoDAO(); //es solo para comprobar que lo que ingrese ne la tabla este en el articulo
	ArrayList<ArrayList<String>> articuloCodigo = new ArrayList<>();
	CargaStockDAO stockDao = new CargaStockDAO();
//	ArrayList<String> cantFilas=new ArrayList<>();
	
//	public int preguntarCarga(){
//		String cant=JOptionPane.showInputDialog("Cuantos articulos distintos desea ingresar?");
//		filas=-1;
//		try {
//			filas=Integer.parseInt(cant);
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "ingrese datos correctos");
//			return -1;
//		}
//		return filas;
//		
//	}
	
	public void recorrerTabla(TableModel t){
		
		ArrayList<Integer> plano=new ArrayList<>();
		ArrayList<Integer> cant=new ArrayList<>();
//		TableModel t=tablaCarga.getModel();
		for (int i=1;i<filas+1;i++){
			System.out.println(t.getValueAt(i, 0)+" "+t.getValueAt(i, 1));
			try {
				String c=t.getValueAt(i, 1).toString();
				cant.add(Integer.parseInt(c));
				String p=t.getValueAt(i, 0).toString();
				int pl=Integer.valueOf(p);
				plano.add(pl);
				if(dao.getPlano().contains(pl)){	//si esta en lo que elegi para el remito
					
				}
				else{
					JOptionPane.showMessageDialog(null, "articulo: "+plano+" no disponible en stock");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, " datos incorrectos en la tabla");
				return;
			}
		}
//		carga.recorrerTabla(plano, cant);
		Remito r=new Remito(plano,cant);
		r.inicializarUI();
		//esto se fija si lo que tiene la tabla lo tiene en el stock pero no lo uso por ahora
//		Remito r=new Remito();
//		if(r.comprobarPyC(plano, cant)==true){
//			System.out.println("todo perfecto.. comienze a ingresar pedidos");
//			
//		}
	}
	
	
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public ArrayList<Integer> getPlanoInt() {
		return planoInt;
	}

	public ArrayList<Integer> getCantInt() {
		return cantInt;
	}
	public ArrayList<String> cargaArticulosLista(){
		
		ArrayList<String> articuloDescripcion = new ArrayList<>();
		
		articuloCodigo=stockDao.obtenerArticulosStock();
		for (ArrayList<String> art: articuloCodigo)
		{
			articuloDescripcion.add(art.get(1));
		}
		
		return articuloDescripcion;
	}
	public ArrayList<String> obtenerCodigoPlano(ArrayList<String> descArt){
		filas=descArt.size();
		ArrayList<String> codigoPlano=new ArrayList<>();
		for(ArrayList<String> artCod:articuloCodigo){
			for(String desc: descArt ){
				if(artCod.get(1).equals(desc)){
					codigoPlano.add(artCod.get(0));
				}
			}
		}
		return codigoPlano;
	}
}
