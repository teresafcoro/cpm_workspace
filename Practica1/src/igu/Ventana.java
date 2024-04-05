package igu;

import java.awt.*;
import javax.swing.*;

/**
 * Creación de una Aplicación con Interfaz Gráfica de Usuario
 * @author UO263728
 */
public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pnContenidos;	// panel donde se incorporaran los componentes
	private JLabel jlNombre;
	private JTextField jtNombre;
	private JButton btAceptar;
	private JButton btCancelar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Ventana v = new Ventana();
		v.setVisible(true);
	}
	
	/**
	 * Constructor
	 * Inicializa y da propiedades al marco y panel de la interfaz.
	 * También se añaden los componentes al panel.
	 */
	public Ventana() {
		setBounds(100, 100, 450, 300);
		setTitle("Ventana Principal");
		
		pnContenidos = new JPanel();
		setContentPane(pnContenidos);
		pnContenidos.setBackground(Color.WHITE);
		pnContenidos.setLayout(null);
		
		jlNombre = new JLabel();
		jlNombre.setText("Introduzca su nombre:");
		jlNombre.setBounds(50, 50, 200, 100);
		pnContenidos.add(jlNombre);
		setLocationRelativeTo(null);
		
		jtNombre = new JTextField();
		jtNombre.setBounds(200, 80, 200, 30);
		pnContenidos.add(jtNombre);
		setLocationRelativeTo(null);
		
		btAceptar = new JButton();
		btAceptar.setText("Aceptar");
		btAceptar.setForeground(Color.blue);
		btAceptar.setBounds(180, 200, 100, 30);
		pnContenidos.add(btAceptar);
		setLocationRelativeTo(null);
		
		btCancelar = new JButton();
		btCancelar.setText("Cancelar");
		btCancelar.setForeground(Color.blue);
		btCancelar.setBounds(300, 200, 100, 30);
		pnContenidos.add(btCancelar);
		setLocationRelativeTo(null);
	}

}
