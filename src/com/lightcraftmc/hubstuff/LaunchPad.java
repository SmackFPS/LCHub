package com.lightcraftmc.hubstuff;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchPad
  implements Listener
{
  @EventHandler
  public void launch(PlayerMoveEvent e)
  {
    if ((e.getPlayer().getLocation().getWorld().getBlockAt(e.getPlayer().getLocation()).getType() == Material.STONE_PLATE) && (e.getPlayer().getLocation().getWorld().getBlockAt(e.getPlayer().getLocation()).getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_BLOCK) && 
      ((e.getPlayer() instanceof Player)))
    {
      e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(5.0D));
      e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
      e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.WITHER_SHOOT, 10.0F, 10.0F);
      e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
    }
  }
}


/* Location:           A:\LC\Lobby\plugins\HubPlugin.jar
 * Qualified Name:     com.lightcraftmc.hubstuff.LaunchPad
 * JD-Core Version:    0.7.0.1
 */