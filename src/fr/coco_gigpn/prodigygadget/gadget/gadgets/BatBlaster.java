package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.ArrayList;
import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilAction;
import fr.coco_gigpn.prodigygadget.util.UtilAlg;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilEnt;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilLag;
import fr.coco_gigpn.prodigygadget.util.UtilParticle;
import fr.coco_gigpn.prodigygadget.util.UtilParticle.Particle;
import fr.coco_gigpn.prodigygadget.util.UtilServer;

public class BatBlaster implements Listener{
	
	  private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Location> _velocity = new HashMap<Player, Location>();
	  private HashMap<Player, ArrayList<Bat>> _bats = new HashMap<Player, ArrayList<Bat>>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	  
	
	
	public static void activate(Player p){
		p.getOpenInventory().close();
		if (Configs.gadgetEnabledPosition == true) {
		p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
		p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.IRON_BARDING, 1, Messages.batblasterName.replace("&", "§")));
		} else {
			p.getInventory().addItem(UtilItem.create(Material.IRON_BARDING, 1, Messages.batblasterName.replace("&", "§")));
		}
	}
	
	  @EventHandler
	  public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	  {
	    if ((event.getRightClicked() instanceof Horse))
	    {
			
			Player damager = event.getPlayer();
			
			if (damager.getItemInHand().equals(UtilItem.create(Material.IRON_BARDING, 1, Messages.batblasterName.replace("&", "§")))) {
				event.setCancelled(true);
			}

			
	    }
	  }
	
	  @SuppressWarnings({ "unchecked", "rawtypes" })
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.IRON_BARDING)) {
	      return;
	    }
	    ItemStack batblaster = new ItemStack(Material.IRON_BARDING);
	    UtilItem.setName(batblaster	, Messages.batblasterName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(batblaster.getItemMeta())) {
	    	return;
	    }
	   
	    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
	    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
	    } else {

	    	event.setCancelled(true);
	    	
	    	
	    if(_time.containsKey(player)){
	    	double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.batblasterName.replace("&", "§")).replace("%time", time));
	    	return;
	    }
	    
	    
	    _time.put(player, Configs.batblasterCooldown);
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
	 
	    
	    this._velocity.put(player, player.getEyeLocation());
	    this._bats.put(player, new ArrayList<Bat>());
	    
	    final Integer task = Bukkit.getScheduler().runTaskTimer(Main.schedule, new Runnable(){

		public void run()
	  	  {
		    for (Player cur : UtilServer.getPlayers())
		    {
		      if (_active.containsKey(cur))
		      {
		          Location loc = (Location)_velocity.get(cur);
		          

		          for (Bat bat : _bats.get(cur))
		          {
		            if (bat.isValid())
		            {
		              Vector rand = new Vector((Math.random() - 0.5D) / 3.0D, (Math.random() - 0.5D) / 3.0D, (Math.random() - 0.5D) / 3.0D);
		              bat.setVelocity(loc.getDirection().clone().multiply(0.5D).add(rand));
		              
		              for (Player other : UtilServer.getPlayers())
		              {
		                if (!other.equals(cur))
		                {

		                    if (UtilEnt.hitBox(bat.getLocation(), other, 1.0D))
		                    {

		                      UtilAction.velocity(other, UtilAlg.getTrajectory(cur, other), 0.4D, false, 0.0D, 0.2D, 10.0D, true);
		                      

		                      bat.getWorld().playSound(bat.getLocation(), Sound.BAT_HURT, 1.0F, 1.0F);
		                     
		                      
		                      bat.remove();
		  			
		                      if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
		     					 return;
		     				 } else {
		  						 UtilParticle.sendParticleToLocation(bat.getLocation(), Particle.LARGE_SMOKE, 0F, 0F, 0F, 0F, 5);
		                      }
		                      
		                    } } }
		           
		            } }
		        }
		      }
	  	  }
	    }, 1, 1).getTaskId();
	    Bukkit.getScheduler().runTaskLater(Main.schedule, new Runnable(){

			@Override
			public void run() {
				
				Bukkit.getScheduler().cancelTask(task);
				GadgetManager.removeGadget(player);
				Clear(player);
			}
        	
        }, 60L);
	    
	    for (int i = 0; i < 16; i++) {
	      ((ArrayList)this._bats.get(player)).add((Bat)player.getWorld().spawn(player.getEyeLocation(), Bat.class));
	    }
	    if (!GadgetManager.hasGadget(player)) {
	    	GadgetManager.addGadget(player, GadgetType.BATBLASTER);
	    	}
	    }
	  }

	  
	  public void Clear(Player player) {
	    this._active.remove(player);
	    this._velocity.remove(player);
	   
	    if (this._bats.containsKey(player))
	    {
	      for (Bat bat : _bats.get(player))
	      {
	        if (bat.isValid()) {
	        	if (UtilLag.ServerisLag() && Configs.stopParticleOnLag == true) {
					 return;
				 } else {
	          UtilParticle.sendParticleToLocation(bat.getLocation(), Particle.LARGE_SMOKE, 0F, 0F, 0F, 0F, 5);
	        	 }
	        }
	        bat.remove();
	      }
	      
	      this._bats.remove(player);
	    }
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }

}
