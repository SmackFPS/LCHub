package fr.coco_gigpn.prodigygadget.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;

public class UtilCounter implements Listener {
	
	public static float count = 0;
	
	@EventHandler
	public void updateEvent(UpdateEvent e) {
		if (e.getType() == UpdateType.TICK) {
			count += 1;
		}
	}
}
