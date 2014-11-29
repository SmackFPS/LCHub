package fr.coco_gigpn.prodigygadget.gadget.gadgets;


import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.morph.MorphManager;
import fr.coco_gigpn.prodigygadget.updater.UpdateType;
import fr.coco_gigpn.prodigygadget.updater.event.UpdateEvent;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilBlock.RiderType;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilInventory;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

	public class Rocket implements Listener {
		
		private static HashMap<Player, Long> _active = new HashMap<Player, Long>();
		  private static HashMap<Player, Double> _time = new HashMap<Player, Double>();
		  static HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
		  public static HashMap<Player, Location> stationnaire = new HashMap<Player, Location>();
		  public static HashMap<Player, Entity> effect = new HashMap<Player, Entity>();
		  public static HashMap<Player, Entity> arrowToRemove = new HashMap<Player, Entity>();
		  public static HashMap<Player, ArrayList<Entity>> fallingblocktoremove = new HashMap<Player,  ArrayList<Entity>>();
		  
			 @EventHandler
			 public void Onplayerquit(PlayerQuitEvent event) {
				 Player player = event.getPlayer();
				 if (stationnaire.containsKey(player)) {
					 UtilItem.clearArmor(player);
						UtilInventory.restoreArmor(player);
						UtilInventory.forsakeArmor(player);
					 stationnaire.remove(player);
					 if(player.isInsideVehicle()) {
						 player.eject();
					 }
					 arrowToRemove.get(player).remove();
				 }
				 if (effect.containsKey(player)) {
					 UtilItem.clearArmor(player);
						UtilInventory.restoreArmor(player);
						UtilInventory.forsakeArmor(player);
					 effect.remove(player);
					 if(player.isInsideVehicle()) {
						 player.eject();
					 }
					 for(Entity entity : fallingblocktoremove.get(player)) {
						 entity.remove();
						 
					 }
				 }
				 
			 }
			 
			 @EventHandler
			  public void onLeave(PlayerKickEvent e)
			  {
				 Player player = e.getPlayer();
				 if (stationnaire.containsKey(player)) {
					 UtilItem.clearArmor(player);
						UtilInventory.restoreArmor(player);
						UtilInventory.forsakeArmor(player);
					 stationnaire.remove(player);
					 if(player.isInsideVehicle()) {
						 player.eject();
					 }
					 arrowToRemove.get(player).remove();
				 }
				 if (effect.containsKey(player)) {
					 UtilItem.clearArmor(player);
						UtilInventory.restoreArmor(player);
						UtilInventory.forsakeArmor(player);
					 effect.remove(player);
					 if(player.isInsideVehicle()) {
						 player.eject();
					 }
					 for(Entity entity : fallingblocktoremove.get(player)) {
						 entity.remove();
						 
					 }
				 }
			  }
		  
		  @EventHandler
			public void ghostDamage(EntityDamageEvent event) {
				Entity e = event.getEntity();

				if (e instanceof FallingBlock) {

					if (e.hasMetadata("nodamage")) {
						event.setCancelled(true);
					}

				}

			}
		  
		  @EventHandler(priority = EventPriority.NORMAL)
		  public void EntityChangeBlockEvent (final EntityChangeBlockEvent event) {
			  Entity e = event.getEntity();
				if (e instanceof FallingBlock) {

					if (e.hasMetadata("nodamage")) {
						event.setCancelled(true);
					}

				}
		  }
		  
		  @SuppressWarnings("deprecation")
		public static void constructFirework(final Player p, Location l) {
			  
			  if(!MorphManager.hasMorph(p)) {
			  
			  UtilInventory.saveArmor(p);
				p.getInventory().setHelmet(UtilItem.create(Material.STAINED_GLASS ,1 , (byte) 11 , "Scaphandre")); 
				p.getInventory().setChestplate(UtilInventory.getColorArmor(Material.LEATHER_CHESTPLATE, Color.WHITE));
				p.getInventory().setLeggings(UtilInventory.getColorArmor(Material.LEATHER_LEGGINGS, Color.WHITE));
				p.getInventory().setBoots(UtilInventory.getColorArmor(Material.LEATHER_BOOTS, Color.WHITE));
				p.updateInventory();
			  
			  Block b1 = new Location(p.getWorld() , l.getX() + 1, l.getY() , l.getZ() ).getBlock();
			  Block b2 = new Location(p.getWorld() , l.getX() , l.getY() , l.getZ() + 1).getBlock();
			  Block b3 = new Location(p.getWorld() , l.getX() - 1, l.getY() , l.getZ()).getBlock();
			  Block b4 = new Location(p.getWorld() , l.getX() , l.getY() , l.getZ() - 1).getBlock();
			  Block b5 = new Location(p.getWorld() , l.getX() + 1, l.getY() + 1, l.getZ() ).getBlock();
			  Block b6 = new Location(p.getWorld() , l.getX() , l.getY() + 1, l.getZ() + 1).getBlock();
			  Block b7 = new Location(p.getWorld() , l.getX() - 1, l.getY() + 1, l.getZ()).getBlock();
			  Block b8 = new Location(p.getWorld() , l.getX() , l.getY() + 1 , l.getZ() - 1).getBlock();
			  Block b9 = new Location(p.getWorld() , l.getX() , l.getY() + 1 , l.getZ() ).getBlock();
			  Block b10 = new Location(p.getWorld() , l.getX() , l.getY() + 2, l.getZ()).getBlock();
			  Block b11 = new Location(p.getWorld() , l.getX(), l.getY() + 3, l.getZ()).getBlock();
			  
			  if(b1.isEmpty() 
					  && b2.isEmpty() 
					  && b3.isEmpty() 
					  && b4.isEmpty() 
					  && b5.isEmpty() 
					  && b6.isEmpty() 
					  && b7.isEmpty() 
					  && b8.isEmpty() 
					  && b9.isEmpty() 
					  && b10.isEmpty() 
					  && b11.isEmpty() ) {
				  
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
			  
			  stationnaire.put(p, l);
			 
			  
			
			  UtilBlock.setBlockToRestore(b1 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b2 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b3 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b4 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  
			  UtilBlock.setBlockToRestore(b5 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b6 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b7 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b8 ,85, (byte) 0, 10, RiderType.GhostEffect);
			  
			  
			  UtilBlock.setBlockToRestore(b9 ,42, (byte) 0, 10, RiderType.GhostEffect);
			 
			  UtilBlock.setBlockToRestore(b10 ,42, (byte) 0, 10, RiderType.GhostEffect);
			  
			  UtilBlock.setBlockToRestore(b11 ,76, (byte) 0, 9, RiderType.GhostEffect);
			  
			
			  final Arrow arrow = (Arrow) p.getWorld().spawnEntity(b11.getLocation().add(0.5, 1 , 0.5), EntityType.ARROW);
			  arrow.setPassenger(p);
			  arrowToRemove.put(p, arrow);
			  
			  Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
					
				    public void run() {
				    	if(stationnaire.containsKey(p) || p.isValid()) {
				      arrow.remove();
				      arrowToRemove.remove(p);
				    	
				      playFirework(p);
				    	}

				    }
				}, 20 * 10);
			  
			  }  else {
				  p.sendMessage(Messages.youMustClearTheArea.replace("&", "§"));
			  }
			  }
		  }
		  
		  
		  
		  @SuppressWarnings("deprecation")
		public static void playFirework(final Player p) {
			  if(!MorphManager.hasMorph(p)) {
			  
			  fallingblocktoremove.put(p, new ArrayList<Entity>());
			  
			  Location l = stationnaire.get(p);
			  
			  Location b11 = new Location(p.getWorld() , l.getX() + 1, l.getY() + 1 , l.getZ());
			  Location b12 = new Location(p.getWorld() , l.getX() , l.getY() + 1, l.getZ() + 1);
			  Location b13 = new Location(p.getWorld() , l.getX() - 1, l.getY() + 1, l.getZ());
			  Location b14 = new Location(p.getWorld() , l.getX() , l.getY() + 1, l.getZ() - 1);
			 
			  Location b1 = new Location(p.getWorld() , l.getX() + 1, l.getY() , l.getZ());
			  Location b2 = new Location(p.getWorld() , l.getX() , l.getY(), l.getZ() + 1);
			  Location b3 = new Location(p.getWorld() , l.getX() - 1, l.getY(), l.getZ());
			  Location b4 = new Location(p.getWorld() , l.getX() , l.getY(), l.getZ() - 1);
			  Location b5 = new Location(p.getWorld() , l.getX() + 1, l.getY() + 1, l.getZ());
			  Location b6 = new Location(p.getWorld() , l.getX() , l.getY() + 1, l.getZ() + 1);
			  Location b7 = new Location(p.getWorld() , l.getX() - 1, l.getY() + 1, l.getZ());
			  Location b8 = new Location(p.getWorld() , l.getX() , l.getY() + 1 , l.getZ() - 1);
			  Location b9 = new Location(p.getWorld() , l.getX() , l.getY() + 1 , l.getZ());
			  Location b10 = new Location(p.getWorld() , l.getX() , l.getY() + 2, l.getZ());
			  
			  final FallingBlock fb11 = p.getWorld().spawnFallingBlock(b11, Material.FENCE, (byte) 0);
			  fb11.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb12 = p.getWorld().spawnFallingBlock(b12, Material.FENCE, (byte) 0);
			  fb12.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb13 = p.getWorld().spawnFallingBlock(b13, Material.FENCE, (byte) 0);
			  fb13.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb14 = p.getWorld().spawnFallingBlock(b14, Material.FENCE, (byte) 0);
			  fb14.setVelocity(new Vector(0 , 7 , 0));
	
			
			  final FallingBlock fb1 = p.getWorld().spawnFallingBlock(b1, Material.FENCE, (byte) 0);
			  fb1.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb2 = p.getWorld().spawnFallingBlock(b2, Material.FENCE, (byte) 0);
			  fb2.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb3 = p.getWorld().spawnFallingBlock(b3, Material.FENCE, (byte) 0);
			  fb3.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb4 = p.getWorld().spawnFallingBlock(b4, Material.FENCE, (byte) 0);
			  fb4.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb5 = p.getWorld().spawnFallingBlock(b5, Material.FENCE, (byte) 0);
			  fb5.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb6 = p.getWorld().spawnFallingBlock(b6, Material.FENCE, (byte) 0);
			  fb6.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb7 = p.getWorld().spawnFallingBlock(b7, Material.FENCE, (byte) 0);
			  fb7.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb8 = p.getWorld().spawnFallingBlock(b8, Material.FENCE, (byte) 0);
			  fb8.setVelocity(new Vector(0 , 7 , 0));
			  final FallingBlock fb9 = p.getWorld().spawnFallingBlock(b9, Material.IRON_BLOCK, (byte) 0);
			  fb9.setVelocity(new Vector(0 , 7 , 0));
			  effect.put(p, fb9);
			  final FallingBlock fb10 = p.getWorld().spawnFallingBlock(b10, Material.IRON_BLOCK, (byte) 0);
			  fb10.setVelocity(new Vector(0 , 7 , 0));
			  
			  
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb11);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb12);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb13);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb14);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb1);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb2);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb3);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb4);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb5);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb6);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb7);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb8);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb9);
			  ((ArrayList<Entity>)fallingblocktoremove.get(p)).add(fb10);
			  
			  
			  fb11.setDropItem(false);
			  fb12.setDropItem(false);
			  fb13.setDropItem(false);
			  fb14.setDropItem(false);
			  fb1.setDropItem(false);
			  fb2.setDropItem(false);
			  fb3.setDropItem(false);
			  fb4.setDropItem(false);
			  fb5.setDropItem(false);
			  fb6.setDropItem(false);
			  fb7.setDropItem(false);
			  fb8.setDropItem(false);
			  fb9.setDropItem(false);
			  fb10.setDropItem(false);
			  
			  fb11.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb12.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb13.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb14.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));

			  fb1.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb2.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb3.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb4.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb5.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb6.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb7.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb8.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb9.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			  fb10.setMetadata("nodamage", new FixedMetadataValue(
						Main.schedule	, "nodamage"));
			
			  stationnaire.remove(p);
			  if (p.isValid() || stationnaire.containsKey(p)) {
			  fb10.setPassenger(p);
			  }
			  
				Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
			
				    public void run() {
				    	 UtilItem.clearArmor(p);
				 		UtilInventory.restoreArmor(p);
				 		UtilInventory.forsakeArmor(p);
				 
				    	effect.remove(p);
				    	fb1.remove();
					      fb2.remove();
					      fb3.remove();
					      fb4.remove();
					      fb11.remove();
					      fb12.remove();
					      fb13.remove();
					      fb14.remove();
					   
				      fb5.remove();
				      fb6.remove();
				      fb7.remove();
				      fb8.remove();
				      fb9.remove();
				      fb10.remove();
				      GadgetManager.removeGadget(p);
				    }
				}, 20 * 8);
				
				if (!GadgetManager.hasGadget(p)){
			    	GadgetManager.addGadget(p, GadgetType.GRENADE);
			    	}
			  }
			  
			}
		  
	
			@EventHandler
			public void ParticleAura(UpdateEvent event) {
			
				if (event.getType() == UpdateType.TICK) {

					if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
						return;
					} else {
						
						for(Location l : stationnaire.values()) {
							UtilParticle.sendParticleToLocation(l, Particle.CLOUD, 0.3f, -0.2f, 0.3f, 0.1f, 10);
						}
						for(Entity e : effect.values()) {
							UtilParticle.sendParticleToLocation(e.getLocation(), Particle.LARGE_SMOKE, 0.3f, -0.2f, 0.3f, 0.1f, 4);
						}
						
					}
				}
				
			

			}
		
		
		 public static void activate(Player p){
			 p.getOpenInventory().close();
				if (Configs.gadgetEnabledPosition == true) {
				p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
				p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.REDSTONE_TORCH_ON, 1, Messages.rocketName.replace("&", "§")));
				} else {
					p.getInventory().addItem(UtilItem.create(Material.REDSTONE_TORCH_ON, 1, Messages.rocketName.replace("&", "§")));
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
		    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.REDSTONE_TORCH_ON)) {
		      return;
		    }
		    ItemStack firework = new ItemStack(Material.REDSTONE_TORCH_ON);
		    UtilItem.setName(firework , Messages.rocketName.replace("&", "§"));
		    if (!player.getItemInHand().getItemMeta().equals(firework .getItemMeta())) {
		    	return;
		    }
		    
		    event.setCancelled(true);
		    player.updateInventory();
		    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
		    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
		    } else {
		    if(_time.containsKey(player)){
		    	double t = arrondi(_time.get(player), 1);
		    	String time = Double.toString(t);
		    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.rocketName.replace("&", "§")).replace("%time", time));
		    	return;
		    }
		    
		   
		    
		    constructFirework(player, player.getLocation());
		
		    }
		    
		  }
		  
		  public static double arrondi(double A, int B) {
			  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
		  }

}
