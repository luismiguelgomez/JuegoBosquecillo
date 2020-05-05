/** 
 * <h1> En esta clase tendremos el metodo principal de java (main)</h1>
 */
package controlador;

import vista.DatosJuego;

/**
 * @author Admin
 *
 */
public class Principal {

	/**
	 *Metodo principal de la clase "Principal"
	 */
	public Principal() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * <h1>Metodo arrancador del programa "MAIN"</h1>
	 * @param args Proveniente del main
	 */
	public static void main(String[] args) {
		
		DatosJuego claseDatosJuego = new DatosJuego();

		/*Try generado para atrapar el throw proveniente
		 * de la clase DatosJuego
		 */
		try {
			claseDatosJuego.datosMatriz();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("JUEGO TERMINADO");
		}
		
		System.out.println("Juego Terminado de manera Exitosa");
		
	}

}
