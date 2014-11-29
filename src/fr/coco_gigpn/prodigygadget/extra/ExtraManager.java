package fr.coco_gigpn.prodigygadget.extra;

import me.mike1665.Main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import fr.coco_gigpn.prodigygadget.effect.EffectManager;
import fr.coco_gigpn.prodigygadget.effect.EffectManager.EffectType;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Cloud;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.CloudLight;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.CloudSnow;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.FlameLilly;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Fountain;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Helix;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.HourGlass;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Shield;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Tornado;
import fr.coco_gigpn.prodigygadget.extra.extraEffects.Vortex;
import fr.coco_gigpn.prodigygadget.particle.ParticleManager;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilLocation;

public class ExtraManager implements Listener {
	
	

	public static boolean hasExtraEffect(Player p) {
		if (EffectManager.getEffect(p) == EffectType.Cloud
				|| EffectManager.getEffect(p) == EffectType.CloudSnow
				|| EffectManager.getEffect(p) == EffectType.CloudLight
				|| EffectManager.getEffect(p) == EffectType.Helix
				|| EffectManager.getEffect(p) == EffectType.Vortex
				|| EffectManager.getEffect(p) == EffectType.FlameLilly
				|| EffectManager.getEffect(p) == EffectType.HourGlass
				|| EffectManager.getEffect(p) == EffectType.Shield
				|| EffectManager.getEffect(p) == EffectType.Fountain
				|| EffectManager.getEffect(p) == EffectType.Tornado) {

			return true;

		} else {
			return false;
		}
	}
	
	public static void removeExtraEffect(Player p) {
		EffectManager.effect.remove(p);
		UtilLocation.locationEvery2Second.remove(p);
	}

	
   public static void Activate(final Player p, EffectType type) {
		
		if (!EffectManager.hasEffect(p)) {
			
			EffectManager.effect.put(p, type);
			
			if (!ExtraManager.hasExtraEffect(p)) {

			UtilLocation.locationEvery2Second.put(p, p.getLocation());

		} else {
		
			ExtraManager.removeExtraEffect(p);
			EffectManager.effect.put(p, type);
			UtilLocation.locationEverySecond.put(p, p.getLocation());
		}
	 } else { 
		 if (ExtraManager.hasExtraEffect(p)) {
			 ExtraManager.removeExtraEffect(p);
			 EffectManager.effect.put(p, type);
				UtilLocation.locationEverySecond.put(p, p.getLocation());
		 }
		 if (ParticleManager.hasCircleEffect(p)) {
				p.sendMessage(Messages.tooManyEffect.replace("&", "§"));
			}
	 }
	}
	
	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
	    pm.registerEvents(new Cloud() , plugin);
	    pm.registerEvents(new CloudSnow() , plugin);
	    pm.registerEvents(new CloudLight() , plugin);
	    pm.registerEvents(new Helix() , plugin);
	    pm.registerEvents(new Vortex() , plugin);
	    pm.registerEvents(new Tornado() , plugin);
	    pm.registerEvents(new HourGlass() , plugin);
	    pm.registerEvents(new Shield() , plugin);
	    pm.registerEvents(new Fountain() , plugin);
	    pm.registerEvents(new FlameLilly() , plugin);
		
	}



}
