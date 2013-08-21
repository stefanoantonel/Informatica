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
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

public class AgregaRelacionUI extends JFrame {

	private JPanel contentPane;
	private JTextField cantidad;
	final JList listPadre = new JList();
	final JList listHijo = new JList();
	DefaultListModel modeloHijo;
	public DefaultListModel getModeloHijo() {
		return modeloHijo;
	}

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

	public JList getListPadre() {
		return listPadre;
	}

	public JList getListHijo() {
		return listHijo;
	}

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
		this.modeloHijo=modelo2;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		final List list_2 = new List();
		final JComboBox<Object> comboBox = new JComboBox<Object>(um);
		
		//PONGO TODO LA UI	
		setBounds(100, 100, 696, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(281, 35, 229, 155);
		contentPane.add(scrollPane_1);
		
		scrollPane_1.setViewportView(listHijo);
		listHijo.setModel(modelo2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 229, 155);
		contentPane.add(scrollPane);
		//DECLARO LAS LISTAS
		
		scrollPane.setViewportView(listPadre);
		listPadre.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				JOptionPane.showMessageDialog(null, "f");
			}
		});
		
		
		
		//APLICO EL MODELO
		listPadre.setModel(modelo1);
		setLocationRelativeTo(null);
	
		JLabel lblPadre = new JLabel("Padre");
		lblPadre.setBounds(10, 11, 46, 14);
		contentPane.add(lblPadre);
		
		JLabel lblHijo = new JLabel("Hijo");
		lblHijo.setBounds(281, 11, 46, 14);
		contentPane.add(lblHijo);
		
		//LISTENER BOTON AGREGAR
		JButton btnAgregarRelacion = new JButton("Agregar Relacion");
		btnAgregarRelacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listPadre.getSelectedValue()!=null&& listHijo.getSelectedValue()!=null && cantidad.getText()!=null&& comboBox.getSelectedItem()!=null){
					if(!listPadre.getSelectedValue().equals(listHijo.getSelectedValue())){
						AgregaRelacion agre=new AgregaRelacion();
//						Nodo nodo=new Nodo(p, h)
						agre.InsertarRelacion(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString(),cantidad.getText().toString(), comboBox.getSelectedItem().toString(),"fechaInicio","fechaFin");
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
		
		//---------------------------------------------INICIALIZO EL COMBO
		
		comboBox.setBounds(544, 80, 113, 20);
		contentPane.add(comboBox);
//		comboBox.
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setBounds(544, 64, 86, 14);
		contentPane.add(lblUnidadDeMedida);
		
		JButton btnAgregeAlternativos = new JButton("Agrege Alternativos");
		btnAgregeAlternativos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listPadre.getSelectedValue()!=null && listHijo.getSelectedValue()!=null){
					AgregaRelacion a = new AgregaRelacion();
					if(a.ControlAlternativos(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString())){
						AgregarAlternativoUI alterna=new AgregarAlternativoUI(AgregaRelacionUI.this,listPadre.getSelectedValue().toString(),listHijo.getSelectedValue().toString());
						alterna.setVisible(true);
					   }
					else {JOptionPane.showMessageDialog(null, "la relacion No se definio");}
				}
				else{
					JOptionPane.showMessageDialog(null, "seleccione una realcion");
				}
			}
		});
		btnAgregeAlternativos.setBounds(526, 203, 144, 50);
		contentPane.add(btnAgregeAlternativos);
		
		
	}
}
