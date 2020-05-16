package Modelo;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Bosquecillo extends JFrame{
	
	private final int OFFSET = 30;

    public Bosquecillo() {

        initUI();
    }

    private void initUI() {
        
        Tablero board = new Tablero();
        add(board);

        setTitle("Bosquecillo");
        
        setSize(board.getBoardWidth() + OFFSET,
                board.getBoardHeight() + 2 * OFFSET);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            
            Bosquecillo game = new Bosquecillo();
            game.setVisible(true);
        });
    }
}
