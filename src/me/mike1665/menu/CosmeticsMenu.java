package me.mike1665.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CosmeticsMenu implements Listener{
	
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

	public static Inventory cosmenu;
	{
		cosmenu = Bukkit.createInventory(null, 54, "§8[§aP§8] §1Hub Menu");

		cosmenu.setItem(
				1,
				createItem(Material.DOUBLE_PLANT, 1, (short) 0, "§5Economy",
						"§1Choose a fun Gadget to show off to everyone!"));
		cosmenu.setItem(
				4,
				createItem(Material.BOOK, 1, (short) 0, "§a§bStats",
						"§1Choose a fun Gadget to show off to everyone!"));
		cosmenu.setItem(
				7,
				createItem(Material.DIAMOND_SWORD, 1, (short) 0, "§eRank",
						"§1Choose a fun Gadget to show off to everyone!"));
		cosmenu.setItem(
				22,
				createItem(Material.CHEST, 1, (short) 0, "§aGadgets",
						"§1Choose a fun Gadget to show off to everyone!"));
		cosmenu.setItem(
				37, 
				createItem(Material.IRON_BARDING, 1, (short) 0, "§cMounts",
						"§3Choose an eye-pooping mount!"));
		cosmenu.setItem(
				40,
				createItem(Material.ENDER_PEARL, 1, (short) 0, "§8Hub Purchaseables",
						"§bPurchase some fun Gadgets!"));
		cosmenu.setItem(
				43,
				createItem(Material.EGG, 1, (short) 0, "§6Pets",
						"§6Want a friend?"));
	}

}
