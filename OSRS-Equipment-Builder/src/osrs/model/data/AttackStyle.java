package osrs.model.data;

public class AttackStyle {
	public enum Melee {
		ACCURATE("Accurate"), AGGRESSIVE("Aggressive"), DEFENSIVE("Defensive"), CONTROLLED("Controlled");

		private final String name;

		private Melee(String name) {
			this.name = name;
		}
		@Override
		public String toString() { return name; }
	}
	public enum Ranged {
		ACCURATE("Accurate"), RAPID("Rapid"), LONGRANGE("Longrange");

		private final String name;

		private Ranged(String name) {
			this.name = name;
		}
		@Override
		public String toString() { return name; }
	}
	public enum Magic {
		ACCURATE("Accurate"), DEFENSIVE("Defensive");

		private final String name;

		private Magic(String name) {
			this.name = name;
		}
		@Override
		public String toString() { return name; }
	}
}