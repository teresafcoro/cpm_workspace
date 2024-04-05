package igu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * VentanaRegistro de la TPV de comida rapida McDonald's España
 * En esta ventana el usuario ha de registrarse e indicar como quiere realizar el pedido
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class VentanaRegistro extends JDialog {

	// Version
	private static final long serialVersionUID = 1L;

	// Componentes de la interfaz
	private JPanel pnContenidos;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txNombre;
	private JLabel lbAño;
	private JComboBox<String> cbAño;
	private JLabel lbPassword;
	private JPasswordField passwordField;
	private JLabel lbPassword1;
	private JPasswordField passwordField1;
	private JPanel pnPedido;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdLocal;
	private JRadioButton rdLlevar;
	private JButton btSiguiente;
	private JButton btCancelar;
	
	// Atributo
	private double totalAPagar;
	
	// VentanaPrincipal de la interfaz
	private VentanaPrincipal vp;
	
	/**
	 * Constructor de la interfaz
	 * @param vp, VentanaPrincipal
	 */
	public VentanaRegistro(VentanaPrincipal vp) {
		this.vp = vp;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España: Registro del cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 500, 355);
		setLocationRelativeTo(null);
		
		pnContenidos = new JPanel();
		pnContenidos.setBackground(Color.WHITE);
		pnContenidos.setLayout(null);
		setContentPane(pnContenidos);
		pnContenidos.add(getPnDatosCliente());
		pnContenidos.add(getPnPedido());
		pnContenidos.add(getBtSiguiente());
		pnContenidos.add(getBtCancelar());
	}
	
	/**
	 * Inicializa el panel de datos 
	 * @return pnDatosCliente, JPanel
	 */
	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosCliente.setBackground(Color.WHITE);
			pnDatosCliente.setBounds(30, 10, 440, 180);
			pnDatosCliente.setLayout(null);
			pnDatosCliente.add(getLbNombre());
			pnDatosCliente.add(getTxNombre());
			pnDatosCliente.add(getLbAño());
			pnDatosCliente.add(getCbAño());
			pnDatosCliente.add(getLbPassword());
			pnDatosCliente.add(getPasswordField());
			pnDatosCliente.add(getLbReintroduzcaP());
			pnDatosCliente.add(getPasswordField1());
		}
		return pnDatosCliente;
	}
	
	/**
	 * Inicializa el mensaje de Nombre y Apellidos del cliente
	 * @return lbNombre, JLabel
	 */
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre y Apellidos:");
			lbNombre.setDisplayedMnemonic('N');
			lbNombre.setLabelFor(getTxNombre());
			lbNombre.setBackground(Color.WHITE);
			lbNombre.setBounds(25, 30, 130, 25);
			lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbNombre;
	}
	
	/**
	 * Inicializa el cuadro de texto de Nombre y Apellidos del cliente
	 * @return txNombre, JTextField
	 */
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBackground(Color.WHITE);
			txNombre.setBounds(195, 30, 215, 25);
			txNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	
	/**
	 * Inicializa el mensaje de Año de nacimiento del cliente 
	 * @return lbAño, JLabel
	 */
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("Año de nacimiento:");
			lbAño.setLabelFor(getCbAño());
			lbAño.setDisplayedMnemonic('A');
			lbAño.setBackground(Color.WHITE);
			lbAño.setBounds(25, 60, 130, 25);
			lbAño.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbAño;
	}
	
	/**
	 * Inicializa el comboBox con los años posibles de nacimiento del cliente
	 * @return cbAño, JComboBox<String>
	 */
	private JComboBox<String> getCbAño() {
		if (cbAño == null) {
			String[] años = new String[90];
			for (int i = 0; i < 90; i++)
				años[i] = "" + ((90-i)+1920);
			cbAño = new JComboBox<String>();
			cbAño.setBackground(SystemColor.control);
			cbAño.setBounds(195, 60, 215, 25);
			cbAño.setModel(new DefaultComboBoxModel<String>(años));
			cbAño.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return cbAño;
	}
	
	/**
	 * Inicializa el mensaje de contraseña
	 * @return lbPassword, JLabel
	 */
	private JLabel getLbPassword() {
		if (lbPassword == null) {
			lbPassword = new JLabel("Password:");
			lbPassword.setDisplayedMnemonic('P');
			lbPassword.setLabelFor(getPasswordField());
			lbPassword.setBackground(Color.WHITE);
			lbPassword.setBounds(25, 95, 90, 25);
			lbPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbPassword;
	}
	
	/**
	 * Inicializa el cuadro para establecer una contraseña
	 * @return passwordField, JPasswordField
	 */
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBackground(Color.WHITE);
			passwordField.setBounds(195, 95, 215, 25);
			passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return passwordField;
	}
	
	/**
	 * Inicializa el mensaje de reintroduccion de la contraseña
	 * @return lbPassword1, JLabel
	 */
	private JLabel getLbReintroduzcaP() {
		if (lbPassword1 == null) {
			lbPassword1 = new JLabel("Reintroduzca Password:");
			lbPassword1.setLabelFor(getPasswordField1());
			lbPassword1.setDisplayedMnemonic('R');
			lbPassword1.setBackground(Color.WHITE);
			lbPassword1.setBounds(25, 130, 150, 25);
			lbPassword1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return lbPassword1;
	}
	
	/**
	 * Inicializa el segundo cuadro para establecer una contraseña
	 * @return passwordField1, JPasswordField
	 */
	private JPasswordField getPasswordField1() {
		if (passwordField1 == null) {
			passwordField1 = new JPasswordField();
			passwordField1.setBackground(Color.WHITE);
			passwordField1.setBounds(195, 130, 215, 25);
			passwordField1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return passwordField1;
	}
	
	/**
	 * Inicializa el panel de tipo de pedido
	 * @return pnPedido, JPanel
	 */
	private JPanel getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JPanel();
			pnPedido.setBackground(Color.WHITE);
			pnPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnPedido.setBounds(30, 210, 200, 70);
			pnPedido.setLayout(null);
			pnPedido.add(getRdLocal());
			pnPedido.add(getRdLlevar());
		}
		return pnPedido;
	}
	
	/**
	 * Inicializa el radio boton de llevar el pedido
	 * @return rdLlevar, JRadioButton
	 */
	private JRadioButton getRdLlevar() {
		if (rdLlevar == null) {
			rdLlevar = new JRadioButton("Llevar");
			rdLlevar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rdLlevar.setMnemonic('r');
			rdLlevar.setBackground(Color.WHITE);
			buttonGroup.add(getRdLlevar());
			rdLlevar.setBounds(107, 27, 65, 23);
		}
		return rdLlevar;
	}
	
	/**
	 * Inicializa el radio boton de recoger en el local
	 * @return rdLocal, JRadioButton
	 */
	private JRadioButton getRdLocal() {
		if (rdLocal == null) {
			rdLocal = new JRadioButton("Local");
			rdLocal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			rdLocal.setMnemonic('L');
			rdLocal.setSelected(true);
			rdLocal.setBackground(Color.WHITE);
			buttonGroup.add(rdLocal);
			rdLocal.setBounds(17, 27, 65, 23);
		}
		return rdLocal;
	}
	
	/**
	 * Inicializa el boton siguiente
	 * Se encarga de mostrar la VentanaConfirmacion
	 * si los campos fueron rellenados correctamente
	 * @return btSiguiente, JButton
	 */
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.setMnemonic('S');
			btSiguiente.setForeground(Color.WHITE);
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (todoOk())
						mostrarVentanaConfirmacion();
				}
			});
			btSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btSiguiente.setBackground(new Color(0, 128, 0));
			btSiguiente.setBounds(270, 285, 90, 25);
		}
		return btSiguiente;
	}
	
	/**
	 * Comprueba que los campos se rellenen correctamente
	 * @return true, si asi es
	 * false y cuadro de dialogo en otro caso
	 */
	private boolean todoOk() {
		String nombre = getTxNombre().getText();
		String pass1 = String.valueOf(getPasswordField().getPassword());
		String pass2 = String.valueOf(getPasswordField1().getPassword());
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe introducir su nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
			getTxNombre().grabFocus();
			return false;
		} else if ((!pass1.equals(pass2)) || (pass1.equals(""))) {
			JOptionPane.showMessageDialog(null, "Contraseñas mal introducidas", "ERROR", JOptionPane.ERROR_MESSAGE);
			getPasswordField().grabFocus();
			return false;
		} else
			return true;
	}
	
	/**
	 * Hace visible la VentanaConfirmacion de la TPV
	 */
	private void mostrarVentanaConfirmacion() {
		VentanaConfirmacion vc = new VentanaConfirmacion(this);
		vc.getTxTotalAPagar().setText(String.format("%.2f", getTotalAPagar()) + "\u20AC");
		vc.setVisible(true);
	}
	
	/**
	 * Inicializa el boton cancelar
	 * Cierra la ventana actual
	 * @return btCancelar, JButton
	 */
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setMnemonic('C');
			btCancelar.setForeground(Color.WHITE);
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btCancelar.setBackground(Color.RED);
			btCancelar.setBounds(380, 285, 90, 25);
		}
		return btCancelar;
	}
	
	/**
	 * Obtiene el precio total a pagar
	 * @return totalAPagar, double
	 */
	private double getTotalAPagar() {
		return totalAPagar;
	}
	
	/**
	 * Asigna el precio total a pagar
	 * @param total, double
	 */
	protected void setTotalAPagar(double total) {
		this.totalAPagar = total;
	}

	/**
	 * Obtiene el objeto VentanaPrincipal 
	 * @return vp, VentanaPrincipal
	 */
	protected VentanaPrincipal getVP() {
		return this.vp;
	}

}
