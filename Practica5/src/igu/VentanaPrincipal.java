package igu;

import java.awt.*;
import java.awt.event.*;
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
	private JMenuBar menuBar;
	private JMenu mnPedido;
	private JMenuItem mntmNuevo;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JSeparator separator1;
	private JMenuItem mntmAcercaDe;
	private JLabel lbLogo;
	private JLabel lbNombre;
	private JLabel lbPedido;
	private JScrollPane spPedido;
	private JTextArea taPedido;
	private JLabel lbArticulos;
	private JComboBox<Articulo> cbArticulos;
	private JLabel lbUnidades;
	private JSpinner spUnidades;
	private JButton btAñadir;
	private JButton btEliminar;
	private JLabel lbImagenProducto;
	private JLabel lbPrecioPedido;
	private JTextField txtPrecio;
	private JButton btSiguiente;
	private JButton btCancelar;
	private JPanel pnFiltro;
	private JButton btHamburguesas;
	private JButton btBebidas;
	private JButton btComplementos;
	private JButton btPostres;
	
	// Clases del Package Logica
	private Carta carta;
	private Pedido pedido;
	
	/**
	 * Metodo principal de la aplicacion, permite su ejecucion
	 * Se establece un look and feel determinado
	 * @param args, String[]
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
		setBounds(100, 100, 767, 486);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(getLbLogo());
		contentPane.add(getLbNombre());
		contentPane.add(getLbPedido());
		contentPane.add(getSpPedido());
		contentPane.add(getLbArticulos());
		contentPane.add(getCbArticulos());
		contentPane.add(getLbUnidades());
		contentPane.add(getSpUnidades());
		contentPane.add(getBtAñadir());
		contentPane.add(getBtEliminar());
		contentPane.add(getLbImagenProducto());
		contentPane.add(getLbPrecioPedido());
		contentPane.add(getTxtPrecio());
		contentPane.add(getBtSiguiente());
		contentPane.add(getBtCancelar());
		contentPane.add(getPnFiltro());
	}

	/**
	 * Inicializa la barra de menu de la interfaz
	 * @return menuBar, JMenuBar
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnPedido());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	
	/**
	 * Inicializa la opcion de menu Pedido
	 * @return mnPedido, JMenu
	 */
	private JMenu getMnPedido() {
		if (mnPedido == null) {
			mnPedido = new JMenu("Pedido");
			mnPedido.setMnemonic('P');
			mnPedido.add(getMntmNuevo());
			mnPedido.add(getSeparator());
			mnPedido.add(getMntmSalir());
		}
		return mnPedido;
	}
	
	/**
	 * Inicializa el elemento de menu Nuevo
	 * @return mntmNuevo, JMenuItem
	 */
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNuevo.setMnemonic('N');
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		}
		return mntmNuevo;
	}
	
	/**
	 * Inicializa el separador de elementos de menu
	 * @return separator, JSeparator
	 */
	private JSeparator getSeparator() {
		if (separator == null)
			separator = new JSeparator();
		return separator;
	}
	
	/**
	 * Inicializa el elemento de menu Salir
	 * @return mntmSalir, JMenuItem
	 */
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmSalir.setMnemonic('S');
		}
		return mntmSalir;
	}
	
	/**
	 * Inicializa la opcion de menu Ayuda
	 * @return mnAyuda, JMenu
	 */
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('d');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	
	/**
	 * Inicializa el elemento de menu Contenidos
	 * @return mntmContenidos, JMenuItem
	 */
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Ayuda no disponible", "Contenidos de la Ayuda" , JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContenidos.setMnemonic('C');
		}
		return mntmContenidos;
	}
	
	/**
	 * Inicializa el separador de elementos de menu
	 * @return separato1r, JSeparator
	 */
	private JSeparator getSeparator1() {
		if (separator1 == null) {
			separator1 = new JSeparator();
		}
		return separator1;
	}
	
	/**
	 * Inicializa el elemento de menu Acerca De
	 * @return mntmAcercaDe, JMenuItem
	 */
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mensaje = "Aplicación para TPV de comida rápica\nPrácticas CPM 19-20\nEII Oviedo";
					JOptionPane.showMessageDialog(null, mensaje, "Acerca de" , JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mntmAcercaDe.setMnemonic('r');
		}
		return mntmAcercaDe;
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
			lbLogo.setBounds(128, 19, 176, 141);
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
			lbNombre.setBounds(314, 73, 227, 42);
		}
		return lbNombre;
	}
	
	/**
	 * Inicializa el mensaje de Pedido
	 * Permite la visualizacion del pedido en un JTextArea
	 * @return lbPedido, JLabel
	 */
	private JLabel getLbPedido() {
		if (lbPedido == null) {
			lbPedido = new JLabel();
			lbPedido.setBounds(578, 19, 131, 42);
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
	
	/**
	 * Inicializa un panel de scroll
	 * @returnspPedido, JScrollPane
	 */
	private JScrollPane getSpPedido() {
		if (spPedido == null) {
			spPedido = new JScrollPane();
			spPedido.setBounds(555, 72, 185, 112);
			spPedido.setViewportView(getTaPedido());
			spPedido.setColumnHeaderView(getTaPedido());
		}
		return spPedido;
	}
	
	/**
	 * Inicializa un area de texto para mostrar el pedido
	 * @return taPedido, JTextArea
	 */
	private JTextArea getTaPedido() {
		if (taPedido == null) {
			taPedido = new JTextArea();
			taPedido.setFont(new Font("Tahoma", Font.PLAIN, 12));
			taPedido.setBackground(Color.WHITE);
			taPedido.setEditable(false);
		}
		return taPedido;
	}
	
	/**
	 * Inicializa el mensaje Articulos 
	 * @return lbArticulos, JLabel
	 */
	private JLabel getLbArticulos() {
		if (lbArticulos == null) {
			lbArticulos = new JLabel("Articulos:");
			lbArticulos.setBounds(145, 200, 72, 28);
			lbArticulos.setLabelFor(getCbArticulos());
			lbArticulos.setDisplayedMnemonic('r');
			lbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 13));
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
			cbArticulos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarImagen();
				}
			});
			cbArticulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
			cbArticulos.setBackground(SystemColor.WHITE);
			cbArticulos.setBounds(142, 233, 310, 30);
			cbArticulos.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
		}
		return cbArticulos;
	}
	
	/**
	 * Segun el articulo seleccionado en el JComboBox modifica la imagen a mostrar en un JLabel
	 */
	private void cambiarImagen() {
		String codigoIcono = ((Articulo) getCbArticulos().getSelectedItem()).getCodigo();
		getLbImagenProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/"+codigoIcono+".png")));
	}
	
	/**
	 * Inicializa un cuadro en el que mostrar una imagen asociada a un cierto articulo del JComboBox
	 * @return lbImagenProducto, JLabel
	 */
	private JLabel getLbImagenProducto() {
		if (lbImagenProducto == null) {
			lbImagenProducto = new JLabel("");
			lbImagenProducto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/HA01.png")));
			lbImagenProducto.setBounds(213, 274, 160, 122);
		}
		return lbImagenProducto;
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
			lbUnidades.setBounds(482, 200, 72, 22);
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
			spUnidades.setBounds(482, 233, 58, 28);
		}
		return spUnidades;
	}
	
	/**
	 * Inicializa el boton de añadir un articulo al pedido 
	 * @return btAñadir, JButton
	 */
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
			btAñadir.setBounds(557, 233, 83, 26);
		}
		return btAñadir;
	}
	
	/**
	 * Añade un articulo al pedido
	 */
	private void añadirAPedido() {
		Articulo articuloSeleccionado = (Articulo) getCbArticulos().getSelectedItem();
		int unidadesSeleccionadas = (int) getSpUnidades().getValue();
		getPedido().add(articuloSeleccionado, unidadesSeleccionadas);
		getTxtPrecio().setText(String.format("%.2f", getPedido().calcularTotal()) + "\u20AC");
		if (!getBtSiguiente().isEnabled())
			getBtSiguiente().setEnabled(true);
		getSpUnidades().setValue(1);
	}
	
	/**
	 * Inicializa el boton de eliminar un articulo del pedido 
	 * @return btEliminar, JButton
	 */
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
			btEliminar.setBounds(657, 233, 83, 26);
		}
		return btEliminar;
	}
	
	/**
	 * Elimina un articulo o ciertas unidades de un articulo del pedido
	 */
	private void eliminarDePedido() {
		Articulo articuloSeleccionado = (Articulo) getCbArticulos().getSelectedItem();
		int unidadesSeleccionadas = (int) getSpUnidades().getValue();
		if (getPedido().getListaPedido().size() == 0)
			JOptionPane.showMessageDialog(null, "No hay artículos en el pedido", "ERROR", JOptionPane.ERROR_MESSAGE);
		else if (getPedido().udArticuloEnPedido(articuloSeleccionado) != 0) {
			if (unidadesSeleccionadas <= getPedido().udArticuloEnPedido(articuloSeleccionado))
				getPedido().remove(articuloSeleccionado, unidadesSeleccionadas);
			else
				JOptionPane.showMessageDialog(null, "No hay tantas ud del artículo en el pedido", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "El artículo no se encuentra en el pedido", "ERROR", JOptionPane.ERROR_MESSAGE);	
	}
	
	/**
	 * Permite continuar con el pedido al cliente
	 */
	private void continuarPedido() {
		getTxtPrecio().setText(String.format("%.2f", getPedido().calcularTotal()) + "\u20AC");
		getSpUnidades().setValue(1);
	}
	
	/**
	 * Inicializa el mensaje de precio del pedido
	 * @return lbPrecioPedido, JLabel
	 */
	private JLabel getLbPrecioPedido() {
		if (lbPrecioPedido == null) {
			lbPrecioPedido = new JLabel("Precio Pedido:");
			lbPrecioPedido.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbPrecioPedido.setBounds(483, 272, 89, 22);
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
			txtPrecio.setBounds(483, 304, 86, 28);
		}
		return txtPrecio;
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
			btSiguiente.setBounds(528, 378, 90, 30);
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
			btCancelar.setBounds(636, 378, 90, 30);
		}
		return btCancelar;
	}
	
	/**
	 * Inicializa la TPV
	 */
	protected void inicializar() {
		getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
		cambiarImagen();
		getSpUnidades().setValue(1);
		getTxtPrecio().setText(null);
		getBtSiguiente().setEnabled(false);
		pedido.inicializar();
	}
	
	/**
	 * Inicializa el panel de filtro de articulos en carta
	 * @return pnFiltro, JPanel
	 */
	private JPanel getPnFiltro() {
		if (pnFiltro == null) {
			pnFiltro = new JPanel();
			pnFiltro.setBounds(10, 13, 120, 409);
			pnFiltro.setLayout(new GridLayout(0, 1, 0, 1));
			pnFiltro.add(getBtHamburguesas());
			pnFiltro.add(getBtBebidas());
			pnFiltro.add(getBtComplementos());
			pnFiltro.add(getBtPostres());
		}
		return pnFiltro;
	}
	
	/**
	 * Inicializa el boton de seleccion de filtro Hamburguesas
	 * @return btHamburguesas, JButton
	 */
	private JButton getBtHamburguesas() {
		if (btHamburguesas == null) {
			btHamburguesas = new JButton("Hamburguesas");
			btHamburguesas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(carta.filtrar("Hamburguesa")));
					getLbImagenProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/HA01.png")));
				}
			});
			btHamburguesas.setBackground(Color.WHITE);
			btHamburguesas.setMnemonic('H');
			btHamburguesas.setMargin(new Insets(0, 0, 0, 0));
			btHamburguesas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btHamburguesas.setVerticalAlignment(SwingConstants.TOP);
			btHamburguesas.setHorizontalTextPosition(SwingConstants.CENTER);
			btHamburguesas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Hamburguesa.png")));
		}
		return btHamburguesas;
	}
	
	/**
	 * Inicializa el boton de seleccion de filtro Bebidas
	 * @return btBebidas, JButton
	 */
	private JButton getBtBebidas() {
		if (btBebidas == null) {
			btBebidas = new JButton("Bebidas");
			btBebidas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(carta.filtrar("Bebida")));
					getLbImagenProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/BE01.png")));
				}
			});
			btBebidas.setMargin(new Insets(0, 0, 0, 0));
			btBebidas.setMnemonic('B');
			btBebidas.setHorizontalTextPosition(SwingConstants.CENTER);
			btBebidas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btBebidas.setVerticalAlignment(SwingConstants.TOP);
			btBebidas.setBackground(Color.WHITE);
			btBebidas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Bebida.png")));
		}
		return btBebidas;
	}
	
	/**
	 * Inicializa el boton de seleccion de filtro Complementos
	 * @return btComplementos, JButton
	 */
	private JButton getBtComplementos() {
		if (btComplementos == null) {
			btComplementos = new JButton("Complementos");
			btComplementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(carta.filtrar("Complemento")));
					getLbImagenProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/CO01.png")));
				}
			});
			btComplementos.setMargin(new Insets(0, 0, 0, 0));
			btComplementos.setMnemonic('o');
			btComplementos.setHorizontalTextPosition(SwingConstants.CENTER);
			btComplementos.setVerticalTextPosition(SwingConstants.BOTTOM);
			btComplementos.setVerticalAlignment(SwingConstants.TOP);
			btComplementos.setBackground(Color.WHITE);
			btComplementos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Complemento.png")));
		}
		return btComplementos;
	}
	
	/**
	 * Inicializa el boton de seleccion de filtro Postres
	 * @return btPostres, JButton
	 */
	private JButton getBtPostres() {
		if (btPostres == null) {
			btPostres = new JButton("Postres");
			btPostres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getCbArticulos().setModel(new DefaultComboBoxModel<Articulo>(carta.filtrar("Postre")));
					getLbImagenProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/PO01.png")));
				}
			});
			btPostres.setMargin(new Insets(0, 0, 0, 0));
			btPostres.setMnemonic('P');
			btPostres.setHorizontalTextPosition(SwingConstants.CENTER);
			btPostres.setVerticalTextPosition(SwingConstants.BOTTOM);
			btPostres.setVerticalAlignment(SwingConstants.TOP);
			btPostres.setBackground(Color.WHITE);
			btPostres.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Postre.png")));
		}
		return btPostres;
	}
	
	/**
	 * Obtiene el pedido
	 * @return pedido, Pedido
	 */
	protected Pedido getPedido() {
		return this.pedido;
	}
	
}
