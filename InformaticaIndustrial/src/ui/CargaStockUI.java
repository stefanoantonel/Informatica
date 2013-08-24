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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigoDelArticulo = new JLabel("Codigo del articulo ");
		lblCodigoDelArticulo.setBounds(15, 68, 91, 14);
		
		plano = new JTextField();
		plano.setBounds(110, 65, 94, 20);
		plano.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(15, 114, 43, 14);
		
		cantidad = new JTextField();
		cantidad.setBounds(110, 114, 94, 20);
		cantidad.setColumns(10);
		
		JLabel lblCargaDeStock = new JLabel("Carga de Stock");
		lblCargaDeStock.setBounds(176, 5, 114, 32);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(315, 64, 65, 23);
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int cant;
				int codigoPlano;
				try{
					codigoPlano=Integer.parseInt(plano.getText());
					cant=Integer.parseInt(cantidad.getText());
					
				}
				catch(Exception e){
					JOptionPane.showConfirmDialog(null, "datos mal ingresados");
					return;
				}
				CargaStock cs=new CargaStock(cant, codigoPlano);
				
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
}
