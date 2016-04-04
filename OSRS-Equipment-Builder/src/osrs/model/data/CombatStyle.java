package osrs.model.data;

public enum CombatStyle {
	MELEE("Melee"), RANGED("Ranged"), MAGIC("Magic");

	private final String name;

	private CombatStyle(String name) { this.name = name; }

	@Override
	public String toString() { return name; }
}
