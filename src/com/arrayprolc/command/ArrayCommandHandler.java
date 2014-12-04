package com.arrayprolc.command;

import java.util.UUID;

import me.jrl1004.lightcraft.utils.ProxiedEconomy;
import me.jrl1004.lightcraft.utils.ProxiedEconomy.Currency;
import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import com.arrayprolc.rank.RankManager;
import com.arrayprolc.rank.ServerRank;
import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class ArrayCommandHandler {

	static Main plugin;

	public static void setup(Main instance) {
		plugin = instance;
	}

	public static boolean command(CommandSender sender, Command cmd, String label, String[] a) {
		if (label.equalsIgnoreCase("g-reward")) {
			if (sender instanceof Player)
				return false;
			Player p = Bukkit.getPlayer(a[0]);
			int amount = Integer.parseInt(a[2]);
			switch (a[1].toLowerCase()) {
			
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
		if (label.equalsIgnoreCase("sheeplol") && ((Player) sender).isOp()) {
			try {
				((Player) sender).getPassenger().eject();
				((Player) sender).eject();
			//	((Player) sender).spigot().
			} catch (Exception ex) {
			}
			for (Entity e : ((Player) sender).getNearbyEntities(5, 5, 5)) {
				if (e instanceof Sheep) {
					((Player) sender).setPassenger(e);
					return false;
				}
			}
		}
		if (label.equalsIgnoreCase("rank")) {
			if (sender instanceof Player) {
				if (!((Player) sender).isOp())
					return false;
			}
			Player p = Bukkit.getPlayer(a[0]);
			ServerRank r = RankManager.getRankFromString(a[1]);
			RankManager.setRank(p, r);

		}
		if (label.equalsIgnoreCase("test")) {
			Player player = (Player) sender;
			/* 
			 * ProxiedParty.getInstance().requestPartyHost(player.getName());
			 * System.out.println("Host");
			 * ProxiedParty.getInstance().requestPartySize(player.getName());
			 * System.out.println("Size");
			 */
			//ProxiedParty.getInstance().requestPartyList(player);
			ProxiedEconomy.getInstance().increasePlayerBalance(player, Currency.Coins, 10l);
			// System.out.println("List");

		}
		return false;
	}

	static boolean isOnline(Player p) {
		for (Player p2 : Bukkit.getOnlinePlayers()) {
			if (p2.getName().equalsIgnoreCase(p.getName())) {
				return true;
			}
		}
		return false;
	}

}
