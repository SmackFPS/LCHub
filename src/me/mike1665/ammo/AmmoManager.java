package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class AmmoManager implements Listener{
	
	  public static void registerEvents(Main plugin)
	  {
	    PluginManager pm = plugin.getServer().getPluginManager();
	    pm.registerEvents(new BatBlasterAmmoManager(), plugin);
	    pm.registerEvents(new EnderDogeAmmoManager(), plugin);
	    pm.registerEvents(new FireWorksAmmoManager(), plugin);
	    pm.registerEvents(new KittyCannonAmmoManager(), plugin);
	    pm.registerEvents(new MeowAmmoManager(), plugin);
	    pm.registerEvents(new PaintballAmmoManager(), plugin);
	  }
}
