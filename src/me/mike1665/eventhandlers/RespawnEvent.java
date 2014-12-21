package me.mike1665.eventhandlers;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.lightcraftmc.fusebox.strings.MessageType;
import net.lightcraftmc.fusebox.strings.StringManager;

public class RespawnEvent implements Listener{
	
	
	@EventHandler
	public void onPlayerRespawn(PlayerJoinEvent event) {
	Player p = event.getPlayer();
		ItemStack hub = new ItemStack(Material.CHEST, 1);
		ItemMeta hu = hub.getItemMeta();
		hu.setDisplayName(ChatColor.LIGHT_PURPLE + "Hub Menu " + ChatColor.GRAY + "(Right-Click)");
		hub.setItemMeta(hu);
	    p.getInventory().setItem(4, hub);
	}
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event)
	{
	  Player p = event.getPlayer();
	  if (p.getItemInHand().getType() == Material.CHEST && p.getItemInHand().hasItemMeta() && event.getAction().toString().contains("RIGHT")) {
		  p.openInventory(me.mike1665.menu.CosmeticsMenu.cosmenu(p));
		  p.playSound(p.getLocation(), Sound.DOOR_OPEN, 10, 10);
		  p.sendMessage(StringManager.getPrefix(MessageType.INFO) + "Opened Hub Menu.");
		  event.setCancelled(true);

	  }
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Hub Menu " + ChatColor.GRAY + "(Right-Click)"))){
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
	if(event.getCause().equals(TeleportCause.ENDER_PEARL)) { 
		event.setCancelled(true);
		final org.bukkit.entity.Firework fw = (org.bukkit.entity.Firework) event.getPlayer().getWorld().spawnEntity(event.getTo(), EntityType.FIREWORK);
		Builder builder = FireworkEffect.builder();
		FireworkMeta m = fw.getFireworkMeta();
		builder.trail(true);
		builder.withColor(Color.PURPLE);
		builder.with(Type.BURST);
		builder.withFade(Color.PURPLE);
		m.addEffect(builder.build());
		m.setPower(0);
		fw.detonate();
		fw.setFireworkMeta(m); 
		
		}
	}
	
	@EventHandler
	public void removegadget(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		p.getInventory().setItem(2, null);
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
