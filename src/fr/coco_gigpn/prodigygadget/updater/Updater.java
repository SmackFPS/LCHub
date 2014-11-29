package fr.coco_gigpn.prodigygadget.updater;

import org.bukkit.plugin.java.JavaPlugin;

import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;

public class Updater
  implements Runnable
{
  private JavaPlugin _plugin;
  
  public Updater(JavaPlugin plugin)
  {
    this._plugin = plugin;
    this._plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this._plugin, this, 0L, 1L);
  }
  
  public void run()
  {
    for (UpdateType updateType : UpdateType.class.getEnumConstants()) {
      if (updateType.Elapsed()) {
        this._plugin.getServer().getPluginManager().callEvent(new UpdateEvent(updateType));
      }
    }
  }
}
