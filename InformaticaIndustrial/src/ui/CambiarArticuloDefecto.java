package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import modelo.Articulos;
import modelo.Nodo;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class CambiarArticuloDefecto extends JFrame {

	private JPanel contentPane;
	private JTextField artDefecto;
	JList listArticulos = new JList();
	JList listAlternativos = new JList();
	String defecto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarArticuloDefecto frame = new CambiarArticuloDefecto();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CambiarArticuloDefecto() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 38, 171, 267);
		contentPane.add(scrollPane);
		listArticulos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				//obtiene el laternativo 
				String art=listArticulos.getSelectedValue().toString();
				Nodo n=new Nodo();
				ArrayList<String> li=new ArrayList<>();
				li=n.getAlternativos(art);
				//setea en el lista del alternativo todos
				DefaultListModel<String> mod=new DefaultListModel<>();
				for(String s:li){
					mod.addElement(s);
				}
				listAlternativos.setModel(mod);
//				defecto=n.ge
			}
		});
		
		
		scrollPane.setViewportView(listArticulos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(255, 134, 146, 167);
		contentPane.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(listAlternativos);
		
		JLabel lblArticulo = new JLabel("Articulo");
		lblArticulo.setBounds(24, 13, 71, 14);
		contentPane.add(lblArticulo);
		
		JLabel lblAlternativos = new JLabel("Alternativos");
		lblAlternativos.setBounds(253, 109, 71, 14);
		contentPane.add(lblAlternativos);
		
		JLabel lblArticuloPorDefecto = new JLabel("Articulo por defecto");
		lblArticuloPorDefecto.setBounds(253, 58, 120, 14);
		contentPane.add(lblArticuloPorDefecto);
		
		artDefecto = new JTextField();
		artDefecto.setEditable(false);
		artDefecto.setBounds(367, 55, 86, 20);
		contentPane.add(artDefecto);
		artDefecto.setColumns(10);
		
		JButton btnCambiarPorDefecto = new JButton("Cambiar por defecto");
		btnCambiarPorDefecto.setBounds(431, 153, 144, 35);
		contentPane.add(btnCambiarPorDefecto);
		
		
		Articulos art=new Articulos();
		ArrayList<String> articulos=new ArrayList<>();
		articulos=art.ObtenerArticulos();
		DefaultListModel<String> modeloLista=new DefaultListModel<>();
		for(String s:articulos){
			modeloLista.addElement(s);
		}
		listArticulos.setModel(modeloLista);
	}
}
