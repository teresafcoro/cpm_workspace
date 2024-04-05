package igu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import logica.Juego;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * Juego Invasion Espacial
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class VentanaPrincipal extends JFrame {

	// Version
	private static final long serialVersionUID = 1L;
	
	// Componentes de la interfaz
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenuItem mntmNuevo;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JSeparator separator_1;
	private JMenuItem mntmAcercaDe;
	private JButton btDado;
	private JLabel lbNave;
	private JPanel pnDisparos;
	private JLabel lbPuntos;
	private JTextField textPuntos;
	private JLabel lbPlaneta;
	private JPanel pnTablero;
	private JButton bt0;
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	private JButton bt5;
	private JButton bt6;
	private JButton bt7;
	
	// Clases necesarias para la implementacion de la interfaz
	private Juego juego;
	private procesaBotones pB;

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
		// Inicializacion de las clases necesarias para la implementacion de la interfaz
		juego = new Juego();
		pB = new procesaBotones();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/invader.jpg")));
		setTitle("Invasi\u00F3n espacial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 380);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(getMenuBar_1());
		contentPane.add(getBtDado());
		contentPane.add(getLbNave());
		contentPane.add(getPnDisparos());
		contentPane.add(getLbPuntos());
		contentPane.add(getTxPuntos());
		contentPane.add(getLbPlaneta());
		contentPane.add(getPnTablero());
	}

	/**
	 * Inicializa la barra de menu de la interfaz
	 * @return menuBar, JMenuBar
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 884, 21);
			menuBar.add(getMnJuego());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	
	/**
	 * Inicializa la opcion de menu juego
	 * @return mnJuego, JMenu
	 */
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('J');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	
	/**
	 * Inicializa el elemento de menu nuevo
	 * Permite inicializar el juego
	 * @return mntmNuevo, JMenuItem
	 */
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNuevo.setMnemonic('N');
		}
		return mntmNuevo;
	}
	
	/**
	 * Inicializa el juego
	 */
	private void inicializar() {
		juego.inicializarJuego();
		getBtDado().setEnabled(true);
		getPnDisparos().removeAll();
		getTxPuntos().setText(String.valueOf(juego.getPuntos()));
		despintaCasillas();
		accesoTablero(false);
	}
	
	/**
	 * Despinta las casillas del tablero
	 */
	private void despintaCasillas() {
		for (int i = 0; i < getPnTablero().getComponents().length; i++) {
			((JButton) getPnTablero().getComponent(i)).setIcon(null);
			((JButton) getPnTablero().getComponent(i)).setDisabledIcon(null);
			getPnTablero().repaint();
		}
		validate();
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
	 * Inicializa el elemento de menu salir
	 * Finaliza la ejecucion del juego
	 * @return mntmSalir, JMenuItem
	 */
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.setToolTipText("S");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
	
	/**
	 * Inicializa la opcion de menu ayuda
	 * @return mnAyuda, JMenu
	 */
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('d');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	
	/**
	 * Inicializa el elemento de menu acerca de
	 * @return mntmAcercaDe, JMenuItem
	 */
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.setMnemonic('r');
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mensaje = "Aplicación Juego Invasión Espacial\nPrácticas CPM 19-20\nEII Oviedo";
					JOptionPane.showMessageDialog(null, mensaje, "Acerca de" , JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	
	/**
	 * Inicializa un separador de elementos de menu
	 * @return separator_1, JSeparator
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null)
			separator_1 = new JSeparator();
		return separator_1;
	}
	
	/**
	 * Inicializa el elemento de menu contenidos
	 * @return mntmContenidos, JMenuItem
	 */
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContenidos.setMnemonic('C');
			mntmContenidos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Ayuda no disponible", "Contenidos de la Ayuda" , JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmContenidos;
	}
	
	/**
	 * Inicializa un boton que permite la simulacion de un dado
	 * Al pulsar sobre este boton se inicia el juego
	 * @return btDado, JButton
	 */
	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarJuego();
				}
			});
			btDado.setBounds(33, 36, 114, 109);
			btDado.setBorderPainted(false);
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
		}
		return btDado;
	}
	
	/**
	 * Inicia el juego
	 */
	private void iniciarJuego() {
		juego.lanzar();
		getBtDado().setEnabled(false);
		pintaDisparos();
		accesoTablero(true);
	}
	
	/**
	 * Pinta los disparos en el panel de disparos
	 */
	private void pintaDisparos() {
		for (int i = 0; i < juego.getDisparos(); i++)
			getPnDisparos().add(nuevoDisparo());
		validate();
	}
	
	/**
	 * Crea un nuevo componente disparo en tiempo de ejecucion
	 * @return lbDisparos, JLabel
	 */
	private JLabel nuevoDisparo() {
		JLabel lbDisparo = new JLabel("");
		lbDisparo.setBorder(new LineBorder(Color.GREEN, 1));
		lbDisparo.setIcon(ImagenFactoria.getShootImage());
		return lbDisparo;
	}
	
	/**
	 * Permite el acceso al tablero o no segun el estado del parametro
	 * @param estado, boolean
	 */
	private void accesoTablero(boolean estado) {
		for (int i = 0; i < getPnTablero().getComponents().length; i++)
			getPnTablero().getComponent(i).setEnabled(estado);
		validate();
	}
	
	/**
	 * Inicializa un icono del juego
	 * @return lbNave, JLabel
	 */
	private JLabel getLbNave() {
		if (lbNave == null) {
			lbNave = new JLabel("");
			lbNave.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
			lbNave.setBounds(330, 36, 137, 72);
		}
		return lbNave;
	}
	
	/**
	 * Inicializa el panel de disparos
	 * @return pnDisparos, JPanel
	 */
	private JPanel getPnDisparos() {
		if (pnDisparos == null) {
			pnDisparos = new JPanel();
			pnDisparos.setBackground(Color.BLACK);
			pnDisparos.setBounds(226, 119, 345, 78);
		}
		return pnDisparos;
	}
	
	/**
	 * Inicializa el mensaje puntos
	 * @return lbPuntos, JLabel
	 */
	private JLabel getLbPuntos() {
		if (lbPuntos == null) {
			lbPuntos = new JLabel("Puntos");
			lbPuntos.setFont(new Font("Tahoma", Font.BOLD, 13));
			lbPuntos.setForeground(Color.WHITE);
			lbPuntos.setBounds(596, 36, 45, 27);
		}
		return lbPuntos;
	}
	
	/**
	 * Inicializa el cuadro de texto que muestra los puntos del jugador
	 * @return textPuntos, JTextField
	 */
	private JTextField getTxPuntos() {
		if (textPuntos == null) {
			textPuntos = new JTextField();
			textPuntos.setBackground(Color.BLACK);
			textPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			textPuntos.setForeground(Color.GREEN);
			textPuntos.setText(String.valueOf(juego.getPuntos()));
			textPuntos.setFont(new Font("Tahoma", Font.BOLD, 14));
			textPuntos.setEditable(false);
			textPuntos.setBounds(583, 63, 70, 20);
			textPuntos.setColumns(10);
		}
		return textPuntos;
	}
	
	/**
	 * Inicializa un icono del juego 
	 * @return lbPlaneta, JLabel
	 */
	private JLabel getLbPlaneta() {
		if (lbPlaneta == null) {
			lbPlaneta = new JLabel("");
			lbPlaneta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lbPlaneta.setBounds(676, 41, 198, 156);
		}
		return lbPlaneta;
	}
	
	/**
	 * Inicializa el panel tablero del juego
	 * @return pnTablero, JPanel
	 */
	private JPanel getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanel();
			pnTablero.setBackground(new Color(65, 105, 225));
			pnTablero.setBorder(new LineBorder(new Color(0, 0, 255)));
			pnTablero.setBounds(33, 223, 774, 100);
			pnTablero.setLayout(null);
			pnTablero.add(getBt0());
			pnTablero.add(getBt1());
			pnTablero.add(getBt2());
			pnTablero.add(getBt3());
			pnTablero.add(getBt4());
			pnTablero.add(getBt5());
			pnTablero.add(getBt6());
			pnTablero.add(getBt7());
			accesoTablero(false);
		}
		return pnTablero;
	}
	
	/**
	 * Clase procesaBotones, implementa la interfaz ActionListener
	 * Permite realizar la accion de los botones del tablero
	 * @author MariaTeresaFernandezCoro-UO263728-71728885G
	 */
	private class procesaBotones implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			accionBoton(e);
		}
	}
	
	/**
	 * Realiza la accion de los botones del tablero
	 * @param e, AcionEvent
	 */
	private void accionBoton(ActionEvent e) {
		int n = Integer.parseInt(((JButton) e.getSource()).getActionCommand());
		juego.dispara(n);
		representaJuego(n);
	}
	
	/**
	 * Representa el juego segun va transcurriendo
	 * Comprueba si el juego llega a su fin
	 * @param n, integer
	 */
	private void representaJuego(int n) {
		getTxPuntos().setText(String.valueOf(juego.getPuntos()));
		despintaDisparo();
		pintaCasilla(n);
		getPnTablero().getComponent(n).setEnabled(false);
		if (juego.isPartidaFinalizada())
			finPartida();
	}
	
	/**
	 * Despinta el disparo efectuado por el usuario al tablero
	 */
	private void despintaDisparo() {
		getPnDisparos().remove(0);
		getPnDisparos().repaint(0);
	}
	
	/**
	 * Pinta la casilla disparada por el usuario
	 * @param n, integer
	 */
	private void pintaCasilla(int n) {
		ImageIcon imagen = ImagenFactoria.getImagen(juego.getTablero().getCasillas()[n]);
		((JButton) getPnTablero().getComponent(n)).setIcon(imagen);
		((JButton) getPnTablero().getComponent(n)).setDisabledIcon(imagen);
	}
	
	/**
	 * Finaliza la partida mostrando en un mensaje el motivo
	 */
	private void finPartida() {
		accesoTablero(false);
		if (juego.isInvasorEncontrado())
			JOptionPane.showMessageDialog(null, "Has encontrado al invasor... ¡Ganaste!", "Información final de partida", JOptionPane.INFORMATION_MESSAGE);
		else if (juego.isMeteoritoEncontrado())
			JOptionPane.showMessageDialog(null, "Has encontrado el meteorito... ¡Perdiste!", "Información final de partida", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "¡Perdiste!", "Información final de partida", JOptionPane.INFORMATION_MESSAGE);
		for (int i = 0; i < getPnTablero().getComponents().length; i++)
			pintaCasilla(i);
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt0, JButton
	 */
	private JButton getBt0() {
		if (bt0 == null) {
			bt0 = new JButton();
			bt0.setActionCommand("0");
			bt0.addActionListener(pB);
			bt0.setBackground(Color.BLACK);
			bt0.setBounds(10, 5, 88, 88);
		}
		return bt0;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt1, JButton
	 */
	private JButton getBt1() {
		if (bt1 == null) {
			bt1 = new JButton("");
			bt1.setActionCommand("1");
			bt1.addActionListener(pB);
			bt1.setBackground(Color.BLACK);
			bt1.setBounds(105, 5, 88, 88);
		}
		return bt1;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt2, JButton
	 */
	private JButton getBt2() {
		if (bt2 == null) {
			bt2 = new JButton();
			bt2.setActionCommand("2");
			bt2.addActionListener(pB);
			bt2.setBackground(Color.BLACK);
			bt2.setBounds(200, 5, 88, 88);
		}
		return bt2;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt3, JButton
	 */
	private JButton getBt3() {
		if (bt3 == null) {
			bt3 = new JButton();
			bt3.setActionCommand("3");
			bt3.addActionListener(pB);
			bt3.setBackground(Color.BLACK);
			bt3.setBounds(295, 5, 88, 88);
		}
		return bt3;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt4, JButton
	 */
	private JButton getBt4() {
		if (bt4 == null) {
			bt4 = new JButton();
			bt4.setActionCommand("4");
			bt4.addActionListener(pB);
			bt4.setBackground(Color.BLACK);
			bt4.setBounds(390, 5, 88, 88);
		}
		return bt4;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt5, JButton
	 */
	private JButton getBt5() {
		if (bt5 == null) {
			bt5 = new JButton();
			bt5.setActionCommand("5");
			bt5.addActionListener(pB);
			bt5.setBackground(Color.BLACK);
			bt5.setBounds(485, 5, 88, 88);
		}
		return bt5;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt6, JButton
	 */
	private JButton getBt6() {
		if (bt6 == null) {
			bt6 = new JButton();
			bt6.setActionCommand("6");
			bt6.addActionListener(pB);
			bt6.setBackground(Color.BLACK);
			bt6.setBounds(580, 5, 88, 88);
		}
		return bt6;
	}
	
	/**
	 * Inicializa un boton del tablero
	 * @return bt7, JButton
	 */
	private JButton getBt7() {
		if (bt7 == null) {
			bt7 = new JButton();
			bt7.setActionCommand("7");
			bt7.addActionListener(pB);
			bt7.setBackground(Color.BLACK);
			bt7.setBounds(675, 5, 88, 88);
		}
		return bt7;
	}
	
}
