package me.jrl1004.lightcraft.mounts;

import java.util.Arrays;
import java.util.List;

import me.jrl1004.lightcraft.utils.Config;
import me.jrl1004.lightcraft.utils.LcConstants;
import me.mike1665.Main.Main;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class MountManager {
	private static MountManager instance;

	private MountManager() {
	}

	public static MountManager getInstance() {
		if (instance == null)
			instance = new MountManager();
		return instance;
	}

	public boolean mountIsUnlocked(OfflinePlayer player, String mount) {
		String[] mounts = Config.getInstance().getUnlockedMounts(player);
		if (mounts.length == 0)
			return false;
		List<String> l = Arrays.asList(mounts);
		return l.contains(mount);
	}

	public void unlockMount(OfflinePlayer player, String mount) {
		Config.getInstance().unlockMount(player, mount);
	}

	public void lockMount(OfflinePlayer player, String mount) {
		if (!mountIsUnlocked(player, mount))
			return;

	}

	public void spawnMountForPlayer(Player player, MountType type) {
		Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
		horse.setAdult();
		horse.setColor(type.getColor());
		horse.setStyle(type.getStyle());
		horse.setVariant(type.getVariant());
		horse.setJumpStrength(1D);
		horse.setTamed(true);
		horse.setOwner(player);
		horse.setCustomName(type.getName(player));
		horse.setCustomNameVisible(true);
		horse.setMetadata(LcConstants.MOUNT_METADATA_STRING, new FixedMetadataValue(Main.getInstance(), true));
	}

	public boolean isEntityAMount(Entity e) {
		return e.hasMetadata(LcConstants.MOUNT_METADATA_STRING);
	}
}
