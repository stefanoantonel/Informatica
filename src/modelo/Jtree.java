package modelo;



	import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ui.ExplosionUI;
import utilidades.DateClass;

	public class Jtree {
		// descomentar todos los if(nodo.getFecha_inicio()
	    
		private ArrayList<DefaultMutableTreeNode> dmtnLista= new ArrayList<>();
		private DefaultTreeModel modelo;
		private JTree tree;
		
		public Jtree(ArrayList<Nodo> ListaNodo,String fecha ){
			
//			DateClass parseDate= new DateClass();
//			fecha= parseDate.toDate(fecha);
			System.out.println("FECHA "+fecha);
			//para los padres principales
			
			//Defino el primer padre de TODO para crear el modelo con ese padre
			DefaultMutableTreeNode articulo = new DefaultMutableTreeNode("Articulos");			
			modelo =new DefaultTreeModel(articulo);
			int i=0;
			//Llamo a la funcion por cada padre principal que haya mandado en ListaNodo
			for(Nodo nodo: ListaNodo)
		      {
				//compareTo 0= si son iguales, menor a 0 si (fecha)es mas grande, mayor a 0 si (fecha) es mas chico
				//if(nodo.getFecha_inicio().compareTo(fecha)<0 && (nodo.getFecha_fin().compareTo(fecha)>0|| nodo.getFecha_fin()==null))				
				{
				String descPadre=nodo.getDescripcion().toString();
				DefaultMutableTreeNode p = new DefaultMutableTreeNode (descPadre);
				modelo.insertNodeInto(p, articulo,i);
				modelo= ArmaJtree(p,modelo,nodo,1,fecha);
				i++;
				}
		      }
		    
		    this.tree = new JTree(modelo);
			MostrarArbol();
		}
		
		
		//Otro constructor para explosionar un nodo especifico
		public Jtree(Nodo nodo, float cantidad,String fecha){

			//Defino el primer padre de TODO para crear el modelo con ese padre
			DefaultMutableTreeNode articulo = new DefaultMutableTreeNode("Articulos");			
			modelo =new DefaultTreeModel(articulo);
			//if(nodo.getFecha_inicio().compareTo(fecha)<0 && (nodo.getFecha_fin().compareTo(fecha)>0|| nodo.getFecha_fin()==null))
				{
				String descPadre=nodo.getId()+"/ "+nodo.getDescripcion().toString();
				DefaultMutableTreeNode padre = new DefaultMutableTreeNode (descPadre);
				modelo.insertNodeInto(padre, articulo,0);
				modelo= ArmaJtree(padre,modelo,nodo, cantidad,fecha);
				}
		    this.tree = new JTree(modelo);
			MostrarArbol();
		}
		
		
		
		public DefaultTreeModel ArmaJtree(DefaultMutableTreeNode articulo, DefaultTreeModel modelo, Nodo padre, float cantidad,String fecha)
		{
			int j=0;
			String a=null;
			DefaultMutableTreeNode vector[]= new DefaultMutableTreeNode[100];
			//System.out.println("m:"+j);
			if (padre.GetHijos()!= null)
			{Iterator<Nodo> ListaHijos = padre.GetHijos().iterator();
			
			while (ListaHijos.hasNext())
			{
			    Nodo nodoH = (Nodo)ListaHijos.next();
			    
				DateClass date=new DateClass();
				int comparacionInicio= date.compararFecha(nodoH.getFecha_inicio(), fecha);
				int comparacionFin= date.compararFecha(nodoH.getFecha_fin(), fecha);
			    if(comparacionInicio<0 && (comparacionFin>0|| nodoH.getFecha_fin()==null))
			    {a= nodoH.getDescripcion();
				Integer b= padre.getId();
				
				float cant = nodoH.getCantidad()*cantidad;
				String aa=nodoH.getId()+"/ "+a+" - Cant:"+cant+" "+nodoH.getUm();
				vector[j]=new DefaultMutableTreeNode(aa);
				DefaultMutableTreeNode x= new DefaultMutableTreeNode(b);
				modelo.insertNodeInto(vector[j], articulo,j);
				ArmaJtree(vector[j],modelo,nodoH,cantidad,fecha);
				j++;
			    }
			}
			}	
				
			//return;	
		return modelo;
			
		}
		
		public void MostrarArbol ()
		{
			ExplosionUI v = new ExplosionUI((DefaultTreeModel)this.tree.getModel());
//	        JScrollPane scroll = new JScrollPane(this.tree);
//	        v.getContentPane().setSize(100, 100);
//	        v.getContentPane().add(scroll);
//	        v.pack();
	        v.setVisible(true);
	        //v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
		}
		
	}
