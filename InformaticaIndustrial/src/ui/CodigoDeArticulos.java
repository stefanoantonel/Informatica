package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CodigoDeArticulos extends JFrame {

	private JPanel contentPane;
	private JTable table;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CodigoDeArticulos frame = new CodigoDeArticulos(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	
	
	public CodigoDeArticulos(DefaultTableModel modelo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		table = new JTable();
		table.setBounds(5, 11, 424, 245);
		Font f=new Font("3 of 9 Barcode", 0, 10);
		contentPane.setLayout(null);
		table.setFont(f);
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"Codigo Barra"
//			}
//		));
		table.setModel(modelo);
		contentPane.add(table);
		this.setVisible(true);
		
	}
	
	
	public void mostrar(){
		
	}

}
