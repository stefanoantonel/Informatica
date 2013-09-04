package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import persistencia.Conexion;
import ui.*;

public class EliminaRelacion {
	
	Nodo padre;
	Nodo hijo;
	EliminaRelacionUI elim;
	Arbol a;
	DefaultListModel<String> modeloPadre= new DefaultListModel<>();
	DefaultListModel<String> modelo2 = new DefaultListModel<>();

		public EliminaRelacion()
		{
			elim= new EliminaRelacionUI();
			a= MenuUI.arbol;
		}
		
		public void InicializarPadre ()
		{
			
			//elim= new EliminaRelacionUI();
			//modeloPadre = new DefaultListModel<>();
			//modelo2 = new DefaultListModel<>();
			ArrayList<Nodo> padres = a.obtenerPadres();
			
			for (Nodo p: padres)
			{
				String pDesc =p.getDescripcion();
				modeloPadre.addElement(pDesc);
			}
			
			elim.SetPadre(modeloPadre);
			elim.setVisible(true);
		
			return;
			
		}
		
		
		public void recargaPantalla ()
		{
			ArrayList<Nodo> padres = a.obtenerPadres();
			//modeloPadre = new DefaultListModel<>();
			for (Nodo p: padres)
			{
				String pDesc =p.getDescripcion();
				modeloPadre.addElement(pDesc);
			}
			
			elim.SetPadre(modeloPadre);
			//elim.setVisible(true);
		
			return;
			
		}


	public DefaultListModel<String> InicializarHijo(Nodo nodo){
		DefaultListModel<String> modeloHijo = new DefaultListModel<>();
		//--------------------busco los hijos con el nodo padre
		ArrayList<Nodo> hijos=new ArrayList();
		hijos=nodo.GetHijos();
		for (Nodo n:hijos){
			modeloHijo.addElement(n.getDescripcion());
		}
		return modeloHijo;
	}

	public void Eliminacion(String padreDesc, String hijoDesc, EliminaRelacionUI ui){
		Connection con;
		ResultSet rs=null;
		
		Arbol a = MenuUI.arbol;
		padre= (a.getNodoByDescripcion(padreDesc));
		hijo= (a.getNodoByDescripcion(hijoDesc));
		
		Nodo nodo = new Nodo();
		nodo.eliminarRelacion(padre, hijo);

	}
}
