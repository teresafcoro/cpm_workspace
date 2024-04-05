package igu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import logica.*;

/**
 * Creacion de una aplicacion con Interfaz Grafica de Usuario utilizando WindowBuilder
 * VentanaConfirmacion de la TPV de comida rapida McDonald's España
 * Finaliza la ejecucion de la TPV guardando primero el pedido realizado
 * @author MariaTeresaFernandezCoro-UO263728-71728885G
 */
public class VentanaConfirmacion extends JDialog {

	// Version
	private static final long serialVersionUID = 1L;

	// Componentes de la interfaz
	private JPanel pnContenido;
	private JLabel lbIcono;
	private JLabel lbMensaje;
	private JLabel lbTotalPagar;
	private JTextField txTotalAPagar;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private JButton btFinalizar;
	
	// Nombre final del pedido creado
	private final String codigo = FileUtil.setFileName();
	
	// VentanaRegistro de la interfaz
	private VentanaRegistro vr;

	/**
	 * Constructor de la interfaz
	 * @param vr, VentanaRegistro
	 */
	public VentanaConfirmacion(VentanaRegistro vr) {
		this.vr = vr;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España: Confirmacion del pedido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		pnContenido = new JPanel();
		pnContenido.setBackground(Color.WHITE);
		pnContenido.setLayout(null);
		setContentPane(pnContenido);
		pnContenido.add(getLbIcono());
		pnContenido.add(getLbMensaje());
		pnContenido.add(getLbTotalPagar());
		pnContenido.add(getTxTotalAPagar());
		pnContenido.add(getLbCodigo());
		pnContenido.add(getTxCodigo());
		pnContenido.add(getBtFinalizar());
	}
	
	/**
	 * Inicializa un icono 
	 * @return lbIcono, JLabel
	 */
	private JLabel getLbIcono() {
		if (lbIcono == null) {
			lbIcono = new JLabel();
			lbIcono.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lbIcono.setBounds(27, 41, 46, 44);
		}
		return lbIcono;
	}
	
	/**
	 * Inicializa un mensaje de confirmacion del pedido
	 * @return lbMensaje, JLabel
	 */
	private JLabel getLbMensaje() {
		if (lbMensaje == null) {
			lbMensaje = new JLabel("Estamos procesando su pedido");
			lbMensaje.setFont(new Font("Tahoma", Font.BOLD, 20));
			lbMensaje.setBounds(76, 54, 332, 31);
		}
		return lbMensaje;
	}
	
	/**
	 * Inicializa un mensaje de precio final del pedido 
	 * @return lbTotalPagar, JLabel
	 */
	private JLabel getLbTotalPagar() {
		if (lbTotalPagar == null) {
			lbTotalPagar = new JLabel("Total a pagar:");
			lbTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbTotalPagar.setBounds(112, 109, 105, 25);
		}
		return lbTotalPagar;
	}
	
	/**
	 * Inicializa un cuadro de texto 
	 * @return txTotalPagar, JTextField
	 */
	protected JTextField getTxTotalAPagar() {
		if (txTotalAPagar == null) {
			txTotalAPagar = new JTextField();
			txTotalAPagar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txTotalAPagar.setEditable(false);
			txTotalAPagar.setBackground(SystemColor.WHITE);
			txTotalAPagar.setBounds(262, 109, 86, 28);
			txTotalAPagar.setColumns(10);
		}
		return txTotalAPagar;
	}
	
	/**
	 * Inicializa un mensaje de codigo de pedido
	 * @return lbCodigo, JLabel
	 */
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("Código de recogida:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbCodigo.setBounds(112, 156, 130, 22);
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
			txCodigo.setText(codigo);
			txCodigo.setEditable(false);
			txCodigo.setBounds(262, 156, 86, 28);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	
	/**
	 * Inicializa un boton de finalizacion del pedido
	 * Graba el pedido y finaliza la ejecucion de la TPV
	 * @return btFinalizar, JButton
	 */
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedido pedido = vr.getVP().getPedido();
					pedido.grabarPedido(codigo);
					System.exit(0);
				}
			});
			btFinalizar.setMnemonic('F');
			btFinalizar.setBackground(new Color(0, 128, 0));
			btFinalizar.setForeground(Color.WHITE);
			btFinalizar.setBounds(314, 216, 90, 25);
		}
		return btFinalizar;
	}
	
}
