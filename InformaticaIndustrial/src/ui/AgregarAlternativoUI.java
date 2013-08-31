                                                                     
                                                                     
                                                                     
                                             
package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modelo.AgregaRelacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AgregarAlternativoUI extends JFrame {

	private JPanel contentPane;
	AgregaRelacionUI relacion;
	JLabel nombreArticulo = new JLabel("");
	JLabel articuloXdefecto = new JLabel("");
	JList list = new JList();
	private String padreDesc;
	private String hijoDesc;


	public AgregarAlternativoUI(AgregaRelacionUI rela,String p,String h) {
		setTitle("Agregar Alternativo");
		this.relacion=rela;
		padreDesc=p;
		hijoDesc=h;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleecioneArticulosAlternativos = new JLabel("Seleecione articulos alternativos para: ");
		lblSeleecioneArticulosAlternativos.setBounds(19, 19, 255, 16);
		contentPane.add(lblSeleecioneArticulosAlternativos);
		
		nombreArticulo.setBounds(19, 41, 255, 16);
		contentPane.add(nombreArticulo);
		

		articuloXdefecto.setBounds(19, 73, 255, 16);
		contentPane.add(articuloXdefecto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 138, 324, 262);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Agrega alternativos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregaRelacion agre=new AgregaRelacion();
				ArrayList<String> alt =(ArrayList) list.getSelectedValuesList();
			    agre.InsertarAlternativo(padreDesc, hijoDesc,alt);
				
			}
		});
		btnNewButton.setBounds(112, 424, 162, 26);
		contentPane.add(btnNewButton);
		
		JLabel lblpresioneCtrlY = new JLabel("(Presione CTRL y seleccione multiple)");
		lblpresioneCtrlY.setBounds(63, 111, 255, 16);
		contentPane.add(lblpresioneCtrlY);

		
		Inicializar();
	}
	
	public void Inicializar(){
			nombreArticulo.setText(relacion.getListPadre().getSelectedValue().toString());
			articuloXdefecto.setText("Articulo por defecto: "+relacion.getListHijo().getSelectedValue().toString());
			list.setModel(relacion.getModeloHijo());

		
	}
}