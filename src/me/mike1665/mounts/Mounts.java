package me.mike1665.mounts;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Mounts implements Listener{
	
	  public HashSet<Player> _owners = new HashSet();
	  public HashMap<Player, Entity> _active = new HashMap();
	  

	  public HashSet<Player> GetOwners()
	  {
	    return this._owners;
	  }

	  public HashMap<Player, Entity> GetActive()
	  {
	    return this._active;
	  }

	  public boolean IsActive(Player player)
	  {
	    return this._active.containsKey(player);
	  }

	  public boolean HasMount(Player player)
	  {
	    return this._owners.contains(player);
	  }
	  
	  public void removeMount(Player player)
	  {
	    Horse horse = (Horse)this._active.remove(player);
	    if (horse != null)
	    {
	      horse.remove();

	      player.sendMessage("Your other mount was de-spawned!");
	    }
	  }
}
