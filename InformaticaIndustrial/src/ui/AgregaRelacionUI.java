package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.AgregaRelacion;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AgregaRelacionUI extends JFrame {

	private JPanel contentPane;
	private JTextField cantidad;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReporteUI frame = new ReporteUI(new DefaultListModel<>());
//
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

	public AgregaRelacionUI(DefaultListModel modelo1, DefaultListModel modelo2, Object[] um) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
//				AgregaRelacionUI.this.dispose();
			}
		});
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//DECLARO LAS LISTAS
		final JList listPadre = new JList();
		listPadre.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				JOptionPane.showMessageDialog(null, "f");
			}
		});
		final JList listHijo = new JList();
//		final List list_2 = new List();
		final JComboBox<Object> comboBox = new JComboBox<Object>(um);
		
		//PONGO TODO LA UI	
		setBounds(100, 100, 696, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listHijo.setBounds(281, 35, 229, 155);
		contentPane.add(listHijo);
		listPadre.setBounds(10, 35, 229, 155);
		contentPane.add(listPadre);
		setLocationRelativeTo(null);
	
		JLabel lblPadre = new JLabel("Padre");
		lblPadre.setBounds(10, 11, 46, 14);
		contentPane.add(lblPadre);
		
		JLabel lblHijo = new JLabel("Hijo");
		lblHijo.setBounds(281, 11, 46, 14);
		contentPane.add(lblHijo);
		
		
		
		//APLICO EL MODELO
		listPadre.setModel(modelo1);
		listHijo.setModel(modelo2);
		
		//LISTENER BOTON AGREGAR
		JButton btnAgregarRelacion = new JButton("Agregar Relacion");
		btnAgregarRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listPadre.getSelectedValue()!=null&& listHijo.getSelectedValue()!=null && cantidad.getText()!=null&& comboBox.getSelectedItem()!=null){
					if(!listPadre.getSelectedValue().equals(listHijo.getSelectedValue())){
						AgregaRelacion agre=new AgregaRelacion();
//						Nodo nodo=new Nodo(p, h)
						agre.InsertarRelacion(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString(),cantidad.getText().toString(), comboBox.getSelectedItem().toString());
					}
					else{ JOptionPane.showMessageDialog(null, "seleccione articulos distintos");}
				}
				else { JOptionPane.showMessageDialog(null, "Complete todos los campos");}
			}
		});
		btnAgregarRelacion.setBounds(526, 140, 144, 50);
		contentPane.add(btnAgregarRelacion);
		
		cantidad = new JTextField();
		cantidad.setText("1");
		cantidad.setBounds(544, 33, 86, 20);
		contentPane.add(cantidad);
		cantidad.setColumns(10);
		
		JLabel lblCantidadDelHijo = new JLabel("Cantidad del hijo");
		lblCantidadDelHijo.setBounds(544, 11, 113, 14);
		contentPane.add(lblCantidadDelHijo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(581, 247, 89, 23);
		contentPane.add(btnAtras);
		
		//---------------------------------------------INICIALIZO EL COMBO
		
		comboBox.setBounds(544, 80, 113, 20);
		contentPane.add(comboBox);
//		comboBox.
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setBounds(544, 64, 86, 14);
		contentPane.add(lblUnidadDeMedida);
		
		
	}
}
