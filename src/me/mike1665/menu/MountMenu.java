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
				20,
				createItem(Material.INK_SACK, 1, (short) 8, "§b§lAngel Mount",
						(plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + ".AngelMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		mountmenu.setItem(
				21, 
				createItem(Material.INK_SACK, 1, (short) 8, "§4§lGhost Mount",
						(plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + ".GhostMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		mountmenu.setItem(
				22,
				createItem(Material.INK_SACK, 1, (short) 8, "§f§lDark Mount",
						(plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + ".DarkMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		mountmenu.setItem(
				23,
				createItem(Material.INK_SACK, 1, (short) 8, "§9§lPoseidon Mount",
						(plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + ".PoseidonMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		mountmenu.setItem(
				24,
				createItem(Material.INK_SACK, 1, (short) 8, "§5§lNyan Mount",
						(plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + ".NyanMount") ? ChatColor.GREEN + "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a10000 Coins")));
		
		mountmenu.setItem(
				4,
				createItem(Material.CHEST, 1, (short) 0, "§c<- Go Back",
						""));
		mountmenu.setItem(
				40,
				createItem(Material.REDSTONE_BLOCK, 1, (short) 0, "§4Remove Mount",
						"")); 
		return mountmenu;
	}

}
