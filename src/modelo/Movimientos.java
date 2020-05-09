/**
 * <h1>Clase encargada de verificar, penalizar y realizar movimientos</h1>
 */
package modelo;

/**
 * @author lmgomezp@unbosque.edu.co
 *	<h1>Clase encargada de verificar, penalizar y realizar movimientos</h1>
 */
public class Movimientos {

	/**
	 * numero del largo de casillas
	 */
	int casillasLargo;
	/**
	 * Numero del ancho de casillas
	 */
	int casillasAncho;
//	Object array = new ArrayList<Object>();
	
	/**
	 * Metodo constructor de movimientos
	 */
	public Movimientos() {
	}
	
	/**
	 * variable de numero de movimientos
	 */
	int numeroMovimientos;

	/**
	 * Inicia la matriz
	 */
	String matriz [][];
	
	/**
	 * 
	 * @param largo - Define lo larga de la matriz
	 * @param ancho - Define lo ancho de la matriz
	 */
	public void valorMatriz(int largo, int ancho) {
		
		casillasLargo = largo;
		casillasAncho = ancho;
		
		
		
		matriz = new String [largo][ancho];
		
		
		
		numeroMovimientos = largo * ancho;
	}
	
	/**
	 * Posicion de los premios dentro de la matriz
	 * @param numeroPremios - Proveniente de DatosJuego -> metodo longitudMatriz
	 */
	public void premios(int numeroPremios) {
		
		for (int i = 0; i < numeroPremios; i++) {
			double valorEnteroUno = Math.floor(Math.random()*(0-casillasAncho+1)+casillasAncho);
			System.out.println(valorEnteroUno);
			double valorEnteroDos = Math.floor(Math.random()*(0-casillasAncho+1)+casillasAncho);
			System.out.println(valorEnteroDos);
		
			int numeroPremio = i + 1;
			
			if (matriz[(int) valorEnteroUno][(int) valorEnteroDos] == null ) {
				matriz[(int) valorEnteroUno][(int) valorEnteroDos] = "Premio" + numeroPremio;
				System.out.println("SI SE PUDO");
			} else {
				System.out.println("ENTRO AL ELSE");
				System.out.println("La casilla esta llena");
				i = i - 1;
			}
			
		}
	}

	/**
	 * Posicion inicial del personaje
	 */
	public void personaje() {
		double posicionInicialPersonajeAlto= 
				Math.floor(Math.random()*(0-casillasAncho+1)+casillasAncho);
		
		double posicionInicialPersonajeAncho= 
				Math.floor(Math.random()*(0-casillasAncho+1)+casillasAncho);
		
		if (matriz[(int) posicionInicialPersonajeAlto][(int) posicionInicialPersonajeAncho] == null) {
			matriz[(int) posicionInicialPersonajeAlto][(int) posicionInicialPersonajeAncho] = "Personaje";
		} else {
			personaje();
		}
		
	}
	
}