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
import fr.coco_gigpn.prodigygadget.util.UtilAction;
import fr.coco_gigpn.prodigygadget.util.UtilAlg;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class Ghast implements Listener {
	
	  

	  public static ArrayList<Player> ghast = new ArrayList<>();
	


	
	@SuppressWarnings("deprecation")
	public static void playGhastToPlayer(final Player ghaster) {
		if (!MorphManager.hasMorph(ghaster)  && !GadgetManager.hasGadget( ghaster)){
			ghaster.playSound(ghaster.getLocation(), Sound.GHAST_CHARGE ,1, 2);
			ghaster.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(ghaster);
		ghast.add(ghaster);
		ghaster.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , Integer.MAX_VALUE , 1));
		ghaster.getInventory().setHelmet(UtilItem.getNamedSkull("TininchoMC")); 
		ghaster.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.WHITE));
		ghaster.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (ghaster.isValid() || ghast.contains(ghaster)) {
		    		if (MorphManager.hasMorph(ghaster)) {
		    			if(MorphManager.morph.containsValue(MorphType.GHAST)) {
		    				ghaster.playSound(ghaster.getLocation(), Sound.GHAST_DEATH ,1, 0);
		    				ghaster.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
				MorphManager.removeMorph(ghaster ,false);
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.blazeDuration);
		}
		
			MorphManager.addMorph(ghaster, MorphType.GHAST);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((ghast.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((ghast.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
	
		@EventHandler
			public void PlayerDamage(EntityDamageByEntityEvent event) {

			if (!(event.getEntityType() == EntityType.PLAYER)) return;
			if (!(event.getDamager().getType() == EntityType.PLAYER)) return;
			Player newTarget = (Player) event.getEntity();
			
			final Player damager = (Player) event.getDamager();	
			
			if(ghast.contains(damager)) {
			
			if (!ghast.contains(newTarget) || !ghast.contains(newTarget)) {
				
				event.setCancelled(true);
				newTarget.getLocation().getWorld().playEffect(newTarget.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 51);
				newTarget.damage(0);
				UtilParticle.sendPartileToPlayer(damager, Particle.EXPLODE, newTarget.getLocation(), 0.3f, 0.3f, 0.3f, 0.3f, 10);
				UtilAction.velocity(newTarget, UtilAlg.getTrajectory(damager, newTarget), 0.4D, false, 0.0D, 0.2D, 10.0D, true);
				damager.playSound(damager.getLocation(), Sound.GHAST_FIREBALL, 1, 1);
				damager.addPotionEffect(new PotionEffect(PotionEffectType.JUMP , 20 * 4 , 2));
			
			}
			}

			}
	
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
		
				
				if (event.getType() == UpdateType.TICK) {
					for (Player ghaster : ghast) {
						UtilParticle.sendParticleToLocation(ghaster.getLocation().add(0 , 0.5F , 0), Particle.RED_DUST, 0.3f, 0.3f, 0.3f, 0f, 1);
						UtilParticle.sendParticleToLocation(ghaster.getLocation().add(0 , 0.2F , 0), Particle.LAVA, 0.3f, 0.3f, 0.3f, 0.05f, 1);
					}
				}
				}
		
}


