package me.mike1665.effects;

import java.util.HashMap;

import me.mike1665.Main.Main;
import me.mike1665.extra.ExtraManager;
import me.mike1665.particle.ParticleManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

public class EffectManager
  implements Listener
{
  public static HashMap<Player, EffectType> effect = new HashMap();

  @EventHandler
  public void OnPlayerLeft(PlayerQuitEvent e)
  {
    Player p = e.getPlayer();
    if (hasEffect(p))
      removeEffect(p, false);
  }

  @EventHandler
  public void onLeave(PlayerKickEvent e)
  {
    Player p = e.getPlayer();

    if (hasEffect(p))
      removeEffect(p, false);
  }

  public static boolean hasEffect(Player p)
  {
    if (effect.containsKey(p)) {
      return true;
    }
    return false;
  }

  public static void addEffect(Player p, EffectType type)
  {
    if (!effect.containsKey(p)) {
      effect.put(p, type);
    } else {
      removeEffect(p, false);
      effect.put(p, type);
    }
  }

  public static EffectType getEffect(Player p)
  {
    return (EffectType)effect.get(p);
  }

  public static void removeEffect(Player p, boolean message) {
    if (hasEffect(p)) {
      if (ParticleManager.hasCircleEffect(p)) {
        ParticleManager.removeCircleEffect(p);
      }
      effect.remove(p);
      if (ExtraManager.hasExtraEffect(p)) {
        ExtraManager.removeExtraEffect(p);
      }

    }
    else if (message) {
      p.sendMessage("Particle Aready Removed");
    }
  }

  public static void registerEvents(Main plugin)
  {
    PluginManager pm = plugin.getServer().getPluginManager();
    pm.registerEvents(new EffectManager(), plugin);
  }

  public static enum EffectType
  {
    CircleEffect, FlameRing, GreenSpiral, Cloud, CloudSnow, CloudLight, Helix, Tornado, Vortex, FlameLilly, HourGlass, Shield, Fountain, GreenRing;
  }
}