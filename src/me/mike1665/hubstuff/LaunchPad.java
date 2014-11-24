package me.mike1665.hubstuff;


import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchPad implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void launch(PlayerMoveEvent e) {
		if ((e.getPlayer().getLocation().getWorld().getBlockAt(e.getPlayer().getLocation()).getType() == Material.STONE_PLATE) && (e.getPlayer().getLocation().getWorld().getBlockAt(e.getPlayer().getLocation()).getRelative(BlockFace.DOWN).getType() == Material.REDSTONE_BLOCK)) {
			if ((e.getPlayer() instanceof Player)) {
				e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(5.0D));
				e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getX()));
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.WITHER_SHOOT, 10, 10);
				e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
			}
		}
	}

}
