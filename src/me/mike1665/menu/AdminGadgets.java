package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminGadgets implements Listener{
	
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

	public static Inventory admingadmenu;
	public static void init()  {
		admingadmenu = Bukkit.createInventory(null, 9, "§7§nAdmin Gadgets Menu");

		admingadmenu.setItem(
				0,
				createItem(Material.BEACON, 1, (short) 0, "§cGo Back",
						"§6Go back"));
		admingadmenu.setItem(
				3,
				createItem(Material.SKULL_ITEM, 1, (short) 0, "§aSpawn A Twerking Zombie",
						"§6Spawn a Baby Zombie!"));
		admingadmenu.setItem(
				4,
				createItem(Material.WOOL, 1, (short) 0, "§cSpawn A Rainbow Sheep",
						"§6Spawn a Sheep"));
		admingadmenu.setItem(
				5,
				createItem(Material.GOLD_CHESTPLATE, 1, (short) 0, "§1Wear Rainbow Armor",
						"§6R.A.I.N.B.O.W Armor"));
	}

}
