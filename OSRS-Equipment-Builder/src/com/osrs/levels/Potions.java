package com.osrs.levels;

import java.util.Arrays;

/**
 * Enum declaring all available potions for boosting skills. Used in LevelBoosts.
 * Does NOT include Negative side effects.
 * @author Landon Reams
 * @author Severus Snape
 */
public enum Potions {
	REG_ATT(0,  3, 0.10, new LevelType[]{LevelType.ATTACK}), 
	REG_STR(1,  3, 0.10, new LevelType[]{LevelType.STRENGTH}), 
	REG_DEF(2,  3, 0.10, new LevelType[]{LevelType.DEFENCE}),
	REG_CMB(3,  3, 0.10, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	REG_RNG(4,  4, 0.10, new LevelType[]{LevelType.RANGED}),
	REG_MAG(5,  4, 0.00, new LevelType[]{LevelType.MAGIC}),
	SUP_ATT(6,  5, 0.15, new LevelType[]{LevelType.ATTACK}), 
	SUP_STR(7,  5, 0.15, new LevelType[]{LevelType.STRENGTH}),
	SUP_DEF(8,  5, 0.15, new LevelType[]{LevelType.DEFENCE}),
	SUP_CMB(9,  5, 0.15, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	SUP_RNG(10, 5, 0.15, new LevelType[]{LevelType.RANGED}),
	SUP_MAG(11, 5, 0.15, new LevelType[]{LevelType.MAGIC}),
	OTH_OVL(12, 5, 0.15, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE, LevelType.RANGED, LevelType.MAGIC}),
	OTH_ZBR(13, -1, -1, new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH}), 
	OTH_SBR(14, 2, 0.20, new LevelType[]{LevelType.DEFENCE}),
	OTH_DBX(15, -1, -1, new LevelType[]{LevelType.STRENGTH}),
	OTH_EXC(16, 8, 0.00, new LevelType[]{LevelType.DEFENCE});
	
	public final int index;
	public final int constant;
	public final double percentage;
	public final LevelType[] applicables;
	public final static int NUM_POTIONS = 17;
	private Potions(int index, int constant, double percentage, LevelType[] applicables){
		this.index = index;
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
	
	public String toString(){
		switch(this){
		case OTH_DBX: return "Dragon battleaxe";
		case OTH_OVL: return "Overload";
		case OTH_SBR: return "Saradomin brew";
		case OTH_ZBR: return "Zamorak brew";
		case OTH_EXC: return "Excalibur";
		case REG_ATT: return "Attack";
		case REG_CMB: return "Combat";
		case REG_DEF: return "Defence";
		case REG_MAG: return "Magic";
		case REG_RNG: return "Ranging";
		case REG_STR: return "Strength";
		case SUP_ATT: return "Super attack";
		case SUP_CMB: return "Super combat";
		case SUP_DEF: return "Super defence";
		case SUP_MAG: return "Super magic";
		case SUP_RNG: return "Super ranging";
		case SUP_STR: return "Super strength";
		default: return "";
		}
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
