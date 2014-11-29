package fr.coco_gigpn.prodigygadget.gadget.gadgets;


import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

	public class Grenade implements Listener {
		
		private HashMap<Player, Long> _active = new HashMap<Player, Long>();
		  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
		  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
		  
		  public void playGrenade(final Player p) {
				final Entity item = p.getWorld().dropItemNaturally(p.getLocation().add(0 , 1.5f , 0), new ItemStack(Material.ENCHANTED_BOOK, 1));
				item.setVelocity(p.getEyeLocation().getDirection().multiply(1));
				item.setMetadata("unpickable", new FixedMetadataValue(
						Main.schedule	, "unpickable"));
				
				final int id = Bukkit.getServer().getScheduler().runTaskTimer(Main.schedule, new Runnable() {
					int range = 6;
					float step = 4;
				    public void run() {
				    	if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
							 return;
						 } else {
				    	UtilParticle.sendParticleToLocation(item.getLocation(), Particle.RED_DUST, 0.5f, 0.5f, 0.5f, 0, 3);
				    	 }
				     for(Entity entity : item.getNearbyEntities(range, range, range)) {
							if (entity instanceof Player) {
									((Player) entity).playSound(entity.getLocation(), Sound.ITEM_PICKUP , 1 , step);
							}
						}     
		                  
				     step -= 0.5;
					
				    }
				}, 5L, 5L).getTaskId();
				
				Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
					int range = 10;
				    public void run() {
				        Bukkit.getServer().getScheduler().cancelTask(id);
				        item.remove();
				        if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
							 return;
						 } else {
				        UtilParticle.sendParticleToLocation(item.getLocation(), Particle.EXPLODE, 0.5f, 0.5f, 0.5f, 0.3f, 30);
				        UtilParticle.sendParticleToLocation(item.getLocation(), Particle.HUGE_EXPLOSION, 0.5f, 0.5f, 0.5f, 0.3f, 5);
				        }
				        for(Entity entity : item.getNearbyEntities(range, range, range)) {
							if (entity instanceof Player) {
									((Player) entity).playSound(entity.getLocation(), Sound.EXPLODE , 1 , 1);
									
										entity.setVelocity(UtilMath.calculateVelocity(item.getLocation(), entity));
									
							}
						}     
				        GadgetManager.removeGadget(p);
				    }
				}, 20 * 4);
				
				if (!GadgetManager.hasGadget(p)){
			    	GadgetManager.addGadget(p, GadgetType.GRENADE);
			    	}
				
			}
		
		
		 public static void activate(Player p){
			 p.getOpenInventory().close();
				if (Configs.gadgetEnabledPosition == true) {
				p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
				p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.ENCHANTED_BOOK, 1, Messages.grenadeName.replace("&", "§")));
				} else {
					p.getInventory().addItem(UtilItem.create(Material.ENCHANTED_BOOK, 1, Messages.grenadeName.replace("&", "§")));
				}
			}
			

		  
		  @EventHandler
		  public void Activate(final PlayerInteractEvent event)
		  {
		    final Player player = event.getPlayer();
		    if ((event.getAction() != Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
		      return;
		    }
		    if (UtilBlock.usable(event.getClickedBlock())) {
		      return;
		    }
		    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.ENCHANTED_BOOK)) {
		      return;
		    }
		    ItemStack grenade = new ItemStack(Material.ENCHANTED_BOOK);
		    UtilItem.setName(grenade , Messages.grenadeName.replace("&", "§"));
		    if (!player.getItemInHand().getItemMeta().equals(grenade .getItemMeta())) {
		    	return;
		    }
		    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
		    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		    } else {
		    	event.setCancelled(true);
		    if(_time.containsKey(player)){
		    	double t = arrondi(_time.get(player), 1);
		    	String time = Double.toString(t);
		    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.grenadeName.replace("&", "§")).replace("%time", time));
		    	return;
		    }
		    
		    _time.put(player, Configs.grenadeCooldown);
		    _cdRunnable.put(player, new BukkitRunnable() {
	             public void run() {
	             	_time.put(player, _time.get(player) - 0.1D);
	                     if (_time.get(player) < 0.01) {
	                     		_time.remove(player);
	                     		_cdRunnable.remove(player);
	                             cancel();
	                     }
	             }
	     });
		    _cdRunnable.get(player).runTaskTimer(Main.schedule, 2, 2);

		    this._active.put(player, Long.valueOf(System.currentTimeMillis()));
		    
		playGrenade(player);
		
		    }
		    
		  }
		  
		  public static double arrondi(double A, int B) {
			  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
		  }

}
