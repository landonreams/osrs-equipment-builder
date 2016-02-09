package com.osrs.npc;

import java.util.ArrayList;

import com.osrs.items.BasicStat;
import com.osrs.items.StatType;

/**
 * Calculates several damage-dealing factors for an NPC
 * Useful in combat calculations.
 */
public class Damage {
	public static int getMaxHit(Fightable attacker, BasicStat attackingType){
		
		return 0;
	}
	
	public static int getMaxRoll(Fightable attacker, BasicStat attackingType){
		/*
		 * Procedure for calculating a maximum Attack roll:
		 * 1. Get effective level
		 * 2. Multiply by (1 + Primary Bonus / 64)
		 * 3. Apply any attack roll modifiers.
		 */
		int eff = 1;
		double bonus = 1.0;
		switch(attackingType){
		case STAB: eff = getEffectiveLevel(attacker, LevelType.ATTACK);
		   bonus = attacker.getStat(StatType.ACC_STAB);
		   break;
		case SLASH: eff = getEffectiveLevel(attacker, LevelType.ATTACK);
		   bonus = attacker.getStat(StatType.ACC_SLASH);
		   break;
		case CRUSH: eff = getEffectiveLevel(attacker, LevelType.ATTACK);
		   bonus = attacker.getStat(StatType.ACC_CRUSH);
		   break;
		case RANGED: eff = getEffectiveLevel(attacker, LevelType.RANGED);
		   bonus = attacker.getStat(StatType.ACC_RANGE);
		   break;
		case MAGIC: eff = getEffectiveLevel(attacker, LevelType.MAGIC);
		   bonus = attacker.getStat(StatType.ACC_MAGE);
		   break;
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
	
	public static int getMaxDefenceRoll(Fightable defender, BasicStat attackingType){
		int eff = 1;
		double bonus = 1.0;
		
		if(attackingType.equals(BasicStat.MAGIC))
			eff = getMagicDefence(defender);
		else
			eff = getEffectiveLevel(defender, LevelType.DEFENCE);
		
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
	
	public static int getEffectiveLevel(Fightable anNPC, LevelType level){
		/*
		 * Procedure for calculating Effective Level:
		 * 1. Apply Potion Boost to selected level (if any).
		 * 2. Multiply with prayer adjustments and round down.
		 * 3. Add 8.
		 * 4. Add attack style bonus from anNPC.CombatStyle, if any.
		 * 5. Multiply by Void Set bonus (only if NPC is Player wearing full Void equipment)
		 */
		ArrayList<Potions> potions = anNPC.getPotions();
		ArrayList<Prayers> prayers = anNPC.getPrayers();
		
		int newLevel = anNPC.getLevel(level);
		
		for(Potions p : potions){
			if(p.appliesToLevel(level))
				newLevel = LevelBoosts.applyPotion(p, newLevel);
		}
		
		for(Prayers p : prayers){
			newLevel = LevelBoosts.applyPrayer(p, newLevel, level);
		}
		
		newLevel += 8;
		
		newLevel += anNPC.getStyle().levelBoost[level.index];
		
		ArmorBoostType abt = anNPC.getArmorBoost();		
		
		if(abt.equals(ArmorBoostType.VOID_MELEE) && level.equals(LevelType.ATTACK))
			newLevel *= abt.acc;
		if(abt.equals(ArmorBoostType.VOID_MELEE) && level.equals(LevelType.STRENGTH))
			newLevel *= abt.str;
		if(abt.equals(ArmorBoostType.VOID_RANGED) && level.equals(LevelType.RANGED))
			newLevel *= abt.acc;
		if(abt.equals(ArmorBoostType.VOID_MAGIC) && level.equals(LevelType.MAGIC))
			newLevel *= abt.acc;
		
		return newLevel;
	}
	
	public static int getMagicDefence(Fightable anNPC){
		int magicDef = 0;
		
		int magic = getEffectiveLevel(anNPC, LevelType.MAGIC);
		int defence = getEffectiveLevel(anNPC, LevelType.DEFENCE);
		
		magicDef = (int) Math.floor(0.7 * magic + 0.3 * defence);
		
		return magicDef;
	}
}
