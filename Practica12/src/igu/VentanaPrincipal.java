package igu;

import java.awt.*;
import javax.swing.*;
import logica.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;
import javax.help.*;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * VentanaPrincipal de la TPV de comida rapida McDonald's España
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
	private JPanel pnLogo;
	private JLabel lbLogo;
	private JLabel lbNombreApp;
	private JPanel pnContenido;
	// Componentes panel 1
	private JPanel pn1;
	private JPanel pnFiltro;
	private JButton btHamburguesas;
	private JButton btBebidas;
	private JButton btComplementos;
	private JButton btPostres;
	private JPanel pnArticulos;
	private JPanel pnInfo1;
	private JTabbedPane pnPedido;
	private JScrollPane scrollComida;
	private DefaultListModel<Articulo> modeloListComida;
	private JList<Articulo> listComida;
	private JScrollPane scrollBebida;
	private DefaultListModel<Articulo> modeloListBebida;
	private JList<Articulo> listBebida;
	private JPanel pnBts1;
	private JTextField txPrecio;
	private JButton btAnular1;
	private JButton btSig1;
	// Componentes panel 2
	private JPanel pn2;
	private JPanel pnFormulario;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel lbAño;
	private JComboBox<String> cbAños;
	private JLabel lbPassword;
	private JPasswordField password;
	private JLabel lbPassword1;
	private JPasswordField password1;
	private JPanel pnDatosPedido;
	private final ButtonGroup grPedido = new ButtonGroup();
	private JRadioButton rbLocal;
	private JRadioButton rbLlevar;
	private JPanel pnInfo2;
	private JPanel pnBts2;
	private JButton btSig2;
	private JButton btAnt2;
	// Componentes panel 3
	private JPanel pn3;
	private JPanel pnConfirmacion;
	private JLabel lbAviso;
	private JLabel lbOk;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private JPanel pnInfo3;
	private JPanel pnBts3;
	private JButton btAnt3;
	private JButton btFin3;

	// Creacion de clases
	private AccionBoton aB;
	private MouseLista ml;
	private BotonSupr bs;
	private AccionFiltro aF;
	
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
			    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
		// Inicializacion de las clases usadas
		carta = new Carta();
		pedido = new Pedido();
		aB = new AccionBoton();
		ml = new MouseLista();
		bs = new BotonSupr();
		aF = new AccionFiltro();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 800);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(600, 700));
		setJMenuBar(getMenuBar_1());
		getRootPane().setDefaultButton(getBtSig1());	// Boton por defecto al pulsar 'enter'
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnLogo(), BorderLayout.NORTH);
		contentPane.add(getPnContenido(), BorderLayout.CENTER);
		
		cargarAyuda();
	}
	
	private void cargarAyuda() {
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			System.out.println("Ayuda no encontrada");
			return;
		}
		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		hb.enableHelpOnButton(getBtHamburguesas(), "filtrar", hs);
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
			mnPedido.setMnemonic('o');
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
					inicializarPanel1();
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
			mnAyuda.setMnemonic('y');
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
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContenidos.setMnemonic('C');
		}
		return mntmContenidos;
	}
	
	/**
	 * Inicializa el separador de elementos de menu
	 * @return separator1, JSeparator
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
	 * Inicializa un panel
	 * @return pnLogo, JPanel
	 */
	private JPanel getPnLogo() {
		if (pnLogo == null) {
			pnLogo = new JPanel();
			pnLogo.setBackground(Color.WHITE);
			pnLogo.setLayout(new GridLayout(1, 0, 0, 0));
			pnLogo.add(getLbLogo());
			pnLogo.add(getLbNombreApp());
		}
		return pnLogo;
	}
	
	/**
	 * Inicializa el logo de la TPV
	 * @return lbLogo, JLabel
	 */
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		}
		return lbLogo;
	}
	
	/**
	 * Inicializa el nombre de la TPV 
	 * @return lbNombre, JLabel
	 */
	private JLabel getLbNombreApp() {
		if (lbNombreApp == null) {
			lbNombreApp = new JLabel("McDonald's");
			lbNombreApp.setFont(new Font("Arial Black", Font.PLAIN, 44));
			lbNombreApp.setForeground(Color.BLACK);
		}
		return lbNombreApp;
	}
	
	/**
	 * Inicializa el panel de contenido
	 * Contiene tres subpaneles
	 * @return pnContenido, JPanel
	 */
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setLayout(new CardLayout(0, 0));
			pnContenido.add(getPn1(), "pn1");
			pnContenido.add(getPn2(), "pn2");
			pnContenido.add(getPn3(), "pn3");
		}
		return pnContenido;
	}
	
	/**
	 * Inicializa el panel 1 del Pedido
	 * @return pn1, JPanel
	 */
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setLayout(new BorderLayout(1, 1));
			pn1.add(getPnArticulos(), BorderLayout.CENTER);
			pn1.add(getPnInfo1(), BorderLayout.SOUTH);
			pn1.add(getPnFiltro(), BorderLayout.WEST);
		}
		return pn1;
	}
	
	/**
	 * Inicializa el panel de articulos
	 * @return pnArticulos, JPanel
	 */
	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBorder(new LineBorder(Color.ORANGE, 4));
			pnArticulos.setBackground(Color.WHITE);
			pnArticulos.setLayout(new GridLayout(carta.getArticulos().length/5,5,3,3));
			creaBotonesTablero();
		}
		return pnArticulos;
	}
	
	/**
	 * Crea botones de forma dinamiza
	 * Uno para cada articulo en carta
	 */
	private void creaBotonesTablero() {
		getPnArticulos().removeAll();
		for (int i = 0; i < carta.getArticulos().length; i++)
			getPnArticulos().add(nuevoBoton(i));
		asociaImagenBotones();
	}
	
	/**
	 * Crea un boton y le da las caracteristicas necesarias
	 * @param posicion, Integer
	 * @return boton, JButton
	 */
	private JButton nuevoBoton(Integer posicion) {
		JButton boton = new JButton("");
		boton.setBackground(Color.white);
		boton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		boton.setToolTipText(carta.getArticulos()[posicion].toString());
		boton.setActionCommand(posicion.toString());
		boton.addActionListener(aB);
		return boton;
	}
	
	/**
	 * Asocia una imagen a cada boton creado
	 */
	private void asociaImagenBotones() {
		for (int i = 0; i < getPnArticulos().getComponents().length; i++)
			setImagenAdaptada((JButton) getPnArticulos().getComponent(i), carta.getArticulos()[i].getRutaFoto());
	}
	
	/**
	 * Adapta la imagen a asociar a un cierto boton
	 * @param boton, JButton
	 * @param rutaImagen, String
	 */
	private void setImagenAdaptada(JButton boton, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(95,90, Image.SCALE_DEFAULT);
		boton.setIcon(new ImageIcon(imgEscalada));
	}
	
	/**
	 * Clase AccionBoton que implementa la interfaz ActionListener
	 * En concreto, la clase actionPerformed que especifica la accion
	 * realizada por los botones creados de forma dinamica en el panel
	 * @author MariaTeresaFernandezCoro-UO263728-71728885G
	 */
	class AccionBoton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			añadirAPedido(Integer.parseInt(((JButton) e.getSource()).getActionCommand()));
		}
	}
	
	/**
	 * Añade un articulo al pedido 
	 * @param posArticuloEnCarta, integer
	 */
	private void añadirAPedido(int posArticuloEnCarta) {
		Articulo a = carta.getArticulos()[posArticuloEnCarta];
		pedido.add(a, 1);
		mostrarEnLista(a);
		getTxPrecio().setText("Precio: " + String.format("%.2f", pedido.calcularTotal()));
		if (!getBtSig1().isEnabled())
			getBtSig1().setEnabled(true);
	}
	
	/**
	 * Añade visualmente al pedido el articulo añadido
	 * @param a, Articulo
	 */
	private void mostrarEnLista(Articulo a) {
		if (a.getTipo().contentEquals("Bebida"))
			modeloListBebida.addElement(a);
		else
			modeloListComida.addElement(a);
	}
	
	/**
	 * Inicializa un panel de informacion
	 * @return pnInfo1, JPanel
	 */
	private JPanel getPnInfo1() {
		if (pnInfo1 == null) {
			pnInfo1 = new JPanel();
			pnInfo1.setBackground(Color.WHITE);
			pnInfo1.setLayout(new BorderLayout(0, 0));
			pnInfo1.add(getPnPedido(), BorderLayout.NORTH);
			pnInfo1.add(getPnBts1(),BorderLayout.SOUTH);
		}
		return pnInfo1;
	}
	
	/**
	 * Inicializa un panel de pestañas
	 * @return pnPedido, JTabbedPane
	 */
	private JTabbedPane getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JTabbedPane(JTabbedPane.TOP);
			pnPedido.addTab("Comida", null, getScrollComida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(0, 2);
			pnPedido.addTab("Bebida", null, getScrollBebida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(1, 4);
		}
		return pnPedido;
	}
	
	/**
	 * Inicializa un panel de scroll
	 * @returns scrollComida, JScrollPane
	 */
	private JScrollPane getScrollComida() {
		if (scrollComida == null) {
			scrollComida = new JScrollPane();
			scrollComida.setViewportView(getListComida());
		}
		return scrollComida;
	}
	
	/**
	 * Inicializa una lista de comidas
	 * @return listComida, JList<Articulo>
	 */
	private JList<Articulo> getListComida() {
		if (listComida == null) {
			modeloListComida = new DefaultListModel<Articulo>();
			listComida = new JList<Articulo>(modeloListComida);
			listComida.addKeyListener(bs);
			listComida.addMouseListener(ml);
		}
		return listComida;
	}
	
	/**
	 * Inicializa un panel de Scroll
	 * @return acrollBebida, JScrollPane
	 */
	private JScrollPane getScrollBebida() {
		if (scrollBebida == null) {
			scrollBebida = new JScrollPane();
			scrollBebida.setViewportView(getListBebida());
		}
		return scrollBebida;
	}
	
	/**
	 * Inicializa una lista de bebidas
	 * @return listBebida, JList<Articulo>
	 */
	private JList<Articulo> getListBebida() {
		if (listBebida == null) {
			modeloListBebida = new DefaultListModel<Articulo>();
			listBebida = new JList<Articulo>(modeloListBebida);
			listBebida.addKeyListener(bs);
			listBebida.addMouseListener(ml);
		}
		return listBebida;
	}
	
	/**
	 * Clase MouseLista que hereda de la clase MouseAdapter
	 * Especifica la accion realizada por los elementos añadidos en el pedido
	 * Elimina el seleccionado al hacer doble click
	 * @author MariaTeresaFernandezCoro-UO263728-71728885G
	 */
	class MouseLista extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2)
				eliminarDePedido((JList<Articulo>) e.getSource());
		}
	}
	
	/**
	 * Clase BotonSupr que hereda de la clase KeyAdapter
	 * Especifica la accion realizada por los elementos añadidos en el pedido
	 * Elimina el seleccionado al pulsar 'Supr'
	 * @author MariaTeresaFernandezCoro-UO263728-71728885G
	 */
	class BotonSupr extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_DELETE)
				eliminarDePedido((JList<Articulo>) e.getSource());
		}
	}
	
	/**
	 * Elimina un articulo del pedido
	 * @param l, JList<Articulo>
	 */
	private void eliminarDePedido(JList<Articulo> l) {
		Articulo a;
		if (l.getModel().equals(modeloListComida))
			a = getListComida().getSelectedValue();
		else
			a = getListBebida().getSelectedValue();
		pedido.remove(a, 1);
		eliminarEnLista(a);
		getTxPrecio().setText("Precio: " + String.format("%.2f", pedido.calcularTotal()));
		if (pedido.calcularTotal() == 0.0)
			getBtSig1().setEnabled(false);
	}
	
	/**
	 * Elimina visualmente el articulo eliminado del pedido
	 * @param a, Articulo
	 */
	private void eliminarEnLista(Articulo a) {
		if (a.getTipo().contentEquals("Bebida"))
			modeloListBebida.removeElement(a);
		else
			modeloListComida.removeElement(a);
	}
	
	/**
	 * Inicializa un panel de botones
	 * @return pnBts1, JPanel
	 */
	private JPanel getPnBts1() {
		if (pnBts1 == null) {
			pnBts1 = new JPanel();
			pnBts1.setBackground(Color.WHITE);
			pnBts1.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts1.add(getTxPrecio());
			pnBts1.add(getBtAnular1());
			pnBts1.add(getBtSig1());
		}
		return pnBts1;
	}
	
	/**
	 * Inicializa un cuadro de texto con el precio del pedido
	 * @return txPrecio, JTextField
	 */
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
			txPrecio.setForeground(Color.WHITE);
			txPrecio.setBackground(new Color(255, 153, 51));
			txPrecio.setText("Precio: 0€");
			txPrecio.setEditable(false);
			txPrecio.setColumns(10);
		}
		return txPrecio;
	}
	
	/**
	 * Inicializa un boton siguiente
	 * Permite avanzar al siguiente panel de la TPV
	 * @return btSig1, JButton
	 */
	private JButton getBtSig1() {
		if (btSig1 == null) {
			btSig1 = new JButton("Siguiente");
			btSig1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (pedido.getMcHappyDay())
						mcHappyDay();
					mostrarPanel("pn2");
				}
			});
			btSig1.setMnemonic('S');
			btSig1.setFont(new Font("Tahoma", Font.BOLD, 12));
			btSig1.setForeground(Color.WHITE);
			btSig1.setBackground(new Color(0, 153, 102));
			btSig1.setEnabled(false);
		}
		return btSig1;
	}
	
	/**
	 * Aplica el descuento McHappyDay al pedido del cliente
	 */
	private void mcHappyDay() {
		float descuento = (float) (pedido.calcularTotal()*0.1);
		float precioFinal = (float) (pedido.calcularTotal() - descuento);
		getTxPrecio().setText("Precio: "+String.format("%.2f", precioFinal)+"€");
		String msj = "Se le aplicó el descuento McHappyDay, -" + String.format("%.2f", descuento) + "€";
		JOptionPane.showMessageDialog(null, msj, "Descuento McHappyDay" , JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Hace visible el panel indicado
	 * @param name, String
	 */
	private void mostrarPanel(String name) {
		if (name.equals("pn1")) {
			getListComida().addKeyListener(bs);
			getListComida().addMouseListener(ml);
			getListBebida().addKeyListener(bs);
			getListBebida().addMouseListener(ml);
			getPnInfo1().add(getPnPedido());
			getPnBts1().add(getTxPrecio(), 0);
			getRootPane().setDefaultButton(getBtSig1());
			((CardLayout) getPnContenido().getLayout()).show(getPnContenido(), name);
		}
		else if (name.equals("pn2")) {
			getListComida().removeKeyListener(bs);
			getListComida().removeMouseListener(ml);
			getListBebida().removeKeyListener(bs);
			getListBebida().removeMouseListener(ml);
			getPnInfo2().add(getPnPedido());
			getPnBts2().add(getTxPrecio(), 0);
			getRootPane().setDefaultButton(getBtSig2());
			((CardLayout) getPnContenido().getLayout()).show(getPnContenido(), name);
		}
		else if (comprobarCampos()) {
			getPnInfo3().add(getPnPedido());
			getPnBts3().add(getTxPrecio(), 0);
			getTxCodigo().setText(FileUtil.setFileName());
			getRootPane().setDefaultButton(getBtFin3());
			((CardLayout) getPnContenido().getLayout()).show(getPnContenido(), name);
		}
	}
	
	/**
	 * Comprueba que los campos se rellenen correctamente
	 * @return true, si asi es
	 * false y cuadro de dialogo en otro caso
	 */
	private boolean comprobarCampos() {
		String nombre = getTxtNombre().getText();
		String pass1 = String.valueOf(getPassword().getPassword());
		String pass2 = String.valueOf(getPassword1().getPassword());
		if (nombre.equals("")) {
			JOptionPane.showMessageDialog(null, "Debe introducir su nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
			getTxtNombre().grabFocus();
			return false;
		} else if ((!pass1.equals(pass2)) || (pass1.equals("")) || (pass1.length() < 8)) {
			JOptionPane.showMessageDialog(null, "Contraseñas mal introducidas", "ERROR", JOptionPane.ERROR_MESSAGE);
			getPassword().grabFocus();
			return false;
		} else
			return true;
	 }
	
	/**
	 * Inicializa un boton anular
	 * Permite reiniciar la TPV, anulando el pedido actual
	 * @return btAnular1, JButton
	 */
	private JButton getBtAnular1() {
		if (btAnular1 == null) {
			btAnular1 = new JButton("Anular");
			btAnular1.setMnemonic('A');
			btAnular1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializarPanel1();
					inicializarPanel2();
				}
			});
			btAnular1.setFont(new Font("Tahoma", Font.BOLD, 12));
			btAnular1.setForeground(Color.WHITE);
			btAnular1.setBackground(new Color(255, 51, 51));
		}
		return btAnular1;
	}
	
	/**
	 * Inicializa el primer panel
	 */
	private void inicializarPanel1() {
		pedido.inicializar();
		creaBotonesTablero();
		modeloListBebida.removeAllElements();
		modeloListComida.removeAllElements();
		getTxPrecio().setText("Precio: 0€");
		getBtSig1().setEnabled(false);
		mostrarPanel("pn1");
	}
	
	/**
	 * Inicializa el segundo panel
	 */
	private void inicializarPanel2() {
		getTxtNombre().setText("");
		getCbAños().setSelectedIndex(0);
		getPassword().setText("");
		getPassword1().setText("");
		getRbLocal().setSelected(true);
	}
	
	/**
	 * Inicializa el segundo panel de la TPV
	 * @return pn2, JPanel
	 */
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(Color.WHITE);
			pn2.setLayout(new BorderLayout(0, 0));
			pn2.add(getPnFormulario(), BorderLayout.CENTER);
			pn2.add(getPnInfo2(), BorderLayout.SOUTH);
		}
		return pn2;
	}
	
	/**
	 * Inicializa el panel del formulario
	 * @return pnFormulario, JPanel
	 */
	private JPanel getPnFormulario() {
		if (pnFormulario == null) {
			pnFormulario = new JPanel();
			pnFormulario.setBorder(new LineBorder(Color.ORANGE, 4));
			pnFormulario.setBackground(Color.WHITE);
			pnFormulario.setLayout(null);
			pnFormulario.add(getPnDatosCliente());
			pnFormulario.add(getPnDatosPedido());
		}
		return pnFormulario;
	}
	
	/**
	 * Inicializa el panel de datos del cliente
	 * @return pnDatosCliente, JPanel
	 */
	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setBounds(104, 58, 558, 224);
			pnDatosCliente.setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnDatosCliente.setBackground(Color.WHITE);
			pnDatosCliente.setLayout(null);
			pnDatosCliente.add(getLbNombre());
			pnDatosCliente.add(getTxtNombre());
			pnDatosCliente.add(getLbAño());
			pnDatosCliente.add(getCbAños());
			pnDatosCliente.add(getLbPassword());
			pnDatosCliente.add(getPassword());
			pnDatosCliente.add(getLbPassword1());
			pnDatosCliente.add(getPassword1());
		}
		return pnDatosCliente;
	}
	
	/**
	 * Inicializa el mensaje de Nombre y Apellidos del cliente
	 * @return lbNombre, JLabel
	 */
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel();
			lbNombre.setText("Nombre y Apellidos:");
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			lbNombre.setDisplayedMnemonic('N');
			lbNombre.setBounds(30, 31, 132, 20);
		}
		return lbNombre;
	}
	
	/**
	 * Inicializa el cuadro de texto de Nombre y Apellidos del cliente
	 * @return txNombre, JTextField
	 */
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			txtNombre.setBounds(193, 24, 330, 25);
		}
		return txtNombre;
	}
	
	/**
	 * Inicializa el mensaje de Año de nacimiento del cliente 
	 * @return lbAño, JLabel
	 */
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("A\u00F1o de nacimiento:");
			lbAño.setFont(new Font("Arial", Font.PLAIN, 14));
			lbAño.setDisplayedMnemonic('d');
			lbAño.setBounds(30, 81, 121, 16);
		}
		return lbAño;
	}
	
	/**
	 * Inicializa el comboBox con los años posibles de nacimiento del cliente
	 * @return cbAño, JComboBox<String>
	 */
	private JComboBox<String> getCbAños() {
		if (cbAños == null) {
			String[]años = new String[90];
			for (int i=0;i<90;i++){
				String año = ""+((90-i)+1920);
				años[i]= año;
			}
			cbAños = new JComboBox<String>();
			cbAños.setFont(new Font("Arial", Font.PLAIN, 14));
			cbAños.setModel(new DefaultComboBoxModel<String>(años));
			cbAños.setBounds(new Rectangle(193, 77, 157, 25));
		}
		return cbAños;
	}
	
	/**
	 * Inicializa el mensaje de contraseña
	 * @return lbPassword, JLabel
	 */
	private JLabel getLbPassword() {
		if (lbPassword == null) {
			lbPassword = new JLabel();
			lbPassword.setText("Password:");
			lbPassword.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPassword.setDisplayedMnemonic('P');
			lbPassword.setBounds(new Rectangle(13, 123, 105, 16));
			lbPassword.setBounds(30, 129, 105, 16);
		}
		return lbPassword;
	}
	
	/**
	 * Inicializa el cuadro para establecer una contraseña
	 * @return password, JPasswordField
	 */
	private JPasswordField getPassword() {
		if (password == null) {
			password = new JPasswordField();
			password.setFont(new Font("Arial", Font.PLAIN, 14));
			password.setBounds(new Rectangle(176, 121, 218, 25));
			password.setBounds(193, 122, 218, 25);
		}
		return password;
	}
	
	/**
	 * Inicializa el mensaje de reintroduccion de la contraseña
	 * @return lbPassword1, JLabel
	 */
	private JLabel getLbPassword1() {
		if (lbPassword1 == null) {
			lbPassword1 = new JLabel();
			lbPassword1.setText("Reintroduzca password:");
			lbPassword1.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPassword1.setDisplayedMnemonic('s');
			lbPassword1.setBounds(new Rectangle(13, 167, 151, 16));
			lbPassword1.setBounds(30, 181, 151, 16);
		}
		return lbPassword1;
	}
	
	/**
	 * Inicializa el segundo cuadro para establecer una contraseña
	 * @return password1, JPasswordField
	 */
	private JPasswordField getPassword1() {
		if (password1 == null) {
			password1 = new JPasswordField();
			password1.setFont(new Font("Arial", Font.PLAIN, 14));
			password1.setBounds(new Rectangle(176, 163, 218, 25));
			password1.setBounds(193, 172, 218, 25);
		}
		return password1;
	}
	
	/**
	 * Inicializa el panel de daros del pedido
	 * @return pnDatosPedido, JPanel
	 */
	private JPanel getPnDatosPedido() {
		if (pnDatosPedido == null) {
			pnDatosPedido = new JPanel();
			pnDatosPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosPedido.setBackground(Color.WHITE);
			pnDatosPedido.setBounds(104, 294, 204, 60);
			pnDatosPedido.add(getRbLocal());
			pnDatosPedido.add(getRbLlevar());
		}
		return pnDatosPedido;
	}
	
	/**
	 * Inicializa el radio boton de recoger en el local
	 * @return rbLocal, JRadioButton
	 */
	private JRadioButton getRbLocal() {
		if (rbLocal == null) {
			rbLocal = new JRadioButton();
			grPedido.add(rbLocal);
			rbLocal.setText("Local");
			rbLocal.setSelected(true);
			rbLocal.setMnemonic('L');
			rbLocal.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLocal.setBounds(new Rectangle(17, 27, 94, 24));
			rbLocal.setBackground(Color.WHITE);
		}
		return rbLocal;
	}
	
	/**
	 * Inicializa el radio boton de llevar el pedido
	 * @return rbLlevar, JRadioButton
	 */
	private JRadioButton getRbLlevar() {
		if (rbLlevar == null) {
			rbLlevar = new JRadioButton();
			grPedido.add(rbLlevar);
			rbLlevar.setText("Llevar");
			rbLlevar.setMnemonic('r');
			rbLlevar.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLlevar.setBounds(new Rectangle(115, 27, 86, 24));
			rbLlevar.setBackground(Color.WHITE);
		}
		return rbLlevar;
	}
	
	/**
	 * Inicializa el segundo panel de informacion
	 * @return pnInfo2, JPanel
	 */
	private JPanel getPnInfo2() {
		if (pnInfo2 == null) {
			pnInfo2 = new JPanel();
			pnInfo2.setBackground(Color.WHITE);
			pnInfo2.setLayout(new BorderLayout(0, 0));
			pnInfo2.add(getPnBts2(),BorderLayout.SOUTH);
		}
		return pnInfo2;
	}
	
	/**
	 * Inicializa el segundo panel de botones
	 * @return pnBts2, JPanel
	 */
	private JPanel getPnBts2() {
		if (pnBts2 == null) {
			pnBts2 = new JPanel();
			pnBts2.setBackground(Color.WHITE);
			pnBts2.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts2.add(getBtAnt2());
			pnBts2.add(getBtSig2());
		}
		return pnBts2;
	}
	
	/**
	 * Inicializa el boton anterior
	 * Permite volver al primer panel
	 * @return btAnt2, JButton
	 */
	private JButton getBtAnt2() {
		if (btAnt2 == null) {
			btAnt2 = new JButton("Anterior");
			btAnt2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanel("pn1");
				}
			});
			btAnt2.setMnemonic('A');
			btAnt2.setForeground(Color.WHITE);
			btAnt2.setFont(new Font("Tahoma", Font.BOLD, 12));
			btAnt2.setBackground(new Color(255, 51, 51));
		}
		return btAnt2;
	}
	
	/**
	 * Inicializa el boton siguiente
	 * Se encarga de mostrar el tercer panel
	 * @return btSig2, JButton
	 */
	private JButton getBtSig2() {
		if (btSig2 == null) {
			btSig2 = new JButton("Siguiente");
			btSig2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanel("pn3");
				}
			});
			btSig2.setMnemonic('S');
			btSig2.setForeground(Color.WHITE);
			btSig2.setFont(new Font("Tahoma", Font.BOLD, 12));
			btSig2.setBackground(new Color(0, 153, 102));
		}
		return btSig2;
	}
	
	/**
	 * Inicializa el tercer panel de la TPV
	 * @return pn3, JPanel
	 */
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(Color.WHITE);
			pn3.setLayout(new BorderLayout(0, 0));
			pn3.add(getPnConfirmacion());
			pn3.add(getPnInfo3(), BorderLayout.SOUTH);
		}
		return pn3;
	}
	
	/**
	 * Inicializa el panel de confirmacion del pedido
	 * @return pnConfirmacion, JPanel
	 */
	private JPanel getPnConfirmacion() {
		if (pnConfirmacion == null) {
			pnConfirmacion = new JPanel();
			pnConfirmacion.setBorder(new LineBorder(Color.ORANGE, 4));
			pnConfirmacion.setBackground(Color.WHITE);
			pnConfirmacion.setLayout(null);
			pnConfirmacion.add(getLbOk());
			pnConfirmacion.add(getLbAviso());
			pnConfirmacion.add(getLbCodigo());
			pnConfirmacion.add(getTxCodigo());
		}
		return pnConfirmacion;
	}
	
	/**
	 * Inicializa un icono de ok
	 * @return lbOk, JLabel
	 */
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel();
			lbOk.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ok.png")));
			lbOk.setBounds(70, 91, 73, 52);
		}
		return lbOk;
	}
	
	/**
	 * Inicializa un aviso de confirmacion del pedido
	 * @return lbAviso, JLabel
	 */
	private JLabel getLbAviso() {
		if (lbAviso == null) {
			lbAviso = new JLabel("Estamos procesando su pedido");
			lbAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
			lbAviso.setBounds(153, 108, 478, 35);
		}
		return lbAviso;
	}
	
	/**
	 * Inicializa un mensaje de codigo de pedido
	 * @return lbCodigo, JLabel
	 */
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("Código de recogida:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbCodigo.setBounds(227, 204, 130, 22);
		}
		return lbCodigo;
	}
	
	/**
	 * Inicializa un cuadro de texto 
	 * @return txCodigo, JTextField
	 */
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setBackground(SystemColor.WHITE);
			txCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txCodigo.setEditable(false);
			txCodigo.setText(FileUtil.setFileName());
			txCodigo.setBounds(383, 201, 86, 28);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	
	/**
	 * Inicializa un panel con botones
	 * @return pnInfo3, JPanel
	 */
	private JPanel getPnInfo3() {
		if (pnInfo3 == null) {
			pnInfo3 = new JPanel();
			pnInfo3.setBackground(Color.WHITE);
			pnInfo3.setLayout(new BorderLayout(0, 0));
			pnInfo3.add(getPnBts3(), BorderLayout.SOUTH);
		}
		return pnInfo3;
	}
	
	/**
	 * Inicializa un panel con botones
	 * @return pnBts3, JPanel
	 */
	private JPanel getPnBts3() {
		if (pnBts3 == null) {
			pnBts3 = new JPanel();
			pnBts3.setBackground(Color.WHITE);
			pnBts3.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts3.add(getBtAnt3());
			pnBts3.add(getBtFin3());
		}
		return pnBts3;
	}
	
	/**
	 * Inicializa un boton Anterior
	 * Se encarga de mostrar el segundo panel
	 * @return btAnt3, JButton
	 */
	private JButton getBtAnt3() {
		if (btAnt3 == null) {
			btAnt3 = new JButton("Anterior");
			btAnt3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPanel("pn2");
				}
			});
			btAnt3.setMnemonic('A');
			btAnt3.setForeground(Color.WHITE);
			btAnt3.setFont(new Font("Tahoma", Font.BOLD, 12));
			btAnt3.setBackground(new Color(255, 51, 51));
		}
		return btAnt3;
	}
	
	/**
	 * Inicializa un boton Fin
	 * Se encarga de finalizar el pedido e inicializar la TPV
	 * @return btFin3, JButton
	 */
	private JButton getBtFin3() {
		if (btFin3 == null) {
			btFin3 = new JButton("Finalizar");
			btFin3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
			btFin3.setMnemonic('F');
			btFin3.setForeground(Color.WHITE);
			btFin3.setFont(new Font("Tahoma", Font.BOLD, 12));
			btFin3.setBackground(new Color(0, 153, 102));
		}
		return btFin3;
	}
	
	/**
	 * Finaliza el pedido actual
	 */
	private void finalizar() {
		if (getRbLocal().isSelected())
			pedido.grabarPedido(getTxCodigo().getText(), "Local");
		else
			pedido.grabarPedido(getTxCodigo().getText(), "Llevar");
		inicializarPanel1();
		inicializarPanel2();
		mostrarPanel("pn1");
	}
	
	/**
	 * Inicializa el panel de filtro de articulos en carta
	 * @return pnFiltro, JPanel
	 */
	private JPanel getPnFiltro() {
		if (pnFiltro == null) {
			pnFiltro = new JPanel();
			pnFiltro.setBounds(10, 13, 120, 409);
			pnFiltro.setLayout(new GridLayout(0, 1, 1, 1));
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
			btHamburguesas.setToolTipText("Filtrar por Hamburguesas");
			btHamburguesas.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btHamburguesas.setActionCommand("Hamburguesa");
			btHamburguesas.addActionListener(aF);
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
			btBebidas.setToolTipText("Filtrar por Bebidas");
			btBebidas.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btBebidas.setActionCommand("Bebida");
			btBebidas.addActionListener(aF);
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
			btComplementos.setToolTipText("Filtrar por Complementos");
			btComplementos.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btComplementos.setActionCommand("Complemento");
			btComplementos.addActionListener(aF);
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
			btPostres.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btPostres.setToolTipText("Filtrar por Postres");
			btPostres.setActionCommand("Postre");
			btPostres.addActionListener(aF);
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
	 * Clase AccionFiltro que implementa la Interfaz ActionListener
	 * En concreto su metodo actionPerformed, a partir del cual,
	 * filtra los articulos de la carta segun un tipo selecciondo
	 * @author MariaTeresaFernandezCoro-UO263728-71728885G
	 */
	class AccionFiltro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			filtrar(((JButton) e.getSource()).getActionCommand());
		}
	}
	
	/**
	 * Filtra los articulos de la carta segun un tipo selecciondo
	 * @param type, String
	 */
	private void filtrar(String type) {
		for (int i = 0; i < carta.getArticulos().length; i++) {
			if (!carta.getArticulos()[i].getTipo().equals(type))
				getPnArticulos().getComponent(i).setEnabled(false);
			else
				getPnArticulos().getComponent(i).setEnabled(true);
		}
	}
	
}