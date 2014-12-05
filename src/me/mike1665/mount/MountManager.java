package me.mike1665.mount;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.mount.mounts.NyanRider;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.utils.UpdateEvent;
import me.mike1665.utils.UpdateType;
import me.mike1665.utils.UtilServer;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

public class MountManager
  implements Listener
{
  public static HashMap<UUID, Horse> pet = new HashMap();

  public static boolean HasPet(Player p) {
    if (pet.containsKey(p.getUniqueId())) {
      return true;
    }
    return false;
  }

  public static void removeCurrentPet(Player p, boolean message)
  {
    if (pet.containsKey(p.getUniqueId()))
    {
      ((Horse)pet.get(p.getUniqueId())).remove();
      pet.remove(p.getUniqueId());
      pet.remove(p.getUniqueId());
    } else {
      if (message) {
        p.sendMessage("Pet already removed!");
      }
      return;
    }
  }

  public static boolean isPetOwner(Player p, Horse h) {
    Horse horse = (Horse)pet.get(p.getUniqueId());
    return (horse != null) && (horse.equals(h));
  }

  @EventHandler
  public void OnPlayerLeft(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (HasPet(p))
      removeCurrentPet(p, false);
  }

  @EventHandler
  public void onLeave(PlayerKickEvent e)
  {
    Player p = e.getPlayer();
    if (HasPet(p))
      removeCurrentPet(p, false);
  }

  @EventHandler
  public void InventoryOpenHorse(InventoryOpenEvent event)
  {
    Player p = (Player)event.getPlayer();
    if (!p.isInsideVehicle()) return;

    Entity e = p.getVehicle();
    if ((e.hasMetadata("poseidonrider")) || 
      (e.hasMetadata("darkrider")) || 
      (e.hasMetadata("nyanrider")) || 
      (e.hasMetadata("angelrider")) || 
      (e.hasMetadata("ghostrider")))
      event.setCancelled(true);
  }

  @EventHandler
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
  {
    if ((event.getRightClicked() instanceof Horse))
    {
      Player damager = event.getPlayer();
      Horse h = (Horse)event.getRightClicked();

      if ((h.hasMetadata("poseidonrider")) || 
        (h.hasMetadata("darkrider")) || 
        (h.hasMetadata("nyanrider")) || 
        (h.hasMetadata("angelrider")) || 
        (h.hasMetadata("ghostrider")))
      {
        if (!isPetOwner(damager, h))
        {
          try
          {
            event.setCancelled(true);
            damager.sendMessage("Dis not yur pet");
          } catch (Exception localException) {
          }
          return;
        }
      }
    }
  }

  @EventHandler
  public void PlayerChangedWorldEvent(PlayerChangedWorldEvent e)
  {
    Player p = e.getPlayer();
    if (HasPet(p))
      removeCurrentPet(p, false);
  }

  @EventHandler
  public void HorseDamageEvent(EntityDamageEvent event)
  {
    Entity e = event.getEntity();

    if (!(e instanceof Horse)) {
      return;
    }

    if ((e.hasMetadata("poseidonrider")) || (e.hasMetadata("darkrider")) || 
      (e.hasMetadata("nyanrider")) || 
      (e.hasMetadata("angelrider")) || 
      (e.hasMetadata("ghostrider")))
      event.setCancelled(true);
  }

  @EventHandler
  public void ParticleAura(UpdateEvent event)
  {
    if (event.getType() == UpdateType.TICK)
    {
      for (UUID p : pet.keySet())
      {
        if (((Horse)pet.get(p)).isValid())
        {
          if (((Horse)pet.get(p)).hasMetadata("angelrider")) {
        	  ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SNOW_SHOVEL, 10, 5, 1);
        	  snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
         //   new UtilParticle(UtilParticle.Particle.SNOW_SHOVEL, 0.1000000014901161D, 3, 0.25D).sendToLocation(((Horse)pet.get(p)).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("darkrider")) {
          //  new UtilParticle(UtilParticle.Particle.SPELL_WITCH, 0.1000000014901161D, 3, 0.25D).sendToLocation(((Horse)pet.get(p)).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("ghostrider")) {
           // new UtilParticle(UtilParticle.Particle.FLAME, 0.1000000014901161D, 3, 0.25D).sendToLocation(((Horse)pet.get(p)).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("nyanrider")) {
           // new UtilParticle(UtilParticle.Particle.SPELL_MOB, 0.1000000014901161D, 3, 0.25D).sendToLocation(((Horse)pet.get(p)).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("poseidonrider")) {
           // new UtilParticle(UtilParticle.Particle.WATER_SPLASH, 0.1000000014901161D, 3, 0.25D).sendToLocation(((Horse)pet.get(p)).getLocation().add(0.0D, 1.0D, 0.0D));
          }

        }

      }

    }

    if (event.getType() == UpdateType.SEC)
    {
      for (Player p : UtilServer.getPlayers())
        if (HasPet(p)) {
          Double distanceBtw = Double.valueOf(p.getLocation().distance(((Horse)pet.get(p.getUniqueId())).getLocation()));
          if ((distanceBtw.doubleValue() > 15.0D) && 
            (p.isOnGround()))
            ((Horse)pet.get(p.getUniqueId())).teleport(p);
        }
    }
  }

  public static void registerEvents(Main plugin)
  {
    PluginManager pm = plugin.getServer().getPluginManager();
    pm.registerEvents(new NyanRider(), plugin);
    pm.registerEvents(new MountManager(), plugin);
  }
}