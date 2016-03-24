package com.osrs.npc;

import com.osrs.data.ArmorStats;
import com.osrs.data.AttackStyle;
import com.osrs.data.Levels;

/**
 * @author Landon
 *
 */
public class Player extends Fightable {
	private Gear gear;
	private AttackStyle style;
	
	public Player(){
		super();

		style = AttackStyle.MLE_CONTROLLED;
	}
	
	public int[] getArmorStats(){ return null; /*gear.getArmorStats();*/ }
	public int   getArmorStat(ArmorStats as){ return gear.getArmorStat(as); }
	public void setArmorStats(int[] as){ 
		throw new UnsupportedOperationException(); 
	}
	public void setArmorStat(int as, ArmorStats asType){ 
		throw new UnsupportedOperationException(); 
	}
	
	public AttackStyle getStyle(){ return style; }
	
	@Override
	public double aspeedInSeconds(){
		double as = super.aspeedInSeconds();
		
		boolean isRapid = style.equals(AttackStyle.RNG_RAPID);
		boolean usingTrident = false;
		
		if(isRapid || usingTrident){
			as -= 0.6;
		}
		
		return as;
	}
	
	public int getEffectiveLevel(Levels level){
		
		
		return 0;
	}
}
