package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import persistencia.Conexion;

import modelo.AgregaArticulo;
import modelo.Arbol;
import modelo.Nodo;

public class AdministraAlternativoUI extends JFrame {

	private JPanel contentPane;
	private final JComboBox altComboBox = new JComboBox();;
	private final String[][] conversion = new String[10][10];
	private final JLabel lblCantidad = new JLabel();
	private final JLabel lblUm = new JLabel();

	public AdministraAlternativoUI(String art, String cant, String um, ArrayList<String> altDesc, String[][] conv) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		for (int k=0; k<10; k++)
		   {this.conversion[k][0]=conv[k][0];
		   this.conversion[k][1]=conv[k][1];
		   this.conversion[k][2]=conv[k][2];
		   System.out.println("CONVERSION 1: "+k+"  "+this.conversion[k][1] );
		   this.conversion[k][3]=conv[k][3];
		   }
		
		JLabel lblLosAlternativosSon = new JLabel("Los Alternativos Son:");
		lblLosAlternativosSon.setBounds(5, 5, 424, 14);
		contentPane.add(lblLosAlternativosSon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 49, 173, 178);
		contentPane.add(scrollPane);
		
		final JList<String> listAlternativos = new JList<>();
		scrollPane.setViewportView(listAlternativos);
		
	
		//contentPane.add(listAlternativos);
		//contentPane.add(scrollPane);
		
		Nodo n=new Nodo();
		ArrayList<String> articulos=n.getAlternativos(art);
		int i=0;
		DefaultListModel<String> modelo=new DefaultListModel<>();
		for (String s:articulos){
//			s=articulos.get(i);
			modelo.addElement(s);
			i++;
		}
		listAlternativos.setModel(modelo);
		
		
		
		
		
		listAlternativos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int respuesta= JOptionPane.showConfirmDialog(null, "¿Desea Cambiar el valor por defecto?");
				if(respuesta==0) //Dijo que si
				{
//					String selec = listAlternativos.getSelectedValue();
//					System.out.println(selec);
//					Conexion cn = new Conexion();
//					Connection con = cn.getConexion();
//					String sentencia = ""
					
					
				}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		lblCantidad.setText(cant);
		lblCantidad.setBounds(34, 273, 46, 14);
		contentPane.add(lblCantidad);
		
		//final JComboBox altComboBox = new JComboBox();
		
		altComboBox.setBounds(164, 270, 78, 20);
		contentPane.add(altComboBox);
		//cargo el comboBox con la lista de alternativos que le pase por parametro
		for(String s:altDesc){
			altComboBox.addItem(s);
		}
		
		
		altComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s=(altComboBox.getSelectedItem().toString());
				System.out.println("Selecionadooo : "+s);
				for (int h=0; h<5; h++)
				{
					System.out.println("Conversion: "+conversion[h][2]);
					if (s.equalsIgnoreCase(conversion[h][2]))
					{
						lblUm.setText(conversion[h][2]);
						CalcCant(conversion[h][3]);
					}
				}
			}
		});
		
		
		JLabel lblCantidad_1 = new JLabel("Cantidad: ");
		lblCantidad_1.setBounds(5, 248, 75, 14);
		contentPane.add(lblCantidad_1);
		
		lblUm.setText(um);
		lblUm.setBounds(90, 273, 46, 14);
		contentPane.add(lblUm);
		//scrollPane.setViewportView(listAlternativos);
		
	}
	
	private final void CalcCant(String factor)
	{
		String cantActual = lblCantidad.getText();
		System.out.println("factor: ---" +factor);
		System.out.println("cantActual: ---" +cantActual);
		Float cantNueva = Float.parseFloat(cantActual)*Float.parseFloat(factor);
		lblCantidad.setText(cantNueva.toString());
	}

	
}
