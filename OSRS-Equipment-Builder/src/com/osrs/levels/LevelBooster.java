package com.osrs.levels;



/**
 * Class for calculating level boosts, at least until I find a better way of doing so.
 * @author Landon Reams
 */
public class LevelBooster {
	
	/** Applies Potions pot to the input.
	 * @param pot
	 * @param level
	 * @return boostedLevel
	 */
	public static int[] applyPotion(Potions pot, int[] levels){
		int[] boostedLevels = new int[LevelType.NUM_LEVELS];
		if(pot.equals(Potions.OTH_DBX))
			boostedLevels[LevelType.STRENGTH.index] = getStrFromDragonBattleaxeSpec(levels);
		else if(pot.equals(Potions.OTH_ZBR)){
			boostedLevels[LevelType.ATTACK.index] = (int) Math.floor(2 + levels[LevelType.ATTACK.index] * 0.12);
			boostedLevels[LevelType.STRENGTH.index] = (int) Math.floor(2 + levels[LevelType.STRENGTH.index] * 0.2);
		}
		else {
			for(LevelType l : pot.applicables){
				double boost = 0;
				boost = pot.constant + levels[l.index] * pot.percentage;
				boostedLevels[l.index] = (int) Math.floor(boost);
			}
		}
		return boostedLevels;
	}
	
	/** Returns Strength level after using Dragon Battleaxe special attack.
	 * This special attack drains Atk, Def, Rng, and Mgc by 10%,
	 * increasing Strength by 10 + (stats drained / 4).
	 * @param levels
	 * @return boostedStrength
	 */
	private static int getStrFromDragonBattleaxeSpec(int[] levels){
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
	public static int applyPrayer(Prayers prayer, int level, LevelType levelType){
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
			default: break;
			}
			break;
		case PIETY:
			switch(levelType){
			case ATTACK:   newLevel *= 1.20; break;
			case STRENGTH: newLevel *= 1.23; break;
			case DEFENCE:  newLevel *= 1.25; break;
			default: break;
			}
			break;
		default: break;
		}
		return newLevel;
	}
}
