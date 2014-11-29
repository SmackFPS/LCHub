package fr.coco_gigpn.prodigygadget.morph.morphs;

import java.util.ArrayList;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
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

public class Witch implements Listener {
	
	  
	  private static ArrayList<Player> witched = new ArrayList<>();
	  public static ArrayList<Player> witch = new ArrayList<>();
	
			
	public void playAnimationToRemove(final Player victim) {
		if(!witched.contains(victim)) {
			witched.add(victim);
		}
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        witched.remove(victim);
		    }
		}, 20 * 1);
	}

	
	@SuppressWarnings("deprecation")
	public static void playWitchToPlayer(final Player witcher) {
		if (!MorphManager.hasMorph(witcher)  && !GadgetManager.hasGadget(witcher)){
			witcher.playSound(witcher.getLocation(), Sound.ZOMBIE_UNFECT ,1, 2);
			witcher.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		UtilInventory.saveArmor(witcher);
		witch.add(witcher);
		witcher.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY , Integer.MAX_VALUE , 1));
		witcher.getInventory().setHelmet(UtilItem.getNamedSkull("KingEndermen")); 
		witcher.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.PURPLE));
		witcher.updateInventory();
		if(Configs.MorphInfiniteDuration == false) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		    	if (witcher.isValid() || witch.contains(witcher)) {
		    		if (MorphManager.hasMorph(witcher)) {
		    			if(MorphManager.morph.containsValue(MorphType.WITCH)) {
				MorphManager.removeMorph(witcher, false);
				witcher.playSound(witcher.getLocation(), Sound.ZOMBIE_UNFECT ,1, 2);
				witcher.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION , 10 , 1));
		    			}
		    		}
		    	}
		    }
		}, 20 * Configs.witchDuration);
		}
		
			MorphManager.addMorph(witcher, MorphType.WITCH);
	    	}
		
	}
		
		 @EventHandler
		  public void invInteract(InventoryClickEvent e)
		  {
		    if ((witch.contains(e.getWhoClicked()))) {
		      e.setCancelled(true);
		      e.getWhoClicked().getOpenInventory().close();
		    }
		  }
		 
			@EventHandler
			public void OnplayerInteract(PlayerInteractEvent e) {
				if ((witch.contains(e.getPlayer()))) {
					e.setCancelled(true);
				}
			}
		 
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
				if (event.getType() == UpdateType.SEC) {
					for(Player victim : witched) {
						victim.damage(0);
						UtilParticle.sendParticleToLocation(victim.getLocation().add(0 , 1 , 0), Particle.MOB_SPELL, 0.2f, 0.1f, 0.2f, 0.1f, 2);
					}
				
				}
				
				if (event.getType() == UpdateType.TICK) {
					for (Player witcher : witch) {
						UtilParticle.sendParticleToLocation(witcher.getLocation(), Particle.WITCH_MAGIC, 0.5f, 0.5f, 0.5f, 0.1f, 2);
						UtilParticle.sendParticleToLocation(witcher.getLocation(), Particle.MOB_SPELL, 0.5f, 0.5f, 0.5f, 0f, 1);
						for(Entity e : witcher.getNearbyEntities(3, 3, 3)) {
							if(e instanceof Player) {
								Player p = (Player) e;
								if(!witch.contains(p)) {
									playAnimationToRemove(p);
								}
							}
						}
					}
				}
				}
		
}


