package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.AgregaArticulo;
import modelo.AgregaRelacion;
import modelo.Arbol;
import modelo.Articulos;
import modelo.EliminaRelacion;
import modelo.Nodo;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.text.SimpleAttributeSet;

import persistencia.RemitoDAO;

public class MenuUI extends JFrame {

	private JPanel contentPane;

	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	String hoy=df.format(new java.util.Date());
	public static Arbol arbol=new Arbol();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUI frame = new MenuUI();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuUI() {
//		setTitle("Informatica Industrial");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 747, 483);
//		
//		contentPane = new JPanel();
////		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		JLabel lblAdministracionDeBom = new JLabel("Administracion de BOM");
//		lblAdministracionDeBom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
//		lblAdministracionDeBom.setBounds(191, 11, 213, 51);
//		contentPane.add(lblAdministracionDeBom);
//		
//		
////		JButton btnExplosionTotal = new JButton("Explosion");
////		btnExplosionTotal.addActionListener(new ActionListener() {
////			public void actionPerformed(ActionEvent arg0) {
////				Arbol arbol = new Arbol();
////				arbol.MostrarArbol(articuloSelecc);
////			}
////		});
//		
//		JButton btnNewButton_1 = new JButton("Eliminar Relacion");
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				EliminaRelacion elim=new EliminaRelacion();
//				elim.InicializarPadre();
////				MenuUI.this.setVisible(false);
//			}
//		});
//		btnNewButton_1.setBounds(341, 318, 159, 67);
//		contentPane.add(btnNewButton_1);
//		
//		JButton btnNewButton_2 = new JButton("Agregar Relacion");
//		btnNewButton_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				AgregaRelacion ag=new AgregaRelacion();
//				ag.InicilizarUI();
////				MenuUI.this.setVisible(false);
//			}
//		});
//		btnNewButton_2.setBounds(339, 240, 161, 67);
//		contentPane.add(btnNewButton_2);
//		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(30, 73, 254, 312);
//		contentPane.add(scrollPane);
//		
//		//CARGO LOS ARTICULOS
//		final JList<String> listArticulos = new JList<>();
//		final ArticuloUI artui=new ArticuloUI();
//		listArticulos.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				System.out.println("evento de seleccion");
//				
//				Arbol a=new Arbol();
//
//				Nodo elecc=null;
//				String eleccion=listArticulos.getSelectedValue().toString();
//				elecc=a.getNodoByDescripcion(eleccion);
//				if(elecc==null){
//					AgregaArticulo aa=new AgregaArticulo();
//					
//					String dato=aa.getDatosArticulo(eleccion);
//					JOptionPane.showMessageDialog(null, dato);
//				}
//				//inicio la interfaz, seteo y luego muestro sino null
//			    artui.setArticuloSelecc(elecc);
//			    artui.InicializarCampos();
////				artui.setArticuloSelecc(elecc);
////				artui.InicializarCampos();
//				artui.setVisible(true);
//			}
//		});
//		
//
//		
//		
//		
//		Articulos arti=new Articulos();
//		ArrayList<String> articulos=arti.ObtenerArticulos();
//		int i=0;
//		DefaultListModel<String> modelo=new DefaultListModel<>();
//		for (String s:articulos){
////			s=articulos.get(i);
//			modelo.addElement(s);
//			i++;
//		}
//		listArticulos.setModel(modelo);
//		scrollPane.setViewportView(listArticulos);
//		
//		JLabel lblArticulos = new JLabel("Articulos");
//		lblArticulos.setBounds(30, 57, 69, 14);
//		contentPane.add(lblArticulos);
//		
//		JButton button = new JButton("Explosion");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
////				Arbol arbol = new Arbol("getDate()");
//				arbol.MostrarArbol(hoy);
//			}
//		});
//		button.setBounds(341, 162, 159, 67);
//		contentPane.add(button);
//		
//		JButton btnNewButton = new JButton("Agrega Articulo");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				AgregaArticulo a=new AgregaArticulo();
//				a.InicializarUI();
//				
//			}
//		});
//		btnNewButton.setBounds(341, 83, 159, 67);
//		contentPane.add(btnNewButton);
//		
//		JButton btnNewButton_3 = new JButton("Cargar Stock");
//		btnNewButton_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//				CargaStockUI.main(null);
//			}
//		});
//		btnNewButton_3.setBounds(512, 82, 159, 68);
//		contentPane.add(btnNewButton_3);
//		
//		JButton btnNewButton_4 = new JButton("Genera Remito");
//		btnNewButton_4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TablaCargaRemitoUI.main(null);
//				
//			}
//		});
//		btnNewButton_4.setBounds(512, 162, 159, 67);
//		contentPane.add(btnNewButton_4);
//		
//		JButton btnRemitosPendientes = new JButton("Remitos Pendientes ");
//		
//		btnRemitosPendientes.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				PedidosPendientesListaUI pp=new PedidosPendientesListaUI();
//				pp.inicializarUI();
//			}
//		});
//		btnRemitosPendientes.setBounds(510, 240, 161, 67);
//		contentPane.add(btnRemitosPendientes);
//		setLocationRelativeTo(null);
//		
		
		MovimientoStockUI msui= new MovimientoStockUI();
		msui.setVisible(true);
			
		
	}
}
