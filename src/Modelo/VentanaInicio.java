package Modelo;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window.Type;
import javax.swing.JTextPane;

import Modelo.VentanaNombre.GuardarNombreListener;

import javax.swing.JTextArea;

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
	 *Comienzo de la aplicacion 
	 */
	public static void main(String[] args) {
		
		JFrame frames = new VentanaNombre();
		frames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frames.setVisible(true);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 *Crear JFrame para seleccionar nivel o ver reglas
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
		
		JButton botonFacil = new JButton("Facil");
		botonFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Se oprimio");
				int nivel = 1;	
				Tablero claseTablero = new Tablero();
				claseTablero.dificultad(nivel);
				}
		});
		botonFacil.setBounds(100, 290, 116, 42);
		getContentPane().add(botonFacil);
		
		JButton botonIntermedio = new JButton("Intermedio");
		botonIntermedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nivel = 2;
				Tablero claseTablero = new Tablero();
				claseTablero.dificultad(nivel);
			}
		});
		botonIntermedio.setBounds(250, 290, 117, 42);
		getContentPane().add(botonIntermedio);
		
		JButton botonDificil = new JButton("Dificil");
		botonDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nivel = 3;
				Tablero claseTablero = new Tablero();
				claseTablero.dificultad(nivel);
			}
		});
		botonDificil.setBounds(400, 290, 108, 42);
		getContentPane().add(botonDificil);
		
		JButton botonReglas = new JButton("Reglas");
		botonReglas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Reglas claseReglas = new Reglas();
				claseReglas.muestraReglas();
			
			}
		});
		botonReglas.setBounds(258,340,100,40);
		getContentPane().add(botonReglas);		
	}

}
