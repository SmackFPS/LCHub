package fr.coco_gigpn.prodigygadget.morph;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;

import fr.coco_gigpn.prodigygadget.morph.morphs.Astronot;
import fr.coco_gigpn.prodigygadget.morph.morphs.Blaze;
import fr.coco_gigpn.prodigygadget.morph.morphs.Cactus;
import fr.coco_gigpn.prodigygadget.morph.morphs.Enderman;
import fr.coco_gigpn.prodigygadget.morph.morphs.Ghast;
import fr.coco_gigpn.prodigygadget.morph.morphs.Vampire;
import fr.coco_gigpn.prodigygadget.morph.morphs.Witch;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;

public class MorphManager implements Listener {
public static HashMap<Player, MorphType> morph = new HashMap<Player, MorphType>();
	
	public static enum MorphType {
		ASTRONAUT,
		VAMPIRE,
		WITCH,
		BLAZE,
		CACTUS,
		GHAST,
		ENDERMAN
	}
	
	public static void addMorph(Player p , MorphType type) {
		morph.put(p, type);
	}
	
	public static void removeMorph(Player p, boolean message) {
		
		if(hasMorph(p)) {
		
		 UtilItem.clearArmor(p);
		UtilInventory.restoreArmor(p);
		UtilInventory.forsakeArmor(p);
		for (PotionEffect effect : p.getActivePotionEffects()) {
	        p.removePotionEffect(effect.getType());
		}
		if(message == true) {
		p.playSound(p.getLocation(), Sound.ZOMBIE_UNFECT ,1, 2);
		}
		morph.remove(p);
		if(Astronot.astronot.contains(p)) {
			Astronot.astronot.remove(p);
		}
		if(Blaze.blaze.contains(p)) {
			Blaze.blaze.remove(p);
		}
		if(Cactus.cactus.contains(p)) {
			Cactus.cactus.remove(p);
		}
		if(Vampire.dracula.contains(p)) {
			Vampire.dracula.remove(p);
		}
		if(Witch.witch.contains(p)) {
			Witch.witch.remove(p);
		}
		if(Ghast.ghast.contains(p)) {
			Ghast.ghast.remove(p);
		}
		if(Enderman.enderman.contains(p)) {
			Enderman.enderman.remove(p);
		}
		} else {
			if(message == true) {
			Messages.alreadymorphremoved.replace("&", "§");
			}
		}
	}
	
	public static boolean hasMorph(Player p) {
		if (morph.containsKey(p)) {
			return true;
		} else {
			return false;
		}
	}
	
	 @EventHandler
	 public void Onplayerquit(PlayerQuitEvent event) {
		 Player player = event.getPlayer();
		if(hasMorph(player)) {
			removeMorph(player, false);
		}
	 }
	 
	 @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		 Player player = e.getPlayer();
		 if(hasMorph(player)) {
				removeMorph(player, false);
			}
	  }
	 
	public static void registerEvents(Main plugin) {

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(new Vampire() , plugin);
		pm.registerEvents(new Witch() , plugin);
		pm.registerEvents(new Ghast() , plugin);
		pm.registerEvents(new Enderman() , plugin);
		pm.registerEvents(new Cactus() , plugin);
		pm.registerEvents(new Blaze() , plugin);
		pm.registerEvents(new Astronot() , plugin);
	    pm.registerEvents(new MorphManager() , plugin);
		
	}

}
