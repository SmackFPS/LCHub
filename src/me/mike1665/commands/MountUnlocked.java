package me.mike1665.commands;

import java.util.UUID;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class MountUnlocked {

	static Main plugin;

	public static void setup() {
		plugin = Main.getInstance();
	}

	@SuppressWarnings("deprecation")
	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			}
		if (cmd.getName().equalsIgnoreCase("unlockmount")) {
			if (a.length < 2) {
        		sender.sendMessage(ChatColor.DARK_RED + "/unlockmount <mountname> <playername>");
        		return true;
        	}
			if (a.length == 2) {
				if (a[0].equalsIgnoreCase("angelmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".AngelMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Angel Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("darkmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".DarkMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Dark Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("ghostmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".GhostMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Ghost Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("nyanmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".NyanMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Nyan Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("poseidonmount")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					UUID uuid = Bukkit.getOfflinePlayer(a[1]).getUniqueId();
					plugin.getConfig().set(uuid + ".PoseidonMount", true);
					plugin.saveConfig();
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your new Poseidon Mount!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				} 
			}
		return false;
	}
}
