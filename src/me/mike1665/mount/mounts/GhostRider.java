package me.mike1665.mount.mounts;

import java.util.Iterator;
import java.util.UUID;

import me.mike1665.Main.Main;
import me.mike1665.coinapi.LcCoinsAPI;
import me.mike1665.mount.MountManager;
import me.mike1665.utils.UtilityBlock;
import net.minecraft.server.v1_8_R1.AttributeInstance;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.PathEntity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import com.arrayprolc.strings.MessageType;
import com.arrayprolc.strings.StringManager;

public class GhostRider implements Listener {

	private static Main plugin;

	public static void initialize(Main plugin) {
		GhostRider.plugin = plugin;
	}

	public static void playGhostRider(Player p) {
		UUID pn = p.getPlayer().getUniqueId();
		boolean check = plugin.getConfig().getBoolean(pn + ".GhostMount");
		if (!check && LcCoinsAPI.hasEnough(p, 10000)) {
			LcCoinsAPI.takePoints(p, 10000);
			plugin.getConfig().set(pn + ".GhostMount", true);
			plugin.saveFile();
			p.sendMessage(StringManager.getPrefix(MessageType.INFO)
					+ ChatColor.GREEN + "" + ChatColor.BOLD
					+ "Mount Purchased!");
			p.sendMessage(StringManager.getPrefix(MessageType.INFO)
					+ ChatColor.AQUA
					+ "Note: Click on your mount again to spawn your new mount! ");

		} else if (check) {
			if (Bukkit.getWorld("world") != null) {
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
				
				horse.setCustomName(ChatColor.AQUA + "" + ChatColor.BOLD
						+ p.getPlayer().getName() + ChatColor.RESET
						+ "'s Horse");
				horse.setCustomNameVisible(true);
				horse.setOwner(p);
				horse.setVariant(Horse.Variant.SKELETON_HORSE);
				horse.setAdult();
				horse.setPassenger(p);

				horse.setMetadata("ghostrider", new FixedMetadataValue(
						Main.schedule, "ghostrider"));
				MountManager.pet.put(p.getUniqueId(), horse);
				PetFollow(p.getPlayer(), horse, 0.3);

			} else {
				p.sendMessage(StringManager.getPrefix(MessageType.ERROR)
						+ ChatColor.DARK_RED
						+ "You cannot spawn mounts outside of the Hub world! ");
			}
		}
		else {
		   	p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Mounts" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "> " + ChatColor.RED + "Insufficient Funds!");
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if ((event.getFrom().getBlockX() != event.getTo().getBlockX())
				|| (event.getFrom().getBlockZ() != event.getTo().getBlockZ())) {
			Player p = event.getPlayer();
			Entity e = p.getVehicle();

			if ((e instanceof Horse)) {
				if (e.hasMetadata("ghostrider")) {
					byte color = 2;
					double r = Math.random();
					if (r > 0.8D)
						color = 14;
					else if (r > 0.6D)
						color = 1;
					else if (r > 0.2D) {
						color = 4;
					}

					Iterator localIterator = UtilityBlock
							.getInRadius(
									((Horse) MountManager.pet.get(p
											.getUniqueId())).getLocation(),
									2.5D, true).keySet().iterator();

					while (localIterator.hasNext()) {
						Block block = (Block) localIterator.next();
						if (UtilityBlock.solid(block)) {
							if (!UtilityBlock.blockToRestore.contains(block)) {
								UtilityBlock.setBlockToRestore(block, 159,
										color, 2L, true, false, false);
							}
						}
					}
				}
			}
		}
	}
	
	public static void PetFollow(final Player player , final Entity pet , final double speed){
		new BukkitRunnable(){
		public void run(){
		if ((!pet.isValid() || (!player.isOnline()))){
		this.cancel();}
		net.minecraft.server.v1_8_R1.Entity pett = ((CraftEntity) pet).getHandle();
		((EntityInsentient) pett).getNavigation().a(2);
		Object petf = ((CraftEntity) pet).getHandle();
		Location targetLocation = player.getLocation();
		PathEntity path;
		path = ((EntityInsentient) petf).getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
		if (path != null) {
		((EntityInsentient) petf).getNavigation().a(path, 1.0D);
		((EntityInsentient) petf).getNavigation().a(2.0D);}
		int distance = (int) Bukkit.getPlayer(player.getName()).getLocation().distance(pet.getLocation());
		if (distance > 10 && !pet.isDead() && player.isOnGround()) {
		pet.teleport(player.getLocation());}
		AttributeInstance attributes = ((EntityInsentient)((CraftEntity)pet).getHandle()).getAttributeInstance(GenericAttributes.d);
		attributes.setValue(speed);}}.runTaskTimer(Main.schedule, 0L, 20L);}
}