package com.osrs.npc;


/**
 * Class for calculating level boosts, at least until I find a better way of doing so.
 * @author Landon Reams
 */
public class LevelBoosts {
	
	/** Applies Potions pot to the input.
	 * @param pot
	 * @param level
	 * @return boostedLevel
	 */
	public static int applyPotion(Potions pot, int level){
		double boostedLevel = level;
		switch(pot){
		case REGULAR: boostedLevel = 3 + 1.10 * level; break;
		case SUPER:   boostedLevel = 5 + 1.15 * level; break;
		case RANGED:  boostedLevel = 4 + 1.10 * level; break;
		case MAGIC:   boostedLevel = 4 +        level; break;
		case ZBREW_A: boostedLevel = 2 + 1.12 * level; break; 
		case ZBREW_S: boostedLevel = 2 + 1.20 * level; break;
		case SBREW:   boostedLevel = 2 + 1.20 * level; break;
		}
		
		return (int) Math.floor(boostedLevel);
	}
	
	/** Returns Strength level after using Dragon Battleaxe special attack.
	 * This special attack drains Atk, Def, Rng, and Mgc by 10%,
	 * increasing Strength by 10 + (stats drained / 4).
	 * @param levels
	 * @return boostedStrength
	 */
	public static int getStrFromDragonBattleaxeSpec(int[] levels){
		double boost = 10 + 0.025 * (levels[LevelType.ATTACK.index] + levels[LevelType.DEFENCE.index]
							+ levels[LevelType.RANGED.index] + levels[LevelType.MAGIC.index]);
		double boostedStrength = boost + levels[LevelType.STRENGTH.index];
		return (int) Math.floor(boostedStrength);
	}
	
	/** Boosts a level by the selected prayer.
	 * Very ugly at the moment, will think of a better way
	 * to rewrite this later. Atm it gets the job done.
	 * @param prayer
	 * @param level
	 * @param levelType
	 * @return
	 */
	public static int boostByPrayer(Prayers prayer, int level, LevelType levelType){
		int newLevel = level;
		switch(prayer){
		case TIER_ONE:    newLevel *= 1.05; break;
		case TIER_TWO:    newLevel *= 1.10; break;
		case TIER_THREE:  newLevel *= 1.15; break;
		case CHIVALRY:
			switch(levelType){
			case ATTACK:   newLevel *= 1.15; break;
			case STRENGTH: newLevel *= 1.18; break;
			case DEFENCE:  newLevel *= 1.20; break;
			}
			break;
		case PIETY:
			switch(levelType){
			case ATTACK:   newLevel *= 1.20; break;
			case STRENGTH: newLevel *= 1.23; break;
			case DEFENCE:  newLevel *= 1.25; break;
			}
			break;
		}
		return newLevel;
	}
}
