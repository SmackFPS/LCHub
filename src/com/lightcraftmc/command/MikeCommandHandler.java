package com.lightcraftmc.command;

import me.mike1665.coinapi.ApiEvent;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.menu.BuyGadgets;
import me.mike1665.menu.MountMenu;
import me.mike1665.menu.WardrobeMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.arrayprolc.event.ColouredWardrobe;
import com.arrayprolc.event.WardrobeType;
import com.lightcraftmc.hub.main.Main;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class MikeCommandHandler {

	public static boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("gadgets")) {
			player.openInventory(me.mike1665.menu.GadjetsMenu.gadmenu);
		}
		if (cmd.getName().equalsIgnoreCase("cosmenu")) {
			player.openInventory(me.mike1665.menu.CosmeticsMenu.cosmenu((Player) sender));
		}
		if (cmd.getName().equalsIgnoreCase("mountmenu")) {
			player.openInventory(MountMenu.getMountShop(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("gadmenu")) {
			player.openInventory(BuyGadgets.buygadmenu(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("wardrobe")) {
			player.openInventory(WardrobeMenu.getWardrobeShop(player));
		}
		
		if (cmd.getName().equalsIgnoreCase("colorhelm")) {
			try{
			ColouredWardrobe.openColorWardrobe(player, WardrobeType.CHESTPLATE);
			}
			catch(Exception ex){
				
			}
		}

		if (cmd.getName().equalsIgnoreCase("parkour")) {
			player.sendMessage(ChatColor.RED + "          :" + ChatColor.RED + " Parkour Version 1" + ChatColor.RED + " :");
			player.sendMessage(ChatColor.RED + "          :" + ChatColor.GREEN + " Developed by @SwaggyYolo " + ChatColor.RED + ":");
			player.sendMessage(Main.getInstance().tag + ChatColor.YELLOW + " /setparkour1 - Sets the spawn for Parkour 1. ");
			player.sendMessage(Main.getInstance().tag + ChatColor.YELLOW + " /parkour1 - Teleport to the set point. ");
			player.sendMessage(Main.getInstance().tag + ChatColor.YELLOW + " /cp1 - Sets Checkpoint for Course 1. ");

		}
		if (cmd.getName().equalsIgnoreCase("setparkour1") && player.isOp()) {
			Main.getInstance().getConfig().set("parkour1.world", player.getLocation().getWorld().getName());
			Main.getInstance().getConfig().set("parkour1.x", player.getLocation().getX());
			Main.getInstance().getConfig().set("parkour1.y", player.getLocation().getY());
			Main.getInstance().getConfig().set("parkour1.z", player.getLocation().getZ());
			Main.getInstance().getConfig().set("parkour1.direction", player.getLocation().getDirection());
			Main.getInstance().getConfig().set("parkour1.yaw", player.getLocation().getYaw());
			Main.getInstance().getConfig().set("parkour1.pitch", player.getLocation().getPitch());

			Main.getInstance().saveConfig();
			player.sendMessage(Main.getInstance().tag + ChatColor.GREEN + "Parkour 1 spawn set!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("setmountspawn") && player.isOp()) {
			Main.getInstance().getConfig().set("mount.world", player.getLocation().getWorld().getName());
			Main.getInstance().getConfig().set("mount.x", player.getLocation().getX());
			Main.getInstance().getConfig().set("mount.y", player.getLocation().getY());
			Main.getInstance().getConfig().set("mount.z", player.getLocation().getZ());

			Main.getInstance().saveConfig();
			player.sendMessage(ChatColor.GREEN + "Mount spawn set!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("cp1") && player.isOp()) {
			Main.getInstance().getConfig().set("cp1.world", player.getLocation().getWorld().getName());
			Main.getInstance().getConfig().set("cp1.x", player.getLocation().getX());
			Main.getInstance().getConfig().set("cp1.y", player.getLocation().getY());
			Main.getInstance().getConfig().set("cp1.z", player.getLocation().getZ());
			Main.getInstance().getConfig().set("cp1.direction", player.getLocation().getDirection());
			Main.getInstance().getConfig().set("cp1.yaw", player.getLocation().getYaw());
			Main.getInstance().getConfig().set("cp1.pitch", player.getLocation().getPitch());

			Main.getInstance().saveConfig();
			player.sendMessage(StringManager.getMessage("Checkpoint set for parkour 1!", MessageType.PARKOUR));
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("parkour1")) {
			if (Main.getInstance().getConfig().getConfigurationSection("parkour1") == null) {
				player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Parkour spawn not yet set!");
				return true;
			}
			org.bukkit.World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("parkour1.world"));
			double yaw = Main.getInstance().getConfig().getDouble("parkour1.yaw");
			double pitch = Main.getInstance().getConfig().getDouble("parkour1.pitch");
			double x = Main.getInstance().getConfig().getDouble("parkour1.x");
			double y = Main.getInstance().getConfig().getDouble("parkour1.y");
			double z = Main.getInstance().getConfig().getDouble("parkour1.z");
			Location parkour1 = new Location(w, x, y, z);
			parkour1.setPitch((float) pitch);
			parkour1.setYaw((float) yaw);
			player.teleport(parkour1);
			player.sendMessage(StringManager.getPrefix(MessageType.PARKOUR) + "Teleported to " + ChatColor.AQUA + "Parkour1");
		}

		if (cmd.getName().equalsIgnoreCase("addstaff") && player.isOp()) {
			if (player.isOp()) {
				Main.getInstance().getConfig().set(player.getUniqueId() + ".Administrator", true);
				Main.getInstance().saveFile();
				player.sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + ChatColor.BLUE + "Added " + ChatColor.YELLOW + player.getUniqueId().toString() + ChatColor.BLUE + " to socreboard staff!");
			}
		}

		if (cmd.getName().equalsIgnoreCase("givetokens") && player.isOp()) {
			if (a.length >= 1) {
				if (a.length == 1) {
					int tempValue;
					try {
						tempValue = Integer.parseInt(a[0]);
					} catch (NumberFormatException e) {
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcTokensAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[0] + ChatColor.AQUA + " Tokens recieved!");

				} else if (a.length == 2) {
					LcTokensAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = Main.getInstance().getServer().getPlayer(a[0]);
					if (tempPlayer != null) {
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA + " Tokens recieved!");
					}

				}

			} else {
				player.sendMessage(ChatColor.RED + "Something Failed!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("givecoins") && player.isOp()) {
			if (a.length >= 1) {
				if (a.length == 1) {
					int tempValue;
					try {
						tempValue = Integer.parseInt(a[0]);
					} catch (NumberFormatException e) {
						player.sendMessage(StringManager.getPrefix(MessageType.ERROR) + "Value must be a number!");
						return true;
					}
					LcCoinsAPI.givePoints(player, tempValue);
					ApiEvent.updatescore(player);
					player.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + a[0] + ChatColor.AQUA + " Coins recieved!");

				} else if (a.length == 2) {
					LcCoinsAPI.givePoints(Bukkit.getOfflinePlayer(a[0]), Integer.parseInt(a[1]));
					Player tempPlayer = Main.getInstance().getServer().getPlayer(a[0]);
					if (tempPlayer != null) {
						ApiEvent.updatescore(tempPlayer);
						tempPlayer.sendMessage(StringManager.getPrefix(MessageType.TRANSACTION) + "" + a[1] + ChatColor.AQUA + " Coins recieved!");
					}

				}

			}
		}

		return false;
	}

	
}
