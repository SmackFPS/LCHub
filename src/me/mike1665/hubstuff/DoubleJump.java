package me.mike1665.hubstuff;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump
  implements Listener
{
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    p.setAllowFlight(true);
    p.setFlying(false);
  }
  
  @EventHandler
  public void onPlayerFly(PlayerToggleFlightEvent e)
  {
    Player p = e.getPlayer();
    if (p.getGameMode() != GameMode.CREATIVE)
    {
      e.setCancelled(true);
      p.setAllowFlight(false);
      p.setFlying(false);
      p.setVelocity(p.getLocation().getDirection().multiply(3.0D)
        .setY(0.7D));
      p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
    }
  }
  
  @EventHandler
  public void move(PlayerMoveEvent e)
  {
    Player p = e.getPlayer();
    if ((e.getPlayer().getGameMode() != GameMode.CREATIVE) && 
      (p.getLocation().getBlock().getRelative(BlockFace.DOWN)
      .getType() != Material.AIR)) {
      p.setAllowFlight(true);
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     me.mike1665.hubstuff.DoubleJump
 * JD-Core Version:    0.7.0.1
 */