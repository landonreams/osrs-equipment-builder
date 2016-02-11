package com.osrs.gui;

import com.osrs.levels.Prayers;

/**
 * Not yet in use. Eventually will be used to draw new Prayer Selector.
 * @author Landon
 *
 */
public enum PrayerDrawing {
	T1_DEF(Prayers.T1_DEF, "T1_Def", -1, -1),
	T1_STR(Prayers.T1_STR, "T1_Str", -1, -1),
	T1_ATT(Prayers.T1_ATT, "T1_Att", -1, -1),
	T1_RNG(Prayers.T1_RNG, "T1_Rng", -1, -1),
	T1_MAG(Prayers.T1_MAG, "T1_Mag", -1, -1),
	
	T2_DEF(Prayers.T2_DEF, "T2_Def", -1, -1),
	T2_STR(Prayers.T2_STR, "T2_Str", -1, -1),
	T2_ATT(Prayers.T2_ATT, "T2_Att", -1, -1),
	T2_RNG(Prayers.T2_RNG, "T2_Rng", -1, -1),
	T2_MAG(Prayers.T2_MAG, "T2_Mag", -1, -1),
	
	T3_DEF(Prayers.T3_DEF, "T3_Def", -1, -1),
	T3_STR(Prayers.T3_STR, "T3_Str", -1, -1),
	T3_ATT(Prayers.T3_ATT, "T3_Att", -1, -1),
	T3_RNG(Prayers.T3_RNG, "T3_Rng", -1, -1),
	T3_MAG(Prayers.T3_MAG, "T3_Mag", -1, -1),
	
	OTH_RESTORE(Prayers.OTH_RESTORE, "Restore", -1, -1),
	OTH_HEAL(Prayers.OTH_HEAL, "Heal", -1, -1),
	OTH_ITEM(Prayers.OTH_ITEM, "Item", -1, -1),
	OTH_MAG(Prayers.OTH_MAG, "Mage", -1, -1),
	OTH_RNG(Prayers.OTH_RNG, "Range", -1, -1),
	OTH_MLE(Prayers.OTH_MLE, "Melee", -1, -1),
	
	OTH_RETRIBUTION(Prayers.OTH_RETRIBUTION, "Retri", -1, -1),
	OTH_REDEMPTION(Prayers.OTH_REDEMPTION, "Redem", -1, -1),
	OTH_SMITE(Prayers.OTH_SMITE, "Smite", -1, -1),
	OTH_CHIVALRY(Prayers.OTH_CHIVALRY, "Chiv", -1, -1),
	OTH_PIETY(Prayers.OTH_PIETY, "Piety", -1, -1);
	
	public final Prayers prayer;
	public final String filename;
	public final int xpos, ypos;
	private PrayerDrawing(Prayers prayer, String filename, int xpos, int ypos){
		this.prayer = prayer;
		this.filename = filename;
		this.xpos = xpos;
		this.ypos = ypos;
	}
}
