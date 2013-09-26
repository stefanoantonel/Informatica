package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Movimiento;

public class DeshacerMovimientoUI extends JFrame {

	private JPanel contentPane;
	private Movimiento m;
	private JList listMovimientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeshacerMovimientoUI frame = new DeshacerMovimientoUI();
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
	public DeshacerMovimientoUI() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listMovimientos = new JList();
		listMovimientos.setBounds(72, 61, 599, 162);
		contentPane.add(listMovimientos);
		
		JLabel lblMovimientos = new JLabel("Ultimos Movimientos");
		lblMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMovimientos.setBounds(41, 25, 197, 14);
		contentPane.add(lblMovimientos);
		
		JButton btnDeshacer = new JButton("Deshacer");
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listMovimientos.getSelectedValue()!=null)
				{
					String id= listMovimientos.getSelectedValue().toString();
					id= id.split("\\)")[0];
					m.revertirCambio(id);		
					
				}
				JOptionPane.showMessageDialog(null, "Deshecho exitosamente");
			}
		});
		btnDeshacer.setBounds(556, 267, 115, 37);
		contentPane.add(btnDeshacer);
		m = new Movimiento();
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(71, 61, 599, 162);
//		contentPane.add(scrollPane);
//		scrollPane.setViewportView(listMovimientos);
//		listMovimientos.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				
//			}
//		});
		
		
		
		InicializaListaModelo();
	}
	
	public void InicializaListaModelo()
	{
		DefaultListModel<String> modeloMov = new DefaultListModel<>();
		ArrayList<String> ultMov= m.obtenerUltimosMov();
		
		for (String i: ultMov)
		{
			modeloMov.addElement(i);
		}
		listMovimientos.setModel(modeloMov);
	}
	
	
}
