package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import modelo.Arbol;
import modelo.Nodo;

public class ArticuloUI extends JFrame {
	private JTextField nombreArticulo;
	private JTextField codigoArticulo;

	NumberFormat f = NumberFormat.getNumberInstance(); 
	
	
	JFormattedTextField cantidadParaProducir = new JFormattedTextField(NumberFormat.getIntegerInstance());
	
	Nodo articuloSelecc;

	public Nodo getArticuloSelecc() {
		return articuloSelecc;
	}

	public void setArticuloSelecc(Nodo articuloSelecc) {
		this.articuloSelecc = articuloSelecc;
	}

	public ArticuloUI() {
		setTitle("Descripcon Articulos");
		
		setBounds(100, 100, 657, 448);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNombreDelArticulo = new JLabel("Nombre del articulo");
		lblNombreDelArticulo.setBounds(10, 71, 112, 14);
		getContentPane().add(lblNombreDelArticulo);

		JLabel lblDetallesDelArticulo = new JLabel("Detalles del Articulo");
		lblDetallesDelArticulo.setBounds(244, 11, 120, 14);
		getContentPane().add(lblDetallesDelArticulo);

		nombreArticulo = new JTextField();
		nombreArticulo.setEditable(false);
		nombreArticulo.setBounds(132, 65, 193, 20);
		getContentPane().add(nombreArticulo);
		nombreArticulo.setColumns(10);

		JLabel lblCodiigoArticulo = new JLabel("Codiigo articulo");
		lblCodiigoArticulo.setBounds(10, 102, 98, 14);
		getContentPane().add(lblCodiigoArticulo);

		codigoArticulo = new JTextField();
		codigoArticulo.setEditable(false);
		codigoArticulo.setBounds(132, 96, 193, 20);
		getContentPane().add(codigoArticulo);
		codigoArticulo.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 127, 98, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 277, 226, 71);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCuantoEsNecesario = new JLabel("Cuanto es necesario para producir");
		lblCuantoEsNecesario.setBounds(6, 11, 210, 19);
		panel.add(lblCuantoEsNecesario);
		
		JButton calcularNecesarioProducir = new JButton("Calcular");
		calcularNecesarioProducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float cant;
				if(cantidadParaProducir.getText().equals("")){
					cant=1;
				}
				else{
					cant =Float.parseFloat(cantidadParaProducir.getText());
				}
				
//				JOptionPane.showInputDialog(")
				
				String a=JOptionPane.showInputDialog(null,"Ingrese fecha a ver la compocision \n(YYYY-MM-DD)");
				
				if(!a.equals("")){
					
					Arbol arbol=new Arbol("'"+a+"'");
//					Arbol ar=new Arbol();
//					arbol.MostrarArbol();
					System.out.println("a:"+a);
					arbol.MostrarArbol(arbol.getNodoByDescripcion(articuloSelecc.getDescripcion()),cant);
//					ar.MostrarArbol();
				}
				else{
					Arbol arbol=new Arbol("getDate()");
//					Arbol ar=new Arbol();
//					arbol.MostrarArbol();
					System.out.println("a:"+a);
					arbol.MostrarArbol(arbol.getNodoByDescripcion(articuloSelecc.getDescripcion()),cant);
				}
				
				
				
				
				
//				Arbol a = new Arbol();
//				a.MostrarArbol(articuloSelecc,cant);
			}
		});
		calcularNecesarioProducir.setBounds(127, 42, 89, 19);
		panel.add(calcularNecesarioProducir);
		
		
		cantidadParaProducir.setBounds(16, 41, 82, 19);
//		cantidadParaProducir.
		panel.add(cantidadParaProducir);
		
		JButton btnNewButton = new JButton("Implosion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arbol ar=new Arbol();
				String descrip=articuloSelecc.getDescripcion();
//				System.out.println();
//				StringTokenizer st=new StringTokenizer(descrip, "-");
//				descrip=st.nextToken().trim();
//				System.out.println("descrip"+descrip);
				ArrayList<StringBuilder> listaSB =new ArrayList<>();
				
				listaSB=ar.ArmaListaPadre(ar.getNodoByDescripcion(descrip));
				if (!(listaSB.size()==0))
				{	
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<listaSB.size();i++){
					sb.append(listaSB.get(i));
					sb.append("\n");
				    }
				JOptionPane.showMessageDialog(null,sb);
				}
				else
					JOptionPane.showMessageDialog(null,"El articulo no tiene implosion");
					
			}
		});
		btnNewButton.setBounds(424, 266, 138, 67);
		getContentPane().add(btnNewButton);
		
		JButton btnHistorial = new JButton("Explosion");
		btnHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a=JOptionPane.showInputDialog(null,"Ingrese fecha a ver la compocision \n(YYYY-MM-DD)");
				if(!a.equals("")){

					Arbol arbol=new Arbol("'"+a+"'");
//					Arbol ar=new Arbol();
//					arbol.MostrarArbol();
					System.out.println("a:"+a);
					arbol.MostrarArbol(arbol.getNodoByDescripcion(articuloSelecc.getDescripcion()),1);
//					ar.MostrarArbol();

				}
				else
				{
					Arbol arbol=new Arbol("getDate()");
//					Arbol ar=new Arbol();
//					arbol.MostrarArbol();
					//System.out.println("a:"+a);
					arbol.MostrarArbol(arbol.getNodoByDescripcion(articuloSelecc.getDescripcion()),1);
//					ar.MostrarArbol();
				}
				
				
			}
		});
		btnHistorial.setBounds(265, 266, 138, 67);
		getContentPane().add(btnHistorial);
		
//		JButton button = new JButton("Alternativos");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				Arbol ar=new Arbol();
//				String descrip=articuloSelecc.getDescripcion();
//				ar.getAlternativos(ar.getNodoByDescripcion(descrip));
//			}
//		});
//		button.setBounds(424, 161, 138, 67);
//		getContentPane().add(button);

		//pongo en variable
		//setArticuloSelecc(eleccion);
		//InicializarCampos();
	}

	public void InicializarCampos() {
		// inicializa los campos
		nombreArticulo.setText(articuloSelecc.getDescripcion());
		codigoArticulo.setText(articuloSelecc.GetValor().toString());

	}
}
