package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilFirework;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilMath;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;

public class SlimeHat implements Listener {

	
	private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	private HashMap<Player, ArrayList<Slime>> slim = new HashMap<Player,  ArrayList<Slime>>();
	private HashMap<Player, ArrayList<Entity>> slimeball = new HashMap<Player,  ArrayList<Entity>>();
	private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	
	@EventHandler
    public void SlimeDamageEvent (EntityDamageEvent event) {
		Entity e = event.getEntity();
		
		if(!(e instanceof Slime)) return;
		
				if (e.hasMetadata("nodamage")) {
					event.setCancelled(true);
				}
		
	}
	
	    @EventHandler
		public void PlayerDamage(EntityDamageByEntityEvent event) {

		if (!(event.getEntityType() == EntityType.PLAYER)) return;
		if (!(event.getDamager().getType() == EntityType.SLIME)) return;
		Player player = (Player) event.getEntity();
		
		Slime damager = (Slime) event.getDamager();	
		
		if (slim.containsKey(player) && damager.hasMetadata("nodamage")) {

		event.setCancelled(true);
		
		}
		
		}
	



	public void playSlimeHat(final Player p) {
	
		slim.put(p, new ArrayList<Slime>());
		
		
		Slime s = p.getWorld().spawn(p.getLocation(), Slime.class);
		s.setSize(1);
		((ArrayList<Slime>)slim.get(p)).add(s);
		s.setMetadata("nodamage", new FixedMetadataValue(
				Main.schedule	, "nodamage"));
		Slime s2 = p.getWorld().spawn(p.getLocation(), Slime.class);
		s2.setSize(2);
		((ArrayList<Slime>)slim.get(p)).add(s2);
		s2.setMetadata("nodamage", new FixedMetadataValue(
				Main.schedule	, "nodamage"));
		Slime s3 = p.getWorld().spawn(p.getLocation(), Slime.class);
		s3.setSize(3);	
		((ArrayList<Slime>)slim.get(p)).add(s3);
		s3.setMetadata("nodamage", new FixedMetadataValue(
				Main.schedule	, "nodamage"));
		Slime s4 = p.getWorld().spawn(p.getLocation(), Slime.class);
		s4.setSize(4);
		((ArrayList<Slime>)slim.get(p)).add(s4);
		s4.setMetadata("nodamage", new FixedMetadataValue(
				Main.schedule	, "nodamage"));
		s.setPassenger(s2);
		s2.setPassenger(s3);
		s3.setPassenger(s4);
		p.setPassenger(s);
		
		slimeball.put(p, new ArrayList<Entity>());
		
		final int id = Bukkit.getServer().getScheduler().runTaskTimer(Main.schedule, new Runnable() {
		    public void run() {
		    	Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2.3f, p.getLocation().getZ());
		       for (int k =0 ; k < 6 ; k++) {
		        EntityItem ei = new EntityItem(
					      ((CraftWorld)loc.getWorld()).getHandle(),
					      loc.getX(),
					      loc.getY(),
					      loc.getZ(),
					      CraftItemStack.asNMSCopy(new ItemStack(Material.SLIME_BALL))) {
					      public boolean a(EntityItem entityitem) {
					        return false;
					      }
					    };
					    ei.pickupDelay = 20;
					    ((Item)ei.getBukkitEntity()).setVelocity(new Vector(UtilMath.randomRange(-0.5f, 0.5f), UtilMath.randomRange(0.2f, 0.5f), UtilMath.randomRange(-0.5f, 0.5f)));
					    ((CraftWorld)loc.getWorld()).getHandle().addEntity(ei);
					    ((Item) ei.getBukkitEntity()).setMetadata("slimeball", new FixedMetadataValue(
								Main.schedule	, "slimeball"));
					    ((ArrayList<Entity>)slimeball.get(p)).add(ei.getBukkitEntity());
		       }
		       if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
				        FireworkEffect effect = FireworkEffect.builder().flicker(true).trail(true).withColor(Color.GREEN).withFade(Color.LIME).with(Type.BURST).flicker(true).trail(true).build();
				        UtilFirework.spawn(loc, effect,p);
		       }
				  
		    }
		}, 10L, 10L).getTaskId();
		
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        Bukkit.getServer().getScheduler().cancelTask(id);

		        if (slim.containsKey(p)) {
		    	for(Entity entity : slim.get(p)) {
							entity.remove();
						
					}
		    	slim.remove(p);
		        }
		    
		    }
		}, 20 * 10);
		
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		
		    	 if (slimeball.containsKey(p)) {
		    	for(Entity entity : slimeball.get(p)) {
				
			
						Location cLoc = entity.getLocation();
						if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
							 return;
						 } else {
						UtilParticle.sendParticleToLocation(cLoc, Particle.SLIME, 0, 0, 0, 0.2f, 2);
						 }
						entity.remove();
					
						
						
					}
		    	slimeball.remove(p);
		    	GadgetManager.removeGadget(p);
		    	 }
		    }
		}, 20 * 13);
		if (!GadgetManager.hasGadget(p)){
	    	GadgetManager.addGadget(p, GadgetType.DISCOBALL);
	    	}
		
	}

	 public static void activate(Player p) {
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.SLIME_BALL, 1, Messages.slimehatName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.SLIME_BALL, 1, Messages.slimehatName.replace("&", "§")));
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.SLIME_BALL)) {
	      return;
	    }
	    final ItemStack slimehat = new ItemStack(Material.WOOL ,  1 , (byte) 2);
	    UtilItem.setName(slimehat, Messages.slimehatName.replace("&", "§"));
	    if (!event.getPlayer().getItemInHand().getItemMeta().equals(slimehat.getItemMeta())) {
	    	return;
	    }
	    
	    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
	    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
	    } else {
	    	
	    	event.setCancelled(true);
	    if(_time.containsKey(player)){
	    	double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.slimehatName.replace("&", "§")).replace("%time", time));
	    	return;
	    }

	    _time.put(player, Configs.slimehatCooldown);
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

	   _active.put(player, Long.valueOf(System.currentTimeMillis()));
	   
	   if(!Rocket.stationnaire.containsKey(player) || !Rocket.effect.containsKey(player)) {
	    
	playSlimeHat(player);
	   }
	    }
	    
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }
	  
	  @EventHandler
		 public void Onplayerquit(PlayerQuitEvent event) {
			 Player player = event.getPlayer();
			 if(slim.containsKey(player)) {
				
				 for (Slime s : slim.get(player)) {
					 s.remove();
				 }
				
			 }
		 }
	  
	  @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		  Player player = e.getPlayer();
			 if(slim.containsKey(player)) {
				
				 for (Slime s : slim.get(player)) {
					 s.remove();
				 }
				
			 } 
	  }


}
