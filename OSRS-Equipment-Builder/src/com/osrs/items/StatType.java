package com.osrs.items;

public enum StatType {
	ACC_STAB(0, "Stab accuracy"), ACC_SLASH(1, "Slash accuracy"), ACC_CRUSH(2, "Crush accuracy"), ACC_MAGE(3, "Magic accuracy"), ACC_RANGE(4, "Ranged accuracy"),
	DEF_STAB(5, "Stab defence"), DEF_SLASH(6, "Slash defence"), DEF_CRUSH(7, "Crush defence"), DEF_MAGE(8, "Magic defence"), DEF_RANGE(9, "Ranged defence"),
	MSC_MELEE(10, "Melee strength"), MSC_RANGE(11, "Ranged strength"), MSC_MAGE(12, "Magic damage bonus"), MSC_PRAYER(13, "Prayer bonus");
	
	public final int index;
	public final String name;
	public static final int NUM_STATS = 14;
	
	private StatType(int index, String name){
		this.index = index;
		this.name = name;
	}
	
	public int[] getNPCGrid(){
		switch(this){
		case ACC_STAB:  return new int[] {2,  9};
		case ACC_SLASH: return new int[] {3,  9};
		case ACC_CRUSH: return new int[] {4,  9};
		case ACC_RANGE: return new int[] {5,  9};
		case ACC_MAGE:  return new int[] {6,  9};
		case DEF_STAB:  return new int[] {2, 10};
		case DEF_SLASH: return new int[] {3, 10};
		case DEF_CRUSH: return new int[] {4, 10};
		case DEF_RANGE: return new int[] {5, 10};
		case DEF_MAGE:  return new int[] {6, 10};
		case MSC_MELEE: return new int[] {4, 11};
		case MSC_RANGE: return new int[] {5, 11};
		default:        return new int[] {-1, -1};
		}
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	public static StatType fromIndex(int i){
		switch(i){
		case 0: return ACC_STAB;
		case 1: return ACC_SLASH;
		case 2: return ACC_CRUSH;
		case 3: return ACC_MAGE;
		case 4: return ACC_RANGE;
		case 5: return DEF_STAB;
		case 6: return DEF_SLASH;
		case 7: return DEF_CRUSH;
		case 8: return DEF_MAGE;
		case 9: return DEF_RANGE;
		case 10: return MSC_MELEE;
		case 11: return MSC_RANGE;
		case 12: return MSC_MAGE;
		case 13: return MSC_PRAYER;
		default: throw new IndexOutOfBoundsException();
		}
	}
}
