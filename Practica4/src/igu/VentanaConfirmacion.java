package igu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import logica.*;

/**
 * Ultima ventana de la interfaz para finalizar el pedido
 * @author UO263728
 */
public class VentanaConfirmacion extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel pnContenido;
	private JLabel lbIcono;
	private JLabel lbMensaje;
	private JLabel lbTotalPagar;
	private JTextField txTotalAPagar;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private JButton btFinalizar;
	
	private final String codigo = FileUtil.setFileName();
	private VentanaRegistro vr;
	private String tipoPedido;

	/**
	 * Create the frame.
	 */
	public VentanaConfirmacion(VentanaRegistro vr) {
		this.vr = vr;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.PNG")));
		setModal(true);
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("McDonald's: Confirmacion del pedido");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		pnContenido = new JPanel();
		setContentPane(pnContenido);
		pnContenido.setBackground(Color.WHITE);
		pnContenido.setLayout(null);
		pnContenido.add(getLbIcono());
		pnContenido.add(getLbMensaje());
		pnContenido.add(getLbTotalPagar());
		pnContenido.add(getTxTotalAPagar());
		pnContenido.add(getLbCodigo());
		pnContenido.add(getTxCodigo());
		pnContenido.add(getBtFinalizar());
	}
	
	private JLabel getLbIcono() {
		if (lbIcono == null) {
			lbIcono = new JLabel("New label");
			lbIcono.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lbIcono.setBounds(20, 51, 46, 44);
		}
		return lbIcono;
	}
	private JLabel getLbMensaje() {
		if (lbMensaje == null) {
			lbMensaje = new JLabel("Estamos procesando su pedido");
			lbMensaje.setFont(new Font("Tahoma", Font.BOLD, 20));
			lbMensaje.setBounds(76, 54, 332, 31);
		}
		return lbMensaje;
	}
	private JLabel getLbTotalPagar() {
		if (lbTotalPagar == null) {
			lbTotalPagar = new JLabel("Total a pagar:");
			lbTotalPagar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbTotalPagar.setEnabled(true);
			lbTotalPagar.setBounds(112, 109, 105, 25);
		}
		return lbTotalPagar;
	}
	public JTextField getTxTotalAPagar() {
		if (txTotalAPagar == null) {
			txTotalAPagar = new JTextField();
			txTotalAPagar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txTotalAPagar.setEditable(false);
			txTotalAPagar.setBackground(SystemColor.controlHighlight);
			txTotalAPagar.setBounds(262, 109, 86, 28);
			txTotalAPagar.setColumns(10);
		}
		return txTotalAPagar;
	}
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("C\u00F3digo de recogida:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lbCodigo.setBounds(112, 156, 130, 22);
		}
		return lbCodigo;
	}
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setBackground(SystemColor.controlHighlight);
			txCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txCodigo.setText(codigo);
			txCodigo.setEditable(false);
			txCodigo.setBounds(262, 156, 86, 28);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	private JButton getBtFinalizar() {
		if (btFinalizar == null) {
			btFinalizar = new JButton("Finalizar");
			btFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pedido pedido = vr.getVP().getPedido();
					pedido.setTipoPedido(tipoPedido);
					pedido.grabarPedido(codigo);
					vr.getVP().inicializar();
					vr.dispose();
					dispose();
				}
			});
			btFinalizar.setMnemonic('F');
			btFinalizar.setBackground(new Color(0, 128, 0));
			btFinalizar.setForeground(Color.WHITE);
			btFinalizar.setBounds(314, 216, 89, 26);
		}
		return btFinalizar;
	}

	public void setTipoPedido(String selectedButton) {
		this.tipoPedido = selectedButton;
	}
	
}
