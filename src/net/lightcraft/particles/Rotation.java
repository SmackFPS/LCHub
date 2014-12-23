package net.lightcraft.particles;

import java.util.HashMap;

import me.mike1665.Main.Main;
import me.mike1665.particles18.ParticleLib18;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Rotation extends ParticleMenu
{
  public static final HashMap<Player, Integer> player_particle = new HashMap<Player, Integer>();

  public static void color(Player p, ParticleLib18.ParticleType particle2) {
    if (player_particle.containsKey(p)) {
      stopRotation(p);
      rotationEffect(p, particle2);
    } else {
      rotationEffect(p, particle2);
    }
  }

  public static void angryColor(Player p, ParticleLib18.ParticleType particle2) {
    if (player_particle.containsKey(p)) {
      stopRotation(p);
      angryRotationEffect(p, particle2);
    } else {
      angryRotationEffect(p, particle2);
    }
  }

  public static void rotationEffect(final Player p, final ParticleLib18.ParticleType particle2) {
    if (!player_particle.containsKey(p)) {
      final double radialsPerStep = 3.141592653589793D / 4;
      int i = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
        float step = 0.0F;
        float radius = 0.4F;

        public void run()
        {
          Location loc = p.getLocation();
          loc.add(0.0D, 2.0, 0.0D);
          loc.add(Math.cos(radialsPerStep * step) * radius, 0.0D, Math.sin(radialsPerStep * step) * radius);
  
          ParticleLib18 particle = new ParticleLib18(particle2, 0.0F, 5, 0.0001D);
          particle.sendToLocation(loc);
          this.step += 1.0F;
        }
      }
      , 2, 2).getTaskId();
      player_particle.put(p, Integer.valueOf(i));
    } else {
      stopRotation(p);
    }
  }

  public static void angryRotationEffect(final Player p, final ParticleLib18.ParticleType particle2) {
    if (!player_particle.containsKey(p)) {
      final double radialsPerStep = 3.141592653589793D / 4;
      int i = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new Runnable() {
        float step = 0.0F;
        float radius = 0.4F;


        public void run()
        {
          Location loc = p.getLocation();
          loc.add(0.0D, 2.0 - 0.5D, 0.0D);
          loc.add(Math.cos(radialsPerStep * this.step) * radius, 0.0D, Math.sin(radialsPerStep * this.step) * radius);
          
  		  ParticleLib18 particle = new ParticleLib18(particle2, 0.0F, 5, 0.0001D);
  		  particle.sendToLocation(loc);
          this.step += 1.0F;
        }
      }
      , 2, 2).getTaskId();
      player_particle.put(p, Integer.valueOf(i));
    } else {
      stopRotation(p);
    }
  }

  public static void stopRotation(Player p) {
    if (player_particle.containsKey(p)) {
      Bukkit.getServer().getScheduler().cancelTask(((Integer)player_particle.get(p)).intValue());
      player_particle.remove(p);
    } else {
      p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have an effect to clear!"));
    }
  }
}