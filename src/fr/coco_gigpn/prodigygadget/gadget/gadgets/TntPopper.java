package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;
import net.minecraft.server.v1_8_R1.EntityItem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
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
import org.bukkit.util.Vector;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilMath;

public class TntPopper implements Listener {
	
	 private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	  final public static HashMap<Player, Integer> party = new HashMap<Player, Integer>();
	  
		 @EventHandler
		 public void OnPlayerLeft(PlayerQuitEvent e) {
			 
			 Player p = e.getPlayer();
			 if(party.containsKey(p)) {
				 Bukkit.getServer().getScheduler().cancelTask(party.get(p));
			 }
			 
	  }
	  
	  @EventHandler
	  public void onLeave(PlayerKickEvent e)
	  {
		  Player p = e.getPlayer();
		  if(party.containsKey(p)) {
			  Bukkit.getServer().getScheduler().cancelTask(party.get(p));
			 }
	  }
	
	public static void playPartyPopper(final Player p) {

		int id = Bukkit.getServer().getScheduler().runTaskTimer(Main.schedule, new Runnable() {
			
		    public void run() {
		    	Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ());
		    	
		    	
		    	ItemStack i = UtilItem.create(Material.TNT);
		    	
		    for (int k = 0 ; k < 5 ; k++) {
		        EntityItem ei = new EntityItem(
					      ((CraftWorld)loc.getWorld()).getHandle(),
					      loc.getX(),
					      loc.getY(),
					      loc.getZ(),
					      CraftItemStack.asNMSCopy(i)) {
					      public boolean a(EntityItem entityitem) {
					        return false;
					      }
					    };
					    ei.pickupDelay = 20;
					    ((Item)ei.getBukkitEntity()).setVelocity(new Vector(UtilMath.randomRange(-0.2f, 0.2f), 0.5f, UtilMath.randomRange(-0.2f, 0.2f)));
					    ((CraftWorld)loc.getWorld()).getHandle().addEntity(ei);
					    ((Item) ei.getBukkitEntity()).setMetadata("unpickable", new FixedMetadataValue(
								Main.schedule	, "unpickable"));
					    UtilItem.EntityToRemove(ei.getBukkitEntity(), 1,true);
					    
		    }
				
			
		    }
		}, 5L, 5L).getTaskId();
		party.put(p, id);
		
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        Bukkit.getServer().getScheduler().cancelTask(party.get(p));
		        GadgetManager.removeGadget(p);
		    }
		}, 20 * 10);
		
		if (!GadgetManager.hasGadget(p)){
	    	GadgetManager.addGadget(p, GadgetType.TNTPOPPER);
	    	}
		
	}
	
	 public static void activate(Player p){
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.SULPHUR, 1, Messages.tntpopperName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.SULPHUR, 1, Messages.tntpopperName.replace("&", "§")));
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.SULPHUR)) {
	      return;
	    }
	    ItemStack tntpopper = new ItemStack(Material.SULPHUR);
	    UtilItem.setName(tntpopper, Messages.tntpopperName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(tntpopper.getItemMeta())) {
	    	return;
	    }
	    
	    if(Configs.OnebyOneGadget == true && GadgetManager.hasGadget(player) && !_time.containsKey(player)) {
	    	player.sendMessage(Messages.onegadgetbygadgetmessage.replace("&", "§"));
	    } else {
	    	
	    	event.setCancelled(true);
	    if(_time.containsKey(player)){
	    	double t = arrondi(_time.get(player), 1);
	    	String time = Double.toString(t);
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.tntpopperName.replace("&", "§")).replace("%time", time));
	    	return;
	    }
	    event.setCancelled(true);
	    
	    _time.put(player, Configs.tntpopperCooldown);
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
	    
	playPartyPopper(player);
	
	    }
	    
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }

}
