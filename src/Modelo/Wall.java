package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends Actor{
	private Image image;
	/**
	 * Estem tedo es utilizado para dar la posicion de los muros 
	 * @param x ubicacion en eje x
	 * @param y ubicacion en eje y
	 */
    public Wall(int x, int y) {
        super(x, y);
        
        initWall();
    }
    /**
     * Metodo utilizado para cargar las imagenes de los muros  e inicializarlos
     */
    private void initWall() {
        
        ImageIcon iicon = new ImageIcon("..\\src\\resources\\wall.png");
        image = iicon.getImage();
        setImage(image);
    }
}
