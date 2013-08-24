package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Remito;
import persistencia.RemitoDAO;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.AbstractListModel;

public class RemitoUI extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	JList<String> list = new JList<>();
	
	static RemitoDAO remDao;
	static Remito remitop;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemitoUI frame = new RemitoUI();
					frame.setVisible(true);
					
					remDao =new RemitoDAO();
					remDao.cargar();
					
					remitop=new Remito(remDao);
					
//					int a=33;
//					String b=Integer.toBinaryString(a);
//					System.out.println("binario: "+b);
//					int c=Integer.parseInt(b,2);
//					System.out.println("int: "+c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemitoUI() {
		setTitle("Remito");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 386, 315);
		contentPane = new JPanel();
//		contentPane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigoBarra = new JLabel("Codigo");
		lblCodigoBarra.setBounds(10, 22, 62, 48);
		contentPane.add(lblCodigoBarra);
		
		codigo = new JTextField();
		codigo.setBounds(82, 36, 166, 20);
		contentPane.add(codigo);
		codigo.setColumns(20);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(278, 35, 82, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String co=codigo.getText();
				boolean b=remitop.verificarExistencia(co);
				if(b==true){
					System.out.println("si esta");
					agregarALista(co);
				}
				else{
					JOptionPane.showMessageDialog(null, "articulo no disponible");
					System.out.println("no esta");
				}
				
			}
		});
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 79, 166, 162);
		contentPane.add(scrollPane);
		
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				ListModel<String> listModddel;
//				listModddel=list.getModel();
//				
//				String a=list.getSelectedValue();
//				DefaultListModel<String> mo=new DefaultListModel<>();
//				mo.removeElement(a);
//				list.setModel(mo);
			}
		});
		
		scrollPane.setViewportView(list);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resp=JOptionPane.showConfirmDialog(null, "Desea despacharlo?");
				if(resp==0){
					JOptionPane.showMessageDialog(null, "sii");
					ArrayList<String> listaParaDespachar=new ArrayList<>();
					ListModel<String> m=list.getModel();
					int tam=m.getSize();
					for(int i=0;i<tam;i++){
						listaParaDespachar.add(m.getElementAt(i));
					}
					
					remitop.despachado(listaParaDespachar);
				}
				if(resp==1){
					ArrayList<String> listaParaEspera=new ArrayList<>();
					ListModel<String> m=list.getModel();
					int tam=m.getSize();
					for(int i=0;i<tam;i++){
						listaParaEspera.add(m.getElementAt(i));
					}
					
					remitop.espera(listaParaEspera);
				}
				
			}
		});
		btnFinalizar.setBounds(271, 218, 89, 23);
		contentPane.add(btnFinalizar);
		
		setLocationRelativeTo(null);
	}
	
	private void eliminarDeLista(int indice){
		DefaultListModel<String> mod=new DefaultListModel<>();
		ListModel<String> m=list.getModel();
		
		for(int i=0;i<m.getSize();i++){
			mod.addElement(m.getElementAt(i));
		}
		mod.remove(indice);
		list.setModel(mod);
	}
	private void agregarALista(String codigo){
		DefaultListModel<String> mod=new DefaultListModel<>();
		ListModel<String> m=list.getModel();
		int tam=m.getSize();
		for(int i=0;i<tam;i++){
			mod.addElement(m.getElementAt(i));
		}
		mod.addElement(codigo);
		list.setModel(mod);
	}
}
