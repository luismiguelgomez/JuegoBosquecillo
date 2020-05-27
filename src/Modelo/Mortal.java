package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Mortal extends Actor{
	public Mortal(int x, int y) {
        super(x, y);

        initPlayer();
    }
	/**
	 * Este metodo me utilizado para caragar las imagenes de monstruo mortal 
	 */
    private void initPlayer() {

        ImageIcon iicon = new ImageIcon("..\\src\\resources\\Mortal.png");
        Image image = iicon.getImage();
        setImage(image);
    }
    /**
     * Este metodo es utilizado para dar la ubicación del monstruo mortal 
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
