package com.osrs.data;

public enum ArmorStats {
	ASTAB(0), ASLASH(1), ACRUSH(2), AMAGIC(3), ARANGE(4),
	DSTAB(5), DSLASH(6), DCRUSH(7), DMAGIC(8), DRANGE(9),
	STR(10), RSTR(11), MDMG(12), PRAYER(13), ASPEED(14);
	
public final int index;
	public static final int COUNT = 15;
	
	private ArmorStats(int index){
		this.index = index;
	}
	
	public ArmorStats fromString(String str){
		ArmorStats result;
		
		switch(str.toUpperCase()){
		case "ASTAB":  result = ASTAB; break;
		case "ASLASH": result = ASLASH; break;
		case "ACRUSH": result = ACRUSH; break;
		case "AMAGIC": result = AMAGIC; break;
		case "ARANGE": result = ARANGE; break;
		case "DSTAB":  result = DSTAB; break;
		case "DSLASH": result = DSLASH; break;
		case "DCRUSH": result = DCRUSH; break;
		case "DMAGIC": result = DMAGIC; break;
		case "DRANGE": result = DRANGE; break;
		case "STR":    result = STR; break;
		case "RSTR":   result = RSTR; break;
		case "MDMG":   result = MDMG; break;
		case "ASPEED": result = ASPEED; break;
		case "PRAY":
		case "PRAYER": result = PRAYER; break;
		default:       result = null; break;
		}
		
		return result;
	}
}
