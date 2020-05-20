package Modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * Inicializa el tablero y crea el ambiente de juego
 * 
 * @author valkirian
 *
 */
public class Tablero extends JPanel {
	private final int OFFSET = 30;
	private final int SPACE = 20;
	private final int LEFT_COLLISION = 1;
	private final int RIGHT_COLLISION = 2;
	private final int TOP_COLLISION = 3;
	private final int BOTTOM_COLLISION = 4;

	private ArrayList<Wall> walls;
	private ArrayList<Baggage> baggs;
	private ArrayList<Area> areas;

	private final int MAXIMO_COLUMNAS_NIVEL_2 = 21 ;
	private final int MAXIMO_FILAS_NIVEL_2 = 11;
	char item;
	int filaBosquecillo;
	int columnaBosquecillo;
	int filaTormentoso;
	int columnaTormentoso;
	
	private Player bosquecillo;
	private Mortal mortal;
	private Tormentoso tormentoso;
	private int w = 0;
	private int h = 0;

	private boolean isCompleted = false;

	private String level = "    ######\n" 
			+ "    ##   #\n" 
			+ "    ##$  #\n" 
			+ "  ####  $##\n" 
			+ "  ##  $ $ #\n"
			+ "#### # ## #   ######\n" 
			+ "##   # ## #####  ..#\n" 
			+ "## $  $ %  m     ..#\n" 
			+ "###### ### #@##  ..#\n"
			+ "    ##     #########\n" 
			+ "    ########\n";

	private String level2 
			= "##################\n" 
			+ "#                #\n" 
			+ "##   @     $     #\n"
			+ "###   ##         #\n" 
			+ "#     ##         #\n" 
			+ "#    $           #\n" 
			+ "#       ##      #####\n"
			+ "#  $              ..#\n" 
			+ "#  %       $      ..#\n" 
			+ "#       ##    #######\n" 
			+ "##############\n";

	public Tablero() {
		filaBosquecillo = 6;
		columnaBosquecillo = 3;
		filaTormentoso = 4;
		columnaTormentoso = 9;
		initBoard();
	}

