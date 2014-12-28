package com.lightcraftmc.hub.gadgets;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.util.Vector;

import com.lightcraftmc.particles18.ParticleLib18;
import com.lightcraftmc.particles18.ParticleLib18.ParticleType;

public class FunCreepersListener
  implements Listener
{
  @EventHandler
  public void cancelExplosion(EntityExplodeEvent e)
  {
    if ((e.getEntity() instanceof Creeper))
    {
      Location loc = e.getEntity().getLocation();
      e.setCancelled(true);
      e.blockList().clear();
      ParticleLib18 firework = new ParticleLib18(ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.0D, 20, 0.0D);
      ParticleLib18 lava = new ParticleLib18(ParticleLib18.ParticleType.LAVA, 0.0D, 10, 0.0D);
      ParticleLib18 heart = new ParticleLib18(ParticleLib18.ParticleType.HEART, 0.0D, 5, 0.0D);
      ParticleLib18 smoke = new ParticleLib18(ParticleLib18.ParticleType.SMOKE_LARGE, 0.0D, 10, 0.0D);
      firework.sendToLocation(loc);
      lava.sendToLocation(loc);
      heart.sendToLocation(loc);
      smoke.sendToLocation(loc);
      


      List<Entity> near = ((Entity)e).getNearbyEntities(10.0D, 10.0D, 10.0D);
      for (Entity z : near) {
        z.setVelocity(new Vector(0, 2, 0));
      }
    }
  }
  
  @EventHandler
  public void preventEggSpawn(ItemSpawnEvent event)
  {
    if ((event.getEntity() instanceof Egg)) {
      event.setCancelled(true);
    }
  }
  
  @EventHandler
  public void creeper(EntityDamageByEntityEvent e)
  {
    if ((e.getDamager() instanceof Creeper)) {
      e.setCancelled(true);
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     com.lightcraftmc.funstuff.FunCreepers
 * JD-Core Version:    0.7.0.1
 */