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
//	static RemitoDAO remDao;
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
		if(remitop.getEstado()==10){
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		else{
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
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
				
				String comp=codigo.getText();
				String co=remitop.rellenar(comp); //para que si o si el codigo sea de 12 digitos
				boolean concordancia=remitop.verificarConcordancia(co); //que lo que ingrese este en lo que pidio
				if(concordancia==true){
					boolean b=remitop.verificarExistencia(co);	//que este disponible segun el estado del articulo
					if(b==true){
						System.out.println("si esta");
						agregarALista(co);
					}
					else{
						JOptionPane.showMessageDialog(null, "articulo no disponible");
						System.out.println("no esta");
					}
					
				}
				else
					return;
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
				if(remitop.getEstado()==11){
					remitop.updateRemito(listaParaDespachar,remitop.getIdRemito());
				}
				else{
					remitop.guardarRemito(listaParaDespachar);
				}
				
				
				int resp=JOptionPane.showConfirmDialog(null, "Desea despacharlo?");
				if(resp==0){
					JOptionPane.showMessageDialog(null, "sii");
					
					if(remitop.verificarFinalizacionPedido(listaParaDespachar)==true){ //coincide lo pedido con lo ingresado en el PIP..
						remitop.guardarRemitoDespachado();
						remitop.despachado(listaParaDespachar);
					}
					
				}
				if(resp==1){
					remitop.guardarRemitoEspera(); //En espera
					remitop.espera(listaParaDespachar); //esta en espera
					RemitoUI.this.setVisible(false);
					return;
				}
				if(resp==2){
					return;
				}
				
				
			}
		});
		btnFinalizar.setBounds(271, 218, 89, 23);
		contentPane.add(btnFinalizar);
		
		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getModel().getSize()>0){ //si tiene articulos 
					JOptionPane.showMessageDialog(null, "Debe eliminar los articulos de la lista");
					return;
				}
				else{
					remitop.guardarRemitoActivo();
					RemitoUI.this.setVisible(false);
				}
				
			}
		});
		btnDescartar.setBounds(271, 173, 89, 23);
		contentPane.add(btnDescartar);
		
		setLocationRelativeTo(null);
	}
	
	private void eliminarDeLista(int indice){
		DefaultListModel<String> mod=new DefaultListModel<>();
		ListModel<String> m=list.getModel();
		String art=list.getModel().getElementAt(indice);
		for(int i=0;i<m.getSize();i++){
			mod.addElement(m.getElementAt(i));
		}
		mod.remove(indice);
		if(remitop.getEstado()==11){
			
			remitop.liberarArticulo(art);
		}
		
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
	
	public void agregarCargados(){
		ArrayList<String> e=remitop.getArticulosCargados();
		DefaultListModel<String> d=new DefaultListModel<>();
		for(String s:e){
			d.addElement(s);
		}
		list.setModel(d);
	}
}
