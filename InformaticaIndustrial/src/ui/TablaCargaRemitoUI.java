package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JButton;

import modelo.TablaCargaRemito;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TablaCargaRemitoUI extends JFrame {

	private JPanel contentPane;
	private JTable tablaCarga= new JTable();
	int cantidadFilas=-1;
	DefaultTableModel modeloTabla=new DefaultTableModel();
	TablaCargaRemito carga=new TablaCargaRemito();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaCargaRemitoUI frame = new TablaCargaRemitoUI();
					
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablaCargaRemitoUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(tablaCarga, BorderLayout.CENTER);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				carga.recorrerTabla(tablaCarga.getModel());
			}
		});
		contentPane.add(btnContinuar, BorderLayout.SOUTH);
		
		
		cargaModelo();
	}
	
	private void cargaModelo(){
		
		this.cantidadFilas=carga.preguntarCarga();
		modeloTabla.setRowCount(cantidadFilas+1);
		modeloTabla.setColumnCount(2);
		modeloTabla.setValueAt("Codigo Articulo", 0, 0);
		modeloTabla.setValueAt("Cantidad", 0, 1);
		this.tablaCarga.setModel(modeloTabla);
		this.setVisible(true);
		
	}

}
