package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Baggage extends Actor{
	public Baggage(int x, int y) {
        super(x, y);
        
        initBaggage();
    }
    /**
     * Este metodo iniciara los premios y cargara las imagenes de las monedas
     */
    private void initBaggage() {
        
//        ImageIcon iicon = new ImageIcon("src/resources/baggage.png");
    	ImageIcon iicon = new ImageIcon("..\\src\\resources\\baggage.png");
    	
        Image image = iicon.getImage();
        setImage(image);
    }
    /**
     * Este metodo para darle la posicion de las monedas
     * @param x ubicacion eje x
     * @param y ubicacion eje y
     */
    public void move(int x, int y) {
        
        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
