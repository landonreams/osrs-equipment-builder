package osrs.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import osrs.model.data.ArmorStats;
import osrs.model.data.ItemDatabase;
import osrs.model.data.Slot;
import osrs.model.npc.Item;

public class ItemDatabaseTest {

	private final ItemDatabase db = ItemDatabase.getInstance();
	private Item DRAGON_HALBERD, RUNE_PLATEBODY, ABYSSAL_WHIP;


	@Before
	public void setUp(){
		DRAGON_HALBERD = new Item();
		DRAGON_HALBERD.setName("Dragon halberd");
		DRAGON_HALBERD.set2h(true);
		DRAGON_HALBERD.setSlot(Slot.WEAPON);

		DRAGON_HALBERD.setStat(ArmorStats.ASTAB,  70);
		DRAGON_HALBERD.setStat(ArmorStats.ASLASH, 95);
		DRAGON_HALBERD.setStat(ArmorStats.ACRUSH, 0);
		DRAGON_HALBERD.setStat(ArmorStats.AMAGIC, -4);
		DRAGON_HALBERD.setStat(ArmorStats.ARANGE, 0);

		DRAGON_HALBERD.setStat(ArmorStats.DSTAB, -1);
		DRAGON_HALBERD.setStat(ArmorStats.DSLASH, 4);
		DRAGON_HALBERD.setStat(ArmorStats.DCRUSH, 5);
		DRAGON_HALBERD.setStat(ArmorStats.DMAGIC, 0);
		DRAGON_HALBERD.setStat(ArmorStats.DRANGE, 0);

		DRAGON_HALBERD.setStat(ArmorStats.STR, 89);
		DRAGON_HALBERD.setStat(ArmorStats.RSTR, 0);
		DRAGON_HALBERD.setStat(ArmorStats.MDMG, 0);
		DRAGON_HALBERD.setStat(ArmorStats.PRAYER, 0);
		DRAGON_HALBERD.setStat(ArmorStats.ASPEED, 3);

		RUNE_PLATEBODY = new Item();
		RUNE_PLATEBODY.setName("Rune platebody");
		RUNE_PLATEBODY.set2h(false);
		RUNE_PLATEBODY.setSlot(Slot.BODY);

		RUNE_PLATEBODY.setStat(ArmorStats.ASTAB,  0);
		RUNE_PLATEBODY.setStat(ArmorStats.ASLASH, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.ACRUSH, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.AMAGIC, -30);
		RUNE_PLATEBODY.setStat(ArmorStats.ARANGE, -10);

		RUNE_PLATEBODY.setStat(ArmorStats.DSTAB,  82);
		RUNE_PLATEBODY.setStat(ArmorStats.DSLASH, 80);
		RUNE_PLATEBODY.setStat(ArmorStats.DCRUSH, 72);
		RUNE_PLATEBODY.setStat(ArmorStats.DMAGIC, -6);
		RUNE_PLATEBODY.setStat(ArmorStats.DRANGE, 80);

		RUNE_PLATEBODY.setStat(ArmorStats.STR, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.RSTR, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.MDMG, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.PRAYER, 0);
		RUNE_PLATEBODY.setStat(ArmorStats.ASPEED, -1);

		ABYSSAL_WHIP = new Item();
		ABYSSAL_WHIP.setName("Abyssal whip");
		ABYSSAL_WHIP.set2h(false);
		ABYSSAL_WHIP.setSlot(Slot.WEAPON);

		ABYSSAL_WHIP.setStat(ArmorStats.ASTAB,  0);
		ABYSSAL_WHIP.setStat(ArmorStats.ASLASH, 82);
		ABYSSAL_WHIP.setStat(ArmorStats.ACRUSH, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.AMAGIC, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.ARANGE, 0);

		ABYSSAL_WHIP.setStat(ArmorStats.DSTAB, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.DSLASH, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.DCRUSH, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.DMAGIC, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.DRANGE, 0);

		ABYSSAL_WHIP.setStat(ArmorStats.STR, 82);
		ABYSSAL_WHIP.setStat(ArmorStats.RSTR, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.MDMG, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.PRAYER, 0);
		ABYSSAL_WHIP.setStat(ArmorStats.ASPEED, 6);
	}

	@Test
	public void testGetItemThatExists(){
		Item actual = db.get("Dragon halberd");
		Item expected = DRAGON_HALBERD;

		//Data pulled from OSRS Wikia


		assertEquals("Items are not equal!", expected, actual);
	}

	@Test
	public void testGetItemThatDoesNotExist(){
		Item actual = db.get("Stick of Totally Existant Doom");

		assertNull("Non-existant item should be null", actual);
	}

	@Test
	public void testGetAllExistantItems(){
		String[] items = {
				"Dragon halberd",
				"Rune platebody",
				"Abyssal whip"
		};

		Item[] actual = db.getAll(items);
		Item[] expected = new Item[3];

		expected[0] = DRAGON_HALBERD;
		expected[1] = RUNE_PLATEBODY;
		expected[2] = ABYSSAL_WHIP;

		assertNotNull("Actual should not be null!", actual);
		assertEquals("Arrays are of inequal length!", actual.length, expected.length);

		for(int i = 0; i < actual.length; i++){
			assertEquals("Item at index "+i+" is not equal!", actual[i], expected[i]);
		}
	}

	@Test
	public void testGetAllOneExists(){
		String[] items = {
				"This one exists",
				"Dragon halberd",
				"This one too!"
		};

		Item[] actual = db.getAll(items);
		Item[] expected = new Item[3];

		expected[0] = null;
		expected[1] = DRAGON_HALBERD;
		expected[2] = null;

		assertNotNull("Actual should not be null!", actual);
		assertEquals("Arrays are of inequal length!", actual.length, expected.length);

		for(int i = 0; i < actual.length; i++){
			assertEquals("Item at index "+i+" is not equal!", actual[i], expected[i]);
		}
	}

	@Test
	public void testGetGetAllNoneExist(){
		String[] items = {
				"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
				"HELP I'M TRAPPED IN A COMPUTER",
				"EVERYTHING LOOKS LIKE THE MATRIX"
		};

		Item[] actual = db.getAll(items);
		Item[] expected = new Item[3];

		expected[0] = null;
		expected[1] = null;
		expected[2] = null;

		assertNotNull("Actual should not be null!", actual);
		assertEquals("Arrays are of inequal length!", actual.length, expected.length);

		for(int i = 0; i < actual.length; i++){
			assertEquals("Item at index "+i+" is not equal!", actual[i], expected[i]);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNone(){
		db.get(null);

		fail("Expected an IllegalArgumentException.");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAllNone(){
		db.getAll(null);

		fail("Expected an IllegalArgumentException.");
	}

	@Test
	public void testGetAllEmpty(){
		Item[] actual = db.getAll(new String[]{ });
		Item[] expected = new Item[]{ };

		assertNotNull("Actual should not be null!", actual);
		assertEquals("Arrays are of inequal length!", actual.length, expected.length);
	}

}
