package Modelo;

import java.awt.EventQueue;  
import javax.swing.JFrame;


public class Bosquecillo extends JFrame {

	/**
	 * No deja salir al jugador de esta dimension
	 */
	private final int OFFSET = 30;
	/**
	 * Variable de ventana inicio
	 */
	public static int ventanaInicio;
	

	/**
	 * Metodo constructor de la clase bosquecillo
	 */
	public Bosquecillo() {

		initUI();
		otro();
	}
	/**
	 * Este metodo es utilizado para cargar el tablero 
	 */
	private void initUI() {

		Tablero board = new Tablero();
		add(board);

		setTitle("Bosquecillo");

		setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2 * OFFSET);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	/**
	 * @deprecated lo pensabamos utilizar para el nivel del juego
	 * @param num entero proveniente de la ventana inicio
	 */
	public void datosVentanaInicio(int num) {
		ventanaInicio = num;
	}
	

	/**
	 * Metodo por donde inicia la ejecucion del programa 
	 * @param args es necesario para el main
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("2");
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		otro();
	}

	public static int n2 = 0;
	
	/**
	 * Llama a la clase bosquecillo y esta crea el juego
	 */
	private static void otro() {
		int numeroVeces=0;
		if (ventanaInicio == 1) {
				EventQueue.invokeLater(() -> {
				
					
					Bosquecillo game = new Bosquecillo();
					if (n2 == 0) {
						game.setVisible(true);
						n2 = numeroVeces + 1;
					}
					
				});
				
		} else {
			System.out.println("nada");
		}
	}
}
