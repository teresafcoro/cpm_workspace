package igu;

import java.awt.*;
import javax.swing.*;
import logica.*;
import java.awt.event.*;
import javax.swing.border.*;

public class VentanaPrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel pnLogo;
	private JLabel lbLogo;
	private JLabel lbNombreApp;
	private JPanel pnContenido;
	
	private JPanel pn1;
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
	
	private JPanel pn2;
	private JPanel pnFormulario;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel lbAño;
	private JComboBox<String> cbAños;
	private JLabel lbPasw1;
	private JPasswordField psw1;
	private JLabel lbPasw2;
	private JPasswordField psw2;
	private JPanel pnDatosPedido;
	private JRadioButton rbLocal;
	private JRadioButton rbLlevar;
	private JPanel pnInfo2;
	private JPanel pnBts2;
	
	private JPanel pn3;
	private JPanel pnConfirmacion;
	private JLabel lbAviso;
	private JLabel lbOk;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private JPanel pnInfo3;
	private JPanel pnBts3;
	
	private AccionBoton aB;
	private final ButtonGroup grPedido = new ButtonGroup();
	private Carta carta;
	private Pedido pedido;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				imprimeTamaño();
			}
		});
		
		carta = new Carta();
		pedido = new Pedido();
		aB = new AccionBoton();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 761);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(590,494));		// la determino con el evento resized + imprimeTamaño()
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnLogo(), BorderLayout.NORTH);
		contentPane.add(getPnContenido(), BorderLayout.CENTER);
	}
	
	private void imprimeTamaño() {
		System.out.println(this.getWidth() + " " + this.getHeight());
	}
	
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
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("");
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		}
		return lbLogo;
	}
	private JLabel getLbNombreApp() {
		if (lbNombreApp == null) {
			lbNombreApp = new JLabel("McDonald's");
			lbNombreApp.setFont(new Font("Arial Black", Font.PLAIN, 44));
			lbNombreApp.setForeground(Color.BLACK);
		}
		return lbNombreApp;
	}
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setLayout(new CardLayout(0, 0));
			pnContenido.add(getPn1(), "pn1");			// Acordarse de cambiar los nombres como aqui
			pnContenido.add(getPn2(), "pn2");
			pnContenido.add(getPn3(), "pn3");
		}
		return pnContenido;
	}
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setLayout(new BorderLayout(0, 0));
			pn1.add(getPnArticulos(), BorderLayout.CENTER);
			pn1.add(getPnInfo1(), BorderLayout.SOUTH);
		}
		return pn1;
	}
	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBorder(new LineBorder(Color.ORANGE, 4));
			pnArticulos.setBackground(Color.WHITE);
			pnArticulos.setLayout(new GridLayout(carta.getListaArticulos().size()/5,5,3,3));
			creaBotonesTablero();
			asociaImagenBotones();
		}
		return pnArticulos;
	}
	private void creaBotonesTablero() {
		pnArticulos.removeAll();
		for (int i = 0; i < carta.getListaArticulos().size(); i++)
			pnArticulos.add(nuevoBoton(i));
	}
	private JButton nuevoBoton(Integer posicion) {
		JButton boton = new JButton("");
		boton.setBackground(Color.white);
		boton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		boton.setToolTipText(carta.getListaArticulos().get(posicion).toString());
		boton.setActionCommand(posicion.toString());
		boton.addActionListener(aB);
		return boton;
	}
	private void asociaImagenBotones() {
		for (int i = 0; i < pnArticulos.getComponents().length; i++) {
			JButton boton = (JButton) (pnArticulos.getComponents()[i]);
			setImagenAdaptada(boton, carta.getListaArticulos().get(i).getRutaFoto());
		}
	}
	private void setImagenAdaptada(JButton boton, String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(95,90, Image.SCALE_DEFAULT);
		boton.setIcon(new ImageIcon(imgEscalada));
	}
	class AccionBoton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			añadirAPedido(Integer.parseInt(bt.getActionCommand()));
		}
	}
	private void añadirAPedido(int posArticuloEnCarta) {
		Articulo a = carta.getListaArticulos().get(posArticuloEnCarta);
		pedido.add(a, 1);	// 1ud por click
		mostrarEnLista(a);
		getTxPrecio().setText("Precio: " + String.format("%.2f", pedido.calcularTotalSinIva()));
		if (!getBtSig1().isEnabled())
			getBtSig1().setEnabled(true);
	}
	private void mostrarEnLista(Articulo a) {
		if (a.getTipo().contentEquals("Bebida"))
			modeloListBebida.addElement(a);
		else
			modeloListComida.addElement(a);
	}
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
	private JTabbedPane getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JTabbedPane(JTabbedPane.TOP);
			pnPedido.addTab("Comida", null, getScrollComida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(0, 4);
			pnPedido.addTab("Bebida", null, getScrollBebida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(1, 0);
		}
		return pnPedido;
	}
	private JScrollPane getScrollComida() {
		if (scrollComida == null) {
			scrollComida = new JScrollPane();
			scrollComida.setViewportView(getListComida());
		}
		return scrollComida;
	}
	private JList<Articulo> getListComida() {
		if (listComida == null) {
			modeloListComida = new DefaultListModel<Articulo>();
			listComida = new JList<Articulo>(modeloListComida);
			listComida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2)
						eliminarDePedido(listComida.getSelectedIndex());
				}
			});
		}
		return listComida;
	}
	private JScrollPane getScrollBebida() {
		if (scrollBebida == null) {
			scrollBebida = new JScrollPane();
			scrollBebida.setViewportView(getListBebida());
		}
		return scrollBebida;
	}
	private JList<Articulo> getListBebida() {
		if (listBebida == null) {
			modeloListBebida = new DefaultListModel<Articulo>();
			listBebida = new JList<Articulo>(modeloListBebida);
			listBebida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2)
						eliminarDePedido(listBebida.getSelectedIndex());
				}
			});
		}
		return listBebida;
	}
	private void eliminarDePedido(int posArticuloEnCarta) {
		Articulo a = carta.getListaArticulos().get(posArticuloEnCarta);
		pedido.remove(a, a.getUnidades());
		eliminarEnLista(a);
		getTxPrecio().setText("Precio: " + String.format("%.2f", pedido.calcularTotalSinIva()));
		if (pedido.calcularTotalSinIva() == 0.0)
			getBtSig1().setEnabled(false);
	}
	private void eliminarEnLista(Articulo a) {
		if (a.getTipo().contentEquals("Bebida"))
			modeloListBebida.removeElement(a);
		else
			modeloListComida.removeElement(a);
	}
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
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setFont(new Font("Tahoma", Font.BOLD, 12));
			txPrecio.setForeground(Color.WHITE);
			txPrecio.setBackground(new Color(255, 153, 51));
			txPrecio.setText("Precio: 0.0\u20AC");
			txPrecio.setEditable(false);
			txPrecio.setColumns(10);
		}
		return txPrecio;
	}
	private JButton getBtSig1() {
		if (btSig1 == null) {
			btSig1 = new JButton("Siguiente");
			btSig1.setMnemonic('S');
			btSig1.setFont(new Font("Tahoma", Font.BOLD, 12));
			btSig1.setForeground(Color.WHITE);
			btSig1.setBackground(new Color(0, 153, 102));
			btSig1.setEnabled(false);
		}
		return btSig1;
	}
	private JButton getBtAnular1() {
		if (btAnular1 == null) {
			btAnular1 = new JButton("Anular");
			btAnular1.setMnemonic('A');
			btAnular1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btAnular1.setFont(new Font("Tahoma", Font.BOLD, 12));
			btAnular1.setForeground(Color.WHITE);
			btAnular1.setBackground(new Color(255, 51, 51));
		}
		return btAnular1;
	}
	protected void inicializar() {
		pedido.inicializar();
		modeloListBebida.removeAllElements();
		modeloListComida.removeAllElements();
		getTxPrecio().setText("Precio: 0.0\u20AC");
		getBtSig1().setEnabled(false);
	}
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
			pnDatosCliente.add(getLbPasw1());
			pnDatosCliente.add(getPsw1());
			pnDatosCliente.add(getLbPasw2());
			pnDatosCliente.add(getPsw2());
		}
		return pnDatosCliente;
	}
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
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			txtNombre.setBounds(193, 24, 330, 25);
		}
		return txtNombre;
	}
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("A\u00F1o de nacimiento:");
			lbAño.setFont(new Font("Arial", Font.PLAIN, 14));
			lbAño.setDisplayedMnemonic('A');
			lbAño.setBounds(30, 81, 121, 16);
		}
		return lbAño;
	}
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
	private JLabel getLbPasw1() {
		if (lbPasw1 == null) {
			lbPasw1 = new JLabel();
			lbPasw1.setText("Password:");
			lbPasw1.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw1.setDisplayedMnemonic('P');
			lbPasw1.setBounds(new Rectangle(13, 123, 105, 16));
			lbPasw1.setBounds(30, 129, 105, 16);
		}
		return lbPasw1;
	}
	private JPasswordField getPsw1() {
		if (psw1 == null) {
			psw1 = new JPasswordField();
			psw1.setFont(new Font("Arial", Font.PLAIN, 14));
			psw1.setBounds(new Rectangle(176, 121, 218, 25));
			psw1.setBounds(193, 122, 218, 25);
		}
		return psw1;
	}
	private JLabel getLbPasw2() {
		if (lbPasw2 == null) {
			lbPasw2 = new JLabel();
			lbPasw2.setText("Reintroduzca password:");
			lbPasw2.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw2.setDisplayedMnemonic('R');
			lbPasw2.setBounds(new Rectangle(13, 167, 151, 16));
			lbPasw2.setBounds(30, 181, 151, 16);
		}
		return lbPasw2;
	}
	private JPasswordField getPsw2() {
		if (psw2 == null) {
			psw2 = new JPasswordField();
			psw2.setFont(new Font("Arial", Font.PLAIN, 14));
			psw2.setBounds(new Rectangle(176, 163, 218, 25));
			psw2.setBounds(193, 172, 218, 25);
		}
		return psw2;
	}
	private JPanel getPnDatosPedido() {
		if (pnDatosPedido == null) {
			pnDatosPedido = new JPanel();
			pnDatosPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosPedido.setBackground(Color.WHITE);
			pnDatosPedido.setBounds(112, 304, 204, 60);
			pnDatosPedido.add(getRbLocal());
			pnDatosPedido.add(getRbLlevar());
		}
		return pnDatosPedido;
	}
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
	private JPanel getPnInfo2() {
		if (pnInfo2 == null) {
			pnInfo2 = new JPanel();
			pnInfo2.setBackground(Color.WHITE);
			pnInfo2.setLayout(new BorderLayout(0, 0));
			pnInfo2.add(getPnBts2(),BorderLayout.SOUTH);
		}
		return pnInfo2;
	}
	private JPanel getPnBts2() {
		if (pnBts2 == null) {
			pnBts2 = new JPanel();
			pnBts2.setBackground(Color.WHITE);
			pnBts2.setLayout(new GridLayout(1, 3, 0, 0));
		}
		return pnBts2;
	}
