package igu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Creación de una aplicación con Interfaz Gráfica de Usuario utilizando WindowBuilder
 * Ventana que toma los datos del cliente y el tipo de pedido
 * @author UO263728
 */
public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel pnContenidos;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txNombre;
	private JLabel lbAño;
	private JComboBox<String> cbAño;
	private JLabel lbPassword;
	private JPasswordField passwordField;
	private JLabel lbReintroduzcaP;
	private JPasswordField passwordField_1;
	private JPanel pnPedido;
	private JRadioButton rdLlevar;
	private JRadioButton rdLocal;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btSiguiente;
	private JButton btCancelar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame, the panel and add the elements to it.
	 */
	public VentanaRegistro() {
		setBounds(100, 100, 500, 371);
		setTitle("Datos del cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnContenidos = new JPanel();
		pnContenidos.setBackground(Color.WHITE);
		pnContenidos.setLayout(null);
		setContentPane(pnContenidos);
		pnContenidos.add(getPnDatosCliente());
		pnContenidos.add(getPnPedido());
		pnContenidos.add(getBtSiguiente());
		pnContenidos.add(getBtCancelar());
	}
	
	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosCliente.setBackground(Color.WHITE);
			pnDatosCliente.setBounds(30, 11, 426, 190);
			pnDatosCliente.setLayout(null);
			pnDatosCliente.add(getLbNombre());
			pnDatosCliente.add(getTxNombre());
			pnDatosCliente.add(getLbAño());
			pnDatosCliente.add(getCbAño());
			pnDatosCliente.add(getLbPassword());
			pnDatosCliente.add(getPasswordField());
			pnDatosCliente.add(getLbReintroduzcaP());
			pnDatosCliente.add(getPasswordField_1());
		}
		return pnDatosCliente;
	}
	
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre y Apellidos:");
			lbNombre.setBackground(Color.WHITE);
			lbNombre.setBounds(10, 32, 129, 19);
			lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lbNombre;
	}
	
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBackground(Color.WHITE);
			txNombre.setBounds(183, 29, 217, 25);
			txNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("A\u00F1o de nacimiento:");
			lbAño.setBackground(Color.WHITE);
			lbAño.setBounds(10, 61, 129, 25);
			lbAño.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lbAño;
	}
	
	private JComboBox<String> getCbAño() {
		if (cbAño == null) {
			String[] años = new String[90];
			for (int i = 0; i < 90; i++) {
				String año = "" + ((90-i)+1920);
				años[i] = año;
			}
			cbAño = new JComboBox<String>();
			cbAño.setBackground(SystemColor.control);
			cbAño.setBounds(182, 61, 218, 25);
			cbAño.setModel(new DefaultComboBoxModel<String>(años));
			cbAño.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return cbAño;
	}
	
	private JLabel getLbPassword() {
		if (lbPassword == null) {
			lbPassword = new JLabel("Password:");
			lbPassword.setBackground(Color.WHITE);
			lbPassword.setBounds(10, 97, 98, 25);
			lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lbPassword;
	}
	
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBackground(Color.WHITE);
			passwordField.setBounds(183, 97, 217, 25);
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return passwordField;
	}
	
	private JLabel getLbReintroduzcaP() {
		if (lbReintroduzcaP == null) {
			lbReintroduzcaP = new JLabel("Reintroduzca Password:");
			lbReintroduzcaP.setBackground(Color.WHITE);
			lbReintroduzcaP.setBounds(10, 133, 153, 25);
			lbReintroduzcaP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return lbReintroduzcaP;
	}
	
	private JPasswordField getPasswordField_1() {
		if (passwordField_1 == null) {
			passwordField_1 = new JPasswordField();
			passwordField_1.setBackground(Color.WHITE);
			passwordField_1.setBounds(183, 133, 217, 25);
			passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}
		return passwordField_1;
	}
	
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombre = txNombre.getText();
					String pass1 = String.valueOf(passwordField.getPassword());
					String pass2 = String.valueOf(passwordField_1.getPassword());
					if (nombre.equals(""))
						JOptionPane.showMessageDialog(null, "Debe introducir el nombre");
					else if ((!pass1.equals(pass2)) || (pass1.equals("")))
						JOptionPane.showMessageDialog(null, "Contraseñas mal introducidas");
				}
			});
			btSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btSiguiente.setForeground(Color.WHITE);
			btSiguiente.setBackground(new Color(51, 153, 0));
			btSiguiente.setBounds(224, 298, 110, 23);
		}
		return btSiguiente;
	}
	
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBackground(Color.RED);
			btCancelar.setBounds(350, 298, 110, 23);
		}
		return btCancelar;
	}
	
	private JPanel getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JPanel();
			pnPedido.setBackground(Color.WHITE);
			pnPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnPedido.setBounds(30, 212, 206, 72);
			pnPedido.setLayout(null);
			pnPedido.add(getRdLocal());
			pnPedido.add(getRdLlevar());
		}
		return pnPedido;
	}
	
	private JRadioButton getRdLocal() {
		if (rdLocal == null) {
			rdLocal = new JRadioButton("Local");
			rdLocal.setBackground(Color.WHITE);
			buttonGroup.add(rdLocal);
			rdLocal.setBounds(10, 28, 77, 23);
			rdLocal.setSelected(true);
		}
		return rdLocal;
	}
	
	private JRadioButton getRdLlevar() {
		if (rdLlevar == null) {
			rdLlevar = new JRadioButton("Llevar");
			rdLlevar.setBackground(Color.WHITE);
			buttonGroup.add(rdLlevar);
			rdLlevar.setBounds(100, 28, 77, 23);
		}
		return rdLlevar;
	}
	
}
