package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.FlowLayout;
import java.awt.GridLayout;

public class MovimientoStockUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovimientoStockUI frame = new MovimientoStockUI();
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
	public MovimientoStockUI() {
		setTitle("Movimiento de Stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblOrigen = new JLabel("Origen");
		contentPane.add(lblOrigen);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel lblDestino = new JLabel("Destino");
		contentPane.add(lblDestino);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JLabel lblCantidadAMover = new JLabel("Cantidad a mover");
		contentPane.add(lblCantidadAMover);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblProducto = new JLabel("Producto");
		contentPane.add(lblProducto);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JLabel lblLote = new JLabel("Lote");
		contentPane.add(lblLote);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JLabel lblCausa = new JLabel("Causa");
		contentPane.add(lblCausa);
		
		JComboBox causas = new JComboBox();
		contentPane.add(causas);
		
		JLabel lblNota = new JLabel("Nota");
		contentPane.add(lblNota);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_4 = new JLabel("");
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("");
		contentPane.add(label_7);
	}

}
