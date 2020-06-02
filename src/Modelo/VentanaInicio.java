package Modelo;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * MASTER
 * @author Juan
 *
 * Crea la ventana inicial en la que deja elegir la dificultad del juego
 * Muestra el boton de las reglas 
 */
public class VentanaInicio extends JFrame {
	private JTextField textField;


	/**
	 * @deprecated Crear JFrame para seleccionar nivel o ver reglas
	 * @return devuelve un boolean
	 */
	public boolean saberVentana() {
		boolean ventana = true;

		System.out.println("sssss");
		Bosquecillo classeBosquecillo = new Bosquecillo();
		classeBosquecillo.datosVentanaInicio(1);
		
		return ventana;
	}
	/**
	 * Metodo constructor
	 */
	public VentanaInicio() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		setType(Type.NORMAL);
		setTitle("Juego Bosquecillo");
		add(new ImagenFondo());
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 120, 610, 430);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JButton botonIntermedio = new JButton("Jugar");
		botonIntermedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("saber ventana");
				saberVentana();
			}
		});
		botonIntermedio.setBounds(234, 240, 140, 42);
		getContentPane().add(botonIntermedio);
		
		JButton botonReglas = new JButton("Reglas");
		botonReglas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Reglas claseReglas = new Reglas();
				claseReglas.muestraReglas();
			
			}
		});
		botonReglas.setBounds(255,315,100,40);
		getContentPane().add(botonReglas);		
	}

}
