package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilBlock.RiderType;
import fr.coco_gigpn.prodigygadget.util.UtilEnt;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class GravityStation implements Listener {
	
	 private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	 public static ArrayList<Entity> activateGravity = new ArrayList<>();
	  
	  private HashMap<Player, Location> stationLocation = new HashMap<Player, Location>();
	
	public void constructGravityStation(final Player p ,Location l) {
		
		if(l.getBlock().isEmpty() 
				  &&  new Location(l.getWorld() , l.getX(), l.getY() + 1 , l.getZ()).getBlock().isEmpty() 
				  && new Location(l.getWorld() , l.getX(), l.getY() + 2 , l.getZ()).getBlock().isEmpty()) {
			
			 _time.put(p, Configs.rocketCooldown);
			    _cdRunnable.put(p, new BukkitRunnable() {
		             public void run() {
		             	_time.put(p, _time.get(p) - 0.1D);
		                     if (_time.get(p) < 0.01) {
		                     		_time.remove(p);
		                     		_cdRunnable.remove(p);
		                             cancel();
		                     }
		             }
		     });
			    _cdRunnable.get(p).runTaskTimer(Main.schedule, 2, 2);

			    _active.put(p, Long.valueOf(System.currentTimeMillis()));
		
		UtilBlock.setBlockToRestore(l.getBlock(), 101, (byte) 0, 20, RiderType.DarkRider);
		UtilBlock.setBlockToRestore(l.add(0 , 1 , 0).getBlock(), 95, (byte) 11, 20, RiderType.GhostEffect);
		stationLocation.put(p, new Location(l.getWorld() , l.getX() + 0.5f , l.getY() + 0.5f , l.getZ() + 0.5));
		UtilBlock.setBlockToRestore(l.add(0 , 1 , 0).getBlock(), 101, (byte) 0, 20, RiderType.DarkRider);
		
		if (!GadgetManager.hasGadget(p)) {
			GadgetManager.addGadget(p, GadgetType.GRAVITYSTATION);
		}
		
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        stationLocation.remove(p);
		        GadgetManager.removeGadget(p);
		    }
		}, 20 * 20);

		} else {
			  p.sendMessage(Messages.youMustClearTheArea.replace("&", "§"));
		  }
	}
	
	public void activateGravityToRemove(final Entity p) {
		if(!activateGravity.contains(p)) {
			activateGravity.add(p);
			Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
			    public void run() {
			        activateGravity.remove(p);
			    }
			}, 20 * 5);
		}
	}
	
	 public static void activate(Player p){
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.ENDER_PORTAL_FRAME, 1, Messages.gravitystationName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.WEB, 1, Messages.gravitystationName.replace("&", "§")));
			}
		}
	 
	 
		@EventHandler
		public void ParticleAura(UpdateEvent event) {
			if (event.getType() == UpdateType.SEC) {
		

				for(Location l : stationLocation.values()) {
					for(Entity p : UtilEnt.getNearbyEntities(l, 6)) {
				activateGravityToRemove(p);
					}
				}
				
			}
			
			if (event.getType() == UpdateType.TICK) {
				for(Location l : stationLocation.values()) {
					UtilParticle.sendParticleToLocation(l, Particle.SPELL, 0.3f, 0.3f, 0.3f, 0.05f, 1);
					UtilParticle.sendParticleToLocation(l, Particle.RED_DUST, 0.3f, 0.3f, 0.3f, 0, 2);
			}
			}
			
	
}
		

	  
	  @SuppressWarnings("deprecation")
	@EventHandler
	  public void Activate(final PlayerInteractEvent event)
	  {
	    final Player player = event.getPlayer();
	    if ((event.getAction() == Action.RIGHT_CLICK_AIR) && (event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
	      return;
	    }
	    if (UtilBlock.usable(event.getClickedBlock())) {
	      return;
	    }
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.ENDER_PORTAL_FRAME)) {
	      return;
	    }
	    ItemStack gravitystation = new ItemStack(Material.ENDER_PORTAL_FRAME);
	    UtilItem.setName(gravitystation, Messages.gravitystationName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(gravitystation.getItemMeta())) {
	    	return;
	    }
	    
	    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
	    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
	    } else {
	    	
	    	event.setCancelled(true);
	    	player.updateInventory();
	    if(_time.containsKey(player)){
	    	double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.gravitystationName.replace("&", "§")).replace("%time", time));
	    	return;
	    }
	    
	    Location l = event.getClickedBlock().getLocation();
	    l.add(0 , 1 , 0);
	    constructGravityStation(player, l);
	    event.setCancelled(true);
	    player.updateInventory();
	
	    }
	    
	  }
	  
	  
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }

}
