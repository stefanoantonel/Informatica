package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AgregaArticuloUI extends JFrame {

	private JPanel contentPane;
	private JTextField descripcion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregaArticuloUI frame = new AgregaArticuloUI();
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
	public AgregaArticuloUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(20, 59, 67, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblAgregarArticulos = new JLabel("Agregar Articulos");
		lblAgregarArticulos.setBounds(167, 11, 134, 14);
		contentPane.add(lblAgregarArticulos);
		
		descripcion = new JTextField();
		descripcion.setBounds(113, 56, 86, 20);
		contentPane.add(descripcion);
		descripcion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Material");
		lblNewLabel.setBounds(20, 84, 46, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox material = new JComboBox();
		material.setBounds(113, 87, 65, 20);
		contentPane.add(material);
		
		JLabel lblAlto = new JLabel("Alto");
		lblAlto.setBounds(20, 125, 46, 14);
		contentPane.add(lblAlto);
		
		JLabel lblAncho = new JLabel("Ancho");
		lblAncho.setBounds(20, 150, 46, 14);
		contentPane.add(lblAncho);
		
		JLabel lblProfundidad = new JLabel("Profundidad");
		lblProfundidad.setBounds(20, 175, 77, 14);
		contentPane.add(lblProfundidad);
		
		JLabel lblDiametro = new JLabel("Diametro");
		lblDiametro.setBounds(20, 200, 46, 14);
		contentPane.add(lblDiametro);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(20, 225, 46, 14);
		contentPane.add(lblPeso);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setBounds(236, 125, 95, 14);
		contentPane.add(lblUnidadDeMedida);
		
		JComboBox unidadMedida = new JComboBox();
		unidadMedida.setBounds(341, 122, 86, 20);
		contentPane.add(unidadMedida);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(236, 150, 46, 14);
		contentPane.add(lblColor);
		
		JLabel lblCostoStd = new JLabel("Costo Std");
		lblCostoStd.setBounds(236, 175, 65, 14);
		contentPane.add(lblCostoStd);
		
		JLabel lblPrecioVtaStd = new JLabel("Precio Vta Std");
		lblPrecioVtaStd.setBounds(236, 200, 95, 14);
		contentPane.add(lblPrecioVtaStd);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(236, 243, 46, 14);
		contentPane.add(lblTipo);
		
		JComboBox tipo = new JComboBox();
		tipo.setBounds(341, 240, 86, 20);
		contentPane.add(tipo);
		
		JLabel lblCentroDeCosto = new JLabel("Centro de costo");
		lblCentroDeCosto.setBounds(20, 268, 95, 14);
		contentPane.add(lblCentroDeCosto);
		
		JLabel lblClaseMers = new JLabel("Clase merciologica");
		lblClaseMers.setBounds(20, 299, 109, 14);
		contentPane.add(lblClaseMers);
		
		JComboBox centroCosto = new JComboBox();
		centroCosto.setBounds(123, 265, 65, 20);
		contentPane.add(centroCosto);
		
		JComboBox claseMerciologica = new JComboBox();
		claseMerciologica.setBounds(123, 296, 65, 20);
		contentPane.add(claseMerciologica);
		
		textField = new JTextField();
		textField.setBounds(92, 122, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(92, 147, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(92, 175, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(92, 197, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(92, 222, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(341, 147, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(341, 172, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(341, 200, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
	}
}
