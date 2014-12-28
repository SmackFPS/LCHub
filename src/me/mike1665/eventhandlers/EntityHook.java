package me.mike1665.eventhandlers;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.lightcraftmc.fusebox.util.strings.MessageType;
import net.lightcraftmc.fusebox.util.strings.StringManager;

public class EntityHook implements Listener{
	
	@SuppressWarnings("unused")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.DARK_BLUE + "Entity Hook"))){
			 	player.playSound(player.getLocation(), Sound.ITEM_BREAK, 10, 10);
				player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) +  "Nearby entities have been hooked!");
				player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
			      if (player.getItemInHand().getAmount() == 1) {
			    	  player.setItemInHand(null);
			      }
				double radius = 5D;
				List<Entity> near = player.getNearbyEntities(10.0d, 10.0d, 10.0d);
				for(Entity e : near) {
					if(e.getType() == EntityType.PLAYER) {
					Player entity = (Player) e;
					player.playSound(player.getLocation(), Sound.LAVA_POP, 10, 10);
					player.sendMessage(StringManager.getPrefix(MessageType.GADGETS) +  "You have been hooked!");
					}
					e.setVelocity(new Vector(0, 3, 0));
				}
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