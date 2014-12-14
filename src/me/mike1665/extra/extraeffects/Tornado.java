package me.mike1665.extra.extraeffects;

import me.mike1665.effects.EffectManager;
import me.mike1665.extra.ExtraManager;
import me.mike1665.particles18.ParticleLib18;
import me.mike1665.update.UpdateType;
import me.mike1665.update.event.UpdateEvent;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Tornado
  implements Listener
{
  float LineNumber = 3.0F;
  float j = 0.0F;
  float radius = 0.3F;
  float heightEcart = 0.01F;

  @EventHandler
  public void LocationUpdater(UpdateEvent event) {
    if (event.getType() == UpdateType.TICK)
    {
      for (Player p : EffectManager.effect3.keySet())
      {
        if ((EffectManager.getEffect(p) == EffectManager.EffectType.Tornado) && 
          (p.isValid()))
        {
          if (ExtraManager.isMoving(p))
          {
            Location loc = p.getLocation();

            loc.setY(loc.getY() + 2.0D);

            for (int k = 0; k < this.LineNumber; k++)
            {
              loc.add(Math.cos(this.j) * this.radius, this.j * this.heightEcart, 
                Math.sin(this.j) * this.radius);
   		   	  ParticleLib18 fw = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.0D, 1, 0.0D);
   		   	  fw.sendToLocation(loc);
            }

            this.j += 0.2F;
            if (this.j > 50.0F)
            {
              this.j = 0.0F;
            }

          }
          else if (!p.isInsideVehicle()) {
 		   ParticleLib18 fw = new ParticleLib18(me.mike1665.particles18.ParticleLib18.ParticleType.FIREWORKS_SPARK, 0.1000000014901161D, 4, 0.300000011920929D);
 		   fw.sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
          }
        }
      }
    }
  }
}