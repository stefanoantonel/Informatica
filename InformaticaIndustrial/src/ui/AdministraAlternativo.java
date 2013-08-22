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

public class AdministraAlternativo extends JFrame {

	private JPanel contentPane;

	public AdministraAlternativo(String art) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLosAlternativosSon = new JLabel("Los Alternativos Son:");
		lblLosAlternativosSon.setBounds(5, 5, 424, 14);
		contentPane.add(lblLosAlternativosSon);
		
		final JList<String> listAlternativos = new JList<>();
		listAlternativos.setBounds(43, 49, 103, 119);
		contentPane.add(listAlternativos);
		Articulos arti=new Articulos();
		ArrayList<String> articulos=arti.ObtenerArticulos();
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
