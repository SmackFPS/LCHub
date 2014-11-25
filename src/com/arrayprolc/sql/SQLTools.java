package com.arrayprolc.sql;

import java.sql.SQLException;
import java.sql.Statement;

import me.mike1665.Main.Main;

public class SQLTools {

	public static void statementTest(){
		try {
			Statement statement = Main.c.createStatement();
			statement.executeUpdate("INSERT INTO tokens (`PlayerRanks`) VALUES ('" + "ThisIsATestYay" + "');");
			System.out.println("Inserted info");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
