package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerGadjets implements Listener{
	
	public ItemStack createItem(Material material, int amount, short shrt,
			String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);

		item.setItemMeta(meta);
		return item;
	}

	public static Inventory plrgadmenu;
	{
		plrgadmenu = Bukkit.createInventory(null, 9, "§7§nPlayer Gadgets Menu");

		
		plrgadmenu.setItem(
				0,
				createItem(Material.BEACON, 1, (short) 0, "§cGo Back",
						"§6Go back"));
		plrgadmenu.setItem(
				3,
				createItem(Material.ENDER_PEARL, 1, (short) 0, "§aEnder Ride",
						"§bShoot n' Ride EnderPearls!"));
		
		plrgadmenu.setItem(
				5,
				createItem(Material.MELON_BLOCK, 1, (short) 0, "§cMelon Block",
						"§bRight click for a little surpise."));
	}

}
