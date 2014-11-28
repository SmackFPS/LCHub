package me.mike1665.funstuff;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PvPSword implements Listener{
	
	public static HashMap<Player, Boolean> pvp = new HashMap<Player, Boolean>();
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerJoinEvent event) {
	Player p = event.getPlayer();
		ItemStack sword = new ItemStack(Material.CHEST, 1);
		ItemMeta pvp = sword.getItemMeta();
		pvp.setDisplayName(ChatColor.LIGHT_PURPLE + "PvP Sword " + ChatColor.GRAY + "(Right-Click to activate)");
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add("Make sure others have this enabled!");
		pvp.setLore(Lore);
		sword.setItemMeta(pvp);
	    p.getInventory().setItem(3, sword);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			  if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "PvP Sword " + ChatColor.GRAY + "(Right-Click to activate)"))){
			 	pvp.put(player, true);
			 	player.sendMessage("PVP Enabled for yourself! You can only attack people that have this enabled!");
			} else if(pvp.containsKey(player)) {
				pvp.remove(player);
				pvp.put(player, false);
				player.sendMessage("PVP Disabled!");
			}
		  }
		}
	
	@EventHandler
	public void parkourreturn(EntityDamageEvent event) {
		Player p = (Player) event.getEntity();
		if (pvp.containsKey(p)) {
			if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
				event.setCancelled(false);
				}
		}else if (!pvp.containsKey(p)) {
			if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK || event.getCause() == EntityDamageEvent.DamageCause.CONTACT) {
				event.setCancelled(true);
				p.sendMessage("PVP Protected.");
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
