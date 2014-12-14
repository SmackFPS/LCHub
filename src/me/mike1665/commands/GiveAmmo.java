package me.mike1665.commands;

import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.FunCreeperAmmoManager;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class GiveAmmo {

	static Main plugin;

	public static void setup(Main instance) {
		plugin = instance;
	}

	@SuppressWarnings("deprecation")
	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			}
		if (cmd.getName().equalsIgnoreCase("giveammo")) {
			if (a.length < 3) {
        		sender.sendMessage(ChatColor.DARK_RED + "/giveammoe <ammoname> <playername> <amount>");
        		return true;
        	}
			
			if (a.length == 3) {
				if (a[0].equalsIgnoreCase("batblaster")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					BatBlasterAmmoManager.giveBatAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " Bat Blaster ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("enderdoge")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					EnderDogeAmmoManager.giveEnderDogeAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " Ender Doge ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("fireworks")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					FireWorksAmmoManager.giveFireWorkAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " Firework ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("funcreepers")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					FunCreeperAmmoManager.giveCreeperAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " Fun Creeper ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("kittycannon")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					KittyCannonAmmoManager.giveCatAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " Kitty Cannon ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				if (a[0].equalsIgnoreCase("meowball")) {
					OfflinePlayer recieved = Bukkit.getOfflinePlayer(a[1]);
					OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[1]);
					int amount = Integer.parseInt(a[2]);
					MeowAmmoManager.giveMeowAmmo(p2, amount);
					if (recieved.isOnline()) {
						Player onlineplyr = recieved.getPlayer();
						onlineplyr.sendMessage(StringManager.getPrefix(MessageType.SUCCESS)
								+ "Enjoy your " + a[2] + " MeowBall ammo!");
						} else if (!recieved.isOnline()) {
							return true;
						}
					}
				}
			}
		return false;
	}
}