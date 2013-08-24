package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.math.BigInteger;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import modelo.CargaStock;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CargaStockUI extends JFrame {

	private JPanel contentPane;
	private JTextField plano;
	private JTextField cantidad;
	int cant=-1;
	int codigoPlano=-1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargaStockUI frame = new CargaStockUI();
					frame.setVisible(true);
					
//					System.out.println(b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CargaStockUI() {
		setTitle("Carga de Stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigoDelArticulo = new JLabel("Codigo del articulo ");
		lblCodigoDelArticulo.setBounds(10, 73, 127, 14);
		
		plano = new JTextField();
		plano.setBounds(146, 70, 94, 20);
		plano.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 117, 127, 14);
		
		cantidad = new JTextField();
		cantidad.setBounds(146, 114, 94, 20);
		cantidad.setColumns(10);
		
		JLabel lblCargaDeStock = new JLabel("Carga de Stock");
		lblCargaDeStock.setBounds(89, 11, 114, 32);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(96, 173, 85, 23);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(controlar()==true){
					CargaStock cs=new CargaStock(cant, codigoPlano);
				}
				
				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(lblCodigoDelArticulo);
		contentPane.add(lblCantidad);
		contentPane.add(cantidad);
		contentPane.add(plano);
		contentPane.add(btnCargar);
		contentPane.add(lblCargaDeStock);
	}
	
	private boolean controlar(){
		
		try{
			if(plano.getText().length()<4||plano.getText().length()>4){
				
				JOptionPane.showMessageDialog(null, "codigo debe ser de 4 digitos");
				return false;
			}
			try {
				
				codigoPlano=Integer.parseInt(plano.getText());
				cant=Integer.parseInt(cantidad.getText());
				if(cant<0){
					JOptionPane.showMessageDialog(null, "ingrese cantidad positiva");
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "no ingreso numeros");
				return false;
			}
			
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "datos mal ingresados");
			return false;
		}
		return true;
	}
}
