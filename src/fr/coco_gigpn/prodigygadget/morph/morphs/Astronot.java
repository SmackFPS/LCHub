package fr.coco_gigpn.prodigygadget.morph.morphs;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.morph.MorphManager.MorphType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class Astronot implements Listener {
	

	 public static ArrayList<Player> astronot = new ArrayList<>();
	

	@SuppressWarnings("deprecation")
	public static void playAstronotToPlayer(final Player astro) {
		if (!MorphManager.hasMorph(astro) && !GadgetManager.hasGadget(astro)){
			astro.playSound(astro.getLocation(), Sound.BAT_TAKEOFF ,1, 2);
			astro.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(astro);
		astronot.add(astro);
		astro.addPotionEffect(new PotionEffect(PotionEffectType.SLOW , Integer.MAX_VALUE , 2));
		astro.getInventory().setHelmet(UtilItem.create(Material.STAINED_GLASS ,1 , (byte) 11 , "Scaphandre")); 
		astro.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.WHITE));
		astro.getInventory().setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, Color.WHITE));
		astro.getInventory().setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, Color.WHITE));
		astro.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (astro.isValid() || astronot.contains(astro)) {
		    		if (MorphManager.hasMorph(astro)) {
		    			if(MorphManager.morph.containsValue(MorphType.ASTRONAUT)) {
				MorphManager.removeMorph(astro ,false);
				astro.playSound(astro.getLocation(), Sound.BAT_TAKEOFF ,1, 2);
				astro.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.astronotDuration);
		}

			MorphManager.addMorph(astro, MorphType.ASTRONAUT);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((astronot.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			
		 
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {


				if (event.getType() == UpdateType.TICK) {
					for (Player astro : astronot) {
						UtilParticle.sendParticleToLocation(astro.getLocation().add(0 , 0.1F , 0), Particle.MAGIC_CRIT, 0.3f, 0.3f, 0.3f, 0.05f, 2);
						UtilParticle.sendParticleToLocation(astro.getLocation().add(0 , 0.2F , 0), Particle.SPELL, 0.4f, 0.4f, 0.4f, 0.4f, 2);
					}
				}
				
			}
}


