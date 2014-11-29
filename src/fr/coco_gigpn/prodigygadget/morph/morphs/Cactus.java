package fr.coco_gigpn.prodigygadget.morph.morphs;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

public class Cactus implements Listener {
	
	  
	  private static ArrayList<Player> cactused = new ArrayList<>();
	  public static ArrayList<Player> cactus = new ArrayList<>();
	
			
	public void playAnimationToRemove(final Player victim) {
		if(!cactused.contains(victim)) {
			cactused.add(victim);
		}
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	cactused.remove(victim);
		    }
		}, 20 * 1);
	}

	
	@SuppressWarnings("deprecation")
	public static void playCactusToPlayer(final Player cactuser) {
		if (!MorphManager.hasMorph(cactuser) && !GadgetManager.hasGadget(cactuser)){
			cactuser.playSound(cactuser.getLocation(), Sound.ITEM_BREAK ,1, 0);
			cactuser.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(cactuser);
		cactus.add(cactuser);
		cactuser.addPotionEffect(new PotionEffect(PotionEffectType.SPEED , Integer.MAX_VALUE , 1));
		cactuser.getInventory().setHelmet(UtilItem.create(Material.CACTUS)); 
		cactuser.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.OLIVE));
		cactuser.getInventory().setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, Color.OLIVE));
		cactuser.getInventory().setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, Color.OLIVE));
		cactuser.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (cactuser.isValid() || cactus.contains(cactuser)) {
		    		if (MorphManager.hasMorph(cactuser)) {
		    			if(MorphManager.morph.containsValue(MorphType.CACTUS)) {
				MorphManager.removeMorph(cactuser ,false);
				cactuser.playSound(cactuser.getLocation(), Sound.ITEM_BREAK ,1, 0);
				cactuser.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.cactusDuration);
		}
		
		
			MorphManager.addMorph(cactuser, MorphType.CACTUS);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((cactus.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((cactus.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
		

	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
				if (event.getType() == UpdateType.SEC) {
					for(Player victim : cactused) {
						victim.damage(0);
						victim.getLocation().getWorld().playEffect(victim.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 81);
					
					}
				
				}
				
				if (event.getType() == UpdateType.TICK) {
					for (Player cactuser : cactus) {
						UtilParticle.sendParticleToLocation(cactuser.getLocation().add(0 , 1 , 0), Particle.CRIT, 0.5f, 0.5f, 0.5f, 0.1f, 1);
						UtilParticle.sendParticleToLocation(cactuser.getLocation().add(0 , 1 , 0), Particle.TOWN_AURA, 0.5f, 0.5f, 0.5f, 0.1f, 3);
						for(Entity e : cactuser.getNearbyEntities(3, 3, 3)) {
							if(e instanceof Player) {
								Player p = (Player) e;
								if(!cactus.contains(p)) {
									playAnimationToRemove(p);
								}
							}
						}
					}
				}
				}
		
}


