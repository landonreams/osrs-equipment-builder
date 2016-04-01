package osrs.model.npc;

import osrs.model.data.ArmorStats;
import osrs.model.data.AttackStyle;
import osrs.model.data.Levels;

/**
 * @author Landon
 *
 */
public class Player extends Fightable {
	private Gear gear;
	private AttackStyle style;
	
	public static final double UNARMED_SPEED_IN_SECONDS = 2.4;	
	
	public Player(){
		super();
		gear = new Gear();
		style = AttackStyle.MLE_CONTROLLED;
	}
	
	public Player(String[] items){
		super();
		gear = new Gear();
		style = AttackStyle.MLE_CONTROLLED;
		equipArray(items);
	}
	
	public Item equip(String item){
		return gear.equip(item);
	}
	
	public void equipArray(String[] items){
		gear.equipArray(items);
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
		
		/*if(gear.isUnarmed())
			as = UNARMED_SPEED_IN_SECONDS;
		*/
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
