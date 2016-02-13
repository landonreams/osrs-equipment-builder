package com.osrs.levels;


/**
 * Enum denoting potential types of stat-boosting prayers a player could use.
 * @author Landon Reams
 * @authur Jesus
 */
public enum Prayers {
	//                     x  y
	T1_DEF(0, 1, 12, 0.05, 0, 1, LevelType.DEFENCE, "Thick Skin"),
	T1_STR(1, 4, 12, 0.05, 0, 2, LevelType.STRENGTH, "Burst of Strength"),
	T1_ATT(2, 7, 12, 0.05, 0, 3, LevelType.ATTACK, "Clarity of Thought"),
	T1_RNG(3, 8, 12, 0.05, 0, 4, LevelType.RANGED, "Sharp Eye"),
	T1_MAG(4, 9, 12, 0.05, 0, 5, LevelType.MAGIC, "Mystic Will"),
	
	T2_DEF(5, 10, 6, 0.1,  1, 1, LevelType.DEFENCE, "Rock Skin"),
	T2_STR(6, 13, 6, 0.1,  1, 2, LevelType.STRENGTH, "Superhuman Strength"),
	T2_ATT(7, 16, 6, 0.1,  1, 3, LevelType.ATTACK, "Improved Reflexes"),
	T2_RNG(8, 26, 6, 0.1,  2, 2, LevelType.RANGED, "Hawk Eye"),
	T2_MAG(9, 27, 6, 0.1,  2, 3, LevelType.MAGIC, "Mystic Lore"),
	
	T3_DEF(10, 28, 3, 0.1, 2, 4, LevelType.DEFENCE, "Steel Skin"),
	T3_STR(11, 31, 3, 0.1, 2, 5, LevelType.STRENGTH, "Ultimate Strength"),
	T3_ATT(12, 34, 3, 0.1, 3, 1, LevelType.ATTACK, "Incredible Reflexes"),
	T3_RNG(13, 44, 3, 0.1, 3, 5, LevelType.RANGED, "Eagle Eye"),
	T3_MAG(14, 45, 3, 0.1, 4, 1, LevelType.MAGIC, "Mystic Might"),
	
	OTH_RESTORE(15, 19, 26, -1, 1, 4, null, "Rapid Restore"),
	OTH_HEAL(16, 22, 18, -1,    1, 5, null, "Rapid Heal"),
	OTH_ITEM(17, 25, 18, -1,    2, 1, null, "Protect Items"),
	OTH_MAG(18, 37, 3, -1,      3, 2, null, "Protect from Magic"),
	OTH_RNG(19, 40, 3, -1,      3, 3,  null, "Protect from Missiles"),
	OTH_MLE(20, 43, 3, -1,      3, 4, null, "Protect from Melee"),
	
	OTH_RETRIBUTION(21, 46, 12, -1, 4, 2, null, "Retribution"),
	OTH_REDEMPTION(22, 49, 6, -1,   4, 3, null, "Redemption"),
	OTH_SMITE(23, 52, 1.8, -1,      4, 4, null, "Smite"),
	OTH_CHIVALRY(24, 60, 1.8, -1,   4, 5, null, "Chivalry"),
	OTH_PIETY(25, 70, 1.5, -1,      5, 1, null, "Piety");
	
	/*
	 * Drain Interval is the rate at which the prayer drains prayer points
	 * while active. Multiple prayers stack additively. Interval
	 * is in the format "1 point per X seconds". 
	 */
	
	public final int index, level, gridx, gridy;
	public final LevelType applicable;
	public final double drainInterval, boostPercent;
	public final String name;
	public static final int NUM_PRAYERS = 26;
	// gridx and gridy are switched because I'm dumb
	private Prayers(int index, int level, double drainInterval, double boostPercent, int gridy, int gridx, LevelType applicable, String name){
		this.index = index;
		this.level = level;
		this.boostPercent = boostPercent;
		this.drainInterval = drainInterval;
		this.name = name;
		this.applicable = applicable;
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
	public boolean conflicts(Prayers o){
		if(this.equals(o))
			return false;
		switch(this){
		case T1_DEF:
		case T2_DEF:
		case T3_DEF:
			return(o.equals(T1_DEF) ||
				   o.equals(T2_DEF) ||
				   o.equals(T3_DEF) ||
				   o.equals(OTH_CHIVALRY) ||
				   o.equals(OTH_PIETY));
		case T1_STR:
		case T2_STR:
		case T3_STR:
			return(o.equals(T1_STR) ||
				   o.equals(T2_STR) ||
				   o.equals(T3_STR) ||
				   o.equals(OTH_CHIVALRY) ||
				   o.equals(OTH_PIETY));
		case T1_ATT:
		case T2_ATT:
		case T3_ATT:
			return(o.equals(T1_ATT) ||
				   o.equals(T2_ATT) ||
				   o.equals(T3_ATT) ||
				   o.equals(OTH_CHIVALRY) ||
				   o.equals(OTH_PIETY));	
		case T1_MAG:
		case T2_MAG:
		case T3_MAG:
		case T1_RNG:
		case T2_RNG:
		case T3_RNG:
			return(o.equals(T1_RNG) ||
				   o.equals(T2_RNG) ||
				   o.equals(T3_RNG) ||
				   o.equals(T1_MAG) ||
				   o.equals(T2_MAG) ||
				   o.equals(T3_MAG) );
		case OTH_CHIVALRY:
		case OTH_PIETY:
			return(o.equals(T1_STR) ||
				   o.equals(T2_STR) ||
				   o.equals(T3_STR) ||
				   o.equals(T1_ATT) ||
				   o.equals(T2_ATT) ||
				   o.equals(T3_ATT) ||
				   o.equals(T1_DEF) ||
				   o.equals(T2_DEF) ||
				   o.equals(T3_DEF) ||
				   o.equals(OTH_CHIVALRY) ||
				   o.equals(OTH_PIETY));
		case OTH_RNG:
		case OTH_MAG:
		case OTH_MLE:
		case OTH_RETRIBUTION:
		case OTH_SMITE:
		case OTH_REDEMPTION:
			return(o.equals(OTH_RNG) ||
				   o.equals(OTH_MAG) ||
				   o.equals(OTH_MLE) ||
				   o.equals(OTH_RETRIBUTION) ||
				   o.equals(OTH_SMITE) ||
				   o.equals(OTH_REDEMPTION));
		default: return false;
		}
	}
	
	public static Prayers fromIndex(int i){
		for(Prayers p : Prayers.values()){
			if(p.index == i)
				return p;
		}
		return null;
	}
}
