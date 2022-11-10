package a11;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

/**
 * A top-level panel for playing a game similar to Plants Vs Zombies.
 * 
 * This panel is primarily responsible for coordinating the various aspects of
 * the game, including: - Running the game step-by-step using a timer - Creating
 * and displaying other components that make up the game - Creating new plants
 * and/or zombies, when necessary - Checking for the end of the game
 * 
 * (Not all of the above behavior is provided in the starter code)
 * 
 * @author Travis Martin and David Johnson
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, MouseListener {
	private static final int NUM_ROWS = 5;
	private static final int NUM_COLS = 7;
	private static final int GRID_BUFFER_PIXELS = 20;
	private static final int CELL_SIZE = 75;
	private static final int STEP_TIME = 30;

	private Timer timer = new Timer(STEP_TIME, this);
	private JLabel text = new JLabel("Progressing...");

	/**
	 * This panel is responsible for displaying plants and zombies, and for managing
	 * their interactions.
	 */
	private ActorDisplay actorDisplay = new ActorDisplay(NUM_COLS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
			NUM_ROWS * CELL_SIZE + GRID_BUFFER_PIXELS * 2);

	private Game() {
		add(new PlantPanel());
		add(actorDisplay);
		addMouseListener(this);
		add(text);

		// This layout causes all elements to be stacked vertically
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// The timer calls the actionPerformed method every STEP_TIME milliseconds
		timer.start();

	}

	/**
	 * Executes game logic every time the timer ticks.
	 * 
	 * If the Zombie makes it to the left side of the screen, the game stops.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		actorDisplay.step();
		Random rand = new Random();
		if (rand.nextInt(100) > 98) {
			addZombie(NUM_COLS - 1, rand.nextInt(NUM_ROWS));
		}
		if (rand.nextInt(1000) > 995) {
			addReduceAttackZombie(NUM_COLS - 1, rand.nextInt(NUM_ROWS));
		}

		if (ActorDisplay.stop == true) {
			timer.stop();
			text.setText("Game Over");
		}

	}

	/**
	 * Adds a plant to the official game grid & display panel.
	 */
	private void addPlant(int col, int row) {
		// The magic numbers below define various hardcoded plant properties
		actorDisplay.addActor(
				new Plant(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, "src/a11/plant1.jpg", 200, 20, 20));
	}

	/**
	 * Adds a bomb to the official game grid & display panel.
	 */
	private void addBomb(int col, int row) {
		// The magic numbers below define various hardcoded bomb properties
		actorDisplay.addActor(
				new Bomb(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, "src/a11/plant 2.png", 150, 5, 130));
	}

	/**
	 * Adds a zombie to the official game grid & display panel.
	 */
	private void addZombie(int col, int row) {
		// The magic numbers below define various hardcoded zombie properties
		actorDisplay.addActor(new Zombie(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, "src/a11/zombie1.png",
				100, 50, -2, 50));
	}

	/**
	 * Adds a ReduceAttackZombie to the official game grid & display panel.
	 */
	private void addReduceAttackZombie(int col, int row) {
		// The magic numbers below define various hardcoded ReduceAttackZombie
		// properties
		actorDisplay.addActor(new ReduceAttackZombie(gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5,
				"src/a11/zombie2.png", 150, 200, -1, 30));
	}

	/**
	 * Converts a row or column to its exact pixel location in the grid.
	 */
	private int gridToPixel(int rowOrCol) {
		return rowOrCol * CELL_SIZE + GRID_BUFFER_PIXELS;
	}

	/**
	 * The inverse of gridToPixel
	 */
	private int pixelToGrid(int xOrY) {
		return (xOrY - GRID_BUFFER_PIXELS) / CELL_SIZE;
	}

	/**
	 * Create, start, and run the game.
	 */
	public static void main(String[] args) {
		JFrame app = new JFrame("Plant and Zombie Test");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.add(new Game());
		app.pack();
		app.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (pixelToGrid(e.getX()) < NUM_COLS - 1 && pixelToGrid(e.getY()) < NUM_ROWS) {
			if (PlantPanel.getPlant().equals("PLANT")) {
				addPlant(pixelToGrid(e.getX()), pixelToGrid(e.getY()));
			} else if (PlantPanel.getPlant().equals("BOMB")) {
				addBomb(pixelToGrid(e.getX()), pixelToGrid(e.getY()));
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}