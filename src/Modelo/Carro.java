package Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Carro extends Actor{
	public Carro(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {

        ImageIcon iicon = new ImageIcon("..\\src\\resources\\bosquecillo.png");
        Image image = iicon.getImage();
        setImage(image);
    }

    public void move(int x, int y) {

        int dx = x() + x;
        int dy = y() + y;
        
        setX(dx);
        setY(dy);
    }
}
