package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

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

	private final int MAXIMO_COLUMNAS_NIVEL_2 = 21;
	private final int MAXIMO_FILAS_NIVEL_2 = 11;
	char item;
	int filaBosquecillo;
	int columnaBosquecillo;
	int filaTormentoso;
	int columnaTormentoso;
	int filaCarro;
	int columnaCarro;
	int premiosGanados;

	private Player bosquecillo;
	private Carro carro;
	private Mortal mortal;
	private Tormentoso tormentoso;
	private int w = 0;
	private int h = 0;

	private boolean isCompleted = false;

	private String level 
           = "####################\n"
            + "#                 #\n"
            + "## @C            #\n"
            + "###     ##        #\n"
            + "#              ####\n"
            + "#  $            % #\n"
            + "#     ##       #######\n"
            + "#                   1#\n"
            + "#              #  ####\n"
            + "#                 #\n"
	        + "##  ##            #\n"
            + "##               ##\n"
	        + "#####           ###\n"
            + "#    ##           #\n"
            + "###################\n";
	        
	private String level2 
			= "##################\n" 
			+ "#                #\n" 
			+ "##   @C          #\n"
			+ "###   ##         #\n" 
			+ "#     ##         #\n" 
			+ "#    $           #\n" 
			+ "#       ##      ######\n"
			+ "#  $                1#\n" 
			+ "#  %       $       32#\n" 
			+ "#       ##    ########\n" 
			+ "##############\n";
	
	private String level3 
        		= "  ########\n" 
			+ "  #       #\n" 
			+ "  #  @    #\n" 
			+ "  #  C    ##\n" 
			+ "  #   $ $ #\n"
			+ "###       ##########\n" 
			+ "##     ##     ##  ##\n" 
			+ "## $  $ %         41#\n" 
			+ "##  #    #     #  32#\n"
			+ "##                  #\n"
			+ " ####      %#########\n" 
			+ "    ########\n";
	

	public Tablero() {
		filaBosquecillo = 6;
		columnaBosquecillo = 3;
		filaTormentoso = 4;
		columnaTormentoso = 9;
		filaCarro = 7;
		columnaCarro = 3;
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

			case '1':
			case '2':
			case '3':
			case '4':
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
		
			case 'C':
				carro = new Carro(x, y);
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
		world.add(carro);

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

	/**
	 * 
	 * @author luisgomez por medio de la entrada de teclado, hacemos los movimientos
	 *         del bosquecillo cada movimiento del bosquecillo revisa la colision.
	 *
	 *         El mountruo se mueve de forma que se mueve cuando el bosquecillo se
	 *         mueve.
	 *
	 * Cuando se llega al punto de llegada hace una transicion
	 */
	private class TAdapter extends KeyAdapter {

		int movimientos = 231;
		int diferenciaFila = 0;
		int diferenciaColumna = 0;
		@Override
		
		public void keyPressed(KeyEvent e) {

			if (isCompleted) {
				return;
			}

			System.out.println("***************");
			diferenciaFila = filaCarro - filaBosquecillo;
			diferenciaColumna= columnaCarro - columnaBosquecillo;
			
			System.out.println("diferenciaFILA.-" +  diferenciaFila);
			System.out.println("COLUMNAdiferencia" + diferenciaColumna);
			
//			
//			if (diferenciaFila > 3) {
//				System.out.println("OJO VA MUY LEJOS DEL CARRO");
//				return;
//			}
			
			
			/*Llega a fila 2 sin tener nada*/
			if (columnaCarro == 9 && filaCarro == 19 && movimientos < 1) {
				System.out.println("Perdio");
				movimientos = 0;
			}
			
			if (columnaCarro == 8 && filaCarro == 20) {
				System.out.println("Creo que entro el primero");
				premiosGanados = 1;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				System.out.println("A GANADO UN PREMIO:d");
				repaint();
			}
			if (columnaCarro == 9 && filaCarro == 20) {
				System.out.println("Creo que entro el segundo");
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				System.out.println("A GANADO UN PREMIO:d");
				repaint();
			}

			if (columnaCarro == 9 && filaCarro == 20) {
				System.out.println("Creo que entro el segundo");
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				System.out.println("A GANADO UN PREMIO:d");
				repaint();
			}

			if (columnaCarro == 8 && filaCarro == 20) {
				System.out.println("Creo que entro el segundo");
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				System.out.println("A GANADO UN PREMIO:d");
				repaint();
			}
			System.out.println();
			System.out.println("PREMIOS:"+premiosGanados);
			System.out.println("FILA CARRO" + filaCarro);
			System.out.println("COL CARRO :"+columnaCarro);
			System.out.println("FIL BOS:" + filaBosquecillo);
			System.out.println("COL BOS:"+columnaBosquecillo);
			
			/*Llega a fila 1 teniendo ya uno*/
			if (premiosGanados == 1) {
				System.out.println("Premio ganados son: "+ premiosGanados);
				if (filaCarro == 18 && columnaCarro == 8) {
					System.out.println("Perdio POR HP");
					movimientos = 0;
				}
			}
			
			
			if (movimientos > 0) {
				int key = e.getKeyCode();
				switch (key) {
	
				case KeyEvent.VK_LEFT:
	

					if (diferenciaFila > 3) {
						System.out.println("OJO VA MUY LEJOS DEL CARRO");
						return;
					}
					if (checkWallCollision(bosquecillo, LEFT_COLLISION)) {
						return;
					}
					if (checkBagCollision(LEFT_COLLISION)) {
						return;
					}
	
	//				if (checkWallCollision(tormentoso, LEFT_COLLISION)) {
	//				} else {
	//					System.out.println("No hay nada");
	//					tormentoso.move(-SPACE, 0);
	//					filaTormentoso = filaTormentoso - 1;
	//					System.out.println("la columna del tormentoso es: "+ columnaTormentoso);
	//					System.out.println("la FIL del tormentoso es:" + filaTormentoso);
	//				}
	
					bosquecillo.move(-SPACE, 0);
					filaBosquecillo = filaBosquecillo - 1;
					repaint();
					movimientoCarroIzquierda();
					movimientoEnemigo();
					break;
	
				case KeyEvent.VK_RIGHT:
	
					if (diferenciaFila < -3) {
						System.out.println("OJO VA MUY LEJOS DEL CARRO");
						return;
					}
					if (checkWallCollision(bosquecillo, RIGHT_COLLISION)) {
						return;
					}
	
					if (checkBagCollision(RIGHT_COLLISION)) {
						return;
					}
	
	//				if (checkWallCollision(tormentoso, RIGHT_COLLISION)) {
	//				} else {
	//					tormentoso.move(SPACE, 0);
	//					filaTormentoso = filaTormentoso + 1;
	//					System.out.println("COL Tormentoso:" + columnaTormentoso);
	//					System.out.println("FIL Tormentoso:" + filaTormentoso);
	//				}
	
					bosquecillo.move(SPACE, 0);
					filaBosquecillo = filaBosquecillo + 1;
					movimientoCarroDerecha();
					repaint();
					movimientoEnemigo();
					break;
	
				// Arriba
				case KeyEvent.VK_UP:
	
					if (diferenciaColumna > 3) {
						System.out.println("OJO VA MUY LEJOS DEL CARRO");
						return;
					}
					
					if (checkWallCollision(bosquecillo, TOP_COLLISION)) {
						return;
					}
	
	//				if (checkWallCollision(tormentoso, TOP_COLLISION)) {
	//				} else {
	//					tormentoso.move(0, -SPACE);
	//					columnaTormentoso = columnaTormentoso - 1;
	//					System.out.println("la FILA del tormentoso es: "+ filaTormentoso);
	//					System.out.println("La COLM del tormentoso es:" + columnaTormentoso);
	//				}
	
					if (checkBagCollision(TOP_COLLISION)) {
						return;
					}
	
					bosquecillo.move(0, -SPACE);
					columnaBosquecillo = columnaBosquecillo - 1;
					repaint();
					movimientoCarroArriba();
					movimientoEnemigo();
					break;
	
				case KeyEvent.VK_DOWN:
	
					if (diferenciaColumna < -3) {
						System.out.println("OJO VA MUY LEJOS DEL CARRO");
						return;
					}
					if (checkWallCollision(bosquecillo, BOTTOM_COLLISION)) {
						return;
					}
	
					if (checkBagCollision(BOTTOM_COLLISION)) {
						return;
					}

					bosquecillo.move(0, SPACE);
					columnaBosquecillo = columnaBosquecillo + 1;
					System.out.println();
					movimientoCarroAbajo();
	//              filaBosquecillo = filaBosquecillo - 1;
					repaint();
					movimientoEnemigo();
	
					break;
	
				case KeyEvent.VK_R:
	
					restartLevel();
	
					break;
	
				default:
					break;
				}
			}

			movimientos = movimientos - 1;

			if (columnaBosquecillo == columnaTormentoso) {
				if (filaBosquecillo - 2 == filaTormentoso || filaBosquecillo - 1 == filaTormentoso) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				if (filaBosquecillo + 2 == filaTormentoso || filaBosquecillo + 1 == filaTormentoso) {
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
				if (columnaBosquecillo + 2 == columnaTormentoso || columnaBosquecillo + 1 == columnaTormentoso) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}

				if (columnaBosquecillo - 2 == columnaTormentoso || columnaBosquecillo - 1 == columnaTormentoso) {
					System.out.println();
					System.out.println("Se resta movimientos al bosquecillo");
					System.out.println("los movimientos anteriores son:" + movimientos);
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
			}
			if (filaBosquecillo == filaTormentoso && columnaBosquecillo == columnaTormentoso) {
				System.out.println("Perdio");
				movimientos = 0;
			} else if (movimientos <= 0) {
				System.out.println("PERDIOOOO");
			} else {
				repaint();
			}

		}
	}

	private void movimientoEnemigo() {
		if (columnaBosquecillo == columnaTormentoso) {
			System.out.println("1111ENEMIGOS1111");
			if (filaBosquecillo > filaTormentoso) {
				tormentoso.move(SPACE, 0);
				filaTormentoso = filaTormentoso + 1;
//			} else if(filaBosquecillo < filaTormentoso){
			} else {
				tormentoso.move(-SPACE, 0);
				filaTormentoso = filaTormentoso - 1;
			}
		} else {
			System.out.println("2222ENEMIGOS222");
			if (filaBosquecillo == filaTormentoso) {

				if (columnaBosquecillo < columnaTormentoso) {
					System.out.println("1,2");
					tormentoso.move(0, -SPACE);
					columnaTormentoso = columnaTormentoso - 1;
				}

				if (columnaBosquecillo > columnaTormentoso) {
					System.out.println("21");
					tormentoso.move(0, SPACE);
					columnaTormentoso = columnaTormentoso + 1;
				}

			} else {
				/* Codigo a mostrar al profesor */
//				if (filaBosquecillo > filaTormentoso) {
//					tormentoso.move(SPACE, 0);
//					filaTormentoso = filaTormentoso + 1;
//				} else {
//					tormentoso.move(-SPACE, 0);
//					filaTormentoso = filaTormentoso - 1;
//				}
			}
		}
		System.out.println();
		System.out.println();
		System.out.println("la FILA del bosquecillo es: " + filaBosquecillo);
		System.out.println("La COL del bosquecillo es:" + columnaBosquecillo);
		System.out.println();
		System.out.println("FILA TORMENTO :" + filaTormentoso);
		System.out.println("COL TORMTEN:" + columnaTormentoso);
		repaint();
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

	private boolean movimientoCarroIzquierda() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			System.out.println("EL bosquecillo y el carro estan en la misma parte");
			carro.move(-SPACE, 0);
			filaCarro = filaCarro -1;
		}

		return isCompleted;
	}

	private boolean movimientoCarroDerecha() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			System.out.println("EL bosquecillo y el carro estan en la misma parte");
			carro.move(SPACE, 0);
			filaCarro = filaCarro + 1;
		}

		return isCompleted;
	}

	private boolean movimientoCarroArriba() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			System.out.println("EL bosquecillo y el carro estan en la misma parte");
			carro.move(0, -SPACE);
			columnaCarro = columnaCarro - 1;
		}

		return isCompleted;
	}

	private boolean movimientoCarroAbajo() {
		System.out.println("Entre a carro abajo");
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			System.out.println("EL bosquecillo y el carro estan en la misma parte");
			carro.move(0, SPACE);
			columnaCarro = columnaCarro + 1;
		}

		return true;
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
