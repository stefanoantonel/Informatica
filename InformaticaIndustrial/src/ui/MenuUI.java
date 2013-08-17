package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.AgregaRelacion;
import modelo.Arbol;
import modelo.Articulos;
import modelo.EliminaRelacion;
import modelo.Nodo;

import com.jgoodies.forms.factories.Borders.EmptyBorder;

public class MenuUI extends JFrame {

	private JPanel contentPane;

	/**FLOR
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MenuUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 467);
		
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministracionDeBom = new JLabel("Administracion de BOM");
		lblAdministracionDeBom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAdministracionDeBom.setBounds(191, 11, 213, 51);
		contentPane.add(lblAdministracionDeBom);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminaRelacion elim=new EliminaRelacion();
				elim.InicializarPadre();
//				MenuUI.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(341, 318, 141, 67);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Agregar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregaRelacion ag=new AgregaRelacion();
				ag.InicilizarUI();
//				MenuUI.this.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(339, 240, 143, 67);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 73, 254, 312);
		contentPane.add(scrollPane);
		
		//CARGO LOS ARTICULOS
		final JList<String> listArticulos = new JList<>();
		listArticulos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println("evento de seleccion");
				
				Arbol a=new Arbol();

				Nodo elecc=null;

				elecc=a.getNodoByDescripcion(listArticulos.getSelectedValue().toString());
				//inicio la interfaz, seteo y luego muestro sino null
				ArticuloUI artui=new ArticuloUI(elecc);
//				artui.setArticuloSelecc(elecc);
//				artui.InicializarCampos();
				artui.setVisible(true);
			}
		});
		
		
		Articulos arti=new Articulos();
		ArrayList<String> articulos=arti.ObtenerArticulos();
		int i=0;
		DefaultListModel<String> modelo=new DefaultListModel<>();
		for (String s:articulos){
//			s=articulos.get(i);
			modelo.addElement(s);
			i++;
		}
		listArticulos.setModel(modelo);
		scrollPane.setViewportView(listArticulos);
		
		JLabel lblArticulos = new JLabel("Articulos");
		lblArticulos.setBounds(30, 57, 69, 14);
		contentPane.add(lblArticulos);
		setLocationRelativeTo(null);
	}
}
