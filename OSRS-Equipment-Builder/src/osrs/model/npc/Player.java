package osrs.model.npc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import osrs.model.data.ArmorStats;
import osrs.model.data.AttackStyle;
import osrs.model.data.CombatStyle;
import osrs.model.data.Levels;
import osrs.model.data.Potion;
import osrs.model.data.Prayer;
import osrs.model.data.Spell;
import osrs.model.data.WeaponType;

public class Player extends Fightable {
	private ArmorSet armor;

	private ObjectProperty<Potion.Attack>   attackPotionProperty;
	private ObjectProperty<Potion.Strength> strengthPotionProperty;
	private ObjectProperty<Potion.Ranged>   rangedPotionProperty;
	private ObjectProperty<Potion.Magic>    magicPotionProperty;

	private ObjectProperty<Prayer.Attack>   attackPrayerProperty;
	private ObjectProperty<Prayer.Strength> strengthPrayerProperty;
	private ObjectProperty<Prayer.Ranged>   rangedPrayerProperty;
	private ObjectProperty<Prayer.Magic>    magicPrayerProperty;

	private ObjectProperty<CombatStyle>        combatStyleProperty;
	private ObjectProperty<AttackStyle.Melee>  meleeStyleProperty;
	private ObjectProperty<AttackStyle.Ranged> rangedStyleProperty;
	private ObjectProperty<AttackStyle.Magic>  magicStyleProperty;

	private ObjectProperty<Spell>      spellProperty;
	private ObjectProperty<WeaponType> weaponTypeProperty;

	public static final int DEFAULT_PLAYER_SPEED = 6;

	private static final String HISCORES_LOOKUP = "http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=%s";

	public static class Builder<T extends Player> extends Fightable.Builder<T> {
		private List<String> itemsToEquip = new ArrayList<String>();

		public Builder<T> equip(String item) {
			itemsToEquip.add(item);
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected T getInstance() {
			String[] items = new String[itemsToEquip.size()];
			items = itemsToEquip.toArray(items);
			return (T) new Player(this, items);
		}
	}

	public Double getCombatLevel() {
		Double base = 0.25 * (this.getLevel(Levels.DEFENCE) + this.getLevel(Levels.HITPOINTS) + Math.floor( this.getLevel(Levels.PRAYER) / 2));
		Double melee = 0.325 * (this.getLevel(Levels.ATTACK) + this.getLevel(Levels.STRENGTH));
		Double range = 0.325 * (Math.floor(this.getLevel(Levels.RANGED)/2) + this.getLevel(Levels.RANGED));
		Double mage  = 0.325 * (Math.floor(this.getLevel(Levels.MAGIC)/2) + this.getLevel(Levels.MAGIC));

		Double result = base + Math.max(melee, Math.max(range, mage));

		return result;
	}

	public Player() {
		super(new Player.Builder<>().name("None"));
		armor = new ArmorSet();
		initProperties();
	}

	protected Player(Builder<? extends Player> builder, String[] items) {
		super(builder);
		armor = new ArmorSet();
		armor.equipArray(items);
		initProperties();
	}

	private void initProperties() {
		attackPotionProperty = new SimpleObjectProperty<>(Potion.Attack.NONE);
		strengthPotionProperty = new SimpleObjectProperty<>(Potion.Strength.NONE);
		rangedPotionProperty = new SimpleObjectProperty<>(Potion.Ranged.NONE);
		magicPotionProperty = new SimpleObjectProperty<>(Potion.Magic.NONE);

		attackPrayerProperty = new SimpleObjectProperty<>(Prayer.Attack.NONE);
		strengthPrayerProperty = new SimpleObjectProperty<>(Prayer.Strength.NONE);
		rangedPrayerProperty = new SimpleObjectProperty<>(Prayer.Ranged.NONE);
		magicPrayerProperty = new SimpleObjectProperty<>(Prayer.Magic.NONE);

		combatStyleProperty = new SimpleObjectProperty<>(CombatStyle.MELEE);
		meleeStyleProperty = new SimpleObjectProperty<>(AttackStyle.Melee.CONTROLLED);
		rangedStyleProperty = new SimpleObjectProperty<>(AttackStyle.Ranged.RAPID);
		magicStyleProperty = new SimpleObjectProperty<>(AttackStyle.Magic.ACCURATE);

		weaponTypeProperty = new SimpleObjectProperty<>(WeaponType.CRUSH);
		spellProperty = new SimpleObjectProperty<>(Spell.NONE);
	}

