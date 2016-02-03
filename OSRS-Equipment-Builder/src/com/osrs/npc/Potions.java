package com.osrs.npc;

/**
 * Enum declaring all available potions for boosting skills. Used in LevelBoosts.
 * Does NOT include Negative side effects.
 * @author Landon Reams
 * @author Severus Snape
 */
public enum Potions {
	REGULAR, SUPER, 
	RANGED, MAGIC,
	ZBREW_A, ZBREW_S, SBREW;
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
