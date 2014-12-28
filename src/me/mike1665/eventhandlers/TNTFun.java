package me.mike1665.eventhandlers;

import java.util.List;

import me.mike1665.Main.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class TNTFun implements Listener{
	
	private Main plugin;
	public TNTFun() {
		this.plugin = Main.getInstance();
	}
	
	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.RED + "Fun Bomb"))){
			 	final TNTPrimed tnt = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), TNTPrimed.class);
				player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) + "Explosion incoming!");
				player.playSound(player.getLocation(), Sound.FUSE, 10, 10);
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			      if (player.getItemInHand().getAmount() == 1) {
			    	  player.setItemInHand(null);
			      }
				 new BukkitRunnable() {
					 
			            @Override
			            public void run() {
			            	Location location = tnt.getLocation();
			            	player.playSound(player.getLocation(), Sound.EXPLODE, 10, 10);
			    			double radius = 5D;
			    			List<Entity> near = player.getNearbyEntities(10.0d, 10.0d, 10.0d);
			    			for(Entity e : near) {
			    				e.setVelocity(new Vector(0, 2, 0));
			    			}
			            	tnt.remove();
			            }
			 
			        }.runTaskLater(plugin, 60);
				event.setCancelled(true);
		 		}
			}
		}
	
	public String disName(ItemStack i)
	{
	  if (i == null) {
	    return null;
	  }
	  if (!i.hasItemMeta()) {
	    return null;
	  }
	  if (!i.getItemMeta().hasDisplayName()) {
	    return null;
	  }
	  return i.getItemMeta().getDisplayName();
	}
}