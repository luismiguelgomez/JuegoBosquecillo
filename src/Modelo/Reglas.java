package Modelo;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Juan
 * Muestra la ventana de las reglas del juego
 *
 */
public class Reglas extends JFrame {


	/**
	 * Metodo constructor de mostrar Reglas
	 */
	public Reglas() {
		muestraReglas();
		createPanel();
		setBounds(430, 200, 500, 250);
	}

	/**
	 * crea el panel
	 */
	public void muestraReglas() {

		createPanel();
	}

	/**
	 * Metodo que muestra el panel y los datos de las reglas del juego
	 */
	private void createPanel() {
		JPanel panel = new JPanel();
		add(panel);
		setVisible(true);
		JLabel Label_1 = new JLabel("Reglas del juego");
		Label_1.setBounds(135, 11, 252, 32);
		getContentPane().add(Label_1);
		Label_1.setFont(new Font("Wide Latin", Font.PLAIN, 16));

		JLabel lblBosquecilloEsUn = new JLabel("Bosquecillo es un juego que consiste en recolectar cierta cantidad");
		lblBosquecilloEsUn.setBounds(35, 50, 390, 32);
		getContentPane().add(lblBosquecilloEsUn);

		JLabel lblNoSePuede = new JLabel("El limite de movimientos esta dado por las dimensiones del tablero.");
		lblNoSePuede.setBounds(35, 160, 453, 32);
		getContentPane().add(lblNoSePuede);

		JLabel lblDebeRecogerLos = new JLabel(
				"Debe recoger los premios en un orden especifico. En caso de\r\n encontrar");
		lblDebeRecogerLos.setBounds(35, 95, 410, 26);
		getContentPane().add(lblDebeRecogerLos);

		JLabel lblNewLabel_1 = new JLabel("de premios con un limite de movimientos.");
		lblNewLabel_1.setBounds(35, 70, 235, 23);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("una bestia letal, se termina el juego, mientras que una bestia ");
		lblNewLabel_2.setBounds(35, 115, 453, 27);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("tormentosa disminuira la cantidad de movimientos.");
		lblNewLabel_3.setBounds(35, 135, 453, 27);
		getContentPane().add(lblNewLabel_3);
	}

}
