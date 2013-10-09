package ui;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Articulos;
import modelo.MRP;
import modelo.Nodo;
import java.awt.Font;
import javax.swing.JSlider;

public class MrpUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private static MRP mrp;
	private JComboBox cmbArticulos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mrp = new MRP();
				try {
					MrpUI frame = new MrpUI();
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
	public MrpUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPlanMaestroDe = new JLabel("Plan Maestro de Producci\u00F3n");
		lblPlanMaestroDe.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblPlanMaestroDe.setBounds(194, 11, 260, 14);
		contentPane.add(lblPlanMaestroDe);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(261, 71, 75, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(164, 74, 75, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(164, 136, 61, 14);
		contentPane.add(lblProducto);
		
		cmbArticulos = new JComboBox();
		cmbArticulos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		cmbArticulos.setBounds(261, 129, 175, 28);
		contentPane.add(cmbArticulos);
		
		JLabel lblSemena = new JLabel("Semena");
		lblSemena.setBounds(164, 187, 61, 14);
		contentPane.add(lblSemena);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3", "4", "5"}));
		comboBox.setBounds(261, 184, 75, 20);
		contentPane.add(comboBox);
		
		InicializaArticulo();
	}
	
	public void InicializaArticulo ()
	{
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		ArrayList<Nodo> padresPrincipales;
		padresPrincipales = mrp.obtenerPadresPrincipales();

		modelo.addElement("");		
		for(Nodo art:padresPrincipales){
			modelo.addElement(art.getDescripcion());
		}
		
		cmbArticulos.setModel(modelo);
	}	
}