	@Override
	public void setStat(ArmorStats stat, Integer value) { throw new UnsupportedOperationException("Player stats are determined by equipment!"); }

	protected void updateStats() {
		for(ArmorStats stat : ArmorStats.values() ){
			Integer value = armor.getStat(stat);
			super.setStat(stat, value);
		}
	}

	public ArmorSet equipSet(ArmorSet set) {
		ArmorSet old = armor;
		armor = set;
		updateStats();
		return old;
	}

	public ArmorSet getArmor() { return armor; }

	public void equip(String item) {
		armor.equip(item);
		updateStats();
	}

	public void equipList(String[] items) {
		armor.equipArray(items);
		updateStats();
	}



	public Potion.Attack getAttackPotion() { return attackPotionProperty.get(); }
	public void setAttackPotion(Potion.Attack potion) { attackPotionProperty.set(potion); }
	public ObjectProperty<Potion.Attack> attackPotionProperty() { return attackPotionProperty; }

	public Potion.Strength getStrengthPotion() { return strengthPotionProperty.get(); }
	public void setStrengthPotion(Potion.Strength potion) { strengthPotionProperty.set(potion); }
	public ObjectProperty<Potion.Strength> strengthPotionProperty() { return strengthPotionProperty; }

	public Potion.Ranged getRangedPotion() { return rangedPotionProperty.get(); }
	public void setRangedPotion(Potion.Ranged potion) { rangedPotionProperty.set(potion); }
	public ObjectProperty<Potion.Ranged> rangedPotionProperty() { return rangedPotionProperty; }

	public Potion.Magic getMagicPotion() { return magicPotionProperty.get(); }
	public void setMagicPotion(Potion.Magic potion) { magicPotionProperty.set(potion); }
	public ObjectProperty<Potion.Magic> magicPotionProperty() { return magicPotionProperty; }

