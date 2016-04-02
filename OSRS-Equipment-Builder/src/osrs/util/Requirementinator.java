package osrs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * A command line utility for adding requirements to items.
 * @author Landon
 *
 */
public class Requirementinator {
	public static final String DB_PATH = "./db/items.db";
	private static final String URL_PREFIX = "jdbc:sqlite:";


	public static void main(String[] args) {

		Connection conn = null;
		Statement  stmt = null;
		PreparedStatement upd8 = null;
		ResultSet    rs = null;

		String sql = "SELECT ROWID, * FROM EQUIPMENT WHERE REQUIREMENTS IS NULL LIMIT 1";


		try {

			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_PREFIX + DB_PATH);
			stmt = conn.createStatement();
			upd8 = conn.prepareStatement("UPDATE EQUIPMENT SET REQUIREMENTS = ? WHERE ROWID = ?");

			while(( rs = stmt.executeQuery(sql)).next()) {
				int rowid = rs.getInt("ROWID");
				String name = rs.getString("NAME");

				System.out.printf("[%d] Item is %s. Input requirements: ",rowid,name);

				Scanner s = new Scanner(System.in);
				String input;

				boolean good = false;
				while( !good ) {
					good = true;
					input = s.nextLine();

					if(Requirement.isValid(input) || input.equals("")) {
						upd8.setString(1, Requirement.parse(input).toString());
						upd8.setInt(2, rowid);
						upd8.execute();
					} else {
						System.out.printf("INVALID! [%d] Item is %s. Input requirements: ",rowid,name);
						good = false;
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
