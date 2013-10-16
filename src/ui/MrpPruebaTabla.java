package ui;

import java.awt.BorderLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import persistencia.MrpDao;
import modelo.Arbol;
import modelo.Nodo;
import modelo.Proveedor;

public class MrpPruebaTabla extends JFrame {

	public MrpPruebaTabla(ArrayList<ArrayList<Integer>> mrp2, Arbol a, int articuloID) {

		generaTabla(mrp2,a);
		ArrayList<Nodo> art=a.getNodoByArticulo(articuloID);
		this.pack();
		this.setTitle("MRP - "+art.get(0).getDescripcion());
		this.setLocationRelativeTo(null);
		insertarEnTablaMRP(mrp2);
		this.setVisible(true);
	}

	public void generaTabla(ArrayList<ArrayList<Integer>> mrp,Arbol arbol) {

		ArrayList<Object> nombreColumnas = new ArrayList<>();
		int tam = mrp.get(0).size(); // obtengo el primer padre que NO tiene 2 columans
		for (int i = 0; i < tam; i++) {
			// pongo los nombres de columna segun las semanas del mrp
			nombreColumnas.add("Semana: "+i);
		}
		nombreColumnas.add("Articulo");
		nombreColumnas.add("Proveedor");
		
		Proveedor prov=new Proveedor();
		mrp.get(0).add(0);
		mrp.get(0).add(0);
		ArrayList<ArrayList<Object>> tablaMRP=new ArrayList<>();
		for (int fila = 0; fila < mrp.size(); fila++) {
			ArrayList<Object> auxfi=new ArrayList<>();
			//es una auxiliar
			for (int col = 0; col < mrp.get(fila).size(); col++) {
				if(col==(mrp.get(fila).size()-2)&&(mrp.get(fila).get(col)!=0)){
					//esta en articulo
					ArrayList<Nodo>n=arbol.getNodoByArticulo(mrp.get(fila).get(col));
					String desc=n.get(0).getDescripcion();
					auxfi.add(desc);
				}
				else{
					if(col==(mrp.get(fila).size()-1)&&(mrp.get(fila).get(col)!=0)){
						//esta en proveedor
						String desc=prov.getDescripcion(mrp.get(fila).get(col));
						auxfi.add(desc);
					}
					else{
						auxfi.add(mrp.get(fila).get(col));
					}
				}
			}
			tablaMRP.add(auxfi);
		}
		
		
		int columnas=tablaMRP.get(0).size();
		int filas=tablaMRP.size();
		//System.out.println("["+filas+"]["+columnas+"]");
		Object[][] data = new Object[filas][columnas];
		//Lo paso a [][]
		for (int i = 0; i < data.length; i++) { 
	           for (int j = 0; j < data[i].length; j++) {
	        	   data[i][j]=tablaMRP.get(i).get(j);
	        	   //System.out.println(data[i][j]);
	           }
	           
		}
		
		DefaultTableModel modelo = new DefaultTableModel(data,nombreColumnas.toArray());
		
		JTable table = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(table);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
//		String[] newColumn = { "Flan", "Pastel", "Helado", "Barquillo","Manzana" };
//		modelo.addColumn("Postre", newColumn);
		
	}
	
	public void insertarEnTablaMRP(ArrayList<ArrayList<Integer>> mrp2){
		MrpDao dao=new MrpDao();
		int proveedorId;
		int articuloId;
		int cantidad=0;
		int fecha=0;
		int indiceP=mrp2.get(1).size()-1;
		int indiceA=mrp2.get(1).size()-2;
		for(int fila=1;fila<mrp2.size();fila++){
			proveedorId=mrp2.get(fila).get(indiceP);
			articuloId=mrp2.get(fila).get(indiceA);
			for(int colum=0;colum<mrp2.get(1).size()-2;colum++){
				if(mrp2.get(fila).get(colum)!=0){
					fecha=colum*7;
					cantidad=mrp2.get(fila).get(colum);
					dao.insertarEnTablaMRP(proveedorId, articuloId, cantidad, fecha);
				}
				
			}
		}
		
	}
	
	
	
	
}
