package osrs.model.data;

public class Potion {
	public enum Attack {
		NONE("None", 0, 1.0),
		REG("Attack potion", 3, 1.1),
		CMB("Combat potion", 3, 1.1),
		SUP("Super attack", 5, 1.15),
		OVL("Overload", 5, 1.15),
		BRW("Zamorak brew", 2, 1.2);

		private final int constant;
		private final double percentage;
		private final String name;

		private Attack(String name, int constant, double percentage) {
			this.constant = constant;
			this.name = name;
			this.percentage = percentage;
		}

		public int apply(int level) {
			double result = level;
			result = level * percentage + constant;
			return (int) result;
		}

		@Override
		public String toString() { return name; }
	}

	public enum Strength {
		NONE("None", 0, 1.0),
		REG("Strength potion", 3, 1.1),
		CMB("Combat potion", 3, 1.1),
		SUP("Super attack", 5, 1.15),
		OVL("Overload", 5, 1.15),
		BRW("Zamorak brew", 2, 1.12);

		private final int constant;
		private final double percentage;
		private final String name;

		private Strength(String name, int constant, double percentage) {
			this.constant = constant;
			this.name = name;
			this.percentage = percentage;
		}

		public int apply(int level) {
			double result = level;
			result = level * percentage + constant;
			return (int) result;
		}

		@Override
		public String toString() { return name; }
	}

	public enum Ranged {
		NONE("None", 0, 1.0),
		REG("Ranging potion", 4, 1.1),
		OVL("Overload", 5, 1.15);

		private final int constant;
		private final double percentage;
		private final String name;

		private Ranged(String name, int constant, double percentage) {
			this.constant = constant;
			this.name = name;
			this.percentage = percentage;
		}

		public int apply(int level) {
			double result = level;
			result = level * percentage + constant;
			return (int) result;
		}

		@Override
		public String toString() { return name; }
	}

	public enum Magic {
		NONE("None", 0, 1.0),
		REG("Magic potion", 4, 1.0),
		OVL("Overload", 5, 1.15);

		private final int constant;
		private final double percentage;
		private final String name;

		private Magic(String name, int constant, double percentage) {
			this.constant = constant;
			this.name = name;
			this.percentage = percentage;
		}

		public int apply(int level) {
			double result = level;
			result = level * percentage + constant;
			return (int) result;
		}

		@Override
		public String toString() { return name; }
	}
}
