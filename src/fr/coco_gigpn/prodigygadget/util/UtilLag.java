package fr.coco_gigpn.prodigygadget.util;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;

public class UtilLag implements Listener
{
  private long _lastRun = -1L;
  private int _count;
  private static double _ticksPerSecond;
  @SuppressWarnings("unused")
private double _ticksPerSecondAverage;
  private long _lastAverage;

private HashSet<Player> _monitoring = new HashSet<Player>();
  
  public UtilLag(JavaPlugin plugin)
  {
    this._lastRun = System.currentTimeMillis();
    this._lastAverage = System.currentTimeMillis();
  }
  
  
  @EventHandler
  public void playerQuit(PlayerQuitEvent event)
  {
    this._monitoring.remove(event.getPlayer());
  }
  
  @EventHandler
  public void update(UpdateEvent event)
  {
    if (event.getType() != UpdateType.SEC) {
      return;
    }
    long now = System.currentTimeMillis();
    _ticksPerSecond = (1000.0D / (now - this._lastRun) * 20.0D);
    
    if (this._count % 30 == 0)
    {
      this._ticksPerSecondAverage = (30000.0D / (now - this._lastAverage) * 20.0D);
      this._lastAverage = now;
    }
    this._lastRun = now;
    
    this._count += 1;
  }
  
  public static double getTicksPerSecond()
  {
    return _ticksPerSecond;
  }
  
  public static double getTPS()
  {
    return _ticksPerSecond;
  }
  
  public static boolean ServerisLag() {
	  if (getTicksPerSecond() <= 18) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  

}
