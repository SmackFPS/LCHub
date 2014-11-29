package fr.coco_gigpn.prodigygadget.event;

import me.mike1665.Main.Main;

import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.event.events.PlayerListeners;

public class EventManager {
	
	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
	    pm.registerEvents(new PlayerListeners() , plugin);
		
	}

}
