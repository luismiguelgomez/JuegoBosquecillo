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
import javax.swing.JTextArea;

/**
 * MASTERRRR
 * @author ANGELA SANTA MARIA
 *
 */
public class VentanaInicio extends JFrame {
	private JTextField textField;

	/**
	 *Comienzos de la aplicacion 
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
	 *crear jFrame
	 */
	
	//public Image imagenFondo;
	//public URL fondo;
	
	public VentanaInicio() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		setType(Type.NORMAL);
		setTitle("Juego Bosquecillo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 120, 610, 430);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		//Container contenedor = getContentPane();
		
		//contenedor.add(panel);
		
		JButton NewButton = new JButton("Facil");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		NewButton.setBounds(100, 284, 116, 42);
		getContentPane().add(NewButton);
		
		JButton Boton_1 = new JButton("Intermedio");
		Boton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Boton_1.setBounds(250, 284, 117, 42);
		getContentPane().add(Boton_1);
		
		JButton Boton_2 = new JButton("Dificil");
		Boton_2.setBounds(400, 284, 108, 42);
		getContentPane().add(Boton_2);
		
		/*textField = new JTextField();
		textField.setBounds(15, 176, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);*/
		
		JLabel Label_1 = new JLabel("Juego Bosquecillo");
		Label_1.setBounds(146, 11, 252, 32);
		getContentPane().add(Label_1);
		Label_1.setFont(new Font("Wide Latin", Font.PLAIN, 16));
		
		JLabel Label_2 = new JLabel("Reglas de juego");
		Label_2.setBounds(131, 85, 116, 14);
		getContentPane().add(Label_2);
		
		JLabel lblBosquecilloEsUn = new JLabel("Bosquecillo es un juego que consiste en recolectar cierta cantidad");
		lblBosquecilloEsUn.setBounds(131, 110, 390, 32);
		getContentPane().add(lblBosquecilloEsUn);
		
		JLabel lblNoSePuede = new JLabel("El limite de movimientos esta dado por las dimensiones del tablero.");
		lblNoSePuede.setBounds(131, 215, 453, 32);
		getContentPane().add(lblNoSePuede);
		
		JLabel lblDebeRecogerLos = new JLabel("Debe recoger los premios en un orden especifico. En caso de\r\n encontrar");
		lblDebeRecogerLos.setBounds(131, 160, 410, 26);
		getContentPane().add(lblDebeRecogerLos);
		
		/*JLabel Label_6 = new JLabel("Ingrese su nombre");
		Label_6.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		Label_6.setBounds(0, 145, 131, 20);
		getContentPane().add(Label_6);*/
		
		JLabel lblNewLabel = new JLabel("Elija la dificultad");
		lblNewLabel.setBounds(260, 259, 91, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("de premios con un limite de movimientos.");
		lblNewLabel_1.setBounds(131, 130, 235, 23);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("una bestia letal, se termina el juego, mientras que una bestia ");
		lblNewLabel_2.setBounds(131, 175, 453, 27);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("tormentosa disminuira la cantidad de movimientos.");
		lblNewLabel_3.setBounds(131, 190, 453, 27);
		getContentPane().add(lblNewLabel_3);
		
	}

	private void setImage(Image image) {
		ImageIcon fondo = new ImageIcon("/src/resources/sunflower.jpg");
		Image imagen = fondo.getImage();
		setImage(imagen);
		
	}

	/*public JPanel panel = new JPanel()
	{
	public void paintComponent(Graphics g)
	{
		g.drawImage(imagenFondo, 0,0, getWidth(), getHeight(), this);
	}
	};*/
}
	
	

