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
import javax.swing.JScrollPane;

public class PedidosPendientesListaUI extends JFrame {

	private JPanel contentPane;
	JList list = new JList();
	RemitoDAO dao=new RemitoDAO();
	

	public PedidosPendientesListaUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		list.setValueIsAdjusting(true);
		
		JScrollPane scrollPane = new JScrollPane(list);
		contentPane.add(scrollPane);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if ( !arg0.getValueIsAdjusting() && ! list.isSelectionEmpty()){
					//tengo que sacar el id del string con todo
					
					String seleccion=(String)list.getSelectedValue();
					String split[]=seleccion.split("-");
					int remitoId=Integer.parseInt(split[1].trim());
					
					dao.getElecciones(remitoId);
				}
				
			}
		});
		
		
		//contentPane.add(list, BorderLayout.CENTER);
		
		
	}
	public void inicializarUI(){
		
		dao.cargarRemitosPendientes();
		ArrayList<Integer> pedidosPendId =dao.getIdRemitosPendientes();
		ArrayList<String> fechaPedidosPendientes =dao.getFechaRemitoPendiente();
		DefaultListModel<String> modelo=new DefaultListModel<>();
		int j=0;
		for(Integer id:pedidosPendId){
			String m= "Remito - "+ id + " - Fecha: " + fechaPedidosPendientes.get(j);
			modelo.addElement(m);
			j++;
		}
		list.setModel(modelo);
		this.setVisible(true);
	}

}
