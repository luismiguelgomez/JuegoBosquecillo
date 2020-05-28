package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Tormentoso extends Actor{
	public Tormentoso(int x, int y) {
        super(x, y);

        initPlayer();
    }
	/**
	 *Este metodo es utilizado par cargar las imagenes del mostruo tormentoso
	 */
    private void initPlayer() {

        ImageIcon iicon = new ImageIcon("..\\src\\resources\\Tormentoso.png");
        Image image = iicon.getImage();
        setImage(image);
    }
    /**
     * Este metodo es utilizado para dar la ubicacion del mostruo tormentoso
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
