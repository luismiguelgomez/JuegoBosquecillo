package Modelo;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author JUAN
 * Carga la imagen de fondo
 *
 */

public class ImagenFondo extends JPanel {

	@Override
	public void paintComponent(Graphics g) {

		Dimension tam = getSize();

		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/resources/Fondo.jpg")).getImage());
		g.drawImage(imagen.getImage(), 0, 0, tam.width, tam.height, null);

	}
}
