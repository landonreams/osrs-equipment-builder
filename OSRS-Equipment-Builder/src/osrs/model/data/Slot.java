package osrs.model.data;

public enum Slot {
	AMMO("Ammo"),
	BODY("Body"),
	BOOTS("Boots"),
	CAPE("Cape"),
	HANDS("Hands"),
	HEAD("Head"),
	LEGS("Legs"),
	NECK("Neck"),
	RING("Ring"),
	SHIELD("Shield"),
	WEAPON("Weapon"),
	NONE("None");

	public static final int COUNT = 11;

	private String name;
	Slot(String name){ this.name = name; }

	@Override
	public String toString(){ return name; }

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

		default: return NONE;
		}
	}
}
