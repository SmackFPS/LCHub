package me.mike1665.funstuff;

import me.mike1665.Main.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import de.slikey.effectlib.effect.DiscoBallEffect;

public class DiscoBall implements Listener{
	
	
	  public Main plugin;
	  
	  public DiscoBall(Main plugin)
	  {
	    this.plugin = plugin;
	  }
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerPlaceBlock(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if ((disName(player.getItemInHand()) != null) && (disName(player.getItemInHand()).equalsIgnoreCase(ChatColor.DARK_BLUE + "Disco Ball"))){
			 spawnDisco(player);
			 player.setItemInHand(null);
		 }
	  }
	}
	
	 public void spawnDisco(final Player p) {
		 final Location l= p.getEyeLocation();
		 l.add(0, 5, 0).getBlock().setType(Material.DIAMOND_BLOCK);
		 final DiscoBallEffect discoBallEffect = new DiscoBallEffect(this.plugin.manager);
	     discoBallEffect.setLocation(l);
	     discoBallEffect.start();
		 p.sendMessage("Tried to run effect");
		 new BukkitRunnable() {
			 
			int count = 1;
			@Override
			public void run() {
				l.getBlock().setType(Material.AIR);
				discoBallEffect.cancel();
				count--;
				if (count == 0) cancel();
			}
		}.runTaskTimer(plugin, 20*40, 1);
	 }
	 
	 
	public String disName(ItemStack i) {
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
