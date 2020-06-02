package Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


import javax.swing.JPanel;


/**
 * Inicializa el tablero y crea el ambiente de juego
 * 	
 * @author luisGomez
 * @author valkirian
 * @author Juan	
 * @author Mateo
 */
public class Tablero extends JPanel {
	/**
	 * Constante para saber el limite
	 */
	private final int OFFSET = 30;
	/**
	 * Constante para saber el espacio
	 */
	private final int SPACE = 20;
	/**
	 * Constante para saber la colision izquierda
	 */
	private final int LEFT_COLLISION = 1;
	/**
	 * Constante para saber la colision derecha
	 */
	private final int RIGHT_COLLISION = 2;
	/**
	 * Constante para saber la colision arriba
	 */
	private final int TOP_COLLISION = 3;
	/**
	 * Constante para saber la colision abajo
	 */
	private final int BOTTOM_COLLISION = 4;

	private ArrayList<Wall> walls;
	private ArrayList<Baggage> baggs;
	private ArrayList<Area> areas;

	/**
	 * Constante del maximo de columnas del nivel 2
	 */
	private final int MAXIMO_COLUMNAS_NIVEL_2 = 21;
	/**
	 * Constante del maximo defilas del nivel 2
	 */

	private final int MAXIMO_FILAS_NIVEL_2 = 11;
	
	char item;
	/**
	 * sirve para conocer la fila de bosquecillo
	 */
	int filaBosquecillo;
	/**
	 * sirve para conocer la columna de bosquecillo
	 */
	int columnaBosquecillo;
	/**
	 * sirve para conocer la fila del tormentoso
	 */
	int filaTormentoso;

	int filaMortal;
	int columnaMortal;

	/**
	 * sirve para conocer la columna del tormentoso
	 */

	int columnaTormentoso;
	/**
	 * sirve para conocer la fila del carro
	 */
	int filaCarro;
	/**
	 * Sirve para coocer la columna del carro
	 */
	int columnaCarro;
	/**
	 * Sirve para conocer los premios ganados
	 */
	int premiosGanados;

	private Player bosquecillo;
	private Carro carro;
	private Mortal mortal;
	private Tormentoso tormentoso;
	private int w = 0;
	private int h = 0;

	private boolean isCompleted = false;

	/**
	 * @deprecated Escenario uno
	 */
	private String level 
		    = "####################\n"
		     + "#                 #\n"
		     + "##  @C            #\n"
		     + "###     ##        #\n"
		     + "#              ####\n"
		     + "#  $             #\n"
		     + "#     ##       #####\n"
		     + "#                 1#\n"
		     + "##%               2#\n"
		     + "#####       $   ####\n"
		     + "#                 #\n"
		     + "###################\n";
	
	/**
	 * Escenario dos
	 */
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
			+ "#  M    ##    ########\n" 
			+ "##############\n";
	
	/**
	 * @deprecated Escenario tres
	 */
	private String level3 
        	= "  ########\n" 
			+ "  #       #\n" 
			+ "  #  @C   #\n" 
			+ "  #       ##\n" 
			+ "  #   $ $ #\n"
			+ "###       ##########\n" 
			+ "##     ##     ##  ##\n" 
			+ "## $  $          41#\n" 
			+ "##  #    #     # 32#\n"
			+ "##       %          #\n"
			+ " ####      #########\n" 
			+ "    ########\n";
	
/**
 * Este metodo es utilizado para indicar los elemetos del juego 
 */
	public Tablero() {
		filaBosquecillo = 6;
		columnaBosquecillo = 3;
		filaTormentoso = 4;
		columnaTormentoso = 9;
		filaMortal = 4;
		columnaMortal = 10;
		filaCarro = 7;
		columnaCarro = 3;
		initBoard();
	}
	
