package modelo;



	import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ui.*;

	public class Jtree {
	    
		private ArrayList<DefaultMutableTreeNode> dmtnLista= new ArrayList<>();
		private DefaultTreeModel modelo;
		private JTree tree;
		
		public Jtree(ArrayList<Nodo> ListaNodo){
			//para los padres principales
			
			//Defino el primer padre de TODO para crear el modelo con ese padre
			DefaultMutableTreeNode articulo = new DefaultMutableTreeNode("Articulos");			
			modelo =new DefaultTreeModel(articulo);

			int i=0;
			//Llamo a la funcion por cada padre principal que haya mandado en ListaNodo
			for(Nodo nodo: ListaNodo)
		      {
				DefaultMutableTreeNode padre = new DefaultMutableTreeNode (nodo.GetValor().toString());
				DefaultMutableTreeNode p = new DefaultMutableTreeNode (nodo.getDescripcion());
				modelo.insertNodeInto(p, articulo,i);
				modelo= ArmaJtree(p,modelo,nodo);
				i++;
		      }
		    
		    this.tree = new JTree(modelo);
			MostrarArbol();
		}
		
		
		//Otro constructor para explosionar un nodo especifico
		public Jtree(Nodo nodo){

			//Defino el primer padre de TODO para crear el modelo con ese padre
			DefaultMutableTreeNode articulo = new DefaultMutableTreeNode("Articulos");			
			modelo =new DefaultTreeModel(articulo);

				DefaultMutableTreeNode padre = new DefaultMutableTreeNode (nodo.getDescripcion().toString());
				modelo.insertNodeInto(padre, articulo,0);
				modelo= ArmaJtree(padre,modelo,nodo);
		    
		    this.tree = new JTree(modelo);
			MostrarArbol();
		}
		
		
		
		public DefaultTreeModel ArmaJtree(DefaultMutableTreeNode articulo, DefaultTreeModel modelo, Nodo padre)
		{
			int j=0;
			DefaultMutableTreeNode vector[]= new DefaultMutableTreeNode[100];
			//System.out.println("m:"+j);
			if (padre.GetHijos()!= null)
			{Iterator<Nodo> ListaHijos = padre.GetHijos().iterator();
			
			while (ListaHijos.hasNext())
			{
			    Nodo nodoH = (Nodo)ListaHijos.next();
				String a= nodoH.getDescripcion();
				Integer b= padre.GetValor();
				
//				System.out.println("NODO PADRE: "+ padre.GetValor());
//				System.out.println("NODOH: "+ nodoH.GetValor());
//				System.out.println("nodoH   A: "+a);
				String aa=a+" - Cant:"+nodoH.getCantidad()+" "+nodoH.getUm();
				vector[j]=new DefaultMutableTreeNode(aa);
//				System.out.println("vector j: "+vector[0]);
				DefaultMutableTreeNode x= new DefaultMutableTreeNode(b);
				modelo.insertNodeInto(vector[j], articulo,j);

//				System.out.println("nodo: "+ a);
				ArmaJtree(vector[j],modelo,nodoH);
				j++;
				//m++;
//				System.out.println("m: "+j);
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
