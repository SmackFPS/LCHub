package me.jrl1004.lightcraft.mounts;

import java.util.Arrays;
import java.util.List;

import me.jrl1004.lightcraft.utils.Config;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
		if(!mountIsUnlocked(player, mount)) return;
		
	}
	
	public void spawnMountForPlayer(Player player, MountType type) {
		
	}
}
