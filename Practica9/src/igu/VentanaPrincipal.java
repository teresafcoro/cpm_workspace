package igu;

import java.awt.*;
import java.util.List;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import player.MusicPlayer;
import player.MyFile;
import java.awt.event.*;
import java.io.File;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MusicPlayer mP;
	
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
	private JButton Clear;
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
		
		mP = new MusicPlayer();
		
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
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
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
	private void cargarMusica() {
		int opcion = getSelector().showOpenDialog(null); 
		if (opcion == JFileChooser.APPROVE_OPTION) {
			for (int i = 0; i < getSelector().getSelectedFiles().length; i++) {
				if (! estaLibreria(getSelector().getSelectedFiles()[i]) )
					modelolistLibrary.addElement(new MyFile(getSelector().getSelectedFiles()[i]));
			}
		}
	}
	private boolean estaLibreria(File file) {
		for (int i = 0; i < modelolistLibrary.size(); i++) {
			if (file.equals(modelolistLibrary.get(i).getF()))
				return true;
		}
		return false;
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
			mnOptions.add(getMntmrbAleatorio());
		}
		return mnOptions;
	}
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
					setVolumen(slVolumen.getValue());
				}
			});
		}
		return slVolumen;
	}
	private void setVolumen(int vol) {
		mP.setVolume(vol, getSlVolumen().getMaximum());
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
			txVolumen.setText(String.valueOf(getSlVolumen().getValue()));
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
			pnLibreria.add(getLbLibrery(), BorderLayout.NORTH);
			pnLibreria.add(getPnBotonesLibreria(), BorderLayout.SOUTH);
			pnLibreria.add(getScLibreria(), BorderLayout.CENTER);
		}
		return pnLibreria;
	}
	private JLabel getLbLibrery() {
		if (lbLibrery == null) {
			lbLibrery = new JLabel("\u266A Librery:");	//mapa de caracteres
			lbLibrery.setFont(new Font("Dialog", Font.BOLD, 12));
			lbLibrery.setForeground(new Color(255, 102, 0));
		}
		return lbLibrery;
	}
	private JScrollPane getScLibreria() {
		if (scLibreria == null) {
			scLibreria = new JScrollPane();
			scLibreria.setBorder(new LineBorder(new Color(255, 102, 0), 5, true));
			scLibreria.setViewportView(getListLibreria());
		}
		return scLibreria;
	}
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
	private void añadir() {
		List<MyFile> selected = getListLibreria().getSelectedValuesList();
		for (int i = 0; i < selected.size(); i++) {
			if (! estaPlaylist(selected.get(i).getF()) )
				modelolistPlay.addElement(selected.get(i));
		}
	}
	private boolean estaPlaylist(File file) {
		for (int i = 0; i < modelolistPlay.size(); i++) {
			if (file.equals(modelolistPlay.get(i).getF()))
				return true;
		}
		return false;
	}
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
	private void borrar() {
		List<MyFile> selected = getListLibreria().getSelectedValuesList();
		for (int i = 0; i < selected.size(); i++)
			modelolistLibrary.removeElement(selected.get(i));
	}
	private JButton getClear() {
		if (Clear == null) {
			Clear = new JButton("Clear");
			Clear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelolistLibrary.removeAllElements();
				}
			});
			Clear.setMnemonic('C');
			Clear.setMargin(new Insets(0, 0, 0, 0));
		}
		return Clear;
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
	private void rewind() {
		if (mP.isAleatorio()) {
			int index = (int)(Math.random()*modelolistPlay.size());
			while ( index == modelolistPlay.indexOf(mP.getCancionSonando()) )
				index = (int)(Math.random()*modelolistPlay.size());
			mP.play(modelolistPlay.get(index).getF());
			mP.setCancionSonando(modelolistPlay.get(index).getF());
			getListPlayList().setSelectedIndex(index);
		}
		else {
			if (getListPlayList().getSelectedIndex() == 0)
				getListPlayList().setSelectedIndex(getListPlayList().getLastVisibleIndex());
			else
				getListPlayList().setSelectedIndex(getListPlayList().getSelectedIndex()-1);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
	}
	private JButton getBtStop() {
		if (btStop == null) {
			btStop = new JButton();
			btStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mP.stop();
					mP.setCancionSonando(null);
				}
			});
			btStop.setText("\u25A0");
			btStop.setMargin(new Insets(0, 0, 0, 0));
		}
		return btStop;
	}
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
	private void play() {
		if (mP.isAleatorio()) {
			int index = (int)(Math.random()*modelolistPlay.size());
			mP.play(modelolistPlay.get(index).getF());
			mP.setCancionSonando(modelolistPlay.get(index).getF());
			getListPlayList().setSelectedIndex(index);
		}
		else {
			if (getListPlayList().getSelectedIndex() == -1)
				getListPlayList().setSelectedIndex(0);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
		this.setVolumen(getSlVolumen().getValue());
	}
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
	private void forward() {
		if (mP.isAleatorio()) {
			int index = (int)(Math.random()*modelolistPlay.size());
			while ( index == modelolistPlay.indexOf(mP.getCancionSonando()) )
				index = (int)(Math.random()*modelolistPlay.size());
			mP.play(modelolistPlay.get(index).getF());
			mP.setCancionSonando(modelolistPlay.get(index).getF());
			getListPlayList().setSelectedIndex(index);
		}
		else {
			if (getListPlayList().getSelectedIndex() == getListPlayList().getLastVisibleIndex())
				getListPlayList().setSelectedIndex(0);
			else
				getListPlayList().setSelectedIndex(getListPlayList().getSelectedIndex()+1);
			mP.play(getListPlayList().getSelectedValue().getF());
			mP.setCancionSonando(getListPlayList().getSelectedValue().getF());
		}
	}
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
	private void delete() {
		List<MyFile> selected = getListPlayList().getSelectedValuesList();
		File cancionSonando = mP.getCancionSonando();
		for (int i = 0; i < selected.size(); i++) {
			if (selected.get(i).getF().equals(cancionSonando)) {
				mP.stop();
				mP.setCancionSonando(null);
			}
			modelolistPlay.removeElement(selected.get(i));
		}
	}

}
