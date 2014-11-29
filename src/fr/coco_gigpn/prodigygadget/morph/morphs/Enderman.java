package fr.coco_gigpn.prodigygadget.morph.morphs;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
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
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class Enderman implements Listener {
	
	  

	  public static ArrayList<Player> enderman = new ArrayList<>();
	  private static ArrayList<Player> endermaned = new ArrayList<>();
	
	  public void playAnimationToRemove(final Player newTarget, final Player damager) {
			if(!endermaned.contains(newTarget)) {
				endermaned.add(newTarget);
				UtilAction.velocity(newTarget, UtilAlg.getTrajectory(damager, newTarget), 0.8D, false, 0.0D, 0.5D, 10.0D, true);
				damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED , 20*4 , 2));
				damager.getWorld().playSound(damager.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				UtilParticle.sendParticleToLocation(newTarget.getLocation().add(0 , 1 , 0), Particle.PORTAL, 0.5f, 0.5f, 0.5f, 0.5f, 20);
				UtilParticle.sendParticleToLocation(newTarget.getLocation().add(0 , 1 , 0), Particle.ENCHANTMENT_TABLE, 0.5f, 0.5f, 0.5f, 0.5f, 20);
				Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
				    public void run() {
				    	if (newTarget.isValid()) {
				    		newTarget.teleport(newTarget.getLocation().add(UtilMath.randomRangeInt(-4, 4) , 2 ,UtilMath.randomRangeInt(-4, 4)));
				    	}
				    }
				}, 20 * 1);
				
			}
			Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
			    public void run() {
			        endermaned.remove(newTarget);
			    }
			}, 20 * 5);
		}

	
	@SuppressWarnings("deprecation")
	public static void playEndermanToPlayer(final Player endermaner) {
		if (!MorphManager.hasMorph(endermaner)  && !GadgetManager.hasGadget(endermaner)){
			endermaner.playSound(endermaner.getLocation(), Sound.ENDERMAN_HIT ,1, 2);
			endermaner.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(endermaner);
		enderman.add(endermaner);
		endermaner.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , Integer.MAX_VALUE , 1));
		endermaner.getInventory().setHelmet(UtilItem.getNamedSkull("MHF_Enderman")); 
		endermaner.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.BLACK));
		endermaner.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (endermaner.isValid() || enderman.contains(endermaner)) {
		    		if (MorphManager.hasMorph(endermaner)) {
		    			if(MorphManager.morph.containsValue(MorphType.ENDERMAN)) {
		    				endermaner.playSound(endermaner.getLocation(), Sound.ENDERMAN_IDLE ,1, 2);
		    				endermaner.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
				MorphManager.removeMorph(endermaner ,false);
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.blazeDuration);
		}
		
			MorphManager.addMorph(endermaner, MorphType.ENDERMAN);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((enderman.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((enderman.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
	

		@EventHandler
			public void PlayerDamage(EntityDamageByEntityEvent event) {

			if (!(event.getEntityType() == EntityType.PLAYER)) return;
			if (!(event.getDamager().getType() == EntityType.PLAYER)) return;
			final Player newTarget = (Player) event.getEntity();
			
			final Player damager = (Player) event.getDamager();	
			
			if(enderman.contains(damager)) {
			
			if (!enderman.contains(newTarget) || !enderman.contains(newTarget)) {
			
			event.setCancelled(true);
				
				playAnimationToRemove(newTarget, damager);
		
			}
			}

			}
	
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
		
				
				if (event.getType() == UpdateType.TICK) {
					for (Player ghaster : enderman) {
						UtilParticle.sendParticleToLocation(ghaster.getLocation().add(0 , 0 , 0), Particle.PORTAL, 0.2f, -0.2f, 0.2f, 0f, 3);
						UtilParticle.sendParticleToLocation(ghaster.getLocation().add(0 , 0.1F , 0), Particle.MAGIC_CRIT, 0.1f, 0.1f, 0.1f, 0.05f, 1);
					}
				}
				}
		
}


