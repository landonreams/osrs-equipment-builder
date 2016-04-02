package osrs.model.data;

/**
 * Item Database object for retrieving items from the database
 * @author Landon Reams
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import osrs.model.npc.Item;
import osrs.util.Comparison;

public class ItemDatabase {
	public static final String DB_PATH = "./db/items.db";
	private static final String URL_PREFIX = "jdbc:sqlite:";

	public static class Search {
		private String name;
		private Slot   slot;
		private Integer[] bonuses = new Integer[ArmorStats.COUNT];
		private Comparison[] comparisons = new Comparison[ArmorStats.COUNT];

		public Search name(String name) { this.name = name; return this; }
		public Search slot(Slot slot)   { this.slot = slot; return this; }

		public Search stat(ArmorStats stat, Comparison comp, Integer value) {
			bonuses[stat.index()] = value;
			comparisons[stat.index()] = comp;
			return this;
		}

		protected String getPreparedStatement() {
			StringBuilder stmt = new StringBuilder();

			stmt.append("SELECT * FROM EQUIPMENT");

			boolean addedWhere = false;

			if(name != null) {
				if(!addedWhere) {
					addedWhere = true;
					stmt.append(" WHERE ");
				} else {
					stmt.append(" AND ");
				}
				stmt.append("NAME LIKE ?");
			}

			if(slot != null) {
				if(!addedWhere) {
					addedWhere = true;
					stmt.append(" WHERE ");
				} else {
					stmt.append(" AND ");
				}
				stmt.append("SLOT = ?");
			}

			for(int i = 0; i < bonuses.length; i++) {
				if(bonuses[i] != null) {
					if(!addedWhere) {
						addedWhere = true;
						stmt.append(" WHERE ");
					} else {
						stmt.append(" AND ");
					}
					stmt.append(ArmorStats.fromIndex(i).toString());
					stmt.append(" ");
					stmt.append(comparisons[i].toString());
					stmt.append(" ?");
				}
			}

			return stmt.toString();
		}

		protected Object[] getParams() {
			ArrayList<Object> paramsList = new ArrayList<>();

			if(name != null)
				paramsList.add("%" + name + "%");
			if(slot != null)
				paramsList.add(slot.toString().toUpperCase());

			for(int i = 0; i < bonuses.length; i++){
				if(bonuses[i] != null)
					paramsList.add(bonuses[i]);
			}

			Object[] params = new Object[paramsList.size()];
			return paramsList.toArray(params);
		}
	}

	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static final ItemDatabase DB = new ItemDatabase();
	private ItemDatabase() { }
	public static ItemDatabase getInstance() { return DB; }

	public Item get(String item){
		if(item == null)
			throw new IllegalArgumentException("String cannot be null.");

		Item result = null;
		conn = null; stmt = null;


		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);
			stmt = conn.prepareStatement("select * from equipment where name = ?");
			stmt.setString(1, item);
			rs = stmt.executeQuery();

			if(rs.next()){
				result = new Item();
				String name = rs.getString("name");
				Slot   slot = Slot.fromString(rs.getString("slot"));
				boolean is2h = rs.getBoolean("is2h");

				result.setName(name);
				result.setSlot(slot);
				result.set2h(is2h);

				for(ArmorStats as : ArmorStats.values()){
					result.setStat(as, rs.getInt(as.toString()));
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return result;
	}

	public Item[] getAll(String[] items){
		if(items == null)
			throw new IllegalArgumentException("Array cannot be null.");
		Item result[] = new Item[items.length];
		conn = null; stmt = null;

		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);
			stmt = conn.prepareStatement("select * from equipment where name = ?");

			for(int i = 0; i < items.length; i++){
				stmt.setString(1, items[i]);
				rs = stmt.executeQuery();

				if(rs.next()){
					result[i] = new Item();
					String name = rs.getString("name");
					Slot   slot = Slot.fromString(rs.getString("slot"));
					boolean is2h = rs.getBoolean("is2h");

					result[i].setName(name);
					result[i].setSlot(slot);
					result[i].set2h(is2h);

					for(ArmorStats as : ArmorStats.values()){
						result[i].setStat(as, rs.getInt(as.toString()));
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	public Item[] executeSearch(Search search) {
		if(search == null)
			throw new IllegalArgumentException("Search cannot be null.");
		ArrayList<Item> results = new ArrayList<Item>();
		conn = null; stmt = null;

		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);

			System.out.println(search.getPreparedStatement());

			stmt = conn.prepareStatement(search.getPreparedStatement());


			Object[] params = search.getParams();


			for(int i = 0; i < params.length; i++) {
				System.out.printf("Param %d: %s%n", i+1, params[i]);
				if(params[i] != null)
					stmt.setObject(i + 1, params[i]);
				else
					stmt.setNull(i + 1, -1);
			}

			rs = stmt.executeQuery();

			while(rs.next()) {
				Item temp = new Item();
				String name = rs.getString("name");
				Slot   slot = Slot.fromString(rs.getString("slot"));
				boolean is2h = rs.getBoolean("is2h");

				temp.setName(name);
				temp.setSlot(slot);
				temp.set2h(is2h);

				for(ArmorStats as : ArmorStats.values()){
					temp.setStat(as, rs.getInt(as.toString()));
				}

				results.add(temp);
			}

		} catch(Exception e){
			e.printStackTrace();
		} finally {
			if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return results.toArray(new Item[results.size()]);
	}


	public static void main(String[] args){
		ItemDatabase db = new ItemDatabase();

		Search s = new Search().slot(Slot.AMMO);

		Item[] list = db.executeSearch(s);

		for(Item ii : list){
			System.out.println(ii);
		}
	}


}
