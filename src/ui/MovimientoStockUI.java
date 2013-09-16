package ui;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Movimiento;

public class MovimientoStockUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox origenSucursal;
	private final JComboBox cuasaCombo;
	private Movimiento m;
	private String sucursal;
	private String almacen;
	private String ubicacion;
	private JComboBox origenAlmacen;
	private JComboBox origenUbicacion;
	private JComboBox destinoSucursal;
	private JComboBox destinoAlmacen;
	private JComboBox destinoUbicacion;

	public MovimientoStockUI() {
		setTitle("Movimiento de Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCantidadAMover = new JLabel("Cantidad a mover");
		lblCantidadAMover.setBounds(323, 193, 107, 43);
		contentPane.add(lblCantidadAMover);
		
		textField_2 = new JTextField();
		textField_2.setBounds(442, 193, 77, 43);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(5, 193, 68, 43);
		contentPane.add(lblProducto);
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(537, 193, 53, 43);
		contentPane.add(lblLote);
		
		JComboBox causas = new JComboBox();
		causas.setBounds(104, 198, 209, 32);
		contentPane.add(causas);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(5, 265, 97, 43);
		contentPane.add(lblNota);
		
		textField = new JTextField();
		textField.setBounds(104, 265, 619, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(5, 308, 359, 43);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(364, 308, 359, 43);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(5, 351, 359, 43);
		contentPane.add(label_6);
		
		origenSucursal = new JComboBox();
		origenSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sucursal= arg0.getItem().toString();
				InicializaAlmacen(sucursal, origenAlmacen);
			}
		});
		origenSucursal.setBounds(104, 85, 204, 32);
		contentPane.add(origenSucursal);
		
		origenAlmacen = new JComboBox();
		origenAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacen= e.getItem().toString();
				InicializaUbicacion(almacen, origenUbicacion);
			}
		});
		origenAlmacen.setBounds(318, 86, 204, 30);
		contentPane.add(origenAlmacen);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(5, 94, 46, 14);
		contentPane.add(lblOrigen);
		
		origenUbicacion = new JComboBox();
		origenUbicacion.setBounds(532, 86, 204, 30);
		contentPane.add(origenUbicacion);
		
		JLabel lblDestino_1 = new JLabel("Destino");
		lblDestino_1.setBounds(5, 145, 46, 14);
		contentPane.add(lblDestino_1);
		
		destinoSucursal= new JComboBox();
		destinoSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sucursal= arg0.getItem().toString();
				InicializaAlmacen(sucursal, destinoAlmacen);
			}
		});
		destinoSucursal.setBounds(104, 136, 204, 32);
		contentPane.add(destinoSucursal);
		
		destinoAlmacen = new JComboBox();
		destinoAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacen= e.getItem().toString();
				InicializaUbicacion(almacen, destinoUbicacion);
			}
		});
		destinoAlmacen.setBounds(318, 137, 204, 30);
		contentPane.add(destinoAlmacen);
		
		destinoUbicacion= new JComboBox();
		destinoUbicacion.setBounds(532, 137, 204, 30);
		contentPane.add(destinoUbicacion);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(577, 198, 134, 32);
		contentPane.add(comboBox_6);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(104, 56, 89, 18);
		contentPane.add(lblSucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen:");
		lblAlmacen.setBounds(318, 61, 77, 14);
		contentPane.add(lblAlmacen);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		lblUbicacin.setBounds(537, 61, 84, 14);
		contentPane.add(lblUbicacin);
		
		cuasaCombo= new JComboBox();
		cuasaCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				e.getItem();
				System.out.println(e.getItem().toString());
			}
		});
		cuasaCombo.setBounds(104, 16, 204, 32);
		contentPane.add(cuasaCombo);
		
		JLabel label = new JLabel("Causa");
		label.setBounds(5, 11, 77, 43);
		contentPane.add(label);
		
		
		Inicializa();
		
	
	}
	
	public void Inicializa ()
	{
		m = new Movimiento();
		
		ArrayList<String> causas = m.getCausas();
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		for(String cau:causas){
			modelo.addElement(cau);
		}
		
		cuasaCombo.setModel(modelo);
		
		
		
		ArrayList<String> sucursales = m.getSucursales();
		modelo=new DefaultComboBoxModel<>();
		for(String suc:sucursales){
			modelo.addElement(suc);
		}
		
		origenSucursal.setModel(modelo);
		destinoSucursal.setModel(modelo);
		
		
		
	}
	
	private void InicializaAlmacen (String alm, JComboBox almacen)
	{
		m = new Movimiento();
		ArrayList<String> almacenes = m.getAlmacenes(alm);
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		for(String a:almacenes){
			modelo.addElement(a);
		}
		
		almacen.setModel(modelo);
	}
	
	private void InicializaUbicacion (String alm, JComboBox ubicacion)
	{
		m = new Movimiento();
		ArrayList<String> ubicaciones = m.getUbicacion(alm);
		if(ubicaciones!=null){
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		for(String ub:ubicaciones){
			modelo.addElement(ub);
		}
		
		ubicacion.setModel(modelo);
		}
	}
}
