package osrs.util;

import java.text.ParseException;

import osrs.model.data.Levels;
import osrs.model.npc.Player;

public class Requirement {
	public Integer attack, strength, defence, ranged, magic, prayer;

	public Requirement() {
		attack = 1;
		strength = 1;
		defence = 1;
		ranged = 1;
		prayer = 1;
		magic = 1;
	}

	public static boolean isValid(String reqString) {
		try {
			parse(reqString);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Requirement parse(String str) throws ParseException, IllegalArgumentException {
		if(str == null)
			return null;
		if(str.equals(""))
			return new Requirement();

		String[] tokens = str.split(";");
		Requirement result = new Requirement();
		for(String s : tokens){
			String[] pair = s.split(":");

			if(pair.length != 2)
				throw new ParseException("Improperly formatted token: "+s, 0);

			Integer req = null;
			try {
				req = Integer.parseInt(pair[1]);
			} catch (Exception e) {
				throw new ParseException("Unable to parse value: "+pair[1], 0);
			}

			if(req < 1 || req > 99)
				throw new IllegalArgumentException("Level requirement must be between 1 and 99 inclusive!");

			switch(pair[0]) {
			case "a": result.attack = req; break;
			case "s": result.strength = req; break;
			case "d": result.defence = req; break;
			case "r": result.ranged = req; break;
			case "m": result.magic = req; break;
			case "p": result.prayer = req; break;
			default: throw new ParseException("Invalid requirement token: "+pair[0], 0);
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if(attack > 1)
			sb.append(String.format("a:%d;",attack));
		if(strength > 1)
			sb.append(String.format("s:%d;",strength));
		if(defence > 1)
			sb.append(String.format("d:%d;",defence));
		if(ranged > 1)
			sb.append(String.format("r:%d;",ranged));
		if(magic > 1)
			sb.append(String.format("m:%d;",magic));
		if(prayer > 1)
			sb.append(String.format("p:%d;",prayer));

		if(sb.length() > 0)
			sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	public boolean canEquip(Player player) {
		return player.getLevel(Levels.ATTACK) >= attack &&
			   player.getLevel(Levels.STRENGTH) >= strength &&
			   player.getLevel(Levels.DEFENCE) >= defence &&
			   player.getLevel(Levels.RANGED) >= ranged &&
			   player.getLevel(Levels.MAGIC) >= magic &&
			   player.getLevel(Levels.PRAYER) >= prayer;
	}
}
