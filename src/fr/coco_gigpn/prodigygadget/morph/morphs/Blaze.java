package fr.coco_gigpn.prodigygadget.morph.morphs;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

public class Blaze implements Listener {
	
	  
	  private static ArrayList<Player> victim = new ArrayList<>();
	  public static ArrayList<Player> blaze = new ArrayList<>();
	
			
	public void playAnimationToRemove(final Player victime) {
		if(!victim.contains(victime)) {
			victim.add(victime);
		}
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        victim.remove(victime);
		    }
		}, 20 * 1);
	}

	
	public static void playBlazeToPlayer(final Player blazer) {
		if (!MorphManager.hasMorph(blazer)  && !GadgetManager.hasGadget(blazer)){
			blazer.playSound(blazer.getLocation(), Sound.BLAZE_BREATH ,1, 1);
			blazer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(blazer);
		blaze.add(blazer);
		blazer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , Integer.MAX_VALUE , 1));
		blazer.getInventory().setHelmet(UtilItem.getNamedSkull("MHF_Blaze")); 
		blazer.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.YELLOW));
		blazer.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (blazer.isValid() || blaze.contains(blazer)) {
		    		if (MorphManager.hasMorph(blazer)) {
		    			if(MorphManager.morph.containsValue(MorphType.BLAZE)) {
				blazer.playSound(blazer.getLocation(), Sound.GHAST_FIREBALL ,1, 0);
				blazer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
				MorphManager.removeMorph(blazer ,false);
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.blazeDuration);
		}
		
			MorphManager.addMorph(blazer, MorphType.BLAZE);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((blaze.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((blaze.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
	
		 
		 @EventHandler
			public void PlayerDamage(EntityDamageByEntityEvent event) {

			if (!(event.getEntityType() == EntityType.PLAYER)) return;
			if (!(event.getDamager().getType() == EntityType.PLAYER)) return;
			Player newTarget = (Player) event.getEntity();
			
			final Player damager = (Player) event.getDamager();	
			
			if(blaze.contains(damager)) {
			
			if (!blaze.contains(newTarget) || !blaze.contains(newTarget)) {

				playAnimationToRemove(newTarget);
				event.setCancelled(true);
				damager.addPotionEffect(new PotionEffect(PotionEffectType.JUMP , 20 * 4 , 2));
		
			}
			}

			}
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
				if (event.getType() == UpdateType.SEC) {
					for(Player victime : victim) {
						victime.damage(0);
						 victime.getLocation().getWorld().playEffect(victime.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 11);
					}
				
				}
				
				if (event.getType() == UpdateType.TICK) {
					for (Player blazer : blaze) {
						UtilParticle.sendParticleToLocation(blazer.getLocation().add(0 , 0.5F , 0), Particle.FLAME, 0.3f, 0.3f, 0.3f, 0.05f, 2);
						UtilParticle.sendParticleToLocation(blazer.getLocation().add(0 , 0.2F , 0), Particle.LARGE_SMOKE, 0.3f, 0.3f, 0.3f, 0.05f, 2);
					}
				}
				}
		
}


