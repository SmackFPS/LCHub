package me.mike1665.eventhandlers;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

public class EnderRide implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(ProjectileLaunchEvent event) {
		Player p = (Player) event.getEntity().getShooter();
		 if ((disName(p.getItemInHand()) != null) && (disName(p.getItemInHand()).equalsIgnoreCase(ChatColor.GREEN + "Ender Ride"))){
			    p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 10, 10);
			    Projectile proj = event.getEntity();
			    if (proj instanceof EnderPearl) {
			        EnderPearl pearl = (EnderPearl)proj;
			        ProjectileSource source = pearl.getShooter();
			        if (source instanceof Player) {
			            Player player = (Player)source;
			            pearl.setPassenger(player);
			        }
		 		}
			}
		}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event) {
	if(event.getCause().equals(TeleportCause.ENDER_PEARL)) { 
		event.setCancelled(true);
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