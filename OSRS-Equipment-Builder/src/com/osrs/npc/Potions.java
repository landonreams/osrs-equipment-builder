package com.osrs.npc;

import java.util.Arrays;

/**
 * Enum declaring all available potions for boosting skills. Used in LevelBoosts.
 * Does NOT include Negative side effects.
 * @author Landon Reams
 * @author Severus Snape
 */
public enum Potions {
	REG_ATT(3, 1.10, new LevelType[]{LevelType.ATTACK}), 
	REG_STR(3, 1.10, new LevelType[]{LevelType.STRENGTH}), 
	REG_DEF(3, 1.10, new LevelType[]{LevelType.DEFENCE}),
	REG_CMB(3, 1.10, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	REG_RNG(4, 1.10, new LevelType[]{LevelType.RANGED}),
	REG_MAG(4, 1.00, new LevelType[]{LevelType.MAGIC}),
	SUP_ATT(5, 1.15, new LevelType[]{LevelType.ATTACK}), 
	SUP_STR(5, 1.15, new LevelType[]{LevelType.STRENGTH}),
	SUP_DEF(5, 1.15, new LevelType[]{LevelType.DEFENCE}),
	SUP_CMB(5, 1.15, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	SUP_RNG(5, 1.15, new LevelType[]{LevelType.RANGED}),
	SUP_MAG(5, 1.15, new LevelType[]{LevelType.MAGIC}),
	OTH_OVL(5, 1.15, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE, LevelType.RANGED, LevelType.MAGIC}),
	OTH_ZBREW_A(2, 1.12, new LevelType[]{LevelType.ATTACK}), 
	OTH_ZBREW_S(2, 1.20, new LevelType[]{LevelType.STRENGTH}), 
	OTH_SBREW(2, 1.20, new LevelType[]{LevelType.DEFENCE});
	
	public final int constant;
	public final double percentage;
	public final LevelType[] applicables;
	private Potions(int constant, double percentage, LevelType[] applicables){
		this.constant = constant;
		this.percentage = percentage;
		this.applicables = applicables;
	}
	
	public boolean appliesToLevel(LevelType level){
		return Arrays.asList(applicables).contains(level);
	}
	
	public boolean conflicts(Potions other){
		for(LevelType l : other.applicables){
			if(this.appliesToLevel(l)) return true;
		}
		return false;
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
