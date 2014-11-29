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

public class Vampire implements Listener {
	
	  
	  private static ArrayList<Player> draculed = new ArrayList<>();
	  public static ArrayList<Player> dracula = new ArrayList<>();
	
			
	public void playAnimationToRemove(final Player victim) {
		if(!draculed.contains(victim)) {
			draculed.add(victim);
		}
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        draculed.remove(victim);
		    }
		}, 20 * 1);
	}

	
	@SuppressWarnings("deprecation")
	public static void playDraculaToPlayer(final Player draculer) {
		if (!MorphManager.hasMorph(draculer)  && !GadgetManager.hasGadget(draculer)){
			draculer.playSound(draculer.getLocation(), Sound.AMBIENCE_THUNDER,1, 1);
			draculer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(draculer);
		dracula.add(draculer);
		draculer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , Integer.MAX_VALUE , 1));
		 draculer.getInventory().setHelmet(UtilItem.getNamedSkull("MHF_Wither")); 
		 draculer.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.BLACK));
		 draculer.updateInventory();
		 if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (draculer.isValid() || dracula.contains(draculer)) {
		    		if (MorphManager.hasMorph(draculer)) {
		    			if(MorphManager.morph.containsValue(MorphType.VAMPIRE)) {
				draculer.playSound(draculer.getLocation(), Sound.ENDERMAN_HIT,1, 0);
				draculer.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
				MorphManager.removeMorph(draculer , false);
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.vampireDuration);
		 }
		
		
			MorphManager.addMorph(draculer, MorphType.VAMPIRE);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((dracula.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((dracula.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
		
		 
		 @EventHandler
			public void PlayerDamage(EntityDamageByEntityEvent event) {

			if (!(event.getEntityType() == EntityType.PLAYER)) return;
			if (!(event.getDamager().getType() == EntityType.PLAYER)) return;
			Player newTarget = (Player) event.getEntity();
			
			final Player damager = (Player) event.getDamager();	
			
			if(dracula.contains(damager)) {
			
			if (!draculed.contains(newTarget) || !dracula.contains(newTarget)) {

				playAnimationToRemove(newTarget);
				event.setCancelled(true);
				damager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED , 20 * 5 , 2));
				
			}
			}

			}
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
				if (event.getType() == UpdateType.SEC) {
					for(Player victim : draculed) {
						victim.damage(0);
						 victim.getLocation().getWorld().playEffect(victim.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 152);
					}
				
				}
				
				if (event.getType() == UpdateType.TICK) {
					for (Player draculer : dracula) {
						UtilParticle.sendParticleToLocation(draculer.getLocation(), Particle.WITCH_MAGIC, 0.5f, 0.5f, 0.5f, 0.1f, 5);
					}
				}
				}
		
}


