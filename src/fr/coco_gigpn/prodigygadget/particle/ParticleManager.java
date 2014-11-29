package fr.coco_gigpn.prodigygadget.particle;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.particle.particles.CircleParticle;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;

public class ParticleManager implements Listener {
	
	public enum ParticleType {
		Heart, Music, Flame, Water, Lava, Spark, Reddust, Enchantement, Witch, AngryVillager, Portal, Snow, Slime, Rainbow, SnowShovel, MagicCrit

	}
	

	public static boolean hasCircleEffect(Player p) {
		if (CircleParticle.effect.containsKey(p)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void removeCircleEffect(Player p) {
		EffectManager.effect.remove(p);
		UtilLocation.locationEverySecond.remove(p);
		CircleParticle.effect.remove(p);
	}


	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
	    pm.registerEvents(new CircleParticle() , plugin);
		
	}
}
