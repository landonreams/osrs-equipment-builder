package osrs.model.npc;

import osrs.model.data.Levels;

public class NPC extends Fightable {

	public NPC() {
		super();
		setName("NPC");
	}

	@Override
	public int applyPotion(Levels level, int value) {
		return value;
	}

	public int applyPrayer(Levels level, int value) {
		return value;
	}
}
