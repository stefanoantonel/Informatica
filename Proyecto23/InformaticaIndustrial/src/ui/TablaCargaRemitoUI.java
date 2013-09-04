package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
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
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class TablaCargaRemitoUI extends JFrame {

	private JPanel contentPane;
	private JTable tablaCarga= new JTable();
	int cantidadFilas=-1;
	DefaultTableModel modeloTabla=new DefaultTableModel();
	TablaCargaRemito carga=new TablaCargaRemito();
	JList<String> list = new JList();
	DefaultListModel<String> modeloLista=new DefaultListModel<>();
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
		setBounds(100, 100, 722, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tablaCarga.setBounds(234, 57, 442, 309);
		contentPane.add(tablaCarga);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(441, 377, 235, 23);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				carga.recorrerTabla(tablaCarga.getModel());
			}
		});
		contentPane.add(btnContinuar);
		
		JButton button = new JButton(">>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String [] articulosElccion=(String[])list.getSelectedValues();
				ArrayList<String>artSelecc=(ArrayList<String>)list.getSelectedValuesList();
				ArrayList<String>codigoArt=new ArrayList<>();
				codigoArt=carga.obtenerCodigoPlano(artSelecc);
				cargaModelo(codigoArt);
				
			}
		});
		button.setBounds(185, 189, 49, 23);
		contentPane.add(button);
		
		
		list.setBounds(10, 57, 169, 309);
		contentPane.add(list);
		
		JLabel lblSeleccioneAriculos = new JLabel("Seleccione Ariculos ");
		lblSeleccioneAriculos.setBounds(10, 32, 169, 14);
		contentPane.add(lblSeleccioneAriculos);
		
		JLabel lblIngreseCantidades = new JLabel("Ingrese Cantidades");
		lblIngreseCantidades.setBounds(234, 32, 110, 14);
		contentPane.add(lblIngreseCantidades);
		
		
		//cargaModelo();
		cargaLista();
	}
	
//	private void cargaModelo(){
//		
//		this.cantidadFilas=carga.preguntarCarga();
//		modeloTabla.setRowCount(cantidadFilas+1);
//		modeloTabla.setColumnCount(2);
//		modeloTabla.setValueAt("Codigo Articulo", 0, 0);
//		modeloTabla.setValueAt("Cantidad", 0, 1);
//		this.tablaCarga.setModel(modeloTabla);
//		this.setVisible(true);
//		
//	}
	
	private void cargaModelo(ArrayList<String> codigoPlano){
		
		modeloTabla.setRowCount(codigoPlano.size()+1);
		modeloTabla.setColumnCount(2);
		modeloTabla.setValueAt("Codigo Articulo", 0, 0);
		modeloTabla.setValueAt("Cantidad", 0, 1);
		int i=1;
		for(String s:codigoPlano){
			modeloTabla.setValueAt(s, i, 0);
			i++;
		}
		
		
		this.tablaCarga.setModel(modeloTabla);
		this.setVisible(true);
		
	}
	private void cargaLista(){
		ArrayList<String> listaArticulo = new ArrayList<>();
		
		listaArticulo= carga.cargaArticulosLista();
		
		
		for (String art : listaArticulo){
			modeloLista.addElement(art);
		}
		list.setModel(modeloLista);
		this.setVisible(true);
	}
}