//	private boolean comprobarCampos() {
//		if (isVacio()) {
//			JOptionPane.showMessageDialog(null, "Error: Hay algún campo en blanco");
//			return false;
//		}
//		else if (isIncorrecta()) {
//				JOptionPane.showMessageDialog(null, "Error: Las passwords no coinciden");
//				return false;
//			}
//		return true;
//	 }
//	private boolean isVacio() {
//		return (txtNombre.getText().equals("")||(String.valueOf(psw1.getPassword()).equals(""))||(String.valueOf(psw2.getPassword()).equals(""))); 
//	}
//	private boolean isIncorrecta() {
//		return (!String.valueOf(psw1.getPassword()).equals(String.valueOf(psw2.getPassword())));
//	}
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
	private JPanel getPnConfirmacion() {
		if (pnConfirmacion == null) {
			pnConfirmacion = new JPanel();
			pnConfirmacion.setBorder(new LineBorder(Color.ORANGE, 4));
			pnConfirmacion.setBackground(Color.WHITE);
			pnConfirmacion.setLayout(null);
			pnConfirmacion.add(getLbAviso());
			pnConfirmacion.add(getLbOk());
			pnConfirmacion.add(getLbCodigo());
			pnConfirmacion.add(getTxCodigo());
		}
		return pnConfirmacion;
	}
	private JLabel getLbAviso() {
		if (lbAviso == null) {
			lbAviso = new JLabel("Estamos procesando su pedido");
			lbAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
			lbAviso.setBounds(135, 104, 478, 35);
		}
		return lbAviso;
	}
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("");
			lbOk.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ok.png")));
			lbOk.setBounds(50, 91, 73, 52);
		}
		return lbOk;
	}
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("El c\u00F3digo de recogida es:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbCodigo.setBounds(138, 172, 191, 26);
		}
		return lbCodigo;
	}
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txCodigo.setEditable(false);
			txCodigo.setText(FileUtil.setFileName());
			txCodigo.setBounds(341, 161, 191, 45);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	private JPanel getPnInfo3() {
		if (pnInfo3 == null) {
			pnInfo3 = new JPanel();
			pnInfo3.setBackground(Color.WHITE);
			pnInfo3.setLayout(new BorderLayout(0, 0));
			pnInfo3.add(getPnBts3(),BorderLayout.SOUTH);
		}
		return pnInfo3;
	}
	private JPanel getPnBts3() {
		if (pnBts3 == null) {
			pnBts3 = new JPanel();
			pnBts3.setBackground(Color.WHITE);
			pnBts3.setLayout(new GridLayout(1, 3, 0, 0));
		}
		return pnBts3;
	}
	
//	private void mostrarPn3() {
//		if (comprobarCampos()) {   
//		
//		}
//	}
//	private void finalizar() {
//		pedido.grabarPedido(getTxCodigo().getText());
//		inicializar();
//	}
	
}
