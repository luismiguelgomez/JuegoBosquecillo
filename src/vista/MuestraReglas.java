package vista;

import javax.swing.JFrame;

/**
 * Crea el JTextArea en el que muestra las reglas
 */
import javax.swing.JTextArea;

/**
 * Crea el JTextArea y muestra las reglas en el 
 */
public class MuestraReglas extends JFrame {

	private JTextArea reglas;

	public MuestraReglas() {
		setLayout(null);

		reglas = new JTextArea();
		reglas.setBounds(25, 25, 470, 630);
		reglas.setText("El juego de Bosquecillo es un juego donde el objetivo es mover un carrito \n"
				+ "para recorrer los puntos objetivo en un laberinto hasta llegar a un punto \n"
				+ "determinado, sin alcanzar un n�mero m�ximo de movimientos y bajo algunas \n"
				+ "restricciones. En este juego, al jugador se le denomina Bosquecillo y es \n"
				+ "quien debe mover el carrito. El juego se desarrolla en un tablero con forma \n"
				+ "de matriz de M x N casillas, lo que representa el laberinto, y los movimientos\n"
				+ "se realizan traslad�ndose entre una casilla y otra en direcci�n vertical u \n"
				+ "horizontal, solamente. \n" + "\n"
				+ "Dentro del laberinto se pueden encontrar objetos tales como muros, bestias \n"
				+ "y los puntos objetivo. La cantidad de cada uno de estos objetos puede ser \n"
				+ "determinada por el usuario al inicio del juego y su ubicaci�n inicial en el\n"
				+ "laberinto es aleatoria, pero de manera que siempre hay al menos un camino\n" + "posible. \n" + "\n"
				+ "Los puntos objetivo est�n dispuestos en el laberinto en un orden determinado.\n"
				+ "Bosquecillo debe recorrer los puntos en ese orden a menos que el usuario, al\n"
				+ "inicio del juego, elija recorrerlos en orden inverso. El n�mero m�nimo de\n"
				+ "puntos objetivo en el juego es dos (2) y el m�ximo es de cinco (5). \n"
				+ "Las bestias son de dos tipos: Asesino letal y Tormentoso. Cuando Bosquecillo\n"
				+ "se desplaza, ellas tienen la posibilidad de moverse una casilla en cualquier\n"
				+ "direcci�n. Si en el recorrido Bosquecillo se encuentra con un Asesino Letal\n"
				+ "se pierde instant�neamente el juego. Se denomina encuentro a la situaci�n en\n"
				+ "la cual la casilla en la que est� Bosquecillo colinda con la casilla en la \n"
				+ "que est� la bestia. Si el encuentro es con un Tormentoso se genera una \n"
				+ "disminuci�n del n�mero m�ximo de movimientos establecido para el juego. La \n"
				+ "disminuci�n corresponder� al cinco por ciento (5%) de los movimientos restantes \n"
				+ "del jugador. El m�ximo n�mero de movimientos para un juego ser� equivalente al \n"
				+ "producto de las dimensiones de la matriz, esto es M x N, las cuales son \n"
				+ "determinadas por el usuario al inicio del juego. El valor m�ximo que pueden \n"
				+ "tener las dimensiones de la matriz es de veinte (20) y el m�nimo es de cinco (5).\n"
				+ "Si en el recorrido se pasa por una casilla que se encuentre en medio de dos muros,\n"
				+ "se tendr� una penalizaci�n equivalente a la suma de los valores de las coordenadas\n"
				+ "de la posici�n donde se encuentra Bosquecillo. Bosquecillo tiene la posibilidad\n"
				+ "de recordar las instrucciones del juego en cualquier momento, sin embargo, el\n"
				+ "juego le penalizar� con un n�mero determinado de movimientos.\n" + "\n"
				+ "Para empujar el carrito Bosquecillo debe estar en una casilla contigua a la casilla\n"
				+ "donde �ste se encuentra.");
		reglas.setEditable(false);
		add(reglas);
	}
}