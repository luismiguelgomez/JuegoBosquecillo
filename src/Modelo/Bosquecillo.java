package Modelo;

import java.awt.EventQueue; 
import javax.swing.JFrame;

import Modelo.VentanaNombre.GuardarNombreListener;

public class Bosquecillo extends JFrame {

	private final int OFFSET = 30;
	public static int ventanaInicio;
	

	public Bosquecillo() {

		initUI();
		otro();
	}

	private void initUI() {

		Tablero board = new Tablero();
		add(board);

		setTitle("Bosquecillo");

		setSize(board.getBoardWidth() + OFFSET, board.getBoardHeight() + 2 * OFFSET);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void datosVentanaInicio(int num) {
		ventanaInicio = num;
	}
	

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("2");
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
//					if (ventanaInicio == 1) {
//						System.out.println("yupi");
//						frame.setVisible(false);
//						
//						EventQueue.invokeLater(() -> {
//
//							System.out.println("1");
//							JFrame frames = new VentanaNombre();
//							frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//							frames.setVisible(true);
//							
//							System.out.println("3");
//							Bosquecillo game = new Bosquecillo();
//							game.setVisible(true);
//						});
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		otro();
	}

	public static int n2 = 0;
	private static void otro() {
		int numeroVeces=0;
		if (ventanaInicio == 1) {
	//			numeroVeces = numeroVeces+1;
	//			VentanaInicio frame = new VentanaInicio();
	//			frame.setVisible(false);
				
				EventQueue.invokeLater(() -> {
				
					JFrame frames = new VentanaNombre();
					
					Bosquecillo game = new Bosquecillo();
					if (n2 == 0) {
						game.setVisible(true);
						frames.setVisible(true);
						n2 = numeroVeces + 1;
					}
					
					frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frames.setLocationRelativeTo(null);
//					frames.setEnabled(false);
				});
				
		} else {
			System.out.println("nada");
		}
	}
}
