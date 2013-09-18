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
	private JTextField txtNota;
	private JTextField txtCantidad;
	private JComboBox origenSucursal;
	private final JComboBox causaCombo;
	private Movimiento m;
	public String sucursalOrigen;
	public String almacenOrigen;
	public String ubicacionOrigen;
	public String sucursalDestino;
	public String almacenDestino;
	public String ubicacionDestino;
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
	public String causaElegida;
	public Integer cantIngresada;
	private ArrayList<Articulos> artsCompra;
	private ArrayList<Articulos> arts;
	private JLabel lbUm;
	public String articulo;
	private JLabel lblFecha;
	private JTextField txtFecha;
	public String fecha;
	public String nota;

	public MovimientoStockUI() {
		setTitle("Movimiento de Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCantidadAMover = new JLabel("Cantidad a mover");
		lblCantidadAMover.setBounds(288, 193, 107, 43);
		contentPane.add(lblCantidadAMover);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(398, 198, 68, 32);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblProducto = new JLabel("Articulo");
		lblProducto.setBounds(5, 193, 68, 43);
		contentPane.add(lblProducto);
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(641, 193, 53, 43);
		contentPane.add(lblLote);
		
		articuloCombo = new JComboBox();
		articuloCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cantMax.setText("Max()");
				articulo=arg0.getItem().toString();
				System.out.println("artElegido: "+articulo);
				inicializaLote(arg0.getItem().toString());
				inicializaUM(arg0.getItem().toString());
			}
		});
		articuloCombo.setBounds(104, 198, 174, 32);
		contentPane.add(articuloCombo);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(5, 265, 97, 43);
		contentPane.add(lblNota);
		
		txtNota = new JTextField();
		txtNota.setBounds(104, 265, 668, 43);
		contentPane.add(txtNota);
		txtNota.setColumns(10);
		
		origenSucursal = new JComboBox();
		origenSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sucursalOrigen= arg0.getItem().toString();
				InicializaAlmacen(sucursalOrigen, origenAlmacen);
				System.out.println("origenAlmacen" + sucursalOrigen);
			}
		});
		origenSucursal.setBounds(104, 85, 204, 32);
		contentPane.add(origenSucursal);
		
		origenAlmacen = new JComboBox();
		origenAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacenOrigen= e.getItem().toString();
				InicializaUbicacion(almacenOrigen, origenUbicacion);
				System.out.println("almacenOrigen: "+ almacenOrigen);
			    InicializaArticulo(almacenOrigen);
			}
		});
		origenAlmacen.setBounds(318, 86, 204, 30);
		contentPane.add(origenAlmacen);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(5, 94, 46, 14);
		contentPane.add(lblOrigen);
		
		origenUbicacion = new JComboBox();
		origenUbicacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ubicacionOrigen= e.getItem().toString();
			}
		});
		origenUbicacion.setBounds(532, 86, 240, 30);
		contentPane.add(origenUbicacion);
		
		JLabel lblDestino_1 = new JLabel("Destino");
		lblDestino_1.setBounds(5, 145, 46, 14);
		contentPane.add(lblDestino_1);
		
		destinoSucursal= new JComboBox();
		destinoSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				sucursalDestino= arg0.getItem().toString();
				InicializaAlmacen(sucursalDestino, destinoAlmacen);
				//System.out.println("destinoAlmacen");
			}
		});
		destinoSucursal.setBounds(104, 136, 204, 32);
		contentPane.add(destinoSucursal);
		
		destinoAlmacen = new JComboBox();
		destinoAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacenDestino= e.getItem().toString();
				InicializaUbicacion(almacenDestino, destinoUbicacion);
			}
		});
		destinoAlmacen.setBounds(318, 137, 204, 30);
		contentPane.add(destinoAlmacen);
		
		destinoUbicacion= new JComboBox();
		destinoUbicacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ubicacionDestino=e.getItem().toString();
			}
		});
		destinoUbicacion.setBounds(532, 137, 240, 30);
		contentPane.add(destinoUbicacion);
		
		loteCombo = new JComboBox();
		loteCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lote= arg0.getItem().toString();
				//System.out.println("lote" + lote);
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
		loteCombo.setBounds(704, 198, 68, 32);
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
				//System.out.println(e.getItem().toString());
				String causa=e.getItem().toString();
				String selec;
				selec=(causa.split("\\)"))[0];
				//System.out.println("selec: "+selec);
				causaElegida=selec;
				origenSucursal.setEnabled(true);
				loteCombo.setEnabled(true);
				InicializaSuc();
				if (causaElegida.equals("1"))
				 {
					InicializaArticulo(null);
				 }
			}
		});
		causaCombo.setBounds(104, 16, 204, 32);
		contentPane.add(causaCombo);
		
		JLabel label = new JLabel("Causa");
		label.setBounds(5, 11, 77, 43);
		contentPane.add(label);
		
		cantMax = new JLabel("( )");
		cantMax.setBounds(575, 207, 46, 14);
		contentPane.add(cantMax);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkContenido()==false)
					{JOptionPane.showMessageDialog(null, "Debe completar los campos");
					return;
					}
				//System.out.println("txtCantidad"+txtCantidad.getText());
				cantIngresada=Integer.parseInt(txtCantidad.getText());
				if(cantidadMaxima!=null)
				{	if( cantIngresada>cantidadMaxima)
					{
						JOptionPane.showMessageDialog(null, "No hay suficientes articulos para ese lote");
						return;
					}
				}
				fecha= txtFecha.getText();
				nota=txtNota.getText();
				m.insertarMovimiento(MovimientoStockUI.this);
				
			}
		});
		btnAceptar.setBounds(656, 340, 116, 39);
		contentPane.add(btnAceptar);
		
		lbUm = new JLabel("New label");
		lbUm.setBounds(476, 207, 89, 14);
		contentPane.add(lbUm);
		
		lblFecha = new JLabel("fecha");
		lblFecha.setBounds(5, 352, 46, 14);
		contentPane.add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(104, 337, 135, 32);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		
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
		//m = new Movimiento();
		
		ArrayList<String> sucursales = m.getSucursales();
		DefaultComboBoxModel<String> modelo;
		if(causaElegida.equals("1") ||causaElegida.equals("2") ||causaElegida.equals("3") || causaElegida.equals("4"))
		{
			origenSucursal.setEnabled(false);
			//System.out.println("causa 1,2,3 o 4");
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
		//m = new Movimiento();
		ArrayList<ArrayList<String>> almacenes = m.getAlmacenes(alm);
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		for(ArrayList<String> a:almacenes){
			//obtengo la descripcion
			modelo.addElement(a.get(1));
		}
		
		almacen.setModel(modelo);
	}
	
	private void InicializaUbicacion (String alm, JComboBox ubicacion)
	{
		//m = new Movimiento();
		ArrayList<String> ubicaciones = m.getUbicacion(alm);
		if(ubicaciones!=null){
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		for(String ub:ubicaciones){
			modelo.addElement(ub);
		}
		System.out.println(ubicaciones.get(0));
		ubicacion.setModel(modelo);
		}
	}
	
	private void InicializaArticulo (String alm)
	{
		System.out.println("alm : "+alm);
		//m = new Movimiento();
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		
		if(!causaElegida.equals("1"))
		{
			System.out.println("causa: "+ causaElegida);
			arts = m.getArticulosXalmacen(alm);
			if(arts!=null){
			
			modelo.addElement("");
			
			for(Articulos art:arts){
				modelo.addElement(art.getDesc());
				System.out.println("artDEsc"+art.getDesc());
			}
			
			articuloCombo.setModel(modelo);
			System.out.println("artcombo");
			}	
		}
		else
		{
			//si la causa es 1: son los articulos de compra
			artsCompra= m.getArticulosCompra();
			for(Articulos art:artsCompra){
				modelo.addElement(art.getDesc());
			}
			articuloCombo.setModel(modelo);
			loteCombo.setEnabled(false);
			System.out.println("lote combo flase");
		}
	}
	
	private void inicializaLote(String art)
	{
		System.out.println("art IL: "+art);
		Articulos a= m.getArtByDesc(art);
		DefaultComboBoxModel<String> modeloLote=new DefaultComboBoxModel<>();
		modeloLote.addElement("");
		modeloLote.addElement("Indistinto");
		if(!causaElegida.equals("1") && a!=null)
		{
			System.out.println("a!=null");
			for(Integer lote:a.getLote()){
			modeloLote.addElement(lote.toString());
		}
		loteCombo.setModel(modeloLote);
		}
	}
	
	private void inicializaUM(String art)
	{
		Articulos a= m.getArtByDesc(art);
		if(a!=null)
		{String desc= a.getUM().getDesc();
		 lbUm.setText(desc);}
	}

	private Boolean checkContenido()
	{
		 if((causaElegida.equals("1") && !txtCantidad.getText().equals("")) || (!txtCantidad.getText().equals("") && lote!=null))
			return true;
		else
			return false;
		 
	}
}
