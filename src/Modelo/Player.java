package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Actor{
	public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }
	/**
	 * Este metodo es utilizado para cargar las imagenes del jugador principal es este caso bosquecillo 
	 */
    private void initPlayer() {

        ImageIcon iicon = new ImageIcon("..\\src\\resources\\Bosquecillo1.png");
        Image image = iicon.getImage();
        setImage(image);
    }
    /**
     * Este metodo es utilizado para para dar la ubicacion del jugador
     * @param x ubicacion en eje x
     * @param y ubicacion en eje y
     */
    public void move(int x, int y) {

        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
