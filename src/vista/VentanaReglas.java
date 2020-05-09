package vista;

import javax.swing.JFrame;

/**
 * 
 * @author Juan 
 * Inicializa y da tamaño al JFrame de las reglas
 */
public class VentanaReglas {

	public void inicioVentanaReglas() {
		
		JFrame frames = new MuestraReglas();
		frames.setBounds(0, 0, 540, 720);
		frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.setVisible(true);
	}
}
