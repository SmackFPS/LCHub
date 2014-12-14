package me.mike1665.Main;

import me.mike1665.utils.UpdateEvent;
import me.mike1665.utils.UpdateType;

import org.bukkit.plugin.java.JavaPlugin;

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
    for (UpdateType updateType : (UpdateType[])UpdateType.class.getEnumConstants())
      if (updateType.Elapsed())
        this._plugin.getServer().getPluginManager().callEvent(new UpdateEvent(updateType));
  }
}