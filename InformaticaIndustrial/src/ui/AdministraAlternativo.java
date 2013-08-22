package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

import modelo.Articulos;
import modelo.Nodo;
import javax.swing.JScrollPane;

public class AdministraAlternativo extends JFrame {

	private JPanel contentPane;

	public AdministraAlternativo(String art) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 211, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLosAlternativosSon = new JLabel("Los Alternativos Son:");
		lblLosAlternativosSon.setBounds(5, 5, 424, 14);
		contentPane.add(lblLosAlternativosSon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 49, 103, 119);
		contentPane.add(scrollPane);
		
		final JList<String> listAlternativos = new JList<>();
		scrollPane.setViewportView(listAlternativos);
		
		//contentPane.add(listAlternativos);
		//contentPane.add(scrollPane);
		
		Nodo n=new Nodo();
		ArrayList<String> articulos=n.getAlternativos(art);
		int i=0;
		DefaultListModel<String> modelo=new DefaultListModel<>();
		for (String s:articulos){
//			s=articulos.get(i);
			modelo.addElement(s);
			i++;
		}
		listAlternativos.setModel(modelo);
		//scrollPane.setViewportView(listAlternativos);
	}
}
