package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import modelo.Arbol;

public class ExplosionUI extends JFrame {

	private JPanel contentPane;

	private JTree tree;//stefanostyefano


	public ExplosionUI(DefaultTreeModel modelo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 568, 371);//jhkj
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 41, 318, 238);
		contentPane.add(scrollPane);
		
		JTree tree_1 = new JTree();
		scrollPane.setViewportView(tree_1);
		tree_1.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				
//				int eleccion=JOptionPane.showConfirmDialog(null, "Desea Implosionar?");
//				if(eleccion==0){
//					
//				}
			}
		});
		tree_1.setModel(modelo);
				
//		this.setVisible(true);
		
	}
}

