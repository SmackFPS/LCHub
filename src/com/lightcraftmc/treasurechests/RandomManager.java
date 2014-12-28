package com.lightcraftmc.treasurechests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.FireWorksAmmoManager;
import me.mike1665.ammo.FunCreeperAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.coinapi.LcTokensAPI;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.particles18.ParticleLib18.ParticleType;
import me.mike1665.wardrobe.WardrobeManager;
import net.lightcraft.particles.UnlockedParticle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import com.lightcraftmc.fusebox.util.MathUtils;
import com.lightcraftmc.fusebox.util.UtilEffect;
import com.lightcraftmc.fusebox.util.UtilEnt;
import com.lightcraftmc.fusebox.util.UtilMath;
import com.lightcraftmc.fusebox.util.UtilServer;
import com.lightcraftmc.fusebox.util.UtilVector;
import com.lightcraftmc.fusebox.util.UtilityMath;
import com.lightcraftmc.hub.main.Main;

public class RandomManager
{
	
	Random random;
	
	private static Main plugin;
	  
	public static void initialize()
	{
		RandomManager.plugin = Main.getInstance();
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
	case 3:
	      giveBombs(p, l);
	      break;
	default:
	      giveRandomCoins(p, l);
	      break;
	}
  }

  public static void giveRandomCoins(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
	  
	  int ran = UtilMath.randInt(0, 2);
	  int am = UtilityMath.getRandomNumberBetween(10, 100);


	  switch (ran) {
	  case 1:
		    LcCoinsAPI.givePoints(p, am);
		    UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DOUBLE_PLANT, (byte) 0, Integer.toString(am) + " §e§lCoins");
		    break;
	  case 2:
		    LcTokensAPI.givePoints(p, am);
		    UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.NETHER_STAR, (byte) 0, Integer.toString(am) + " §d§lTokens");
		    break;
	  default:
		  giveRandomParticle(p, l);
		  break;
	  }
  }

  public static void giveRandomParticle(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    int ran = UtilMath.randInt(0, 14);

	UtilEffect.playFlameThing(l);
	
	switch (ran) {
	case 1: 
    	UnlockedParticle.unlockParticle(p, ParticleType.HEART);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.RED_ROSE, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &c&lHeart Particles"));
    	break;
	case 2:
    	UnlockedParticle.unlockParticle(p, ParticleType.NOTE);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.NOTE_BLOCK, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &a&lNote Particles"));
        break;
	case 3:
    	UnlockedParticle.unlockParticle(p, ParticleType.FLAME);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.BLAZE_POWDER, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &6&lFlame Particles"));
        break;
	case 4:
    	UnlockedParticle.unlockParticle(p, ParticleType.DRIP_WATER);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.WATER_BUCKET, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &9&lWater Particles"));
        break;
	case 5:
    	UnlockedParticle.unlockParticle(p, ParticleType.DRIP_LAVA);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.LAVA_BUCKET, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &e&lLava Particles"));
        break;
	case 6:
    	UnlockedParticle.unlockParticle(p, ParticleType.ENCHANTMENT_TABLE);
		UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.ENCHANTED_BOOK, (byte) 50, ChatColor.translateAlternateColorCodes('&', " &lEnchantment Particles"));
        break;
	case 7: 
    	UnlockedParticle.unlockParticle(p, ParticleType.SPELL);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.STRING, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &7&lSwirly Particles"));
        break;
	case 8: 
    	UnlockedParticle.unlockParticle(p, ParticleType.REDSTONE);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.REDSTONE, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &4&lRedstone Particles"));
    	break;
	case 9:
    	UnlockedParticle.unlockParticle(p, ParticleType.SPELL_WITCH);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.GLOWSTONE_DUST, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &5&lWitch Particles"));
        break;
	case 10:
    	UnlockedParticle.unlockParticle(p, ParticleType.CRIT_MAGIC);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.NETHER_STAR, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &3&lCritial Particles"));
        break;
	case 11:
    	UnlockedParticle.unlockParticle(p, ParticleType.SPELL_MOB);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.FIREWORK_CHARGE, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &nMob Spell Particles"));
        break;
	case 12:
    	UnlockedParticle.unlockParticle(p, ParticleType.SMOKE_NORMAL);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.COAL, (byte) 0, ChatColor.translateAlternateColorCodes('&', " &8&lSmoke Particles"));
        break;
	case 13:
    	UnlockedParticle.unlockParticle(p, ParticleType.VILLAGER_HAPPY);
		UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.EMERALD, (byte) 50, ChatColor.translateAlternateColorCodes('&', "&a&lHappy Villager Particles"));
        break;
	case 14: 
    	UnlockedParticle.unlockParticle(p, ParticleType.VILLAGER_ANGRY);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.FIREWORK_CHARGE, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&e&lAngry Villager Particles"));
        break;
    default:
    	giveRandomAmmo(p, l);
    	break;
        	
	}	
  }
  
  public static void giveRandomAmmo(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    int ran = UtilMath.randInt(0, 6);
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
	case 5:
        FireWorksAmmoManager.giveFireWorkAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.FIREWORK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &5&lFirework Ammo"));
        break;
	case 6:
		FunCreeperAmmoManager.giveCreeperAmmo(p, amount);
		UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.MONSTER_EGG, (byte) 50, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &a&lFunCreeper's"));
        break;
	default: 
        MeowAmmoManager.giveMeowAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.SNOW_BALL, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&6MeowBall Ammo"));
        break;
	}	
  }
  
  public static void giveBombs(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    int ran = UtilMath.randInt(0, 2);
	int amount = UtilityMath.getRandomNumberBetween(0, 2);
    
	UtilEffect.playFlameThing(l);
	
	switch (ran) {
	case 1: 
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Treasure Chest> &a" + p.getPlayer().getName() + " &7Has gotten " + amount + " &d&lToken Bomb's"));
        for (Player cur : UtilServer.getPlayers()) {
          cur.playSound(cur.getLocation(), Sound.ENDERMAN_SCREAM, 1.0F, 1.0F);
        }
        
		GadgetAmmo.addGadgetAmo(p, "TokenBomb", amount);
		UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.NETHER_STAR, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &d&lToken Bomb's"));
    	break;
	case 2:
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Treasure Chest> &a" + p.getPlayer().getName() + " &7Has gotten " + amount + " &a&lCoin Bomb's!"));
        for (Player cur : UtilServer.getPlayers()) {
          cur.playSound(cur.getLocation(), Sound.ENDERMAN_SCREAM, 1.0F, 1.0F);
        }
        
        GadgetAmmo.addGadgetAmo(p, "CoinBomb", amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DOUBLE_PLANT, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &a&lCoin Bomb's"));
        break;
	default: 
        giveRandomAmmo(p, l);
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
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a" + p.getPlayer().getName() + " &8Has found the Rare &e&lAngel Mount"));
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
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a" + p.getPlayer().getName() + " &8Has found the Rare &8&lDark Mount"));
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
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a" + p.getPlayer().getName() + " &8Has found the Rare &r&lGhost Mount"));
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
	            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a" + p.getPlayer().getName() + " &8Has found the Rare &3&lPoseidon Mount"));
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
	              Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a" + p.getPlayer().getName() + " &8Has found the Rare &d&lNyan Mount"));
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
    
	launchWardobeFW(l, 1);
    
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
       	break;
	case 20:
    	WardrobeManager.unlockArmor(p, Material.DIAMOND_BOOTS);
      	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_BOOTS, (byte) 0, ChatColor.translateAlternateColorCodes('&', "&b&lDiamond Boots"));
      	break;
	default:
	   giveRandomMount(p, l);
	   break;
	}
  }
  
  public static void runHelix(Location loc) {
	   
      double radius = 5;
 
      for (double y = 5; y >= 0; y -= 0.007) {
          radius = y / 3;
          double x = radius * Math.cos(3 * y);
          double z = radius * Math.sin(3 * y);
     
          double y2 = 5 - y;
     
          Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
      	  ParticleLib18 res = new ParticleLib18(ParticleLib18.ParticleType.FLAME,  0.0D, 1, 0.0D);
      	  res.sendToLocation(loc2);
      }
 
      for (double y = 5; y >= 0; y -= 0.007) {
          radius = y / 3;
          double x = -(radius * Math.cos(3 * y));
          double z = -(radius * Math.sin(3 * y));
     
          double y2 = 5 - y;
     
          Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
          ParticleLib18 res = new ParticleLib18(ParticleLib18.ParticleType.FLAME,  0.0D, 1, 0.0D);
      	  res.sendToLocation(loc2);
      }
 
  }
  
  public static void launchFirework(Location l, int speed) {
	    Firework fw = (Firework)l.getWorld().spawn(l, Firework.class);
	    FireworkMeta meta = fw.getFireworkMeta();
	    meta.addEffect(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.STAR).build());
	    fw.setFireworkMeta(meta);
	    fw.setVelocity(l.getDirection().multiply(speed));
	    detonate(fw);
	  }
  
  public static void launchWardobeFW(Location l, int speed) {
	    Firework fw = (Firework)l.getWorld().spawn(l, Firework.class);
	    FireworkMeta meta = fw.getFireworkMeta();
	    meta.addEffect(FireworkEffect.builder().withColor(Color.RED, Color.AQUA, Color.YELLOW, Color.PURPLE).with(FireworkEffect.Type.CREEPER).build());
	    fw.setFireworkMeta(meta);
	    fw.setVelocity(l.getDirection().multiply(speed));
	    detonate(fw);
	  }
	  public static void detonate(final Firework fw) {
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
	      public void run() {
	        try {
	          fw.detonate();
	        }
	        catch (Exception localException)
	        {
	        }
	      }
	    }
	    , 4L);
	  }
	  
	  public static void playFlameBonus(Location l)
	  {
	    final int i2 = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() { private int particles;
	      private int particlesPerIteration;
	      private float size;
	      private float xFactor;
	      private float yFactor;
	      private float zFactor;
	      private float yOffset;
	      private double xRotation;
	      private double yRotation;
	      private double zRotation;
	      private int step;
	      Location location;

	      public void run() { Vector vector = new Vector();

	        for (int i = 0; i < this.particlesPerIteration; i++) {
	          this.step += 1;

	          float t = 3.141593F / this.particles * this.step;
	          float r = MathUtils.sin(t * 2.718282F * this.particlesPerIteration / this.particles) * this.size;
	          float s = r * 3.141593F * t;

	          vector.setX(this.xFactor * r * -MathUtils.cos(s));
	          vector.setZ(this.zFactor * r * -MathUtils.sin(s));
	          vector.setY(this.yFactor + this.yOffset - 1.0F);

	          UtilVector.rotateVector(vector, this.xRotation, this.yRotation, this.zRotation);
	          ParticleLib18 res = new ParticleLib18(ParticleLib18.ParticleType.FLAME,  0.0D, 1, 0.0D);
	          res.sendToLocation(this.location.add(vector));

	          this.location.subtract(vector);
	        }
	      }
	    }
	    , 1L, 1L).getTaskId();

	    Bukkit.getServer().getScheduler()
	      .runTaskLater(Main.getInstance(), new Runnable()
	    {
	      public void run() {
	        Bukkit.getScheduler().cancelTask(i2);
	      }
	    }
	    , 80L);
	  }
  
}