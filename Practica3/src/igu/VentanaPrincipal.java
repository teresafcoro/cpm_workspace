package igu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logica.*;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * VentanaPrincipal de la TPV de comida rapida McDonald's España
 * Contiene los elementos fundamentales para la compra de articulos
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class VentanaPrincipal extends JFrame {

	// Version
	private static final long serialVersionUID = 1L;

	// Componentes de la interfaz
	private JPanel contentPane;
	private JLabel lbLogo;
	private JLabel lbNombre;
	private JLabel lbArticulos;
	private JComboBox<Articulo> cbArticulos;
	private JLabel lbUnidades;
	private JSpinner spUnidades;
	private JButton btAñadir;
	private JLabel lbUd;
	private JTextField txUd;
	private JLabel lbPrecioPedido;
	private JTextField txtPrecio;
	private JLabel lbDescuento;
	private JTextField txDescuento;
	private JButton btSiguiente;
	private JButton btCancelar;
	
	// Clases del Package Logica
	private Carta carta;
	private Pedido pedido;

	/**
	 * Metodo principal de la aplicacion, permite su ejecucion
	 * @param args, String[]
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor de la interfaz
	 */
	public VentanaPrincipal() {
		// inicializa las clases del Package Logica necesarias
		carta = new Carta();
		pedido = new Pedido();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 555, 385);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(getLbLogo());
		contentPane.add(getLbNombre());
		contentPane.add(getLbArticulos());
		contentPane.add(getCbArticulos());
		contentPane.add(getLbUnidades());
		contentPane.add(getSpUnidades());
		contentPane.add(getBtAñadir());
		contentPane.add(getLbUd());
		contentPane.add(getTxUd());
		contentPane.add(getLbPrecioPedido());
		contentPane.add(getTxtPrecio());
		contentPane.add(getLbDescuento());
		contentPane.add(getTxDescuento());
		contentPane.add(getBtSiguiente());
		contentPane.add(getBtCancelar());
	}
	
	/**
	 * Inicializa el logo de la TPV
	 * @return lbLogo, JLabel
	 */
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel();
			lbLogo.setBackground(Color.WHITE);
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
			lbLogo.setBounds(10, 11, 193, 140);
		}
		return lbLogo;
	}
	
	/**
	 * Inicializa el nombre de la TPV 
	 * @return lbNombre, JLabel
	 */
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("McDonald's");
			lbNombre.setBackground(Color.WHITE);
			lbNombre.setFont(new Font("Arial Black", Font.PLAIN, 35));
			lbNombre.setBounds(213, 53, 237, 42);
		}
		return lbNombre;
	}
	
	/**
	 * Inicializa el mensaje Articulos 
	 * @return lbArticulos, JLabel
	 */
	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos:");
			lbArticulos.setLabelFor(getCbArticulos());
			lbArticulos.setDisplayedMnemonic('r');
			lbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbArticulos.setBounds(20, 165, 70, 25);
		}
		return lbArticulos;
	}
	
	/**
	 * Inicializa el comboBox de articulos de la carta 
	 * @return cbArticulos, JComboBox<Articulo>
	 */
	private JComboBox<Articulo> getCbArticulos() {
		if (cbArticulos == null) {		
			cbArticulos = new JComboBox<Articulo>();
			cbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			cbArticulos.setBackground(Color.WHITE);
			cbArticulos.setBounds(20, 190, 300, 30);
			cbArticulos.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
		}
		return cbArticulos;
	}
	
	/**
	 * Inicializa el mensaje de unidades a añadir
	 * @return lbUnidades, JLabel
	 */
	private JLabel getLbUnidades() {
		if (lbUnidades == null) {
			lbUnidades = new JLabel("Unidades:");
			lbUnidades.setDisplayedMnemonic('U');
			lbUnidades.setLabelFor(getSpUnidades());
			lbUnidades.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbUnidades.setBounds(340, 165, 70, 25);
		}
		return lbUnidades;
	}
	
	/**
	 * Inicializa el spinner de unidades
	 * @return spUnidades, JSpinner
	 */
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.setFont(new Font("Tahoma", Font.PLAIN, 13));
			spUnidades.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnidades.setBounds(340, 190, 60, 30);
		}
		return spUnidades;
	}
	
	/**
	 * Inicializa el boton de añadir un articulo al pedido 
	 * @return btAñadir, JButton
	 */
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("Añadir");
			btAñadir.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirAPedido();
				}
			});
			btAñadir.setMnemonic('A');
			btAñadir.setBackground(new Color(0, 128, 0));
			btAñadir.setForeground(Color.WHITE);
			btAñadir.setBounds(420, 190, 80, 25);
		}
		return btAñadir;
	}
	
	/**
	 * Añade un articulo al pedido
	 */
	private void añadirAPedido() {
		Articulo articuloSeleccionado = (Articulo) getCbArticulos().getSelectedItem();
		int unidadesSolicitadas = (int) getSpUnidades().getValue();
		getPedido().add(articuloSeleccionado, unidadesSolicitadas);
		getTxUd().setText(String.format("%d", getPedido().udArticuloEnPedido(articuloSeleccionado)));
		getTxtPrecio().setText(String.format("%.2f", getPedido().calcularTotal()) + "\u20AC");
		if (!getBtSiguiente().isEnabled())
			getBtSiguiente().setEnabled(true);
		getSpUnidades().setValue(1);
	}
	
	/**
	 * Inicializa el mensaje de las unidades en el pedido
	 * @return lbUd, JLabel
	 */
	private JLabel getLbUd() {
		if (lbUd == null) {
			lbUd = new JLabel("Ud en el pedido del producto:");
			lbUd.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbUd.setBounds(20, 235, 185, 25);
		}
		return lbUd;
	}
	
	/**
	 * Inicializa el cuadro de texto de las unidades en el pedido
	 * @return txUd, JTextField
	 */
	private JTextField getTxUd() {
		if (txUd == null) {
			txUd = new JTextField();
			txUd.setBackground(SystemColor.WHITE);
			txUd.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txUd.setEditable(false);
			txUd.setBounds(200, 235, 50, 25);
			txUd.setColumns(10);
		}
		return txUd;
	}
	
	/**
	 * Inicializa el mensaje de precio del pedido
	 * @return lbPrecioPedido, JLabel
	 */
	private JLabel getLbPrecioPedido() {
		if (lbPrecioPedido == null) {
			lbPrecioPedido = new JLabel("Precio Pedido:");
			lbPrecioPedido.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbPrecioPedido.setBounds(340, 230, 90, 25);
		}
		return lbPrecioPedido;
	}
	
	/**
	 * Inicializa el cuadro de texto del precio del pedido
	 * @return txtPrecio, JTextField
	 */
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtPrecio.setEditable(false);
			txtPrecio.setBackground(SystemColor.WHITE);
			txtPrecio.setBounds(340, 255, 85, 25);
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
	
	/**
	 * Inicializa el mensaje de descuento
	 * @return lbDescuento, JLabel
	 */
	private JLabel getLbDescuento() {
		if (lbDescuento == null) {
			lbDescuento = new JLabel("Descuento:");
			lbDescuento.setEnabled(false);
			lbDescuento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbDescuento.setBounds(440, 230, 70, 25);
		}
		return lbDescuento;
	}
	
	/**
	 * Inicializa el cuadro de texto del descuento
	 * @return txDescuento, JTextField
	 */
	private JTextField getTxDescuento() {
		if (txDescuento == null) {
			txDescuento = new JTextField();
			txDescuento.setHorizontalAlignment(SwingConstants.CENTER);
			txDescuento.setForeground(Color.RED);
			txDescuento.setBackground(SystemColor.controlHighlight);
			txDescuento.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txDescuento.setEnabled(false);
			txDescuento.setEditable(false);
			txDescuento.setBounds(440, 255, 85, 25);
			txDescuento.setColumns(10);
		}
		return txDescuento;
	}
	
	/**
	 * Inicializa el boton siguiente
	 * Se encarga de mostrar la VentanaRegistro
	 * @return btSiguiente, JButton
	 */
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btSiguiente.setEnabled(false);
			btSiguiente.setMnemonic('S');
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaRegistro();
				}
			});
			btSiguiente.setForeground(Color.WHITE);
			btSiguiente.setBackground(new Color(0, 128, 0));
			btSiguiente.setBounds(325, 305, 90, 25);
		}
		return btSiguiente;
	}
	
	/**
	 * Hace visible la VentanaRegistro
	 * Pero antes comprueba si se debe aplicar un descuento
	 */
	private void mostrarVentanaRegistro() {
		VentanaRegistro vr = new VentanaRegistro(this);
		if (getPedido().getMcHappyDay())
			vr.setTotalAPagar(mcHappyDay());
		else
			vr.setTotalAPagar(getPedido().calcularTotal());
		vr.setVisible(true);
	}
	
	/**
	 * Descuento McHappyDay, 10% sobre el precio final
	 * Muestra el precio descontado y un aviso
	 * @return total_conDescuento, double
	 */
	private double mcHappyDay() {
		double descuento = getPedido().calcularTotal()*0.1;
		double total_conDescuento = getPedido().calcularTotal() - descuento;
		getTxtPrecio().setText(String.format("%.2f", total_conDescuento) + "\u20AC");
		getLbDescuento().setEnabled(true);
		getTxDescuento().setEnabled(true);
		getTxDescuento().setText("-" + String.format("%.2f", descuento) + "\u20AC");
		JOptionPane.showMessageDialog(null, "Aplicado el descuento McHappy Day (10%)", "Descuento McHappy Day" , JOptionPane.INFORMATION_MESSAGE);
		return total_conDescuento;
	}
	
	/**
	 * Inicializa el boton cancelar
	 * La accion de este es inicializar la TPV
	 * @return btCancelar, JButton
	 */
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btCancelar.setMnemonic('C');
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btCancelar.setBackground(Color.RED);
			btCancelar.setForeground(Color.WHITE);
			btCancelar.setBounds(435, 305, 90, 25);
		}
		return btCancelar;
	}
	
	/**
	 * Inicializa la TPV
	 */
	private void inicializar() {
		getCbArticulos().setSelectedIndex(0);
		getSpUnidades().setValue(1);
		getTxUd().setText(null);
		getTxtPrecio().setText(null);
		getTxDescuento().setText(null);
		getLbDescuento().setEnabled(false);
		getTxDescuento().setEnabled(false);
		getBtSiguiente().setEnabled(false);
		pedido.inicializar();
	}
	
	/**
	 * Obtiene el pedido
	 * @return pedido, Pedido
	 */
	protected Pedido getPedido() {
		return this.pedido;
	}
	
}
