package me.mike1665.mount.mounts;

import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.mount.MountManager;
import me.mike1665.utils.UtilityBlock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class AngelRider implements Listener {

	private static Main plugin;

	public static void initialize(Main plugin) {
		AngelRider.plugin = plugin;
	}

	public static void playAngelRider(Player p) {
		UUID pn = p.getPlayer().getUniqueId();
		boolean check = plugin.getConfig().getBoolean(pn + ".AngelMount");
		if (!check && LcCoinsAPI.hasEnough(p, 10000)) {
			LcCoinsAPI.takePoints(p, 10000);
			plugin.getConfig().set(pn + ".AngelMount", true);
			plugin.saveFile();
			p.sendMessage(StringManager.getPrefix(MessageType.INFO)
					+ ChatColor.GREEN + "" + ChatColor.BOLD
					+ "Mount Purchased!");
			p.sendMessage(StringManager.getPrefix(MessageType.INFO)
					+ ChatColor.AQUA
					+ "Note: Click on your mount again to spawn your new mount! ");

		} else if (check) {
			if (Bukkit.getWorld("world2") != null) {
				World w = p.getWorld();
				double x = plugin.getConfig().getDouble("mount.x");
				double y = plugin.getConfig().getDouble("mount.y");
				double z = plugin.getConfig().getDouble("mount.z");
				Location mountspawnloc = new Location(w, x, y, z);

				MountManager.removeCurrentPet(p, false);

				Horse horse = (Horse) p.getWorld().spawn(mountspawnloc,
						Horse.class);

				Entity entity = horse;
				Horse entityHorse = (Horse) entity;
				entityHorse.getInventory().setSaddle(
						new ItemStack(Material.SADDLE));

				horse.setCustomNameVisible(true);
				horse.setOwner(p);
				horse.setVariant(Horse.Variant.HORSE);
				horse.setColor(Horse.Color.WHITE);
				horse.setAdult();
				horse.setPassenger(p);

				horse.setMetadata("angelrider", new FixedMetadataValue(
						Main.schedule, "angelrider"));
				MountManager.pet.put(p.getUniqueId(), horse);
			}
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if ((event.getFrom().getBlockX() != event.getTo().getBlockX())
				|| (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) {
			Player p = event.getPlayer();
			Entity e = p.getVehicle();

			if ((UtilityBlock.frostyBlock.contains(p.getLocation().getBlock()))
					&& (!p.isInsideVehicle())) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100,
						2));
				p.addPotionEffect(new PotionEffect(
						PotionEffectType.SLOW_DIGGING, 100, 5));
				// new UtilParticle(UtilParticle.Particle.SNOW_SHOVEL,
				// 0.1000000014901161D, 10,
				// 2.0D).sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
				// new UtilParticle(UtilParticle.Particle.SNOWBALL,
				// 0.1000000014901161D, 10,
				// 2.0D).sendToLocation(p.getLocation().add(0.0D, 1.0D, 0.0D));
				UtilityBlock.sendBreak(p, 80, (byte) 0, p.getLocation()
						.getBlock().getLocation());
			}

			if ((e instanceof Horse)) {
				if (e.hasMetadata("angelrider")) {
					int id = 80;
					int height = -1;
					double r = Math.random();
					if (r > 0.8D) {
						id = 79;
						height = -1;
					} else if (r > 0.6D) {
						id = 174;
						height = -1;
					} else if (r > 0.4D) {
						id = 78;
						height = 0;
					}
					for (Block block : UtilityBlock
							.getInRadius(
									((Horse) MountManager.pet.get(p
											.getUniqueId())).getLocation(),
									3.5D, true).keySet()) {
						if ((p.getLocation().getBlock().getType() != Material.WATER)
								&& (p.getLocation().getBlock().getType() != Material.STATIONARY_WATER)
								&& (p.getLocation().getBlock().getType() != Material.SNOW_BLOCK)) {
							if (UtilityBlock.solid(block)) {
								if (!UtilityBlock.blockToRestore
										.contains(block)) {
									Block b = UtilityBlock.getHighest(block
											.getWorld(), block.getLocation()
											.getBlockX(), block.getLocation()
											.getBlockZ());

									Location l = b.getLocation().add(0.0D,
											height, 0.0D);
									UtilityBlock.setBlockToRestore(
											l.getBlock(), id, (byte) 0, 4L,
											true, true, false);
								}
							}
						}
					}
				}
			}
		}
	}
}