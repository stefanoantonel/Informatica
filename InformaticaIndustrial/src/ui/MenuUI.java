package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;

import modelo.AgregaRelacion;
import modelo.Arbol;
import modelo.EliminaRelacion;

import modelo.Jtree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUI frame = new MenuUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 435);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdministracionDeBom = new JLabel("Administracion de BOM");
		lblAdministracionDeBom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblAdministracionDeBom.setBounds(191, 11, 213, 51);
		contentPane.add(lblAdministracionDeBom);
		
		JButton explosionar = new JButton("Explosionar");
		explosionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arbol ar=new Arbol();
				ar.MostrarArbol();
			}
		});
		explosionar.setBounds(50, 100, 143, 67);
		contentPane.add(explosionar);
		
		
		
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminaRelacion elim=new EliminaRelacion();
				elim.InicializarPadre();
				MenuUI.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(52, 240, 141, 67);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Agregar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregaRelacion ag=new AgregaRelacion();
				ag.InicilizarUI();
				MenuUI.this.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(339, 240, 143, 67);
		contentPane.add(btnNewButton_2);
		setLocationRelativeTo(null);
	}
}
