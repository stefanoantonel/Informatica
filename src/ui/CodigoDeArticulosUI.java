package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class CodigoDeArticulosUI extends JFrame {

	private JPanel contentPane;
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
	
	
//	public CodigoDeArticulos(DefaultTableModel modelo) {
	public CodigoDeArticulosUI(DefaultListModel<String> modelo1) {
		setTitle("Codigo de Barras");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		Font f=new Font("3 of 9 Barcode", 0, 20);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 11, 209, 239);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<>();
		scrollPane.setViewportView(list);
		list.setFont(new Font("3 of 9 Barcode", Font.PLAIN, 24));
		list.setModel(modelo1);
		this.setVisible(true);
		
	}
	
	
	public void mostrar(){
		
	}
}
