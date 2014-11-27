package me.mike1665.menu;

import java.util.ArrayList;

import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import net.md_5.bungee.api.ChatColor;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.arrayprolc.item.ItemTools;
import com.arrayprolc.rank.RankManager;

public class CosmeticsMenu implements Listener{
	
	public static String name = "§8[§aP§8] §1Hub Menu";
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

	public static Inventory cosmenu(Player p)
	{
		Inventory cosmenu;
		int a = LcTokensAPI.balancePoints(p);
		int b = LcCoinsAPI.balancePoints(p);
		
		cosmenu = Bukkit.createInventory(null, 54, name);

		cosmenu.setItem(1, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "§5Economy", 
			new String[] { 
			"",
			"Your economy status:", 
			"" ,
			"§d§lTokens - " + a,
			"",
			"§a§lCoins - " + b,
			"",
			"§cPurchae more on the website!"
			}));
		
		
		cosmenu.setItem(4, ItemTools.setName(new ItemStack(Material.BOOK), "§a§bStats", 
				new String[] {
			"",
			"Your server status:",
			"",
			ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Name: " + ChatColor.LIGHT_PURPLE + p.getName().toString(),
			"",
			ChatColor.YELLOW + "" + ChatColor.BOLD + ">>  " + ChatColor.GRAY + "Time played: " + ChatColor.LIGHT_PURPLE + "Coming soon....",
			""
			}));
		

		cosmenu.setItem(7, ItemTools.setName(new ItemStack(Material.DIAMOND_SWORD), "§eRank", 
				new String[] { 
			"",
			"§eYou current rank:",
			"",
			"§7§lRank - " + RankManager.getColor(RankManager.getRank(p)) + WordUtils.capitalize(RankManager.getRank(p).toString().toLowerCase()),
			"",
			"§cPurchase a rank on the website!"
			}));
		
		
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
		return cosmenu;


		
	}

}
