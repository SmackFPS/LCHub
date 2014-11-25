package com.arrayprolc.sql;

import java.sql.SQLException;
import java.sql.Statement;

import me.mike1665.Main.Main;

public class SQLTools {

	public static void statementTest(){
		try {
			Statement statement = Main.c.createStatement();
			statement.executeUpdate("DELETE FROM tokens (`Rank`) VALUES ('" + "ArrayPro:OWNER,mike1665:DEV" + "');");
			statement.executeUpdate("INSERT INTO tokens (`Rank`) VALUES ('" + "ArrayPro:OWNER,mike1665:DEV" + "');");
			System.out.println("Inserted info");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
