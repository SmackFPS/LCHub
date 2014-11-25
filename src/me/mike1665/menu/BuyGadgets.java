package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyGadgets implements Listener{
	
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

	public static Inventory buygadmenu;
	{
		buygadmenu = Bukkit.createInventory(null, 45, "§7§nHub Perks!");
		
		buygadmenu.setItem(
				36,
				createItem(Material.NETHER_STAR, 1, (short) 0, "§cGo Back",
						""));
		buygadmenu.setItem(
				12,
				createItem(Material.SNOW_BALL, 1, (short) 0, "§5Meow Balls",
						"§b (16) Costs §6§l: §a60 Coins"));
		buygadmenu.setItem(
				13, 
				createItem(Material.ENDER_PEARL, 1, (short) 0, "§9Ender Doge",
						"§b (16) Costs §6§l: §a50 Coins"));
		buygadmenu.setItem(
				14, 
				createItem(Material.FIREWORK, 1, (short) 0, "§5Fireworks",
						"§b (64) Costs §6§l: §a100 Coins"));
		buygadmenu.setItem(
				21,
				createItem(Material.TRIPWIRE_HOOK, 1, (short) 0, "§1Entity Hook",
						"§b (10) Costs §6§l: §d50 Tokens"));
		buygadmenu.setItem(
				22,
				createItem(Material.TNT, 1, (short) 0, "§cTNT",
						"§b (10) Costs §6§l: §d30 Tokens"));
		buygadmenu.setItem(
				23,
				createItem(Material.DOUBLE_PLANT, 1, (short) 0, "§2Coin Bomb",
						"§b (1) Costs §6§l: §a5000 Coins"));
		buygadmenu.setItem(
				31,
				createItem(Material.IRON_BARDING, 1, (short) 0, "§dToken Bomb",
						"§b (1) Costs §6§l: §d100 Tokens"));

	}

}