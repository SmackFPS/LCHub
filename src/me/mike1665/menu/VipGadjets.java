package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VipGadjets implements Listener{
	
	public static ItemStack createItem(Material material, int amount, short shrt,
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

	public static Inventory vipgadmenu;
	public static void init() {
		vipgadmenu = Bukkit.createInventory(null, 45, "§7§nVip Gadgets Menu");
		
		vipgadmenu.setItem(
				36,
				createItem(Material.BEACON, 1, (short) 0, "§cGo Back",
						"§6Go back"));
		vipgadmenu.setItem(
				13,
				createItem(Material.SNOW_BALL, 1, (short) 0, "§bMeow Balls",
						"§bMeow Meow Meow"));
		vipgadmenu.setItem(
				12, 
				createItem(Material.GOLD_INGOT, 1, (short) 0, "§5Ender Doge",
						"§bMuch explosion"));
		vipgadmenu.setItem(
				14, 
				createItem(Material.ENDER_PEARL, 1, (short) 0, "§5Cat Works",
						"§bShoots Cats then FIREWORKS!"));
		vipgadmenu.setItem(
				23,
				createItem(Material.DIAMOND_BARDING, 1, (short) 0, "§dPaintball Gun",
						"§bShoot paintballs to color the Hub!"));
		vipgadmenu.setItem(
				22,
				createItem(Material.IRON_BARDING, 1, (short) 0, "§7Bat Blaster",
						"§7Shoot Bats at others!"));
	}

}
