package com.lightcraftmc.particles;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;

import com.lightcraftmc.hub.main.Main;
import com.lightcraftmc.particles18.ParticleLib18.ParticleType;

public class UnlockedParticle implements Listener{
	
	private static Main plugin;
	
	public static void initialize(){
		UnlockedParticle.plugin = Main.getInstance();
	}
	
	public static boolean hasUnlockedParticle(OfflinePlayer p, ParticleType m) {
		return plugin.getConfig().getBoolean(p.getPlayer().getUniqueId() + "." + m.toString() + "_Particle");
	}
	
	public static void unlockParticle(OfflinePlayer p, ParticleType m) {
		plugin.getConfig().set(p.getPlayer().getUniqueId() + "." + m.toString() + "_Particle", true);
		plugin.saveConfig();
	}

}
