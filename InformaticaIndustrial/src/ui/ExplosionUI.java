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
	private JTree tree;//florflor

	public ExplosionUI(DefaultTreeModel modelo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 568, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree_1 = new JTree();
		tree_1.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				
				int eleccion=JOptionPane.showConfirmDialog(null, "Desea Implosionar?");
				if(eleccion==0){
					Arbol ar=new Arbol();
					String descrip=(arg0.getPath().getLastPathComponent().toString());
//					System.out.println();
					StringTokenizer st=new StringTokenizer(descrip, "-");
					descrip=st.nextToken().trim();
//					System.out.println("descrip"+descrip);
					ArrayList<StringBuilder> listaSB =new ArrayList<>();
					
					listaSB=ar.ArmaListaPadre(ar.getNodoByDescripcion(descrip));
					StringBuilder sb=new StringBuilder();
					for (int i=0;i<listaSB.size();i++){
						sb.append(listaSB.get(i));
						sb.append("\n");
					}
					JOptionPane.showMessageDialog(null,sb);
				}
			}
		});
		tree_1.setBounds(90, 41, 318, 238);
		tree_1.setModel(modelo);
		contentPane.add(tree_1);
				
//		this.setVisible(true);
		
	}
}

