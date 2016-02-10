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
	OTH_ZBA(2, 1.12, new LevelType[]{LevelType.ATTACK}), 
	OTH_ZBS(2, 1.20, new LevelType[]{LevelType.STRENGTH}), 
	OTH_SBR(2, 1.20, new LevelType[]{LevelType.DEFENCE}),
	OTH_DBX(-1, -1, new LevelType[]{LevelType.STRENGTH});
	
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
	
	public String toString(){
		switch(this){
		case OTH_DBX: return "Dragon battleaxe";
		case OTH_OVL: return "Overload";
		case OTH_SBR: return "Saradomin brew";
		case OTH_ZBA: return "Z-brew (Attack)";
		case OTH_ZBS: return "Z-brew (Strength)";
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
