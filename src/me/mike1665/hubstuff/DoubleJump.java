package me.mike1665.hubstuff;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setAllowFlight(true);
		p.setFlying(false);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerFly(PlayerToggleFlightEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() != GameMode.CREATIVE) {
			e.setCancelled(true);
			p.setAllowFlight(false);
			p.setFlying(false);
			p.setVelocity(p.getLocation().getDirection().multiply(3.0D)
					.setY(0.7D));
			p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
		}
	}

	@EventHandler
	public void move(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
			if (p.getLocation().getBlock().getRelative(BlockFace.DOWN)
					.getType() != Material.AIR) {
				p.setAllowFlight(true);
			}
		}
	}

}
