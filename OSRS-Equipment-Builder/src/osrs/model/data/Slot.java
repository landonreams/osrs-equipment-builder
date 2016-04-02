package osrs.model.data;

public enum Slot {
	AMMO("Ammo", 0),
	BODY("Body", 1),
	BOOTS("Boots", 2),
	CAPE("Cape", 3),
	HANDS("Hands", 4),
	HEAD("Head", 5),
	LEGS("Legs", 6),
	NECK("Neck", 7),
	RING("Ring", 8),
	SHIELD("Shield", 9),
	WEAPON("Weapon", 10);

	public static final int COUNT = 11;
	private final int index;

	private String name;
	Slot(String name, int index){
		this.name = name;
		this.index = index;
		}


	public int index() { return index; }

	@Override
	public String toString(){ return name; }

	public static Slot fromIndex(int index) {
		for(Slot slot : Slot.values()) {
			if(slot.index() == index)
				return slot;
		}
		return null;
	}

	public static Slot fromString(String str){
		String lwr = str.toLowerCase();
		switch(lwr){
		case "neck":
		case "necklace":
		case "amulet": return NECK;

		case "head":
		case "helmet": return HEAD;

		case "body":
		case "torso": return BODY;

		case "legs":
		case "legwear": return LEGS;

		case "feet":
		case "boots": return BOOTS;

		case "hands":
		case "gloves": return HANDS;

		case "shield": return SHIELD;

		case "weapon":
		case "2h": return WEAPON;

		case "cape": return CAPE;

		case "ring": return RING;

		case "ammo":
		case "ammunition": return AMMO;

		default: return null;
		}
	}
}
