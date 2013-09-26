package ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.management.StringValueExp;
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
	public String lote;
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
	private JButton btnNewButton;

	public MovimientoStockUI() {
		setTitle("Movimiento de Stock");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 478);
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
		lblLote.setBounds(653, 193, 53, 43);
		contentPane.add(lblLote);
		
		articuloCombo = new JComboBox();
		articuloCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cantMax.setText("Max()");
				articulo=arg0.getItem().toString();
				
				inicializaLote(articulo);
				inicializaUM(articulo);
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
			}
		});
		origenSucursal.setBounds(104, 85, 204, 32);
		contentPane.add(origenSucursal);
		
		origenAlmacen = new JComboBox();
		origenAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				almacenOrigen= e.getItem().toString();
				InicializaUbicacion(almacenOrigen, origenUbicacion);
			  
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
				InicializaArticulo(ubicacionOrigen);
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
				if(causaElegida.equals("2")||causaElegida.equals("4"))
					InicializaArticulo(ubicacionDestino);
			}
		});
		destinoUbicacion.setBounds(532, 137, 240, 30);
		contentPane.add(destinoUbicacion);
		
		loteCombo = new JComboBox();
		loteCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lote= arg0.getItem().toString();
				if(lote!=null && lote!="")
				{if(!lote.equals("Indistinto") && !causaElegida.equals("3"))			
					{
					if(causaElegida.equals("2")||causaElegida.equals("4")) //destruccion o ajuste negativo
					    {
						cantidadMaxima= m.getCantidadXlote(Integer.parseInt(lote), ubicacionDestino, articulo);
						System.out.println("cantdadMaxima: "+cantidadMaxima);
					    }
					else
						{
						System.out.println("alm para CM:"+almacenOrigen);
						cantidadMaxima= m.getCantidadXlote(Integer.parseInt(lote), ubicacionOrigen, articulo);
						}
					System.out.println("cant max:"+cantidadMaxima);
					if(cantidadMaxima==null)
						{Articulos art = m.getArtByDesc(articulo);
						cantMax.setText("Max("+art.getCant()+")");}
					else
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
				String causa=e.getItem().toString();
				String selec;
				selec=(causa.split("\\)"))[0];
				causaElegida=selec;
				origenSucursal.setEnabled(true);
				origenAlmacen.setEnabled(true);
				origenUbicacion.setEnabled(true);
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
		cantMax.setBounds(563, 207, 78, 14);
		contentPane.add(cantMax);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkContenido()==false)
					{JOptionPane.showMessageDialog(null, "Debe completar los campos");
					return;
					}
				cantIngresada=Integer.parseInt(txtCantidad.getText());
				if(cantidadMaxima!=null)
				{	if( cantIngresada>cantidadMaxima)
					{
						JOptionPane.showMessageDialog(null, "No hay suficientes articulos para ese lote");
						return;
					}
				}
				fecha= txtFecha.getText();
				nota="'"+txtNota.getText()+"'";
				m.insertarMovimiento(MovimientoStockUI.this);
				
			}
		});
		btnAceptar.setBounds(653, 340, 116, 39);
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
		
		btnNewButton = new JButton("Deshacer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeshacerMovimientoUI dMov = new DeshacerMovimientoUI();
				dMov.setVisible(true);
			}
		});
		btnNewButton.setBounds(653, 390, 119, 39);
		contentPane.add(btnNewButton);
		
		
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
			origenAlmacen.setEnabled(false);
			origenUbicacion.setEnabled(false);
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
		ubicacion.setModel(modelo);
		}
	}
	
	private void InicializaArticulo (String ub)
	{
		//m = new Movimiento();
		DefaultComboBoxModel<String> modelo=new DefaultComboBoxModel<>();
		cantidadMaxima=null;
		if(!causaElegida.equals("1"))
		{
			//System.out.println("causa: "+ causaElegida);
			arts = m.getArticulosXubicacion(ub);
			if(arts!=null){
			modelo.addElement("");
			
			for(Articulos art:arts){
				modelo.addElement(art.getDesc());
			}
			
			articuloCombo.setModel(modelo);
			}	
		}
		else
		{
		
			//si la causa es 1: son los articulos de compra
			artsCompra= m.getArticulosCompra();
			modelo.addElement("");
			for(Articulos art:artsCompra){
			
				modelo.addElement(art.getDesc());
			}
			articuloCombo.setModel(modelo);
			loteCombo.setEnabled(false);
		}
	}
	
	private void inicializaLote(String art)
	{
		Articulos a= m.getArtByDesc(art);
		DefaultComboBoxModel<String> modeloLote=new DefaultComboBoxModel<>();
		modeloLote.addElement("");
		modeloLote.addElement("Indistinto");
		String ubicacion="";
		if (causaElegida.equals("5"))
			 ubicacion=ubicacionOrigen;
		else
			ubicacion=ubicacionDestino;
		if(!causaElegida.equals("1") && a!=null && a.getLote()!=null)
		{
			if(a.getLote().size()>0)
			{
				loteCombo.setEnabled(true);
				for(Integer lote:a.getLote()){
					if(m.controlLoteUbicacion(lote,ubicacion,a))
						modeloLote.addElement(lote.toString());
					
				}
			}
			else
				{
				
				 loteCombo.setEnabled(false);
				 cantMax.setText("Max("+a.getCant()+")");
				 return;
			  }
		}	
		if(causaElegida.equals("3"))//ajuste positivo: cualquier lote
			{
				for(Integer i=1;i<15;i++){
				modeloLote.addElement(i.toString());
			    }
			}	
		loteCombo.setModel(modeloLote);
		
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
		//causas: 1compra, 2destruccion, 3ajusteP, 4ajusteN, 5movimiento
		if (!causaElegida.equals("5"))
			{origenAlmacen.setModel(new DefaultComboBoxModel<>());
			origenUbicacion.setModel(new DefaultComboBoxModel<>());
			}
		
		 if(causaElegida.equals("3") || causaElegida.equals("5") && (lote==null || lote.equals("")))
			return false;

		 if ( txtCantidad.getText().equals("") || ubicacionDestino.equals(""))
			return false;
		
		 if (causaElegida.equals("5") && ubicacionOrigen.equals(""))
			return false;
			
		 return true;
		 
	}
	

	
}
