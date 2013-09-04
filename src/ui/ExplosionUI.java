package ui;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
//import modelo.AdministraAlternativo;

import modelo.AdministraAlternativo;
import modelo.Nodo;

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
				
				System.out.println("path "+arg0.getPath().getLastPathComponent());
				String art =arg0.getPath().getLastPathComponent().toString();
				String id=null;
				if(art.contains("/ "))
				{
				id=(art.split("/ "))[0];
				art = (art.split("/ "))[1];
				art = (art.split(" - "))[0];
				}
				else
				{
				 art=art.split("- Cant:")[0];	
				}
				String cant1=arg0.getPath().getLastPathComponent().toString();
				String cant = null;
				String um =null;
				if(cant1.contains("- Cant:"))
					{ cant1 = (cant1.split("- Cant:")[1]);
					  cant = (cant1.split(" ")[0]);
					  um = (cant1.split(" ")[1]);
					}
				else
				  {cant1="-";
				   cant ="-";
				   um ="-";
				  }
				
			//	System.out.println("Cant: "+cant);
				//Cola - Cant:1.0 Litros
				
				// UM 
				AdministraAlternativo adm = new AdministraAlternativo(id,art,cant,um);
				
				System.out.println("ID:"+id+" art:"+art);

			}
		});
		tree_1.setModel(modelo);
				
//		this.setVisible(true);
		
	}
}

