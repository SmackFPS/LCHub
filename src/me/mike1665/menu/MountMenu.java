package me.mike1665.menu;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MountMenu implements Listener{
	
	public static final String INV_NAME = "§8[§aP§8] §1Mount Menu";
	
	private static Main plugin;
	
	public static void initialize(Main plugin){
		MountMenu.plugin = plugin;
	}
	
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

	public static Inventory getMountShop(Player p)
	{
		Inventory mountmenu = Bukkit.createInventory(null, 54, INV_NAME);

		mountmenu.setItem(
				28,
				createItem(Material.SNOW_BALL, 1, (short) 0, "§aFrosty",
						(plugin.getConfig().getBoolean(p.getName() + ".FrostMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a5000 Coins")));
		mountmenu.setItem(
				30, 
				createItem(Material.REDSTONE_BLOCK, 1, (short) 14, "§2Dragon",
						(plugin.getConfig().getBoolean(p.getName() + ".DragonMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : /*"§bCosts §6§l: §a50000 Coins"*/ "§cThis Mount is in development.")));
		mountmenu.setItem(
				32,
				createItem(Material.HAY_BLOCK, 1, (short) 0, "§4Mule",
						(plugin.getConfig().getBoolean(p.getName() + ".MuleMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §d100 Pixels")));
		mountmenu.setItem(
				34,
				createItem(Material.BONE, 1, (short) 0, "§4Undead",
						(plugin.getConfig().getBoolean(p.getName() + ".UndeadMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		mountmenu.setItem(
				4,
				createItem(Material.CHEST, 1, (short) 0, "§c<- Go Back",
						""));
		/*mountmenu.setItem(
				0,
				createItem(Material.REDSTONE_BLOCK, 1, (short) 0, "§4Remove",
						"")); */
		return mountmenu;
	}

}
