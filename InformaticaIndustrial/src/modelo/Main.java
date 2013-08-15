package modelo;
 import java.sql.Connection;
import java.sql.ResultSet;

import persistencia.Conexion;
import ui.EliminiaRelacionUI;

//import sql.Conexion;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Arbol ma = new Arbol();
		EliminaRelacion elimina=new EliminaRelacion();
		elimina.InicializarPadre();
		AgregaRelacion ag=new AgregaRelacion();
		ag.InicilizarUI();
		
	}
	

}
