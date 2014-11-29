package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilAction;
import fr.coco_gigpn.prodigygadget.util.UtilAlg;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilCrack;
import fr.coco_gigpn.prodigygadget.util.UtilEnt;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilServer;

public class AxeLauncher implements Listener {
	  
	
	  private static HashMap<Player, Entity> axe = new HashMap<Player,  Entity>();
	  private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	  
	  
	  @EventHandler
		 public void Onplayerquit(PlayerQuitEvent event) {
			 Player player = event.getPlayer();
			if (axe.containsKey(player)) {
				axe.get(player).remove();
				axe.remove(player);
				
			}
		 }
		 
		 @EventHandler
		  public void onLeave(PlayerKickEvent e)
		  {
			 Player player = e.getPlayer();
			 if (axe.containsKey(player)) {
				 axe.get(player).remove();
					axe.remove(player);
				
				}
		  }

	public static void launchAxe(final Player p) {
		
		
		World w = p.getWorld();
		Location l = p.getLocation();
		Location loc = p.getEyeLocation().toVector()
				.add(l.getDirection().multiply(1))
				.toLocation(w, l.getYaw(), l.getPitch());
		final Entity e = w.dropItem(loc, UtilItem.create(Material.IRON_AXE , 1 , (byte) 0 , Messages.axelauncherName.replace("&", "§")));
	
		e.setMetadata("unpickable", new FixedMetadataValue(
				Main.schedule	, "unpickable"));
		axe.put(p, e);

		e.setVelocity(loc.getDirection().multiply(2f));
		p.getInventory().remove(UtilItem.create(Material.IRON_AXE , 1 , (byte) 0 , Messages.axelauncherName.replace("&", "§")));
		Bukkit.getServer().getScheduler()
				.runTaskLater(Main.schedule, new Runnable() {

					@SuppressWarnings("deprecation")
					public void run() {
						if(axe.containsKey(p)) {
							if (p.isValid()) {
								if (Configs.gadgetEnabledPosition == true) {
									p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
									p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.IRON_AXE, 1, Messages.axelauncherName.replace("&", "§")));
									} else {
										p.getInventory().addItem(UtilItem.create(Material.IRON_AXE, 1, (byte) 1, Messages.axelauncherName.replace("&", "§")));
									}
						p.updateInventory();
							}
						axe.remove(p);
						e.remove();
						}

					}
				}, 20 * 4);

	}

	@EventHandler
	public void ParticleAura(UpdateEvent event) {
		if (event.getType() == UpdateType.TICK) {
			
			for (Player cur : Bukkit.getOnlinePlayers())
		    {
		for(Player p : axe.keySet()) {
			for(Entity entity : axe.values()) {
				if(entity.isOnGround()) {
					entity.remove();
					if (Configs.gadgetEnabledPosition == true) {
						p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
						p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.IRON_AXE, 1, Messages.axelauncherName.replace("&", "§")));
						} else {
							p.getInventory().addItem(UtilItem.create(Material.IRON_AXE, 1, (byte) 1, Messages.axelauncherName.replace("&", "§")));
						}
					axe.remove(p);
					UtilCrack.displayIconCrack(entity.getLocation().add(0 , 0.3f , 0), 258, 0.3F, 0.3F, 0.3F, 0.1F, 10);
					entity.getWorld().playSound(entity.getLocation(), Sound.ITEM_BREAK, 1, 1);
					
				} else {
					UtilParticle.sendParticleToLocation(entity.getLocation(), Particle.CRIT, 0, 0, 0, 0, 1);
				}
				
			
			}
		}
		  
		              for (Player other : UtilServer.getPlayers())
		              {
		                if (!other.equals(cur))
		                {

		                	for(Player p : axe.keySet()) {
		            			for(Entity entity : axe.values()) {
		                    if (UtilEnt.hitBox(entity.getLocation(), other, 1.5D))
		                    {
		    

		                    	 UtilAction.velocity(other, UtilAlg.getTrajectory(cur, other), 0.4D, false, 0.0D, 0.2D, 10.0D, true);
		                    	 other.getLocation().getWorld().playEffect(other.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 152);
		                      other.damage(0);

		                     entity.getWorld().playSound(entity.getLocation(), Sound.ITEM_BREAK, 1.0F, 1.0F);
		                     
		                      axe.remove(p);
		                      entity.remove();
		                      if (Configs.gadgetEnabledPosition == true) {
		  						p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
		  						p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.IRON_AXE, 1, Messages.axelauncherName.replace("&", "§")));
		  						} else {
		  							p.getInventory().addItem(UtilItem.create(Material.IRON_AXE, 1, (byte) 1, Messages.axelauncherName.replace("&", "§")));
		  						}
		  			
		                      if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
		     					 return;
		     				 } else {
		     					UtilCrack.displayIconCrack(entity.getLocation().add(0 , 0.3f , 0), 258, 0.3F, 0.3F, 0.3F, 0.1F, 10);
		                      }
		                      
		                    	 } } } } } }
		}
	}
	
	 public static void activate(Player p){
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.IRON_AXE, 1, Messages.axelauncherName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.IRON_AXE, 1, (byte) 1, Messages.axelauncherName.replace("&", "§")));
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.IRON_AXE)) {
	      return;
	    }
	    ItemStack axelauncher = new ItemStack(Material.IRON_AXE);
	    UtilItem.setName(axelauncher , Messages.axelauncherName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(axelauncher .getItemMeta())) {
	    	return;
	    }
	    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
	    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
	    } else {
	    if(_time.containsKey(player)){
	    	double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.axelauncherName.replace("&", "§")).replace("%time", time));
	    	return;
	    }
	    
	    _time.put(player,Configs.axelauncherCooldown);
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
	    
	    
	   launchAxe(player);
	    
	
	    }
	    
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }
	
}
