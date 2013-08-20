                                                                     
                                                                     
                                                                     
                                             
package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class AgregarAlternativoUI extends JFrame {

	private JPanel contentPane;
	AgregaRelacionUI relacion;
	JLabel nombreArticulo = new JLabel("");
	JList list = new JList();


	
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AgregarAlternativoUI frame = new AgregarAlternativoUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	public AgregarAlternativoUI(AgregaRelacionUI rela) {
		this.relacion=rela;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSeleecioneArticulosAlternativos = new JLabel("Seleecione articulos alternativos para: ");
		lblSeleecioneArticulosAlternativos.setBounds(19, 19, 255, 16);
		contentPane.add(lblSeleecioneArticulosAlternativos);
		
		nombreArticulo.setBounds(19, 41, 255, 16);
		contentPane.add(nombreArticulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 80, 324, 262);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Agrega alternativos");
		btnNewButton.setBounds(112, 367, 162, 26);
		contentPane.add(btnNewButton);
		
		JLabel lblpresioneCtrlY = new JLabel("(Presione CTRL y seleccione multiple)");
		lblpresioneCtrlY.setBounds(63, 59, 255, 16);
		contentPane.add(lblpresioneCtrlY);
		
		Inicializar();
	}
	
	public void Inicializar(){
		if(relacion.getListPadre().getSelectedValue()!=null){
			nombreArticulo.setText(relacion.getListPadre().getSelectedValue().toString());
			list.setModel(relacion.getModeloHijo());
			
		}
		else{
			JOptionPane.showMessageDialog(null, "selecione un padre");
		}
		
	}
}