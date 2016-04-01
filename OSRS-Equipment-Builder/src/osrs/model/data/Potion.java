package osrs.model.data;

public enum Potion implements Boost {
	REG_ATT("Attack potion",   3, 1.1,  FLAG_ATTACK),
	REG_STR("Strength potion", 3, 1.1,  FLAG_STRENGTH),
	REG_DEF("Defence potion",  3, 1.1,  FLAG_DEFENCE),
	REG_RNG("Ranging potion",  4, 1.1,  FLAG_RANGED),
	REG_MAG("Magic potion",    4, 1.0,  FLAG_MAGIC),
	REG_CMB("Combat potion",   3, 1.1,  FLAG_ATTACK | FLAG_STRENGTH),
	SUP_ATT("Super attack",    5, 1.15, FLAG_ATTACK);
	
	private int affected, constant;
	private double percent;
	private String name;
	private Potion(String name, int constant, double percent, int affected){
		this.name = name;
		this.constant = constant;
		this.percent = percent;
		this.affected = affected;
	}
	
	public int[] apply(int[] levels){
		int[] result = levels.clone();
		
		return result;
	}
	
	public int affected(){ return affected; }
	
	public int apply(int level, Levels levelType){
		int flag = (int) Math.pow(2, levelType.index);
		double result = level;
		
		System.out.printf("Flag: %s%n", Integer.toBinaryString(flag));
		
		if( (this.affected & flag) == flag){
			result *= percent;
			result += constant;
		}
		
		return (int) Math.floor(result);
	}
	
	public boolean conflicts(Boost other) {
		if(other instanceof Potion){
			return ( ( this.affected & other.affected() ) == 0);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public static void main(String[] args){
		int level = 50;
		Levels levelType = Levels.STRENGTH;
		Potion pot = Potion.REG_STR;
		
		System.out.println(pot.apply(level, levelType));
	}

}
