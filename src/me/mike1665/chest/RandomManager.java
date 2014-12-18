package me.mike1665.chest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.ammo.BatBlasterAmmoManager;
import me.mike1665.ammo.KittyCannonAmmoManager;
import me.mike1665.ammo.PaintballAmmoManager;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.utils.UtilEffect;
import me.mike1665.utils.UtilEnt;
import me.mike1665.utils.UtilFirework;
import me.mike1665.utils.UtilServer;
import me.mike1665.utils.UtilityMath;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

public class RandomManager
{
	
	private static Main plugin;
	  
	public static void initialize(Main plugin)
	{
		RandomManager.plugin = plugin;
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
      giveRandomAmmo(p, l);
  }

  public static void giveRandomCoins(Player p, Location l)
  {
    int am = UtilityMath.getRandomNumberBetween(200, 500);
    LcCoinsAPI.givePoints(p, am);
    UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), Integer.toString(am) + " §e§lCoins");
    UtilFirework.fireFirework(l.add(0.0D, 2.0D, 0.0D));
  }

  public static void giveRandomAmmo(Player p, Location l) throws FileNotFoundException, IOException, InvalidConfigurationException
  {
    double r = Math.random();
    int amount = UtilityMath.getRandomNumberBetween(200, 500);
    UtilEffect.playFlameThing(l);
    if (r < 0.34D) {
      BatBlasterAmmoManager.giveBatAmmo(p, amount);
      UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "§4" + Integer.toString(amount) + " BatBlaster Ammo");
    } else if (r < 0.67D) {
      PaintballAmmoManager.givePBAmmo(p, amount);
      UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "§4" + Integer.toString(amount) + " Paintball Ammo");
    } else {
      KittyCannonAmmoManager.giveCatAmmo(p, amount);
      UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "§4" + Integer.toString(amount) + " Kitty Cannon Ammo");
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
            Bukkit.broadcastMessage("Has found the RARE Angel Rider Mount!");
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "AngelRider Mount");
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount angelmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.4D) {
        if (!dark) {
            Bukkit.broadcastMessage("Has Found the RARE DarkRider Mount!");
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "DarkRider Mount");
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount darkmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.6D) {
        if (!ghost) {
            Bukkit.broadcastMessage("Has found the GhostMount!");
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "GhostRider Mount");
          UtilEffect.playFlameBonus(l);
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount ghostmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.8D) {
        if (!poseidon) {
            Bukkit.broadcastMessage("Has found the Poseidon Mount!");
            for (Player cur : UtilServer.getPlayers()) {
              cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
            }
          
          UtilEffect.playFlameBonus(l);
          UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "Poseidon Mount");
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "unlockmount poseidonmount " + p.getName());
        } else {
          giveRandomThing(p, l);
        }
      }
      else if (r < 0.10D) {
          if (!nyan) {
              Bukkit.broadcastMessage("Has found the Nyan Mount!");
              for (Player cur : UtilServer.getPlayers()) {
                cur.playSound(cur.getLocation(), Sound.WITHER_SPAWN, 1.0F, 1.0F);
              }
            
            UtilEffect.playFlameBonus(l);
            UtilEnt.spawnFlyingEntity(p, l.add(0.0D, 0.8D, 0.0D), "Poseidon Mount");
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