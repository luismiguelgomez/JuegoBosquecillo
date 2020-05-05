/**
 * 
 */
package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Movimientos;

/**
 * @author lmgomezp@unbosque.edu.co
 * 
 * <h1>Clase encargada de pedir datos por javaSwing almacenar estos datos</h1>
 *
 */
public class DatosJuego {

	int casillasLargo;
	int casillasAncho;
	int numeroPremios;
	
	/**
	 * Metodo constructor de la clase DatosJuego
	 */
	public DatosJuego() {
	}

	/**
	 * Pide el numero de casillas a lo largo y el numero de casillas a los ancho
	 * @throws Exception generada por los throw new Exception al leer los numeros de la matriz
	 */
	public void datosMatriz() throws Exception {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Por favor deme el numero de casillas a lo largo");
			casillasLargo = input.nextInt();
			
			if (casillasLargo > 20) {
				datosMatriz();
				System.out.println("El numero de casillas a lo largo es muy grande, debe ser menor a 20");
			}
			if (casillasLargo <= 4 ) {
				datosMatriz();
			}
		} catch (InputMismatchException e) {
			throw new Exception ("Debe agregar un numero entre 4 y 20");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("Ocurrio un error a leer el numero de casillas a lo largo");
		}
		
		try {
			System.out.println("Por favor deme el numero de casillas a lo ancho");
			casillasAncho = input.nextInt();
			
			if (casillasAncho > 20) {
				datosMatriz();
				System.out.println("El numero de casillas a lo ancho muy grande, debe ser menor a 20");
			}
			if (casillasAncho <= 4) {
				datosMatriz();
				System.out.println("El numero de casillas a lo ancho es muy pequenio, el minimo permitido es 5");
			}
		} catch (InputMismatchException e) {
			throw new Exception ("Debe agregar un numero entre 4 y 20");
		} catch (Exception e) {
			e.printStackTrace();
			datosMatriz();
			throw new Exception ("Ocurrio un error a leer el numero de casillas a lo ancho");
		}
		
		try {
			System.out.println("Cuantos premios va a dar:");
			numeroPremios = input.nextInt();
			
			if (numeroPremios < 2) {
				System.out.println("No puede ser menor a 2");
			}
			
			if (numeroPremios > 5) {
				System.out.println("No puede ser mayor a 5");
			}
			
		} catch (InputMismatchException e) {
			throw new Exception ("Debe agregar un numero entre 4 y 20");
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
