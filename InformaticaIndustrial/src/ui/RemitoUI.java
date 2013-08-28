package ui;

import java.awt.EventQueue;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Remito;
import persistencia.RemitoDAO;

public class RemitoUI extends JFrame {

	private JPanel contentPane;
	private JTextField codigo;
	JList<String> list = new JList<>();
	ArrayList<Integer> plano,cant;
	static RemitoDAO remDao;
	static Remito remitop;

	
//	public static void main() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RemitoUI frame = new RemitoUI();
//					
//					remitop=new Remito(plano, cant);
//					//remitop.preguntarCarga();
//					frame.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame. final ArrayList<Integer> plano, final ArrayList<Integer> cant
	 */
	public RemitoUI(Remito r) {
		remitop=r;
		
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
				if ( !arg0.getValueIsAdjusting() && ! list.isSelectionEmpty()){
					int r=JOptionPane.showConfirmDialog(null, "Desea Eliminar?");
					if(r==0){ //SII
						int s=list.getSelectedIndex();
						eliminarDeLista(s);
					}
				}
				
				
			}
		});
		
		scrollPane.setViewportView(list);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<String> listaParaDespachar=new ArrayList<>();
				ListModel<String> m=list.getModel();
				int tam=m.getSize();
				for(int i=0;i<tam;i++){
					listaParaDespachar.add(m.getElementAt(i));
				}
				
				remitop.guardarRemito(listaParaDespachar);
				
				int resp=JOptionPane.showConfirmDialog(null, "Desea despacharlo?");
				if(resp==0){
					JOptionPane.showMessageDialog(null, "sii");
					
					if(remitop.verificarFinalizacionPedido(listaParaDespachar)==true){ //coincide lo pedido con lo ingresado en el PIP..
						remitop.despachado(listaParaDespachar);
					}
					
				}
				if(resp==1){
					
					remitop.espera(listaParaDespachar); //esta en espera
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
			if(!m.getElementAt(i).equals(codigo)){
				mod.addElement(m.getElementAt(i));
			}
			
		}
		mod.addElement(codigo);
		list.setModel(mod);
	}
//	public void asignarPlanoyCant(ArrayList<Integer> p,ArrayList<Integer> c){
//		this.plano=p;
//		this.cant=c;
//	}
}
