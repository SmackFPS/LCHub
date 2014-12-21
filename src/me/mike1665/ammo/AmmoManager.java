package me.mike1665.ammo;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class AmmoManager implements Listener{
	
	  public static void registerEvents()
	  {
	    PluginManager pm = Bukkit.getServer().getPluginManager();
	    pm.registerEvents(new BatBlasterAmmoManager(), Main.getInstance());
	    pm.registerEvents(new EnderDogeAmmoManager(), Main.getInstance());
	    pm.registerEvents(new FireWorksAmmoManager(), Main.getInstance());
	    pm.registerEvents(new KittyCannonAmmoManager(), Main.getInstance());
	    pm.registerEvents(new MeowAmmoManager(), Main.getInstance());
	    pm.registerEvents(new PaintballAmmoManager(), Main.getInstance());
	  }
}
