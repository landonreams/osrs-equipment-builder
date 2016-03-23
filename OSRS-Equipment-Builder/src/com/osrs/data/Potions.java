package com.osrs.data;

import java.util.Arrays;

/**
 * Enum declaring all available potions for boosting skills. Used in LevelBoosts.
 * Does NOT include Negative side effects.
 * @author Landon Reams
 * @author Severus Snape
 */
public enum Potions {
	//      i   c  %%%%  x  y  name               applicables
	REG_ATT(0,  3, 0.10, 0, 1, "Attack potion",   new LevelType[]{LevelType.ATTACK}), 
	REG_STR(1,  3, 0.10, 0, 2, "Strength potion", new LevelType[]{LevelType.STRENGTH}), 
	REG_DEF(2,  3, 0.10, 0, 3, "Defence potion",  new LevelType[]{LevelType.DEFENCE}),
	REG_CMB(3,  3, 0.10, 0, 4, "Combat potion",   new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	REG_RNG(4,  4, 0.10, 0, 5, "Ranging potion",  new LevelType[]{LevelType.RANGED}),
	REG_MAG(5,  4, 0.00, 0, 6, "Magic potion",    new LevelType[]{LevelType.MAGIC}),
	SUP_ATT(6,  5, 0.15, 1, 1, "Super attack",    new LevelType[]{LevelType.ATTACK}), 
	SUP_STR(7,  5, 0.15, 1, 2, "Super strength",  new LevelType[]{LevelType.STRENGTH}),
	SUP_DEF(8,  5, 0.15, 1, 3, "Super defence",   new LevelType[]{LevelType.DEFENCE}),
	SUP_CMB(9,  5, 0.15, 1, 4, "Super combat",    new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE}),
	SUP_RNG(10, 5, 0.15, 1, 5, "Super ranging",   new LevelType[]{LevelType.RANGED}),
	SUP_MAG(11, 5, 0.15, 1, 6, "Super magic",     new LevelType[]{LevelType.MAGIC}),
	OTH_OVL(12, 5, 0.15, 2, 1, "Overload",        new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE, LevelType.RANGED, LevelType.MAGIC}),
	OTH_ZBR(13, -1, -1,  2, 3, "Zamorak brew",    new LevelType[]{LevelType.ATTACK, LevelType.STRENGTH}), 
	OTH_SBR(14, 2, 0.20, 2, 2, "Saradomin brew",  new LevelType[]{LevelType.DEFENCE}),
	OTH_DBX(15, -1, -1,  2, 4, "Dragon battleaxe",new LevelType[]{LevelType.STRENGTH}),
	OTH_EXC(16, 8, 0.00, 2, 5, "Excalibur",       new LevelType[]{LevelType.DEFENCE});
	
	public final int index;
	public final int constant;
	public final double percentage;
	public final LevelType[] applicables;
	public final static int NUM_POTIONS = 17;
	public final int gridx, gridy;
	public final String name;
	private Potions(int index, int constant, double percentage, int gridx, int gridy, String name, LevelType[] applicables){
		this.index = index;
		this.constant = constant;
		this.percentage = percentage;
		this.applicables = applicables;
		this.gridx = gridx;
		this.gridy = gridy;
		this.name = name;
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
	
	@Override
	public String toString(){
		return name;
	}
	
	public static Potions fromIndex(int i){
		for(Potions p : Potions.values()){
			if(p.index == i)
				return p;
		}
		return null;
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
