package com.lightcraftmc.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lightcraftmc.fusebox.util.strings.MessageType;
import com.lightcraftmc.fusebox.util.strings.StringManager;
import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.wardrobe.WardrobeManager;

public class UnlockAllArmor {
	
	static Main plugin;

	public static void setup() {
		plugin = Main.getInstance();
	}

	public static boolean onCommand(CommandSender sender, Command cmd,
			String label, String[] a) {
		if(sender instanceof Player){
			Player p = (Player)sender;
			if(!p.isOp()) return false;
			}
		if (cmd.getName().equalsIgnoreCase("unlockallarmor") && sender.isOp()) {
			if (a.length < 1) {
				sender.sendMessage(ChatColor.DARK_RED + "/unlockallarmor {playername}");
			}
				OfflinePlayer p2 = Bukkit.getOfflinePlayer(a[0]);
				
				//Leather Armor
				WardrobeManager.unlockArmor(p2, Material.LEATHER_HELMET);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_HELMET);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_LEGGINGS);
				WardrobeManager.unlockArmor(p2, Material.LEATHER_BOOTS);

				
				//Chain Armor
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_HELMET);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_HELMET);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_LEGGINGS);
				WardrobeManager.unlockArmor(p2, Material.CHAINMAIL_BOOTS);

				
				//Iron Armor
				WardrobeManager.unlockArmor(p2, Material.IRON_HELMET);
				WardrobeManager.unlockArmor(p2, Material.IRON_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.IRON_HELMET);
				WardrobeManager.unlockArmor(p2, Material.IRON_LEGGINGS);
				WardrobeManager.unlockArmor(p2, Material.IRON_BOOTS);

				
				//Gold Armor
				WardrobeManager.unlockArmor(p2, Material.GOLD_HELMET);
				WardrobeManager.unlockArmor(p2, Material.GOLD_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.GOLD_HELMET);
				WardrobeManager.unlockArmor(p2, Material.GOLD_LEGGINGS);
				WardrobeManager.unlockArmor(p2, Material.GOLD_BOOTS);

				
				//Diamond Armor
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_HELMET);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_CHESTPLATE);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_HELMET);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_LEGGINGS);
				WardrobeManager.unlockArmor(p2, Material.DIAMOND_BOOTS);

				
				if (p2.isOnline()) {
					p2.getPlayer().sendMessage(StringManager.getPrefix(MessageType.SUCCESS) + "Successfully unlocked all Wardrobe contents!");
				}
				
				else if (!p2.isOnline()) {
					return true;
				}
			}
		return false;
	}

}
