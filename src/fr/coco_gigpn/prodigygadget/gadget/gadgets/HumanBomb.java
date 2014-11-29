package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
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
import fr.coco_gigpn.prodigygadget.util.UtilFlash;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilRandoms;

public class HumanBomb implements Listener {
	
	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	  private HashMap<Player, ArrayList<Entity>> item = new HashMap<Player,  ArrayList<Entity>>();
	
	public void playHumanBomb(final Player p) {
		
		UtilFlash.addRed(p);
		item.put(p, new ArrayList<Entity>());
		
		final int i = Bukkit.getScheduler()
				.runTaskTimer(Main.schedule, new Runnable() {
					double range = 6D;
					public void run() {
						
						for (int i = 0; i < 20; i++) {
							Location location = p.getLocation();
							if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
								 return;
							 } else {
							location.add(UtilRandoms.getRandomCircleVector().multiply(UtilRandoms.random.nextDouble() * 0.6d));
							location.add(0, UtilRandoms.random.nextFloat() * 2, 0);
							UtilParticle.sendParticleToLocation(location, Particle.RED_DUST , 0, 0, 0, 0, 1);
							 }
						}
						
						for(Entity entity : p.getNearbyEntities(range, range, range)) {
							if (entity instanceof Player) {
									((Player) entity).playSound(entity.getLocation(), Sound.BURP,1 , 1);
							}
						}
						
					}
				}, 10L, 10L).getTaskId();
		
		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			double range = 6D;
			public void run() {
				Bukkit.getServer().getScheduler().cancelTask(i);
				UtilFlash.removeRed(p);
				
				for(Entity entity : p.getNearbyEntities(range, range, range)) {
					
					if (entity instanceof Player) {
						
						if (! entity.equals(p)) {
							
							entity.setVelocity(UtilMath.calculateVelocity(p ,entity));
							((Player) entity).playSound(entity.getLocation(), Sound.EXPLODE,1 , 1);
							
						}
					}
				}
				
				if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
			
				UtilParticle.sendParticleToLocation(p.getLocation(),Particle.LARGE_EXPLODE, 0f, 0f, 0f, 1f, 1);
				UtilParticle.sendParticleToLocation(p.getLocation(),Particle.LAVA, 0, 0, 0, 0, 2);
				 }
				Location l = p.getLocation().add(0 , 2.5f , 0);
				
				for (int i = 0; i < 6; i++) {
					
					double x = UtilMath.randomRange(-0.2f, 0.2f);
					double y = UtilMath.randomRange(-0.2f, 0.2f);
					double z = UtilMath.randomRange(-0.2f, 0.2f);
				 EntityItem ei = new EntityItem(
					      ((CraftWorld)l.getWorld()).getHandle(),
					      l.getX(),
					      l.getY(),
					      l.getZ(),
					      CraftItemStack.asNMSCopy(new ItemStack(Material.REDSTONE))) {
					      public boolean a(EntityItem entityitem) {
					        return false;
					      }
					    };
					    
					    ((Item)ei.getBukkitEntity()).setVelocity(new Vector(x, y, z));
					    ((Item) ei.getBukkitEntity()).setMetadata("unpickable", new FixedMetadataValue(
								Main.schedule	, "unpickable"));
					    ei.pickupDelay = 20;
					    ((CraftWorld)l.getWorld()).getHandle().addEntity(ei);
					    ((ArrayList<Entity>)item.get(p)).add(ei.getBukkitEntity());
				

				}
				
				for (int i = 0; i < 12; i++) {
					
					double x = UtilMath.randomRange(-0.1f, 1f);
					double y = UtilMath.randomRange(0.3f, 0.4f);
					double z = UtilMath.randomRange(-0.9f, 0.4f);
				
							
					 EntityItem ei = new EntityItem(
						      ((CraftWorld)l.getWorld()).getHandle(),
						      l.getX(),
						      l.getY(),
						      l.getZ(),
						      CraftItemStack.asNMSCopy(new ItemStack(Material.REDSTONE_BLOCK))) {
						      public boolean a(EntityItem entityitem) {
						        return false;
						      }
						    };
						    
						    ((Item)ei.getBukkitEntity()).setVelocity(new Vector(x, y, z));
						    ((Item) ei.getBukkitEntity()).setMetadata("unpickable", new FixedMetadataValue(
									Main.schedule	, "unpickable"));
						    ei.pickupDelay = 20;
						    ((CraftWorld)l.getWorld()).getHandle().addEntity(ei);
						    ((ArrayList<Entity>)item.get(p)).add(ei.getBukkitEntity());
					

					}
				
				
				
			
			}
		}, 200L);
		
		
		Bukkit.getServer().getScheduler()
		.runTaskLater(Main.schedule, new Runnable() {
			public void run() {
				if (item.containsKey(p)) {
			    	for(Entity entity : item.get(p)) {
						
							Location cLoc = entity.getLocation();
							UtilParticle.sendParticleToLocation(cLoc,Particle.LAVA, 0, 0, 0, 0.2f, 2);
								entity.remove();
						}
								
						}
				GadgetManager.removeGadget(p);		
						
			}
		}, 250L);
		
		if (!GadgetManager.hasGadget(p)){
	    	GadgetManager.addGadget(p, GadgetType.HUMANBOMB);
	    	}
	}
	
	 public static void activate(Player p){
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.TNT, 1, Messages.humanbombName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.TNT, 1, Messages.humanbombName.replace("&", "§")));
			}
		}
		

	  
	  @SuppressWarnings("deprecation")
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.TNT)) {
	      return;
	    }
	    ItemStack humanbomb = new ItemStack(Material.TNT);
	    UtilItem.setName(humanbomb, Messages.humanbombName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(humanbomb.getItemMeta())) {
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
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.humanbombName.replace("&", "§")).replace("%time", time));
	    	return;
	    }

	    _time.put(player, Configs.humanbombCooldown);
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
	    
	playHumanBomb(player);
	    }
	    
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }

}
