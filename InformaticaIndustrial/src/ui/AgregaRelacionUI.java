package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.AgregaRelacion;

public class AgregaRelacionUI extends JFrame {

	private JPanel contentPane;
	private JTextField cantidad;
	final JList listPadre = new JList();
	final JList listHijo = new JList();
	DefaultListModel modeloHijo;
	private JTextField fechaInicio;
	private JTextField fechaFIn;
	
	public String getFechaInicio() {
		return fechaInicio.getText();
	}

	public void setFechaInicio(JTextField fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFIn() {
		
//			StringTokenizer st=new StringTokenizer(,"-");
		return fechaFIn.getText();
		
		
	}

	public void setFechaFIn(JTextField fechaFIn) {
		this.fechaFIn = fechaFIn;
	}

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
		setBounds(100, 100, 696, 350);
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
//				listPadre.getsel
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
						System.out.println("fecha inicio"+ getFechaInicio());
						System.out.println("fecha fin"+ getFechaFIn());
						agre.InsertarRelacion(listPadre.getSelectedValue().toString(), listHijo.getSelectedValue().toString(),cantidad.getText().toString(), comboBox.getSelectedItem().toString(),getFechaInicio(),getFechaFIn());
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
		btnAgregeAlternativos.setBounds(526, 225, 144, 50);
		contentPane.add(btnAgregeAlternativos);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setBounds(10, 221, 80, 14);
		contentPane.add(lblFechaInicio);
		
		fechaInicio = new JTextField();
		fechaInicio.setBounds(83, 218, 86, 20);
		contentPane.add(fechaInicio);
		fechaInicio.setColumns(10);
		
		JLabel lblddmmyyyy = new JLabel("(YYYY-MM-DD)");
		lblddmmyyyy.setBounds(181, 221, 86, 14);
		contentPane.add(lblddmmyyyy);
		
		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setBounds(10, 261, 59, 14);
		contentPane.add(lblFechaFin);
		
		fechaFIn = new JTextField();
		fechaFIn.setBounds(83, 258, 86, 20);
		contentPane.add(fechaFIn);
		fechaFIn.setColumns(10);
		
		JLabel label = new JLabel("(YYYY-MM-DD)");
		label.setBounds(181, 261, 86, 14);
		contentPane.add(label);
		
		

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		
	}
}
