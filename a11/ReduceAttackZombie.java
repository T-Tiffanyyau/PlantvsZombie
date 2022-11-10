package a11;

/**
 * A ReduceAttackZombie actor. Variants on this class can be created by varying
 * the constructor parameters or by subclassing this.
 */
public class ReduceAttackZombie extends Zombie {

	/**
	 * Creates a ReduceAttackZombie. For parameter descriptions, see Actor.
	 */
	public ReduceAttackZombie(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown,
			int speed, int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, speed, attackDamage);
	}

	/**
	 * An attack only happens when two hitboxes are overlapping and the Zombie is
	 * ready to attack again (based on its cooldown).
	 * 
	 * It also reduces plants' attack damage to at most 10 by lowering plants'
	 * attack damage by 2 for each attacks.
	 * 
	 * Zombies only attack Plants.
	 */
	@Override
	public void actOn(Plant other) {
		if (isColliding(other)) {
			if (isReadyForAction()) {
				other.changeHealth(-attackDamage);
				if (other.attackDamage > 10) {
					other.changeAttackDamage(-2);
				}
				resetCoolDown();
			}
		}
	}

}
