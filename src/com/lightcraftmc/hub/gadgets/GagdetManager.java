package com.lightcraftmc.hub.gadgets;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import com.lightcraftmc.hub.main.Main;

public class GagdetManager
{
  public static void registerEvents()
  {
    PluginManager pm = Bukkit.getServer().getPluginManager();
    pm.registerEvents(new BatBlaster(), Main.getInstance());
    pm.registerEvents(new EnderDoge(), Main.getInstance());
    pm.registerEvents(new FireWorks(), Main.getInstance());
    pm.registerEvents(new KittyCannon(), Main.getInstance());
    pm.registerEvents(new MeowBall(), Main.getInstance());
    pm.registerEvents(new FunCreepers(), Main.getInstance());
    pm.registerEvents(new QuakeGun(), Main.getInstance());
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.gadgets.GagdetManager
 * JD-Core Version:    0.7.0.1
 */