package me.mike1665.menu;

import java.util.ArrayList;

import me.mike1665.Main.Main;
import me.mike1665.wardrobe.WardrobeManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WardrobeMenu
  implements Listener
{
  private static Main plugin;
  
  public static void initialize()
  {
    WardrobeMenu.plugin = Main.getInstance().getInstance();
  }
  
  public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore)
  {
    ItemStack item = new ItemStack(material, amount, shrt);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(displayname);
    ArrayList<String> Lore = new ArrayList<String>();
    Lore.add(lore);
    meta.setLore(Lore);
    
    item.setItemMeta(meta);
    return item;
  }
  
  public static Inventory getWardrobeShop(Player p)
  {
	  
    Inventory wardrobeMenu = Bukkit.createInventory(null, 54, "Wardrobe Menu");
    
    //LEATHER ARMOR
    wardrobeMenu.setItem(
      11, 
      createItem(Material.LEATHER_HELMET, 1, (short) 0, "§b§lLeather Helmet", 
      WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_HELMET) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
      wardrobeMenu.setItem(
      20, 
      createItem(Material.LEATHER_CHESTPLATE, 1, (short) 0, "§b§lLeather Chestplate", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_CHESTPLATE) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
      29, 
      createItem(Material.LEATHER_LEGGINGS, 1, (short) 0, "§b§lLeather Leggings", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_LEGGINGS) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
      38, 
      createItem(Material.LEATHER_BOOTS, 1, (short) 0, "§b§lLeather Boots", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.LEATHER_BOOTS) ? ChatColor.GREEN + ""+ ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));  
    
    //CHAINMAIL ARMOR
    wardrobeMenu.setItem(
      12, 
      createItem(Material.CHAINMAIL_HELMET, 1, (short) 0, "§b§lChainmail Helmet", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_HELMET) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
      21, 
      createItem(Material.CHAINMAIL_CHESTPLATE, 1, (short) 0, "§b§lChainmail Chestplate", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_CHESTPLATE) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
      30, 
      createItem(Material.CHAINMAIL_LEGGINGS, 1, (short) 0, "§b§lChainmail Leggings", 
    	      WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_LEGGINGS) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
      39, 
      createItem(Material.CHAINMAIL_BOOTS, 1, (short) 0, "§b§lChainmain Boots", 
    		 WardrobeManager.hasUnlockedArmor(p, Material.CHAINMAIL_BOOTS) ? ChatColor.GREEN + ""+ ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));  
    
    //IRON ARMOR
    wardrobeMenu.setItem(
       13, 
       createItem(Material.IRON_HELMET, 1, (short) 0, "§b§lIron Helmet", 
    	      WardrobeManager.hasUnlockedArmor(p, Material.IRON_HELMET) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
     wardrobeMenu.setItem(
       22, 
      createItem(Material.IRON_CHESTPLATE, 1, (short) 0, "§b§lIron Chestplate", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.IRON_CHESTPLATE) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       31, 
      createItem(Material.IRON_LEGGINGS, 1, (short) 0, "§b§lIron Leggings", 
    		  WardrobeManager.hasUnlockedArmor(p, Material.IRON_LEGGINGS) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       40, 
      createItem(Material.IRON_BOOTS, 1, (short) 0, "§b§lIron Boots", 
    	      WardrobeManager.hasUnlockedArmor(p, Material.IRON_BOOTS) ? ChatColor.GREEN + ""+ ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));  
   
   //GOLD ARMOR
    wardrobeMenu.setItem(
       14, 
       createItem(Material.GOLD_HELMET, 1, (short) 0, "§b§lGold Helmet", 
    		   WardrobeManager.hasUnlockedArmor(p, Material.GOLD_HELMET) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       23, 
       createItem(Material.GOLD_CHESTPLATE, 1, (short) 0, "§b§lGold Chestplate", 
    		   WardrobeManager.hasUnlockedArmor(p, Material.GOLD_CHESTPLATE) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       32, 
       createItem(Material.GOLD_LEGGINGS, 1, (short) 0, "§b§lGold Leggings", 
    		   WardrobeManager.hasUnlockedArmor(p, Material.GOLD_LEGGINGS) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       41, 
       createItem(Material.GOLD_BOOTS, 1, (short) 0, "§b§lGold Boots", 
    		   WardrobeManager.hasUnlockedArmor(p, Material.GOLD_BOOTS) ? ChatColor.GREEN + ""+ ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));  
    	    	    
   //DIAMOND ARMOR
    wardrobeMenu.setItem(
       15, 
       createItem(Material.DIAMOND_HELMET, 1, (short) 0, "§b§lDiamond Helmet", 
    		   WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_HELMET) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       24, 
       createItem(Material.DIAMOND_CHESTPLATE, 1, (short) 0, "§b§lDiamond Chestplate", 
    	    	WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_CHESTPLATE) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       33, 
       createItem(Material.DIAMOND_LEGGINGS, 1, (short) 0, "§b§lDiamond Leggings", 
    	    	WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_LEGGINGS) ? ChatColor.GREEN+ "" + ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));
    wardrobeMenu.setItem(
       42, 
      createItem(Material.DIAMOND_BOOTS, 1, (short) 0, "§b§lDiamond Boots", 
    	    	WardrobeManager.hasUnlockedArmor(p, Material.DIAMOND_BOOTS) ? ChatColor.GREEN + ""+ ChatColor.BOLD + "Unlocked" : "§bCosts §6§l: §a30 Tokens"));  
    
    //Remove Armor
    wardrobeMenu.setItem(
    	16, 
    	createItem(Material.GLASS, 1, (short) 0, "§a§lRemove Helmet", 
    	    	  "§aRemove Your Helmet"));
    wardrobeMenu.setItem(
        25, 
        createItem(Material.GLASS, 1, (short) 0, "§a§lRemove Chestplate", 
        	      "§aRemove Your Chestplate"));
    wardrobeMenu.setItem(
   	    34, 
        createItem(Material.GLASS, 1, (short) 0, "§a§lRemove Leggings", 
    	    	 "§aRemove Your Leggings"));
    wardrobeMenu.setItem(
        43, 
    	createItem(Material.GLASS, 1, (short) 0, "§a§lRemove Boots", 
    			  "§aRemove Your Boots"));
    wardrobeMenu.setItem(
        52, 
       	createItem(Material.REDSTONE_BLOCK, 1, (short) 0, "§a§lRemove All", 
       			  "§aRemove Your Armor"));
    
    //Set The Color
    wardrobeMenu.setItem(
        10, 
       createItem(Material.INK_SACK, 1, (short) 15, "§a§lHelmet Colour", 
        	       "§cMust Have Leather Armor"));
    wardrobeMenu.setItem(
        19, 
        createItem(Material.INK_SACK, 1, (short) 15, "§a§lChestplate Colour", 
            	    "§cMust Have Leather Armor"));
    wardrobeMenu.setItem(
        28, 
        createItem(Material.INK_SACK, 1, (short) 15, "§a§lLeggings Colour", 
        	        "§cMust Have Leather Armor"));
    wardrobeMenu.setItem(
        37, 
        createItem(Material.INK_SACK, 1, (short) 15, "§a§lBoots Colour", 
        			 "§cMust Have Leather Armor")); 
    
    //Pumpkin Head
    wardrobeMenu.setItem(
        4, 
        createItem(Material.PUMPKIN, 1, (short) 0, "§6§lPumpkin Head", 
                      "§cSwagg Style!"));     
    
    	    
    //OTHERS
    wardrobeMenu.setItem(
      49, 
      createItem(Material.ARROW, 1, (short)0, "§c<- Go Back", 
      ""));
    return wardrobeMenu;
  }
}