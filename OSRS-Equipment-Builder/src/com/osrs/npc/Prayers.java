package com.osrs.npc;


/**
 * Enum denoting potential types of stat-boosting prayers a player could use.
 * @author Landon Reams
 * @authur Jesus
 */
public enum Prayers {
	TIER_ONE(12.0), TIER_TWO(6.0), TIER_THREE(3.0),
	PROTECT(3.0), RETRIBUTION(12.0), REDEMPTION(6.0),
	SMITE(1.8), CHIVALRY(1.8), PIETY(1.5);
	
	/*
	 * Drain Interval is the rate at which the prayer drains prayer points
	 * while active. Multiple prayers stack additively. Interval
	 * is in the format "1 point per X seconds". 
	 * Only TIER_ONE, TIER_TWO, TIER_THREE,
	 * CHIVALRY, and PIETY boost levels.
	 */
	
	public final double drainInterval;
	
	private Prayers(double drainInterval){
		this.drainInterval = drainInterval;
	}
	
	public boolean conflicts(Prayers other){
		switch(this){
		case TIER_ONE: 
		case TIER_TWO:
		case TIER_THREE:
		case CHIVALRY:
		case PIETY: return other.equals(TIER_ONE) || 
					  other.equals(TIER_TWO) ||
					  other.equals(TIER_THREE) ||
					  other.equals(CHIVALRY) ||
					  other.equals(PIETY);
		case PROTECT:
		case RETRIBUTION:
		case SMITE:
		case REDEMPTION: return other.equals(PROTECT) ||
						   other.equals(RETRIBUTION) ||
						   other.equals(REDEMPTION) ||
						   other.equals(SMITE);
		}
		return false;
	}
}
