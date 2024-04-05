package igu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import logica.*;

/**
 * Ampliacion de la TPV de comida rapida
 * @author UO263728
 */
public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lbLogo;
	private JLabel lbNombre;
	private JLabel lbArticulos;
	private JComboBox<Articulo> cbArticulos;
	private JLabel lbUnidades;
	private JSpinner spUnidades;
	private JButton btAñadir;
	private JButton btEliminar;
	private JLabel lbPrecioPedido;
	private JTextField txtPrecio;
	private JButton btSiguiente;
	private JButton btCancelar;
	private JLabel lbPedido;
	private JScrollPane spPedido;
	private JTextArea taPedido;
	
	private Pedido pedido;
	private Carta carta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		carta = new Carta();
		pedido = new Pedido();
		
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's Espa\u00F1a");
		setBounds(100, 100, 596, 418);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		contentPane.add(getLbLogo());
		contentPane.add(getLbNombre());
		contentPane.add(getLbArticulos());
		contentPane.add(getCbArticulos());
		contentPane.add(getLbUnidades());
		contentPane.add(getSpUnidades());
		contentPane.add(getBtAñadir());
		contentPane.add(getBtEliminar());
		contentPane.add(getLbPrecioPedido());
		contentPane.add(getTxtPrecio());
		contentPane.add(getBtSiguiente());
		contentPane.add(getBtCancelar());
		contentPane.add(getLbPedido());
		contentPane.add(getSpPedido());
	}
	
	protected Pedido getPedido() {
		return pedido;
	}

	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("M");
			lbLogo.setBackground(Color.WHITE);
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
			lbLogo.setBounds(8, 14, 187, 140);
		}
		return lbLogo;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("McDonald's");
			lbNombre.setBackground(Color.WHITE);
			lbNombre.setFont(new Font("Arial Black", Font.PLAIN, 35));
			lbNombre.setBounds(192, 53, 227, 42);
		}
		return lbNombre;
	}
	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos:");
			lbArticulos.setLabelFor(getCbArticulos());
			lbArticulos.setDisplayedMnemonic('r');
			lbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbArticulos.setBounds(10, 162, 67, 28);
		}
		return lbArticulos;
	}
	private JComboBox<Articulo> getCbArticulos() {
		if (cbArticulos == null) {		
			cbArticulos = new JComboBox<Articulo>();
			cbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbArticulos.setBackground(SystemColor.controlHighlight);
			cbArticulos.setBounds(10, 190, 301, 28);
			cbArticulos.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
		}
		return cbArticulos;
	}
	private JLabel getLbUnidades() {
		if (lbUnidades == null) {
			lbUnidades = new JLabel("Unidades:");
			lbUnidades.setDisplayedMnemonic('U');
			lbUnidades.setLabelFor(getSpUnidades());
			lbUnidades.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbUnidades.setBounds(331, 162, 72, 22);
		}
		return lbUnidades;
	}
	private JSpinner getSpUnidades() {
		if (spUnidades == null) {
			spUnidades = new JSpinner();
			spUnidades.setFont(new Font("Tahoma", Font.PLAIN, 13));
			spUnidades.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnidades.setBounds(331, 190, 58, 28);
		}
		return spUnidades;
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("A\u00F1adir");
			btAñadir.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirAPedido();
				}
			});
			btAñadir.setMnemonic('A');
			btAñadir.setBackground(new Color(0, 128, 0));
			btAñadir.setForeground(Color.WHITE);
			btAñadir.setBounds(399, 191, 86, 26);
		}
		return btAñadir;
	}
	private void añadirAPedido() {
		Articulo articuloSeleccionado = (Articulo)getCbArticulos().getSelectedItem();
		int unidadesSeleccionadas = (int)getSpUnidades().getValue();
		getPedido().add(articuloSeleccionado, unidadesSeleccionadas);
		String precio = String.format("%.2f", getPedido().calcularTotal());
		getTxtPrecio().setText(precio + "\u20AC");
		if (!getBtSiguiente().isEnabled())
			getBtSiguiente().setEnabled(true);
		getSpUnidades().setValue(1);
	}
	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarDePedido();
					if (getPedido().getListaPedido().size() == 0)
						inicializar();
					else
						continuarPedido();
				}
			});
			btEliminar.setMnemonic('E');
			btEliminar.setForeground(Color.WHITE);
			btEliminar.setBackground(new Color(0, 128, 0));
			btEliminar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btEliminar.setBounds(494, 191, 86, 26);
		}
		return btEliminar;
	}
	private void eliminarDePedido() {
		Articulo articuloSeleccionado = (Articulo)getCbArticulos().getSelectedItem();
		int unidadesSeleccionadas = (int)getSpUnidades().getValue();
		if (getPedido().getListaPedido().size() == 0)
			JOptionPane.showMessageDialog(null, "Error: No hay artículos en el pedido");
		else if (getPedido().udEnPedido(articuloSeleccionado) != 0) {
			if (unidadesSeleccionadas <= getPedido().udEnPedido(articuloSeleccionado))
				getPedido().remove(articuloSeleccionado, unidadesSeleccionadas);
			else
				JOptionPane.showMessageDialog(null, "Error: No hay tantas ud del artículo en el pedido");
		}	
		else
			JOptionPane.showMessageDialog(null, "Error: El artículo no se encuentra en el pedido");
	}
	private void continuarPedido() {
		String precio = String.format("%.2f", getPedido().calcularTotal());
		getTxtPrecio().setText(precio + "\u20AC");
		if (getPedido().calcularTotal() < 50 && getPedido().getMcHappyDay())
			getPedido().setMcHappyDay(false);
		getSpUnidades().setValue(1);
	}
	private JLabel getLbPrecioPedido() {
		if (lbPrecioPedido == null) {
			lbPrecioPedido = new JLabel("Precio Pedido:");
			lbPrecioPedido.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbPrecioPedido.setBounds(331, 232, 89, 22);
		}
		return lbPrecioPedido;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtPrecio.setEditable(false);
			txtPrecio.setBackground(SystemColor.controlHighlight);
			txtPrecio.setBounds(331, 260, 86, 28);
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
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
			btSiguiente.setBounds(369, 338, 89, 26);
		}
		return btSiguiente;
	}
	private void mostrarVentanaRegistro() {
		VentanaRegistro vr = new VentanaRegistro(this);
		if (pedido.getMcHappyDay() == true)
			vr.setTotalAPagar(mcHappyDay());
		else
			vr.setTotalAPagar(pedido.calcularTotal());
		vr.setVisible(true);
	}
	private double mcHappyDay() {
		double descuento = pedido.calcularTotal()*0.1;
		double total_conDescuento = pedido.calcularTotal() - descuento;
		String precio_conDescuento = String.format("%.2f", total_conDescuento);
		getTxtPrecio().setText(precio_conDescuento + "\u20AC");
		JOptionPane.showMessageDialog(null, "Aplicado el descuento McHappy Day (10%)", "Descuento McHappy Day" , JOptionPane.INFORMATION_MESSAGE);
		return total_conDescuento;
	}
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
			btCancelar.setBounds(481, 338, 89, 26);
		}
		return btCancelar;
	}
	protected void inicializar() {
		getCbArticulos().setSelectedIndex(0);
		getSpUnidades().setValue(1);
		getTxtPrecio().setText(null);
		getBtSiguiente().setEnabled(false);
		pedido.inicializar();
	}
	private JTextArea getTaPedido() {
		if (taPedido == null) {
			taPedido = new JTextArea();
			taPedido.setFont(new Font("Tahoma", Font.PLAIN, 12));
			taPedido.setBackground(Color.WHITE);
			taPedido.setEditable(false);
		}
		return taPedido;
	}
	private JScrollPane getSpPedido() {
		if (spPedido == null) {
			spPedido = new JScrollPane();
			spPedido.setBounds(417, 66, 160, 100);
			spPedido.setViewportView(getTaPedido());
		}
		return spPedido;
	}
	private JLabel getLbPedido() {
		if (lbPedido == null) {
			lbPedido = new JLabel();
			lbPedido.setBounds(430, 20, 131, 42);
			lbPedido.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					getSpPedido().setVisible(true);
					getTaPedido().setVisible(true);
					getTaPedido().setText(getPedido().toString());
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					getSpPedido().setVisible(false);
					getTaPedido().setVisible(false);
				}
			});
			lbPedido.setBackground(Color.WHITE);
			lbPedido.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/pedido.png")));
		}
		return lbPedido;
	}
}
