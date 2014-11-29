package fr.coco_gigpn.prodigygadget.util;

import me.mike1665.Main.Main;

import org.bukkit.scheduler.BukkitRunnable;

public class GravityTask
  extends BukkitRunnable
{
  private final Main plugin;
  
  public GravityTask(Main plugin)
  {
    this.plugin = plugin;
  }
  
  public void run()
  {
    this.plugin.UpdateVelocities();
    new GravityTask(this.plugin).runTaskLater(this.plugin, 1L);
  }
}
