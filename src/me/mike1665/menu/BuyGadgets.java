package me.mike1665.menu;

import java.util.ArrayList;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.ammo.PaintballAmmoManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.arrayprolc.item.ItemTools;

public class BuyGadgets implements Listener{
	
	public static String name = "§a§lGadgets";
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

	public static Inventory buygadmenu(Player p) {

	    Inventory buygadmenu;
	    
		buygadmenu = Bukkit.createInventory(null, 54, name);
		
		buygadmenu.setItem(
				4,
				createItem(Material.BED, 1, (short) 0, "§cGo Back",
						""));

		buygadmenu.setItem(19, ItemTools.setName(new ItemStack(Material.SNOW_BALL), "§5Meow Balls", 
				new String[] { 
				"",
				"§rWant a little bit of fun on", 
				"§rthe hub? Throw some of these" ,
				"§rand everyone will be amazed!",
				"",
				"§aClick to purchase",
				"§r50 for §a500 Coins",
				"",
				"§rYour ammo: " + MeowAmmoManager.balaceMeowAmmo(p)
				}));
		
		buygadmenu.setItem(20, ItemTools.setName(new ItemStack(Material.ENDER_PEARL), "§9Ender Doge", 
				new String[] { 
				"",
				"§rNeed a little WOOF WOOF on the", 
				"§rserver?" ,
				"",
				"§aClick to purchase",
				"§r50 for §a500 Coins",
				"",
				"§rYour ammo: " + EnderDogeAmmoManager.balaceEnderDogeAmmo(p)
				}));
		buygadmenu.setItem(21, ItemTools.setName(new ItemStack(Material.FIREWORK), "§5Fireworks", 
				new String[] { 
				"",
				"§rWant to style to the hub with", 
				"§rKABOOM? This is the right thing." ,
				"",
				"§aClick to purchase",
				"§r50 for §a800 Coins",
				"",
				"§rYour ammo: " + FireWorksAmmoManager.balaceFireWorkAmmo(p)
				}));
		buygadmenu.setItem(22, ItemTools.setName(new ItemStack(Material.EGG), "§aFun Creepers", 
				new String[] { 
				"",
				"§rWant to prank the players with", 
				"§rsome creepers?" ,
				"",
				"§aClick to purchase",
				"§r1 for §a100 Coins",
				"",
				"§rYour ammo: " + "§cNo ammo needed."
				}));
		buygadmenu.setItem(23, ItemTools.setName(new ItemStack(Material.IRON_BARDING), "§5Bat Blaster", 
				new String[] { 
				"",
				"§rWant to move players around with", 
				"§rstyle? This is the right thing!" ,
				"",
				"§aClick to purchase",
				"§r50 for §a800 Coins",
				"",
				"§rYour ammo: " + BatBlasterAmmoManager.balaceBatAmmo(p)
				}));
		buygadmenu.setItem(24, ItemTools.setName(new ItemStack(Material.DIAMOND_BARDING), "§dPaintball Gun", 
				new String[] { 
				"§4§lTHIS FEATURE IS IN DEVELOPMENT!",
				"",
				"§rWant to color the hub with", 
				"§rcool looking style?" ,
				"",
				"§aClick to purchase",
				"§r50 for §a800 Coins",
				"",
				"§rYour ammo: " + PaintballAmmoManager.balacePBAmmo(p)
				}));
		buygadmenu.setItem(28, ItemTools.setName(new ItemStack(Material.DOUBLE_PLANT), "§2Coin Bomb", 
				new String[] { 
				"",
				"§rWant to throw an awesome", 
				"§rparty with coins?" ,
				"",
				"§aClick to purchase",
				"§r1 for §a300 Coins",
				"",
				"§rYour ammo: " + "Not Needed."
				}));
		buygadmenu.setItem(29, ItemTools.setName(new ItemStack(Material.NETHER_STAR), "§dToken Bomb", 
				new String[] { 
				"",
				"§rWant to throw an awesome", 
				"§rparty with RARE tokens?" ,
				"",
				"§aClick to purchase",
				"§r1 for §d200 Tokens",
				"",
				"§rYour ammo: " + "Not Needed."
				}));
		buygadmenu.setItem(49, ItemTools.setName(new ItemStack(Material.CHEST), "§d§lTeasure chest", 
				new String[] { 
				"",
				"§rWant to earn something comepletley", 
				"§rRARE and free!" ,
				"",
				"§aClick to purchase",
				"§r1 for §d100 Tokens",
				"",
				"§rYour chests: " + "COMING SOON!"
				}));
		return buygadmenu;

	}

}