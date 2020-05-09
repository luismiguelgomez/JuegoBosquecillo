/**
 * 
 */
package vista;

import java.util.InputMismatchException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Movimientos;

/**
 * @author lmgomezp@unbosque.edu.co
 * 
 *         <h1>Clase encargada de pedir datos por javaSwing almacenar estos
 *         datos</h1>
 *
 */
public class DatosJuego extends JFrame {

	int casillasLargo;
	int casillasAncho;
	int numeroPremios;

	/**
	 * Pide el numero de casillas a lo largo y el numero de casillas a los ancho
	 * 
	 * @throws Exception generada por los throw new Exception al leer los numeros de
	 *                   la matriz
	 */
	public void datosMatriz() throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println();
		System.out.println("Debe dar los numeros del numero de casillas a lo ancho,"
				+ " numero de casillas a lo ancho y numero de premios ");

		System.out.println("Estos datos deben ser mayor a 3 y menor a 21");
		System.out.println();
		System.out.println();
		try {
			System.out.println("Por favor deme el numero de casillas a lo largo:");
			casillasLargo = input.nextInt();

			if (casillasLargo > 20) {
				System.out.println("El numero de casillas a lo largo es muy grande, debe ser menor a 20");
				datosMatriz();
			}
			if (casillasLargo <= 4) {
				System.out.println("El numero de casillas a lo largo es muy pequenio, minimo debe ser 5");
				datosMatriz();
			}
		} catch (InputMismatchException e) {
			System.out.println("Debe agregar un numero entero entre 4 y 20");
			datosMatriz();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Ocurrio un error a leer el numero de casillas a lo largo");
		}

		try {
			System.out.println("Por favor deme el numero de casillas a lo ancho:");
			casillasAncho = input.nextInt();

			if (casillasAncho > 20) {
				System.out.println("El numero de casillas a lo ancho muy grande, debe ser menor a 20");
				datosMatriz();
			}
			if (casillasAncho <= 4) {
				System.out.println("El numero de casillas a lo ancho es muy pequenio, el minimo permitido es 5");
				datosMatriz();
			}
		} catch (InputMismatchException e) {
			System.out.println("Debe agregar un numero entero entre 4 y 20");
			datosMatriz();
		} catch (Exception e) {
			e.printStackTrace();
			datosMatriz();
			throw new Exception("Ocurrio un error a leer el numero de casillas a lo ancho");
		}

		try {
			System.out.println("Cuantos premios va a dar:");
			numeroPremios = input.nextInt();

			if (numeroPremios < 2) {
				System.out.println("No puede ser menor a 2, debe estar entre 3 a 5");
			}

			if (numeroPremios > 5) {
				System.out.println("No puede ser mayor a 5, debe estar entre 3 a 5");
			}

		} catch (InputMismatchException e) {
			System.out.println("Debe agregar un numero entre 4 y 20");
			datosMatriz();
		} catch (Exception e) {
			e.printStackTrace();
			datosMatriz();
			throw new Exception("Ocurrio un error al leer el numero de premios");
		}

		Movimientos claseMovimientos = new Movimientos();
		crearMatrizJuego(casillasLargo, casillasAncho);
		claseMovimientos.valorMatriz(casillasLargo, casillasAncho);
		claseMovimientos.premios(numeroPremios);
		claseMovimientos.personaje();

	}

	/**
	 * 
	 * @param l - Largo de la matriz del juego
	 * @param a - Ancho de la matriz del juego
	 * @return
	 */
	public String[][] crearMatrizJuego(int l, int a) {
		String matriz[][] = new String[casillasLargo][casillasAncho];

		return matriz;
	}

}
