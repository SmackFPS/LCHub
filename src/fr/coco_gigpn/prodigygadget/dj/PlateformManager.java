package fr.coco_gigpn.prodigygadget.dj;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

import fr.coco_gigpn.prodigygadget.block.BlockManager;
import fr.coco_gigpn.prodigygadget.dj.effect.Vague;
import fr.coco_gigpn.prodigygadget.util.UtilBlock;

public class PlateformManager {
	
	public static HashMap<Player ,ArrayList<Location>> saveLoc = new HashMap<Player, ArrayList<Location>>();
	
	private Player p;
	private Material m;
	private byte data;
	public PlateformManager(Player p, Material m , byte data) {
		this.p = p;
		this.m = m;
		this.data = data;
	}
	
	public void buildRectangle2() {
		Location l = p.getLocation();
		l.setY(l.getY() - 1);
		
		saveLoc.put(p, new ArrayList<Location>());

		Location l2 = new Location(p.getWorld(), l.getX() +1, l.getY(), l.getZ());
		Location l3 = new Location(p.getWorld(), l.getX() -1, l.getY(), l.getZ());
		Location l4 = new Location(p.getWorld(), l.getX() , l.getY(), l.getZ() -1);
		Location l5 = new Location(p.getWorld(), l.getX() , l.getY(), l.getZ() +1);
		Location l6 = new Location(p.getWorld(), l.getX()+ 1 , l.getY(), l.getZ() +1);
		Location l7 = new Location(p.getWorld(), l.getX()+ 1 , l.getY(), l.getZ() -1);
		Location l8 = new Location(p.getWorld(), l.getX()- 1 , l.getY(), l.getZ() -1);
		Location l9 = new Location(p.getWorld(), l.getX()- 1 , l.getY(), l.getZ() +1);
		
		((ArrayList<Location>)saveLoc.get(p)).add(l2);
		((ArrayList<Location>)saveLoc.get(p)).add(l3);
		((ArrayList<Location>)saveLoc.get(p)).add(l4);
		((ArrayList<Location>)saveLoc.get(p)).add(l5);
		((ArrayList<Location>)saveLoc.get(p)).add(l6);
		((ArrayList<Location>)saveLoc.get(p)).add(l7);
		((ArrayList<Location>)saveLoc.get(p)).add(l8);
		((ArrayList<Location>)saveLoc.get(p)).add(l9);
		
		BlockManager.saveBlock(p.getWorld(), l);
		BlockManager.saveBlock(p.getWorld(), l2);
		BlockManager.saveBlock(p.getWorld(), l3);
		BlockManager.saveBlock(p.getWorld(), l4);
		BlockManager.saveBlock(p.getWorld(), l5);
		BlockManager.saveBlock(p.getWorld(), l6);
		BlockManager.saveBlock(p.getWorld(), l7);
		BlockManager.saveBlock(p.getWorld(), l8);
		BlockManager.saveBlock(p.getWorld(), l9);

		UtilBlock.setBlock(Material.GLOWSTONE,(byte) 0, l);
		UtilBlock.setBlock(m, data, l2);
		UtilBlock.setBlock(m, data, l3);
		UtilBlock.setBlock(m, data, l4);
		UtilBlock.setBlock(m, data, l5);
		UtilBlock.setBlock(m, data, l6);
		UtilBlock.setBlock(m, data, l7);
		UtilBlock.setBlock(m, data, l8);
		UtilBlock.setBlock(m, data, l9);
	}

	public void buildRectangle3() {

		Location l = p.getLocation();

		Location l2 = new Location(p.getWorld(), l.getX() +2, l.getY() -1 , l.getZ());
		Location l3 = new Location(p.getWorld(), l.getX() , l.getY()  -1, l.getZ() + 2);
		Location l4 = new Location(p.getWorld(), l.getX() , l.getY() -1, l.getZ() -2);
		Location l5 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1 , l.getZ());
		Location l6 = new Location(p.getWorld(), l.getX() +2 , l.getY() -1, l.getZ() -2);
		Location l7 = new Location(p.getWorld(), l.getX() -2 , l.getY() -1, l.getZ() +2);
		Location l8 = new Location(p.getWorld(), l.getX() -2 , l.getY() -1, l.getZ() -2);
		Location l9 = new Location(p.getWorld(), l.getX() +2 , l.getY() -1, l.getZ() +2);

