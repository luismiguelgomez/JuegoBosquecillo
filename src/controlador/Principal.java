/** 
 * <h1> En esta clase tendremos el metodo principal de java (main)</h1>
 */
package controlador;

import javax.swing.JFrame; 

import vista.DatosJuego;
import vista.VentanaNombre;
import vista.VentanaReglas;

/**
 * @author Admin
 *
 */
public class Principal {

	/**
	 * Metodo principal de la clase "Principal"
	 */
	public Principal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <h1>Metodo arrancador del programa "MAIN"</h1> lo primero a realizar es
	 * dirigirse a la clase "DatosJuego"
	 * 
	 * Agrega ventana que permite ingresar el nombre del jugador
	 * Agrega ventana de como jugar?
	 * 
	 * 
	 * @param args Proveniente del main
	 */
	public static void main(String[] args) {

		DatosJuego claseDatosJuego = new DatosJuego();

		/**
		 * Try generado para atrapar el throw proveniente de la clase DatosJuego
		 */
		try {
			claseDatosJuego.datosMatriz();
			System.out.println("Juego terminado de manera exitosa");
		} catch (Exception e) {
			System.out.println("Juego terminado con un error");
			e.printStackTrace();
		}
		
		JFrame frame = new VentanaNombre();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		VentanaReglas claseVentanaInicial = new VentanaReglas();
		claseVentanaInicial.inicioVentanaReglas();
		

	}

}
