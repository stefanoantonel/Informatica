package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import modelo.Arbol;
import modelo.EliminaRelacion;
import modelo.Nodo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminaRelacionUI extends JFrame {

	private JPanel contentPane;
	JList listHijo = new JList();
	JList listPadre = new JList();
	String descripcion=null;
	
	DefaultListModel<String> modeloPadre;
	DefaultListModel<String> modeloHijo;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EliminiaRelacionUI frame = new EliminiaRelacionUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
public EliminaRelacionUI() {

}
	public EliminaRelacionUI(DefaultListModel<String> padre) {
		
		modeloPadre=padre;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		listPadre.setModel(modeloPadre);
		
		listPadre.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				EliminaRelacion elim=new EliminaRelacion();
				Arbol arbol=new Arbol();
			
			String descripcion =listPadre.getSelectedValue().toString();
				//obtengo el valor del nodo padre y lo pongo en nodo
				Nodo nodo=(arbol.getNodoByDescripcion(descripcion));
				System.out.println("valor del padre seleciiondo"+nodo.GetValor());
				listHijo.setModel(elim.InicializarHijo(nodo));
				
			}
		});
		listPadre.setBounds(10, 39, 203, 159);
		contentPane.add(listPadre);
		
		JLabel lblPadre = new JLabel("Padre");
		lblPadre.setBounds(10, 14, 46, 14);
		contentPane.add(lblPadre);
		
		
		listHijo.setBounds(273, 39, 203, 159);
		contentPane.add(listHijo);
		
		JLabel lblHijo = new JLabel("Hijo");
		lblHijo.setBounds(273, 14, 46, 14);
		contentPane.add(lblHijo);
		
		JButton btnEliminaRelacion = new JButton("Elimina Relacion");
		btnEliminaRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminaRelacion eli=new EliminaRelacion();
				eli.Eliminacion(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString(),EliminaRelacionUI.this);
			}
		});
		btnEliminaRelacion.setBounds(193, 232, 143, 23);
		contentPane.add(btnEliminaRelacion);
		this.setLocationRelativeTo(null);
	}
	
	
	public void SetPadre(DefaultListModel<String> padre)
	{

		
		modeloPadre=padre;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		listPadre.setModel(modeloPadre);
		
		listPadre.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				EliminaRelacion elim=new EliminaRelacion();
				Arbol arbol=new Arbol();
			
			 descripcion =listPadre.getSelectedValue().toString();
				//obtengo el valor del nodo padre y lo pongo en nodo
				Nodo nodo=(arbol.getNodoByDescripcion(descripcion));
				System.out.println("valor del padre seleciiondo"+nodo.GetValor());
				listHijo.setModel(elim.InicializarHijo(nodo));
				
			}
			
				
			
			
		});
		
		if (descripcion!= null)
		{
			EliminaRelacion elim=new EliminaRelacion();
			Arbol arbol=new Arbol();
		Nodo nodo=(arbol.getNodoByDescripcion(descripcion));
		System.out.println("valor del padre seleciiondo"+nodo.GetValor());
		listHijo.setModel(elim.InicializarHijo(nodo));
		}
		
		
		listPadre.setBounds(10, 39, 203, 159);
		contentPane.add(listPadre);
		
		JLabel lblPadre = new JLabel("Padre");
		lblPadre.setBounds(10, 14, 46, 14);
		contentPane.add(lblPadre);
		
		
		listHijo.setBounds(273, 39, 203, 159);
		contentPane.add(listHijo);
		
		JLabel lblHijo = new JLabel("Hijo");
		lblHijo.setBounds(273, 14, 46, 14);
		contentPane.add(lblHijo);
		
		JButton btnEliminaRelacion = new JButton("Elimina Relacion");
		btnEliminaRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EliminaRelacion eli=new EliminaRelacion();
				eli.Eliminacion(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString(),EliminaRelacionUI.this);
			}
		});
		btnEliminaRelacion.setBounds(193, 232, 143, 23);
		contentPane.add(btnEliminaRelacion);
		this.setLocationRelativeTo(null);
	
	}
	
	
}