	private void initBoard() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}

	public int getBoardWidth() {
		return this.w;
	}

	public int getBoardHeight() {
		return this.h;
	}

	private void initWorld() {

		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();

		int x = OFFSET;
		int y = OFFSET;

		Wall wall;
		Baggage b;
		Area a;

		for (int i = 0; i < level2.length(); i++) {

			item = level2.charAt(i);

			switch (item) {

			case '\n':
				y += SPACE;

				if (this.w < x) {
					this.w = x;
				}

				x = OFFSET;
				break;

			case '#':
				wall = new Wall(x, y);
				walls.add(wall);
				x += SPACE;
				break;

			case '$':
				b = new Baggage(x, y);
				baggs.add(b);
				x += SPACE;
				break;

			case '.':
				a = new Area(x, y);
				areas.add(a);
				x += SPACE;
				break;

			case '@':
				bosquecillo = new Player(x, y);
				x += SPACE;
				break;

			case '%':
				tormentoso = new Tormentoso(x, y);
				x += SPACE;
				break;

			case ' ':
				x += SPACE;
				break;

			default:
				break;
			}

			h = y;
		}
	}

	private void buildWorld(Graphics g) {

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		ArrayList<Actor> world = new ArrayList<>();

		world.addAll(walls);
		world.addAll(areas);
		world.addAll(baggs);
		world.add(bosquecillo);
		world.add(tormentoso);

		for (int i = 0; i < world.size(); i++) {

			Actor item = world.get(i);

			if (item instanceof Player || item instanceof Baggage) {

				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			} else {

				g.drawImage(item.getImage(), item.x(), item.y(), this);
			}

			if (isCompleted) {

				g.setColor(new Color(255, 255, 255));
				g.drawString("Completed", 25, 20);
			}

		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		buildWorld(g);
	}

	private class TAdapter extends KeyAdapter {
		
		int movimientos = 231;

		@Override
		public void keyPressed(KeyEvent e) {

			if (isCompleted) {
				return;
			}

			int key = e.getKeyCode();

			switch (key) {

			case KeyEvent.VK_LEFT:
				if (checkWallCollision(bosquecillo, LEFT_COLLISION)) {
					return;
				}
				if (checkBagCollision(LEFT_COLLISION)) {
					return;
				}

				if (checkWallCollision(tormentoso, LEFT_COLLISION)) {
				} else {
					System.out.println("No hay nada");
					tormentoso.move(-SPACE, 0);
					filaTormentoso = filaTormentoso - 1;
					System.out.println("la columna del tormentoso es: "+ columnaTormentoso);
					System.out.println("la FIL del tormentoso es:" + filaTormentoso);
				}

				bosquecillo.move(-SPACE, 0);
				filaBosquecillo = filaBosquecillo - 1;
				System.out.println("la columna del bosquecillo es:"+ columnaBosquecillo);
				System.out.println("laFIL DE BOSQUE:" + filaBosquecillo);
				break;

			case KeyEvent.VK_RIGHT:
				if (checkWallCollision(bosquecillo, RIGHT_COLLISION)) {
					return;
				}

				if (checkBagCollision(RIGHT_COLLISION)) {
					return;
				}

				if (checkWallCollision(tormentoso, RIGHT_COLLISION)) {
				} else {
					tormentoso.move(SPACE, 0);
					filaTormentoso = filaTormentoso + 1;
					System.out.println("COL Tormentoso:" + columnaTormentoso);
					System.out.println("FIL Tormentoso:" + filaTormentoso);
				}

				bosquecillo.move(SPACE, 0);
				filaBosquecillo = filaBosquecillo + 1;
				System.out.println("columna bosquecillo:" + columnaBosquecillo);
				System.out.println("FILA BOSQUE:" + filaBosquecillo);
				break;

			// Arriba
			case KeyEvent.VK_UP:
				if (checkWallCollision(bosquecillo, TOP_COLLISION)) {
					return;
				}

				if (checkWallCollision(tormentoso, TOP_COLLISION)) {
				} else {
					tormentoso.move(0, -SPACE);
					columnaTormentoso = columnaTormentoso - 1;
					System.out.println("la FILA del tormentoso es: "+ filaTormentoso);
					System.out.println("La COLM del tormentoso es:" + columnaTormentoso);
				}

				if (checkBagCollision(TOP_COLLISION)) {
					return;
				}

				bosquecillo.move(0, -SPACE);
				columnaBosquecillo = columnaBosquecillo - 1;
				System.out.println("la FILA del bosquecillo es: "+ filaBosquecillo);
				System.out.println("La COL del bosquecillo es:" + columnaBosquecillo);
				break;

			case KeyEvent.VK_DOWN:
				
				if (checkWallCollision(bosquecillo, BOTTOM_COLLISION)) {
                    return;
                }
                
                if (checkBagCollision(BOTTOM_COLLISION)) {
                    return;
                }

                System.out.println();
                bosquecillo.move(0, SPACE);
//                filaBosquecillo = filaBosquecillo - 1;
                columnaBosquecillo = columnaBosquecillo + 1;
                System.out.println("la FILA del bosquecillo es: "+ filaBosquecillo);
                System.out.println("COL bosquecillo:" + columnaBosquecillo);
                System.out.println();
                System.out.println("FILA TORMENTOSO:" + filaTormentoso);
                System.out.println("COL TORMENTOSO: "+columnaTormentoso);
                
                break;

			case KeyEvent.VK_R:

				restartLevel();

				break;

			default:
				break;
			}

			movimientos = movimientos - 1;
			
			if (columnaBosquecillo == columnaTormentoso) {
				if (filaBosquecillo -2 == filaTormentoso || filaBosquecillo -1 == filaTormentoso ) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				if (filaBosquecillo +2 == filaTormentoso || filaBosquecillo +1 == filaTormentoso ) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				if (filaBosquecillo == filaTormentoso) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
			}
			
			if (filaBosquecillo == filaTormentoso) {
				System.out.println("ESTOY EN EL 2 IF GRANDE");
				if (columnaBosquecillo + 2 == columnaTormentoso || columnaBosquecillo + 1 == columnaTormentoso ) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				
				if (columnaBosquecillo - 2 == columnaTormentoso || columnaBosquecillo - 1 == columnaTormentoso ) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
			}
			
			if (isCompleted) {
				
			}
			
			if (movimientos == 0) {
				perderJuego();
			} else {
				repaint();
			}
			
			
		}
	}

	private boolean perderJuego() {
		System.out.println("Estoy en el metodo perder juego");
		Graphics g = new Graphics() {
			
			
			@Override
			public void translate(int x, int y) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setXORMode(Color c1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setPaintMode() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setFont(Font font) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setColor(Color c) {
				setColor(new Color(255, 255, 255));
				
			}
			
			@Override
			public void setClip(int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setClip(Shape clip) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public FontMetrics getFontMetrics(Font f) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Font getFont() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Color getColor() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Rectangle getClipBounds() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Shape getClip() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void fillRect(int x, int y, int width, int height) {
			}
			
			@Override
			public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void fillOval(int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drawString(AttributedCharacterIterator iterator, int x, int y) {
				drawString("Perdio el juego", 25, 20);
				
			}
			
			@Override
			public void drawString(String str, int x, int y) {
				drawString("Perdio el juego", 25, 20);
				
			}
			
			@Override
			public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void drawLine(int x1, int y1, int x2, int y2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
					Color bgcolor, ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
					ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Graphics create() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void copyArea(int x, int y, int width, int height, int dx, int dy) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void clipRect(int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void clearRect(int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void drawOval(int x, int y, int width, int height) {
				// TODO Auto-generated method stub
				
			}
		};
		

		return false;
	}
	
	private boolean checkWallCollision(Actor actor, int type) {

		switch (type) {

		case LEFT_COLLISION:

			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isLeftCollision(wall)) {

					return true;
				}
			}

			return false;

		case RIGHT_COLLISION:

			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isRightCollision(wall)) {
					return true;
				}
			}

			return false;

		case TOP_COLLISION:

			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isTopCollision(wall)) {

					return true;
				}
			}

			return false;

		case BOTTOM_COLLISION:

			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isBottomCollision(wall)) {

					return true;
				}
			}

			return false;

		default:
			break;
		}

		return false;
	}

	private boolean checkBagCollision(int type) {

		switch (type) {

		case LEFT_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (bosquecillo.isLeftCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isLeftCollision(item)) {
								return true;
							}
						}

						if (checkWallCollision(bag, LEFT_COLLISION)) {
							return true;
						}
					}

					bag.move(-SPACE, 0);
					isCompleted();
				}
			}

			return false;

		case RIGHT_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (bosquecillo.isRightCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isRightCollision(item)) {
								return true;
							}
						}

						if (checkWallCollision(bag, RIGHT_COLLISION)) {
							return true;
						}
					}

					bag.move(SPACE, 0);
					isCompleted();
				}
			}
			return false;

		case TOP_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (bosquecillo.isTopCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isTopCollision(item)) {
								return true;
							}
						}

						if (checkWallCollision(bag, TOP_COLLISION)) {
							return true;
						}
					}

					bag.move(0, -SPACE);
					isCompleted();
				}
			}

			return false;

		case BOTTOM_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (bosquecillo.isBottomCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isBottomCollision(item)) {
								return true;
							}
						}

						if (checkWallCollision(bag, BOTTOM_COLLISION)) {

							return true;
						}
					}

					bag.move(0, SPACE);
					isCompleted();
				}
				if (bosquecillo.isBottomCollision(bag)) {

					for (int j = 0; j < baggs.size(); j--) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isBottomCollision(item)) {
								return true;
							}
						}

						if (checkWallCollision(bag, BOTTOM_COLLISION)) {

							return true;
						}
					}

					bag.move(0, SPACE);
					isCompleted();
				}
			}

			break;

		default:
			break;
		}

		return false;
	}

	public void isCompleted() {

		int nOfBags = baggs.size();
		int finishedBags = 0;

		for (int i = 0; i < nOfBags; i++) {

			Baggage bag = baggs.get(i);

			for (int j = 0; j < nOfBags; j++) {

				Area area = areas.get(j);

				if (bag.x() == area.x() && bag.y() == area.y()) {

					finishedBags += 1;
				}
			}
		}

		if (finishedBags == nOfBags) {

			isCompleted = true;
			repaint();
		}
	}

	public void restartLevel() {

		areas.clear();
		baggs.clear();
		walls.clear();

		initWorld();

		if (isCompleted) {
			isCompleted = false;
		}
	}
}
