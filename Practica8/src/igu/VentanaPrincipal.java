package igu;

import java.awt.*;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import java.awt.event.*;
import java.io.File;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAbrir;
	private JFileChooser selector;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	
	private JPanel pnNorte;
	private JLabel lbLogo;
	private JSlider slVolumen;
	private JPanel pnVolumen;
	private JLabel lbVolumen;
	private JTextField txVolumen;
	private JPanel pnCentro;
	private JPanel pnLibreria;
	private JLabel lbLibreria;
	private JScrollPane scLibreria;
	private JList<File> listLibreria;
	private DefaultListModel<File> modelolistLibrary;
	private JPanel pnBotonesLibreria;
	private JButton btPasar;
	private JButton btBorrar;
	private JPanel pnPlayList;
	private JLabel lbPlayList;
	private JScrollPane scPlayList;
	private JList<File> listPlayList;
	private DefaultListModel<File> modelolistPlay;
	private JPanel pnBotonesPlayList;
	private JButton btRewind;
	private JButton btStop;
	private JButton btPlay;
	private JButton btForward;
	private JButton btDelete;
	
	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				getMnFile().grabFocus();
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 460);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
	}

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
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmAbrir());
			mnFile.add(getSeparator());
			mnFile.add(getMntmSalir());
		}
		return mnFile;
	}
	private JMenuItem getMntmAbrir() {
		if (mntmAbrir == null) {
			mntmAbrir = new JMenuItem("Abrir...");
			mntmAbrir.setMnemonic('A');
			mntmAbrir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarMusica();
				}
			});
		}
		return mntmAbrir;
	}
	private void cargarMusica() {
		int opcion = getSelector().showOpenDialog(null); 
		if (opcion == JFileChooser.APPROVE_OPTION) {
			for (int i = 0; i < getSelector().getSelectedFiles().length; i++) {
				modelolistLibrary.addElement(getSelector().getSelectedFiles()[i]);
			}
		}
	}
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
		}
		return selector;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.setMnemonic('S');
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('P');
		}
		return mnPlay;
	}
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
		}
		return mnOptions;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
		}
		return mnHelp;
	}
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
	private JLabel getLbLogo() {
		if (lbLogo == null) {
			lbLogo = new JLabel("EII Music Player");
			lbLogo.setFont(new Font("Dialog", Font.BOLD, 20));
			lbLogo.setForeground(new Color(255, 102, 0));
			lbLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		}
		return lbLogo;
	}
	private JSlider getSlVolumen() {
		if (slVolumen == null) {
			slVolumen = new JSlider();
			slVolumen.setFont(new Font("Dialog", Font.BOLD, 14));
			slVolumen.setMinorTickSpacing(5);
			slVolumen.setMajorTickSpacing(20);
			slVolumen.setPaintTicks(true);
			slVolumen.setPaintLabels(true);
			slVolumen.setBackground(Color.BLACK);
			slVolumen.setForeground(Color.WHITE);
			slVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					getTxVolumen().setText(String.valueOf(slVolumen.getValue()));
				}
			});
		}
		return slVolumen;
	}
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
	private JLabel getLbVolumen() {
		if (lbVolumen == null) {
			lbVolumen = new JLabel("Vol:");
			lbVolumen.setBounds(39, 21, 53, 44);
			lbVolumen.setForeground(new Color(255, 102, 0));
			lbVolumen.setFont(new Font("Dialog", Font.BOLD, 22));
		}
		return lbVolumen;
	}
	private JTextField getTxVolumen() {
		if (txVolumen == null) {
			txVolumen = new JTextField();
			txVolumen.setBounds(89, 24, 80, 41);
			txVolumen.setEditable(false);
			txVolumen.setBackground(Color.BLACK);
			txVolumen.setForeground(Color.WHITE);
			txVolumen.setFont(new Font("Dialog", Font.BOLD, 27));
			txVolumen.setColumns(5);
		}
		return txVolumen;
	}
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
	private JPanel getPnLibreria() {
		if (pnLibreria == null) {
			pnLibreria = new JPanel();
			pnLibreria.setBackground(Color.BLACK);
			pnLibreria.setLayout(new BorderLayout(15, 0));
			pnLibreria.add(getLbLibreria(), BorderLayout.NORTH);
			pnLibreria.add(getPnBotonesLibreria(), BorderLayout.SOUTH);
			pnLibreria.add(getScLibreria(), BorderLayout.CENTER);
		}
		return pnLibreria;
	}
	private JLabel getLbLibreria() {
		if (lbLibreria == null) {
			lbLibreria = new JLabel("\u266A Libreria:");	//mapa de caracteres
			lbLibreria.setFont(new Font("Dialog", Font.BOLD, 12));
			lbLibreria.setForeground(new Color(255, 102, 0));
		}
		return lbLibreria;
	}
	private JScrollPane getScLibreria() {
		if (scLibreria == null) {
			scLibreria = new JScrollPane();
			scLibreria.setBorder(new LineBorder(new Color(255, 102, 0), 5, true));
			scLibreria.setViewportView(getListLibreria());
		}
		return scLibreria;
	}
	private JList<File> getListLibreria() {
		if (listLibreria == null) {
			modelolistLibrary = new DefaultListModel<File>();
			listLibreria = new JList<File>(modelolistLibrary);
			listLibreria.setFont(new Font("Tahoma", Font.PLAIN, 14));
			listLibreria.setForeground(Color.WHITE);
			listLibreria.setBackground(Color.BLACK);
		}
		return listLibreria;
	}
	private JPanel getPnBotonesLibreria() {
		if (pnBotonesLibreria == null) {
			pnBotonesLibreria = new JPanel();
			pnBotonesLibreria.setLayout(new GridLayout(0, 2, 5, 0));
			pnBotonesLibreria.add(getBtPasar());
			pnBotonesLibreria.add(getBtBorrar());
		}
		return pnBotonesLibreria;
	}
	private JButton getBtPasar() {
		if (btPasar == null) {
			btPasar = new JButton("Pasar");
			btPasar.setMargin(new Insets(0, 0, 0, 0));
		}
		return btPasar;
	}
	private JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
			btBorrar.setMargin(new Insets(0, 0, 0, 0));
		}
		return btBorrar;
	}
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
	private JLabel getLbPlayList() {
		if (lbPlayList == null) {
			lbPlayList = new JLabel("\u266A PlayList:");
			lbPlayList.setFont(new Font("Dialog", Font.BOLD, 12));
			lbPlayList.setForeground(new Color(255, 102, 0));
		}
		return lbPlayList;
	}
	private JScrollPane getScPlayList() {
		if (scPlayList == null) {
			scPlayList = new JScrollPane();
			scPlayList.setBorder(new LineBorder(new Color(255, 102, 0), 5, true));
			scPlayList.setViewportView(getListPlayList());
		}
		return scPlayList;
	}
	private JList<File> getListPlayList() {
		if (listPlayList == null) {
			modelolistPlay = new DefaultListModel<File>();
			listPlayList = new JList<File>(modelolistPlay);
			listPlayList.setBackground(Color.BLACK);
			listPlayList.setForeground(Color.WHITE);
			listPlayList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return listPlayList;
	}
	private JPanel getPnBotonesPlayList() {
		if (pnBotonesPlayList == null) {
			pnBotonesPlayList = new JPanel();
			pnBotonesPlayList.setLayout(new GridLayout(0, 5, 5, 0));
			pnBotonesPlayList.add(getBtRewind());
			pnBotonesPlayList.add(getBtStop());
			pnBotonesPlayList.add(getBtPlay());
			pnBotonesPlayList.add(getBtForward());
			pnBotonesPlayList.add(getBtDelete());
		}
		return pnBotonesPlayList;
	}
	private JButton getBtRewind() {
		if (btRewind == null) {
			btRewind = new JButton();
			btRewind.setText("\u25C4\u25C4");
			btRewind.setMargin(new Insets(0, 0, 0, 0));
		}
		return btRewind;
	}
	private JButton getBtStop() {
		if (btStop == null) {
			btStop = new JButton();
			btStop.setText("\u25A0");
			btStop.setMargin(new Insets(0, 0, 0, 0));
		}
		return btStop;
	}
	private JButton getBtPlay() {
		if (btPlay == null) {
			btPlay = new JButton();
			btPlay.setText("\u25BA");
			btPlay.setMargin(new Insets(0, 0, 0, 0));
		}
		return btPlay;
	}
	private JButton getBtForward() {
		if (btForward == null) {
			btForward = new JButton();
			btForward.setText("\u25BA\u25BA");
			btForward.setMargin(new Insets(0, 0, 0, 0));
		}
		return btForward;
	}
	private JButton getBtDelete() {
		if (btDelete == null) {
			btDelete = new JButton();
			btDelete.setText("Del");
			btDelete.setMargin(new Insets(0, 0, 0, 0));
		}
		return btDelete;
	}
	
}
