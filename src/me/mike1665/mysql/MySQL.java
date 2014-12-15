package me.mike1665.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.arrayprolc.coin.Multiplier;

public class MySQL {

    private static Connection connection;
    
    public MySQL(String ip, String userName, String password, String db) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + db + "?user=" + userName + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Getting a banned reason
    public String getBannedReason(Player p) {
        try {
                PreparedStatement statement = connection.prepareStatement("SELECT Reason FROM Bans WHERE UUID='" + p.getUniqueId() + "'");
                ResultSet result = statement.executeQuery();
               
                if (result.next()) {
                        return result.getString("Reason");
                } else {
                        return null;
                }
        } catch (Exception e) {
                e.printStackTrace();
                return "[[Failed to connect]]";
        }
    }
   
    //Banning the player
    public static void banPlayer(Player p, String reason) {
        try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `Bans`(`UUID`, `Reason`)\nVALUES ('" + p.getUniqueId() + "', '" + reason + "')");
                statement.executeUpdate();
                statement.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    //Unbanning the player
    public static void unbanPlayer(OfflinePlayer p) {
        try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM 'Bans' WHERE UUUID='" + p.getUniqueId() + "'");
                statement.executeUpdate();
                statement.close();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    //Giving player coins
    public static void giveCoins(OfflinePlayer p, String amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE CoinsAndPoints SET Coins= Coins +" + amount  + " WHERE UUID='" + p.getUniqueId() + "'");
            statement.executeUpdate();
            statement.close();
    } catch (Exception e) {
            e.printStackTrace();
    	}
    }
    
    //Give player points
    public static void givePoints(OfflinePlayer p, String amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE CoinsAndPoints SET Points= Points +" + amount  + " WHERE UUID='" + p.getUniqueId() + "'");
            statement.executeUpdate();
            statement.close();
    } catch (Exception e) {
            e.printStackTrace();
    	}
    }
    
    //Taking players Coins
    public static void takeCoins(OfflinePlayer p, String amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE CoinsAndPoints SET Coins= Coins -" + amount  + " WHERE UUID='" + p.getUniqueId() + "'");
            statement.executeUpdate();
            statement.close();
    } catch (Exception e) {
            e.printStackTrace();
    	}
    }
    
    //Taking players Points
    public static void takePoints(OfflinePlayer p, String amount) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE CoinsAndPoints SET Points= Points -" + amount  + " WHERE UUID='" + p.getUniqueId() + "'");
            statement.executeUpdate();
            statement.close();
    } catch (Exception e) {
            e.printStackTrace();
    	}
    }
    
    //Getting Points
    public static String getPoints(OfflinePlayer p) {
    	try {
            PreparedStatement statement = connection.prepareStatement("SELECT Points FROM CoinsAndPoints WHERE UUID='" + p.getUniqueId() + "'");
            ResultSet result = statement.executeQuery();
           
            if (result.next()) {
                    return result.getString("Points");
            } else {
                    return null;
            }
    } catch (Exception e) {
            e.printStackTrace();
            return null;
    	}
    }
    
    //Getting Coins
    public static String getCoins(OfflinePlayer p) {
    	try {
            PreparedStatement statement = connection.prepareStatement("SELECT Coins FROM CoinsAndPoints WHERE UUID='" + p.getUniqueId() + "'");
            ResultSet result = statement.executeQuery();
           
            if (result.next()) {
                    return result.getString("Coins");
            } else {
                    return null;
            }
    } catch (Exception e) {
            e.printStackTrace();
            return null;
    	}
    }
    
    //Has enough coins
    public static boolean hasEnoughCoins(OfflinePlayer p, String amount) {
    	int i = Integer.parseInt(amount);
    	i = i * Multiplier.coin(p);
    	if (Integer.parseInt(getCoins(p)) >= i)
    		return true;
		return false;
    }
    
    //Has enough Points
    public static boolean hasEnoughPoints(OfflinePlayer p, String amount) {
    	int i = Integer.parseInt(amount);
    	i = i * Multiplier.coin(p);
    	if (Integer.parseInt(getPoints(p)) >= i)
    		return true;
		return false;
    }
}