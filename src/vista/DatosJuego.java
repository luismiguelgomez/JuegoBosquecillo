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
	int numeroMuros;
	int numeroBestias;
	int BestiaLetal = 1;
	int Tormentoso;
	
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
		
		System.out.println();
		System.out.println
		("Debe dar los numeros del numero de casillas a lo ancho,"
				+ " numero de casillas a lo largo , numero de premios "
					+"y la cantidad de muros");
		
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
			if (casillasLargo <= 4 ) {
				System.out.println("El numero de casillas a lo largo es muy pequenio, minimo debe ser 5");
				datosMatriz();
			}
		} catch (InputMismatchException e) {
			System.out.println("Debe agregar un numero entero entre 4 y 20");
			datosMatriz();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("Ocurrio un error a leer el numero de casillas a lo largo");
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
			throw new Exception ("Ocurrio un error a leer el numero de casillas a lo ancho");
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
	
		/**
		 * metodo para ingresar la cantidad de muros
		 * @throws Exception 
		 */
			System.out.println();
			System.out.println("digite el numero de muros");
			System.out.println();
			System.out.println("Estos datos deben ser mayor a 0 y menor a 20");
			System.out.println();
			try {	
				System.out.println("eliga la cantidad de muros");
				numeroMuros = input.nextInt();
				
			if (numeroMuros <= 0) {
				System.out.println("Digite un numero mayor a 0");
				}
				
			if (numeroMuros>=20) {
				System.out.println("El numero debe ser menor a 20"); 
				}
				
			} catch (InputMismatchException  eMuro) {
				System.out.println("Debe agregar un numero entre 1 y 20");
				datosMatriz();
			} catch (Exception eMuro) {
				eMuro.printStackTrace();
				datosMatriz();
				throw new Exception("Ocurrio un error al leer el numero de muros");
		}
			System.out.println();
			System.out.println("digite el numero de bestias");
			System.out.println();
			System.out.println("Estos datos deben ser mayor a 0 y menor a 20");
			System.out.println();
			try {	
				System.out.println("eliga la cantidad de bestias");
				numeroBestias = input.nextInt();
				Tormentoso = numeroBestias -1;
				numeroBestias = BestiaLetal + Tormentoso;
			
				System.out.println("bestia letal" +" "+ BestiaLetal +" "+ "tormentoso" +" "+ Tormentoso );
			if (numeroBestias <= 0) {
				System.out.println("Digite un numero mayor a 0");
				}
				
			if (numeroBestias>=20) {
				System.out.println("El numero debe ser menor a 20"); 
				}
				
			} catch (InputMismatchException  eBestia) {
				System.out.println("Debe agregar un numero entre 1 y 20");
				datosMatriz();
			} catch (Exception eBestia) {
				eBestia.printStackTrace();
				datosMatriz();
				throw new Exception("Ocurrio un error al leer el numero de muros");
		}
		
		
		Movimientos claseMovimientos = new Movimientos();
		crearMatrizJuego(casillasLargo, casillasAncho);
		claseMovimientos.valorMatriz(casillasLargo, casillasAncho);
		claseMovimientos.premios(numeroPremios);
		claseMovimientos.personaje();
		claseMovimientos.muros(numeroMuros);
		claseMovimientos.bestias(numeroBestias);
		claseMovimientos.buscarPersonaje();
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
