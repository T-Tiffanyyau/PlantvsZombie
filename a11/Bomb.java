package a11;

/**
 * A bomb actor. Variants on this class can be created by varying the
 * constructor parameters or by subclassing this.
 */
public class Bomb extends Plant {

	/**
	 * Creates a bomb. For parameter descriptions, see Actor.
	 */
	public Bomb(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown, int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage);
	}

	/**
	 * An attack only happens when two hitboxes are overlapping and the bomb dies
	 * after attacking.
	 * 
	 * Bombs only attack Zombies.
	 */
	@Override
	public void actOn(Zombie other) {
		if (isColliding(other)) {
			other.changeHealth(-attackDamage);
			this.changeHealth(-this.health);
		}
	}

}
