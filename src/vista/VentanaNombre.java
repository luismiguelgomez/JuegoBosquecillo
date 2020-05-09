package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNombre extends JFrame {

	/**
	 * @author Juan
	 * Crea la ventana con todas sus medidas, el JTextField y JLabel
	 */
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 100;

	private JLabel nombreLabel;
	private JTextField nombreField;

	public VentanaNombre() {

		createTextField();
		createPanel();

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	/**
	 * Crea el Label en el que pide el nombre de usuario Crea el JTextField para que
	 * el usuario escriba el nombre
	 */

	private void createTextField() {
		nombreLabel = new JLabel("Por favor digite su nombre: ");
		// String nombreUsuario = nombreField.getText();

		final int FIELD_WIDTH = 15;
		nombreField = new JTextField(FIELD_WIDTH);

	}

	/**
	 * Crea el panel y le añade el Label y el Field del nombre
	 */

	private void createPanel() {
		JPanel panel = new JPanel();
		panel.add(nombreLabel);
		panel.add(nombreField);
		add(panel);
	}

	/**
	 * Guarda el nombre del usuario en la variable nombreUsuario
	 */

	public void actionPerformed(ActionEvent event) {
		String nombreUsuario = nombreField.getText();

	}
}
