package net.lightcraft.treasurechests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.ammo.PaintballAmmoManager;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.wardrobe.WardrobeManager;
import net.lightcraftmc.fusebox.util.UtilEffect;
import net.lightcraftmc.fusebox.util.UtilEnt;
import net.lightcraftmc.fusebox.util.UtilFirework;
import net.lightcraftmc.fusebox.util.UtilMath;
import net.lightcraftmc.fusebox.util.UtilServer;
import net.lightcraftmc.fusebox.util.UtilityMath;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RandomManager
{
	
	Random random;
	
	private static Main plugin;
	  
	public static void initialize()
	{
		RandomManager.plugin = Main.getInstance();
	 	}

  public static void giveRandomBetweenRareAndNormalThing(Player p, Location l)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    double r = Math.random();
    if (r < 0.5D)
      giveRandomThing(p, l);
    else
      giveRandomRareThing(p, l);
  }

  public static void giveRandomRareThing(Player p, Location l)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    double r = Math.random();
    if (r < 0.2D)
      giveRandomCoins(p, l);
    /*else if (r < 0.4D) {
      if (!p.hasPermission("extra.*"))
        giveRandomExtra(p, l);
      else
        giveRandomThing(p, l);
    } */
    else if (r < 0.4D) {
      if (!p.hasPermission("mount.*"))
        giveRandomMount(p, l);
      else
        giveRandomThing(p, l);
    }
    else if (r < 0.6D) {
      giveRandomAmmo(p, l);
    }
    /*else if (!p.hasPermission("pets.*"))
      giveRandomPet(p, l);
    else
      giveRandomThing(p, l);*/
  }

  public static void giveRandomThing(Player p, Location l)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
	int ran = UtilMath.randInt(0, 3);	
	switch (ran) {
	case 1:
	      giveRandomAmmo(p, l);
	      break;
	case 2:
	      giveWardobeItem(p, l);
	      break;
	default:
	      giveRandomCoins(p, l);
	      break;
	}
  }

  public static void giveRandomCoins(Player p, Location l)
  {
    int am = UtilityMath.getRandomNumberBetween(10, 100);
    LcCoinsAPI.givePoints(p, am);
    UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DOUBLE_PLANT, (byte) 0, Integer.toString(am) + " §e§lCoins");
    UtilFirework.fireFirework(l.add(0.0D, 1.0D, 0.3D));
  }

  public static void giveRandomAmmo(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    int ran = UtilMath.randInt(0, 5);
	int amount = UtilityMath.getRandomNumberBetween(10, 100);

	UtilEffect.playFlameThing(l);
	
	switch (ran) {
	case 1: 
    	BatBlasterAmmoManager.giveBatAmmo(p, amount);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_BARDING, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&8BatBlaster Ammo"));
    	break;
	case 2:
        GadgetAmmo.addGadgetAmo(p, "QuakeGunAmmo", amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
        break;
	case 3:
        KittyCannonAmmoManager.giveCatAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.STICK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &e&lKittyCannon Ammo"));
        break;
	case 4:
        EnderDogeAmmoManager.giveEnderDogeAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.FIREWORK_CHARGE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&1EnderDoge Ammo"));
        break;
	default: 
        MeowAmmoManager.giveMeowAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.SNOW_BALL, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&6MeowBall Ammo"));
        break;
	}	
  }

  public static void giveRandomMount(Player p, Location l)
    throws FileNotFoundException, IOException, InvalidConfigurationException
  {
	  	UUID pn = p.getPlayer().getUniqueId();
	    boolean angel = plugin.getConfig().getBoolean(pn + ".AngelMount");
	    boolean ghost = plugin.getConfig().getBoolean(pn + ".GhostMount");
	    boolean dark = plugin.getConfig().getBoolean(pn + ".DarkMount");
	    boolean poseidon = plugin.getConfig().getBoolean(pn + ".PoseidonMount");
	    boolean nyan = plugin.getConfig().getBoolean(pn + ".NyanMount");
	    int ran = UtilMath.randInt(0 , 5);
	    
	    switch (ran) {
	    case 1:
	        if (!angel) {
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &e&lAngel Mount"));
	            for (Player cur : UtilServer.getPlayers()) {
	              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
	            }
	          
	          UtilEffect.playFlameBonus(l);
	          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.HAY_BLOCK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', "&e&lAngel Mount"));
	          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount angelmount " + p.getName());
	        } else {
	          giveRandomThing(p, l);
	        }
	        break;
	    case 2:
	        if (!dark) {
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &8&lDark Mount"));
	            for (Player cur : UtilServer.getPlayers()) {
	              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
	            }
	          
	          UtilEffect.playFlameBonus(l);
	          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.HAY_BLOCK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', "&8&lDark Mount"));
	          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount darkmount " + p.getName());
	        } else {
	          giveRandomThing(p, l);
	        }
	        break;
	    case 3:
	        if (!ghost) {
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &r&lGhost Mount"));
	            for (Player cur : UtilServer.getPlayers()) {
	              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
	            }
	          
	            UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.HAY_BLOCK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', "&r&lGhost Mount"));
	          UtilEffect.playFlameBonus(l);
	          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount ghostmount " + p.getName());
	        } else {
	          giveRandomThing(p, l);
	        }
	        break;
	    case 4:
	        if (!poseidon) {
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &3&lPoseidon Mount"));
	            for (Player cur : UtilServer.getPlayers()) {
	              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
	            }
	          
	          UtilEffect.playFlameBonus(l);
	          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.HAY_BLOCK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', "&3&lPoseidon Mount"));
	          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount poseidonmount " + p.getName());
	        } else {
	          giveRandomThing(p, l);
	        }
	        break;
	    case 5:
	          if (!nyan) {
	              Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &d&lNyan Mount"));
	              for (Player cur : UtilServer.getPlayers()) {
	                cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
	              }
	            
	            UtilEffect.playFlameBonus(l);
	            UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.HAY_BLOCK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', "&d&lNyan Mount"));
	            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount nyanmount " + p.getName());
	          } else {
	            giveRandomThing(p, l);
	          }
	          break;
	   default: 
	    	giveRandomThing(p, l);
	    	break;
	    }
  }
  
  public static void giveWardobeItem(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    int ran = UtilMath.randInt(0, 20);
	int am = UtilityMath.getRandomNumberBetween(1, 20);
	UtilEffect.playFlameThing(l);
    
	switch (ran) {
	
	//LEATHER ARMOR
	case 1:
    	WardrobeManager.unlockArmor(p, Material.LEATHER_HELMET);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.LEATHER_HELMET, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&6&lLeather Helmet"));
    	break;
	case 2:
    	WardrobeManager.unlockArmor(p, Material.LEATHER_CHESTPLATE);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.LEATHER_CHESTPLATE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&6&lLeather Chestplate"));
    	break;
	case 3:
    	WardrobeManager.unlockArmor(p, Material.LEATHER_LEGGINGS);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.LEATHER_LEGGINGS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&6&lLeather Leggings"));
        break;
	case 4:
    	WardrobeManager.unlockArmor(p, Material.LEATHER_BOOTS);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.LEATHER_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&6&lLeather Boots"));
        break;
        
    //Chain mail
	case 5:
    	WardrobeManager.unlockArmor(p, Material.CHAINMAIL_HELMET);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.CHAINMAIL_HELMET, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&8&lChainmail Helmet"));
    	break;
	case 6:
      	WardrobeManager.unlockArmor(p, Material.CHAINMAIL_CHESTPLATE);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.CHAINMAIL_CHESTPLATE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&8&lChainmail Chestplate"));
      	break;
	case 7:
      	WardrobeManager.unlockArmor(p, Material.CHAINMAIL_LEGGINGS);
       	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.CHAINMAIL_LEGGINGS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&8&lChainmail Leggings"));
       	break;
	case 8:
      	WardrobeManager.unlockArmor(p, Material.CHAINMAIL_BOOTS);
     	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.CHAINMAIL_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&8&lChainmail Boots"));
     	break;
        
   //GOLD ARMOR
	case 9:
    	WardrobeManager.unlockArmor(p, Material.GOLD_HELMET);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.GOLD_HELMET, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&e&lGold Helmet"));
    	break;
	case 10:
      	WardrobeManager.unlockArmor(p, Material.GOLD_CHESTPLATE);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.GOLD_CHESTPLATE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&e&lGold Chestplate"));
      	break;
	case 11:
      	WardrobeManager.unlockArmor(p, Material.GOLD_LEGGINGS);
       	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.GOLD_LEGGINGS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&e&lGold Leggings"));
       	break;
	case 12:
      	WardrobeManager.unlockArmor(p, Material.GOLD_BOOTS);
     	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.GOLD_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&e&lGold Boots"));
     	break;
   // Iron Armor
	case 13:
    	WardrobeManager.unlockArmor(p, Material.IRON_HELMET);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_HELMET, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&7&lIron Helmet"));
    	break;
	case 14: 
      	WardrobeManager.unlockArmor(p, Material.IRON_CHESTPLATE);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_CHESTPLATE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&7&lIron Chestplate"));
      	break;
	case 15: 
      	WardrobeManager.unlockArmor(p, Material.IRON_LEGGINGS);
       	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_LEGGINGS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&7&lIron Leggings"));
       	break;
	case 16:
      	WardrobeManager.unlockArmor(p, Material.IRON_BOOTS);
     	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&7&lIron Boots"));
     	break;
     	
    //Diamond Armor
	case 17: 
    	WardrobeManager.unlockArmor(p, Material.DIAMOND_HELMET);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HELMET, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&b&lDiamond Helmet"));
    	break;
	case 18:
      	WardrobeManager.unlockArmor(p, Material.DIAMOND_CHESTPLATE);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_CHESTPLATE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&b&lDiamond Chestplate"));
      	break;
	case 19:
      	WardrobeManager.unlockArmor(p, Material.DIAMOND_LEGGINGS);
       	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_LEGGINGS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&b&lDiamond Leggings"));
	case 20:
    	WardrobeManager.unlockArmor(p, Material.DIAMOND_BOOTS);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&b&lDiamond Boots"));
      	break;
	default:
	   giveRandomMount(p, l);
	   break;
	}
  }
}