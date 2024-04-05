package igu;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Properties;
import javax.help.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import player.*;
import java.io.*;
import java.net.*;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * VentanaPrincipal del reproductor de musica mp3, EII MusicPlayer
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class VentanaPrincipal extends JFrame {

	// Version
	private static final long serialVersionUID = 1L;
	
	// Componentes de la interfaz
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JFileChooser selector;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JRadioButtonMenuItem mntmrbAleatorio;
	private JMenu mnHelp;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;
	private JPanel pnNorte;
	private JLabel lbLogo;
	private JSlider slVolumen;
	private JPanel pnVolumen;
	private JLabel lbVolumen;
	private JTextField txVolumen;
	private JPanel pnCentro;
	private JPanel pnLibreria;
	private JLabel lbLibrery;
	private JScrollPane scLibreria;
	private JList<MyFile> listLibreria;
	private DefaultListModel<MyFile> modelolistLibrary;
	private JPanel pnBotonesLibreria;
	private JButton btAdd;
	private JButton btDelete;
	private JButton btClear;
	private JPanel pnPlayList;
	private JLabel lbPlayList;
	private JScrollPane scPlayList;
	private JList<MyFile> listPlayList;
	private DefaultListModel<MyFile> modelolistPlay;
	private JPanel pnBotonesPlayList;
	private JButton btRewind;
	private JButton btPlay;
	private JButton btStop;
	private JButton btForward;
	private JButton btDel;
	
	// Clase MusicPlayer
	private MusicPlayer mP;
	
	/**
	 * Metodo principal de la aplicacion, permite su ejecucion
	 * Se establece un look and feel determinado
	 * @param args, String[]
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties p = new Properties();
					p.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(p);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
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
		// Accion de la ventana, establece el foco en el menu File
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				getMnFile().grabFocus();
			}
		});
		
		// Inicializacion de las clases usadas
		mP = new MusicPlayer();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 460);
		setLocationRelativeTo(null);
		setResizable(false);
		setJMenuBar(getMenuBar_1());
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		
		// Carga el sistema de ayuda del EII MusicPlayer
		cargaAyuda();
	}
	
	/**
	 * Sistema de ayuda del EII MusicPlayer
	 */
	private void cargaAyuda() {
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ayuda no encontrada");
			return;
		}
		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();
		hb.enableHelpKey(getRootPane(), "introduccion", hs);
		hb.enableHelpOnButton(getMntmContents(), "introduccion", hs);
		hb.enableHelp(getListLibreria(), "añadir", hs);
		hb.enableHelp(getBtPlay(), "reproducir", hs);
		hb.enableHelp(getSlVolumen(), "modificarVolumen", hs);
	}

	/**
	 * Inicializa la barra de menu de la interfaz
	 * @return menuBar, JMenuBar
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	
	/**
	 * Inicializa la opcion de menu File
	 * @return mnFile, JMenu
	 */
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	
	/**
	 * Inicializa el elemento de menu Open
	 * Se encarga de abrir el explorador de archivos
	 * @return mntmOpen, JMenuItem
	 */
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open...");
			mntmOpen.setMnemonic('O');
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarMusica();
				}
			});
		}
		return mntmOpen;
	}
	
	/**
	 * Carga la musica seleccionada del explorador de archivos del usuario
	 */
	private void cargarMusica() {
		int opcion = getSelector().showOpenDialog(null); 
		if (opcion == JFileChooser.APPROVE_OPTION) {
			for (int i = 0; i < getSelector().getSelectedFiles().length; i++) {
				if (! estaLibreria(getSelector().getSelectedFiles()[i]) )
					modelolistLibrary.addElement(new MyFile(getSelector().getSelectedFiles()[i]));
			}
		}
	}
	
	/**
	 * Comprueba si el fichero ya se añadio
	 * @param file, File
	 * @return boolean
	 */
	private boolean estaLibreria(File file) {
		for (int i = 0; i < modelolistLibrary.size(); i++) {
			if (file.equals(modelolistLibrary.get(i).getF()))
				return true;
		}
		return false;
	}
	
	/**
	 * Selector de ficheros
	 * @return selector, JFileChooser
	 */
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3","mp3"));
			selector.setCurrentDirectory(new File(System.getProperty("user.home")+"/Music"));
		}
		return selector;
	}
	
	/**
	 * Inicializa el separador de elementos de menu
	 * @return separator, JSeparator
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	
	/**
	 * Inicializa el elemento de menu Exit
	 * Finaliza la ejecucion del EII MusicPlayer
	 * @return mntmExit, JMenuItem
	 */
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setMnemonic('E');
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}
	
	/**
	 * Inicializa la opcion de menu Play
	 * @return mnPlay, JMenu
	 */
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('P');
		}
		return mnPlay;
	}
	
	/**
	 * Inicializa la opcion de menu Options
	 * @return mnOptions, JMenu
	 */
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
			mnOptions.add(getMntmrbAleatorio());
		}
		return mnOptions;
	}
	
	/**
	 * Inicializa el radio boton de menu Aleatorio
	 * Permite reproducir la musica de forma aleatoria
	 * @return mntmrbAleatorio, JRadioButtonMenuItem
	 */
	private JRadioButtonMenuItem getMntmrbAleatorio() {
		if (mntmrbAleatorio == null) {
			mntmrbAleatorio = new JRadioButtonMenuItem("Aleatorio");
			mntmrbAleatorio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (mntmrbAleatorio.isSelected())
						mP.setAleatorio(true);
					else
						mP.setAleatorio(false);
				}
			});
			mntmrbAleatorio.setMnemonic('A');
		}
		return mntmrbAleatorio;
	}
	
	/**
	 * Inicializa la opcion de menu Help
	 * @return mnHelp, JMenu
	 */
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	
	/**
	 * Inicializa el elemento de menu Contents
	 * @return mntmContents, JMenuItem
	 */
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContents.setMnemonic('C');
		}
		return mntmContents;
	}
	
	/**
	 * Inicializa el separador de elementos de menu
	 * @return separator_1, JSeparator
	 */
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	
	/**
	 * Inicializa el elemento de menu About
	 * @return mntmAbout, JMenuItem
	 */
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('A');
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mensaje = "Aplicación Reproductor de música MP3\nPrácticas CPM 19-20\nEII Oviedo";
					JOptionPane.showMessageDialog(null, mensaje, "Acerca de" , JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAbout;
	}
	
	/**
	 * Inicializa el panel norte de la interfaz
	 * @return pnNorte, JPanel
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			pnNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnNorte.setBackground(Color.BLACK);
			pnNorte.setLayout(new GridLayout(0, 3, 0, 0));
			pnNorte.add(getLbLogo());
			pnNorte.add(getSlVolumen());
			pnNorte.add(getPnVolumen());
		}
		return pnNorte;
	}
	
	/**
	 * Inicializa el logo del EII MusicPlayer
	 * @return lbLogo, JLabel
	 */
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("EII Music Player");
			lbLogo.setFont(new Font("Dialog", Font.BOLD, 20));
			lbLogo.setForeground(new Color(255, 102, 0));
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		}
		return lbLogo;
	}
	
	/**
	 * Inicializa el Slider que permite modificar el volumen
	 * @return slVolumen, JSlider
	 */
	private JSlider getSlVolumen() {
		if (slVolumen == null) {
			slVolumen = new JSlider();
			slVolumen.setPaintTicks(true);
			slVolumen.setPaintLabels(true);
			slVolumen.setFont(new Font("Dialog", Font.BOLD, 14));
			slVolumen.setMinorTickSpacing(5);
			slVolumen.setMajorTickSpacing(20);
			slVolumen.setBackground(Color.BLACK);
			slVolumen.setForeground(Color.WHITE);
			slVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					modificoVol();
				}
			});
		}
		return slVolumen;
	}
	
	/**
	 * Modifica el volumen
	 */
	private void modificoVol() {
		getTxVolumen().setText(String.valueOf(getSlVolumen().getValue()));
		mP.setVolume(getSlVolumen().getValue(), getSlVolumen().getMaximum());
	}
	
	/**
	 * Inicializa el panel Volumen
	 * @return pnVolumen, JPanel
	 */
	private JPanel getPnVolumen() {
		if (pnVolumen == null) {
			pnVolumen = new JPanel();
			pnVolumen.setBackground(Color.BLACK);
			pnVolumen.setForeground(new Color(255, 102, 0));
			pnVolumen.setLayout(null);
			pnVolumen.add(getLbVolumen());
			pnVolumen.add(getTxVolumen());
		}
		return pnVolumen;
	}
	
	/**
	 * Inicializa el mensaje Volumen
	 * @return lbVolumen, JLabel
	 */
	private JLabel getLbVolumen() {
		if (lbVolumen == null) {
			lbVolumen = new JLabel("Vol:");
			lbVolumen.setBounds(39, 21, 53, 44);
			lbVolumen.setForeground(new Color(255, 102, 0));
			lbVolumen.setFont(new Font("Dialog", Font.BOLD, 22));
		}
		return lbVolumen;
	}
	
	/**
	 * Inicializa el cuadro de texto Volumen
	 * @return txVolumen, JTextField
	 */
	private JTextField getTxVolumen() {
		if (txVolumen == null) {
			txVolumen = new JTextField();
			txVolumen.setBounds(89, 24, 80, 41);
			txVolumen.setEditable(false);
			txVolumen.setBackground(Color.BLACK);
			txVolumen.setForeground(Color.WHITE);
			txVolumen.setFont(new Font("Dialog", Font.BOLD, 27));
			txVolumen.setText(String.valueOf(getSlVolumen().getValue()));
			txVolumen.setColumns(5);
		}
		return txVolumen;
	}
	
	/**
	 * Inicializa el panel Norte
	 * @return pnNorte, JPanel
	 */
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.BLACK);
			pnCentro.setLayout(new GridLayout(1, 2, 0, 0));
			pnCentro.add(getPnLibreria());
			pnCentro.add(getPnPlayList());
		}
		return pnCentro;
	}
	
	/**
	 * Inicializa el panel libreria
	 * @return pnLibreria, JPanel
	 */
	private JPanel getPnLibreria() {
		if (pnLibreria == null) {
			pnLibreria = new JPanel();
			pnLibreria.setBackground(Color.BLACK);
			pnLibreria.setLayout(new BorderLayout(15, 0));
			pnLibreria.add(getLbLibrery(), BorderLayout.NORTH);
			pnLibreria.add(getScLibreria(), BorderLayout.CENTER);
			pnLibreria.add(getPnBotonesLibreria(), BorderLayout.SOUTH);
		}
		return pnLibreria;
	}
	
	/**
	 * Inicializa el mensaje libreria
	 * @return lbLibrary, JLabel
	 */
	private JLabel getLbLibrery() {
		if (lbLibrery == null) {
			lbLibrery = new JLabel("\u266A Librery:");	//mapa de caracteres
			lbLibrery.setFont(new Font("Dialog", Font.BOLD, 12));
			lbLibrery.setForeground(new Color(255, 102, 0));
		}
		return lbLibrery;
	}
	
	/**
	 * Inicializa el panel de scroll libreria
	 * @return scLibreria, JScrollPane
	 */
	private JScrollPane getScLibreria() {
		if (scLibreria == null) {
			scLibreria = new JScrollPane();
			scLibreria.setBorder(new LineBorder(new Color(255, 102, 0), 5, true));
			scLibreria.setViewportView(getListLibreria());
		}
		return scLibreria;
	}
	
	/**
	 * Inicializa la lista de ficheros MyFile libreria
	 * @return listLibreria, JList<MyFile>
	 */
	private JList<MyFile> getListLibreria() {
		if (listLibreria == null) {
			modelolistLibrary = new DefaultListModel<MyFile>();
			listLibreria = new JList<MyFile>(modelolistLibrary);
			listLibreria.setFont(new Font("Tahoma", Font.PLAIN, 14));
			listLibreria.setForeground(Color.WHITE);
			listLibreria.setBackground(Color.BLACK);
		}
		return listLibreria;
	}
	
	/**
	 * Inicializa el panel de botones de la libreria
	 * @return pnBotonesLibreria, JPanel
	 */
	private JPanel getPnBotonesLibreria() {
		if (pnBotonesLibreria == null) {
			pnBotonesLibreria = new JPanel();
			pnBotonesLibreria.setLayout(new GridLayout(0, 3, 5, 0));
			pnBotonesLibreria.add(getBtAdd());
			pnBotonesLibreria.add(getBtDelete());
			pnBotonesLibreria.add(getClear());
		}
		return pnBotonesLibreria;
	}
	
	/**
	 * Inicializa el boton añadir
	 * @return btAdd, JButton
	 */
	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("Add");
			btAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadir();
				}
			});
			btAdd.setMnemonic('A');
			btAdd.setMargin(new Insets(0, 0, 0, 0));
		}
		return btAdd;
	}
	
	/**
	 * Añade un fichero/cancion a la playlist
	 * Siempre que no se encuentre ya
	 */
	private void añadir() {
		for (int i = 0; i < getListLibreria().getSelectedValuesList().size(); i++) {
			if (!estaPlaylist(getListLibreria().getSelectedValuesList().get(i).getF()))
				modelolistPlay.addElement(getListLibreria().getSelectedValuesList().get(i));
		}
	}
	
	/**
	 * Comprueba si la cancion/fichero se encuentra ya en la playlist
	 * @param file, File
	 * @return boolean
	 */
	private boolean estaPlaylist(File file) {
		for (int i = 0; i < modelolistPlay.size(); i++) {
			if (file.equals(modelolistPlay.get(i).getF()))
				return true;
		}
		return false;
	}
	
	/**
	 * Inicializa el boton delete
	 * @return btDelete, JButton
	 */
	private JButton getBtDelete() {
		if (btDelete == null) {
			btDelete = new JButton("Delete");
			btDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrar();
				}
			});
			btDelete.setMnemonic('D');
			btDelete.setMargin(new Insets(0, 0, 0, 0));
		}
		return btDelete;
	}
	
	/**
	 * Elimina la cancion seleccionada de la libreria
	 */
	private void borrar() {
		for (int i = 0; i < getListLibreria().getSelectedValuesList().size(); i++)
			modelolistLibrary.removeElement(getListLibreria().getSelectedValuesList().get(i));
	}
	
	/**
	 * Inicializa el boton clear
	 * Vacia la libreria
	 * @return btClear, JButton
	 */
	private JButton getClear() {
		if (btClear == null) {
			btClear = new JButton("Clear");
			btClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelolistLibrary.removeAllElements();
				}
			});
			btClear.setMnemonic('C');
			btClear.setMargin(new Insets(0, 0, 0, 0));
		}
		return btClear;
	}
	
	/**
	 * Inicializa el panel PlayList
	 * @return pnPlayList, JPanel
	 */
	private JPanel getPnPlayList() {
		if (pnPlayList == null) {
			pnPlayList = new JPanel();
			pnPlayList.setBackground(Color.BLACK);
			pnPlayList.setLayout(new BorderLayout(15, 0));
			pnPlayList.add(getLbPlayList(), BorderLayout.NORTH);
			pnPlayList.add(getPnBotonesPlayList(), BorderLayout.SOUTH);
			pnPlayList.add(getScPlayList(), BorderLayout.CENTER);
		}
		return pnPlayList;
	}
	
	/**
	 * Inicializa el mensaje PlayList
	 * @return lbPlayList, JLabel
	 */
	private JLabel getLbPlayList() {
		if (lbPlayList == null) {
			lbPlayList = new JLabel("\u266A PlayList:");
			lbPlayList.setFont(new Font("Dialog", Font.BOLD, 12));
			lbPlayList.setForeground(new Color(255, 102, 0));
		}
		return lbPlayList;
	}
	
	/**
	 * Inicializa el panel de scroll PlayList
	 * @return scPlayList, JScrollPane
	 */
	private JScrollPane getScPlayList() {
		if (scPlayList == null) {
			scPlayList = new JScrollPane();
			scPlayList.setBorder(new LineBorder(new Color(255, 102, 0), 5, true));
			scPlayList.setViewportView(getListPlayList());
		}
		return scPlayList;
	}
	
	/**
	 * Inicializa la lista de ficheros MyFile PlayList
	 * @return listPlayList, JList<MyFile>
	 */
	private JList<MyFile> getListPlayList() {
		if (listPlayList == null) {
			modelolistPlay = new DefaultListModel<MyFile>();
			listPlayList = new JList<MyFile>(modelolistPlay);
			listPlayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlayList.setBackground(Color.BLACK);
			listPlayList.setForeground(Color.WHITE);
			listPlayList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return listPlayList;
	}
	
	/**
	 * Inicializa el panel de botones de la playlist
	 * @return pnBotonesPlayList, JPanel
	 */
	private JPanel getPnBotonesPlayList() {
		if (pnBotonesPlayList == null) {
			pnBotonesPlayList = new JPanel();
			pnBotonesPlayList.setLayout(new GridLayout(0, 5, 5, 0));
			pnBotonesPlayList.add(getBtRewind());
			pnBotonesPlayList.add(getBtPlay());
			pnBotonesPlayList.add(getBtStop());
			pnBotonesPlayList.add(getBtForward());
			pnBotonesPlayList.add(getBtDel());
		}
		return pnBotonesPlayList;
	}
	
	/**
	 * Inicializa el boton rewind
	 * @return btRewind, JButton
	 */
	private JButton getBtRewind() {
		if (btRewind == null) {
			btRewind = new JButton();
			btRewind.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rewind();
				}
			});
			btRewind.setText("\u25C4\u25C4");
			btRewind.setMargin(new Insets(0, 0, 0, 0));
		}
		return btRewind;
	}
	
	/**
	 * Permite ir a la cancion anterior
	 */
	private void rewind() {
		if (mP.isAleatorio()) {
			int index = (int) Math.random()*modelolistPlay.size();
			while (index == modelolistPlay.indexOf(mP.getCancionSonando()))
				index = (int) Math.random()*modelolistPlay.size();
			getListPlayList().setSelectedIndex(index);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		} else {
			if (getListPlayList().getSelectedIndex() == 0)
				getListPlayList().setSelectedIndex(getListPlayList().getLastVisibleIndex());
			else
				getListPlayList().setSelectedIndex(getListPlayList().getSelectedIndex()-1);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
	}
	
	/**
	 * Inicializa el boton stop
	 * Para la cancion que se encuentre reproduciendose
	 * @return btStop, JButton
	 */
	private JButton getBtStop() {
		if (btStop == null) {
			btStop = new JButton();
			btStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stop();
				}
			});
			btStop.setText("\u25A0");
			btStop.setMargin(new Insets(0, 0, 0, 0));
		}
		return btStop;
	}
	
	/**
	 * Para la cancion que se encuentre reproduciendose
	 */
	private void stop() {
		mP.stop();
		mP.setCancionSonando(null);
	}
	
	/**
	 * Inicializa el boton play
	 * Comienza la reproduccion de una cancion
	 * @return btPlay, JButton
	 */
	private JButton getBtPlay() {
		if (btPlay == null) {
			btPlay = new JButton();
			btPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					play();
				}
			});
			btPlay.setText("\u25BA");
			btPlay.setMargin(new Insets(0, 0, 0, 0));
		}
		return btPlay;
	}
	
	/**
	 * Reproduce una cancion
	 */
	private void play() {
		if (mP.isAleatorio()) {
			int index = (int) Math.random()*modelolistPlay.size();
			while (index == modelolistPlay.indexOf(mP.getCancionSonando()))
				index = (int) Math.random()*modelolistPlay.size();
			getListPlayList().setSelectedIndex(index);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		} else {
			if (getListPlayList().getSelectedIndex() == -1)
				getListPlayList().setSelectedIndex(0);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
		modificoVol();
	}
	
	/**
	 * Inicializa el boton forward
	 * @return btForward, JButton
	 */
	private JButton getBtForward() {
		if (btForward == null) {
			btForward = new JButton();
			btForward.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					forward();
				}
			});
			btForward.setText("\u25BA\u25BA");
			btForward.setMargin(new Insets(0, 0, 0, 0));
		}
		return btForward;
	}
	
	/**
	 * Pasa a la siguiente cancion
	 */
	private void forward() {
		if (mP.isAleatorio()) {
			int index = (int) Math.random()*modelolistPlay.size();
			while ( index == modelolistPlay.indexOf(mP.getCancionSonando()) )
				index = (int) Math.random()*modelolistPlay.size();
			getListPlayList().setSelectedIndex(index);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		} else {
			if (getListPlayList().getSelectedIndex() == getListPlayList().getLastVisibleIndex())
				getListPlayList().setSelectedIndex(0);
			else
				getListPlayList().setSelectedIndex(getListPlayList().getSelectedIndex()+1);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
	}
	
	/**
	 * Inicializa el boton del
	 * @return btDel, JButton
	 */
	private JButton getBtDel() {
		if (btDel == null) {
			btDel = new JButton();
			btDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete();
				}
			});
			btDel.setMnemonic('l');
			btDel.setText("Del");
			btDel.setMargin(new Insets(0, 0, 0, 0));
		}
		return btDel;
	}
	
	/**
	 * Elimina de la lista de playList la cancion seleccionada
	 */
	private void delete() {
		List<MyFile> selected = getListPlayList().getSelectedValuesList();
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getF().equals(mP.getCancionSonando()))
				stop();
			modelolistPlay.removeElement(selected.get(i));
		}
	}

}