		Location l10 = new Location(p.getWorld(), l.getX() +2, l.getY()-1, l.getZ() - 1);
		Location l11 = new Location(p.getWorld(), l.getX() +2 , l.getY()-1, l.getZ() + 1);
		Location l12 = new Location(p.getWorld(), l.getX() +1, l.getY()-1, l.getZ() +2);
		Location l13 = new Location(p.getWorld(), l.getX() -1 , l.getY()-1 , l.getZ() +2);
		Location l14 = new Location(p.getWorld(), l.getX() +1 , l.getY() -1, l.getZ() -2);
		Location l15 = new Location(p.getWorld(), l.getX() -1 , l.getY()-1, l.getZ() -2);
		Location l16 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1, l.getZ() -1);
		Location l17 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1, l.getZ() +1);

		BlockManager.saveBlock(p.getWorld(), l);
		BlockManager.saveBlock(p.getWorld(), l2);
		BlockManager.saveBlock(p.getWorld(), l3);
		BlockManager.saveBlock(p.getWorld(), l4);
		BlockManager.saveBlock(p.getWorld(), l5);
		BlockManager.saveBlock(p.getWorld(), l6);
		BlockManager.saveBlock(p.getWorld(), l7);
		BlockManager.saveBlock(p.getWorld(), l8);
		BlockManager.saveBlock(p.getWorld(), l9);
		
		BlockManager.saveBlock(p.getWorld(), l10);
		BlockManager.saveBlock(p.getWorld(), l11);
		BlockManager.saveBlock(p.getWorld(), l12);
		BlockManager.saveBlock(p.getWorld(), l13);
		BlockManager.saveBlock(p.getWorld(), l14);
		BlockManager.saveBlock(p.getWorld(), l15);
		BlockManager.saveBlock(p.getWorld(), l16);
		BlockManager.saveBlock(p.getWorld(), l17);

		UtilBlock.setBlock(m, data, l2);
		UtilBlock.setBlock(m, data, l3);
		UtilBlock.setBlock(m, data, l4);
		UtilBlock.setBlock(m, data, l5);
		UtilBlock.setBlock(m, data, l6);
		UtilBlock.setBlock(m, data, l7);
		UtilBlock.setBlock(m, data, l8);
		UtilBlock.setBlock(m, data, l9);
		UtilBlock.setBlock(m, data, l10);
		UtilBlock.setBlock(m, data, l11);
		UtilBlock.setBlock(m, data, l12);
		UtilBlock.setBlock(m, data, l13);
		UtilBlock.setBlock(m, data, l14);
		UtilBlock.setBlock(m, data, l15);
		UtilBlock.setBlock(m, data, l16);
		UtilBlock.setBlock(m, data, l17);

	}
	
	@SuppressWarnings("deprecation")
	public void buildRectangle4() {

		Location l = p.getLocation();
		l.setY(l.getY() + 1);

		Location l2 = new Location(p.getWorld(), l.getX() +2, l.getY() -1 , l.getZ());
		Location l3 = new Location(p.getWorld(), l.getX() , l.getY()  -1, l.getZ() + 2);
		Location l4 = new Location(p.getWorld(), l.getX() , l.getY() -1, l.getZ() -2);
		Location l5 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1 , l.getZ());
		Location l6 = new Location(p.getWorld(), l.getX() +2 , l.getY() -1, l.getZ() -2);
		Location l7 = new Location(p.getWorld(), l.getX() -2 , l.getY() -1, l.getZ() +2);
		Location l8 = new Location(p.getWorld(), l.getX() -2 , l.getY() -1, l.getZ() -2);
		Location l9 = new Location(p.getWorld(), l.getX() +2 , l.getY() -1, l.getZ() +2);

		Location l10 = new Location(p.getWorld(), l.getX() +2, l.getY()-1, l.getZ() - 1);
		Location l11 = new Location(p.getWorld(), l.getX() +2 , l.getY()-1, l.getZ() + 1);
		Location l12 = new Location(p.getWorld(), l.getX() +1, l.getY()-1, l.getZ() +2);
		Location l13 = new Location(p.getWorld(), l.getX() -1 , l.getY()-1 , l.getZ() +2);
		Location l14 = new Location(p.getWorld(), l.getX() +1 , l.getY() -1, l.getZ() -2);
		Location l15 = new Location(p.getWorld(), l.getX() -1 , l.getY()-1, l.getZ() -2);
		Location l16 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1, l.getZ() -1);
		Location l17 = new Location(p.getWorld(), l.getX() -2 , l.getY()-1, l.getZ() +1);

		Location l18 = new Location(p.getWorld(), l.getX() +3 , l.getY() -1, l.getZ());
		Location l19 = new Location(p.getWorld(), l.getX(), l.getY()-1, l.getZ() -3);
		Location l20 = new Location(p.getWorld(), l.getX() -3 , l.getY()-1, l.getZ() );
		Location l21 = new Location(p.getWorld(), l.getX() , l.getY()-1, l.getZ() +3);
		
	
		BlockManager.saveBlock(p.getWorld(), l);
		BlockManager.saveBlock(p.getWorld(), l2);
		BlockManager.saveBlock(p.getWorld(), l3);
		BlockManager.saveBlock(p.getWorld(), l4);
		BlockManager.saveBlock(p.getWorld(), l5);
		BlockManager.saveBlock(p.getWorld(), l6);
		BlockManager.saveBlock(p.getWorld(), l7);
		BlockManager.saveBlock(p.getWorld(), l8);
		BlockManager.saveBlock(p.getWorld(), l9);
		
		BlockManager.saveBlock(p.getWorld(), l10);
		BlockManager.saveBlock(p.getWorld(), l11);
		BlockManager.saveBlock(p.getWorld(), l12);
		BlockManager.saveBlock(p.getWorld(), l13);
		BlockManager.saveBlock(p.getWorld(), l14);
		BlockManager.saveBlock(p.getWorld(), l15);
		BlockManager.saveBlock(p.getWorld(), l16);
		BlockManager.saveBlock(p.getWorld(), l17);

		
		UtilBlock.setBlock(Material.PISTON_BASE,(byte) 0, l2);
		UtilBlock.setBlock(Material.PISTON_BASE,(byte) 0, l3);
		UtilBlock.setBlock(Material.PISTON_BASE,(byte) 0, l4);
		UtilBlock.setBlock(Material.PISTON_BASE,(byte) 0, l5);
		UtilBlock.setBlock(Material.JUKEBOX,(byte) 0, l6);
		UtilBlock.setBlock(Material.JUKEBOX,(byte) 0, l7);
		UtilBlock.setBlock(Material.JUKEBOX,(byte) 0, l8);
		UtilBlock.setBlock(Material.JUKEBOX,(byte) 0, l9);
		UtilBlock.setBlock(m, data, l10);
		UtilBlock.setBlock(m, data, l11);
		UtilBlock.setBlock(m, data, l12);
		UtilBlock.setBlock(m, data, l13);
		UtilBlock.setBlock(m, data, l14);
		UtilBlock.setBlock(m, data, l15);
		UtilBlock.setBlock(m, data, l16);
		UtilBlock.setBlock(m, data, l17);
		
		BlockManager.saveBlock(p.getWorld(), l18);
		BlockManager.saveBlock(p.getWorld(), l19);
		BlockManager.saveBlock(p.getWorld(), l20);
		BlockManager.saveBlock(p.getWorld(), l21);
		
		l18.getBlock().setType(Material.SKULL);
		l19.getBlock().setType(Material.SKULL);
		l20.getBlock().setType(Material.SKULL);
		l21.getBlock().setType(Material.SKULL);
		
		Skull s1 = (Skull) l18.getBlock().getState();
		s1.setSkullType(SkullType.PLAYER);
		s1.setOwner("3i5g00d");
		s1.update(true);
		Skull s2 = (Skull) l19.getBlock().getState();
		s2.setSkullType(SkullType.PLAYER);
		s2.setOwner("3i5g00d");
		s2.setRotation(BlockFace.NORTH);
		s2.setRawData((byte) 2);
		s2.update(true);
		Skull s3 = (Skull) l20.getBlock().getState();
		s3.setSkullType(SkullType.PLAYER);
		s3.setRotation(BlockFace.WEST);
		s3.setRawData((byte) 4);
		s3.setOwner("3i5g00d");
		s3.update(true);
		Skull s4 = (Skull) l21.getBlock().getState();
		s4.setSkullType(SkullType.PLAYER);
		s4.setRawData((byte) 3);
		s4.setOwner("3i5g00d");
		s4.update(true);
		
		Vague.Activate(p, l18, l19, l20, l21, 5, -5, +5, -5);

		

	}

}
