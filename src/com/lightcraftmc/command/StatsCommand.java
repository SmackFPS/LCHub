package com.lightcraftmc.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lightcraftmc.coinapi.LcCoinsAPI;
import com.lightcraftmc.coinapi.LcTokensAPI;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.rank.RankManager;

public class StatsCommand {

	static Main plugin;

	public static void setup() {
		plugin = Main.getInstance();
	}

	public static boolean command(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("stats")) {
			int a1 = LcTokensAPI.balancePoints(player);
			int b1 = LcCoinsAPI.balancePoints(player);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "------------------------------------------");
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Name: " + ChatColor.LIGHT_PURPLE + player.getName().toString());
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Rank: " + ChatColor.LIGHT_PURPLE + RankManager.getRank(player).toString());
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Tokens: " + ChatColor.LIGHT_PURPLE + a1);
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Coins: " + ChatColor.LIGHT_PURPLE + b1);
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "------------------------------------------");

		}
		return false;
	}

}
