package ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Articulos;
import modelo.Movimiento;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovimientoStockUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtCantidad;
	private JComboBox origenSucursal;
	private final JComboBox causaCombo;
	private Movimiento m;
	private String sucursal;
	private String almacen;
	private String ubicacion;
	private JComboBox origenAlmacen;
	private JComboBox origenUbicacion;
	private JComboBox destinoSucursal;
	private JComboBox destinoAlmacen;
	private JComboBox destinoUbicacion;
	private JComboBox articuloCombo;
	private JComboBox loteCombo;
	private JLabel cantMax ;
	private String lote;
	private Integer cantidadMaxima;
	private String causaElegida;

	public MovimientoStockUI() {
		setTitle("Movimiento de Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCantidadAMover = new JLabel("Cantidad a mover");
		lblCantidadAMover.setBounds(323, 193, 107, 43);
		contentPane.add(lblCantidadAMover);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(438, 198, 68, 32);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblProducto = new JLabel("Articulo");
		lblProducto.setBounds(5, 193, 68, 43);
		contentPane.add(lblProducto);
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(571, 193, 53, 43);
		contentPane.add(lblLote);
		
		articuloCombo = new JComboBox();
		articuloCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cantMax.setText("Max()");
			}
		});
		articuloCombo.setBounds(104, 198, 209, 32);
		contentPane.add(articuloCombo);
		
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
		
		origenSucursal = new JComboBox();
		origenSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sucursal= arg0.getItem().toString();
				InicializaAlmacen(sucursal, origenAlmacen);
				System.out.println("origenAlmacen");
			}
		});
		origenSucursal.setBounds(104, 85, 204, 32);
		contentPane.add(origenSucursal);
		
		origenAlmacen = new JComboBox();
		origenAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacen= e.getItem().toString();
				InicializaUbicacion(almacen, origenUbicacion);
			    InicializaArticulo(almacen);
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
				System.out.println("destinoAlmacen");
			}
		});
		destinoSucursal.setBounds(104, 136, 204, 32);
		contentPane.add(destinoSucursal);
		
		destinoAlmacen = new JComboBox();
		destinoAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String al= e.getItem().toString();
				InicializaUbicacion(al, destinoUbicacion);
			}
		});
		destinoAlmacen.setBounds(318, 137, 204, 30);
		contentPane.add(destinoAlmacen);
		
		destinoUbicacion= new JComboBox();
		destinoUbicacion.setBounds(532, 137, 204, 30);
		contentPane.add(destinoUbicacion);
		
		loteCombo = new JComboBox();
		loteCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lote= arg0.getItem().toString();
				System.out.println("lote" + lote);
				if(lote!=null && lote!="")
				{if(!lote.equals("indistinto"))			
					{
					cantidadMaxima= m.getCantidadXlote(Integer.parseInt(lote));
					cantMax.setText("Max("+cantidadMaxima+")");
					}
				else
					cantidadMaxima=null;
				}
			}
		});
		loteCombo.setBounds(634, 198, 89, 32);
		contentPane.add(loteCombo);
		
		JLabel lblSucursal = new JLabel("Sucursal:");
		lblSucursal.setBounds(104, 56, 89, 18);
		contentPane.add(lblSucursal);
		
		JLabel lblAlmacen = new JLabel("Almacen:");
		lblAlmacen.setBounds(318, 61, 77, 14);
		contentPane.add(lblAlmacen);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n:");
		lblUbicacin.setBounds(537, 61, 84, 14);
		contentPane.add(lblUbicacin);
		
		causaCombo= new JComboBox();
		causaCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				e.getItem();
				System.out.println(e.getItem().toString());
				String causa=e.getItem().toString();
				String selec;
				//if(causa.contains("\\)"))
				{
				selec=(causa.split("\\)"))[0];
				System.out.println("selec: "+selec);
				causaElegida=selec;
				origenSucursal.setEnabled(true);
				loteCombo.setEnabled(true);
				InicializaSuc();
				if (causaElegida.equals("1"))
				 {
					InicializaArticulo(null);
				 }
				}
			}
		});
		causaCombo.setBounds(104, 16, 204, 32);
		contentPane.add(causaCombo);
		
		JLabel label = new JLabel("Causa");
		label.setBounds(5, 11, 77, 43);
		contentPane.add(label);
		
		cantMax = new JLabel("( )");
		cantMax.setBounds(516, 207, 46, 14);
		contentPane.add(cantMax);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkContenido()==false)
					{JOptionPane.showMessageDialog(null, "Debe completar los campos");
					return;
					}
				Integer cantIngresada=Integer.parseInt(txtCantidad.getText());
				if(cantidadMaxima!=null)
				{	if( cantIngresada>cantidadMaxima)
					{
						JOptionPane.showMessageDialog(null, "No hay suficientes articulos para ese lote");
						return;
					}
				}
			}
		});
		btnAceptar.setBounds(607, 340, 116, 39);
		contentPane.add(btnAceptar);
		
		
		Inicializa();
		
	
	}
	
	
	public void Inicializa ()
	{
		m = new Movimiento();
		
		ArrayList<String> causas = m.getCausas();
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		modelo.addElement("");
		for(String cau:causas){
			modelo.addElement(cau);
		}
		causaCombo.setModel(modelo);
	}	
	
	public void InicializaSuc ()
	{
		m = new Movimiento();
		
		ArrayList<String> sucursales = m.getSucursales();
		DefaultComboBoxModel<String> modelo;
		if(causaElegida.equals("1") ||causaElegida.equals("2") ||causaElegida.equals("3") || causaElegida.equals("4"))
		{
			origenSucursal.setEnabled(false);
			System.out.println("causa 1,2,3 o 4");
		}
		else
		{
			    modelo=new DefaultComboBoxModel<>();
				for(String suc:sucursales){
					modelo.addElement(suc);
				}
				
				origenSucursal.setModel(modelo);
		}
		   
		modelo=new DefaultComboBoxModel<>();
			for(String suc:sucursales){
				modelo.addElement(suc);
			}
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
	
	private void InicializaArticulo (String alm)
	{
		System.out.println("alm : "+alm);
		m = new Movimiento();
		ArrayList<Articulos> arts;
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		
		if(!causaElegida.equals("1"))
		{
			arts = m.getArticulosXalmacen(alm);
			if(arts!=null){
			DefaultComboBoxModel<String> modeloLote=new DefaultComboBoxModel<>();
			modelo.addElement("");
			modeloLote.addElement("");
			modeloLote.addElement("Indistinto");
			for(Articulos art:arts){
				modelo.addElement(art.getDesc());
				modeloLote.addElement(art.getLote().toString());
			}
			
			articuloCombo.setModel(modelo);
			loteCombo.setModel(modeloLote);
			}	
		}
		else
		{
			arts= m.getArticulos();
			for(Articulos art:arts){
				modelo.addElement(art.getDesc());
			}
			articuloCombo.setModel(modelo);
			loteCombo.setEnabled(false);
			System.out.println("lote combo flase");
		}
	}

	private Boolean checkContenido()
	{
		 if((causaElegida.equals("1") && txtCantidad.getText()!=null) || (txtCantidad.getText()!=null && lote!=null))
			return true;
		else
			return false;
		 
	}
}
