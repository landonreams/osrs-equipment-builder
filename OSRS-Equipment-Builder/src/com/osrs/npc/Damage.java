package com.osrs.npc;

import com.osrs.items.StatType;
import com.osrs.levels.ArmorBoostType;
import com.osrs.levels.CombatTriangle;
import com.osrs.levels.LevelBooster;
import com.osrs.levels.LevelType;
import com.osrs.levels.Potions;
import com.osrs.levels.PotionsList;
import com.osrs.levels.Prayers;
import com.osrs.levels.PrayersList;

/**
 * Calculates several damage-dealing factors for an NPC
 * Useful in combat calculations.
 */
public class Damage {
	
	private final static LevelType[] boostableLevels = {LevelType.ATTACK, LevelType.STRENGTH, LevelType.DEFENCE, LevelType.MAGIC, LevelType.RANGED};
	
	public static int getMaxHit(Fightable attacker, CombatTriangle attackingType){
		int[] effectiveLevels = getEffectiveStrengths(attacker);
		int effStr, strBonus = 0;
		double base = 0.0;
		switch(attackingType){
		case MELEE:
			effStr = effectiveLevels[LevelType.STRENGTH.index];
			strBonus = attacker.getStat(StatType.MSC_MELEE);
			//System.out.println(strBonus);
			base = 1.3 + effStr / 10 + strBonus / 80 + effStr * strBonus / 640;
			return (int) Math.floor(base);
		case RANGED:
			effStr = effectiveLevels[LevelType.RANGED.index];
			strBonus = attacker.getStat(StatType.MSC_RANGE);
			base = 1.3 + effStr / 10 + strBonus / 80 + effStr * strBonus / 640;
			return (int) Math.floor(base);
		case MAGIC:
			
			if(attacker.getSpell().maxHit(0) > 0)
				return attacker.getSpell().maxHit(0);
			int[] newLevels = attacker.getLevels();
			PotionsList ptl = attacker.getPotionsList();
			PrayersList prl = attacker.getPrayersList();
			for(Potions pot : ptl){
				int[] boost = LevelBooster.applyPotion(pot, newLevels);
				for(LevelType l : boostableLevels)
					newLevels[l.index] += boost[l.index];
			}
				
			for(Prayers pry : prl){
				int[] boost = LevelBooster.applyPrayer(pry, newLevels);
				for(LevelType l : boostableLevels)
						newLevels[l.index] += boost[l.index];
			}	
			
			return attacker.getSpell().maxHit(newLevels[LevelType.MAGIC.index]);
		default:
			break;
		}
		return -1;
	}
	
	public static int getMaxRoll(Fightable attacker, DamageType attackingType){
		/*
		 * Procedure for calculating a maximum Attack roll:
		 * 1. Get effective level
		 * 2. Multiply by (1 + Primary Bonus / 64)
		 * 3. Apply any attack roll modifiers.
		 */
		int[] effs = getEffectiveAccuracy(attacker);
		double bonus = 1.0;
		switch(attackingType){
		case STAB: bonus = attacker.getStat(StatType.ACC_STAB); break;
		case SLASH: bonus = attacker.getStat(StatType.ACC_SLASH); break;
		case CRUSH: bonus = attacker.getStat(StatType.ACC_CRUSH); break;
		case RANGED: bonus = attacker.getStat(StatType.ACC_RANGE); break;
		case MAGIC: bonus = attacker.getStat(StatType.ACC_MAGE); break;
		}
		
		int eff = 0;
		switch(attackingType){
		case CRUSH: 
		case SLASH:
		case STAB:   eff = effs[LevelType.ATTACK.index]; break;
		case MAGIC:  eff = effs[LevelType.MAGIC.index]; break;
		case RANGED: eff = effs[LevelType.RANGED.index]; break;
		}
		
		double roll = eff * (1 + bonus / 64.0);
		
		ArmorBoostType abt = attacker.getArmorBoost();	
		
		switch(abt){
		case SLAYER_HELM:
		case SALVE:
		case SALVE_E: roll *= abt.acc;
		default: break;
		}
		
		return (int) Math.floor(roll);
	}
	
