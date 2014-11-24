package me.mike1665.hubstuff;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger implements Listener{
	
	@EventHandler
	public void hunger(FoodLevelChangeEvent event) {
		Player player = (Player) event.getEntity();
		event.setCancelled(true);
		player.setFoodLevel(20);
	}

}
