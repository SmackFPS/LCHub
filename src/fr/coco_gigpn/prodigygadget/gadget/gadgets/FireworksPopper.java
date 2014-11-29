package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilFirework;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;


public class FireworksPopper implements Listener {
	
	  
	  private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	
	  
	  @EventHandler
	    public void onCreatureSpawn(CreatureSpawnEvent event)
	    {
	        if (event.getSpawnReason() == SpawnReason.EGG)
	        {
	        		 event.setCancelled(true);
	        }
	    }
	  
		@EventHandler
		public void EggFirework(ProjectileHitEvent event) {

			if (event.getEntity().getType() == EntityType.EGG) {

				Entity e = event.getEntity();

				if (e.hasMetadata("egg")) {
					Location l = e.getLocation();
					if (UtilLag.ServerisLag() && Configs.stopFireworkOnLag == true) {
						return;
					} else {
								UtilFirework.launchRandomFirework(l);
									 
					}
				}

			}
		}
	  
		public static void playFireworksPopper(final Player p ,final World w) {
		
	  
		final int i = Bukkit.getScheduler().runTaskTimer(Main.schedule, new Runnable() {
							public void run() {
								Location l = p.getLocation();
								l.add(0 , 2.5f , 0);
								Entity item = w.spawnEntity(l, EntityType.EGG);

								item.setMetadata("egg", new FixedMetadataValue(Main.schedule, "egg"));
								
								
							item.setVelocity(new Vector(UtilMath.randomRange(- 0.15d, 0.15d) , 0.5f , UtilMath.randomRange(- 0.15d, 0.15d)));
						
							
							}
						}, 5L, 5L).getTaskId();
		
		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			public void run() {
				Bukkit.getServer().getScheduler()
						.cancelTask(i);
				GadgetManager.removeGadget(p);
				
			}
		}, 200L);
		
		
		if (!GadgetManager.hasGadget(p)){
	    	GadgetManager.addGadget(p, GadgetType.FIREWORKPOPPER);
	    	}
		
		}
		
		 public static void activate(Player p){
			 p.getOpenInventory().close();
				if (Configs.gadgetEnabledPosition == true) {
				p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
				p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.FIREWORK, 1, Messages.fireworkpopperName.replace("&", "§")));
				} else {
					p.getInventory().addItem(UtilItem.create(Material.FIREWORK, 1, Messages.fireworkpopperName.replace("&", "§")));
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
		    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.FIREWORK)) {
		      return;
		    }
	
		    ItemStack fireworkpopper = new ItemStack(Material.FIREWORK);
		    UtilItem.setName(fireworkpopper, Messages.fireworkpopperName.replace("&", "§"));
		    if (!player.getItemInHand().getItemMeta().equals(fireworkpopper.getItemMeta())) {
		    	return;
		    }
		    
		    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
		    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		    } else {
		    	
		    	event.setCancelled(true);
		    if(_time.containsKey(player)){
		    	double t = arrondi(_time.get(player), 1);
		    	String time = Double.toString(t);
		    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.fireworkpopperName.replace("&", "§")).replace("%time", time));
		    	return;
		    }
		    _time.put(player, Configs.fireworkpopperCooldown);
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
		    
		playFireworksPopper(player, player.getWorld());
		
		    }
		    
		  }
		  
		  public static double arrondi(double A, int B) {
			  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
		  }
	  
}
