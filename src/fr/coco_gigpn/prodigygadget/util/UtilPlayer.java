package fr.coco_gigpn.prodigygadget.util;

import java.util.List;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UtilPlayer {

	public static void playerToRemove(final Player p, final List<Player> l, int time) {
		Bukkit.getScheduler().runTaskLater(Main.schedule,
				new Runnable() {

					@Override
					public void run() {

						l.remove(p);
						
					}

				}, time * 10);

	}

}
