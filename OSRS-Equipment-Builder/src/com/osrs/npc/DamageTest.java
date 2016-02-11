package com.osrs.npc;

import static org.junit.Assert.*;

import org.junit.Test;

import com.osrs.levels.LevelType;

public class DamageTest {

	@Test
	public void testEffectiveLevelNoBoost() {
		int[] levels = {1, 1, 300, 300, 300, 500, 1};
		int[] stats  = {0, 0, 0, 50, 50, 0, 0, 0, -45, 50, 0, 0, 0, 0};
		NPC zulrah = new NPC(levels, stats);
		
		int effectiveMagic = Damage.getEffectiveLevel(zulrah, LevelType.MAGIC);
		assertEquals("", effectiveMagic, 308);
	}

//	public void testEffectiveLevelPotion() {
//		int[] levels = {99, 99, 99, 99, 99, 99, 99};
//		Player me = new Player(levels);
//		
//		
//	}
}
