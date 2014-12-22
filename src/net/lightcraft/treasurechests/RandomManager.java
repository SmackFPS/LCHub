package net.lightcraft.treasurechests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.EnderDogeAmmoManager;
import me.mike1665.ammo.GadgetAmmo;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.MeowAmmoManager;
import me.mike1665.ammo.PaintballAmmoManager;
import me.mike1665.coinapi.LcCoinsAPI;
import net.lightcraftmc.fusebox.util.UtilEffect;
import net.lightcraftmc.fusebox.util.UtilEnt;
import net.lightcraftmc.fusebox.util.UtilFirework;
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
    double r = Math.random();
    if (r < 0.5D)
      giveRandomCoins(p, l);
    else
      //giveRandomAmmo(p, l);
     giveRandomMount(p, l);
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
    double r = Math.random();
    int amount = UtilityMath.getRandomNumberBetween(10, 100);
    UtilEffect.playFlameThing(l);
    if (r < 0.10D) {
    	BatBlasterAmmoManager.giveBatAmmo(p, amount);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.IRON_BARDING, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&8BatBlaster Ammo"));
    } else if (r < 0.20D){
    	KittyCannonAmmoManager.giveCatAmmo(p, amount);
    	UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.STICK, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&eKittyCannon Ammo"));
    } else if (r < 0.30D){
        MeowAmmoManager.giveMeowAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.SNOW_BALL, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&6MeowBall Ammo"));
    } else if (r < 0.40D){
        EnderDogeAmmoManager.giveEnderDogeAmmo(p, amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.FIREWORK_CHARGE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&1EnderDoge Ammo"));
    } else if (r < 0.50D){
        GadgetAmmo.addGadgetAmo(p, "QuakeGunAmmo", amount);
        UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.BOLD + Integer.toString(amount) + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
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

    if (!angel) {
      double r = Math.random();
      if (r < 0.2D) {
        if (!p.hasPermission("angelrider")) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &e&lAngel Mount"));
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount angelmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.4D) {
        if (!dark) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &8&lDark Mount"));
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount darkmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.6D) {
        if (!ghost) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &r&lGhost Mount"));
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
            UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
          UtilEffect.playFlameBonus(l);
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount ghostmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.8D) {
        if (!poseidon) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &3&lPoseidon Mount"));
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount poseidonmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 1.0D) {
          if (!nyan) {
              Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&9Mounts> &a " + p.getPlayer().getName() + " &8Has found the Rare &d&lNyan Mount"));
              for (Player cur : UtilServer.getPlayers()) {
                cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
              }
            
            UtilEffect.playFlameBonus(l);
            UtilEnt.spawnArmourStandItem(p, l.add(0.0D, 0.0D, 0.0D), Material.DIAMOND_HOE, (byte) 0, ChatColor.DARK_RED + "" + ChatColor.translateAlternateColorCodes('&', " &r&aQuakeGun Ammo"));
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount nyanmount " + p.getName());
          } else {
            giveRandomThing(p, l);
          }
        }
    }
    else
    {
      giveRandomThing(p, l);
    }
  }
}