package me.mike1665.mount;

import java.util.HashMap;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.mount.mounts.AngelRider;
import me.mike1665.mount.mounts.DarkRider;
import me.mike1665.mount.mounts.GhostRider;
import me.mike1665.mount.mounts.NyanRider;
import me.mike1665.mount.mounts.PoseidonRider;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.update.UpdateType;
import me.mike1665.update.event.UpdateEvent;
import net.lightcraftmc.fusebox.util.UtilServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
  public static HashMap<UUID, Horse> pet = new HashMap<UUID, Horse>();

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
        p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.DARK_GRAY + "> " + ChatColor.RESET + "" + ChatColor.RED + "Mount already removed!");
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
  public void HorseInteract(PlayerInteractEntityEvent event)
  {
    if (!(event.getRightClicked() instanceof Horse)) {
      return;
    }
    Player player = event.getPlayer();
    Horse horse = (Horse)event.getRightClicked();

    if ((horse.getOwner() == null) || (!horse.getOwner().equals(player)))
    {
      player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.DARK_GRAY + "> " + ChatColor.RESET + "" + ChatColor.RED + "This is not your mount!");
      event.setCancelled(true);
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

@SuppressWarnings("deprecation")
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
        	  	ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SNOW_SHOVEL, 0.1000000014901161D, 3, 0.25D);
        	  	snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
          }

         if (((Horse)pet.get(p)).hasMetadata("darkrider")) {
       	  		ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SPELL_WITCH, 0.1000000014901161D, 3, 0.25D);
       	  		snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
    	  }

          if (((Horse)pet.get(p)).hasMetadata("ghostrider")) {
           	  	ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.FLAME, 0.1000000014901161D, 3, 0.25D);
           	  	snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("nyanrider")) {
           	  	ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.SPELL_MOB, 0.1000000014901161D, 3, 0.25D);
           	  	snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
          }

          if (((Horse)pet.get(p)).hasMetadata("poseidonrider")) {
           	  	ParticleLib18  snow = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.WATER_SPLASH, 0.1000000014901161D, 3, 0.25D);
           	  	snow.sendToLocation(pet.get(p).getLocation().add(0.0D, 1.0D, 0.0D));
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

  public static void registerEvents()
  {
    PluginManager pm = Bukkit.getServer().getPluginManager();
    pm.registerEvents(new NyanRider(), Main.getInstance());
    pm.registerEvents(new GhostRider(), Main.getInstance());
    pm.registerEvents(new DarkRider(), Main.getInstance());
    pm.registerEvents(new AngelRider(), Main.getInstance());
    pm.registerEvents(new PoseidonRider(), Main.getInstance());
    pm.registerEvents(new MountManager(), Main.getInstance());
  }
}