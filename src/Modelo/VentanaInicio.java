package Vista;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.JTextPane;
import javax.swing.JTextArea;


public class VentanaInicio extends JFrame {
	private JTextField textField;

	/**
	 *Comienzos de la aplicacion 
	 */
	public static void main(String[] args) {
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
	public VentanaInicio() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		setType(Type.NORMAL);
		setTitle("Juego Bosquecillo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 430);
		getContentPane().setLayout(null);
		
		JButton NewButton = new JButton("Facil");
		NewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		NewButton.setBounds(51, 284, 116, 42);
		getContentPane().add(NewButton);
		
		JButton Boton_1 = new JButton("intermedio");
		Boton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Boton_1.setBounds(185, 284, 117, 42);
		getContentPane().add(Boton_1);
		
		JButton Boton_2 = new JButton("dificil");
		Boton_2.setBounds(328, 284, 108, 42);
		getContentPane().add(Boton_2);
		
		textField = new JTextField();
		textField.setBounds(15, 176, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel Label_1 = new JLabel("Juego Bosquecillo");
		Label_1.setBounds(146, 11, 252, 32);
		getContentPane().add(Label_1);
		Label_1.setFont(new Font("Wide Latin", Font.PLAIN, 16));
		
		JLabel Label_2 = new JLabel("reglas de juego");
		Label_2.setBounds(131, 85, 116, 14);
		getContentPane().add(Label_2);
		
		JLabel lblBosquecilloEsUn = new JLabel("Bosquecillo es un juego que consiste en recolectar la cantidad de");
		lblBosquecilloEsUn.setBounds(131, 110, 390, 32);
		getContentPane().add(lblBosquecilloEsUn);
		
		JLabel lblNoSePuede = new JLabel("el limite de movimientos esta dado por las dimensiones del tablero");
		lblNoSePuede.setBounds(131, 205, 453, 32);
		getContentPane().add(lblNoSePuede);
		
		JLabel lblDebeRecogerLos = new JLabel("Debe recoger los premios en orden especifico en caso de\r\n encontrar  ");
		lblDebeRecogerLos.setBounds(131, 176, 410, 26);
		getContentPane().add(lblDebeRecogerLos);
		
		JLabel Label_6 = new JLabel("Ingrese su nombre");
		Label_6.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		Label_6.setBounds(0, 145, 131, 20);
		getContentPane().add(Label_6);
		
		JLabel lblNewLabel = new JLabel("Elija la dificultad");
		lblNewLabel.setBounds(211, 259, 91, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" premios en una limite de movimientos ");
		lblNewLabel_1.setBounds(131, 130, 225, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("una bestia letal acaba el juego y un tormentoso dismunye los movimientos");
		lblNewLabel_2.setBounds(131, 192, 453, 27);
		getContentPane().add(lblNewLabel_2);
		
	}
}