	public Prayer.Attack getAttackPrayer() { return attackPrayerProperty.get(); }
	public void setAttackPrayer(Prayer.Attack prayer) { attackPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Attack> attackPrayerProperty() { return attackPrayerProperty; }

	public Prayer.Strength getStrengthPrayer() { return strengthPrayerProperty.get(); }
	public void setStrengthPrayer(Prayer.Strength prayer) { strengthPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Strength> strengthPrayerProperty() { return strengthPrayerProperty; }

	public Prayer.Ranged getRangedPrayer() { return rangedPrayerProperty.get(); }
	public void setRangedPrayer(Prayer.Ranged prayer) { rangedPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Ranged> rangedPrayerProperty() { return rangedPrayerProperty; }

	public Prayer.Magic getMagicPrayer() { return magicPrayerProperty.get(); }
	public void setMagicPrayer(Prayer.Magic prayer) { magicPrayerProperty.set(prayer); }
	public ObjectProperty<Prayer.Magic> magicPrayerProperty() { return magicPrayerProperty; }

	public CombatStyle getCombatStyle() { return combatStyleProperty.get(); }
	public void setCombatStyle(CombatStyle style) { combatStyleProperty.set(style); }
	public ObjectProperty<CombatStyle> combatStyleProperty() { return combatStyleProperty; }

	public AttackStyle.Melee getMeleeStyle() { return meleeStyleProperty.get(); }
	public void setMeleeStyle(AttackStyle.Melee style) { meleeStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Melee> meleeStyleProperty() { return meleeStyleProperty; }

	public AttackStyle.Ranged getRangedStyle() { return rangedStyleProperty.get(); }
	public void setRangedStyle(AttackStyle.Ranged style) { rangedStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Ranged> rangedStyleProperty() { return rangedStyleProperty; }

	public AttackStyle.Magic getMagicStyle() { return magicStyleProperty.get(); }
	public void setMagicStyle(AttackStyle.Magic style) { magicStyleProperty.set(style); }
	public ObjectProperty<AttackStyle.Magic> magicStyleProperty() { return magicStyleProperty; }

	public void setWeaponType(WeaponType weaponType) { this.weaponTypeProperty.set(weaponType); }
	public WeaponType getWeaponType() { return weaponTypeProperty.get(); }
	public ObjectProperty<WeaponType> weaponTypeProperty() { return weaponTypeProperty; }

	public static Player fromHiscores(String name) {
		try {
			System.out.println("URL IS: "+String.format(HISCORES_LOOKUP, name));
			URL hiscores = new URL(String.format(HISCORES_LOOKUP, name));
			BufferedReader in = new BufferedReader(
								new InputStreamReader(
								hiscores.openConnection().getInputStream()));

			ArrayList<String> lines = new ArrayList<String>();
			String inputLine;

			int count = 0;

			while((inputLine = in.readLine()) != null && count < 8) {
				lines.add(inputLine);
				count++;
			}
			//System.out.println(fullText);

			/*
			 * 0: TOTAL
			 * 1: ATTACK
			 * 2: DEFENCE
			 * 3: STRENGTH
			 * 4: HITPOINTS
			 * 5: RANGED
			 * 6: PRAYER
			 * 7: MAGIC
			 */

			Player.Builder<Player> builder = new Player.Builder<>();

			builder.name(name);

			for(int i = 1; i < 8; i++) {
				String[] skill = lines.get(i).split(",");
				Integer level = Integer.parseInt(skill[1]);
				switch(i) {
				case 1: builder.attack(level); break;
				case 2: builder.defence(level); break;
				case 3: builder.strength(level); break;
				case 4: builder.hitpoints(level); break;
				case 5: builder.ranged(level); break;
				case 6: builder.prayer(level); break;
				case 7: builder.magic(level); break;
				default: throw new ParseException("Unable to parse URL!", 0);
				}
			}

			return builder.build();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void duplicate(Player other) {

	}

	public void print() {
		System.out.println("PLAYER: " + name.get());
		System.out.println("- Atk: " + levels.get(Levels.ATTACK).get());
		System.out.println("- Str: " + levels.get(Levels.STRENGTH).get());
		System.out.println("- Def: " + levels.get(Levels.DEFENCE).get());
		System.out.println("- Rng: " + levels.get(Levels.RANGED).get());
		System.out.println("- Mag: " + levels.get(Levels.MAGIC).get());
		System.out.println("- Pry: " + levels.get(Levels.PRAYER).get());
		System.out.println("- Hit: " + levels.get(Levels.HITPOINTS).get());
	}

	public int getMaxHit() {
		int armorStr, lvlStr, effectiveStr, baseMax;

		switch(weaponTypeProperty.get()) {
		case STAB:
		case SLASH:
		case CRUSH:
			armorStr = stats.get(ArmorStats.STR).get();
			lvlStr   = levels.get(Levels.STRENGTH).get();

			effectiveStr = strengthPotionProperty.get().apply(lvlStr);
			effectiveStr = strengthPrayerProperty.get().apply(effectiveStr);

			//TODO: APPLY ARMOR SET BONUS

			switch(meleeStyleProperty.get()) {
			case AGGRESSIVE: effectiveStr += 3; break;
			case CONTROLLED: effectiveStr += 1; break;
			default: break;
			}

			baseMax = (int)( 1.3 +
								 effectiveStr / 10.0 +
								 armorStr / 80.0 +
								 (effectiveStr * armorStr) / 640.0);
			return baseMax;
		case RANGED:
			armorStr = stats.get(ArmorStats.RSTR).get();
			lvlStr   = levels.get(Levels.RANGED).get();

			effectiveStr = rangedPotionProperty.get().apply(lvlStr);
			effectiveStr = rangedPrayerProperty.get().apply(effectiveStr);

			//APPLY VOID BONUS

			switch(rangedStyleProperty.get()) {
			case ACCURATE: effectiveStr += 3; break;
			default: break;
			}

			baseMax = (int)( 1.3 +
							 effectiveStr / 10.0 +
							 armorStr / 80.0 +
							 (effectiveStr * armorStr) / 640.0);
			return baseMax;
		case MAGIC:
			armorStr = stats.get(ArmorStats.MDMG).get();
			lvlStr = levels.get(Levels.MAGIC).get();

			effectiveStr = magicPotionProperty.get().apply(lvlStr);
			// Magic Prayers do not affect max hit

			baseMax = spellProperty.get().maxHit(effectiveStr);

			double percentageBuff = 1.0 + armorStr / 100.0;

			baseMax = (int)(percentageBuff * baseMax);

			return baseMax;
		}

		return -1;
	}

	public double getHitChance(Fightable target) {
		double hitChance = 0.0;

		int baseLevel, defenderLevel;

		switch(weaponTypeProperty.get()) {
		case STAB:
		case SLASH:
		case CRUSH:
			baseLevel = levels.get(Levels.ATTACK).get();
			baseLevel = attackPotionProperty.get().apply(baseLevel);
			baseLevel = attackPrayerProperty.get().apply(baseLevel);
			//TODO: Armor set bonus!

			switch(meleeStyleProperty.get()) {
			case ACCURATE:   baseLevel += 3; break;
			case CONTROLLED: baseLevel += 1; break;
			default: break;
			}

			break;
		case RANGED:
			baseLevel = levels.get(Levels.RANGED).get();
			baseLevel = rangedPotionProperty.get().apply(baseLevel);
			baseLevel = rangedPrayerProperty.get().apply(baseLevel);

			if(rangedStyleProperty.get() == AttackStyle.Ranged.ACCURATE)
				baseLevel += 3;
			break;
		case MAGIC:
			baseLevel = levels.get(Levels.MAGIC).get();
			baseLevel = magicPotionProperty.get().apply(baseLevel);
			baseLevel = magicPrayerProperty.get().apply(baseLevel);
			break;
		default: baseLevel = 0;
		}

		baseLevel += 8;

		defenderLevel = target.getLevel(Levels.DEFENCE);
		defenderLevel = target.applyPotion(Levels.DEFENCE, defenderLevel);
		//TODO: Slight approximation since I'm assuming the target is using Controlled.
		defenderLevel += 9;

		if(weaponTypeProperty.get() == WeaponType.MAGIC) {
			//TODO: Another approximation. I don't plan on adding PvP dps yet...
			int magicLevel = target.getLevel(Levels.MAGIC);
			magicLevel = target.applyPotion(Levels.MAGIC, magicLevel);
			magicLevel = target.applyPrayer(Levels.MAGIC, magicLevel);
			magicLevel += 8;

			defenderLevel = (int)(0.7 * magicLevel + 0.3 * defenderLevel);
		}

		int offensiveBonus, defensiveBonus;

		switch(weaponTypeProperty.get()) {
		case CRUSH:
			offensiveBonus = stats.get(ArmorStats.ACRUSH).get();
			defensiveBonus = target.getStat(ArmorStats.DCRUSH);
			break;
		case MAGIC:
			offensiveBonus = stats.get(ArmorStats.AMAGIC).get();
			defensiveBonus = target.getStat(ArmorStats.DMAGIC);
			break;
		case RANGED:
			offensiveBonus = stats.get(ArmorStats.ARANGE).get();
			defensiveBonus = target.getStat(ArmorStats.DRANGE);
			break;
		case SLASH:
			offensiveBonus = stats.get(ArmorStats.ASLASH).get();
			defensiveBonus = target.getStat(ArmorStats.DSLASH);
			break;
		case STAB:
			offensiveBonus = stats.get(ArmorStats.ASTAB).get();
			defensiveBonus = target.getStat(ArmorStats.DSTAB);
			break;
		default: offensiveBonus = 0; defensiveBonus = 0;
		}

		double maxAttRoll = Math.floor(((1.0 + offensiveBonus)/64.0 ) * baseLevel);
		double maxDefRoll = Math.floor(((1.0 + defensiveBonus)/64.0 ) * defenderLevel);

		if(maxAttRoll > maxDefRoll) {
			hitChance = 1.0 - ( maxDefRoll + 1.0 ) / ( 2.0 * maxAttRoll );
		} else {
			hitChance = (maxAttRoll - 1.0) / (2 * maxDefRoll);
		}

		//System.out.println(hitChance);

		hitChance = Math.min(1.0, hitChance);
		hitChance = Math.max(0.0, hitChance);

		return hitChance;
	}

	public double attackSpeedInSeconds() {
		int aspeed = this.getStat(ArmorStats.ASPEED);

		return ( (10.0 - aspeed) * 0.6 );
	}




	public int applyPotion(Levels level, int value) {
		switch(level) {
		case ATTACK:  return attackPotionProperty.get().apply(value);
		case DEFENCE: return value;
		case MAGIC:   return magicPotionProperty.get().apply(value);
		case RANGED:  return rangedPotionProperty.get().apply(value);
		case STRENGTH:return strengthPotionProperty.get().apply(value);
		default: return value;
		}
	}
	public int applyPrayer(Levels level, int value) {
		switch(level) {
		case ATTACK:  return attackPrayerProperty.get().apply(value);
		case DEFENCE: return value;
		case MAGIC:   return magicPrayerProperty.get().apply(value);
		case RANGED:  return rangedPrayerProperty.get().apply(value);
		case STRENGTH:return strengthPrayerProperty.get().apply(value);
		default: return value;
		}
	}
}
