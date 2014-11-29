package fr.coco_gigpn.prodigygadget.dj.plateform;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.coco_gigpn.prodigygadget.dj.PlateformManager;

public class Wood {

	public static void buildPlateform(final Player p ){

	new PlateformManager(p, Material.LOG,(byte) 7).buildRectangle2();
	new PlateformManager(p, Material.SANDSTONE,(byte) 0).buildRectangle3();
	new PlateformManager(p, Material.LEAVES,(byte) 3).buildRectangle4();

	}


}
