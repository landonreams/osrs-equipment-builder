package com.osrs.npc;

import com.osrs.items.StatType;

/**
 * Calculates several damage-dealing factors for an NPC
 * Useful in combat calculations.
 */
public class Damage {
	public static int getMaxHit(Fightable attacker){
		
		return 0;
	}
	
	public static int getMaxRoll(Fightable attacker, LevelType level){
		/*
		 * Procedure for calculating a maximum Attack roll:
		 * 
		 */
		return 0;
	}
	
	public static int getMaxDefenceRoll(Fightable defender){
		/*
		 * 
		 */
		return 0;
	}
	
	public static int getEffectiveLevel(Fightable anNPC, LevelType level, boolean isMagicDefence){
		/*
		 * Procedure for calculating Effective Level:
		 * 1. Apply Potion Boost to selected level (if any).
		 * 2. Multiply with prayer adjustments and round down.
		 * 3. Add 8.
		 * 4. Add attack style bonus from anNPC.CombatStyle, if any.
		 * 5. Multiply by Void Set bonus (only if NPC is Player wearing full Void equipment)
		 * 
		 * If calculating MAGIC Defence, used for when an attacker is using a magic-based attack...
		 * 6. Recursively get Effective Level of Defence and Magic up to this point.
		 * 7. Multiply Magic by 0.7 and Defence by 0.3.
		 * 8. Add both and round down.
		 */
		
		
		return 0;
	}
}