	public void initBoard() {
		
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
	
	/**
	 * esta clase es usada para buscar el nivel y se inicializa los componentes del tablero 
	 */
	public void initWorld() {

		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();

		int x = OFFSET;
		int y = OFFSET;
		
		Wall wall;
		Baggage b;
		Area a;

		for (int i = 0 ; i < level2.length(); i++) {

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
				
			case 'M':
				mortal = new Mortal(x, y);
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

	/**
	 * Este metodo pinta una pnatalla negra donde traera todos los elementos del juego 
	 * @param g se utiliza para graficar imagenes 
	 */
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
		world.add(mortal);

		for (int i = 0; i < world.size(); i++) {

			Actor item = world.get(i);

			if (item instanceof Player || item instanceof Baggage) {

				g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
			} else {

				g.drawImage(item.getImage(), item.x(), item.y(), this);
			}
			if (isCompleted) {
				g.setColor(new Color(255, 255, 255));
				g.drawString("�Ha ganado!", 25, 20);
			}

		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		buildWorld(g);
	}

	/**
	 * @author luisgomez por medio de la entrada de teclado, hacemos los movimientos
	 *         del bosquecillo cada movimiento del bosquecillo revisa la colision.
	 *
	 *         El mounstruo se mueve de forma que se mueve cuando el bosquecillo se
	 *         mueve.
	 *
	 * Cuando se llega al punto de llegada hace una transicion
	 */
	private class TAdapter extends KeyAdapter {

		int movimientos = 231;

		@Override
		/**
		 * Este metodo es utilizado para traer todos los enventos del teclado 
		 */
		
		public void keyPressed(KeyEvent e) {

			if (isCompleted) {
				return;
			}

			
			/*Llega a fila 2 sin tener nada*/
			if (columnaCarro == 9 && filaCarro == 19 && movimientos < 1) {
				System.out.println("Perdio");
				movimientos = 0;
			}
			
			if (columnaCarro == 8 && filaCarro == 20) {
				premiosGanados = 1;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				repaint();
			}
			if (columnaCarro == 9 && filaCarro == 20) {
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				repaint();
			}
			if (columnaCarro == 9 && filaCarro == 19) {
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				repaint();
			}
			if (columnaCarro == 8 && filaCarro == 19) {
				premiosGanados = 2;
				filaCarro = filaCarro - 6;
				for (int i = 0; i <= 5; i++) {
					carro.move(-SPACE, 0);
				}
				repaint();
			}
			/*Llega a fila 1 teniendo ya uno*/
			if (premiosGanados == 1) {
				if (filaCarro == 18 && columnaCarro == 8) {
					movimientos = 0;
				}
			}
			
			
			if (movimientos > 0) {
				int key = e.getKeyCode();
				switch (key) {
	
				case KeyEvent.VK_LEFT:
	
					if (checkWallCollision(bosquecillo, LEFT_COLLISION)) {
						return;
					}
					if (checkBagCollision(LEFT_COLLISION)) {
						return;
					}
					bosquecillo.move(-SPACE, 0);
					filaBosquecillo = filaBosquecillo - 1;
					repaint();
					movimientoCarroIzquierda();
					movimientoEnemigo();
					movimientoEnemigoMortal();
					break;
	
				case KeyEvent.VK_RIGHT:
	
					if (checkWallCollision(bosquecillo, RIGHT_COLLISION)) {
						return;
					}
	
					if (checkBagCollision(RIGHT_COLLISION)) {
						return;
					}
	
					bosquecillo.move(SPACE, 0);
					filaBosquecillo = filaBosquecillo + 1;
					movimientoCarroDerecha();
					repaint();
					movimientoEnemigo();
					break;
	
				// Arriba
				case KeyEvent.VK_UP:
	
					if (checkWallCollision(bosquecillo, TOP_COLLISION)) {
						return;
					}
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
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				if (filaBosquecillo + 2 == filaTormentoso || filaBosquecillo + 1 == filaTormentoso) {
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
				if (filaBosquecillo == filaTormentoso) {
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
			}

			if (filaBosquecillo == filaTormentoso) {
				if (columnaBosquecillo + 2 == columnaTormentoso || columnaBosquecillo + 1 == columnaTormentoso) {
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}

				if (columnaBosquecillo - 2 == columnaTormentoso || columnaBosquecillo - 1 == columnaTormentoso) {
					int restaMovimientos = movimientos * 5;
					restaMovimientos = restaMovimientos / 100;
					movimientos = movimientos - restaMovimientos;
					System.out.println("los movimientos restantes son:" + movimientos);
				}
			}
			if (filaBosquecillo == filaTormentoso && columnaBosquecillo == columnaTormentoso) {
				System.out.println("Perdio");
				movimientos = 0;
			} if (filaBosquecillo == filaMortal && columnaBosquecillo == columnaMortal) {
				System.out.println("Perdio");
				movimientos = 0;
			}
			else if (movimientos <= 0) {
				System.out.println("PERDIO");
			} else {
				repaint();
			}

		}
	}
	/**
	 * Este metodo revisa el movimiento del tormentoso con las columnas y las filas para comenzar a seguirlo
	 */
	private void movimientoEnemigoMortal() {
		if (columnaBosquecillo == columnaMortal) {
			if (filaBosquecillo > filaMortal) {
				mortal.move(SPACE, 0);
				filaMortal = filaMortal + 1;
//			} else if(filaBosquecillo < filaTormentoso){
			} else {
				mortal.move(-SPACE, 0);
				filaMortal = filaMortal - 1;
			}
		} else {
			if (filaBosquecillo == filaMortal) {

				if (columnaBosquecillo < columnaMortal) {
					mortal.move(0, -SPACE);
					columnaMortal = columnaMortal - 1;
				}

				if (columnaBosquecillo > columnaMortal) {
					mortal.move(0, SPACE);
					columnaMortal = columnaMortal + 1;
				}

			} else {
			}
		}
		repaint();
	}
	
	private void movimientoEnemigo() {
		if (columnaBosquecillo == columnaTormentoso) {
			if (filaBosquecillo > filaTormentoso) {
				tormentoso.move(SPACE, 0);
				filaTormentoso = filaTormentoso + 1;
//			} else if(filaBosquecillo < filaTormentoso){
			} else {
				tormentoso.move(-SPACE, 0);
				filaTormentoso = filaTormentoso - 1;
			}
		} else {
			if (filaBosquecillo == filaTormentoso) {

				if (columnaBosquecillo < columnaTormentoso) {
					tormentoso.move(0, -SPACE);
					columnaTormentoso = columnaTormentoso - 1;
				}

				if (columnaBosquecillo > columnaTormentoso) {
					tormentoso.move(0, SPACE);
					columnaTormentoso = columnaTormentoso + 1;
				}

			} else {
			}
		}
		repaint();
	}

	/**
	 * Este metodo permite que que el jugador no se salga de los parametros del juego
	 * @param actor toma el carrito 
	 * @param type revisa colisiones 
	 * @return boolean cuando hay colision o no
	 */
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
/** 
 * Este metodo es utilizado para verificar el movimiento del carro cuanod bosquecillo lo empuja
 * @return iscompleted si cumple con la condicion de la posicion de bosquecillo
 */

	private boolean movimientoCarroIzquierda() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			carro.move(-SPACE, 0);
			filaCarro = filaCarro -1;
		}

		return isCompleted;
	}
/**
 * Este metodo es para verificar el movimiento del carro al lado derecho de bosquecillo
 * @return iscompleted  si cumple con la condicion de la posicion de bosquecillo
 */
	private boolean movimientoCarroDerecha() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			carro.move(SPACE, 0);
			filaCarro = filaCarro + 1;
		}

		return isCompleted;
	}
	/**
	 * Este metodo es para verificar el movimiento del carro hacia arriba de bosquecillo
	 * @return iscompleted  si cumple con la condicion de la posicion de bosquecillo
	 */

	private boolean movimientoCarroArriba() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			carro.move(0, -SPACE);
			columnaCarro = columnaCarro - 1;
		}

		return isCompleted;
	}

	/**
	 * Este metodo es para verificar el movimiento del carro hacia abajo de bosquecillo
	 * @return iscompleted  si cumple con la condicion de la posicion de bosquecillo
	 */
	private boolean movimientoCarroAbajo() {
		if (filaBosquecillo == filaCarro && columnaBosquecillo == columnaCarro) {
			carro.move(0, SPACE);
			columnaCarro = columnaCarro + 1;
		}

		return true;
	}
/**
 * Este metodo es utilizado para revisar si existe colision de el carro con los muros
 * @param type el monstruo o bosquecillo u otro que se va a estrellas
 * @return un boolean al final del cual se save si retorna o no nada
 */
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
/**
 * Este metodo es utilizado para revisar si el jugador llevo las monedas a las posiciones indicadas
 */
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
/**
 * Este metodo nos ayuda a reiniciar el nivel y volver a ubicar los elementos del juego en su posici�n inicial
 */
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
