package Modelo;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNombre extends JFrame {

	/**
	 * @author Juan 
	 * Crea la ventana con todas sus medidas, el JTextField y JLabel en la que pide el nombre del usuario
	 *
	 */
	private JLabel nombreLabel;
	private JTextField nombreField;
	private JButton boton;

	public VentanaNombre() {

		createTextField();
		createButton();
		createPanel();
		setBounds(430, 50, 500, 100);
	}

	private void createTextField() {
		nombreLabel = new JLabel("Por favor digite su nombre, guardelo y cierre la ventana: ");
		final int FIELD_WIDTH = 15;
		nombreField = new JTextField(FIELD_WIDTH);

	}

	private void createButton() {
		boton = new JButton("Guardar nombre");
		ActionListener listener = new GuardarNombreListener();
		boton.addActionListener(listener);
	}

	private void createPanel() {
		JPanel panel = new JPanel();
		panel.add(nombreLabel);
		panel.add(nombreField);
		panel.add(boton);
		add(panel);
	}

	/**
	 * 
	 * Guarda el nombre del usuario en la variable nombreUsuario
	 *
	 */
	public class GuardarNombreListener implements ActionListener {

		public String nombreUsuario;

		public void actionPerformed(ActionEvent event) {
			String nombreUsuario = nombreField.getText();
			jugadorGanador(nombreUsuario);

			Tablero claseTablero = new Tablero();
			claseTablero.metodoPrueba(nombreUsuario);
		}

		public String jugadorGanador(String nombreUsuario) {

			String hola;
			hola = nombreUsuario;
			return hola;
		}

		public void retornoNombre() {
			nombreUsuario = jugadorGanador(nombreUsuario);
		}

	}

}
