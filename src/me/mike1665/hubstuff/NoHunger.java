package me.mike1665.hubstuff;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHunger
  implements Listener
{
  @EventHandler
  public void hunger(FoodLevelChangeEvent event)
  {
    Player player = (Player)event.getEntity();
    event.setCancelled(true);
    player.setFoodLevel(20);
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.hubstuff.NoHunger
 * JD-Core Version:    0.7.0.1
 */