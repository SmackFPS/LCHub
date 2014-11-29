package fr.coco_gigpn.prodigygadget.block;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class BlockManager {
	
	static HashMap<Location, Material> saves = new HashMap<Location, Material>();

	public static void saveBlock(World w, Location l) {
		Block b = w.getBlockAt(l);
		Material m = b.getType();
		if(!(saves.containsKey(l))) {
			saves.put(l, m);
		}
	}

	public static void loadAllBlock(World w) {

		for (Entry<Location, Material> entry : saves.entrySet()) {
			Location l = entry.getKey();
			Material key = entry.getValue();
			Bukkit.getWorld(w.getName()).getBlockAt(l).setType(key);
		}
		saves.clear();


	}

}
