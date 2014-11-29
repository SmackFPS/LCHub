package fr.coco_gigpn.prodigygadget.gadget.gadgets;

import java.util.HashMap;

import me.mike1665.Main.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.coco_gigpn.prodigygadget.gadget.GadgetManager;
import fr.coco_gigpn.prodigygadget.gadget.GadgetManager.GadgetType;
import fr.coco_gigpn.prodigygadget.util.Configs;
import fr.coco_gigpn.prodigygadget.util.Messages;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;
import fr.coco_gigpn.prodigygadget.util.UtilGear;
import fr.coco_gigpn.prodigygadget.util.UtilItem;
import fr.coco_gigpn.prodigygadget.util.UtilTornado;

public class Tornado implements Listener {
	
	 private HashMap<Player, Long> _active = new HashMap<Player, Long>();
	  private HashMap<Player, Double> _time = new HashMap<Player, Double>();
	  HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<Player, BukkitRunnable>();
	
	@SuppressWarnings("deprecation")
	public static void playTornado(final Player p) {
		UtilTornado.spawnTornado(Main.schedule, UtilBlock.getHighest(p.getWorld(), p.getTargetBlock(null, 2).getLocation().getBlockX(), p.getTargetBlock(null, 2).getLocation().getBlockZ()).getLocation(), Material.WEB, (byte)0, p.getEyeLocation().getDirection(), 2D, 130,  4 *20L, false, false);
		Bukkit.getServer().getScheduler().runTaskLater(Main.schedule, new Runnable() {
		    public void run() {
		        
		    	GadgetManager.removeGadget(p);
		    }
		}, 20 * 5);
		
		if (!GadgetManager.hasGadget(p)){
	    	GadgetManager.addGadget(p, GadgetType.DIAMONDPARTY);
	    	}
	}
	
	 public static void activate(Player p){
		 p.getOpenInventory().close();
			if (Configs.gadgetEnabledPosition == true) {
			p.getInventory().setItem((Configs.gadgetPosition - 1), new ItemStack(Material.AIR));
			p.getInventory().setItem((Configs.gadgetPosition - 1), UtilItem.create(Material.WEB, 1, Messages.tornadogadgetName.replace("&", "§")));
			} else {
				p.getInventory().addItem(UtilItem.create(Material.WEB, 1, Messages.tornadogadgetName.replace("&", "§")));
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
	    if (!UtilGear.isMat(event.getPlayer().getItemInHand(), Material.WEB)) {
	      return;
	    }
	    ItemStack tornadogadget = new ItemStack(Material.WEB);
	    UtilItem.setName(tornadogadget, Messages.tornadogadgetName.replace("&", "§"));
	    if (!player.getItemInHand().getItemMeta().equals(tornadogadget.getItemMeta())) {
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
	    	player.sendMessage(Messages.cooldownMessage.replace("&", "§").replace("%gadget", Messages.tornadogadgetName.replace("&", "§")).replace("%time", time));
	    	return;
	    }
	    _time.put(player, Configs.tornadogadgetCooldown);
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

	    playTornado(player);
	
	    }
	    
	  }
	  
	  public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	  }
}
