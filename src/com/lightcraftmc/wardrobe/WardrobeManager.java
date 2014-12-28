package com.lightcraftmc.wardrobe;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lightcraftmc.hub.main.Main;

public class WardrobeManager implements Listener{
	
	private static Main plugin;
	
	public static void initialize(){
		WardrobeManager.plugin = Main.getInstance();
	}
	
	public static boolean hasUnlockedArmor(OfflinePlayer p, Material m) {
		return plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + "." + m.toString());
	}
	
	public static void unlockArmor(OfflinePlayer p, Material m) {
		plugin.getConfig().set(p.getPlayer().getUniqueId() + "." + m.toString(), true);
		plugin.saveConfig();
	}
	
	public static void setHelmet(Player p, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> itemMetaLore = new ArrayList<String>();
		itemMetaLore.add("This Helmet makes you look cool!");
		i2.setLore(itemMetaLore);
		i.setItemMeta(i2);
		p.getInventory().setHelmet(i);
	}
	
	public static void setChestPlate(Player p, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> itemMetaLore = new ArrayList<String>();
		itemMetaLore.add("This Chestplate makes you look sexy!");
		i2.setLore(itemMetaLore);
		i.setItemMeta(i2);
		p.getInventory().setChestplate(i);
	}
	
	public static void setLeggings(Player p, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> itemMetaLore = new ArrayList<String>();
		itemMetaLore.add("These Pants makes you look swagged out!");
		i2.setLore(itemMetaLore);
		i.setItemMeta(i2);
		p.getInventory().setLeggings(i);
	}
	
	public static void setBoots(Player p, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> itemMetaLore = new ArrayList<String>();
		itemMetaLore.add("These Boots are more expensive than Jordans!");
		i2.setLore(itemMetaLore);
		i.setItemMeta(i2);
		p.getInventory().setBoots(i);
	}
}
