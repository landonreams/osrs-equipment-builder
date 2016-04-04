package osrs.model.data;

public class Prayer {
	public enum Attack {
		NONE("None", 1.0),
		TIER1("Clarity of Thought", 1.05),
		TIER2("Improved Reflexes", 1.10),
		TIER3("Incredible Reflexes", 1.15),
		CHIVALRY("Chivalry", 1.15),
		PIETY("Piety", 1.20);

		private final String name;
		private final double boost;

		private Attack(String name, double boost) {
			this.name = name;
			this.boost = boost;
		}

		public int apply(int level){
			return (int)((double)level * boost);
		}

		@Override
		public String toString() { return name; }
	}
	public enum Strength {
		NONE("None", 1.0),
		TIER1("Burst of Strength", 1.05),
		TIER2("Superhuman Strength", 1.10),
		TIER3("Ultimate Strength", 1.15),
		CHIVALRY("Chivalry", 1.18),
		PIETY("Piety", 1.23);

		private final String name;
		private final double boost;

		private Strength(String name, double boost) {
			this.name = name;
			this.boost = boost;
		}

		public int apply(int level){
			return (int)((double)level * boost);
		}

		@Override
		public String toString() { return name; }
	}
	public enum Ranged {
		NONE("None", 1.0),
		TIER1("Sharp Eye", 1.05),
		TIER2("Hawk Eye", 1.10),
		TIER3("Eagle Eye", 1.15);

		private final String name;
		private final double boost;

		private Ranged(String name, double boost) {
			this.name = name;
			this.boost = boost;
		}

		public int apply(int level){
			return (int)((double)level * boost);
		}

		@Override
		public String toString() { return name; }
	}
	public enum Magic {
		NONE("None", 1.0),
		TIER1("Mystic Will", 1.05),
		TIER2("Mystic Lore", 1.10),
		TIER3("Mystic Might", 1.15);

		private final String name;
		private final double boost;

		private Magic(String name, double boost) {
			this.name = name;
			this.boost = boost;
		}

		public int apply(int level){
			return (int)((double)level * boost);
		}

		@Override
		public String toString() { return name; }
	}
}