	public static int getMaxDefenceRoll(Fightable defender, DamageType attackingType){
		int eff = 1;
		double bonus = 1.0;
		
		if(attackingType.equals(DamageType.MAGIC))
			eff = getMagicDefence(defender);
		else
			eff = getEffectiveAccuracy(defender)[LevelType.DEFENCE.index];
		
		switch(attackingType){
		case STAB:  bonus = defender.getStat(StatType.DEF_STAB); break;
		case SLASH: bonus = defender.getStat(StatType.DEF_SLASH); break;
		case CRUSH:  bonus = defender.getStat(StatType.DEF_CRUSH); break;
		case RANGED: bonus = defender.getStat(StatType.DEF_RANGE); break;
		case MAGIC: bonus = defender.getStat(StatType.DEF_MAGE); break;
		}
		
		double roll = eff * (1 + bonus / 64.0);
		
		return (int) Math.floor(roll);
	}
	
	public static int[] getEffectiveStrengths(Fightable anNPC){
		/*
		 * Procedure for calculating Effective Level:
		 * 1. Apply Potion Boost to selected level (if any).
		 * 2. Multiply with prayer adjustments and round down.
		 * 3. Multiply by equipment bonuses.
		 * 4. Add attack style bonus from anNPC.CombatStyle, if any.
		 */
		int[] newLevels = anNPC.getLevels();
		PotionsList ptl = anNPC.getPotionsList();
		PrayersList prl = anNPC.getPrayersList();
		for(Potions pot : ptl){
			int[] boost = LevelBooster.applyPotion(pot, newLevels);
			//System.out.println(pot);
			for(LevelType l : boostableLevels)
				newLevels[l.index] += boost[l.index];
		}
			
		for(Prayers pry : prl){
			int[] boost = LevelBooster.applyPrayer(pry, newLevels);
			for(LevelType l : boostableLevels)
					newLevels[l.index] += boost[l.index];
		}
		
		for(LevelType l : boostableLevels){
			newLevels[l.index] += anNPC.getStance().levelBoost[l.index];
		}
		
		ArmorBoostType abt = anNPC.getArmorBoost();		
		
		if(abt.equals(ArmorBoostType.VOID_MELEE))
			newLevels[LevelType.STRENGTH.index] *= abt.str;
		if(abt.equals(ArmorBoostType.VOID_RANGED))
			newLevels[LevelType.RANGED.index] *= abt.str;
		if(abt.equals(ArmorBoostType.VOID_MAGIC))
			newLevels[LevelType.MAGIC.index] *= abt.str;
		
		return newLevels;
	}
	
	public static int[] getEffectiveAccuracy(Fightable anNPC){
		/*
		 * Procedure for calculating Effective Level:
		 * 1. Apply Potion Boost to selected level (if any).
		 * 2. Multiply with prayer adjustments and round down.
		 * 3. Add 8.
		 * 4. Add attack style bonus from anNPC.CombatStyle, if any.
		 * 5. Multiply by Void Set bonus (only if NPC is Player wearing full Void equipment)
		 */
		int[] newLevels = anNPC.getLevels();
		PotionsList ptl = anNPC.getPotionsList();
		PrayersList prl = anNPC.getPrayersList();
		for(Potions pot : ptl){
			int[] boost = LevelBooster.applyPotion(pot, newLevels);
			for(LevelType l : boostableLevels)
				newLevels[l.index] += boost[l.index];
		}
			
		for(Prayers pry : prl){
			int[] boost = LevelBooster.applyPrayer(pry, newLevels);
			for(LevelType l : boostableLevels)
					newLevels[l.index] += boost[l.index];
		}
		
		for(LevelType l : boostableLevels){
			newLevels[l.index] += 8;
			newLevels[l.index] += anNPC.getStance().levelBoost[l.index];
		}
		
		ArmorBoostType abt = anNPC.getArmorBoost();		
		
		if(abt.equals(ArmorBoostType.VOID_MELEE))
			newLevels[LevelType.ATTACK.index] *= abt.acc;
		if(abt.equals(ArmorBoostType.VOID_RANGED))
			newLevels[LevelType.RANGED.index] *= abt.acc;
		if(abt.equals(ArmorBoostType.VOID_MAGIC))
			newLevels[LevelType.MAGIC.index] *= abt.acc;
		
		return newLevels;
		}
	
	
	public static int getMagicDefence(Fightable anNPC){
		int magicDef = 0;
		
		int[] levels = getEffectiveAccuracy(anNPC);
		
		magicDef = (int) Math.floor(0.7 * levels[LevelType.MAGIC.index] + 0.3 * levels[LevelType.DEFENCE.index]);
		
		return magicDef;
	}
}
