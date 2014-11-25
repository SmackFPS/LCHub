package com.arrayprolc.command;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArrayCommandHandler {

	static Main plugin;
	
	public static void setup(Main instance){
		plugin = instance;
	}
	
	public static boolean command(CommandSender sender, Command cmd, String label, String[] a){
		if(label.equalsIgnoreCase("g-reward")){
			if(sender instanceof Player) return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch(a[1].toLowerCase()){
			case "coin": {
				me.mike1665.coinapi.LcCoinsAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
			case "token":
				me.mike1665.coinapi.LcTokensAPI.givePoints(p, amount);
				me.mike1665.coinapi.ApiEvent.scoreboard(p);
				return true;
			}
		}
		return false;
	}
	
}
