package osrs.model.data;

public enum Spell {
	STRIKE_WIND("Wind strike", 2, 1),
	STRIKE_WATER("Water strike", 4, 5),
	STRIKE_EARTH("Earth strike", 6, 9),
	STRIKE_FIRE("Fire strike", 8, 13),
	BOLT_WIND("Wind bolt", 9, 17),
	BOLT_WATER("Water bolt", 10, 23),
	BOLT_EARTH("Earth bolt", 11, 29),
	BOLT_FIRE("Fire bolt", 12, 35),
	BLAST_WIND("Wind blast", 13, 41),
	BLAST_WATER("Water blast", 14, 47),
	BLAST_EARTH("Earth blast", 15, 53),
	BLAST_FIRE("Fire blast", 16, 59),
	WAVE_WIND("Wind wave", 17, 62),
	WAVE_WATER("Water wave", 18, 65),
	WAVE_EARTH("Earth wave", 19, 70),
	WAVE_FIRE("Fire wave", 20, 75),
	CRUMBLE_UNDEAD("Crumble undead", 15, 39),
	IBAN_BLAST("Iban blast", 25, 50),
	MAGIC_DART("Magic dart", -1, 50),
	GOD_SARA("Saradomin strike", 20, 60),
	GOD_GUTH("Claws of Guthix", 20, 60),
	GOD_ZAM("Flames of Zamorak", 20, 60),
	CHARGED_SARA("Saradomin strike - charged", 30, 80),
	CHARGED_GUTH("Claws of Guthix - charged", 30, 80),
	CHARGED_ZAM("Flames of Zamorak - charged", 30, 80),
	RUSH_SMOKE("Smoke rush", 14, 50),
	RUSH_SHADOW("Shadow rush", 15, 52),
	RUSH_BLOOD("Blood rush", 16, 56),
	RUSH_ICE("Ice rush", 17, 58),
	BURST_SMOKE("Smoke burst", 18, 62),
	BURST_SHADOW("Shadow burst", 19, 64),
	BURST_BLOOD("Blood burst", 21, 68),
	BURST_ICE("Ice burst", 22, 70),
	BLITZ_SMOKE("Smoke blitz", 23, 74),
	BLITZ_SHADOW("Shadow blitz", 24, 76),
	BLITZ_BLOOD("Blood blitz", 25, 80),
	BLITZ_ICE("Ice blitz", 26, 82),
	BARRAGE_SMOKE("Smoke barrage", 27, 86),
	BARRAGE_SHADOW("Shadow barrage", 28, 88),
	BARRAGE_BLOOD("Blood barrage", 29, 92),
	BARRAGE_ICE("Ice barrage", 30, 94),
	TRIDENT_SEAS("Trident of the seas", -1, 75),
	TRIDENT_SWAMP("Trident of the swamp", -1, 75),
	NONE("No spell", 0, -1);

	private final int maxHit;
	private final int level;
	private final String name;

	private Spell(String name, int maxHit, int level){
		this.maxHit = maxHit;
		this.name   = name;
		this.level  = level;
	}

	public int maxHit(int magicLevel){
		switch(this){
		case MAGIC_DART: return magicLevel / 10 + 10;
		case TRIDENT_SEAS: return magicLevel / 3 - 5;
		case TRIDENT_SWAMP: return magicLevel / 3 - 2;
		default: return maxHit;
		}
	}

	public int requirement() { return level; }

	@Override
	public String toString(){
		return name;
	}
}

