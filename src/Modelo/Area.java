package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Area extends Actor{
	public Area(int x, int y) {
        super(x, y);
        
        initArea();
    }
    /**
     * Este metodo iniciara el area del juego y cargara las imagenes 
     */
    private void initArea() {

        ImageIcon iicon = new ImageIcon("..\\src\\resources\\area.png");
        Image image = iicon.getImage();
        setImage(image);
    }
}
