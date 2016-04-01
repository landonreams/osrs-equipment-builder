package osrs.model.data;

public interface Boost {
	public static final int   FLAG_ATTACK = 0b000001;
	public static final int FLAG_STRENGTH = 0b000010;
	public static final int  FLAG_DEFENCE = 0b000100;
	public static final int   FLAG_RANGED = 0b001000;
	public static final int    FLAG_MAGIC = 0b010000;
	public static final int   FLAG_PRAYER = 0b100000;
	
	public boolean conflicts(Boost other);
	public int affected();
	public int apply(int level, Levels levelType);
	public int[] apply(int[] levels);
}
