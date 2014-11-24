package me.mike1665.mounts;

import me.mike1665.Main.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MountManager implements Listener{
	
	static Entity ent;
	@SuppressWarnings("unused")
	private static Main plugin;
	
	public static void initialize(Main plugin){
		MountManager.plugin = plugin;
	}
	
	  @EventHandler
	  public void HorseInteract(PlayerInteractEntityEvent event)
	  {
	    if (!(event.getRightClicked() instanceof Horse)) {
	      return;
	    }
	    Player player = event.getPlayer();
	    Horse horse = (Horse)event.getRightClicked();

	    if ((horse.getOwner() == null) || (!horse.getOwner().equals(player)))
	    {
	      player.sendMessage(ChatColor.RED + "This is not your mount.");
	      event.setCancelled(true);
	    }
	  }
	  
	  @EventHandler
	  public void LeashDropCancel(ItemSpawnEvent event)
	  {
	    if (event.getEntity().getItemStack().getType() == Material.LEASH)
	      event.setCancelled(true);
	  }
}
