package osrs.model.data;

/**
 * Enum depicting the styles a Player can use with their
 * weapon. As certain styles are limited to certain weapons,
 * I may find a way to rewrite this later.
 * @author Landon
 *
 */
public enum AttackStyle {
	MLE_ACCURATE(CombatStyle.MELEE, Boost.FLAG_ATTACK),
	MLE_AGGRESSIVE(CombatStyle.MELEE, Boost.FLAG_STRENGTH),
	MLE_DEFENSIVE(CombatStyle.MELEE, Boost.FLAG_DEFENCE),
	MLE_CONTROLLED(CombatStyle.MELEE, Boost.FLAG_ATTACK | Boost.FLAG_STRENGTH | Boost.FLAG_DEFENCE),
	RNG_ACCURATE(CombatStyle.RANGED, Boost.FLAG_RANGED),
	RNG_RAPID(CombatStyle.RANGED, 0),
	RNG_LONGRANGE(CombatStyle.RANGED, 0),
	MGC_ACCURATE(CombatStyle.MAGIC, 0),
	MGC_DEFENSIVE(CombatStyle.MAGIC, 0);
	
	private final CombatStyle style;
	private final int boosts;
	
	private AttackStyle(CombatStyle cs, int boosts){
		style = cs;
		this.boosts = boosts;
	}
	
	public boolean canBoost(Levels level){
		boolean result = false;
		
		if(level.equals(Levels.HITPOINTS))
			result = false;
		else
			result = (boosts & level.flag) == level.flag;
		
		return result;
	}
	
	public int getBoost(){
		int result = 0;
		
		switch(this){
		case MLE_CONTROLLED: result = 1; break;
		case MLE_ACCURATE:
		case MLE_AGGRESSIVE:
		case MLE_DEFENSIVE: 
		case RNG_ACCURATE: result = 3; break;
		default: result = 0; break;
		}
		
		return result;
	}
	
	public CombatStyle getStyle(){ return style; }
}
