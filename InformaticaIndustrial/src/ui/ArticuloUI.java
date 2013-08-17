package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.Arbol;
import modelo.Nodo;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;

public class ArticuloUI extends JFrame {
	private JTextField nombreArticulo;
	private JTextField codigoArticulo;
	Nodo articuloSelecc;
	private JTextField cantidadParaProducir;

	public Nodo getArticuloSelecc() {
		return articuloSelecc;
	}

	public void setArticuloSelecc(Nodo articuloSelecc) {
		this.articuloSelecc = articuloSelecc;
	}

	public ArticuloUI() {
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

		JButton btnExplosionTotal = new JButton("Explosion");
		btnExplosionTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Arbol arbol = new Arbol();
				arbol.MostrarArbol(articuloSelecc);
			}
		});
		btnExplosionTotal.setBounds(424, 67, 143, 67);
		getContentPane().add(btnExplosionTotal);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 277, 226, 71);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblCuantoEsNecesario = new JLabel("Cuanto es necesario para producir");
		lblCuantoEsNecesario.setBounds(6, 11, 210, 19);
		panel.add(lblCuantoEsNecesario);
		
		cantidadParaProducir = new JTextField();
		cantidadParaProducir.setBounds(6, 41, 86, 20);
		panel.add(cantidadParaProducir);
		cantidadParaProducir.setColumns(10);
		
		JButton calcularNecesarioProducir = new JButton("Calcular");
		calcularNecesarioProducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arbol a = new Arbol();
				a.MostrarArbol();
			}
		});
		calcularNecesarioProducir.setBounds(127, 42, 89, 19);
		panel.add(calcularNecesarioProducir);

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
