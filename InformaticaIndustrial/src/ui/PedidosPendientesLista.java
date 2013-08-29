package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Remito;
import persistencia.RemitoDAO;

public class PedidosPendientesLista extends JFrame {

	private JPanel contentPane;
	JList list = new JList();
	RemitoDAO dao=new RemitoDAO();
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PedidosPendientesLista frame = new PedidosPendientesLista(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public PedidosPendientesLista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Remito r=dao.getElecciones((Integer)list.getSelectedValue());
			}
		});
		
		
		contentPane.add(list, BorderLayout.CENTER);
		
		
	}
	public void inicializarUI(){
		
		ArrayList<Integer>in=dao.obtenerRemitosPendientes();
		DefaultListModel<Integer> dm=new DefaultListModel<>();
		for(Integer i:in){
			dm.addElement(i);
		}
		list.setModel(dm);
		this.setVisible(true);
	}

}
