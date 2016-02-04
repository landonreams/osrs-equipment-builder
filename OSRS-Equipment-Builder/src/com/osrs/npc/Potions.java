package com.osrs.npc;

/**
 * Enum declaring all available potions for boosting skills. Used in LevelBoosts.
 * Does NOT include Negative side effects.
 * @author Landon Reams
 * @author Severus Snape
 */
public enum Potions {
	REGULAR(3, 1.10), SUPER(5, 1.15), 
	RANGED(4, 1.10), MAGIC(4, 1.00),
	ZBREW_A(2, 1.12), ZBREW_S(2, 1.20), SBREW(2, 1.20);
	
	public final int constant;
	public final double percentage;
	
	private Potions(int constant, double percentage){
		this.constant = constant;
		this.percentage = percentage;
	}
	/*
	 * Potions are...
	 * Regular potion: 3 + 10% * Only for Attack, Strength, and Defence
	 * Super potion:   5 + 15% * For all stats, identical to Overload
	 * Ranged potion:  4 + 10% * Ranged only, duh.
	 * Magic potion:   4       * Magic only, duh.
	 * Zam. Brew Att:  2 + 12% * Attack only, duh.
	 * Zam. Brew Str:  2 + 20% * Strength only, duh.    
	 * Sara. Brew:     2 + 20% * Defence only
	 */
}
